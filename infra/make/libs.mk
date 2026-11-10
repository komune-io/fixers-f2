VERSION = $(shell cat VERSION)

.PHONY: clean lint build test check publish promote version verify-metadata verify-metadata-dry js-lock-refresh

clean:
	./gradlew clean

lint:
	./gradlew check --continue

# `clean` runs alone: in one invocation the root `clean` deletes build/js while the subprojects'
# Kotlin/JS tasks write their packages there (Unable to delete directory … New files were found).
build:
	./gradlew clean
	VERSION=$(VERSION) ./gradlew build publishToMavenLocal -x test -x jvmTest -x jsTest -x jsBrowserTest -x kotlinStoreYarnLock

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

# Regenerates gradle/verification-metadata.xml and the exported keyring, replacing the
# current files. Runs the dry-run variant then moves the generated files into place.
verify-metadata: verify-metadata-dry
	mv gradle/verification-metadata.dryrun.xml gradle/verification-metadata.xml
	mv gradle/verification-keyring.dryrun.keys gradle/verification-keyring.keys
	mv gradle/verification-keyring.dryrun.gpg gradle/verification-keyring.gpg

# Generates the same files with the .dryrun suffix, to inspect the delta without replacing anything.
verify-metadata-dry:
	./gradlew --write-verification-metadata pgp,sha256 --export-keys --dry-run build publishToMavenLocal

# Regenerates kotlin-js-store/yarn.lock from scratch. kotlinUpgradeYarnLock alone keeps every
# version already locked, so the old lock and build/js must be deleted first.
js-lock-refresh:
	rm -rf build/js kotlin-js-store/yarn.lock
	./gradlew rootPackageJson --rerun kotlinUpgradeYarnLock
