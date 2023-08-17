package f2.client.ktor.auth.impl

class AuthPluginConfig {
    lateinit var auth: AuthProperties
}

class AuthProperties(
    val serverUrl: String,
    val realm: String,
    val clientId: String,
    val clientSecret: String? = null,
    val username: String? = null,
    val password: String? = null,
)

fun AuthProperties.toAuthRealm(): AuthRealm {
    return if (clientSecret != null) {
            AuthRealmClientSecret(
                serverUrl = serverUrl,
                realmId = realm,
                clientId = clientId,
                clientSecret = clientSecret,
                redirectUrl = null
            )
        } else if (username != null && password != null) {
            AuthRealmPassword(
                serverUrl = serverUrl,
                realmId = realm,
                clientId = clientId,
                username = username,
                password = password,
                redirectUrl = ""
            )

        } else {
            throw IllegalStateException("Either clientSecret or username and password must be provided")
        }
}