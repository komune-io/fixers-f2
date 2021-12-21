plugins {
    id("city.smartb.fixers.gradle.kotlin.jvm")
    id("city.smartb.fixers.gradle.publish")

}

dependencies {
    api(project(":f2-spring:function:f2-spring-boot-starter-function"))
    Dependencies.Jvm.Spring.cloudFunctionRSocket(::api)


    implementation(project(":f2-client:f2-client-ktor"))
    implementation(project(":f2-client:f2-client-ktor:f2-client-ktor-rsocket"))
    
    testImplementation(project(":f2-bdd:f2-bdd-spring-autoconfigure"))


}
