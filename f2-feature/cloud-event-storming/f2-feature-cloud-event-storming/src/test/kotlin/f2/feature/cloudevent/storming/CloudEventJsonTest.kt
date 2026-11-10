package f2.feature.cloudevent.storming

import f2.dsl.event.CloudEvent
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.kotlin.KotlinModule

/** The JSON a stored or served event takes: CloudEvents attributes side by side, as the specification lays them. */
class CloudEventJsonTest {

    private val mapper = JsonMapper.builder().addModule(KotlinModule.Builder().build()).build()

    @Test
    fun `a cloud event is written flat, every attribute at the top level`() {
        val event = CloudEvent(
            eventType = "UserCreated",
            eventTypeVersion = "1",
            cloudEventsVersion = "1",
            source = "S2",
            eventID = "event-1",
            eventTime = "2026-11-10T00:00:00Z",
            contentType = "application/json",
            extensions = mapOf("tenant" to "t-1"),
            data = """{"name":"john"}""",
        )

        val json = mapper.readTree(mapper.writeValueAsString(event))

        assertThat(json.propertyNames()).contains(
            "eventType", "eventTypeVersion", "cloudEventsVersion", "source", "eventID", "eventTime",
            "contentType", "extensions", "data",
        )
        assertThat(json.propertyNames()).doesNotContain("attributes")
        assertThat(json.get("eventID").asString()).isEqualTo("event-1")
        assertThat(json.get("extensions").get("tenant").asString()).isEqualTo("t-1")
    }
}
