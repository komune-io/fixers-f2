package f2.spring.observability.opentelemetry

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Defines OpenTelemetry properties specific to a feature or module "F2".
 * If the endpoint is set, it automatically configures metrics.endpoint to `${endpoint}/metrics`
 * and traces.endpoint to `${endpoint}/trace`.
 */
@ConfigurationProperties(prefix = OtelPropertiesListener.F2_OTEL_PREFIX)
class F2OpenTelemetryProperties (
    /**
     * Optional String to specify the endpoint for OpenTelemetry. Null if not specified.
     */
    val endpoint: String?
) {
    /**
     * Properties related to OpenTelemetry metrics.
     */
    val metrics: F2OpenTelemetryMetricsProperties = F2OpenTelemetryMetricsProperties(endpoint?.let { "$it/metrics" })

    /**
     * Properties related to OpenTelemetry traces.
     */
    val traces: F2OpenTelemetryTracesProperties = F2OpenTelemetryTracesProperties(endpoint?.let { "$it/trace" })
}

/**
 * Properties for OpenTelemetry metrics specific to "F2".
 */
data class F2OpenTelemetryMetricsProperties(
    /**
     * Specifies the metrics endpoint for OpenTelemetry.
     * Defaults to `${parentEndpoint}/metrics` if parent endpoint is set.
     */
    val endpoint: String?
)

/**
 * Properties for OpenTelemetry traces specific to "F2".
 */
data class F2OpenTelemetryTracesProperties(
    /**
     * Specifies the traces endpoint for OpenTelemetry.
     * Defaults to `${parentEndpoint}/trace` if parent endpoint is set.
     */
    val endpoint: String?
)
