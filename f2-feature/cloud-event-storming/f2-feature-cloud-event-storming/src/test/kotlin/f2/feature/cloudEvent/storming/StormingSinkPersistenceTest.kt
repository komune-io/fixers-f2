package f2.feature.cloudEvent.storming

import f2.dsl.cqrs.Command
import f2.dsl.event.CloudEvent
import f2.feature.cloudEvent.storming.entity.CloudEventEntity
import java.time.Duration
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.kotlin.KotlinModule

data class RegisterUserCommand(val name: String) : Command

/**
 * The sinks write through the coroutine repository: `save` is a suspending call, so the write is
 * complete when the sink returns. The sinks are suspending `@EventListener` methods, which Spring
 * invokes through `CoroutinesUtils.invokeSuspendingFunction`: the write is asynchronous
 * fire-and-forget from the point of view of the publisher.
 *
 * These tests exercise both the direct (suspending) call and the `@EventListener` path, and check
 * that nothing else is published as a side effect.
 */
class StormingSinkPersistenceTest {

    companion object {
        /** Repository handed to the sink bean of [SinkConfig], reset before each test. */
        lateinit var contextRepository: InMemoryCloudEventEntityRepository

        /** Everything the catch-all [EventSpy] listener saw, reset before each test. */
        val observedEvents = mutableListOf<Any>()
    }

    private val objectMapper = JsonMapper.builder().addModule(KotlinModule.Builder().build()).build()

    @BeforeEach
    fun resetContextState() {
        contextRepository = InMemoryCloudEventEntityRepository()
        observedEvents.clear()
    }

    private fun cloudEvent(id: String) = CloudEvent(
        eventType = "UserCreated",
        eventTypeVersion = "1",
        cloudEventsVersion = "1",
        source = "S2",
        eventID = id,
        data = """{"name":"john"}"""
    )

    @Test
    fun `cloud event sink should have written the event when the suspending call returns`() = runTest {
        val repository = InMemoryCloudEventEntityRepository()
        val event = cloudEvent("event-1")

        StormingCloudEventSink(repo = repository).storeCommand(event)

        assertThat(repository.saved.single().event).isSameAs(event)
    }

    @Test
    fun `command sink should have written the serialised command when the suspending call returns`() = runTest {
        val repository = InMemoryCloudEventEntityRepository()

        StormingCommandSink(repo = repository, objectMapper = objectMapper)
            .storeCommand(RegisterUserCommand(name = "john"))

        val stored = repository.saved.single().event
        assertThat(stored.eventType).isEqualTo("RegisterUserCommand")
        assertThat(stored.data).isEqualTo("""{"name":"john"}""")
    }

    @Test
    fun `each stored event should get its own entity id`() = runTest {
        val repository = InMemoryCloudEventEntityRepository()
        val sink = StormingCloudEventSink(repo = repository)

        sink.storeCommand(cloudEvent("event-1"))
        sink.storeCommand(cloudEvent("event-2"))

        assertThat(repository.saved).hasSize(2)
        assertThat(repository.saved.map(CloudEventEntity::id)).doesNotHaveDuplicates()
    }

    @Test
    fun `a repository failure should propagate to a direct caller of the suspending sink`() = runTest {
        val sink = StormingCloudEventSink(repo = FailingCloudEventEntityRepository())

        val thrown = runCatching { sink.storeCommand(cloudEvent("event-1")) }.exceptionOrNull()

        assertThat(thrown)
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("write failed")
    }

    @Test
    fun `an event published in an application context should be stored exactly once`() {
        AnnotationConfigApplicationContext(SinkConfig::class.java).use { context ->
            context.publishEvent(cloudEvent("event-1"))

            // the listener suspends: Spring subscribes to the returned Mono, so the write is only
            // guaranteed to have happened eventually, not by the time publishEvent returns
            eventually {
                assertThat(contextRepository.saved.map { it.event.eventID }).containsExactly("event-1")
            }
            // the sink returns Unit: no entity is re-published as a follow-up application event
            assertThat(observedEvents.map { it::class.simpleName }).doesNotContain("CloudEventEntity")
        }
    }

    @Test
    fun `a repository failure should not propagate to the publisher of the event`() {
        contextRepository = FailingCloudEventEntityRepository()

        AnnotationConfigApplicationContext(SinkConfig::class.java).use { context ->
            // the failure surfaces through Spring's asynchronous listener error path (logged by
            // ApplicationListenerMethodAdapter), never to the caller of publishEvent
            assertThatCode { context.publishEvent(cloudEvent("event-1")) }.doesNotThrowAnyException()

            assertThat(contextRepository.saved).isEmpty()
            assertThat(observedEvents.map { it::class.simpleName }).doesNotContain("CloudEventEntity")
        }
    }

    /** Retries [assertion] until it holds or [timeout] elapses, so an async write can be awaited. */
    private fun eventually(timeout: Duration = Duration.ofSeconds(5), assertion: () -> Unit) {
        val deadline = System.nanoTime() + timeout.toNanos()
        while (true) {
            try {
                assertion()
                return
            } catch (failure: AssertionError) {
                if (System.nanoTime() >= deadline) {
                    throw failure
                }
                Thread.sleep(10)
            }
        }
    }

    private class FailingCloudEventEntityRepository : InMemoryCloudEventEntityRepository() {
        override suspend fun <S : CloudEventEntity> save(entity: S): CloudEventEntity =
            throw IllegalStateException("write failed")
    }

    @Configuration
    open class SinkConfig {
        @Bean
        open fun sink(): StormingCloudEventSink = StormingCloudEventSink(repo = contextRepository)

        @Bean
        open fun eventSpy(): EventSpy = EventSpy()
    }

    open class EventSpy {
        @EventListener
        open fun onAnyEvent(event: Any) {
            observedEvents.add(event)
        }
    }
}
