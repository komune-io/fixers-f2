@file:JsModule("./fixers-f2-f2-dsl-f2-dsl-function.mjs")

package f2.dsl.fnc.exported

import kotlin.js.Promise

// The bridges as JS sees them: imported by their exported names from the module the library compiles to.

external fun invokeF2Function(fnc: dynamic, msgs: dynamic): Promise<dynamic>
external fun invokeF2Consumer(fnc: dynamic, msgs: dynamic): Promise<dynamic>
external fun invokeF2Supplier(fnc: dynamic): Promise<dynamic>
external fun invokeF2SupplierSingle(fnc: dynamic): Promise<dynamic>
