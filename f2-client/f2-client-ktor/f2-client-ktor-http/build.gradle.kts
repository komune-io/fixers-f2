import org.jetbrains.kotlin.gradle.targets.js.nodejs.NodeJsEnvSpec

plugins {
    alias(libs.plugins.fixers.gradle.kotlin.mpp)
    alias(libs.plugins.fixers.gradle.publish)
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    commonMainApi(project(":f2-client:f2-client-core"))
    commonMainApi(project(":f2-client:f2-client-domain"))
    commonMainApi(project(":f2-dsl:f2-dsl-cqrs"))
    commonMainApi(project(":f2-client:f2-client-ktor:f2-client-ktor-common"))

    commonMainApi(libs.kotlinx.coroutines.core)
    commonMainApi(libs.kotlinx.serialization.json)

    commonMainApi(libs.ktor.client.core)
    commonMainApi(libs.ktor.client.auth)

    commonMainApi(libs.bundles.ktor.client.features)
    jvmMainApi(libs.ktor.client.java)
    jsMainApi(libs.ktor.client.js)

    commonTestImplementation(kotlin("test"))

    jvmTestImplementation(libs.bundles.test.junit)
    jvmTestImplementation(libs.ktor.client.mock)
    jsTestImplementation(libs.ktor.client.mock)
    jsTestImplementation(libs.kotlinx.coroutines.test)

    jvmTestImplementation(project(":f2-spring:function:f2-spring-boot-starter-function-http-webflux"))

}

// The JS client as a front uses it, on what the compiler emitted: ts-check/use.mts type-checks the committed
// contract under tsc --strict, and ts-check/facade.mjs calls a local F2 server through the compiled module.
val node = the<NodeJsEnvSpec>().executable
val tsCheck = rootProject.layout.projectDirectory.dir("ts-check")
val checkTsContract = tasks.register<Exec>("checkTsContract") {
    val tsc = rootProject.layout.buildDirectory.file("js/node_modules/typescript/bin/tsc")
    dependsOn(":kotlinNpmInstall")
    mustRunAfter(":tsGen", ":checkTsGen")
    inputs.dir(rootProject.layout.projectDirectory.dir("ts"))
    inputs.dir(tsCheck)
    executable = node.get()
    args(tsc.get().asFile.absolutePath, "--project", tsCheck.asFile.absolutePath)
}
val checkJsFacade = tasks.register<Exec>("checkJsFacade") {
    val emitted = layout.buildDirectory.dir("dist/js/productionLibrary")
    dependsOn("jsBrowserProductionLibraryDistribution")
    inputs.dir(emitted)
    inputs.file(tsCheck.file("facade.mjs"))
    executable = node.get()
    args(tsCheck.file("facade.mjs").asFile.absolutePath, emitted.get().asFile.absolutePath)
}
tasks.named("check") { dependsOn(checkTsContract, checkJsFacade) }
