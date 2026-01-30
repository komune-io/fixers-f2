plugins {
    id("org.springframework.boot")
    id("io.komune.fixers.gradle.kotlin.jvm")
    kotlin("plugin.spring")
}

repositories {
    mavenCentral()
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
}

dependencies {

    implementation(project(":f2-spring:function:f2-spring-boot-starter-function-http"))

    Dependencies.Jvm.Test.springTest(::testImplementation)
}

tasks.withType<Test> {
    useJUnitPlatform()
}
