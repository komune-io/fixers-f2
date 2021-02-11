plugins {
    kotlin("multiplatform")
}

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":f2-client"))
                api(project(":f2-client:f2-client-ktor:f2-client-ktor-http"))
                api(project(":f2-client:f2-client-ktor:f2-client-ktor-rsocket"))
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
