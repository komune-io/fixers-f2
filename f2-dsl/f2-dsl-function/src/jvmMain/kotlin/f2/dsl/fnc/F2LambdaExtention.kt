package f2.dsl.fnc

import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.collect

fun <T, R> f2LambdaFunction(fnc: suspend (t: T) -> R): F2LambdaFunction<T, R> = { flow ->
	flow.map { value -> fnc(value) }
}

fun <T> f2LambdaConsumer(fnc: suspend (t: T) -> Unit): F2LambdaConsumer<T> = { flow ->
	flow.map { value -> fnc(value) }
}

suspend operator fun <T, R> F2LambdaFunction<T, R>.invoke(t: T): R {
	return invoke(listOf(t).asFlow()).first()
}


suspend operator fun <T, R> F2Function<T, R>.invoke(t: T): R {
	return invoke(listOf(t).asFlow()).first()
}


fun <T, R> f2Function(fnc: suspend (t: T) -> R): F2Function<T, R> = F2Function { msg ->
	msg.map { value ->
		fnc(value)
	}
}

suspend fun <T, R> T.invokeWith(f2: F2Function<T, R>): R {
	return f2.invoke(this)
}

fun <R> f2Supplier(fnc: suspend () -> R): F2Supplier<R> = F2Supplier {
	flow {
		emit(fnc())
	}
}

fun <R> f2Consumer(fnc: suspend (R) -> Unit): F2Consumer<R> = F2Consumer { flow ->
	flow.map(fnc).collect()
}
