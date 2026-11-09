package f2.dsl.fnc

import kotlin.js.JsExport
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList

// The JS forms of the functional interfaces' `invoke`, whose Flow has no JS form.
// They are top-level functions because a second suspend member on these fun interfaces
// crashes the Kotlin/JS backend (2.4.21) on every SAM lambda.

/** Invokes [fnc] with an array of messages and collects its results into an array. */
@JsExport
suspend fun <T, R> invokeF2Function(fnc: F2Function<T, R>, msgs: Array<out T>): Array<R> =
    fnc(msgs.asList().asFlow()).toList().toTypedArray()

/** Invokes [fnc] with an array of messages. */
@JsExport
suspend fun <T> invokeF2Consumer(fnc: F2Consumer<T>, msgs: Array<out T>) {
    fnc(msgs.asList().asFlow())
}

/** Invokes [fnc] and collects its values into an array. */
@JsExport
suspend fun <R> invokeF2Supplier(fnc: F2Supplier<R>): Array<R> = fnc().toList().toTypedArray()

/** Invokes [fnc]. */
@JsExport
suspend fun <R> invokeF2SupplierSingle(fnc: F2SupplierSingle<R>): R = fnc()
