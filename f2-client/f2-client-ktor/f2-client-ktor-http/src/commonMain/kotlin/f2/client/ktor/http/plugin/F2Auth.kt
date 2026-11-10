package f2.client.ktor.http.plugin

import f2.client.domain.AuthRealm
import f2.client.domain.AuthRealmClientSecret
import f2.client.domain.AuthRealmPassword
import f2.client.domain.AuthRealmProvider
import f2.client.domain.TokenInfo
import f2.client.ktor.common.F2DefaultJson
import f2.client.ktor.http.httpClientBuilderGenerics
import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.error.asException
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.HttpClientPlugin
import io.ktor.client.plugins.api.ClientPluginInstance
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.AuthConfig
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.isSuccess
import io.ktor.http.parametersOf
import io.ktor.util.AttributeKey
import io.ktor.util.logging.KtorSimpleLogger
import kotlinx.serialization.json.Json

typealias OnTokenRequestSentAction = HttpRequestBuilder.() -> Unit

/**
 * Ktor client plugin fetching and refreshing OAuth2 bearer tokens for the [AuthRealm] returned by
 * [getAuth].
 *
 * Configure it through the install block, e.g.
 * ```
 * install(F2Auth) {
 *     getAuth = { realm }
 *     isDebug = true // echo the auth provider response in error messages
 * }
 * ```
 *
 * @property json parser used to decode the token endpoint response.
 * @property isDebug when true, failures to decode the token response include the raw provider response
 * in the reported error message. Off by default: that response may carry sensitive material.
 */
class F2Auth(
    var json: Json = F2DefaultJson,
    var isDebug: Boolean = false
) {

    @Suppress("MissingUseCall") // the Auth plugin lives as long as the HttpClient it is installed on, which closes it
    private val authPlugin: ClientPluginInstance<AuthConfig> by lazy { prepareAuth() }

    // Tokens held by this plugin instance, i.e. scoped to the HttpClient it is installed on.
    private var lastBearerTokens: BearerTokens? = null

    private var authRealmProvider: AuthRealmProvider? = null

    /** The realm to fetch tokens for, set in `install(F2Auth) { getAuth = … }`. */
    var getAuth: AuthRealmProvider
        get() = checkNotNull(authRealmProvider) { "F2Auth needs getAuth: set it in install(F2Auth) { getAuth = … }" }
        set(value) {
            authRealmProvider = value
        }

    @Suppress("MissingUseCall") // the Auth plugin lives as long as the HttpClient it is installed on, which closes it
    private fun prepareAuth(): ClientPluginInstance<AuthConfig> = Auth.prepare {
        bearer {
            loadTokens {
                genOAuthToken(lastBearerTokens)
            }
            refreshTokens {
                genOAuthToken(oldTokens) {
                    markAsRefreshTokenRequest()
                }
            }
        }
    }

    private suspend fun genOAuthToken(
        oldTokens: BearerTokens?,
        onTokenRequestSent: OnTokenRequestSentAction? = null
    ): BearerTokens? {
        val authRealm = getAuth()
        val parameters: Map<String, String> = if (oldTokens?.refreshToken.isNullOrBlank()) {
            generateNewToken(authRealm)
        } else {
            refreshToken(oldTokens, authRealm)
        }
        val params = parametersOf(parameters.mapValues { (_, value) -> listOf(value) })
        val tokenResponse = httpClientBuilderGenerics().build(authRealm.serverUrl).httpClient.use { client ->
            val response = client.post {
                url("${authRealm.serverUrl}/realms/${authRealm.realmId}/protocol/openid-connect/token")
                setBody(FormDataContent(params))
                if (onTokenRequestSent != null) {
                    onTokenRequestSent()
                }
            }
            response.status to response.body<String>()
        }
        val (status, tokenInfo) = tokenResponse
        if (!status.isSuccess()) {
            val debugInfo = if (isDebug) " Response: $tokenInfo" else ""
            throw F2Error(
                message = "The auth provider refused the token request with ${status.value}.$debugInfo",
                code = status.value,
            ).asException()
        }
        return try {
            val refreshTokenInfo = json.decodeFromString<TokenInfo>(tokenInfo)
            lastBearerTokens = BearerTokens(
                accessToken = refreshTokenInfo.accessToken,
                refreshToken = refreshTokenInfo.refreshToken.orEmpty()
            )
            lastBearerTokens
        } catch (e: IllegalArgumentException) {
            val debugInfo = if (isDebug) "Invalid argument encountered: $tokenInfo" else ""
            throw F2Error(
                message = "Unable to decode response from auth provider. $debugInfo"
            ).asException(e)
        }
    }

    private fun refreshToken(oldTokens: BearerTokens?, authRealm: AuthRealm): Map<String, String> {
        LOGGER.debug("Refresh Token: grant_type[refresh_token] with client_id[${authRealm.clientId}] " +
                "and refresh_token=${oldTokens?.refreshToken?.take(n = 5) ?: "none"}...}")
        return mapOf(
            GRANT_TYPE to "refresh_token",
            CLIENT_ID to authRealm.clientId,
            "refresh_token" to oldTokens?.refreshToken.orEmpty()
        )
    }

    private fun generateNewToken(authRealm: AuthRealm): Map<String, String>  = when (authRealm) {
        is AuthRealmClientSecret -> {
            LOGGER.debug("Generate token: grant_type[client_credentials] " +
                    "with client_id[${authRealm.clientId}] and client_secret=***")
            mapOf(
                GRANT_TYPE to "client_credentials",
                CLIENT_ID to authRealm.clientId,
                "client_secret" to authRealm.clientSecret,
            )
        }

        is AuthRealmPassword -> {
            LOGGER.debug("Generate token: grant_type[password] " +
                    "with client_id ${authRealm.clientId}, username[${authRealm.username}] and password=***")
            mapOf(
                GRANT_TYPE to "password",
                CLIENT_ID to authRealm.clientId,
                "scope" to "openid",
                "username" to authRealm.username,
                "password" to authRealm.password
            )
        }
    }

    companion object Plugin: HttpClientPlugin<F2Auth, F2Auth> {
        private const val GRANT_TYPE = "grant_type"
        private const val CLIENT_ID = "client_id"

        val LOGGER = KtorSimpleLogger("f2.client.ktor.http.plugin.F2Auth")

        override val key: AttributeKey<F2Auth> = AttributeKey("F2Auth")

        override fun prepare(block: F2Auth.() -> Unit): F2Auth = F2Auth().apply(block)

        override fun install(plugin: F2Auth, scope: HttpClient) {
            Auth.install(plugin.authPlugin, scope)
        }
    }
}
