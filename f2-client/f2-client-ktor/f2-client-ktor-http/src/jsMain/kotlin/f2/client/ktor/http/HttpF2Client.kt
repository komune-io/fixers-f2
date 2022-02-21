package f2.client.ktor.http

import f2.client.F2Client
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlin.js.Promise

actual open class HttpF2Client(
	private val httpClient: HttpClient,
	private val scheme: String,
	private val host: String,
	private val port: Int,
	private val path: String?,
) : F2Client {

	actual override fun supplier(route: String): F2Supplier<String> = object : F2Supplier<String> {
		override fun invoke(): Promise<Array<String>> = GlobalScope.promise {
			httpClient.get(scheme = scheme, host = host, port = port, "${path ?: ""}/${route}")
		}
	}

	actual override fun function(route: String): F2Function<String, String> =
		object : F2Function<String, String> {
			override fun invoke(cmd: String): Promise<String> = GlobalScope.promise {
				httpClient.post(scheme = scheme, host = host, port = port, "${path ?: ""}/${route}", body = cmd)
			}
		}

	actual override fun consumer(route: String): F2Consumer<String> =
		object : F2Consumer<String> {
			override fun invoke(cmd: String): Promise<Unit> = GlobalScope.promise {
				httpClient.get<Unit>(scheme = scheme, host = host, port = port, "${path ?: ""}/${route}", body = cmd)
			}
		}
}
