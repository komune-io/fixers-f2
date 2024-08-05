package f2.dsl.fnc

import kotlin.js.Promise
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.promise


@JsExport
@JsName("F2Function")
actual fun interface F2Function<in T, out R>: suspend (Flow<T>) -> Flow<R> {
	@JsExport.Ignore
	actual override suspend operator fun invoke(msgs: Flow<T>): Flow<R>

	fun invokePromise(cmds: Array<out T>): Promise<Array<out R>> {
		return GlobalScope.promise {
			invoke(cmds.asFlow()).toList().toTypedArray()
		}
	}
}

@JsExport
@JsName("F2Consumer")
actual fun interface F2Consumer<T>: suspend (Flow<T>) -> Unit {
	@JsExport.Ignore
	actual override suspend operator fun invoke(msg: Flow<T>)

	fun invokePromise(cmds: Array<T>): Promise<Unit> {
		return GlobalScope.promise {
			invoke(cmds.asFlow())
		}
	}
}


@JsExport
@JsName("F2Supplier")
actual fun interface F2Supplier<R> : suspend () -> Flow<R> {
	@JsExport.Ignore
	actual override suspend operator fun invoke(): Flow<R>

	fun invokePromise(): Promise<Array<R>> {
		return GlobalScope.promise {
			invoke().toList().toTypedArray()
		}
	}
}

@JsExport
@JsName("F2SupplierSingle")
actual fun interface F2SupplierSingle<R>: suspend () -> R {
	@JsExport.Ignore
	actual override suspend operator fun invoke(): R

	fun invokePromise(): Promise<R> {
		return GlobalScope.promise {
			invoke()
		}
	}
}
