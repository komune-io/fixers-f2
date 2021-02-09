package city.smartb.f2.client.http.ktor

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlin.js.Promise

fun <T> doCall(fnc: suspend () -> Any): Promise<T> = GlobalScope.promise {
    val result = fnc()
    JSON.parse(JSON.stringify(result))
}