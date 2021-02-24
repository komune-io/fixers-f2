package f2.dsl

actual interface F2Remote<T, R> {
	suspend fun invoke(cmd: T): R
}