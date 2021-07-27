package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Deprecated("Better use declaration")
fun <T, R> f2Function(fnc: suspend (t: T) -> R): F2Function<T, R> = { flow ->
	flow.map { value -> fnc(value) }
}

@Deprecated("Better use declaration")
fun <T> f2Consumer(fnc: suspend (t: T) -> Unit): F2Consumer<T> =  { flow ->
	flow.map { value -> fnc(value) }
}

inline fun <reified T,reified R> declaration(crossinline fnc: suspend (t: T) -> R): F2FunctionDeclaration<T, R> = object : F2FunctionDeclaration<T, R> {
	override suspend fun invoke(msg: Flow<T>): Flow<R> {
		return msg.map { value ->
			fnc(value)
		}
	}
}

fun <T, R> F2FunctionDeclaration<T, R>.expose(): F2Function<T, R> = { flow ->
	val ff = flow.map { value ->
		println("+++++++++++++++++++++")
		println(value)
		value
	}
	this.invoke(ff)
}

fun <T, R> declaration(fnc: suspend () -> R): F2ConsumerDeclaration<R> = object : F2ConsumerDeclaration<R> {
	override suspend fun invoke(flow: Flow<R>) {
		flow.map {
				value -> fnc()
		}
	}
}

fun <T, R> F2ConsumerDeclaration<R>.expose(): F2Consumer<R> = { flow ->
	this.invoke(flow)
}