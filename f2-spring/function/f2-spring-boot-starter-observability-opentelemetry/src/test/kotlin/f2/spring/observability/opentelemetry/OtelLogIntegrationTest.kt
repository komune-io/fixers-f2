package f2.spring.observability.opentelemetry

import io.opentelemetry.api.common.AttributeKey
import io.opentelemetry.api.logs.Severity
import io.opentelemetry.sdk.testing.junit5.OpenTelemetryExtension
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.RegisterExtension

/**
 * Integration tests for OpenTelemetry log record collection.
 */
class OtelLogIntegrationTest {

    companion object {
        @RegisterExtension
        @JvmStatic
        val otelTesting: OpenTelemetryExtension = OpenTelemetryExtension.create()
    }

    @Test
    fun `should capture log records`() {
        val logger = otelTesting.openTelemetry.logsBridge.get("test-logger")

        logger.logRecordBuilder()
            .setBody("Test log message")
            .setSeverity(Severity.INFO)
            .emit()

        val logs = otelTesting.logRecords
        assertThat(logs).hasSize(1)
        assertThat(logs[0].bodyValue?.asString()).isEqualTo("Test log message")
        assertThat(logs[0].severity).isEqualTo(Severity.INFO)
    }

    @Test
    fun `should capture log records with attributes`() {
        val logger = otelTesting.openTelemetry.logsBridge.get("test-logger")

        logger.logRecordBuilder()
            .setBody("User login event")
            .setSeverity(Severity.INFO)
            .setAttribute(AttributeKey.stringKey("user.id"), "user-123")
            .setAttribute(AttributeKey.stringKey("event.type"), "login")
            .emit()

        val logs = otelTesting.logRecords
        assertThat(logs).hasSize(1)

        val attributes = logs[0].attributes
        assertThat(attributes.get(AttributeKey.stringKey("user.id"))).isEqualTo("user-123")
        assertThat(attributes.get(AttributeKey.stringKey("event.type"))).isEqualTo("login")
    }

    @Test
    fun `should capture log records with different severity levels`() {
        val logger = otelTesting.openTelemetry.logsBridge.get("test-logger")

        logger.logRecordBuilder()
            .setBody("Debug message")
            .setSeverity(Severity.DEBUG)
            .emit()

        logger.logRecordBuilder()
            .setBody("Warning message")
            .setSeverity(Severity.WARN)
            .emit()

        logger.logRecordBuilder()
            .setBody("Error message")
            .setSeverity(Severity.ERROR)
            .emit()

        val logs = otelTesting.logRecords
        assertThat(logs).hasSize(3)
        assertThat(logs.map { it.severity }).containsExactlyInAnyOrder(
            Severity.DEBUG,
            Severity.WARN,
            Severity.ERROR
        )
    }

    @Test
    fun `should correlate logs with spans`() {
        val tracer = otelTesting.openTelemetry.getTracer("test-tracer")
        val logger = otelTesting.openTelemetry.logsBridge.get("test-logger")

        val span = tracer.spanBuilder("operation-span").startSpan()
        span.makeCurrent().use {
            logger.logRecordBuilder()
                .setBody("Log within span context")
                .setSeverity(Severity.INFO)
                .emit()
        }
        span.end()

        val spans = otelTesting.spans
        val logs = otelTesting.logRecords

        assertThat(spans).hasSize(1)
        assertThat(logs).hasSize(1)

        // Verify log is correlated with span via trace context
        val logRecord = logs[0]
        val spanData = spans[0]
        assertThat(logRecord.spanContext.traceId).isEqualTo(spanData.traceId)
        assertThat(logRecord.spanContext.spanId).isEqualTo(spanData.spanId)
    }
}
