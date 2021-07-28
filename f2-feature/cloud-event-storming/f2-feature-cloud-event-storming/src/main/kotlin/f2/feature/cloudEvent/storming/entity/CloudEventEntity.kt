package f2.feature.cloudEvent.storming.entity

//import f2.dsl.cqrs.CloudEvent
import f2.dsl.event.CloudEvent
import org.springframework.data.annotation.Id
import java.util.*
import javax.persistence.Entity


@Entity
class CloudEventEntity(
	@Id
	val id: UUID,
	val event: CloudEvent<*>,
)