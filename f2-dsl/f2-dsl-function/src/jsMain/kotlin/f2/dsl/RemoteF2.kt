package f2.dsl

import kotlin.js.Promise

actual interface F2Remote<T, R> {
	fun invoke(cmd: T): Promise<R>
}