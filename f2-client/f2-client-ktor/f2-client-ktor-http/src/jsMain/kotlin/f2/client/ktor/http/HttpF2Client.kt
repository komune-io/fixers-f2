package f2.client.ktor.http

import f2.client.F2Client
import f2.client.F2ClientType
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.setBody
import io.ktor.util.reflect.TypeInfo
import kotlin.js.Promise
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise

actual open class HttpF2Client(
	protected val urlBase: String,
	actual val httpClient: HttpClient
) : F2Client {

	override val type: F2ClientType = F2ClientType.HTTP


	override fun <RESPONSE> supplier(
		route: String, typeInfo: TypeInfo
	): F2Supplier<RESPONSE> = object : F2Supplier<RESPONSE> {
		override fun invoke(): Promise<Array<RESPONSE>> = GlobalScope.promise {
			httpClient.get(urlBase).body(typeInfo)
		}
	}
	override fun <QUERY, RESPONSE> function(
		route: String,
		queryTypeInfo: TypeInfo,
		responseTypeInfo: TypeInfo,
	): F2Function<QUERY, RESPONSE> {
		TODO("Not yet implemented")
	}

	override fun <QUERY> consumer(route: String, queryTypeInfo: TypeInfo): F2Consumer<QUERY> =
		object : F2Consumer<QUERY> {
			override fun invoke(cmd: Array<QUERY>): Promise<Unit> = GlobalScope.promise {
				httpClient.get(urlBase) {
					setBody(cmd, queryTypeInfo)
				}
			}
		}
}
