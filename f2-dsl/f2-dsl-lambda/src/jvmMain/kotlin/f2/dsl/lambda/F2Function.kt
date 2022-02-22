package f2.dsl.lambda

import kotlinx.coroutines.flow.Flow

/**
 *  suspend (PAYLOAD) -> RESULT
 */
actual interface F2FunctionSingle<PAYLOAD, RESULT> : (PAYLOAD) -> (RESULT)

/**
 * 	suspend (PAYLOAD) -> Flow<RESULT>
 */
actual interface F2FunctionFlow<PAYLOAD, RESULT> : (PAYLOAD) -> (Flow<RESULT>)

/**
 * 	suspend (Flow<PAYLOAD>) -> Flow<RESULT>
 */
actual interface F2FunctionChannel<PAYLOAD, RESULT> : (Flow<PAYLOAD>) -> (Flow<RESULT>)
