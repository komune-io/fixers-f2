
buildscript {
    dependencies {
        constraints {
            // GHSA-j288-q9x7-2f5v: spring-boot-gradle-plugin -> commons-compress 1.27.1 pulls commons-lang3 3.16.0
            // onto the build classpath. Remove once the Spring Boot plugin brings >= 3.18.0 by itself.
            classpath(libs.commons.lang3)
        }
    }
}

plugins {
    alias(libs.plugins.kotlin.kapt) apply false
    alias(libs.plugins.kotlin.spring) apply false
    alias(libs.plugins.spring.boot) apply false
    alias(libs.plugins.kotlin.serialization)

    alias(libs.plugins.fixers.gradle.config)
    alias(libs.plugins.fixers.gradle.check)
    alias(libs.plugins.fixers.gradle.publish)
    alias(libs.plugins.fixers.gradle.kotlin.jvm) apply false
    alias(libs.plugins.fixers.gradle.kotlin.mpp) apply false
}

subprojects {
    configurations.matching { it.name == "kapt" }.configureEach {
        val bomDep = project.dependencies.platform(project.dependencies.project(":f2-gradle:f2-gradle-bom"))
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
    kt2Ts {
        outputDirectory = "ts"
        committedFiles = io.komune.fixers.gradle.config.model.Kt2TsFiles.DECLARATIONS
        // A Kotlin function type has no optional parameter: the JS client's trailing `signal` is optional in
        // TypeScript, as JS passes undefined for it and Kotlin takes undefined for null.
        additionalCleaning = mapOf(
            ".d.mts" to listOf(Regex("""\bsignal: Nullable<AbortSignal>\) =>""") to "signal?: Nullable<AbortSignal>) =>"),
        )
    }
    bundle {
        group = "io.komune.f2"
        name = "F2"
        description = "Wrapper around Spring Cloud Function"
        url = "https://github.com/komune-io/fixers-f2"
        scmConnection = "scm:git:git://github.com/komune-io/fixers-f2.git"
        scmDeveloperConnection = "scm:git:ssh://github.com/komune-io/fixers-f2.git"
    }
    sonar {
        organization = "komune-io"
        projectKey = "komune-io_fixers-f2"
        properties {
            property("sonar.coverage.exclusions", "f2-bdd/**/*")
            property("sonar.issue.ignore.multicriteria", "vendored")
            property("sonar.issue.ignore.multicriteria.vendored.ruleKey", "*")
            property("sonar.issue.ignore.multicriteria.vendored.resourceKey", "**/org/springframework/**")
        }
    }
}
