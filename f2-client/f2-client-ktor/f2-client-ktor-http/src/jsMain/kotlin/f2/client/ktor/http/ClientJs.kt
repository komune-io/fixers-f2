package f2.client.ktor.http

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlin.js.Promise

fun <T> doCall(fnc: suspend () -> Any): Promise<T> = GlobalScope.promise {
    val result = fnc()
    JSON.parse(JSON.stringify(result))
}