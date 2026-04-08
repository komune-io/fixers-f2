plugins {
    `java-gradle-plugin`
    `kotlin-dsl`
    alias(catalogue.plugins.fixers.gradle.publish)
}

gradlePlugin {
    plugins {
        create("f2Bom") {
            id = "io.komune.fixers.f2.bom"
            implementationClass = "io.komune.fixers.f2.gradle.F2BomPlugin"
            displayName = "F2 BOM Convention Plugin"
            description = "Applies the F2 BOM to all Kotlin subprojects for automatic dependency version management"
        }
    }
}

val generateVersionProperties by tasks.registering {
    val outputDir = layout.buildDirectory.dir("generated/resources")
    val projectVersion = provider { project.version.toString() }
    inputs.property("version", projectVersion)
    outputs.dir(outputDir)
    doLast {
        val file = outputDir.get().file("f2-plugin.properties").asFile
        file.parentFile.mkdirs()
        file.writeText("version=${projectVersion.get()}\n")
    }
}

sourceSets.main {
    resources.srcDir(generateVersionProperties.map { it.outputs.files.singleFile })
}
