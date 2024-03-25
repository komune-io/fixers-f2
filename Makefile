VERSION = $(shell cat VERSION)

lint: lint-libs
build: build-libs
test: test-libs
publish: publish-libs
promote: promote-libs

# Old task
libs: package-kotlin
package-kotlin: build-libs test-libs package-libs

lint-libs:
	./gradlew detekt

build-libs:
	./gradlew build publishToMavenLocal -x test

test-libs:
	./gradlew test

publish-libs: build-libs
	PKG_MAVEN_REPO=github ./gradlew publish

promote-libs: build-libs
	PKG_MAVEN_REPO=sonatype_oss ./gradlew publish

.PHONY: version
version:
	echo "$$VERSION"
