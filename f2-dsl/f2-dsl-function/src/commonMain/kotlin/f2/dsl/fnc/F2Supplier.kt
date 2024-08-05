package f2.dsl.fnc

import kotlinx.coroutines.flow.Flow


typealias F2LambdaSupplier<R> =  suspend () -> Flow<R>

expect fun interface F2Supplier<R> : F2LambdaSupplier<R> {
    override suspend operator fun invoke(): Flow<R>
}

typealias F2LambdaSupplierSingle<R> =  suspend () -> R


expect fun interface F2SupplierSingle<R>: F2LambdaSupplierSingle<R> {
    override suspend operator fun invoke(): R
}

