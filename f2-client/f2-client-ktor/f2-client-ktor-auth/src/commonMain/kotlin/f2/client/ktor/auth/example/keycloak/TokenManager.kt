package f2.client.ktor.auth.example.keycloak


/*
* Copyright 2016 Red Hat, Inc. and/or its affiliates
* and other contributors as indicated by the @author tags.
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import javax.ws.rs.client.WebTarget
import org.jboss.resteasy.client.jaxrs.ResteasyClient
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget
import org.keycloak.admin.client.Config
import org.keycloak.admin.client.Keycloak
import org.keycloak.admin.client.resource.BasicAuthFilter
import org.keycloak.common.util.Time
import org.keycloak.representations.AccessTokenResponse
import javax.ws.rs.BadRequestException
import javax.ws.rs.client.Client
import javax.ws.rs.core.Form
import kotlin.jvm.Synchronized
import org.keycloak.OAuth2Constants.CLIENT_CREDENTIALS
import org.keycloak.OAuth2Constants.CLIENT_ID
import org.keycloak.OAuth2Constants.GRANT_TYPE
import org.keycloak.OAuth2Constants.PASSWORD
import org.keycloak.OAuth2Constants.REFRESH_TOKEN
import org.keycloak.OAuth2Constants.SCOPE
import org.keycloak.OAuth2Constants.USERNAME

/**
 * @author rodrigo.sasaki@icarros.com.br
 */
class TokenManager(config: Config, client: Client) {
    private var currentToken: AccessTokenResponse? = null
    private var expirationTime: Long = 0
    private var minTokenValidity: Long = Companion.DEFAULT_MIN_VALIDITY
    private val config: Config
    private val tokenService: TokenService
    private val accessTokenGrantType: String

    init {
        this.config = config
        val target: WebTarget = client.target(config.getServerUrl())
        if (!config.isPublicClient()) {
            target.register(BasicAuthFilter(config.getClientId(), config.getClientSecret()))
        }
        tokenService = Keycloak.getClientProvider().targetProxy(target, TokenService::class.java)
        accessTokenGrantType = config.getGrantType()
        if (CLIENT_CREDENTIALS.equals(accessTokenGrantType) && config.isPublicClient()) {
            throw java.lang.IllegalArgumentException("Can't use $GRANT_TYPE=$CLIENT_CREDENTIALS with public client")
        }
    }

    val accessTokenString: String
        get() = accessToken.getToken()

    @get:Synchronized
    val accessToken: AccessTokenResponse?
        get() {
            if (currentToken == null) {
                grantToken()
            } else if (tokenExpired()) {
                refreshToken()
            }
            return currentToken
        }

    fun grantToken(): AccessTokenResponse? {
        val form: Form = Form().param(GRANT_TYPE, accessTokenGrantType)
        if (PASSWORD.equals(accessTokenGrantType)) {
            form.param(USERNAME, config.getUsername())
                .param(PASSWORD, config.getPassword())
        }
        if (config.getScope() != null) {
            form.param(SCOPE, config.getScope())
        }
        if (config.isPublicClient()) {
            form.param(CLIENT_ID, config.getClientId())
        }
        val requestTime: Int = Time.currentTime()
        kotlin.synchronized(this) {
            currentToken = tokenService.grantToken(config.getRealm(), form.asMap())
            expirationTime = requestTime + currentToken.getExpiresIn()
        }
        return currentToken
    }

    @Synchronized
    fun refreshToken(): AccessTokenResponse? {
        if (currentToken.getRefreshToken() == null) {
            return grantToken()
        }
        val form: Form = Form().param(GRANT_TYPE, REFRESH_TOKEN)
            .param(REFRESH_TOKEN, currentToken.getRefreshToken())
        if (config.isPublicClient()) {
            form.param(CLIENT_ID, config.getClientId())
        }
        return try {
            val requestTime: Int = Time.currentTime()
            currentToken = tokenService.refreshToken(config.getRealm(), form.asMap())
            expirationTime = requestTime + currentToken.getExpiresIn()
            currentToken
        } catch (e: BadRequestException) {
            grantToken()
        }
    }

    @Synchronized
    fun setMinTokenValidity(minTokenValidity: Long) {
        this.minTokenValidity = minTokenValidity
    }

    @Synchronized
    private fun tokenExpired(): Boolean {
        return Time.currentTime() + minTokenValidity >= expirationTime
    }

    /**
     * Invalidates the current token, but only when it is equal to the token passed as an argument.
     *
     * @param token the token to invalidate (cannot be null).
     */
    @Synchronized
    fun invalidate(token: String) {
        if (currentToken == null) {
            return  // There's nothing to invalidate.
        }
        if (token == currentToken.getToken()) {
            // When used next, this cause a refresh attempt, that in turn will cause a grant attempt if refreshing fails.
            expirationTime = -1
        }
    }

    companion object {
        private const val DEFAULT_MIN_VALIDITY: Long = 30
    }
}
