package city.smartb.f2.client.http.ktor

import city.smartb.f2.dsl.cqrs.S2CQRSClient
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.content.*
import io.ktor.http.*

open class HttpCQRSClient(
	private val httpClient: HttpClient,
	private val scheme: String,
	private val host: String ,
	private val port: Int,
	private val path: String?
): S2CQRSClient {

	override suspend fun invoke(route: String, command: String): String {
		return httpClient.post(scheme = scheme, host = host, port = port, "${path ?: ""}/${route}") {
			body =  TextContent(command, contentType = ContentType.Application.Json)
		}
	}

	override suspend fun fetch(route: String, query: String): String {
		return invoke(route, query)
	}

}