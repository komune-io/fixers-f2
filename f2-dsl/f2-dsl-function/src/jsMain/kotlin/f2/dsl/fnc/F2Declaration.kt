package f2.dsl.fnc

import kotlin.js.Promise

@JsExport
@JsName("F2Function")
actual interface F2Function<T, R> {
	fun invoke(cmd: T): Promise<R>
}

@JsExport
@JsName("F2Supplier")
actual interface F2Supplier<R> {
	fun invoke(): Promise<Array<R>>
}

@JsExport
@JsName("F2Consumer")
actual interface F2Consumer<T> {
	fun invoke(cmd: T): Promise<Unit>
}
