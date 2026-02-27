VERSION = $(shell cat VERSION)

.PHONY: clean lint build test publish promote version

clean:
	./gradlew clean

lint:
	./gradlew check

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