package f2.feature.cloudEvent.storming

import f2.dsl.event.CloudEvent
import f2.feature.cloudEvent.storming.entity.CloudEventEntityRepository
import java.util.function.Supplier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class StormingCloudEventFunction {

	@Bean
	fun cloudEvents(repo: CloudEventEntityRepository): Supplier<Flow<CloudEvent<*>>> {
		return Supplier {
			repo.findAll().map { it.event }
		}
	}
}
