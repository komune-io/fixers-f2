package f2.dsl.lambda

import kotlin.js.Promise

/**
 *  suspend () -> RESULT
 */
actual interface F2SupplierSingle<RESULT> {
	fun invoke(): Promise<RESULT>
}

/**
 * suspend () -> Flow<RESULT>
 */
actual interface F2SupplierFlow<RESULT> {
	fun invoke(): Promise<Array<RESULT>>
}
