@file:JsModule("./fixers-f2-f2-client-f2-client-ktor-f2-client-ktor-http.mjs")

package f2.client.ktor.http.model.exported

// The JS client as JS sees it: imported by its exported names from the module the library compiles to.

external class HttpF2JsClient {
    fun function(route: String): dynamic
    fun supplier(route: String): dynamic
    fun consumer(route: String): dynamic
}

external fun httpF2JsClient(urlBase: String): HttpF2JsClient
