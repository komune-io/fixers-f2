package city.smartb.f2.function.spring.adapter

import f2.dsl.F2Flow
import kotlinx.coroutines.flow.map

fun <T, R> flow(fnc: suspend (t: T) -> R): F2Flow<T, R> =  { flow ->
	flow.map { value -> fnc(value) }
}
