package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map


suspend operator fun <T, R> F2Function<T, R>.invoke(t: T): R {
	return invoke(flowOf(t)).first()
}

suspend fun <T, R> T.invokeWith(f2: F2Function<T, R>): R {
	return f2.invoke(this)
}

fun <T, R> f2Function(fnc: suspend (t: T) -> R): F2Function<T, R> = F2Function { msg ->
	msg.map { value ->
		fnc(value)
	}
}

fun <R> f2SupplierSingle(fnc: suspend () -> R): F2SupplierSingle<R> = F2SupplierSingle {
	fnc()
}

fun <R> f2Supplier(fnc: suspend () -> Flow<R>): F2Supplier<R> = F2Supplier {
	fnc()
}

fun <R> Iterable<R>.asF2Supplier(): F2Supplier<R> = F2Supplier {
	asFlow()
}

fun <R> f2Consumer(fnc: suspend (R) -> Unit): F2Consumer<R> = F2Consumer { flow ->
	flow.map(fnc).collect()
}
