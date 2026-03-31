package f2.bdd.spring.autoconfigure.steps

import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import kotlin.reflect.KFunction

open class F2SpringStep {

    companion object {
        const val GLUE = "f2.bdd.spring"
    }

    lateinit var bag: F2SpringContextBag

    @Suppress("LongMethod")
    fun En.prepareSteps() {
        Before { scenario: Scenario ->
            bag = F2SpringContextBag.init(scenario)
        }

        After { scenario: Scenario ->
            bag.applicationContext?.stop()
            F2SpringContextBag.clear(scenario)
        }
    }

    fun <P, R> KFunction<*>.blockingFunctionBean(): (P) -> R {
        return bag.getBlockingFunctionBean(this.name)
    }

    fun <R> KFunction<*>.blockingSupplierBean(): () -> R {
        return bag.getBlockingSupplierBean(this.name)
    }

    fun <P> KFunction<*>.blockingConsumerBean(): (P) -> Unit {
        return bag.getBlockingConsumerBean(this.name)
    }

    fun <P, R> KFunction<*>.functionBean(): suspend (P) -> R {
        return bag.getFunctionBean(this.name)
    }

    fun <R> KFunction<*>.supplierBean(): suspend () -> R {
        return bag.getSupplierBean(this.name)
    }

    fun <P> KFunction<*>.consumerBean(): suspend (P) -> Unit {
        return bag.getConsumerBean(this.name)
    }

    @Suppress("UNCHECKED_CAST")
    fun <P, R> KFunction<*>.functionF2Bean(): F2Function<P, R> {
        return bag.applicationContext!!.getBean(name) as F2Function<P, R>
    }

    @Suppress("UNCHECKED_CAST")
    fun <R> KFunction<*>.supplierF2Bean(): F2Supplier<R> {
        return bag.applicationContext!!.getBean(name) as F2Supplier<R>
    }

    @Suppress("UNCHECKED_CAST")
    fun <P> KFunction<*>.consumerF2Bean(): F2Consumer<P> {
        return bag.applicationContext!!.getBean(name) as F2Consumer<P>
    }
}
