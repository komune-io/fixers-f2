package f2.spring.observability.opentelemetry

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties

/**
 * Auto-configuration for F2 OpenTelemetry integration.
 *
 * This configuration is activated when OpenTelemetry endpoints are configured via:
 * - `f2.observability.opentelemetry.endpoint` (base endpoint for both metrics and traces)
 * - `f2.observability.opentelemetry.metrics.endpoint` (metrics-specific endpoint)
 * - `f2.observability.opentelemetry.traces.endpoint` (traces-specific endpoint)
 *
 * The actual environment property setup is handled by [OtelPropertiesListener] which runs
 * as an [org.springframework.boot.env.EnvironmentPostProcessor] before the application context is created.
 */
@AutoConfiguration
@EnableConfigurationProperties(F2OpenTelemetryProperties::class)
@ConditionalOnProperty(
    prefix = OtelPropertiesListener.F2_OTEL_PREFIX,
    name = ["endpoint", "metrics.endpoint", "traces.endpoint"],
    matchIfMissing = false
)
class F2OpenTelemetryAutoConfiguration
