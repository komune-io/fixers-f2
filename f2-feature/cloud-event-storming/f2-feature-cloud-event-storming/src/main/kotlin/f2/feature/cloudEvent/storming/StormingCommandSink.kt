package f2.feature.cloudEvent.storming

import f2.dsl.cqrs.Command
import f2.dsl.event.CloudEvent
import f2.feature.cloudEvent.storming.entity.CloudEventEntity
import f2.feature.cloudEvent.storming.entity.CloudEventEntityRepository
import java.time.Instant
import java.util.UUID
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.runBlocking
import org.springframework.context.event.EventListener
import tools.jackson.databind.ObjectMapper

class StormingCommandSink(
    private val repo: CloudEventEntityRepository,
    private val objectMapper: ObjectMapper,
) {

	/**
	 * `repo.save` returns a cold [reactor.core.publisher.Mono]: it is awaited here so the command is
	 * actually written before the listener returns, and so a failure propagates to the publisher of
	 * the command instead of being silently dropped.
	 */
	@EventListener
	fun storeCommand(command: Command) {
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
		runBlocking { repo.save(entity).awaitSingle() }
	}
}
