package f2.dsl.fnc

import kotlin.js.Promise

@JsExport
@JsName("F2FunctionDeclaration")
actual interface F2FunctionDeclaration<T, R> {
	fun invoke(cmd: T): Promise<R>
}

@JsExport
@JsName("F2SupplierDeclaration")
actual interface F2SupplierDeclaration<R>{
	fun invoke(): Promise<List<R>>
}

@JsExport
@JsName("F2ConsumerDeclaration")
actual interface F2ConsumerDeclaration<T> {
	fun invoke(value: T)
}