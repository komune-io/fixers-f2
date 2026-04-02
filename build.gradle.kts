plugins {
    id("f2-bom-conventions")
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
}