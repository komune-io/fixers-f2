plugins {
    alias(libs.plugins.fixers.gradle.kotlin.jvm)
}

dependencies {
    api(libs.blockhound)
    api(libs.kotlinx.coroutines.debug)
    compileOnly(libs.junit.platform.launcher)

    testImplementation(libs.bundles.test.junit)
    testImplementation(libs.kotlinx.coroutines.reactor)
    testRuntimeOnly(libs.junit.platform.launcher)
}

tasks.withType<Test>().configureEach { jvmArgs("-XX:+AllowRedefinitionToAddDeleteMethods") }
