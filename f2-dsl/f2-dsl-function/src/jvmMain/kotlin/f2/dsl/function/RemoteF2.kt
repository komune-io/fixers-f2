package f2.dsl.function

import kotlinx.coroutines.flow.Flow

actual interface F2FunctionRemote<T, R> {
	suspend fun invoke(cmd: T): R
}

actual interface F2SupplierRemote<T> {
	suspend fun invoke(): Flow<T>
}

actual interface F2ConsumerRemote<T> {
	suspend fun invoke(cmd: Flow<T>)
}
