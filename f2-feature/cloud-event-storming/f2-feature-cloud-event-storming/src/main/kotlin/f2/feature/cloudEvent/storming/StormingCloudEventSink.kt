package f2.feature.cloudEvent.storming

import f2.dsl.event.CloudEvent
import f2.feature.cloudEvent.storming.entity.CloudEventEntity
import f2.feature.cloudEvent.storming.entity.CloudEventEntityRepository
import java.util.UUID
import kotlinx.coroutines.runBlocking
import org.springframework.context.event.EventListener

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
