import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnPlugin
import org.jetbrains.kotlin.gradle.targets.js.yarn.YarnRootExtension

buildscript {
    dependencies {
        constraints {
            // GHSA-j288-q9x7-2f5v: spring-boot-gradle-plugin -> commons-compress 1.27.1 pulls commons-lang3 3.16.0
            // onto the build classpath. Remove once the Spring Boot plugin brings >= 3.18.0 by itself.
            classpath(catalogue.commons.lang3)
        }
    }
}

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
    bundle {
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
            property("sonar.issue.ignore.multicriteria", "vendored")
            property("sonar.issue.ignore.multicriteria.vendored.ruleKey", "*")
            property("sonar.issue.ignore.multicriteria.vendored.resourceKey", "**/org/springframework/**")
        }
    }
    repositories {
        sonatypeSnapshots = true
    }
}

// Patched transitive npm versions for kotlin-js-store/yarn.lock: see the `npm-` block in gradle/libs.versions.toml.
plugins.withType<YarnPlugin> {
    the<YarnRootExtension>().apply {
        resolution("**/ws", catalogue.versions.npm.ws.get())
        resolution("**/mocha/serialize-javascript", catalogue.versions.npm.mocha.serialize.javascript.get())
        resolution("**/mocha/diff", catalogue.versions.npm.mocha.diff.get())
        resolution("**/mocha/glob", catalogue.versions.npm.mocha.glob.get())
        resolution("**/mocha/minimatch", catalogue.versions.npm.mocha.minimatch.get())
        resolution("**/karma-webpack/minimatch", catalogue.versions.npm.karma.webpack.minimatch.get())
    }
}
