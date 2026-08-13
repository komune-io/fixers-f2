package f2.feature.cloudEvent.storming

import f2.dsl.event.CloudEvent
import f2.feature.cloudEvent.storming.entity.CloudEventEntity
import java.util.UUID
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StormingCloudEventFunctionTest {

    private val repository = InMemoryCloudEventEntityRepository()
    private val supplier = StormingCloudEventFunction().cloudEvents(repository)

    private suspend fun store(eventId: String) {
        val event = CloudEvent(
            eventType = "UserCreated",
            cloudEventsVersion = "1",
            source = "S2",
            eventID = eventId,
            data = """{"name":"john"}"""
        )
        repository.save(CloudEventEntity(id = UUID.randomUUID(), event = event))
    }

    @Test
    fun `cloudEvents should emit nothing when no event is stored`() = runTest {
        assertThat(supplier.get().toList()).isEmpty()
    }

    @Test
    fun `cloudEvents should emit the stored events`() = runTest {
        store("event-1")
        store("event-2")

        val events = supplier.get().toList()

        assertThat(events.map { it.eventID }).containsExactly("event-1", "event-2")
        assertThat(events.map { it.eventType }).containsOnly("UserCreated")
    }

    @Test
    fun `cloudEvents supplier should re-read the repository on each get`() = runTest {
        store("event-1")
        assertThat(supplier.get().toList()).hasSize(1)

        store("event-2")
        assertThat(supplier.get().toList()).hasSize(2)
    }
}
