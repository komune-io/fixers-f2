package f2.spring.observability.opentelemetry

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner

class F2OpenTelemetryAutoConfigurationTest {

    private val contextRunner = ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(F2OpenTelemetryAutoConfiguration::class.java))

    @Test
    fun `should load when base endpoint is set`() {
        contextRunner
            .withPropertyValues("f2.observability.opentelemetry.endpoint=http://collector:4318")
            .run { context ->
                assertThat(context).hasSingleBean(F2OpenTelemetryAutoConfiguration::class.java)
                assertThat(context).hasSingleBean(F2OpenTelemetryProperties::class.java)
            }
    }

    @Test
    fun `should load when only metrics endpoint is set`() {
        contextRunner
            .withPropertyValues("f2.observability.opentelemetry.metrics.endpoint=http://collector:4318/v1/metrics")
            .run { context ->
                assertThat(context).hasSingleBean(F2OpenTelemetryAutoConfiguration::class.java)
                assertThat(context).hasSingleBean(F2OpenTelemetryProperties::class.java)
            }
    }

    @Test
    fun `should load when only traces endpoint is set`() {
        contextRunner
            .withPropertyValues("f2.observability.opentelemetry.traces.endpoint=http://collector:4318/v1/traces")
            .run { context ->
                assertThat(context).hasSingleBean(F2OpenTelemetryAutoConfiguration::class.java)
                assertThat(context).hasSingleBean(F2OpenTelemetryProperties::class.java)
            }
    }

    @Test
    fun `should load when only logs endpoint is set`() {
        contextRunner
            .withPropertyValues("f2.observability.opentelemetry.logs.endpoint=http://collector:4318/v1/logs")
            .run { context ->
                assertThat(context).hasSingleBean(F2OpenTelemetryAutoConfiguration::class.java)
                assertThat(context).hasSingleBean(F2OpenTelemetryProperties::class.java)
            }
    }

    @Test
    fun `should NOT load when no endpoints configured`() {
        contextRunner
            .run { context ->
                assertThat(context).doesNotHaveBean(F2OpenTelemetryAutoConfiguration::class.java)
                assertThat(context).doesNotHaveBean(F2OpenTelemetryProperties::class.java)
            }
    }

    @Test
    fun `should bind properties correctly`() {
        val baseEndpoint = "http://collector:4318"
        val metricsEndpoint = "http://metrics-collector:4318/v1/metrics"
        val tracesEndpoint = "http://traces-collector:4318/v1/traces"
        val logsEndpoint = "http://logs-collector:4318/v1/logs"

        contextRunner
            .withPropertyValues(
                "f2.observability.opentelemetry.endpoint=$baseEndpoint",
                "f2.observability.opentelemetry.metrics.endpoint=$metricsEndpoint",
                "f2.observability.opentelemetry.traces.endpoint=$tracesEndpoint",
                "f2.observability.opentelemetry.logs.endpoint=$logsEndpoint"
            )
            .run { context ->
                val properties = context.getBean(F2OpenTelemetryProperties::class.java)
                assertThat(properties.endpoint).isEqualTo(baseEndpoint)
                assertThat(properties.metrics.endpoint).isEqualTo(metricsEndpoint)
                assertThat(properties.traces.endpoint).isEqualTo(tracesEndpoint)
                assertThat(properties.logs.endpoint).isEqualTo(logsEndpoint)
            }
    }
}
