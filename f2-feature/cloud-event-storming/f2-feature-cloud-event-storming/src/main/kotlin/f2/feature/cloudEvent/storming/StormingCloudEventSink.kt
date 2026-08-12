package f2.feature.cloudEvent.storming

import f2.dsl.event.CloudEvent
import f2.feature.cloudEvent.storming.entity.CloudEventEntity
import f2.feature.cloudEvent.storming.entity.CloudEventEntityRepository
import java.util.UUID
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.context.event.EventListener

class StormingCloudEventSink(
	private val repo: CloudEventEntityRepository,
) {

	/**
	 * `repo.save` returns a cold [reactor.core.publisher.Mono]: it is awaited here, so the event is
	 * actually written instead of the listener merely building a publisher nobody subscribes to.
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
	suspend fun storeCommand(event: CloudEvent<*>) {
		val entity = CloudEventEntity(
			id = UUID.randomUUID(),
			event = event
		)
		repo.save(entity).awaitSingle()
	}
}
