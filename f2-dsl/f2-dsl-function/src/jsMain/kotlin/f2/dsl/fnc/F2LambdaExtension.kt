package f2.dsl.fnc

import kotlin.js.Promise
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.promise


operator fun <T, R> F2Function<T, R>.invoke(t: T): Promise<R> {
	return invoke(t)
}

suspend fun <T, R> T.invokeWith(f2: F2Function<T, R>): Promise<Array<out R>> {
	return f2.invoke(arrayOf(this))
}

fun <T, R> f2Function(fnc: suspend (t: T) -> R): F2Function<T, R> = object : F2Function<T, R> {
	override fun invoke(cmds: Array<out T>): Promise<Array<out R>> = GlobalScope.promise {
		cmds.map { cmd ->
			fnc(cmd)
		}.toTypedArray()
	}
}

fun <R> f2SupplierSingle(fnc: suspend () -> R): F2SupplierSingle<R> = object : F2SupplierSingle<R> {
	override fun invoke(): Promise<R> = GlobalScope.promise {
		fnc()
	}
}

fun <R> f2Supplier(fnc: suspend () -> Flow<R>): F2Supplier<R> = object : F2Supplier<R> {
	override fun invoke(): Promise<Array<R>> = GlobalScope.promise {
		fnc().toList().toTypedArray()
	}
}

fun <T> f2Consumer(fnc: suspend (T) -> Unit): F2Consumer<T> = object : F2Consumer<T> {
	override fun invoke(cmd: Array<T>): Promise<Unit> = GlobalScope.promise {
		cmd.map{fnc(it)}.toList().toTypedArray()
	}
}
