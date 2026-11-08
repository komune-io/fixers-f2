package io.komune.f2.spring.boot.auth.config

import jakarta.annotation.security.PermitAll
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpMethod
import org.springframework.security.config.web.server.ServerHttpSecurity

// F2 clients call a supplier with GET and a function with POST. spring-cloud-function also maps
// PUT and DELETE on a function's path, and HEAD is not served there: those stay authenticated.
private val PERMIT_ALL_METHODS = listOf(HttpMethod.GET, HttpMethod.POST)

/**
 * Opens the path of every `@PermitAll` bean to requests without a token, on GET and POST only.
 *
 * @return the opened paths
 */
fun ServerHttpSecurity.permitAllFunctionBeans(
    applicationContext: ApplicationContext,
    contextPath: String,
): List<String> {
    val paths = applicationContext.getBeanNamesForAnnotation(PermitAll::class.java)
        .map { bean -> "$contextPath/$bean" }
    if (paths.isNotEmpty()) {
        authorizeExchange { exchange ->
            PERMIT_ALL_METHODS.forEach { method ->
                exchange.pathMatchers(method, *paths.toTypedArray()).permitAll()
            }
        }
    }
    return paths
}
