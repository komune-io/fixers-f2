package f2.feature.cloudEvent.storming

import f2.dsl.event.CloudEvent
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StormingCloudEventSinkTest {

    private val repository = InMemoryCloudEventEntityRepository()
    private val sink = StormingCloudEventSink(repo = repository)

    private fun cloudEvent(id: String) = CloudEvent(
        eventType = "UserCreated",
        eventTypeVersion = "1",
        cloudEventsVersion = "1",
        source = "S2",
        eventID = id,
        data = """{"name":"john"}"""
    )

    @Test
    suspend fun `storeCommand should persist the incoming cloud event as is`() {
        val event = cloudEvent("event-1")

        sink.storeCommand(event)

        assertThat(repository.saved.single().event).isSameAs(event)
    }

    @Test
    suspend fun `storeCommand should assign a distinct entity id to each event`() {
        sink.storeCommand(cloudEvent("event-1"))
        sink.storeCommand(cloudEvent("event-2"))

        assertThat(repository.saved).hasSize(2)
        assertThat(repository.saved.map { it.id }).doesNotHaveDuplicates()
    }
}
