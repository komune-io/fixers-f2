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
    fun `storeCommand should persist one cloud event per command`() {
        sink.storeCommand(CreateUserCommand(name = "john", age = 42)).block()

        assertThat(repository.saved).hasSize(1)
    }

    @Test
    fun `storeCommand should use the command class simple name as event type`() {
        sink.storeCommand(CreateUserCommand(name = "john", age = 42)).block()

        assertThat(repository.saved.single().event.eventType).isEqualTo("CreateUserCommand")
    }

    @Test
    fun `storeCommand should serialize the command as json payload`() {
        sink.storeCommand(CreateUserCommand(name = "john", age = 42)).block()

        assertThat(repository.saved.single().event.data)
            .isEqualTo("""{"name":"john","age":42}""")
    }

    @Test
    fun `storeCommand should fill the cloud event envelope`() {
        sink.storeCommand(DeleteUserCommand(id = "user-1")).block()

        val event = repository.saved.single().event
        assertThat(event.source).isEqualTo("S2")
        assertThat(event.cloudEventsVersion).isEqualTo("1")
        assertThat(event.eventTypeVersion).isEqualTo("1")
        assertThat(event.contentType).isEqualTo("application/json")
        assertThat(event.schemaURL).isNull()
        assertThat(event.extensions).isNull()
    }

    @Test
    fun `storeCommand should generate a unique id for the event and the entity`() {
        sink.storeCommand(CreateUserCommand(name = "john", age = 42)).block()
        sink.storeCommand(CreateUserCommand(name = "jane", age = 24)).block()

        val entities = repository.saved
        assertThat(entities).hasSize(2)
        assertThat(entities.map { it.id }).doesNotHaveDuplicates()
        assertThat(entities.map { it.event.eventID }).doesNotHaveDuplicates()
        entities.forEach { entity ->
            assertThat(UUID.fromString(entity.event.eventID)).isNotNull
        }
    }

    @Test
    fun `storeCommand currently returns a cold Mono and persists nothing until it is subscribed`() {
        // `storeCommand` ends with `repo.save(entity)` inside `runBlocking`, which only *builds* the
        // Mono: nothing is awaited nor subscribed. Spring's event multicaster discards the returned
        // value, so with a real reactive repository no row is ever written.
        // Documented as-is, see issue #130.
        sink.storeCommand(CreateUserCommand(name = "john", age = 42))

        assertThat(repository.saveInvocations).isEqualTo(1)
        assertThat(repository.saved).isEmpty()
    }

    @Test
    fun `storeCommand should timestamp the event with an RFC 3339 instant`() {
        val before = Instant.now().minusSeconds(1)

        sink.storeCommand(CreateUserCommand(name = "john", age = 42)).block()

        val eventTime = Instant.parse(repository.saved.single().event.eventTime)
        assertThat(eventTime).isAfterOrEqualTo(before)
        assertThat(eventTime).isBeforeOrEqualTo(Instant.now().plusSeconds(1))
    }
}
