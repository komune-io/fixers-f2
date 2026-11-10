plugins {
    `version-catalog`
    alias(libs.plugins.fixers.gradle.publish)
}

catalog {
    versionCatalog {
        from(files("../../gradle/libs.versions.toml"))
    }
}
