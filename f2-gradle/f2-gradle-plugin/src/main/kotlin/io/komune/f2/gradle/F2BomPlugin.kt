package io.komune.f2.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.Properties

class F2BomPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val bomVersion = loadBomVersion()
        val bomNotation = "io.komune.f2:f2-gradle-bom:$bomVersion"

        project.subprojects.forEach { sub ->
            sub.configurations.matching { it.name == "kapt" }.configureEach {
                val bomDep = sub.dependencies.platform(bomNotation)
                sub.dependencies.add(name, bomDep)
            }
            sub.pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
                sub.dependencies.add("api", sub.dependencies.platform(bomNotation))
            }
            sub.pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
                sub.dependencies.add("commonMainApi", sub.dependencies.platform(bomNotation))
            }
        }
    }

    private fun loadBomVersion(): String {
        val props = Properties()
        javaClass.getResourceAsStream("/f2-plugin.properties")?.use { props.load(it) }
        return props.getProperty("version")
            ?: throw IllegalStateException("Could not determine F2 BOM version from f2-plugin.properties")
    }
}
