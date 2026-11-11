package f2.bdd.spring.autoconfigure.steps

import f2.dsl.cqrs.exception.F2Exception
import io.cucumber.java8.Scenario
import java.util.UUID
import org.springframework.boot.test.context.runner.ReactiveWebApplicationContextRunner
import org.springframework.cloud.function.context.FunctionCatalog
import org.springframework.context.support.GenericApplicationContext

class F2SpringContextBag {

    /** The port the scenario's web server bound, or null when no web server started. */
    var httpPort: Int? = null
    val uuid: String = UUID.randomUUID().toString()

    var applicationParameters: Map<String, String> = emptyMap()
    val result: MutableMap<String, Any> = mutableMapOf()
    val exceptions: MutableMap<String, F2Exception> = mutableMapOf()
    private var builtContext: ReactiveWebApplicationContextRunner? = null
    var contextBuilder: ReactiveWebApplicationContextRunner
        get() = checkNotNull(builtContext) { "No application context built for this scenario" }
        set(value) {
            builtContext = value
        }
    var applicationContext: GenericApplicationContext? = null

    fun <P, R> getBlockingFunctionBean(name: String): (P) -> R = typedBean(name)

    fun <R> getBlockingSupplierBean(name: String): () -> R = typedBean(name)

    fun <P> getBlockingConsumerBean(name: String): (P) -> Unit = typedBean(name)

    fun <P, R> getFunctionBean(name: String): suspend (P) -> R = typedBean(name)

    fun <R> getSupplierBean(name: String): suspend () -> R = typedBean(name)

    fun <P> getConsumerBean(name: String): suspend (P) -> Unit = typedBean(name)

    /** The bean [name], as the function type the caller registered it with. */
    @Suppress(
        "UNCHECKED_CAST", // a function type is erased at runtime: the caller names the one it registered
        "MissingUseCall", // the context lives as long as the scenario: F2SpringStep's After hook stops it
    )
    fun <F> typedBean(name: String): F = requireApplicationContext().getBean(name) as F

    fun requireApplicationContext(): GenericApplicationContext =
        checkNotNull(applicationContext) { "No application context started for this scenario" }

    @Suppress("MissingUseCall") // the context lives as long as the scenario: F2SpringStep's After hook stops it
    fun getFunctionCatalog(): FunctionCatalog = requireApplicationContext().getBean(FunctionCatalog::class.java)

    companion object {
        val cucumbers: MutableMap<String, F2SpringContextBag> = mutableMapOf()
        fun init(scenario: Scenario): F2SpringContextBag {
            if (!cucumbers.containsKey(scenario.id)) {
                cucumbers[scenario.id] = F2SpringContextBag()
            }
            return cucumbers.getValue(scenario.id)
        }

        fun clear(scenario: Scenario) {
            cucumbers.remove(scenario.id)
        }
    }
}
