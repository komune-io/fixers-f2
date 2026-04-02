subprojects {
    configurations.matching { it.name == "kapt" }.configureEach {
        val bomDep = project.dependencies.platform(project(":f2-gradle:f2-gradle-bom"))
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
