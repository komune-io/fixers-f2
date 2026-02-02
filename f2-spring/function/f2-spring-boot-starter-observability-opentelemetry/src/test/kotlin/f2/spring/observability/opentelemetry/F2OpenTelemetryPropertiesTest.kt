package f2.spring.observability.opentelemetry

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.boot.context.properties.bind.Binder
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource

class F2OpenTelemetryPropertiesTest {

    @Test
    fun `should bind base endpoint`() {
        val properties = mapOf(
            "f2.observability.opentelemetry.endpoint" to "http://collector:4318"
        )

        val bound = bind(properties)

        assertThat(bound.endpoint).isEqualTo("http://collector:4318")
        assertThat(bound.metrics.endpoint).isNull()
        assertThat(bound.traces.endpoint).isNull()
        assertThat(bound.logs.endpoint).isNull()
    }

    @Test
    fun `should bind explicit endpoints`() {
        val properties = mapOf(
            "f2.observability.opentelemetry.endpoint" to "http://base:4318",
            "f2.observability.opentelemetry.metrics.endpoint" to "http://metrics:4318/v1/metrics",
            "f2.observability.opentelemetry.traces.endpoint" to "http://traces:4318/v1/traces",
            "f2.observability.opentelemetry.logs.endpoint" to "http://logs:4318/v1/logs"
        )

        val bound = bind(properties)

        assertThat(bound.endpoint).isEqualTo("http://base:4318")
        assertThat(bound.metrics.endpoint).isEqualTo("http://metrics:4318/v1/metrics")
        assertThat(bound.traces.endpoint).isEqualTo("http://traces:4318/v1/traces")
        assertThat(bound.logs.endpoint).isEqualTo("http://logs:4318/v1/logs")
    }

    @Test
    fun `should have null when not configured`() {
        val properties = emptyMap<String, String>()

        val bound = bind(properties)

        assertThat(bound.endpoint).isNull()
        assertThat(bound.metrics.endpoint).isNull()
        assertThat(bound.traces.endpoint).isNull()
        assertThat(bound.logs.endpoint).isNull()
    }

    @Test
    fun `should bind logs endpoint`() {
        val properties = mapOf(
            "f2.observability.opentelemetry.logs.endpoint" to "http://logs-collector:4318/v1/logs"
        )

        val bound = bind(properties)

        assertThat(bound.endpoint).isNull()
        assertThat(bound.metrics.endpoint).isNull()
        assertThat(bound.traces.endpoint).isNull()
        assertThat(bound.logs.endpoint).isEqualTo("http://logs-collector:4318/v1/logs")
    }

    private fun bind(properties: Map<String, String>): F2OpenTelemetryProperties {
        val source = MapConfigurationPropertySource(properties)
        return Binder(source)
            .bind(OtelPropertiesListener.F2_OTEL_PREFIX, F2OpenTelemetryProperties::class.java)
            .orElseGet { F2OpenTelemetryProperties() }
    }
}
