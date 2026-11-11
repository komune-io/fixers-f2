@file:JsModule("./fixers-f2-f2-dsl-f2-dsl-function.mjs")

package f2.client.ktor.http.model.exported

import kotlin.js.Promise

// The bridges a JS caller invokes the client's functions with, by their exported names.

external fun invokeF2Function(fnc: dynamic, msgs: dynamic): Promise<dynamic>
external fun invokeF2Consumer(fnc: dynamic, msgs: dynamic): Promise<dynamic>
external fun invokeF2Supplier(fnc: dynamic): Promise<dynamic>
