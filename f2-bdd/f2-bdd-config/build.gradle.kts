plugins {
	id("io.komune.fixers.gradle.kotlin.jvm")
}

dependencies {
	Dependencies.Jvm.cucumber(::api)
}
