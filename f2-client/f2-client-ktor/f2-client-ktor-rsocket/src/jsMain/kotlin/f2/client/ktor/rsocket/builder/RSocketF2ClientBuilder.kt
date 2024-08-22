package f2.client.ktor.rsocket.builder

import f2.client.ktor.common.F2ClientConfigLambda
import f2.client.ktor.common.applyConfig
import f2.client.ktor.rsocket.RSocketClient
import f2.client.ktor.rsocket.RSocketF2Client
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import io.rsocket.kotlin.RSocket
import io.rsocket.kotlin.ktor.client.rSocket
import kotlin.js.Promise
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlinx.serialization.json.Json
/**
 * Actual implementation of [RSocketF2ClientBuilder] for the JavaScript platform.
 *
 * @param json Optional JSON configuration. Defaults to null.
 * @param config Additional configuration for the RSocket client. Defaults to null.
 */
@JsExport
actual class RSocketF2ClientBuilder(
	private val json: Json? = null,
	private val config: F2ClientConfigLambda<*>? = null
) {
	/**
	 * Builds an [RSocketF2Client] for RSocket communication.
	 *
	 * @param url The URL to connect to.
	 * @param secure Whether to use a secure connection.
	 * @return An instance of [RSocketF2Client].
	 */
	@JsExport.Ignore
	actual suspend fun build(
		url: String,
		secure: Boolean,
	): RSocketF2Client {
		val rSocket: RSocket = build().rSocket(url, secure = secure)
		val client = RSocketClient(rSocket)
		return RSocketF2Client(client)
	}

	/**
	 * Builds an [RSocketF2Client] for RSocket communication as a JavaScript Promise.
	 *
	 * @param url The URL to connect to.
	 * @param secure Whether to use a secure connection.
	 * @return A Promise of [RSocketF2Client].
	 */
	fun buildPromise(
		url: String,
		secure: Boolean,
	): Promise<RSocketF2Client> = GlobalScope.promise {
		build(url, secure)
	}

	/**
	 * Internal function to build an [HttpClient] with RSocket and additional configurations.
	 *
	 * @return An instance of [HttpClient].
	 */
	private fun build(): HttpClient = HttpClient(Js) {
		applyRSocket()
		applyConfig(json, config)
	}

	/**
	 * Builds an [RSocketClient] for RSocket communication.
	 *
	 * @param baseUrl The base URL to connect to.
	 * @param bearerJwt The JWT token for authentication. Defaults to null.
	 * @return An instance of [RSocketClient].
	 */
	@JsExport.Ignore
	actual suspend fun rsocketClient(
		baseUrl: String,
		bearerJwt: String?,
	): RSocketClient {
		val rSocket: RSocket = build().rSocket(baseUrl, false)
		return RSocketClient(rSocket)
	}
}

/**
 * Provides a default instance of [RSocketF2ClientBuilder].
 *
 * @return A default instance of [RSocketF2ClientBuilder].
 */
actual fun rSocketF2ClientBuilderDefault(): RSocketF2ClientBuilder = RSocketF2ClientBuilder()

/**
 * Provides a generic instance of [RSocketF2ClientBuilder] with optional JSON configuration.
 *
 * @param json The JSON configuration to use. Defaults to null.
 * @param config Additional configuration for the RSocket client. Defaults to null.
 * @return An instance of [RSocketF2ClientBuilder] with the specified configuration.
 */
actual fun rSocketF2ClientBuilderGenerics(
	json: Json?,
	config: F2ClientConfigLambda<*>?
): RSocketF2ClientBuilder {
	return RSocketF2ClientBuilder(json, config)
}
