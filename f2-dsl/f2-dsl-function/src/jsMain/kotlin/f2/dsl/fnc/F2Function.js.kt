package f2.dsl.fnc

import kotlin.js.JsExport
import kotlinx.coroutines.flow.Flow

/**
 * JS actual: cannot extend [F2LambdaFunction] — Kotlin/JS prohibits an interface from
 * implementing a non-suspend function type.
 */
@JsExport
actual fun interface F2Function<in T, out R> {
    actual operator fun invoke(msgs: Flow<T>): Flow<R>
}
