package city.smartb.f2.client.http.ktor

import city.smartb.f2.dsl.cqrs.S2CQRSClient
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*

actual class HttpClientBuilder {
	private fun httpClient(): HttpClient {
		return HttpClient(CIO) {
			install(JsonFeature) {
				serializer = JacksonSerializer {
					this.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
						.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
						.registerModule(KotlinModule())
						.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
				}
			}
		}
	}

	actual fun cqrsClient(
		scheme: String,
		host: String,
		port: Int,
		path: String?
	): S2CQRSClient {
		val httpCLient = httpClient()
		return HttpCQRSClient(
			scheme = scheme,
			host = host,
			port = port,
			path = path,
			httpClient = httpCLient
		)
	}

}

actual fun httpClientBuilder() = HttpClientBuilder()