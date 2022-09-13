package f2.bdd.spring.autoconfigure.steps

import io.cucumber.java8.Scenario
import org.springframework.boot.test.context.runner.ReactiveWebApplicationContextRunner
import org.springframework.context.support.GenericApplicationContext
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

	@SuppressWarnings("MagicNumber")
	var httpPort: Int = 6001
	@SuppressWarnings("MagicNumber")
	var rsoketPort: Int = 6002
	lateinit var uuid: String

	var applicationParameters: Map<String, String> = emptyMap()
	var result: MutableMap<String, Any> = mutableMapOf()
	var exceptions: MutableMap<String, Exception> = mutableMapOf()
	lateinit var contextBuilder: ReactiveWebApplicationContextRunner
	var applicationContext: GenericApplicationContext? = null


	@Suppress("UNCHECKED_CAST")
	fun <P, R> getBlockingFunctionBean(name: String): (P) -> R {
		return applicationContext!!.getBean(name) as (P) -> R
	}

	@Suppress("UNCHECKED_CAST")
	fun <R> getBlockingSupplierBean(name: String): () -> R {
		return applicationContext!!.getBean(name) as () -> R
	}

	@Suppress("UNCHECKED_CAST")
	fun <P> getBlockingConsumerBean(name: String): (P) -> Unit {
		return applicationContext!!.getBean(name) as (P) -> Unit
	}

	@Suppress("UNCHECKED_CAST")
	fun <P, R> getFunctionBean(name: String): suspend (P) -> R {
		return applicationContext!!.getBean(name) as suspend (P) -> R
	}

	@Suppress("UNCHECKED_CAST")
	fun <R> getSupplierBean(name: String): suspend () -> R {
		return applicationContext!!.getBean(name) as suspend () -> R
	}

	@Suppress("UNCHECKED_CAST")
	fun <P> getConsumerBean(name: String): suspend (P) -> Unit {
		return applicationContext!!.getBean(name) as suspend (P) -> Unit
	}
}
