
plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.mpp)
    alias(catalogue.plugins.fixers.gradle.publish)
    alias(catalogue.plugins.kotlin.serialization)
}

dependencies {
    commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))

    jvmTestImplementation(libs.bundles.test.junit)

}

tasks.withType<Test> {
    useJUnitPlatform()
}
