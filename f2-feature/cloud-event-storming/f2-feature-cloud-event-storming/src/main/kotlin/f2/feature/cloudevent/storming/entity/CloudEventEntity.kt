package f2.feature.cloudevent.storming.entity

import f2.dsl.event.CloudEvent
import jakarta.persistence.Entity
import java.util.UUID
import org.springframework.data.annotation.Id

@Entity
data class CloudEventEntity(
    @Id
    val id: UUID,
    val event: CloudEvent<*>,
)
