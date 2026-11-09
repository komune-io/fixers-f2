package f2.dsl.fnc

import kotlin.js.JsExport
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList

// The JS forms of the functional interfaces' `invoke`, whose Flow has no JS form.
// They are top-level functions because a second suspend member on these fun interfaces
// crashes the Kotlin/JS backend (2.4.21) on every SAM lambda.

/** Invokes [fnc] with a list of messages and collects its results. */
@JsExport
suspend fun <T, R> invokeF2Function(fnc: F2Function<T, R>, msgs: List<T>): List<R> =
    fnc(msgs.asFlow()).toList()

/** Invokes [fnc] with a list of messages. */
@JsExport
suspend fun <T> invokeF2Consumer(fnc: F2Consumer<T>, msgs: List<T>) {
    fnc(msgs.asFlow())
}

/** Invokes [fnc] and collects its values. */
@JsExport
suspend fun <R> invokeF2Supplier(fnc: F2Supplier<R>): List<R> = fnc().toList()

/** Invokes [fnc]. */
@JsExport
suspend fun <R> invokeF2SupplierSingle(fnc: F2SupplierSingle<R>): R = fnc()
