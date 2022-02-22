package f2.dsl.lambda

/**
 * 	suspend (PAYLOAD) -> Unit
 */
actual interface F2ConsumerSingle<PAYLOAD>  {
	fun invoke(cmd: PAYLOAD)
}

/**
 * 	suspend (PAYLOAD) -> Unit
 */
actual interface F2ConsumerFlow<PAYLOAD> {
	fun invoke(cmd: Array<PAYLOAD>)
}
