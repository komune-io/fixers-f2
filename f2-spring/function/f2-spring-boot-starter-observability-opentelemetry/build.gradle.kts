plugins {
    id("io.komune.fixers.gradle.kotlin.jvm")
    id("io.komune.fixers.gradle.publish")
    kotlin("kapt")
}

dependencies {
    Dependencies.Jvm.Spring.configurationProcessor(::kapt)

    Dependencies.Jvm.Spring.actuator(::api)

    // Spring Boot 4.0 starter that brings OpenTelemetry SDK and auto-configuration
    Dependencies.Jvm.Spring.opentelemetry(::api)

    // FOR OtlpMetricsExportAutoConfiguration => management.otlp.metrics.export.url
    Dependencies.Jvm.Observability.micrometerRegistryOtlp(::implementation)

    Dependencies.Jvm.Test.springTest(::testImplementation)

    // OpenTelemetry SDK testing utilities (InMemorySpanExporter, etc.)
    testImplementation("io.opentelemetry:opentelemetry-sdk-testing:${Versions.Observability.opentelemetry}")
}
