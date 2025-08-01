VERSION = $(shell cat VERSION)

.PHONY: lint build test publish promote version

lint:
	./gradlew detekt

build:
	VERSION=$(VERSION) ./gradlew clean build publishToMavenLocal -x test

test:
	./gradlew test

stage:
	VERSION=$(VERSION) ./gradlew stage

promote:
	VERSION=$(VERSION) ./gradlew promote

version:
	@echo "$(VERSION)"