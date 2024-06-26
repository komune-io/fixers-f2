VERSION = $(shell cat VERSION)

.PHONY: lint build test publish promote version

lint:
	./gradlew detekt

build:
	VERSION=$(VERSION) ./gradlew clean build publishToMavenLocal -x test

test:
	./gradlew test

publish:
	VERSION=$(VERSION) PKG_MAVEN_REPO=github ./gradlew publish -Dorg.gradle.parallel=true

promote:
	VERSION=$(VERSION) PKG_MAVEN_REPO=sonatype_oss ./gradlew publish

version:
	@echo "$(VERSION)"