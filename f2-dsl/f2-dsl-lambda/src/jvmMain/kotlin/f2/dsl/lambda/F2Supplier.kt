package f2.dsl.lambda

import kotlinx.coroutines.flow.Flow

/**
 *  suspend () -> RESULT
 */
actual interface F2SupplierSingle<RESULT> {
	fun invoke(): RESULT
}

/**
 * suspend () -> Flow<RESULT>
 */
actual interface F2SupplierFlow<RESULT> {
	fun invoke(): Flow<RESULT>
}
