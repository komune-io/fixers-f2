plugins {
    `version-catalog`
    alias(catalogue.plugins.fixers.gradle.publish)
}

catalog {
    versionCatalog {
        from(files("../../gradle/libs.versions.toml"))
    }
}
