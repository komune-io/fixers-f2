package f2.feature.cloudEvent.storming

import f2.dsl.event.CloudEvent
import f2.feature.cloudEvent.storming.entity.CloudEventEntity
import f2.feature.cloudEvent.storming.entity.CloudEventEntityRepository
import java.util.UUID
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.runBlocking
import org.springframework.context.event.EventListener

class StormingCloudEventSink(
	private val repo: CloudEventEntityRepository,
) {

	/**
	 * `repo.save` returns a cold [reactor.core.publisher.Mono]: it is awaited here so the event is
	 * actually written before the listener returns, and so a failure propagates to the publisher of
	 * the event instead of being silently dropped.
	 */
	@EventListener
	fun storeCommand(event: CloudEvent<*>) {
		val entity = CloudEventEntity(
			id = UUID.randomUUID(),
			event = event
		)
		runBlocking { repo.save(entity).awaitSingle() }
	}
}
