package f2.spring.observability.opentelemetry

import io.opentelemetry.api.OpenTelemetry
import io.opentelemetry.sdk.trace.SpanProcessor
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.beans.factory.getBean
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ConfigurableApplicationContext

class OtelPropertiesListenerTest {

    companion object {
        const val SPRING_APPLICATION_NAME = "spring.application.name"
        const val SPRING_APPLICATION_NAME_VALUE = "myTestApp"
        const val SPRING_APPLICATION_NAME_PROPERTIES = "$SPRING_APPLICATION_NAME=$SPRING_APPLICATION_NAME_VALUE"
        const val TEST_ENDPOINT = "http://test-tracing-endpoint:4318"
    }

    @SpringBootApplication
    open class App

    @Test
    fun `should configure metrics traces and logs when explicit endpoints are provided`() {
        val context = SpringApplicationBuilder(App::class.java)
            .properties(
                "f2.observability.opentelemetry.metrics.endpoint=$TEST_ENDPOINT/v1/metrics",
                "f2.observability.opentelemetry.traces.endpoint=$TEST_ENDPOINT/v1/traces",
                "f2.observability.opentelemetry.logs.endpoint=$TEST_ENDPOINT/v1/logs",
                SPRING_APPLICATION_NAME_PROPERTIES
            ).run()

        context.use {
            it.assertThatPropertiesAreSet()
            it.assertThatTracingBeansAreCreated()
        }
    }

    @Test
    fun `should derive all endpoints from base endpoint`() {
        val context = SpringApplicationBuilder(App::class.java)
            .properties(
                "f2.observability.opentelemetry.endpoint=$TEST_ENDPOINT",
                SPRING_APPLICATION_NAME_PROPERTIES
            )
            .run()

        context.use {
            it.assertThatPropertiesAreSet()
            it.assertThatTracingBeansAreCreated()
        }
    }

