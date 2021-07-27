package f2.function.spring

import f2.dsl.fnc.F2Function
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first


suspend fun <T, R> F2Function<T, R>.invokeSingle(t: T): R {
	return invoke(listOf(t).asFlow()).first()
}
