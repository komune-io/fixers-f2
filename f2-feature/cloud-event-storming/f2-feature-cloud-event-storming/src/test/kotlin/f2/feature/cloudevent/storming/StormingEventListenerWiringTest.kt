package f2.feature.cloudevent.storming

import f2.dsl.event.CloudEvent
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.context.annotation.AnnotationConfigApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import tools.jackson.databind.ObjectMapper
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.kotlin.KotlinModule

/**
 * Checks that the `@EventListener` methods of both sinks are actually wired by the Spring event
 * multicaster: publishing a [f2.dsl.cqrs.Command] or a [CloudEvent] must reach the matching sink.
 */
class StormingEventListenerWiringTest {

    @Configuration
    class SinksConfig {
        @Bean
        fun repository() = InMemoryCloudEventEntityRepository()

        @Bean
        fun objectMapper(): JsonMapper = JsonMapper.builder().addModule(KotlinModule.Builder().build()).build()

        @Bean
        fun stormingCommandSink(
            repository: InMemoryCloudEventEntityRepository,
            objectMapper: ObjectMapper
        ) = StormingCommandSink(repo = repository, objectMapper = objectMapper)

        @Bean
        fun stormingCloudEventSink(repository: InMemoryCloudEventEntityRepository) =
            StormingCloudEventSink(repo = repository)
    }

    @Test
    fun `publishing a command should reach the command sink`() {
        withSinks { context, repository ->
            context.publishEvent(CreateUserCommand(name = "john", age = 42))

            assertThat(repository.saved).hasSize(1)
        }
    }

    @Test
    fun `publishing a cloud event should reach the cloud event sink`() {
        withSinks { context, repository ->
            context.publishEvent(
                CloudEvent(
                    eventType = "UserCreated",
                    source = "S2",
                    eventID = "event-1",
                    cloudEventsVersion = "1",
                    data = """{"name":"john"}"""
                )
            )

            assertThat(repository.saved).hasSize(1)
        }
    }

    @Test
    fun `publishing an unrelated event should reach no sink`() {
        withSinks { context, repository ->
            context.publishEvent("some-unrelated-payload")

            assertThat(repository.saved).isEmpty()
        }
    }

    private fun withSinks(
        block: (AnnotationConfigApplicationContext, InMemoryCloudEventEntityRepository) -> Unit,
    ) {
        AnnotationConfigApplicationContext(SinksConfig::class.java).use { context ->
            block(context, context.getBean(InMemoryCloudEventEntityRepository::class.java))
        }
    }
}
