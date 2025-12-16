package io.komune.f2.spring.boot.auth.config

import io.komune.f2.spring.boot.auth.security.TrustedIssuerJwtAuthenticationManagerResolver
import jakarta.annotation.security.PermitAll
import jakarta.annotation.security.RolesAllowed
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.annotation.Order
import org.springframework.security.authorization.AuthorizationDecision
import org.springframework.security.authorization.AuthorizationResult
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.Authentication
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.security.oauth2.server.resource.authentication.JwtIssuerReactiveAuthenticationManagerResolver
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import reactor.core.publisher.Mono

@Order(value = 100)
@Configuration
@ConditionalOnMissingBean(WebSecurityConfig::class)
@EnableConfigurationProperties(F2TrustedIssuersConfig::class)
class WebSecurityConfig {

    companion object {
        const val SPRING_SECURITY_FILTER_CHAIN = "springSecurityFilterChain"
        const val ROLE_PREFIX = "ROLE_"
    }

    private val logger = LoggerFactory.getLogger(WebSecurityConfig::class.java)

    @Value("\${spring.cloud.function.web.path:}")
    lateinit var contextPath: String

    @Autowired
    lateinit var applicationContext: ApplicationContext

    @Autowired
    lateinit var f2TrustedIssuersResolver: F2TrustedIssuersConfig

    @Bean
    @ConfigurationProperties(prefix = "f2.filter")
    fun authFilter(): Map<String, String> = HashMap()

    @Bean(SPRING_SECURITY_FILTER_CHAIN)
    @ConditionalOnExpression(NO_AUTHENTICATION_REQUIRED_EXPRESSION)
    @ConditionalOnBean(ServerHttpSecurity::class)
    fun dummyAuthenticationProvider(http: ServerHttpSecurity): SecurityWebFilterChain {
        logger.trace("Executing dummyAuthenticationProvider (permitAll)")

        http.authorizeExchange { exchange ->
            logger.trace("Setting up authorization for all exchanges to permitAll")
            exchange.anyExchange().permitAll()
        }
        http.csrf { csrf ->
            logger.trace("Disabling CSRF")
            csrf.disable()
        }
        http.corsConfig()

        logger.trace("Building dummy SecurityWebFilterChain (permitAll)")
        return http.build()
    }

    @Bean(SPRING_SECURITY_FILTER_CHAIN)
    @ConditionalOnExpression(AUTHENTICATION_REQUIRED_EXPRESSION)
    fun oauthAuthenticationProvider(http: ServerHttpSecurity): SecurityWebFilterChain {
        logger.trace("Executing oauthAuthenticationProvider (with authentication required)")

        addAuthenticationRules(http)
        http.csrf { csrf ->
            logger.trace("Disabling CSRF")
            csrf.disable()
        }
        http.corsConfig()

        logger.trace("Building SecurityWebFilterChain with authentication required")
        return http.build()
    }

    fun addAuthenticationRules(http: ServerHttpSecurity) {
        logger.trace("Adding authentication rules")
        addRolesAllowedRules(http)
        addPermitAllRules(http)
        addMandatoryAuthRules(http)
        addJwtParsingRules(http)
    }

    private fun authenticate(
        authentication: Mono<Authentication>
    ): Mono<AuthorizationResult> {
        logger.trace("Authenticating using custom filter")

        return authentication.map { auth ->
            if (auth !is JwtAuthenticationToken || auth.token == null) {
                logger.trace("Authentication failed: JWT token is missing or invalid")
                return@map false
            }

            val filters = authFilter()
            val decision = filters.isEmpty() || filters.all { (key, value) ->
                auth.token.claims[key] == value
            }

            logger.trace("Authentication decision: $decision")
            decision
        }.map { granted -> AuthorizationDecision(granted) }
    }

    private fun ServerHttpSecurity.corsConfig() {
        logger.trace("Setting up CORS configuration")

        val config = CorsConfiguration()
        config.allowedOrigins = listOf("*")
        config.allowCredentials = false
        config.addAllowedMethod("*")
        config.addAllowedHeader("*")

        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", config)
        cors { spec ->
            logger.trace("Registering CORS configuration")
            spec.configurationSource(source)
        }
    }

    fun addRolesAllowedRules(http: ServerHttpSecurity) {
        logger.trace("Adding RolesAllowed rules")

        applicationContext.getBeanNamesForAnnotation(RolesAllowed::class.java)
            .associateWith { bean ->
                applicationContext.findAnnotationOnBean(bean, RolesAllowed::class.java)!!.value
            }
            .forEach { (name, roles) ->
                logger.trace("Setting up role-based authorization for $name with roles: ${roles.joinToString()}")
                http.authorizeExchange { exchange ->
                    exchange.pathMatchers("$contextPath/$name")
                        .hasAnyRole(*roles)
                }
            }
    }

    fun addPermitAllRules(http: ServerHttpSecurity) {
        logger.trace("Adding PermitAll rules")

        val permitAllBeans = applicationContext.getBeanNamesForAnnotation(PermitAll::class.java)
            .map { bean -> "$contextPath/$bean" }
            .toTypedArray()

        if (permitAllBeans.isNotEmpty()) {
            logger.trace("Setting up permitAll for beans: ${permitAllBeans.joinToString()}")
            http.authorizeExchange { exchange ->
                exchange.pathMatchers(*permitAllBeans).permitAll()
            }
        }
    }

    fun addMandatoryAuthRules(http: ServerHttpSecurity) {
        logger.trace("Adding mandatory authentication rules")
        http.authorizeExchange { exchange ->
            exchange.anyExchange()
                .access { authentication, _ ->
                    logger.trace("Processing authentication")
                    authenticate(authentication)
                }
        }
    }

    fun addJwtParsingRules(http: ServerHttpSecurity) {
        logger.trace("Adding JWT parsing rules")

        val trustedIssuerJwtAuthenticationManagerResolver = TrustedIssuerJwtAuthenticationManagerResolver(
            f2TrustedIssuersResolver
        )
        http.oauth2ResourceServer { oauth2 ->
            logger.trace("Setting up OAuth2 resource server with trusted issuers")
            oauth2.authenticationManagerResolver(
                JwtIssuerReactiveAuthenticationManagerResolver(trustedIssuerJwtAuthenticationManagerResolver)
            )
        }
    }
}
