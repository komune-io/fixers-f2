package f2.feature.cloudEvent.storming

import f2.dsl.cqrs.Command
import java.time.Instant
import java.util.UUID
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.kotlin.KotlinModule

data class CreateUserCommand(val name: String, val age: Int) : Command
data class DeleteUserCommand(val id: String) : Command

class StormingCommandSinkTest {

    private val objectMapper = JsonMapper.builder().addModule(KotlinModule.Builder().build()).build()
    private val repository = InMemoryCloudEventEntityRepository()
    private val sink = StormingCommandSink(repo = repository, objectMapper = objectMapper)

    @Test
    suspend fun `storeCommand should persist one cloud event per command`() {
        sink.storeCommand(CreateUserCommand(name = "john", age = 42))

        assertThat(repository.saved).hasSize(1)
    }

    @Test
    suspend fun `storeCommand should use the command class simple name as event type`() {
        sink.storeCommand(CreateUserCommand(name = "john", age = 42))

        assertThat(repository.saved.single().event.eventType).isEqualTo("CreateUserCommand")
    }

    @Test
    suspend fun `storeCommand should serialize the command as json payload`() {
        sink.storeCommand(CreateUserCommand(name = "john", age = 42))

        assertThat(repository.saved.single().event.data)
            .isEqualTo("""{"name":"john","age":42}""")
    }

    @Test
    suspend fun `storeCommand should fill the cloud event envelope`() {
        sink.storeCommand(DeleteUserCommand(id = "user-1"))

        val event = repository.saved.single().event
        assertThat(event.source).isEqualTo("S2")
        assertThat(event.cloudEventsVersion).isEqualTo("1")
        assertThat(event.eventTypeVersion).isEqualTo("1")
        assertThat(event.contentType).isEqualTo("application/json")
        assertThat(event.schemaURL).isNull()
        assertThat(event.extensions).isNull()
    }

    @Test
    suspend fun `storeCommand should generate a unique id for the event and the entity`() {
        sink.storeCommand(CreateUserCommand(name = "john", age = 42))
        sink.storeCommand(CreateUserCommand(name = "jane", age = 24))

        val entities = repository.saved
        assertThat(entities).hasSize(2)
        assertThat(entities.map { it.id }).doesNotHaveDuplicates()
        assertThat(entities.map { it.event.eventID }).doesNotHaveDuplicates()
        entities.forEach { entity ->
            assertThat(UUID.fromString(entity.event.eventID)).isNotNull
        }
    }

    @Test
    suspend fun `storeCommand awaits the save instead of returning a cold Mono`() {
        sink.storeCommand(CreateUserCommand(name = "john", age = 42))

        assertThat(repository.saveInvocations).isEqualTo(1)
        assertThat(repository.saved).hasSize(1)
    }

    @Test
    suspend fun `storeCommand should timestamp the event with an RFC 3339 instant`() {
        val before = Instant.now().minusSeconds(1)

        sink.storeCommand(CreateUserCommand(name = "john", age = 42))

        val eventTime = Instant.parse(repository.saved.single().event.eventTime)
        assertThat(eventTime).isAfterOrEqualTo(before)
        assertThat(eventTime).isBeforeOrEqualTo(Instant.now().plusSeconds(1))
    }
}
