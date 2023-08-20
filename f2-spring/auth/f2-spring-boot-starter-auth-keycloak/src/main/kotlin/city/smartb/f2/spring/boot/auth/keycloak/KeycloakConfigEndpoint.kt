package city.smartb.f2.spring.boot.auth.keycloak

import jakarta.annotation.security.PermitAll
import kotlinx.coroutines.runBlocking
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
        runBlocking {
            val keycloakConfig = keycloakConfigResolver.getKeycloakConfig(name)

            KeycloakConfig(
                realm = keycloakConfig.realm,
                authServerUrl = keycloakConfig.authServerUrl,
                resource = keycloakConfig.resource,
            )
        }
    }
}
