package city.smartb.f2.feature.cloudEvent.storming.entity

import city.smartb.f2.dsl.cqrs.CloudEvent
import org.springframework.data.annotation.Id
import java.util.*
import javax.persistence.Entity


@Entity
class CloudEventEntity(
	@Id
	val id: UUID,
	val event: CloudEvent<*>
)