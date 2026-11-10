package f2.feature.cloudevent.storming.entity

import java.util.UUID
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CloudEventEntityRepository : CoroutineCrudRepository<CloudEventEntity, UUID>
