plugins {
    kotlin("multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
            }
        }
        jsMain {
            dependencies {
            }
        }
        jvmMain {
            dependencies {
            }
        }
    }
}

apply(from = rootProject.file("gradle/publishing-mpp.gradle"))
