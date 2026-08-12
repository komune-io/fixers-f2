VERSION = $(shell cat VERSION)

.PHONY: clean lint build test check publish promote version

clean:
	./gradlew clean

lint:
	./gradlew check

build:
	VERSION=$(VERSION) ./gradlew clean build publishToMavenLocal -x test

# `test` is a JVM-only Gradle task: Kotlin Multiplatform modules (f2-dsl, f2-client) do not
# register one, their tests live behind `allTests` (jvmTest + jsTest).
test:
	./gradlew allTests test

check:
	VERSION=$(VERSION) ./gradlew sonar

stage:
	VERSION=$(VERSION) ./gradlew stage

promote:
	VERSION=$(VERSION) ./gradlew promote

version:
	@echo "$(VERSION)"