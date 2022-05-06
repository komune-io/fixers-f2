package f2.client.ktor.http

import f2.client.F2Client
import f2.client.jsonF2Config
import io.ktor.serialization.kotlinx.json.json
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation

typealias GenerateToken = suspend () -> BearerTokens?

actual class HttpClientBuilder {
	private fun httpClient(
		generateBearerToken: GenerateToken?
	): HttpClient {
		return HttpClient(CIO) {
			install(ContentNegotiation) {
				json(jsonF2Config)
			}
			if (generateBearerToken != null) {
				install(Auth) {
					bearer {
						loadTokens {
							generateBearerToken()
						}
					}
				}
			}
		}
	}

	fun build(
		urlBase: String,
		generateBearerToken: GenerateToken? = { null }
	): F2Client {
		val httpClient = httpClient(generateBearerToken)
		return HttpF2Client(
			httpClient = httpClient,
			urlBase
		)
	}
}

actual fun httpClientBuilder() = HttpClientBuilder()
