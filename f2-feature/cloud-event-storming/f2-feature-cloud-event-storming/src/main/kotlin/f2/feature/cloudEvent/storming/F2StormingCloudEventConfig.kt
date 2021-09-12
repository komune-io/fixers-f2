package f2.feature.cloudEvent.storming

import com.fasterxml.jackson.databind.ObjectMapper
import f2.feature.cloudEvent.storming.entity.CloudEventEntityRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.repository.core.support.ReactiveRepositoryFactorySupport

@Configuration
class F2StormingCloudEventConfig {

	@Bean
	fun cloudEventEntityRepository(
		factory: ReactiveRepositoryFactorySupport,
	): CloudEventEntityRepository {
		return factory.getRepository(CloudEventEntityRepository::class.java)
	}

	@Bean
	fun stormingCommandSink(
		cloudEventRepository: CloudEventEntityRepository,
		objectMapper: ObjectMapper,
	): StormingCommandSink {
		return StormingCommandSink(repo = cloudEventRepository, objectMapper)
	}

	@Bean
	fun stormingCloudEventSink(
		cloudEventRepository: CloudEventEntityRepository,
	): StormingCloudEventSink {
		return StormingCloudEventSink(repo = cloudEventRepository)
	}
}
