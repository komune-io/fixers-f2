package f2.client.ktor.http

import f2.client.F2Client
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.content.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList

actual open class HttpF2Client(
	private val httpClient: HttpClient,
	private val scheme: String,
	private val host: String,
	private val port: Int,
	private val path: String?,
) : F2Client {

	actual override fun get(route: String) = object : F2Supplier<String> {
		override suspend fun invoke(): Flow<String> {
			return httpClient.get(scheme = scheme, host = host, port = port, "${path ?: ""}/${route}")
		}

	}

	actual override fun invoke(route: String) = object : F2Function<String, String> {
		override suspend fun invoke(msg: Flow<String>): Flow<String> {
			val list = msg.toList()
			val va: String = httpClient.post(scheme = scheme, host = host, port = port, "${path ?: ""}/${route}") {
				this.body = TextContent(
					text = list.first(),
					ContentType.Application.Json
				)
			}
			return flow {
				emit(va)
			}
		}
	}

}