package f2.spring.observability.opentelemetry

import io.opentelemetry.api.common.AttributeKey
import io.opentelemetry.api.common.Attributes
import io.opentelemetry.sdk.testing.junit5.OpenTelemetryExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * Integration tests for OpenTelemetry metric collection.
 */
class OtelMetricIntegrationTest {

    companion object {
        @RegisterExtension
        @JvmStatic
        val otelTesting: OpenTelemetryExtension = OpenTelemetryExtension.create()
    }

    @Test
    fun `should capture counter metrics`() {
        val meter = otelTesting.openTelemetry.getMeter("test-meter")

        val counter = meter.counterBuilder("test.counter")
            .setDescription("A test counter")
            .build()

        counter.add(5)
        counter.add(3)

        val metrics = otelTesting.metrics
        assertThat(metrics).isNotEmpty

        val counterMetric = metrics.find { it.name == "test.counter" }
        assertThat(counterMetric).isNotNull
        assertThat(counterMetric!!.description).isEqualTo("A test counter")
    }

    @Test
    fun `should capture histogram metrics`() {
        val meter = otelTesting.openTelemetry.getMeter("test-meter")

        val histogram = meter.histogramBuilder("test.histogram")
            .setDescription("A test histogram")
            .build()

        histogram.record(1.5)
        histogram.record(2.5)
        histogram.record(3.5)

        val metrics = otelTesting.metrics
        val histogramMetric = metrics.find { it.name == "test.histogram" }
        assertThat(histogramMetric).isNotNull
    }

    @Test
    fun `should capture metrics with attributes`() {
        val meter = otelTesting.openTelemetry.getMeter("test-meter")

        val counter = meter.counterBuilder("requests.count")
            .build()

        counter.add(1, Attributes.of(AttributeKey.stringKey("http.method"), "GET"))
        counter.add(2, Attributes.of(AttributeKey.stringKey("http.method"), "POST"))

        val metrics = otelTesting.metrics
        val requestsMetric = metrics.find { it.name == "requests.count" }
        assertThat(requestsMetric).isNotNull
    }

    @Test
    fun `should capture gauge metrics`() {
        val meter = otelTesting.openTelemetry.getMeter("test-meter")

        meter.gaugeBuilder("system.memory.usage")
            .setDescription("Current memory usage")
            .buildWithCallback { measurement ->
                measurement.record(1024.0, Attributes.of(AttributeKey.stringKey("type"), "heap"))
            }

        val metrics = otelTesting.metrics
        val gaugeMetric = metrics.find { it.name == "system.memory.usage" }
        assertThat(gaugeMetric).isNotNull
    }
}
