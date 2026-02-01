package f2.spring.observability.opentelemetry

import java.util.Properties
import org.slf4j.LoggerFactory
import org.springframework.boot.EnvironmentPostProcessor
import org.springframework.boot.SpringApplication
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.PropertiesPropertySource

/**
 * Environment post-processor that configures Spring Boot OpenTelemetry properties
 * based on F2 OpenTelemetry configuration.
 *
 * This processor runs early in the application lifecycle (before the application context is created)
 * to set up the necessary management properties for OpenTelemetry tracing and metrics export.
 *
 * Configuration properties:
 * - `f2.observability.opentelemetry.endpoint`: Base endpoint (derives metrics, traces, and logs endpoints)
 * - `f2.observability.opentelemetry.metrics.endpoint`: Explicit metrics endpoint (overrides base)
 * - `f2.observability.opentelemetry.traces.endpoint`: Explicit traces endpoint (overrides base)
 * - `f2.observability.opentelemetry.logs.endpoint`: Explicit logs endpoint (overrides base)
 *
 * When endpoints are configured, this processor sets up:
 * - `management.opentelemetry.tracing.export.otlp.endpoint`: OTLP traces endpoint
 * - `management.otlp.metrics.export.url`: OTLP metrics endpoint
 * - `management.opentelemetry.logging.export.otlp.endpoint`: OTLP logs endpoint
 * - `management.logging.export.otlp.enabled`: Enables/disables OTLP logs export
 * - `management.tracing.enabled`: Enables/disables tracing
 * - `management.tracing.sampling.probability`: Sets to 1.0 (100% sampling)
 * - Various actuator exposure settings
 */
class OtelPropertiesListener : EnvironmentPostProcessor {

    private val logger = LoggerFactory.getLogger(OtelPropertiesListener::class.java)

    companion object {
        const val F2_OTEL_PROPS = "f2OtelProps"
        const val F2_OTEL_METRICS_ENDPOINT_PATH = "/v1/metrics"
        const val F2_OTEL_TRACES_ENDPOINT_PATH = "/v1/traces"
        const val F2_OTEL_LOGS_ENDPOINT_PATH = "/v1/logs"

        const val F2_OTEL_PREFIX = "f2.observability.opentelemetry"
        const val F2_OTEL_ENDPOINT = "$F2_OTEL_PREFIX.endpoint"
        const val F2_OTEL_METRICS_ENDPOINT = "$F2_OTEL_PREFIX.metrics.endpoint"
        const val F2_OTEL_TRACES_ENDPOINT = "$F2_OTEL_PREFIX.traces.endpoint"
        const val F2_OTEL_LOGS_ENDPOINT = "$F2_OTEL_PREFIX.logs.endpoint"
        const val SPRING_APPLICATION_NAME = "spring.application.name"

        const val MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE = "management.endpoints.web.exposure.include"
        const val MANAGEMENT_ENDPOINT_METRICS_ENABLED = "management.endpoint.metrics.enabled"
        const val MANAGEMENT_TRACING_ENABLED = "management.tracing.enabled"
        const val MANAGEMENT_TRACING_SAMPLING_PROBABILITY = "management.tracing.sampling.probability"
        const val MANAGEMENT_OPENTELEMETRY_TRACING_ENDPOINT = "management.opentelemetry.tracing.export.otlp.endpoint"
        const val MANAGEMENT_OTLP_METRICS_EXPORT_URL = "management.otlp.metrics.export.url"
        const val MANAGEMENT_OTLP_METRICS_EXPORT_ENABLED = "management.otlp.metrics.export.enabled"
        const val MANAGEMENT_METRICS_TAGS_APPLICATION = "management.metrics.tags.application"
        const val MANAGEMENT_OPENTELEMETRY_LOGGING_ENDPOINT = "management.opentelemetry.logging.export.otlp.endpoint"
        const val MANAGEMENT_LOGGING_EXPORT_OTLP_ENABLED = "management.logging.export.otlp.enabled"
        const val SPRING_AUTOCONFIGURE_EXCLUDE = "spring.autoconfigure.exclude"

        // OpenTelemetry SDK properties to disable exporters when no endpoint is configured
        const val OTEL_SDK_DISABLED = "otel.sdk.disabled"
        const val OTEL_TRACES_EXPORTER = "otel.traces.exporter"
        const val OTEL_METRICS_EXPORTER = "otel.metrics.exporter"
        const val OTEL_LOGS_EXPORTER = "otel.logs.exporter"
        const val OTEL_EXPORTER_NONE = "none"

        @Suppress("MaxLineLength")
        const val OPENTELEMETRY_TRACING_AUTOCONFIG =
            "org.springframework.boot.micrometer.tracing.opentelemetry.autoconfigure.OpenTelemetryTracingAutoConfiguration"
    }

