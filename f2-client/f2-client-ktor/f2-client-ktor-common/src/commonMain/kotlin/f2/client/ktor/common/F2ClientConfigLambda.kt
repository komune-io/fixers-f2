package f2.client.ktor.common

import io.ktor.client.HttpClientConfig

/**
 * Represents a lambda that configures an [HttpClientConfig].
 *
 * @param T The type of the HTTP client engine configuration.
 */
typealias F2ClientConfigLambda<T> = HttpClientConfig<T>.() -> Unit

