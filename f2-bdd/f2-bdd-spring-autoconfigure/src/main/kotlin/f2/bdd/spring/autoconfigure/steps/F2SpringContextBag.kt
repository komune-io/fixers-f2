package f2.bdd.spring.autoconfigure.steps

import io.cucumber.java8.Scenario
import org.springframework.boot.test.context.runner.ReactiveWebApplicationContextRunner
import java.util.UUID

class F2SpringContextBag {

	companion object {
		val cucumbers: MutableMap<String, F2SpringContextBag> = mutableMapOf()

		fun init(scenario: Scenario): F2SpringContextBag {
			if (!cucumbers.containsKey(scenario.id)) {
				cucumbers[scenario.id] = F2SpringContextBag().apply {
					uuid = UUID.randomUUID().toString()
				}
			}
			return cucumbers[scenario.id]!!
		}

	}

	lateinit var uuid: String

	var applicationParameters: Map<String, String> = emptyMap()
	var result: MutableMap<String, Any> = mutableMapOf()
	lateinit var contextBuilder: ReactiveWebApplicationContextRunner
}
