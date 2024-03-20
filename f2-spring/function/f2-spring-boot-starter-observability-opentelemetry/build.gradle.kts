plugins {
    id("io.komune.fixers.gradle.kotlin.jvm")
    id("io.komune.fixers.gradle.publish")
    kotlin("kapt")
}

dependencies {
    Dependencies.Jvm.Spring.configurationProcessor(::kapt)

    api("org.springframework.boot:spring-boot-starter-actuator:${Versions.Spring.boot}")

    api("io.micrometer:micrometer-tracing-bridge-otel:${Versions.Observability.micrometer}")

    // OtlpTracingConfigurations.Exporters.otlpHttpSpanExporter => management.otlp.tracing.endpoint
    implementation ("io.opentelemetry:opentelemetry-exporter-otlp:${Versions.Observability.opentelemetry}")

    // FOR OtlpMetricsExportAutoConfiguration => management.otlp.metrics.export.url
    implementation("io.micrometer:micrometer-registry-otlp:${Versions.Observability.micrometerOtlp}")

    Dependencies.Jvm.Test.springTest(::implementation)
}
