package f2.dsl.fnc

import kotlin.js.JsExport
import kotlinx.coroutines.flow.Flow

/**
 * JS actual: cannot extend [F2LambdaSupplier] — Kotlin/JS prohibits an interface from
 * implementing a non-suspend function type.
 */
@JsExport
actual fun interface F2Supplier<R> {
    actual operator fun invoke(): Flow<R>
}
