package f2.dsl.function

import kotlin.js.Promise

@JsExport
@JsName("F2FunctionRemote")
actual interface F2FunctionRemote<T, R> {
	fun invoke(cmd: T): Promise<R>
}

@JsExport
@JsName("F2SupplierRemote")
actual interface F2SupplierRemote<T>{
	fun invoke(): Promise<List<T>>
}

@JsExport
@JsName("F2ConsumerRemote")
actual interface F2ConsumerRemote<T> {
	fun invoke(value: T)
}