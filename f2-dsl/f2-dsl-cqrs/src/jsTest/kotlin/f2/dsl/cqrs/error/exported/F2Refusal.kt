@file:JsModule("./fixers-f2-f2-dsl-f2-dsl-cqrs.mjs")

package f2.dsl.cqrs.error.exported

// The refusal as JS sees it: imported by its exported name from the module the library compiles to.

external class F2Refusal(code: String, parameters: dynamic, citers: dynamic) {
    val code: String
    val parameters: dynamic
    val citers: dynamic
}
