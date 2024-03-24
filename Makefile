
.PHONY: version

lint: lint-libs
build: build-libs
test: test-libs
package: package-libs

# Old task
libs: package-kotlin
package-kotlin: build-libs test-libs package-libs

lint-libs:
	./gradlew detekt

build-libs:
	./gradlew build publishToMavenLocal -x test

test-libs:
	./gradlew test

package-libs: build-libs
	./gradlew publish

version:
	@VERSION=$$(cat VERSION); \
	echo "$$VERSION"