    @Test
    fun `should disable tracing and metrics when no endpoints are configured`() {
        val context = SpringApplicationBuilder(App::class.java)
            .properties(
                SPRING_APPLICATION_NAME_PROPERTIES
            ).run()

        context.use {
            val environment = it.environment
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE))
                .isNull()
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_ENDPOINT_METRICS_ENABLED))
                .isNull()
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_TRACING_ENABLED))
                .isEqualTo("false")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_TRACING_SAMPLING_PROBABILITY))
                .isNull()
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OPENTELEMETRY_TRACING_ENDPOINT))
                .isNull()
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_ENABLED))
                .isEqualTo("false")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_URL))
                .isNull()
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_METRICS_TAGS_APPLICATION))
                .isNull()

            // Verify OpenTelemetry SDK exporters are disabled to prevent localhost:4317/4318 connection attempts
            assertThat(environment.getProperty(OtelPropertiesListener.OTEL_TRACES_EXPORTER))
                .isEqualTo("none")
            assertThat(environment.getProperty(OtelPropertiesListener.OTEL_METRICS_EXPORTER))
                .isEqualTo("none")
            assertThat(environment.getProperty(OtelPropertiesListener.OTEL_LOGS_EXPORTER))
                .isEqualTo("none")

            assertThatThrownBy {
                it.getBean<SpanProcessor>()
            }.isInstanceOf(NoSuchBeanDefinitionException::class.java)
        }
    }

    @Test
    fun `should configure only tracing when only traces endpoint is provided`() {
        val context = SpringApplicationBuilder(App::class.java)
            .properties(
                "f2.observability.opentelemetry.traces.endpoint=$TEST_ENDPOINT/v1/traces",
                SPRING_APPLICATION_NAME_PROPERTIES
            ).run()

        context.use {
            val environment = it.environment
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_TRACING_ENABLED))
                .isEqualTo("true")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OPENTELEMETRY_TRACING_ENDPOINT))
                .isEqualTo("$TEST_ENDPOINT/v1/traces")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_ENABLED))
                .isEqualTo("false")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_URL))
                .isNull()
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_LOGGING_EXPORT_OTLP_ENABLED))
                .isEqualTo("false")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OPENTELEMETRY_LOGGING_ENDPOINT))
                .isNull()
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE))
                .isEqualTo("health, metrics")
            // Verify metrics and logs exporters are disabled but traces exporter is not
            assertThat(environment.getProperty(OtelPropertiesListener.OTEL_TRACES_EXPORTER))
                .isNull()
            assertThat(environment.getProperty(OtelPropertiesListener.OTEL_METRICS_EXPORTER))
                .isEqualTo("none")
            assertThat(environment.getProperty(OtelPropertiesListener.OTEL_LOGS_EXPORTER))
                .isEqualTo("none")
        }
    }

    @Test
    fun `should configure only metrics when only metrics endpoint is provided`() {
        val context = SpringApplicationBuilder(App::class.java)
            .properties(
                "f2.observability.opentelemetry.metrics.endpoint=$TEST_ENDPOINT/v1/metrics",
                SPRING_APPLICATION_NAME_PROPERTIES
            ).run()

        context.use {
            val environment = it.environment
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_TRACING_ENABLED))
                .isEqualTo("false")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OPENTELEMETRY_TRACING_ENDPOINT))
                .isNull()
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_ENABLED))
                .isEqualTo("true")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_URL))
                .isEqualTo("$TEST_ENDPOINT/v1/metrics")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_LOGGING_EXPORT_OTLP_ENABLED))
                .isEqualTo("false")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OPENTELEMETRY_LOGGING_ENDPOINT))
                .isNull()
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE))
                .isEqualTo("health, metrics")
            // Verify traces and logs exporters are disabled but metrics exporter is not
            assertThat(environment.getProperty(OtelPropertiesListener.OTEL_TRACES_EXPORTER))
                .isEqualTo("none")
            assertThat(environment.getProperty(OtelPropertiesListener.OTEL_METRICS_EXPORTER))
                .isNull()
            assertThat(environment.getProperty(OtelPropertiesListener.OTEL_LOGS_EXPORTER))
                .isEqualTo("none")
        }
    }

    @Test
    fun `should configure only logs when only logs endpoint is provided`() {
        val context = SpringApplicationBuilder(App::class.java)
            .properties(
                "f2.observability.opentelemetry.logs.endpoint=$TEST_ENDPOINT/v1/logs",
                SPRING_APPLICATION_NAME_PROPERTIES
            ).run()

        context.use {
            val environment = it.environment
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_TRACING_ENABLED))
                .isEqualTo("false")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OPENTELEMETRY_TRACING_ENDPOINT))
                .isNull()
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_ENABLED))
                .isEqualTo("false")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_URL))
                .isNull()
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_LOGGING_EXPORT_OTLP_ENABLED))
                .isEqualTo("true")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OPENTELEMETRY_LOGGING_ENDPOINT))
                .isEqualTo("$TEST_ENDPOINT/v1/logs")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE))
                .isEqualTo("health, metrics")
            // Verify traces and metrics exporters are disabled but logs exporter is not
            assertThat(environment.getProperty(OtelPropertiesListener.OTEL_TRACES_EXPORTER))
                .isEqualTo("none")
            assertThat(environment.getProperty(OtelPropertiesListener.OTEL_METRICS_EXPORTER))
                .isEqualTo("none")
            assertThat(environment.getProperty(OtelPropertiesListener.OTEL_LOGS_EXPORTER))
                .isNull()
        }
    }

    @Test
    fun `should ignore blank endpoints`() {
        val context = SpringApplicationBuilder(App::class.java)
            .properties(
                "f2.observability.opentelemetry.endpoint=   ",
                "f2.observability.opentelemetry.traces.endpoint=",
                "f2.observability.opentelemetry.metrics.endpoint=  ",
                "f2.observability.opentelemetry.logs.endpoint=",
                SPRING_APPLICATION_NAME_PROPERTIES
            ).run()

        context.use {
            val environment = it.environment
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_TRACING_ENABLED))
                .isEqualTo("false")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_ENABLED))
                .isEqualTo("false")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_LOGGING_EXPORT_OTLP_ENABLED))
                .isEqualTo("false")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OPENTELEMETRY_TRACING_ENDPOINT))
                .isNull()
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_URL))
                .isNull()
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OPENTELEMETRY_LOGGING_ENDPOINT))
                .isNull()
            // Verify OpenTelemetry SDK exporters are disabled when blank endpoints are treated as unset
            assertThat(environment.getProperty(OtelPropertiesListener.OTEL_TRACES_EXPORTER))
                .isEqualTo("none")
            assertThat(environment.getProperty(OtelPropertiesListener.OTEL_METRICS_EXPORTER))
                .isEqualTo("none")
            assertThat(environment.getProperty(OtelPropertiesListener.OTEL_LOGS_EXPORTER))
                .isEqualTo("none")
        }
    }

    @Test
    fun `should override base endpoint with explicit endpoints`() {
        val context = SpringApplicationBuilder(App::class.java)
            .properties(
                "f2.observability.opentelemetry.endpoint=http://base-endpoint:4318",
                "f2.observability.opentelemetry.traces.endpoint=http://custom-traces:4318/v1/traces",
                "f2.observability.opentelemetry.metrics.endpoint=http://custom-metrics:4318/v1/metrics",
                "f2.observability.opentelemetry.logs.endpoint=http://custom-logs:4318/v1/logs",
                SPRING_APPLICATION_NAME_PROPERTIES
            ).run()

        context.use {
            val environment = it.environment
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OPENTELEMETRY_TRACING_ENDPOINT))
                .isEqualTo("http://custom-traces:4318/v1/traces")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_URL))
                .isEqualTo("http://custom-metrics:4318/v1/metrics")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OPENTELEMETRY_LOGGING_ENDPOINT))
                .isEqualTo("http://custom-logs:4318/v1/logs")
        }
    }

    @Test
    fun `should handle missing application name gracefully`() {
        val context = SpringApplicationBuilder(App::class.java)
            .properties(
                "f2.observability.opentelemetry.endpoint=$TEST_ENDPOINT"
                // No spring.application.name
            ).run()

        context.use {
            val environment = it.environment
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_METRICS_TAGS_APPLICATION))
                .isNull()
            // Other properties should still be set
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_TRACING_ENABLED))
                .isEqualTo("true")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_ENABLED))
                .isEqualTo("true")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_LOGGING_EXPORT_OTLP_ENABLED))
                .isEqualTo("true")
        }
    }

    @Test
    fun `should handle trailing slash in endpoint`() {
        val context = SpringApplicationBuilder(App::class.java)
            .properties(
                "f2.observability.opentelemetry.endpoint=$TEST_ENDPOINT/",
                SPRING_APPLICATION_NAME_PROPERTIES
            ).run()

        context.use {
            val environment = it.environment
            // Documents current behavior: trailing slash is NOT stripped,
            // resulting in double slash (e.g., http://host:4318//v1/traces)
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OPENTELEMETRY_TRACING_ENDPOINT))
                .isEqualTo("$TEST_ENDPOINT//v1/traces")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_URL))
                .isEqualTo("$TEST_ENDPOINT//v1/metrics")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OPENTELEMETRY_LOGGING_ENDPOINT))
                .isEqualTo("$TEST_ENDPOINT//v1/logs")
        }
    }

    @Test
    fun `should set autoconfigure exclude when tracing disabled`() {
        val context = SpringApplicationBuilder(App::class.java)
            .properties(
                SPRING_APPLICATION_NAME_PROPERTIES
                // No endpoints configured
            ).run()

        context.use {
            val environment = it.environment
            assertThat(environment.getProperty(OtelPropertiesListener.SPRING_AUTOCONFIGURE_EXCLUDE))
                .isEqualTo(OtelPropertiesListener.OPENTELEMETRY_TRACING_AUTOCONFIG)
        }
    }

    @Test
    fun `should handle endpoint with existing path`() {
        val endpointWithPath = "http://proxy:8080/otel"
        val context = SpringApplicationBuilder(App::class.java)
            .properties(
                "f2.observability.opentelemetry.endpoint=$endpointWithPath",
                SPRING_APPLICATION_NAME_PROPERTIES
            ).run()

        context.use {
            val environment = it.environment
            // Documents current behavior: path is simply appended
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OPENTELEMETRY_TRACING_ENDPOINT))
                .isEqualTo("$endpointWithPath/v1/traces")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_URL))
                .isEqualTo("$endpointWithPath/v1/metrics")
            assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OPENTELEMETRY_LOGGING_ENDPOINT))
                .isEqualTo("$endpointWithPath/v1/logs")
        }
    }

    private fun ConfigurableApplicationContext.assertThatPropertiesAreSet() {
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE))
            .isEqualTo("health, metrics")
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_ENDPOINT_METRICS_ENABLED))
            .isEqualTo("true")
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_TRACING_ENABLED))
            .isEqualTo("true")
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_TRACING_SAMPLING_PROBABILITY))
            .isEqualTo("1.0")
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OPENTELEMETRY_TRACING_ENDPOINT))
            .isEqualTo("$TEST_ENDPOINT/v1/traces")
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_ENABLED))
            .isEqualTo("true")
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_URL))
            .isEqualTo("$TEST_ENDPOINT/v1/metrics")
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_LOGGING_EXPORT_OTLP_ENABLED))
            .isEqualTo("true")
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OPENTELEMETRY_LOGGING_ENDPOINT))
            .isEqualTo("$TEST_ENDPOINT/v1/logs")
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_METRICS_TAGS_APPLICATION))
            .isEqualTo("myTestApp")
    }

    private fun ConfigurableApplicationContext.assertThatTracingBeansAreCreated() {
        assertThat(getBean(OpenTelemetry::class.java)).isNotNull
        assertThat(getBean(SpanProcessor::class.java)).isNotNull
    }
}
