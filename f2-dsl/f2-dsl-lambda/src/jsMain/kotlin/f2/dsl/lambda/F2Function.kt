package f2.dsl.lambda

/**
 *  suspend (PAYLOAD) -> RESULT
 */
actual interface F2FunctionSingle<PAYLOAD, RESULT> {
	suspend operator fun invoke(msg: PAYLOAD): RESULT
}

/**
 * 	suspend (PAYLOAD) -> Flow<RESULT>
 */
actual interface F2FunctionFlow<PAYLOAD, RESULT> {
	suspend operator fun invoke(msg: PAYLOAD): Array<RESULT>
}

/**
 * 	suspend (Flow<PAYLOAD>) -> Flow<RESULT>
 */
actual interface F2FunctionChannel<PAYLOAD, RESULT> {
	suspend operator fun invoke(msg: Array<PAYLOAD>): Array<RESULT>
}
