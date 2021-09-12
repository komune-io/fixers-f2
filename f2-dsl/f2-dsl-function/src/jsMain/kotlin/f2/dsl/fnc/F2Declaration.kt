package f2.dsl.fnc

import kotlin.js.Promise

@JsExport
@JsName("F2FunctionDeclaration")
actual external interface F2Function<T, R> {
	fun invoke(cmd: T): Promise<R>
}

@JsExport
@JsName("F2SupplierDeclaration")
actual external interface F2Supplier<R> {
	fun invoke(): Promise<String>
}

@JsExport
@JsName("F2ConsumerDeclaration")
actual external interface F2Consumer<T> {
	fun invoke(value: T)
}
