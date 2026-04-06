package io.komune.f2.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import java.util.Properties

class F2BomPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val bomVersion = loadBomVersion()
        val bomNotation = "io.komune.f2:f2-gradle-bom:$bomVersion"

        fun Project.configureBom() {
            val proj = this
            configurations.matching { it.name == "kapt" || it.name.startsWith("ksp") }.configureEach {
                proj.dependencies.add(name, proj.dependencies.platform(bomNotation))
            }
            pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
                val config = if (configurations.findByName("api") != null) "api" else "implementation"
                dependencies.add(config, dependencies.platform(bomNotation))
            }
            pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
                dependencies.add("commonMainApi", dependencies.platform(bomNotation))
            }
        }

        project.configureBom()
        project.subprojects { configureBom() }
    }

    private fun loadBomVersion(): String {
        val props = Properties()
        javaClass.getResourceAsStream("/f2-plugin.properties")?.use { props.load(it) }
        return props.getProperty("version")
            ?: throw IllegalStateException("Could not determine F2 BOM version from f2-plugin.properties")
    }
}
