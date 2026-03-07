plugins {
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.spring) apply false
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.spring.boot) apply false

    alias(libs.plugins.fixers.config)
    alias(libs.plugins.fixers.check)
    alias(libs.plugins.fixers.publish)
    alias(libs.plugins.fixers.kotlin.jvm) apply false
    alias(libs.plugins.fixers.kotlin.mpp) apply false
}

allprojects {
    group = "io.komune.f2"
    version = System.getenv("VERSION") ?: "experimental-SNAPSHOT"
    repositories {
        if (System.getenv("MAVEN_LOCAL_USE") == "true") {
            mavenLocal()
        }
        mavenCentral()
        maven { url = uri("https://central.sonatype.com/repository/maven-snapshots") }
    }
}

fixers {
    bundle {
        id = "f2"
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
