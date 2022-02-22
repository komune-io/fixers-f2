package f2.dsl.lambda

object F2Supplier {
	interface Single<RESULT>: F2SupplierSingle<RESULT>
	interface Flow<RESULT>: F2SupplierFlow<RESULT>
}

/**
 *  suspend () -> RESULT
 */
expect interface F2SupplierSingle<RESULT>
/**
 * suspend () -> Flow<RESULT>
 */
expect interface F2SupplierFlow<RESULT>
