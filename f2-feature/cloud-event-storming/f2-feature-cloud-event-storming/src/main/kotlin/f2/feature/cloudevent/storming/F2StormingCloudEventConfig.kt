package f2.feature.cloudevent.storming

import f2.feature.cloudevent.storming.entity.CloudEventEntityRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.core.support.ReactiveRepositoryFactorySupport
import tools.jackson.databind.ObjectMapper

@Configuration
class F2StormingCloudEventConfig {

    @Bean
    fun cloudEventEntityRepository(
        factory: ReactiveRepositoryFactorySupport,
    ): CloudEventEntityRepository = factory.getRepository(CloudEventEntityRepository::class.java)

    @Bean
    fun stormingCommandSink(
        cloudEventRepository: CloudEventEntityRepository,
        objectMapper: ObjectMapper,
    ): StormingCommandSink = StormingCommandSink(repo = cloudEventRepository, objectMapper = objectMapper)

    @Bean
    fun stormingCloudEventSink(
        cloudEventRepository: CloudEventEntityRepository,
    ): StormingCloudEventSink = StormingCloudEventSink(repo = cloudEventRepository)
}
