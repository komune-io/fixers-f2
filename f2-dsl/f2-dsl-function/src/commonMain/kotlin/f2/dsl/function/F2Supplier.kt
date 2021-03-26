package f2.dsl.function

import kotlinx.coroutines.flow.Flow

typealias F2Supplier<T> = suspend () -> Flow<T>
expect interface F2SupplierRemote<T>