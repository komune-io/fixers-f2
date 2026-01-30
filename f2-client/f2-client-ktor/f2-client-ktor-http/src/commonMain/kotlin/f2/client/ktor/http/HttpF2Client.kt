package f2.client.ktor.http

import f2.client.F2Client
import f2.client.F2ClientType
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.client.HttpClient
import io.ktor.util.reflect.TypeInfo

expect open class HttpF2Client : F2Client {
    val httpClient: HttpClient
    val urlBase: String
    override val type: F2ClientType
    override fun <RESPONSE> supplier(route: String, responseTypeInfo: TypeInfo): F2Supplier<RESPONSE>
    override fun <QUERY, RESPONSE> function(
        route: String,
        queryTypeInfo: TypeInfo,
        responseTypeInfo: TypeInfo
    ): F2Function<QUERY, RESPONSE>
    override fun <QUERY> consumer(route: String, queryTypeInfo: TypeInfo): F2Consumer<QUERY>
}
