plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.mpp)
    alias(catalogue.plugins.fixers.gradle.publish)
    alias(catalogue.plugins.kotlin.serialization)
}

dependencies {
    commonMainApi(libs.kotlinx.coroutines.core)
    commonMainApi(libs.kotlinx.serialization.core)

    jvmMainImplementation(libs.bundles.spring.data.commons)

    commonTestImplementation(kotlin("test"))
    commonTestImplementation(libs.kotlinx.coroutines.test)

    jvmTestImplementation(libs.bundles.test.junit)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
