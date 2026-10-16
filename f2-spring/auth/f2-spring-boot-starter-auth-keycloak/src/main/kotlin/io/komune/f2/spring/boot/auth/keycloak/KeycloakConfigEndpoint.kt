package io.komune.f2.spring.boot.auth.keycloak

import jakarta.annotation.security.PermitAll
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@EnableConfigurationProperties(F2KeycloakConfig::class)
class KeycloakConfigEndpoint(
    private val keycloakConfigResolver: KeycloakConfigResolver
) {

    @PermitAll
    @Bean
    fun keycloak(): (name: String) -> KeycloakConfig = { name ->
        keycloakConfigResolver.resolve(name)
    }
}
