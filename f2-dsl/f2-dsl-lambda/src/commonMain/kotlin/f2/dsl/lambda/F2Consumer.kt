package f2.dsl.lambda

object F2Consumer {
	interface Single<PAYLOAD>: F2ConsumerSingle<PAYLOAD>
	interface Flow<PAYLOAD>: F2ConsumerFlow<PAYLOAD>
}

/**
 * 	suspend (PAYLOAD) -> Unit
 */
expect interface F2ConsumerSingle<PAYLOAD>
/**
 * 	suspend (PAYLOAD) -> Unit
 */
expect interface F2ConsumerFlow<PAYLOAD>
