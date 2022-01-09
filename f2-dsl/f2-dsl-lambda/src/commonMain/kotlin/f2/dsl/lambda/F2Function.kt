package f2.dsl.lambda

object F2Function {
	interface Single<PAYLOAD, RESULT>: F2FunctionSingle<PAYLOAD, RESULT>
	interface Flow<PAYLOAD, RESULT>: F2FunctionFlow<PAYLOAD, RESULT>
	interface Channel<PAYLOAD, RESULT>: F2FunctionChannel<PAYLOAD, RESULT>
}

/**
 *  suspend (PAYLOAD) -> RESULT
 */
expect interface F2FunctionSingle<PAYLOAD, RESULT>
/**
 * 	suspend (PAYLOAD) -> Flow<RESULT>
 */
expect interface F2FunctionFlow<PAYLOAD, RESULT>
/**
 * 	suspend (Flow<PAYLOAD>) -> Flow<RESULT>
 */
expect interface F2FunctionChannel<PAYLOAD, RESULT>
