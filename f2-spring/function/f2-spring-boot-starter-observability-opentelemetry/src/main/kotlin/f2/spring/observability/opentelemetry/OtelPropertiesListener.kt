package f2.spring.observability.opentelemetry

import java.util.Properties
import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.env.EnvironmentPostProcessor
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
 * - `f2.observability.opentelemetry.endpoint`: Base endpoint (derives both metrics and traces endpoints)
 * - `f2.observability.opentelemetry.metrics.endpoint`: Explicit metrics endpoint (overrides base)
 * - `f2.observability.opentelemetry.traces.endpoint`: Explicit traces endpoint (overrides base)
 *
 * When endpoints are configured, this processor sets up:
 * - `management.opentelemetry.tracing.export.otlp.endpoint`: OTLP traces endpoint
 * - `management.otlp.metrics.export.url`: OTLP metrics endpoint
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

        const val F2_OTEL_PREFIX = "f2.observability.opentelemetry"
        const val F2_OTEL_ENDPOINT = "$F2_OTEL_PREFIX.endpoint"
        const val F2_OTEL_METRICS_ENDPOINT = "$F2_OTEL_PREFIX.metrics.endpoint"
        const val F2_OTEL_TRACES_ENDPOINT = "$F2_OTEL_PREFIX.traces.endpoint"
        const val SPRING_APPLICATION_NAME = "spring.application.name"

        const val MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE = "management.endpoints.web.exposure.include"
        const val MANAGEMENT_ENDPOINT_METRICS_ENABLED = "management.endpoint.metrics.enabled"
        const val MANAGEMENT_TRACING_ENABLED = "management.tracing.enabled"
        const val MANAGEMENT_TRACING_SAMPLING_PROBABILITY = "management.tracing.sampling.probability"
        const val MANAGEMENT_OPENTELEMETRY_TRACING_ENDPOINT = "management.opentelemetry.tracing.export.otlp.endpoint"
        const val MANAGEMENT_OTLP_METRICS_EXPORT_URL = "management.otlp.metrics.export.url"
        const val MANAGEMENT_OTLP_METRICS_EXPORT_ENABLED = "management.otlp.metrics.export.enabled"
        const val MANAGEMENT_METRICS_TAGS_APPLICATION = "management.metrics.tags.application"
        const val SPRING_AUTOCONFIGURE_EXCLUDE = "spring.autoconfigure.exclude"

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
        val applicationName = environment.getProperty(SPRING_APPLICATION_NAME)

        val props = Properties()

        configureCommonProperties(props, tracingEndpoint, metricsEndpoint, applicationName)
        configureTracingProperties(props, tracingEndpoint)
        configureMetricsProperties(props, metricsEndpoint)

        environment.propertySources.addFirst(PropertiesPropertySource(F2_OTEL_PROPS, props))

        logConfiguration(tracingEndpoint, metricsEndpoint)
    }

    private fun configureCommonProperties(
        props: Properties,
        tracingEndpoint: String?,
        metricsEndpoint: String?,
        applicationName: String?
    ) {
        if (tracingEndpoint != null || metricsEndpoint != null) {
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
        }
    }

    private fun logConfiguration(tracingEndpoint: String?, metricsEndpoint: String?) {
        when {
            tracingEndpoint != null && metricsEndpoint != null -> {
                logger.info("F2 OpenTelemetry configured - tracing: {}, metrics: {}", tracingEndpoint, metricsEndpoint)
            }
            tracingEndpoint != null -> {
                logger.info("F2 OpenTelemetry configured - tracing: {} (metrics disabled)", tracingEndpoint)
            }
            metricsEndpoint != null -> {
                logger.info("F2 OpenTelemetry configured - metrics: {} (tracing disabled)", metricsEndpoint)
            }
            else -> {
                logger.debug("F2 OpenTelemetry not configured - no endpoints specified")
            }
        }
    }
}
