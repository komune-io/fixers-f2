package f2.feature.cloudEvent.storming

import f2.dsl.cqrs.Command
import f2.dsl.event.CloudEvent
import f2.feature.cloudEvent.storming.entity.CloudEventEntity
import f2.feature.cloudEvent.storming.entity.CloudEventEntityRepository
import java.time.Instant
import java.util.UUID
import kotlinx.coroutines.runBlocking
import org.springframework.context.event.EventListener
import tools.jackson.databind.ObjectMapper

class StormingCommandSink(
    private val repo: CloudEventEntityRepository,
    private val objectMapper: ObjectMapper,
) {

	@EventListener
	fun storeCommand(command: Command) = runBlocking {
		val cloudEvent = CloudEvent(
			eventType = command::class.simpleName!!,
			eventTypeVersion = "1",
			cloudEventsVersion = "1",
			source = "S2",
			eventID = UUID.randomUUID().toString(),
			eventTime = Instant.now().toString(), // RFC 3339
			schemaURL = null, // RFC 3986
			contentType = "application/json", // RFC 2046
			extensions = null,
			data = objectMapper.writeValueAsString(command)
		)
		val entity = CloudEventEntity(
			id = UUID.randomUUID(),
			event = cloudEvent
		)
		repo.save(entity)
	}
}
