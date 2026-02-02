package f2.spring.observability.opentelemetry

import io.opentelemetry.api.common.AttributeKey
import io.opentelemetry.sdk.testing.junit5.OpenTelemetryExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * Integration tests for OpenTelemetry span creation.
 */
class OtelSpanIntegrationTest {

    companion object {
        @RegisterExtension
        @JvmStatic
        val otelTesting: OpenTelemetryExtension = OpenTelemetryExtension.create()
    }

    @Test
    fun `should create spans`() {
        val tracer = otelTesting.openTelemetry.getTracer("test-tracer")

        val span = tracer.spanBuilder("test-span").startSpan()
        span.end()

        val spans = otelTesting.spans
        assertThat(spans).hasSize(1)
        assertThat(spans[0].name).isEqualTo("test-span")
    }

    @Test
    fun `should capture nested spans with parent-child relationship`() {
        val tracer = otelTesting.openTelemetry.getTracer("test-tracer")

        val parentSpan = tracer.spanBuilder("parent-span").startSpan()
        parentSpan.makeCurrent().use {
            val childSpan = tracer.spanBuilder("child-span").startSpan()
            childSpan.end()
        }
        parentSpan.end()

        val spans = otelTesting.spans
        assertThat(spans).hasSize(2)
        assertThat(spans.map { it.name }).containsExactlyInAnyOrder("parent-span", "child-span")

        val childSpan = spans.find { it.name == "child-span" }!!
        val parentSpanData = spans.find { it.name == "parent-span" }!!
        assertThat(childSpan.parentSpanId).isEqualTo(parentSpanData.spanId)
    }

    @Test
    fun `should capture span attributes`() {
        val tracer = otelTesting.openTelemetry.getTracer("test-tracer")

        val span = tracer.spanBuilder("attributed-span")
            .setAttribute("string.attr", "value")
            .setAttribute("long.attr", 42L)
            .setAttribute("boolean.attr", true)
            .startSpan()
        span.end()

        val spans = otelTesting.spans
        assertThat(spans).hasSize(1)

        val attributes = spans[0].attributes
        assertThat(attributes.get(AttributeKey.stringKey("string.attr"))).isEqualTo("value")
        assertThat(attributes.get(AttributeKey.longKey("long.attr"))).isEqualTo(42L)
        assertThat(attributes.get(AttributeKey.booleanKey("boolean.attr"))).isTrue()
    }
}
