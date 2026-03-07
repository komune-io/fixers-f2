plugins {
    alias(libs.plugins.fixers.kotlin.jvm)
    alias(libs.plugins.fixers.publish)
    alias(libs.plugins.kotlin.serialization)
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
