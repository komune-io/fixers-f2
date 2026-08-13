package f2.feature.cloudEvent.storming

import f2.dsl.cqrs.Command
import f2.dsl.event.CloudEvent
import f2.feature.cloudEvent.storming.entity.CloudEventEntity
import f2.feature.cloudEvent.storming.entity.CloudEventEntityRepository
import java.time.Instant
import java.util.UUID
import org.springframework.context.event.EventListener
import tools.jackson.databind.ObjectMapper

class StormingCommandSink(
    private val repo: CloudEventEntityRepository,
    private val objectMapper: ObjectMapper,
) {

	/**
	 * `repo.save` is a suspending call: the command is written by the time it returns.
	 *
	 * Spring invokes a suspending `@EventListener` through `CoroutinesUtils.invokeSuspendingFunction`,
	 * which wraps the call in a `Mono` and subscribes to it. Persistence is therefore asynchronous
	 * fire-and-forget: the write is not guaranteed to be complete when `publishEvent` returns, and a
	 * repository failure is not propagated to the publisher — it is reported by Spring's asynchronous
	 * listener error path instead.
	 *
	 * The return type must stay [Unit]: Spring re-publishes any other returned value as a new
	 * application event.
	 */
	@EventListener
	suspend fun storeCommand(command: Command) {
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
