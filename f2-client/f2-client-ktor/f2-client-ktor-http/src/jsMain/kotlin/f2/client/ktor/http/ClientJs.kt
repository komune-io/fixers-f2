package f2.client.ktor.http

import f2.client.F2Client
import f2.dsl.fnc.F2ConsumerDeclaration
import f2.dsl.fnc.F2FunctionDeclaration
import f2.dsl.fnc.F2SupplierDeclaration
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.request.*
import io.ktor.http.*
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

	actual override fun get(route: String): F2SupplierDeclaration<String> = object : F2SupplierDeclaration<String> {
		override fun invoke(): Promise<List<String>> = GlobalScope.promise {
			httpClient.get(scheme = scheme, host = host, port = port, "${path ?: ""}/${route}")
		}
	}

	actual override fun invoke(route: String): F2FunctionDeclaration<String, String> =
		object : F2FunctionDeclaration<String, String> {
			override fun invoke(cmd: String): Promise<String> = GlobalScope.promise {
				httpClient.get(scheme = scheme, host = host, port = port, "${path ?: ""}/${route}")
			}
		}

}