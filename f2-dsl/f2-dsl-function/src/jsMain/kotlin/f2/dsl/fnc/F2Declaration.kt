package f2.dsl.fnc

import kotlin.js.Promise

@JsExport
@JsName("F2Function")
actual interface F2Function<in T, out R> {
	fun invoke(cmd: Array<out T>): Promise<Array<out R>>
}

@JsExport
@JsName("F2Supplier")
actual interface F2Supplier<R> {
	fun invoke(): Promise<Array<R>>
}

@JsExport
@JsName("F2Consumer")
actual interface F2Consumer<T> {
	fun invoke(cmd: Array<T>): Promise<Unit>
}

actual interface F2SupplierSingle<R> {
	fun invoke(): Promise<R>
}
