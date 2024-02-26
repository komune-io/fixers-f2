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

    implementation(project(":f2-spring:function:f2-spring-boot-starter-function-http"))

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = "17"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
