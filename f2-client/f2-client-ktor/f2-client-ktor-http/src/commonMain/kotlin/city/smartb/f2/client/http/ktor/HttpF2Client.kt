package city.smartb.f2.client.http.ktor

import f2.client.F2Client
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.content.*
import io.ktor.http.*

open class HttpF2Client(
	private val httpClient: HttpClient,
	private val scheme: String,
	private val host: String,
	private val port: Int,
	private val path: String?
): F2Client {

	override suspend fun invoke(route: String, command: String): String {
		return httpClient.post(scheme = scheme, host = host, port = port, "${path ?: ""}/${route}") {
			body =  TextContent(command, contentType = ContentType.Application.Json)
		}
	}

}