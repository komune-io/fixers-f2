package f2.client.ktor.http

import f2.client.F2Client
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.content.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

open class HttpF2Client(
	private val httpClient: HttpClient,
	private val scheme: String,
	private val host: String,
	private val port: Int,
	private val path: String?
): F2Client {
	override suspend fun get(route: String): Flow<String> {
		return httpClient.get(scheme = scheme, host = host, port = port, "${path ?: ""}/${route}")
	}

	override suspend fun accept(route: String, command: String) {
		return httpClient.post(scheme = scheme, host = host, port = port, "${path ?: ""}/${route}") {
			body =  TextContent(command, contentType = ContentType.Application.Json)
		}
	}

	override suspend fun invoke(route: String, command: String): String {
		return httpClient.post(scheme = scheme, host = host, port = port, "${path ?: ""}/${route}") {
			body =  TextContent(command, contentType = ContentType.Application.Json)
		}
	}
}