    override fun postProcessEnvironment(environment: ConfigurableEnvironment, application: SpringApplication) {
        val mainEndpoint = environment.getProperty(F2_OTEL_ENDPOINT)?.takeIf { it.isNotBlank() }
        val metricsEndpoint = environment.getProperty(F2_OTEL_METRICS_ENDPOINT)?.takeIf { it.isNotBlank() }
            ?: mainEndpoint?.plus(F2_OTEL_METRICS_ENDPOINT_PATH)
        val tracingEndpoint = environment.getProperty(F2_OTEL_TRACES_ENDPOINT)?.takeIf { it.isNotBlank() }
            ?: mainEndpoint?.plus(F2_OTEL_TRACES_ENDPOINT_PATH)
        val logsEndpoint = environment.getProperty(F2_OTEL_LOGS_ENDPOINT)?.takeIf { it.isNotBlank() }
            ?: mainEndpoint?.plus(F2_OTEL_LOGS_ENDPOINT_PATH)
        val applicationName = environment.getProperty(SPRING_APPLICATION_NAME)

        val props = Properties()

        configureCommonProperties(props, tracingEndpoint, metricsEndpoint, logsEndpoint, applicationName)
        configureTracingProperties(props, tracingEndpoint)
        configureMetricsProperties(props, metricsEndpoint)
        configureLogsProperties(props, logsEndpoint)

        environment.propertySources.addFirst(PropertiesPropertySource(F2_OTEL_PROPS, props))

        logConfiguration(tracingEndpoint, metricsEndpoint, logsEndpoint)
    }

    private fun configureCommonProperties(
        props: Properties,
        tracingEndpoint: String?,
        metricsEndpoint: String?,
        logsEndpoint: String?,
        applicationName: String?
    ) {
        if (tracingEndpoint != null || metricsEndpoint != null || logsEndpoint != null) {
            props[MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE] = "health, metrics"
            props[MANAGEMENT_ENDPOINT_METRICS_ENABLED] = "true"
            props[MANAGEMENT_TRACING_SAMPLING_PROBABILITY] = "1.0"
            if (applicationName != null) {
                props[MANAGEMENT_METRICS_TAGS_APPLICATION] = applicationName
            }
        }
    }

    private fun configureTracingProperties(props: Properties, tracingEndpoint: String?) {
        if (tracingEndpoint != null) {
            props[MANAGEMENT_TRACING_ENABLED] = "true"
            props[MANAGEMENT_OPENTELEMETRY_TRACING_ENDPOINT] = tracingEndpoint
        } else {
            props[MANAGEMENT_TRACING_ENABLED] = "false"
            // Disable OpenTelemetry SDK traces exporter to prevent connection attempts to localhost:4317
            props[OTEL_TRACES_EXPORTER] = OTEL_EXPORTER_NONE
            // Exclude OpenTelemetry tracing auto-configuration since it doesn't check management.tracing.enabled
            props[SPRING_AUTOCONFIGURE_EXCLUDE] = OPENTELEMETRY_TRACING_AUTOCONFIG
        }
    }

    private fun configureMetricsProperties(props: Properties, metricsEndpoint: String?) {
        if (metricsEndpoint != null) {
            props[MANAGEMENT_OTLP_METRICS_EXPORT_ENABLED] = "true"
            props[MANAGEMENT_OTLP_METRICS_EXPORT_URL] = metricsEndpoint
        } else {
            props[MANAGEMENT_OTLP_METRICS_EXPORT_ENABLED] = "false"
            // Disable OpenTelemetry SDK metrics exporter to prevent connection attempts to localhost:4318
            props[OTEL_METRICS_EXPORTER] = OTEL_EXPORTER_NONE
        }
    }

    private fun configureLogsProperties(props: Properties, logsEndpoint: String?) {
        if (logsEndpoint != null) {
            props[MANAGEMENT_LOGGING_EXPORT_OTLP_ENABLED] = "true"
            props[MANAGEMENT_OPENTELEMETRY_LOGGING_ENDPOINT] = logsEndpoint
        } else {
            props[MANAGEMENT_LOGGING_EXPORT_OTLP_ENABLED] = "false"
            // Disable OpenTelemetry SDK logs exporter to prevent connection attempts to localhost:4318
            props[OTEL_LOGS_EXPORTER] = OTEL_EXPORTER_NONE
        }
    }

    private fun logConfiguration(tracingEndpoint: String?, metricsEndpoint: String?, logsEndpoint: String?) {
        val enabled = listOfNotNull(
            tracingEndpoint?.let { "tracing: $it" },
            metricsEndpoint?.let { "metrics: $it" },
            logsEndpoint?.let { "logs: $it" }
        )

        if (enabled.isNotEmpty()) {
            logger.info("F2 OpenTelemetry configured - {}", enabled.joinToString(", "))
        } else {
            logger.debug("F2 OpenTelemetry not configured - no endpoints specified")
        }
    }
}
