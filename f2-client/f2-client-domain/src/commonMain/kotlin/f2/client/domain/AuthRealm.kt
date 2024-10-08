package f2.client.domain

import kotlin.js.JsExport

typealias RealmId = String

@JsExport
sealed class AuthRealm(
    open val serverUrl: String,
    open val realmId: RealmId,
    open val clientId: String,
    open val redirectUrl: String?,
)

@JsExport
data class AuthRealmPassword(
    override val serverUrl: String,
    override val realmId: RealmId,
    override val redirectUrl: String,
    override val clientId: String,
    val username: String,
    val password: String,
): AuthRealm(serverUrl, realmId, clientId, redirectUrl)

@JsExport
data class AuthRealmClientSecret(
    override val serverUrl: String,
    override val realmId: RealmId,
    override val clientId: String,
    override val redirectUrl: String? = null,
    val clientSecret: String,
    val isPublic: Boolean = false,
): AuthRealm(serverUrl, realmId, clientId, redirectUrl)
