package city.smartb.f2.feature.cloudEvent.storming.entity

import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CloudEventEntityRepository: ReactiveCrudRepository<CloudEventEntity, String>