plugins {
    `version-catalog`
    `maven-publish`
    alias(catalogue.plugins.fixers.gradle.publish)
}

catalog {
    versionCatalog {
        from(files("../../gradle/catalogue.versions.toml"))
    }
}

publishing {
    publications {
        create<MavenPublication>("versionCatalog") {
            from(components["versionCatalog"])
            artifactId = "f2-gradle-catalog"
            groupId = "io.komune.f2"
        }
    }
}
