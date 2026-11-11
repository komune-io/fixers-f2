package f2.client.domain


typealias RealmId = String

sealed class AuthRealm(
    open val serverUrl: String,
    open val realmId: RealmId,
    open val clientId: String,
    open val redirectUrl: String?,
)

/** A realm a user signs in to with a password. Not a data class: its generated `toString` would print it. */
class AuthRealmPassword(
    override val serverUrl: String,
    override val realmId: RealmId,
    override val redirectUrl: String,
    override val clientId: String,
    val username: String,
    val password: String,
): AuthRealm(serverUrl, realmId, clientId, redirectUrl) {
    override fun toString(): String =
        "AuthRealmPassword(serverUrl=$serverUrl, realmId=$realmId, clientId=$clientId, username=$username)"
}

/** A realm a client signs in to with its secret. Not a data class: its generated `toString` would print it. */
class AuthRealmClientSecret(
    override val serverUrl: String,
    override val realmId: RealmId,
    override val clientId: String,
    override val redirectUrl: String? = null,
    val clientSecret: String,
    val isPublic: Boolean = false,
): AuthRealm(serverUrl, realmId, clientId, redirectUrl) {
    override fun toString(): String =
        "AuthRealmClientSecret(serverUrl=$serverUrl, realmId=$realmId, clientId=$clientId, isPublic=$isPublic)"
}
