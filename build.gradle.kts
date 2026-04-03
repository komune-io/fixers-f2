plugins {
    alias(catalogue.plugins.kotlin.kapt) apply false
    alias(catalogue.plugins.kotlin.spring) apply false
    alias(catalogue.plugins.spring.boot) apply false
    alias(catalogue.plugins.kotlin.serialization)

    alias(catalogue.plugins.fixers.gradle.config)
    alias(catalogue.plugins.fixers.gradle.check)
    alias(catalogue.plugins.fixers.gradle.publish)
    alias(catalogue.plugins.fixers.gradle.kotlin.jvm) apply false
    alias(catalogue.plugins.fixers.gradle.kotlin.mpp) apply false
}

subprojects {
    configurations.matching { it.name == "kapt" }.configureEach {
        val bomDep = project.dependencies.platform(project(":f2-gradle:f2-gradle-bom"))
        project.dependencies.add(name, bomDep)
    }
    pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
        dependencies {
            "api"(platform(project(":f2-gradle:f2-gradle-bom")))
        }
    }
    pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
        dependencies {
            "commonMainApi"(platform(project(":f2-gradle:f2-gradle-bom")))
        }
    }
}

fixers {
    bundle {
        id = "f2"
        group = "io.komune.f2"
        name = "F2"
        description = "Wrapper around Spring Cloud Function"
        url = "https://github.com/komune-io/fixers-f2"
    }
    sonar {
        organization = "komune-io"
        projectKey = "komune-io_fixers-f2"
        properties {
            property("sonar.coverage.exclusions", "f2-bdd/**/*")
        }
    }
    repositories {
        sonatypeSnapshots = true
    }
    publish {
        gradlePluginPortalEnabled = false
    }
}