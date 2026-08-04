# Migrate vendored spring-cloud-function to v5.0.3 Implementation Plan

> **For agentic workers:** REQUIRED SUB-SKILL: Use superpowers:subagent-driven-development (recommended) or superpowers:executing-plans to implement this plan task-by-task. Steps use checkbox (`- [ ]`) syntax for tracking.

**Goal:** Move both the `spring-cloud-function` fork and F2's vendored copies of it from v5.0.1 to v5.0.3 (the latest upstream release), keeping the two patches in sync and every komune fix verified as still correct against the new base.

**Architecture:** v5.0.1 → v5.0.3 touches exactly **one** of the six vendored files content-wise (`SimpleFunctionRegistry.java`, +34 lines: a bounded LRU cache for `wrappedFunctionDefinitions`, `equals`/`hashCode` on `FunctionInvocationWrapper`, and a recursive-composition guard in `andThen()`) plus two `pom.xml` version-string bumps. All six upstream hunks land in code regions with **zero line overlap** with any KOMUNE-modified region — confirmed by direct diff and method-identification below. The other five vendored files (`ContextFunctionCatalogAutoConfiguration.java`, `JsonMessageConverter.java`, `KotlinLambdaToFunctionAutoConfiguration.java`, `SmartCompositeMessageConverter.java`, `FunctionWebRequestProcessingHelper.java`) are **byte-identical** between v5.0.1 and v5.0.3 — content-only diff is 0 lines. Same method as the v5.0.1 port: extract the file fresh at the target tag, reapply each KOMUNE hunk via exact text match, verify.

**Tech Stack:** Java 17, Maven (fork), Kotlin/Gradle (F2), Spring Boot 4.0.6, Spring Cloud 2025.1.x.

## Global Constraints

- **Upstream tags** (already present locally in `/Users/adrien/Dev/komune/fixers/spring-cloud-function`, full history, not shallow):
  - `v5.0.1` = `85c48fb379a74781d5c07c8bfadf4a711a485fae` (current base)
  - `v5.0.3` = `0e791525eddcd0ac3d0a91e03606dd5b8b3c4c3b` (target, latest release — confirmed via `git ls-remote --tags`)
