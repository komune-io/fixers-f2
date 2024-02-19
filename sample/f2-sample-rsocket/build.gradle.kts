plugins {
    id("org.springframework.boot")
    id("io.komune.fixers.gradle.kotlin.jvm")
    kotlin("plugin.spring")
    id("io.spring.dependency-management")
}

repositories {
    mavenCentral()
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
}


dependencies {
    implementation(project(":f2-spring:function:f2-spring-boot-starter-function-rsocket"))

    testImplementation("org.springframework.boot:spring-boot-starter-test")

    implementation(project(":f2-client:f2-client-ktor"))
    implementation(project(":f2-client:f2-client-ktor:f2-client-ktor-rsocket"))
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
