package f2.spring.observability.opentelemetry

import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.AnyNestedCondition
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Conditional
import org.springframework.context.annotation.ConfigurationCondition

/**
 * Auto-configuration for F2 OpenTelemetry integration.
 *
 * This configuration is activated when any OpenTelemetry endpoint is configured via:
 * - `f2.observability.opentelemetry.endpoint` (base endpoint for metrics, traces, and logs)
 * - `f2.observability.opentelemetry.metrics.endpoint` (metrics-specific endpoint)
 * - `f2.observability.opentelemetry.traces.endpoint` (traces-specific endpoint)
 * - `f2.observability.opentelemetry.logs.endpoint` (logs-specific endpoint)
 *
 * The actual environment property setup is handled by [OtelPropertiesListener] which runs
 * as an [org.springframework.boot.EnvironmentPostProcessor] before the application context is created.
 */
@AutoConfiguration
@EnableConfigurationProperties(F2OpenTelemetryProperties::class)
@Conditional(F2OpenTelemetryAutoConfiguration.OnAnyEndpointConfigured::class)
class F2OpenTelemetryAutoConfiguration {

    /**
     * Condition that matches when ANY of the F2 OpenTelemetry endpoints are configured.
     */
    class OnAnyEndpointConfigured : AnyNestedCondition(ConfigurationCondition.ConfigurationPhase.PARSE_CONFIGURATION) {

        @ConditionalOnProperty(
            prefix = OtelPropertiesListener.F2_OTEL_PREFIX,
            name = ["endpoint"]
        )
        class OnBaseEndpoint

        @ConditionalOnProperty(
            prefix = OtelPropertiesListener.F2_OTEL_PREFIX,
            name = ["metrics.endpoint"]
        )
        class OnMetricsEndpoint

        @ConditionalOnProperty(
            prefix = OtelPropertiesListener.F2_OTEL_PREFIX,
            name = ["traces.endpoint"]
        )
        class OnTracesEndpoint

        @ConditionalOnProperty(
            prefix = OtelPropertiesListener.F2_OTEL_PREFIX,
            name = ["logs.endpoint"]
        )
        class OnLogsEndpoint
    }
}
