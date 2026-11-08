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

// Patched transitive npm versions for kotlin-js-store/yarn.lock (Dependabot cannot update it: no package.json).
// Every pin is path-scoped with `**/`: an unscoped glob breaks karma, and `mocha/x` without `**/` is ignored.
// - ws: pinned to 8.20.1 by ktor-client-core, which KGP copies into every ktor-using package,
//   so it needs `**/ws`.
// - serialize-javascript, diff, glob, minimatch: from mocha 11; minimatch also from karma-webpack 5.
// Upstream bumps do not make a pin safe to drop (mocha 12 still asks for minimatch ^10, which is vulnerable):
// remove a pin only once `js-lock-refresh` without it still resolves patched versions for every advisory.
// Regenerate the lock after any change: `make -f infra/make/libs.mk js-lock-refresh`.
plugins.withType<YarnPlugin> {
    the<YarnRootExtension>().apply {
        resolution("**/ws", "8.22.2")
        resolution("**/mocha/serialize-javascript", "7.1.4")
        resolution("**/mocha/diff", "8.0.4")
        resolution("**/mocha/glob", "14.0.4")
        resolution("**/mocha/minimatch", "11.0.5")
        resolution("**/karma-webpack/minimatch", "11.0.5")
    }
}