- **Only file with content churn:** `spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java`. Confirmed via `diff -w -B` between the two tags for every vendored file — all others show 0 changed lines.
- **Only other churn:** `spring-cloud-function-context/pom.xml` and `spring-cloud-function-web/pom.xml` — trivial `<parent><version>` bumps (5.0.1→5.0.3, automatic when branching off the new tag) and one unrelated dependency version bump (2.2.0→2.2.1). Neither touches the `spring-web` `<optional>` KOMUNE edit (verified: that edit is at a different `<dependency>` block).
- **Zero conflict, verified two ways:** (1) line-range comparison — KOMUNE markers sit at lines 898-909 and 1553-1608 in the current file; every upstream hunk lands at 27-145 or 486-515 or 689-722, none overlapping; (2) method identification — the upstream `equals`/`hashCode` addition and recursive-guard addition land inside `andThen()`, a method KOMUNE never touches.
- **F2's SCF version is BOM-derived, not hardcoded.** `gradle/libs.versions.toml:5` sets `spring-cloud = "2025.1.1"`, consumed by `f2-gradle/f2-gradle-bom/build.gradle.kts:18` as `platform("org.springframework.cloud:spring-cloud-dependencies:${...}")`. Confirmed via direct POM inspection: Spring Cloud BOM `2025.1.2` pins `spring-cloud-function.version` to `5.0.3`. **Correction (post-migration):** Spring Cloud BOM 2025.1.2 changes 17 version properties versus 2025.1.1, not just spring-cloud-function (verified via direct file diff of both release POMs — an earlier `diff <(curl ...) <(curl ...)` process-substitution check falsely reported the files as identical, which is why this was initially missed; always diff to real files in this environment, not process substitution). The other 16 are: spring-boot (4.0.2→4.0.7), spring-cloud-config (5.0.1→5.0.4), spring-cloud-contract (5.0.2→5.0.3), and 13 other spring-cloud-* modules F2 doesn't use directly (5.0.1→5.0.2). None of the 16 besides spring-boot are on F2's actual classpath (F2 only pulls spring-cloud-function + spring-cloud-dependencies' platform constraints), so this migration's practical blast radius beyond spring-cloud-function itself is: Spring Boot 4.0.6→4.0.7 (now correctly pinned to match, see `gradle/libs.versions.toml`), and transitively Spring Framework 7.0.7→7.0.8, Jackson 3.1.2→3.1.4, Reactor 3.8.5→3.8.6, reactor-netty 1.3.5→1.3.6, Netty 4.2.12→4.2.15.Final, Micrometer 1.16.5→1.16.6, Logback 1.5.32→1.5.34, slf4j 2.0.17→2.0.18 (all verified via resolved compileClasspath diff, all patch/minor bumps, no test regressions observed). So the F2-side migration is a one-line `spring-cloud` version bump plus re-porting the one changed vendored file, plus keeping the `spring-boot` pin honest.
- **Maven build environment:** `repo.spring.io/release` returns 401 Unauthorized for some artifacts (e.g. `reactor-core:3.8.2`) in this environment. Use a Central-only mirror for all `mvnw` invocations in this plan (Task 1, Step 5 sets this up once).
- Run F2 tests with: `./gradlew test detekt` from `/Users/adrien/Dev/komune/fixers/fixers-f2`.
- Run fork tests with: `./mvnw -s /tmp/settings-central.xml -B -pl <module> test` from `/Users/adrien/Dev/komune/fixers/spring-cloud-function`.
- **Branching:** Task 1 creates `fixers/5.0.3` in the fork off the `v5.0.3` tag (this is the explicit ask). For F2, this plan continues on the **existing branch** `test/vendored-scf-fixes-coverage` (already pushed, PR #118 open, draft, currently v5.0.1-based) rather than opening a second branch/PR about the same vendored files — confirm this with the user before Task 3 if they'd prefer a separate branch/PR instead.
- **Do not push automatically.** Task 1 and Task 4 each end with a local commit only. Pushing (fork branch, F2 branch update) is a separate, explicit step at the end — ask before doing it, matching how the v5.0.1 work was handled.

## File Structure

| Repo | File | Action |
|---|---|---|
| fork | `spring-cloud-function-context/.../catalog/SimpleFunctionRegistry.java` | Regenerate from v5.0.3 + reapply 5 KOMUNE hunks |
| fork | `spring-cloud-function-context/pom.xml` | Regenerate from v5.0.3 (parent version, dep bump come for free) + reapply 1 KOMUNE hunk |
| fork | all other vendored files | No change — verify only |
| f2 | `gradle/libs.versions.toml` | Bump `spring-cloud` version |
| f2 | `f2-spring/.../catalog/SimpleFunctionRegistry.java` | Same regenerate-and-reapply as fork, space-indented |
| f2 | all other vendored files | No change — verify only |

---

### Task 1: Fork — create `fixers/5.0.3` and port `SimpleFunctionRegistry.java`

**Files:**
- Create branch: `fixers/5.0.3` (off tag `v5.0.3`) in `/Users/adrien/Dev/komune/fixers/spring-cloud-function`
- Modify: `spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java`

**Interfaces:**
- Consumes: nothing from other tasks.
- Produces: the `fixers/5.0.3` branch and its `SimpleFunctionRegistry.java`, which Task 2 verifies and Task 4 mirrors into F2.

- [ ] **Step 1: Create the branch**

```bash
cd /Users/adrien/Dev/komune/fixers/spring-cloud-function
git status --short   # must be clean before switching
git checkout -b fixers/5.0.3 v5.0.3
```

- [ ] **Step 2: Extract the pristine v5.0.3 file to scratch, with tabs preserved (fork convention)**

```bash
git show v5.0.3:spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java \
  > /tmp/SimpleFunctionRegistry.v503.java
wc -l /tmp/SimpleFunctionRegistry.v503.java   # sanity check, expect ~1660 lines
```

- [ ] **Step 3: Reapply the 5 KOMUNE hunks**

Copy `/tmp/SimpleFunctionRegistry.v503.java` to the real path (`spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java`), then apply these five edits. Each `old_string` below is upstream v5.0.3's pristine text at that location — verify it still matches exactly (v5.0.1→v5.0.3 made zero changes to any of these regions, so it should) before replacing.

Edit 1 — typed collection deserialization:

```java
// OLD (pristine v5.0.3):
					MessageHeaders headers = input instanceof Message ? ((Message) input).getHeaders() : new MessageHeaders(Collections.emptyMap());
					Collection collectionPayload = jsonMapper.fromJson(payload, Collection.class);
					Class inputClass = FunctionTypeUtils.getRawType(this.inputType);

// NEW:
					MessageHeaders headers = input instanceof Message ? ((Message) input).getHeaders() : new MessageHeaders(Collections.emptyMap());
					// KOMUNE Modification
					// Collection Type is needed by kotlin serializer to deserialize object
					// Original version:
					// Collection collectionPayload = jsonMapper.fromJson(payload, Collection.class);
					Type genType = FunctionTypeUtils.getGenericType(this.inputType);
					ResolvableType resolvableType = ResolvableType.forType(genType);
					if (resolvableType.toClass() == Message.class) {
						resolvableType = resolvableType.getGeneric(0);
					}
					ResolvableType listType = ResolvableType.forClassWithGenerics(List.class, resolvableType);
					Collection collectionPayload = jsonMapper.fromJson(payload, listType.getType());
					// KOMUNE End Of Modification

					Class inputClass = FunctionTypeUtils.getRawType(this.inputType);
```

Edit 2 — import for `ResponseStatusException` (needed by edits 3-6 below; check first whether v5.0.3 already imports it — it does not, since this import is KOMUNE-only):

```java
// OLD:
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

// NEW:
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
```

Edit 3 — `convertInputPublisherIfNecessary`, Mono branch:

```java
// OLD:
					? Mono.from(publisher).map(v -> {
						try {
							return this.convertInputIfNecessary(v, actualType == null ? type : actualType);
						}
						catch (Exception e) {
							throw new IllegalStateException("Failed to convert input", e);
						}
					})
					: Flux.from(publisher).map(v -> {
						try {
							return this.convertInputIfNecessary(v, actualType == null ? type : actualType);
						}
						catch (Exception e) {
							throw new IllegalStateException("Failed to convert input", e);
						}
					});

// NEW:
					? Mono.from(publisher).map(v -> {
						try {
							return this.convertInputIfNecessary(v, actualType == null ? type : actualType);
						}
						// KOMUNE Modification
						// force message conversion error propagation
						catch (ResponseStatusException e) {
							throw e;
						}
						// KOMUNE End Of Modification
						catch (Exception e) {
							throw new IllegalStateException("Failed to convert input", e);
						}
					})
					: Flux.from(publisher).map(v -> {
						try {
							return this.convertInputIfNecessary(v, actualType == null ? type : actualType);
						}
						// KOMUNE Modification
						// force message conversion error propagation
						catch (ResponseStatusException e) {
							throw e;
						}
						// KOMUNE End Of Modification
						catch (Exception e) {
							throw new IllegalStateException("Failed to convert input", e);
						}
					});
```

Edit 4 — `convertOutputPublisherIfNecessary`, both branches:

```java
// OLD:
					? Mono.from(publisher).map(v -> {
						try {
							return this.convertOutputIfNecessary(v, type, expectedOutputContentType);
						}
						catch (Exception e) {
							throw new IllegalStateException("Failed to convert output", e);
						}
					})
					: Flux.from(publisher).map(v -> {
						try {
							return this.convertOutputIfNecessary(v, type, expectedOutputContentType);
						}
						catch (Exception e) {
							throw new IllegalStateException("Failed to convert output", e);
						}
					});

// NEW:
					? Mono.from(publisher).map(v -> {
						try {
							return this.convertOutputIfNecessary(v, type, expectedOutputContentType);
						}
						// KOMUNE Modification
						// force message conversion error propagation
						catch (ResponseStatusException e) {
							throw e;
						}
						// KOMUNE End Of Modification
						catch (Exception e) {
							throw new IllegalStateException("Failed to convert output", e);
						}
					})
					: Flux.from(publisher).map(v -> {
						try {
							return this.convertOutputIfNecessary(v, type, expectedOutputContentType);
						}
						// KOMUNE Modification
						// force message conversion error propagation
						catch (ResponseStatusException e) {
							throw e;
						}
						// KOMUNE End Of Modification
						catch (Exception e) {
							throw new IllegalStateException("Failed to convert output", e);
						}
					});
```

(Edit 3 and Edit 4 both contain a Mono branch and a Flux branch with distinct surrounding text — `convertInputIfNecessary` vs `convertOutputIfNecessary`, `"Failed to convert input"` vs `"Failed to convert output"` — so each of the 4 occurrences is independently unique and safe to match individually if a single combined replace does not apply cleanly.)

- [ ] **Step 4: Verify — the new file should differ from v5.0.3 pristine by exactly the 5 KOMUNE hunks, and from the current v5.0.1-based file by only the 6 upstream hunks**

```bash
cd /Users/adrien/Dev/komune/fixers/spring-cloud-function
f=spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java
echo "vs pristine v5.0.3 (expect exactly the 5 KOMUNE additions, ~44 lines):"
diff -w -B <(git show v5.0.3:"$f") "$f" | grep -cE '^[<>]'
echo "vs the v5.0.1-based file on fixers/5.0.1 (expect exactly the 6 upstream hunks, ~34-38 lines):"
diff -w -B <(git show fixers/5.0.1:"$f") "$f" | grep -cE '^[<>]'
grep -c KOMUNE "$f"   # expect 10 (5 Modification + 5 End Of Modification markers)
```

If either diff shows unexpected lines, stop and inspect — do not proceed to Step 5 with a file that doesn't match this shape.

- [ ] **Step 5: Set up the Central-only Maven mirror (once) and build/test**

```bash
cat > /tmp/settings-central.xml <<'EOF'
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0">
  <mirrors>
    <mirror>
      <id>central-only</id>
      <name>Central mirror for all repos</name>
      <url>https://repo1.maven.org/maven2</url>
      <mirrorOf>*</mirrorOf>
    </mirror>
  </mirrors>
</settings>
EOF
find ~/.m2/repository -name "*.lastUpdated" -delete
cd /Users/adrien/Dev/komune/fixers/spring-cloud-function
./mvnw -s /tmp/settings-central.xml -B -pl spring-cloud-function-context,spring-cloud-function-web -am test
```

Expected: `BUILD SUCCESS`, `You have 0 Checkstyle violations`, and roughly the same test counts as the v5.0.1 build (214 tests in `spring-cloud-function-context`, 196 in `spring-cloud-function-web` — small variance is fine since v5.0.3 adds its own new tests upstream, but there must be 0 failures/errors).

- [ ] **Step 6: Commit**

```bash
git add spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java
git commit -m "Fixers fixes (ported to v5.0.3)

Ports the komune fork's SimpleFunctionRegistry fixes from v5.0.1 to
v5.0.3. Only this file changed upstream between the two releases (a
bounded LRU cache for wrappedFunctionDefinitions, equals/hashCode on
FunctionInvocationWrapper, and a recursive-composition guard in
andThen() - GH-1266/GH-1307 area) and none of those additions overlap
the KOMUNE-modified regions (typed collection deserialization,
ResponseStatusException rethrow on both the input and output
conversion paths). All other vendored files are byte-identical
between v5.0.1 and v5.0.3 (see Task 2) and are carried forward
unchanged.

Verified: mvn test on spring-cloud-function-context and
spring-cloud-function-web, 0 failures, 0 checkstyle violations."
```

---

### Task 2: Fork — verify the other five vendored files and the two `pom.xml`s need no further changes

**Files:**
- Verify only (no edits expected): `ContextFunctionCatalogAutoConfiguration.java`, `JsonMessageConverter.java`, `KotlinLambdaToFunctionAutoConfiguration.java`, `SmartCompositeMessageConverter.java`, `FunctionWebRequestProcessingHelper.java`, `spring-cloud-function-context/pom.xml`, `spring-cloud-function-web/pom.xml`

**Interfaces:**
- Consumes: the `fixers/5.0.3` branch from Task 1 (already checked out on v5.0.3 base, so these 7 files already contain v5.0.3's pristine content plus the pre-existing KOMUNE edits carried over from `git checkout -b ... v5.0.3` history — wait, they are NOT automatically carried over since this is a fresh branch off the tag, not a merge. This step must actively confirm they are unmodified from pristine v5.0.3 and separately confirm the KOMUNE edits from `fixers/5.0.1` still need to be reapplied here, since branching from a tag starts from that tag's pristine state.)

**Important correction to the assumption above:** `git checkout -b fixers/5.0.3 v5.0.3` starts these 7 files at pristine v5.0.3 — the KOMUNE edits from `fixers/5.0.1` are NOT present yet. This task must reapply them (they're unchanged since v5.0.1→v5.0.3 made no upstream changes to these files, so it is a pure carry-forward, not a re-port).

- [ ] **Step 1: Confirm these 6 non-`SimpleFunctionRegistry` files are pristine (matching v5.0.3) right now on the new branch**

```bash
cd /Users/adrien/Dev/komune/fixers/spring-cloud-function
for f in \
  spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/ContextFunctionCatalogAutoConfiguration.java \
  spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/JsonMessageConverter.java \
  spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/KotlinLambdaToFunctionAutoConfiguration.java \
  spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/SmartCompositeMessageConverter.java \
  spring-cloud-function-web/src/main/java/org/springframework/cloud/function/web/util/FunctionWebRequestProcessingHelper.java \
  spring-cloud-function-context/pom.xml \
  spring-cloud-function-web/pom.xml \
  spring-cloud-function-web/src/test/java/org/springframework/cloud/function/web/flux/HttpGetIntegrationTests.java
do
  d=$(diff "$f" <(git show v5.0.3:"$f") | wc -l)
  printf "%-70s diff-from-pristine-v5.0.3=%s\n" "$(basename "$f")" "$d"
done
```

Expected: every line shows `0` (each file is currently pristine v5.0.3, since Task 1 only touched `SimpleFunctionRegistry.java`).

- [ ] **Step 2: Cherry-pick the KOMUNE portions of these 7 files from `fixers/5.0.1`, since v5.0.1→v5.0.3 made no changes to any of them**

For each file, diff it against its `fixers/5.0.1` counterpart and copy that counterpart's content over verbatim (safe here specifically because Step 1 proved zero upstream drift on these particular files):

```bash
cd /Users/adrien/Dev/komune/fixers/spring-cloud-function
for f in \
  spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/ContextFunctionCatalogAutoConfiguration.java \
  spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/JsonMessageConverter.java \
  spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/KotlinLambdaToFunctionAutoConfiguration.java \
  spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/SmartCompositeMessageConverter.java \
  spring-cloud-function-web/src/main/java/org/springframework/cloud/function/web/util/FunctionWebRequestProcessingHelper.java \
  spring-cloud-function-web/src/test/java/org/springframework/cloud/function/web/flux/HttpGetIntegrationTests.java
do
  git show fixers/5.0.1:"$f" > "$f"
done
```

For the two `pom.xml` files, do **not** copy wholesale (they have real v5.0.3-specific version bumps unrelated to komune — the `<parent><version>` and the `2.2.0`→`2.2.1` dependency). Instead reapply only the one KOMUNE hunk by hand:

```xml
<!-- spring-cloud-function-context/pom.xml — locate this exact block (pristine v5.0.3): -->
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
			<optional>true</optional>
		</dependency>

<!-- replace with: -->
			<groupId>org.springframework</groupId>
			<artifactId>spring-web</artifactId>
<!--			KOMUNE Modification-->
<!--			Required: ResponseStatusException is used to propagate conversion errors-->
<!--			<optional>true</optional>-->
<!--			KOMUNE End Of Modification-->
		</dependency>
```

- [ ] **Step 3: Verify each file now differs from pristine v5.0.3 by exactly its KOMUNE hunk, and is content-identical (`diff -w -B`) to its `fixers/5.0.1` counterpart**

```bash
cd /Users/adrien/Dev/komune/fixers/spring-cloud-function
for f in \
  spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/ContextFunctionCatalogAutoConfiguration.java \
  spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/JsonMessageConverter.java \
  spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/KotlinLambdaToFunctionAutoConfiguration.java \
  spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/SmartCompositeMessageConverter.java \
  spring-cloud-function-web/src/main/java/org/springframework/cloud/function/web/util/FunctionWebRequestProcessingHelper.java \
  spring-cloud-function-web/src/test/java/org/springframework/cloud/function/web/flux/HttpGetIntegrationTests.java
do
  n=$(diff -w -B "$f" <(git show fixers/5.0.1:"$f") | grep -cE '^[<>]')
  printf "%-70s content-diff-vs-fixers/5.0.1=%s (expect 0)\n" "$(basename "$f")" "$n"
done
n=$(diff -w -B spring-cloud-function-context/pom.xml <(git show fixers/5.0.1:spring-cloud-function-context/pom.xml) | grep -cE '^[<>]')
echo "pom.xml content-diff-vs-fixers/5.0.1=$n (expect 4: version 5.0.1 vs 5.0.3, dep 2.2.0 vs 2.2.1)"
```

- [ ] **Step 4: Full build + test**

```bash
cd /Users/adrien/Dev/komune/fixers/spring-cloud-function
./mvnw -s /tmp/settings-central.xml -B -pl spring-cloud-function-context,spring-cloud-function-web test
```

Expected: `BUILD SUCCESS`, 0 checkstyle violations, 0 failures/errors.

- [ ] **Step 5: Commit**

```bash
git add -A
git commit -m "Carry forward the remaining vendored fixes (no upstream changes between v5.0.1 and v5.0.3)

ContextFunctionCatalogAutoConfiguration, JsonMessageConverter,
KotlinLambdaToFunctionAutoConfiguration, SmartCompositeMessageConverter,
FunctionWebRequestProcessingHelper, and the HttpGetIntegrationTests
@Disabled marker are byte-identical between v5.0.1 and v5.0.3 upstream
(confirmed: diff -w -B between the two tags shows 0 changed lines for
each), so their KOMUNE patches carry forward unchanged. Only the
spring-web pom.xml optional-dependency comment needed manual
reapplication, since v5.0.3 also bumped the parent version and one
unrelated dependency in that same file."
```

---

### Task 3: F2 — bump the Spring Cloud BOM to resolve spring-cloud-function 5.0.3

**Files:**
- Modify: `gradle/libs.versions.toml`

**Interfaces:**
- Consumes: nothing.
- Produces: F2's dependency graph now resolving `spring-cloud-function-context`/`-kotlin`/`-web` to `5.0.3`, which Task 4 depends on for accurate `git show`-based verification against the actually-shipped jar.

- [ ] **Step 1: Bump the version**

In `gradle/libs.versions.toml`, change line 5:

```toml
# OLD:
spring-cloud = "2025.1.1"

# NEW:
spring-cloud = "2025.1.2"
```

- [ ] **Step 2: Verify the resolved spring-cloud-function version**

```bash
cd /Users/adrien/Dev/komune/fixers/fixers-f2
./gradlew :f2-spring:function:f2-spring-boot-starter-function:dependencies --configuration compileClasspath 2>&1 | grep -iE "spring-cloud-function" | head -8
```

Expected: every line shows `5.0.3`, not `5.0.1`.

- [ ] **Step 3: Compile-check before touching the vendored files**

```bash
./gradlew :f2-spring:function:f2-spring-boot-starter-function:compileJava :f2-spring:function:f2-spring-boot-starter-function-http:compileJava
```

This is expected to still succeed (the vendored files currently shadow 5.0.1-shaped classes; since only `SimpleFunctionRegistry.java` changed upstream and its API surface used by other code is unaffected — the new fields/methods are additive — this should compile clean, confirming no signature break before Task 4 updates it).

- [ ] **Step 4: Commit**

```bash
git add gradle/libs.versions.toml
git commit -m "chore: bump Spring Cloud BOM to 2025.1.2 (resolves spring-cloud-function 5.0.3)

The only property that changed between the 2025.1.1 and 2025.1.2 BOM
releases is spring-cloud-function.version (5.0.1 -> 5.0.3) - confirmed
by diffing every *.version property in both release POMs."
```

**Correction (post-migration):** the commit message above (and this
plan) understated the BOM diff. Spring Cloud BOM 2025.1.2 actually
changes 17 version properties versus 2025.1.1, not just
spring-cloud-function — see the corrected Global Constraints entry
above for the full list and F2's practical blast radius (Spring Boot
4.0.6→4.0.7, now correctly pinned in `gradle/libs.versions.toml`, plus
patch bumps to Spring Framework, Jackson, Reactor, Netty, Micrometer,
Logback, and slf4j). The false "only property that changed" claim was
caught in final review and corrected in a follow-up commit; commit
`937da6a` itself was left unamended.

---

### Task 4: F2 — re-port `SimpleFunctionRegistry.java` to v5.0.3

**Files:**
- Modify: `f2-spring/function/f2-spring-boot-starter-function/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java`

**Interfaces:**
- Consumes: the fork's `fixers/5.0.3` branch (Task 1) as the source of truth for the merged content, adapted to F2's space-indentation convention.

- [ ] **Step 1: Extract the fork's finished v5.0.3 file and re-indent tabs to 4 spaces**

```bash
cd /Users/adrien/Dev/komune/fixers/spring-cloud-function
git show fixers/5.0.3:spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java \
  | expand -t4 > /tmp/SimpleFunctionRegistry.f2.java
```

- [ ] **Step 2: Replace the F2 copy and verify indentation style matches the rest of the file**

```bash
cp /tmp/SimpleFunctionRegistry.f2.java \
  /Users/adrien/Dev/komune/fixers/fixers-f2/f2-spring/function/f2-spring-boot-starter-function/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java
grep -lP "\t" /Users/adrien/Dev/komune/fixers/fixers-f2/f2-spring/function/f2-spring-boot-starter-function/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java || echo "no tabs remain (correct)"
```

- [ ] **Step 3: Verify content-only equivalence to the fork and correct upstream delta**

```bash
cd /Users/adrien/Dev/komune/fixers/fixers-f2
f=f2-spring/function/f2-spring-boot-starter-function/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java
echo "content-diff vs fork's fixers/5.0.3 (expect 0):"
diff -w -B <(git -C /Users/adrien/Dev/komune/fixers/spring-cloud-function show fixers/5.0.3:spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java | expand -t4) "$f" | grep -cE '^[<>]'
grep -c KOMUNE "$f"   # expect 10
```

- [ ] **Step 4: Compile and run this module's tests**

```bash
cd /Users/adrien/Dev/komune/fixers/fixers-f2
./gradlew :f2-spring:function:f2-spring-boot-starter-function:test :f2-spring:function:f2-spring-boot-starter-function-http-mvc:test :f2-spring:function:f2-spring-boot-starter-function-http-webflux:test --rerun-tasks
```

Expected: `BUILD SUCCESSFUL`, same pass counts as before this task (100 / 62 / 62 tests respectively, 0 failures — this file's public behavior is unchanged, only internals gained an LRU bound and a recursion guard that no existing test exercises differently).

- [ ] **Step 5: Commit**

```bash
git add f2-spring/function/f2-spring-boot-starter-function/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java
git commit -m "port: forward-port SimpleFunctionRegistry to v5.0.3

Only vendored file with upstream changes between v5.0.1 and v5.0.3 (a
bounded LRU cache for wrappedFunctionDefinitions, equals/hashCode on
FunctionInvocationWrapper, and a recursive-composition guard in
andThen()), none of which overlap the KOMUNE-modified regions. Content
is byte-identical (modulo tabs vs spaces) to the fork's fixers/5.0.3
branch."
```

---

### Task 5: F2 — verify SimpleFunctionRegistry's KOMUNE fixes are still necessary post-migration

**Files:**
- Temporarily modify then restore: `f2-spring/function/f2-spring-boot-starter-function/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java`

**Interfaces:**
- Consumes: Task 4's re-ported file.
- Produces: empirical confirmation (not just the static line-overlap argument from Global Constraints) that each KOMUNE modification in this file is still load-bearing against the v5.0.3 base — upstream added a bounded LRU cache, `equals`/`hashCode`, and a recursion guard in `andThen()`, none of which should interact with the KOMUNE conversion-error-handling code, but this task proves it rather than asserting it.

Do this as three independent removal-proof cycles, matching the methodology used when these fixes were first added (see `docs/superpowers/plans/2026-08-04-untested-fork-fixes-tests.md` for the original results this task is re-confirming: typed-collection removal caused 24 test failures, output-side rethrow removal caused 1 failure, input-side rethrow removal caused the malformed-JSON raw-HTTP scenarios to fail). Each cycle: remove, run, confirm failure, restore, confirm pass again — never leave a cycle un-restored before moving to the next.

- [ ] **Step 1: Removal-proof — typed collection deserialization**

```bash
cd /Users/adrien/Dev/komune/fixers/fixers-f2
f=f2-spring/function/f2-spring-boot-starter-function/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java
cp "$f" /tmp/SimpleFunctionRegistry.java.bak
```

Edit the file, replacing:

```java
                    // KOMUNE Modification
                    // Collection Type is needed by kotlin serializer to deserialize object
                    // Original version:
                    // Collection collectionPayload = jsonMapper.fromJson(payload, Collection.class);
                    Type genType = FunctionTypeUtils.getGenericType(this.inputType);
                    ResolvableType resolvableType = ResolvableType.forType(genType);
                    if (resolvableType.toClass() == Message.class) {
                        resolvableType = resolvableType.getGeneric(0);
                    }
                    ResolvableType listType = ResolvableType.forClassWithGenerics(List.class, resolvableType);
                    Collection collectionPayload = jsonMapper.fromJson(payload, listType.getType());
                    // KOMUNE End Of Modification
```

with just:

```java
                    Collection collectionPayload = jsonMapper.fromJson(payload, Collection.class);
```

Then run:

```bash
./gradlew :f2-spring:function:f2-spring-boot-starter-function:test :f2-spring:function:f2-spring-boot-starter-function-http-mvc:test :f2-spring:function:f2-spring-boot-starter-function-http-webflux:test --rerun-tasks
```

Expected: `BUILD FAILED` with test failures (the KSerialization cucumber suites depend on this). Record the failure count. Then restore:

```bash
cp /tmp/SimpleFunctionRegistry.java.bak "$f"
```

- [ ] **Step 2: Removal-proof — output-side `ResponseStatusException` rethrow**

```bash
cd /Users/adrien/Dev/komune/fixers/fixers-f2
f=f2-spring/function/f2-spring-boot-starter-function/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java
cp "$f" /tmp/SimpleFunctionRegistry.java.bak
```

Edit the file, removing both occurrences of this block (inside `convertOutputPublisherIfNecessary`'s Mono and Flux branches — identifiable by `"Failed to convert output"` in the surrounding `catch (Exception e)`):

```java
                        // KOMUNE Modification
                        // force message conversion error propagation
                        catch (ResponseStatusException e) {
                            throw e;
                        }
                        // KOMUNE End Of Modification
```

so each branch reads:

```java
                        try {
                            return this.convertOutputIfNecessary(v, type, expectedOutputContentType);
                        }
                        catch (Exception e) {
                            throw new IllegalStateException("Failed to convert output", e);
                        }
```

Then run the same test command as Step 1. Expected: `BUILD FAILED` — at least one failure (originally `feature.Execute basic supplier from the catalog`, in the base function module's cucumber suite). Record it, then restore from `/tmp/SimpleFunctionRegistry.java.bak`.

- [ ] **Step 3: Removal-proof — input-side `ResponseStatusException` rethrow**

```bash
cd /Users/adrien/Dev/komune/fixers/fixers-f2
f=f2-spring/function/f2-spring-boot-starter-function/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java
cp "$f" /tmp/SimpleFunctionRegistry.java.bak
```

Edit the file, removing both occurrences of the equivalent block inside `convertInputPublisherIfNecessary`'s Mono and Flux branches (identifiable by `"Failed to convert input"`):

```java
                        // KOMUNE Modification
                        // force message conversion error propagation
                        catch (ResponseStatusException e) {
                            throw e;
                        }
                        // KOMUNE End Of Modification
```

so each branch reads:

```java
                        try {
                            return this.convertInputIfNecessary(v, actualType == null ? type : actualType);
                        }
                        catch (Exception e) {
                            throw new IllegalStateException("Failed to convert input", e);
                        }
```

Then run:

```bash
./gradlew :f2-spring:function:f2-spring-boot-starter-function-http-webflux:test :f2-spring:function:f2-spring-boot-starter-function-http-mvc:test --tests '*Cucumber*' --rerun-tasks
```

Expected: `BUILD FAILED` — the `Raw: a type-mismatched JSON body is rejected with 400` scenario fails on both runtimes (500 instead of 400). Record it, then restore from `/tmp/SimpleFunctionRegistry.java.bak`.

- [ ] **Step 4: Confirm fully restored and the whole suite is green again**

```bash
cd /Users/adrien/Dev/komune/fixers/fixers-f2
git diff --stat -- f2-spring/function/f2-spring-boot-starter-function/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java
```

Expected: **no output** (file matches the Task 4 commit exactly — if it shows a diff, the restore in one of Steps 1-3 didn't fully apply; re-run `git checkout -- <path>` and re-verify).

```bash
./gradlew test detekt
```

Expected: `BUILD SUCCESSFUL`, same 373/0/0/2 totals as before this task.

- [ ] **Step 5: No commit** — this task only produces evidence (report it in the task's final report: which scenario/count failed in each of the three removal-proofs), it makes no lasting code change.

---

### Task 6: F2 — confirm the other five vendored files need no changes

**Files:**
- Verify only: `ContextFunctionCatalogAutoConfiguration.java`, `JsonMessageConverter.java`, `KotlinLambdaToFunctionAutoConfiguration.java`, `SmartCompositeMessageConverter.java`, `FunctionWebRequestProcessingHelper.java` (all under `f2-spring/function/...`)

**Interfaces:**
- Consumes: Task 2's confirmation that these files are unchanged upstream between v5.0.1 and v5.0.3. Independent of Task 5 — can run in either order relative to it.

Unlike `SimpleFunctionRegistry.java`, these five files need **no edits** — F2's current copies were ported from v5.0.1, and v5.0.1→v5.0.3 made zero changes to any of them (proven in the Global Constraints section and re-confirmed in Task 2, Step 1). This task is a verification-only checkpoint, not a port.

- [ ] **Step 1: Confirm each F2 copy still matches its `fixers/5.0.3` counterpart exactly (content-only)**

```bash
cd /Users/adrien/Dev/komune/fixers/fixers-f2
F2PATHS=(
  "f2-spring/function/f2-spring-boot-starter-function/src/main/java/org/springframework/cloud/function/context/config/ContextFunctionCatalogAutoConfiguration.java"
  "f2-spring/function/f2-spring-boot-starter-function/src/main/java/org/springframework/cloud/function/context/config/KotlinLambdaToFunctionAutoConfiguration.java"
  "f2-spring/function/f2-spring-boot-starter-function-http/src/main/java/org/springframework/cloud/function/context/config/JsonMessageConverter.java"
  "f2-spring/function/f2-spring-boot-starter-function-http/src/main/java/org/springframework/cloud/function/context/config/SmartCompositeMessageConverter.java"
  "f2-spring/function/f2-spring-boot-starter-function-http/src/main/java/org/springframework/cloud/function/web/util/FunctionWebRequestProcessingHelper.java"
)
FORKPATHS=(
  "spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/ContextFunctionCatalogAutoConfiguration.java"
  "spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/KotlinLambdaToFunctionAutoConfiguration.java"
  "spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/JsonMessageConverter.java"
  "spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/SmartCompositeMessageConverter.java"
  "spring-cloud-function-web/src/main/java/org/springframework/cloud/function/web/util/FunctionWebRequestProcessingHelper.java"
)
i=0
while [ $i -lt ${#F2PATHS[@]} ]; do
  f2path="${F2PATHS[$i]}"; forkpath="${FORKPATHS[$i]}"
  n=$(diff -w -B "$f2path" <(git -C /Users/adrien/Dev/komune/fixers/spring-cloud-function show fixers/5.0.3:"$forkpath" | expand -t4) | grep -cE '^[<>]')
  printf "%-55s content-diff=%s (expect 0)\n" "$(basename "$f2path")" "$n"
  i=$((i+1))
done
```

Expected: `0` for `FunctionWebRequestProcessingHelper.java`. The other four (`ContextFunctionCatalogAutoConfiguration.java`, `KotlinLambdaToFunctionAutoConfiguration.java`, `JsonMessageConverter.java`, `SmartCompositeMessageConverter.java`) are known to show small nonzero diffs (2-25 lines) — this is *pre-existing, harmless cosmetic drift* (copyright-header years, one import's position, comment wording, and F2's own kSerialization feature which the fork legitimately lacks) that predates this migration entirely and has nothing to do with v5.0.1→v5.0.3 upstream churn. Confirm this by re-running the same comparison against `fixers/5.0.1` instead of `fixers/5.0.3` — if the counts match, the drift is pre-existing and this task passes. Only escalate ("stop, needs regenerate-and-reapply") if a file's `KOMUNE`-marked region or any executable code differs, not comments/copyright/import-ordering.

- [ ] **Step 2: No commit needed** — this task makes no changes, only confirms Tasks 3-4 didn't miss anything.

---

### Task 7: F2 — full verification and final commit

**Files:**
- None (verification only, plus the plan file itself).

**Interfaces:**
- Consumes: the BOM bump (Task 3), the re-ported file (Task 4), the necessity proof (Task 5), and the no-change confirmation (Task 6) together, as they'd actually ship.

- [ ] **Step 1: Full build, test, and lint**

```bash
cd /Users/adrien/Dev/komune/fixers/fixers-f2
./gradlew test detekt
```

Expected: `BUILD SUCCESSFUL`.

- [ ] **Step 2: Confirm test totals match the pre-migration baseline**

```bash
python3 - <<'EOF'
import glob, xml.etree.ElementTree as ET, collections
tot=collections.Counter()
for p in glob.glob("**/build/test-results/**/*.xml", recursive=True):
    try: r=ET.parse(p).getroot()
    except Exception: continue
    if r.tag!="testsuite": continue
    for k in ("tests","failures","errors","skipped"): tot[k]+=int(r.get(k,0))
print(f"TOTAL: {tot['tests']} tests, {tot['failures']} failures, {tot['errors']} errors, {tot['skipped']} skipped")
EOF
```

Expected: `373 tests, 0 failures, 0 errors, 2 skipped` — identical to the pre-migration count (this migration changes no test-observable behavior; it only gains upstream's own internal improvements).

- [ ] **Step 3: Confirm the fork and F2 are still in sync across all six vendored files**

```bash
cd /Users/adrien/Dev/komune/fixers/fixers-f2
F2PATHS=(
  "f2-spring/function/f2-spring-boot-starter-function/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java"
  "f2-spring/function/f2-spring-boot-starter-function/src/main/java/org/springframework/cloud/function/context/config/ContextFunctionCatalogAutoConfiguration.java"
  "f2-spring/function/f2-spring-boot-starter-function/src/main/java/org/springframework/cloud/function/context/config/KotlinLambdaToFunctionAutoConfiguration.java"
  "f2-spring/function/f2-spring-boot-starter-function-http/src/main/java/org/springframework/cloud/function/context/config/JsonMessageConverter.java"
  "f2-spring/function/f2-spring-boot-starter-function-http/src/main/java/org/springframework/cloud/function/context/config/SmartCompositeMessageConverter.java"
  "f2-spring/function/f2-spring-boot-starter-function-http/src/main/java/org/springframework/cloud/function/web/util/FunctionWebRequestProcessingHelper.java"
)
FORKPATHS=(
  "spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java"
  "spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/ContextFunctionCatalogAutoConfiguration.java"
  "spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/KotlinLambdaToFunctionAutoConfiguration.java"
  "spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/JsonMessageConverter.java"
  "spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/SmartCompositeMessageConverter.java"
  "spring-cloud-function-web/src/main/java/org/springframework/cloud/function/web/util/FunctionWebRequestProcessingHelper.java"
)
i=0
while [ $i -lt ${#F2PATHS[@]} ]; do
  f2path="${F2PATHS[$i]}"; forkpath="${FORKPATHS[$i]}"
  n=$(diff -w -B "$f2path" <(git -C /Users/adrien/Dev/komune/fixers/spring-cloud-function show fixers/5.0.3:"$forkpath" | expand -t4) | grep -cE '^[<>]')
  printf "%-55s %s\n" "$(basename "$f2path")" "$n"
  i=$((i+1))
done
```

Expected: `0` for `SimpleFunctionRegistry.java` and `FunctionWebRequestProcessingHelper.java`. The other four show small nonzero diffs (2-25 lines) — this is confirmed pre-existing, harmless cosmetic drift (copyright years, import order, F2's own kSerialization feature) that predates this migration entirely and is unrelated to it; see Task 6's report for the full analysis and the independent confirmation that these same counts exist against `fixers/5.0.1` too. Do not treat this as a failure.

- [ ] **Step 4: Commit the plan file**

```bash
git add docs/superpowers/plans/2026-08-04-migrate-scf-5.0.3.md
git commit -m "docs: add v5.0.3 migration plan"
```

---

### Task 8: Push (ask first)

**Files:** none.

This task is explicitly gated on user confirmation before running — do not push automatically on completing Task 7.

- [ ] **Step 1: Ask the user which of these they want:**
  - Push `fixers/5.0.3` to the fork's `origin` (mirrors what was done for `fixers/5.0.1`)
  - Push the F2 commits to `origin/test/vendored-scf-fixes-coverage`, updating the already-open draft PR #118 (vs. opening a new branch/PR for the v5.0.3 bump specifically — flagged as a decision in Global Constraints)

- [ ] **Step 2: On confirmation, push**

```bash
cd /Users/adrien/Dev/komune/fixers/spring-cloud-function
git push -u origin fixers/5.0.3   # only if approved

cd /Users/adrien/Dev/komune/fixers/fixers-f2
git push   # only if approved; updates PR #118 in place since the branch is already tracked
```

- [ ] **Step 3: If continuing PR #118, update its description** to mention the v5.0.3 bump (title and body currently say "v5.0.1").

## Coverage Check

| Upstream change (v5.0.1→v5.0.3) | Where handled |
|---|---|
| `SimpleFunctionRegistry`: LRU cache, `equals`/`hashCode`, recursion guard | Task 1 (fork), Task 4 (F2) |
| `pom.xml`: parent version, dependency bump | Task 1/2 (fork, comes free from branching off the tag) |
| 5 other vendored files: no upstream change | Task 2 (fork, verified + KOMUNE carried forward), Task 6 (F2, verified only) |
| F2's resolved SCF version | Task 3 |
| `SimpleFunctionRegistry`'s KOMUNE fixes still load-bearing at v5.0.3 | Task 5 (empirical removal-proof, not just static analysis) |
| Regression risk | Task 7 (full suite + fork/F2 sync check) |

## Out of Scope

- Upgrading past v5.0.3 if a newer release ships before this plan executes — re-run the `git ls-remote --tags` check from this plan's investigation before starting, and adjust the target tag/BOM version if a newer patch release exists.
- Any change to the `F2ErrorWebExceptionHandler` status-masking fix from the prior session — unrelated to the SCF version, already fixed and merged into `test/vendored-scf-fixes-coverage`.
- Removal-proofing the other five vendored files (`JsonMessageConverter`, `SmartCompositeMessageConverter`, `FunctionWebRequestProcessingHelper`, `ContextFunctionCatalogAutoConfiguration`, `KotlinLambdaToFunctionAutoConfiguration`) — Task 5 only covers `SimpleFunctionRegistry`, the one file upstream actually changed; the other five are proven byte-identical to their v5.0.1 state (Task 2/6), so their necessity is unchanged from the prior session's proof and doesn't need re-running.