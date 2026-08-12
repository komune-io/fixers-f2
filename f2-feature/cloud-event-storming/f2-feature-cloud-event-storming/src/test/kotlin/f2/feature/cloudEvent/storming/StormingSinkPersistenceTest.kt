package f2.feature.cloudEvent.storming

import f2.dsl.cqrs.Command
import f2.dsl.event.CloudEvent
import f2.feature.cloudEvent.storming.entity.CloudEventEntity
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.event.EventListener
import reactor.core.publisher.Mono
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.kotlin.KotlinModule

data class RegisterUserCommand(val name: String) : Command

/**
 * The sinks must write through the reactive repository by themselves: `save` returns a cold `Mono`,
 * so the write only happens if the sink subscribes to it. These tests exercise both the direct call
 * and the `@EventListener` path, and check that nothing else is published as a side effect.
 */
class StormingSinkPersistenceTest {

    companion object {
        val contextRepository = InMemoryCloudEventEntityRepository()
        val observedEvents = mutableListOf<Any>()
    }

    private val objectMapper = JsonMapper.builder().addModule(KotlinModule.Builder().build()).build()

    private fun cloudEvent(id: String) = CloudEvent(
        eventType = "UserCreated",
        eventTypeVersion = "1",
        cloudEventsVersion = "1",
        source = "S2",
        eventID = id,
        data = """{"name":"john"}"""
    )

    @Test
    fun `cloud event sink should write the event without any external subscriber`() {
        val repository = InMemoryCloudEventEntityRepository()
        val event = cloudEvent("event-1")

        StormingCloudEventSink(repo = repository).storeCommand(event)

        assertThat(repository.saved.single().event).isSameAs(event)
        assertThat(repository.saveInvocations).isEqualTo(repository.saved.size)
    }

    @Test
    fun `command sink should write the serialised command without any external subscriber`() {
        val repository = InMemoryCloudEventEntityRepository()

        StormingCommandSink(repo = repository, objectMapper = objectMapper)
            .storeCommand(RegisterUserCommand(name = "john"))

        val stored = repository.saved.single().event
        assertThat(stored.eventType).isEqualTo("RegisterUserCommand")
        assertThat(stored.data).isEqualTo("""{"name":"john"}""")
        assertThat(repository.saveInvocations).isEqualTo(repository.saved.size)
    }

    @Test
    fun `each stored event should get its own entity id`() {
        val repository = InMemoryCloudEventEntityRepository()
        val sink = StormingCloudEventSink(repo = repository)

        sink.storeCommand(cloudEvent("event-1"))
        sink.storeCommand(cloudEvent("event-2"))

        assertThat(repository.saved).hasSize(2)
        assertThat(repository.saved.map(CloudEventEntity::id)).doesNotHaveDuplicates()
    }

    @Test
    fun `a repository failure should propagate to the publisher of the event`() {
        val failing = object : InMemoryCloudEventEntityRepository() {
            override fun <S : CloudEventEntity> save(entity: S): Mono<S> =
                Mono.error(IllegalStateException("write failed"))
        }

        assertThatThrownBy { StormingCloudEventSink(repo = failing).storeCommand(cloudEvent("event-1")) }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessage("write failed")
    }

    @Test
    fun `an event published in an application context should be stored exactly once`() {
        AnnotationConfigApplicationContext(SinkConfig::class.java).use { context ->
            context.publishEvent(cloudEvent("event-1"))

            assertThat(contextRepository.saved.map { it.event.eventID }).containsExactly("event-1")
            assertThat(contextRepository.saveInvocations).isEqualTo(1)
            // the sink returns Unit: no entity is re-published as a follow-up application event
            assertThat(observedEvents.map { it::class.simpleName }).doesNotContain("CloudEventEntity")
        }
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
