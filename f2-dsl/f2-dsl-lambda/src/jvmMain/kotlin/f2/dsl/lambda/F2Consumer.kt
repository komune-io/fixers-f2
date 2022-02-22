package f2.dsl.lambda

import kotlinx.coroutines.flow.Flow

/**
 * 	suspend (PAYLOAD) -> Unit
 */
actual interface F2ConsumerSingle<PAYLOAD> : (PAYLOAD) -> Unit

/**
 * 	suspend (PAYLOAD) -> Unit
 */
actual interface F2ConsumerFlow<PAYLOAD> : (Flow<PAYLOAD>) -> Unit
