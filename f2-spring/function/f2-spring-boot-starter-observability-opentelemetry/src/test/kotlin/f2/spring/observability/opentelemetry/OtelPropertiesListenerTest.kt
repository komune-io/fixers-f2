package f2.spring.observability.opentelemetry

import io.opentelemetry.exporter.otlp.http.trace.OtlpHttpSpanExporter
import io.opentelemetry.sdk.trace.SdkTracerProvider
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.NoSuchBeanDefinitionException
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.ConfigurableApplicationContext

class OtelPropertiesListenerTest {

    companion object {
        const val SPRING_APPLICATION_NAME = "spring.application.name"
        const val SPRING_APPLICATION_NAME_VALUE = "myTestApp"
        const val SPRING_APPLICATION_NAME_PROPERTIES = "$SPRING_APPLICATION_NAME=$SPRING_APPLICATION_NAME_VALUE"
    }

    @SpringBootApplication
    open class App

    @Test
    fun springManagementContextShouldBeSet() {
        val context = SpringApplicationBuilder(App::class.java)
            .sources(OtelPropertiesListener::class.java)
            .properties(
                "f2.observability.opentelemetry.metrics.endpoint=http://test-tracing-endpoint:4318/v1/metrics",
                "f2.observability.opentelemetry.traces.endpoint=http://test-tracing-endpoint:4318/v1/traces",
                SPRING_APPLICATION_NAME_PROPERTIES
            ).run()

        context.assertThatPropertiesAreSet()
        context.assertThatBeanAreCreated()
    }

    @Test
    fun f2OtelEndpointShouldInitTracesAndMetrics() {
        val context = SpringApplicationBuilder(App::class.java)
            .sources(OtelPropertiesListener::class.java)
            .properties(
                "f2.observability.opentelemetry.endpoint=http://test-tracing-endpoint:4318",
                SPRING_APPLICATION_NAME_PROPERTIES
            )
            .run()
        context.assertThatPropertiesAreSet()
        context.assertThatBeanAreCreated()

    }

    private fun ConfigurableApplicationContext.assertThatPropertiesAreSet() {
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE))
            .isEqualTo("health, metrics")
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_ENDPOINT_METRICS_ENABLED))
            .isEqualTo("true")
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_TRACING_SAMPLING_PROBABILITY))
            .isEqualTo("1.0")
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_TRACING_ENDPOINT))
            .isEqualTo("http://test-tracing-endpoint:4318/v1/traces")
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_URL))
            .isEqualTo("http://test-tracing-endpoint:4318/v1/metrics")
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_METRICS_TAGS_APPLICATION))
            .isEqualTo("myTestApp")
    }
    private fun ConfigurableApplicationContext.assertThatBeanAreCreated() {
        assertThat(getBean(SdkTracerProvider::class.java)).isNotNull
        assertThat(getBean(OtlpHttpSpanExporter::class.java)).isNotNull
    }


    @Test
    fun f2OtelShouldNotBePutIfPropsAreEmpty() {
        val context = SpringApplicationBuilder(App::class.java)
            .sources(OtelPropertiesListener::class.java)
            .properties(
                SPRING_APPLICATION_NAME_PROPERTIES
            ).run()

        val environment = context.environment
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_ENDPOINTS_WEB_EXPOSURE_INCLUDE))
            .isNull()
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_ENDPOINT_METRICS_ENABLED))
            .isNull()
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_TRACING_SAMPLING_PROBABILITY))
            .isNull()
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_TRACING_ENDPOINT))
            .isNull()
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_OTLP_METRICS_EXPORT_URL))
            .isNull()
        assertThat(environment.getProperty(OtelPropertiesListener.MANAGEMENT_METRICS_TAGS_APPLICATION))
            .isNull()

        assertThatThrownBy {
            context.getBean(OtlpHttpSpanExporter::class.java)
        }.isInstanceOf(NoSuchBeanDefinitionException::class.java)

    }
}
