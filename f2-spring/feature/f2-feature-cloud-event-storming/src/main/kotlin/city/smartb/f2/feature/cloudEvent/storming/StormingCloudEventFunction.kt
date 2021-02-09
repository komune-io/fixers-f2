package city.smartb.f2.feature.cloudEvent.storming

import city.smartb.f2.feature.cloudEvent.storming.entity.CloudEventEntityRepository
import city.smartb.f2.dsl.cqrs.CloudEvent
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux
import java.util.function.Supplier

@Configuration
class StormingCloudEventFunction {

	@Bean
	fun cloudEvents(repo: CloudEventEntityRepository): Supplier<Flux<CloudEvent<*>>> {
		return Supplier {
			repo.findAll().map { it.event }
		}
	}
}