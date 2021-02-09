package city.smartb.f2.feature.cloudEvent.storming

import city.smartb.f2.feature.cloudEvent.storming.entity.CloudEventEntity
import city.smartb.f2.feature.cloudEvent.storming.entity.CloudEventEntityRepository
import city.smartb.f2.dsl.cqrs.CloudEvent
import city.smartb.f2.dsl.cqrs.Command
import com.fasterxml.jackson.databind.ObjectMapper
import kotlinx.coroutines.runBlocking
import org.springframework.context.event.EventListener
import java.time.Instant
import java.util.*

class StormingCommandSink(
	private val repo: CloudEventEntityRepository,
	private val objectMapper: ObjectMapper
) {

	@EventListener
	fun storeCommand(command: Command) = runBlocking {
		val cloudEvent = CloudEvent(
			eventType = command::class.simpleName!!,
			eventTypeVersion = "1",
			cloudEventsVersion = "1",
			source = "S2",
			eventID = UUID.randomUUID().toString(),
			eventTime= Instant.now().toString(), // RFC 3339
			schemaURL = null,  // RFC 3986
			contentType = "application/json", // RFC 2046
			extensions = null,
			data= objectMapper.writeValueAsString(command)
		)
		val entity = CloudEventEntity(
			id = UUID.randomUUID(),
			event = cloudEvent
		)
		repo.save(entity)
	}
}