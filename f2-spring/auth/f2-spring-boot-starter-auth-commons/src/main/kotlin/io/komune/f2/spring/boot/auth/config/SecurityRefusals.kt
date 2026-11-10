package io.komune.f2.spring.boot.auth.config

import f2.spring.exception.config.writeRefusal
import f2.spring.exception.statusError
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.ServerAuthenticationEntryPoint
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler
import tools.jackson.databind.json.JsonMapper

/** The bearer scheme a 401 names, and nothing the token was rejected for: its decoding message stays here. */
private const val BEARER_CHALLENGE = "Bearer"

/**
 * Answers the 401 of a request without a valid token: `WWW-Authenticate: Bearer` and the F2 error body
 * `http.UNAUTHENTICATED`, whatever the token failed on.
 */
fun f2AuthenticationEntryPoint(jsonMapper: JsonMapper) = ServerAuthenticationEntryPoint { exchange, _ ->
    exchange.response.headers.set(HttpHeaders.WWW_AUTHENTICATE, BEARER_CHALLENGE)
    exchange.writeRefusal(statusError(HttpStatus.UNAUTHORIZED), jsonMapper)
}

/** Answers the 403 of a token without the rights a path asks for with the F2 error body `http.FORBIDDEN`. */
fun f2AccessDeniedHandler(jsonMapper: JsonMapper) = ServerAccessDeniedHandler { exchange, _ ->
    exchange.writeRefusal(statusError(HttpStatus.FORBIDDEN), jsonMapper)
}

/**
 * Answers every 401 and 403 of this chain — the authorization rules' and the OAuth2 resource server's — with the
 * F2 error body every other error gets, written with the application's [jsonMapper].
 */
fun ServerHttpSecurity.answerDenialsWithF2Body(jsonMapper: JsonMapper) {
    val entryPoint = f2AuthenticationEntryPoint(jsonMapper)
    val accessDenied = f2AccessDeniedHandler(jsonMapper)
    exceptionHandling { handling ->
        handling.authenticationEntryPoint(entryPoint).accessDeniedHandler(accessDenied)
    }
    oauth2ResourceServer { oauth2 ->
        oauth2.authenticationEntryPoint(entryPoint).accessDeniedHandler(accessDenied)
    }
}
