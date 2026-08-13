plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
    alias(catalogue.plugins.kotlin.spring)
}

dependencies {

    api(project(":f2-dsl:f2-dsl-cqrs"))
    api(project(":f2-dsl:f2-dsl-event"))

    implementation(libs.kotlinx.coroutines.core)
    // Spring's CoroutinesUtils needs MonoKt at runtime to invoke the suspending @EventListener
    // methods, and Spring Data uses the same bridge to back the CoroutineCrudRepository with the
    // host's reactive infrastructure; brings kotlinx-coroutines-reactive transitively.
    implementation(libs.kotlinx.coroutines.reactor)

    implementation(libs.spring.boot.starter.webflux)
    implementation(libs.spring.boot.autoconfigure)

    implementation(libs.bundles.spring.data.commons)
    implementation(libs.jackson.module.kotlin)

    testImplementation(libs.bundles.spring.test)
    testImplementation(libs.spring.tx)
}
