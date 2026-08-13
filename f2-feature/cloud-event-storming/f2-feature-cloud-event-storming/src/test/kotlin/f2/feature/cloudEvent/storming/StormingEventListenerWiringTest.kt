package f2.feature.cloudEvent.storming

import f2.dsl.event.CloudEvent
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.kotlin.KotlinModule

/**
 * Checks that the `@EventListener` methods of both sinks are actually wired by the Spring event
 * multicaster: publishing a [f2.dsl.cqrs.Command] or a [CloudEvent] must reach the matching sink.
 */
class StormingEventListenerWiringTest {

    @Configuration
    open class SinksConfig {
        @Bean
        open fun repository() = InMemoryCloudEventEntityRepository()

        @Bean
        open fun objectMapper() = JsonMapper.builder().addModule(KotlinModule.Builder().build()).build()

        @Bean
        open fun stormingCommandSink(
            repository: InMemoryCloudEventEntityRepository,
            objectMapper: tools.jackson.databind.ObjectMapper
        ) = StormingCommandSink(repo = repository, objectMapper = objectMapper)

        @Bean
        open fun stormingCloudEventSink(repository: InMemoryCloudEventEntityRepository) =
            StormingCloudEventSink(repo = repository)
    }

    private lateinit var context: AnnotationConfigApplicationContext
    private lateinit var repository: InMemoryCloudEventEntityRepository

    @BeforeEach
    fun setUp() {
        context = AnnotationConfigApplicationContext(SinksConfig::class.java)
        repository = context.getBean(InMemoryCloudEventEntityRepository::class.java)
    }

    @AfterEach
    fun tearDown() = context.close()

    @Test
    fun `publishing a command should reach the command sink`() {
        context.publishEvent(CreateUserCommand(name = "john", age = 42))

        assertThat(repository.saved).hasSize(1)
    }

    @Test
    fun `publishing a cloud event should reach the cloud event sink`() {
        context.publishEvent(
            CloudEvent(
                eventType = "UserCreated",
                cloudEventsVersion = "1",
                source = "S2",
                eventID = "event-1",
                data = """{"name":"john"}"""
            )
        )

        assertThat(repository.saved).hasSize(1)
    }

    @Test
    fun `publishing an unrelated event should reach no sink`() {
        context.publishEvent("some-unrelated-payload")

        assertThat(repository.saved).isEmpty()
    }
}
