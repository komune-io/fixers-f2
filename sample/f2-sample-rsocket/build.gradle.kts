plugins {
    id("org.springframework.boot")
    id("city.smartb.fixers.gradle.kotlin.jvm")
    kotlin("plugin.spring")
    id("io.spring.dependency-management")
}

repositories {
    jcenter()
    mavenCentral()
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
}


dependencies {
//    implementation("city.smartb.f2:f2-spring-boot-starter-function-rsocket:latest")
    implementation(project(":f2-spring:function:f2-spring-boot-starter-function-rsocket"))

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.jupiter:junit-jupiter-api")

    implementation(project(":f2-client:f2-client-ktor"))
    implementation(project(":f2-client:f2-client-ktor:f2-client-ktor-rsocket"))

}


tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
