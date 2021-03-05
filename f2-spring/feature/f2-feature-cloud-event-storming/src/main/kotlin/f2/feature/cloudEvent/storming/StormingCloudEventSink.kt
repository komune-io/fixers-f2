package f2.feature.cloudEvent.storming

import f2.feature.cloudEvent.storming.entity.CloudEventEntity
import f2.feature.cloudEvent.storming.entity.CloudEventEntityRepository
import f2.dsl.cqrs.CloudEvent
import kotlinx.coroutines.runBlocking
import org.springframework.context.event.EventListener
import java.util.*

class StormingCloudEventSink(
	private val repo: CloudEventEntityRepository,
) {

	@EventListener
	fun storeCommand(event: CloudEvent<*>) = runBlocking {
		val entity = CloudEventEntity(
			id = UUID.randomUUID(),
			event = event
		)
		repo.save(entity)
	}
}