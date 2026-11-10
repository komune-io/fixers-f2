package f2.bdd.spring.autoconfigure.steps

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.cucumber.java8.En
import io.cucumber.java8.Scenario
import kotlin.reflect.KFunction

open class F2SpringStep {

    private var scenarioBag: F2SpringContextBag? = null

    var bag: F2SpringContextBag
        get() = checkNotNull(scenarioBag) { "No scenario started: the Before hook sets the bag" }
        set(value) {
            scenarioBag = value
        }

    fun En.prepareSteps() {
        Before { scenario: Scenario ->
            bag = F2SpringContextBag.init(scenario)
        }

        After { scenario: Scenario ->
            bag.applicationContext?.stop()
            F2SpringContextBag.clear(scenario)
        }
    }

    fun <P, R> KFunction<*>.blockingFunctionBean(): (P) -> R = bag.getBlockingFunctionBean(this.name)

    fun <R> KFunction<*>.blockingSupplierBean(): () -> R = bag.getBlockingSupplierBean(this.name)

    fun <P> KFunction<*>.blockingConsumerBean(): (P) -> Unit = bag.getBlockingConsumerBean(this.name)

    fun <P, R> KFunction<*>.functionBean(): suspend (P) -> R = bag.getFunctionBean(this.name)

    fun <R> KFunction<*>.supplierBean(): suspend () -> R = bag.getSupplierBean(this.name)

    fun <P> KFunction<*>.consumerBean(): suspend (P) -> Unit = bag.getConsumerBean(this.name)

    fun <P, R> KFunction<*>.functionF2Bean(): F2Function<P, R> = bag.typedBean(name)

    fun <R> KFunction<*>.supplierF2Bean(): F2Supplier<R> = bag.typedBean(name)

    fun <P> KFunction<*>.consumerF2Bean(): F2Consumer<P> = bag.typedBean(name)

    protected fun <T> KFunction<*>.consumerReceiverBean(): ConsumerReceiver<T> = bag.typedBean(name)

    companion object {
        const val GLUE = "f2.bdd.spring"
    }
}
