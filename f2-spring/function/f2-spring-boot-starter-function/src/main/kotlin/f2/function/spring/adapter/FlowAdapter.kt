package f2.function.spring.adapter

import f2.dsl.function.F2Consumer
import f2.dsl.function.F2Function
import f2.dsl.function.F2Supplier
import kotlinx.coroutines.flow.map

fun <T, R> f2Function(fnc: suspend (t: T) -> R): F2Function<T, R> = { flow ->
	flow.map { value -> fnc(value) }
}

fun <T> f2Consumer(fnc: suspend (t: T) -> Unit): F2Consumer<T> =  { flow ->
	flow.map { value -> fnc(value) }
}
