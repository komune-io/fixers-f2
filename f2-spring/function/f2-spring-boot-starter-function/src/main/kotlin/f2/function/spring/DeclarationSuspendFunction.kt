@file:JvmName("CoroutinesUtilsLocal")
package f2.function.spring

import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import kotlinx.coroutines.flow.Flow

internal fun invokeFunction(flux: F2Function<Any, Any>): suspend (Flow<Any>) -> Flow<Any> = { flux.invoke(it) }

internal fun invokeConsumer(flux: F2Consumer<Any>): suspend (Flow<Any>) -> Unit = { flux.invoke(it) }

internal fun invokeSupplier(flux: F2Supplier<Any>): suspend () -> Flow<Any> = { flux.invoke() }
