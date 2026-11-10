package f2.feature.cloudevent.storming.entity

import f2.dsl.event.CloudEvent
import jakarta.persistence.Entity
import java.util.UUID
import org.springframework.data.annotation.Id

/** A stored event: an entity is told apart by its [id], so it keeps the identity equality of a plain class. */
@Entity
@Suppress("UseDataClass") // an entity: value equality would merge two stored events with the same content
class CloudEventEntity(
    @Id
    val id: UUID,
    val event: CloudEvent<*>,
)
