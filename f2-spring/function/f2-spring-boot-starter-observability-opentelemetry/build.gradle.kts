plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
    alias(catalogue.plugins.fixers.gradle.publish)
    alias(catalogue.plugins.kotlin.kapt)
}

dependencies {
    kapt(libs.spring.boot.configuration.processor)

    api(libs.spring.boot.starter.actuator)

    // Spring Boot 4.0 starter that brings OpenTelemetry SDK and auto-configuration
    api(libs.spring.boot.starter.opentelemetry)

    // FOR OtlpMetricsExportAutoConfiguration => management.otlp.metrics.export.url
    implementation(libs.micrometer.registry.otlp)

    testImplementation(libs.bundles.spring.test)

    // OpenTelemetry SDK testing utilities (InMemorySpanExporter, etc.)
    testImplementation(libs.opentelemetry.sdk.testing)
}
