package f2.feature.cloudEvent.storming.entity

import f2.dsl.event.CloudEvent
import jakarta.persistence.Entity
import java.util.UUID
import org.springframework.data.annotation.Id

@Entity
class CloudEventEntity(
	@Id
	val id: UUID,
	val event: CloudEvent<*>,
)
