plugins {
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm)
    alias(catalogue.plugins.fixers.gradle.publish)
    alias(catalogue.plugins.kotlin.serialization)
}

dependencies {
    compileOnly(libs.bundles.spring.cloud.function.dep)
    implementation(libs.slf4j.api)

    api(project(":f2-dsl:f2-dsl-cqrs"))
    api(project(":f2-dsl:f2-dsl-function"))

    api(libs.bundles.coroutines)
    api(libs.bundles.spring.cloud.function)

    testImplementation(project(":f2-bdd:f2-bdd-spring-lambda"))
}
