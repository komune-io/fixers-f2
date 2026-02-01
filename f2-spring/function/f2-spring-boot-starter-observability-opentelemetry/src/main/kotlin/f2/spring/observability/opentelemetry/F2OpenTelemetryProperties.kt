package f2.spring.observability.opentelemetry

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * Defines OpenTelemetry properties for F2 observability configuration.
 *
 * This class only binds user-provided properties. The actual endpoint path derivation
 * (appending `/v1/metrics` and `/v1/traces` per OTLP spec) is handled by [OtelPropertiesListener],
 * which is the single source of truth for translating F2 properties to Spring management properties.
 *
 * @see OtelPropertiesListener
 */
@ConfigurationProperties(prefix = OtelPropertiesListener.F2_OTEL_PREFIX)
class F2OpenTelemetryProperties(
    /**
     * Base endpoint for OpenTelemetry. When set, [OtelPropertiesListener] derives
     * metrics, traces, and logs endpoints by appending `/v1/metrics`, `/v1/traces`,
     * and `/v1/logs` respectively.
     */
    val endpoint: String? = null,
    /**
     * Properties related to OpenTelemetry metrics.
     */
    val metrics: F2OpenTelemetryMetricsProperties = F2OpenTelemetryMetricsProperties(),
    /**
     * Properties related to OpenTelemetry traces.
     */
    val traces: F2OpenTelemetryTracesProperties = F2OpenTelemetryTracesProperties(),
    /**
     * Properties related to OpenTelemetry logs.
     */
    val logs: F2OpenTelemetryLogsProperties = F2OpenTelemetryLogsProperties()
)

/**
 * Properties for OpenTelemetry metrics specific to F2.
 */
data class F2OpenTelemetryMetricsProperties(
    /**
     * Explicit metrics endpoint for OpenTelemetry.
     * When set, overrides the derived endpoint from the base endpoint.
     */
    val endpoint: String? = null
)

/**
 * Properties for OpenTelemetry traces specific to F2.
 */
data class F2OpenTelemetryTracesProperties(
    /**
     * Explicit traces endpoint for OpenTelemetry.
     * When set, overrides the derived endpoint from the base endpoint.
     */
    val endpoint: String? = null
)

/**
 * Properties for OpenTelemetry logs specific to F2.
 */
data class F2OpenTelemetryLogsProperties(
    /**
     * Explicit logs endpoint for OpenTelemetry.
     * When set, overrides the derived endpoint from the base endpoint.
     */
    val endpoint: String? = null
)
