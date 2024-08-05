package f2.dsl.fnc

import kotlin.js.Promise
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.await
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.promise

fun <T, R> F2Function<T, R>.invokePromise(t: T): Promise<R> {
	return GlobalScope.promise {
		invoke(flowOf(t)).first()
	}
}

fun <T, R> T.invokeWithPromise(f2: F2Function<T, R>): Promise<Array<out R>> {
	return f2.invokePromise(arrayOf(this))
}


fun <R> f2Supplier(fnc: suspend () -> Promise<R>): F2Supplier<R> = F2Supplier {
    flow {
        val result = fnc().await()  // Await the Promise result
        emit(result)
    }
}
