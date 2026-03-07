
plugins {
    alias(libs.plugins.fixers.kotlin.mpp)
    alias(libs.plugins.fixers.publish)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))

    jvmTestImplementation(libs.bundles.test.junit)

}

tasks.withType<Test> {
    useJUnitPlatform()
}
