@file:JvmName("F2FunctionInvokers")
package f2.spring

import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.asFlux
import reactor.core.publisher.Flux

/**
 * Checks if the given target is an [F2Function] that can be invoked with the given Flux argument.
 */
fun isValidFlowFunction(kotlinLambdaTarget: Any, arg0: Any): Boolean {
    return kotlinLambdaTarget is F2Function<*, *> && arg0 is Flux<*>
}

/**
 * Invokes an [F2Function] by bridging the incoming Flux to a Flow and the resulting Flow back to a Flux.
 */
@Suppress("UNCHECKED_CAST")
fun invokeFlowFunction(kotlinLambdaTarget: Any, arg0: Any): Flux<Any> {
    require(kotlinLambdaTarget is F2Function<*, *>) {
        "kotlinLambdaTarget must be an F2Function, but was ${kotlinLambdaTarget::class.qualifiedName}"
    }
    require(arg0 is Flux<*>) {
        "arg0 must be a Flux, but was ${arg0::class.qualifiedName}"
    }
    val function = kotlinLambdaTarget as F2Function<Any, Any>
    val flux = arg0 as Flux<Any>
    return function.invoke(flux.asFlow()).asFlux()
}

/**
 * Checks if the given target is an [F2Supplier].
 */
fun isValidFlowSupplier(kotlinLambdaTarget: Any): Boolean {
    return kotlinLambdaTarget is F2Supplier<*>
}

/**
 * Invokes an [F2Supplier] and bridges the resulting Flow to a Flux.
 */
@Suppress("UNCHECKED_CAST")
fun invokeFlowSupplier(kotlinLambdaTarget: Any): Flux<Any> {
    require(kotlinLambdaTarget is F2Supplier<*>) {
        "kotlinLambdaTarget must be an F2Supplier, but was ${kotlinLambdaTarget::class.qualifiedName}"
    }
    val supplier = kotlinLambdaTarget as F2Supplier<Any>
    return supplier.invoke().asFlux()
}
