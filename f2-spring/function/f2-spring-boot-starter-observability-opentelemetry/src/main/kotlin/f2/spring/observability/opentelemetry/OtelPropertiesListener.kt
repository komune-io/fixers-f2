package f2.spring.observability.opentelemetry

import java.util.Properties
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent
import org.springframework.context.ApplicationListener
import org.springframework.core.env.PropertiesPropertySource


class OtelPropertiesListener: ApplicationListener<ApplicationEnvironmentPreparedEvent> {

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
        const val MANAGEMENT_TRACING_SAMPLING_PROBABILITY = "management.tracing.sampling.probability"
        const val MANAGEMENT_OTLP_TRACING_ENDPOINT = "management.otlp.tracing.endpoint"
        const val MANAGEMENT_OTLP_METRICS_EXPORT_URL = "management.otlp.metrics.export.url"
        const val MANAGEMENT_METRICS_TAGS_APPLICATION = "management.metrics.tags.application"
    }

    override fun onApplicationEvent(event: ApplicationEnvironmentPreparedEvent) {
        val environment = event.environment

        val mainEndpoint = environment.getProperty(F2_OTEL_ENDPOINT)
        val metricsEndpoint = environment.getProperty(F2_OTEL_METRICS_ENDPOINT) ?:
            mainEndpoint?.takeIf { it.isNotBlank() }?.plus(F2_OTEL_METRICS_ENDPOINT_PATH)

        val tracingEndpoint = environment.getProperty(F2_OTEL_TRACES_ENDPOINT) ?:
            mainEndpoint?.takeIf { it.isNotBlank() }?.plus(F2_OTEL_TRACES_ENDPOINT_PATH)

        val application = environment.getProperty(SPRING_APPLICATION_NAME)

        val props = Properties()
        if(tracingEndpoint != null || metricsEndpoint != null) {
            props[MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE] = "health, metrics"
            props[MANAGEMENT_ENDPOINT_METRICS_ENABLED] = "true"
            props[MANAGEMENT_TRACING_SAMPLING_PROBABILITY] = "1.0"
            props[MANAGEMENT_METRICS_TAGS_APPLICATION] = application
        }
        if(tracingEndpoint != null) {
            props[MANAGEMENT_OTLP_TRACING_ENDPOINT] = tracingEndpoint
        }
        if(metricsEndpoint != null) {
            props[MANAGEMENT_OTLP_METRICS_EXPORT_URL] = metricsEndpoint
        }
        environment.propertySources.addFirst(PropertiesPropertySource(F2_OTEL_PROPS, props))
    }
}
