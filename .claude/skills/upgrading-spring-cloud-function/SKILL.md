---
name: upgrading-spring-cloud-function
description: Use when bumping F2's spring-cloud-function version, when a KOMUNE-patched vendored file (SimpleFunctionRegistry, JsonMessageConverter, SmartCompositeMessageConverter, FunctionWebRequestProcessingHelper, ContextFunctionCatalogAutoConfiguration, KotlinLambdaToFunctionAutoConfiguration, CoroutinesUtils) needs updating, or when syncing komune-io/spring-cloud-function fork branches (fixers/<version>) with upstream spring-cloud/spring-cloud-function releases.
---

# Upgrading spring-cloud-function

## Overview

F2 vendors (shadows by package/path) 7 files from `spring-cloud-function` — 6 Java files plus `CoroutinesUtils.kt` — each carrying custom patches marked `// KOMUNE Modification` … `// KOMUNE End Of Modification` (`CoroutinesUtils.kt` uses `//KOMUNE Changes Start` … `//KOMUNE Changes End`). A sibling repo, `komune-io/spring-cloud-function`, carries the identical patch on branches named `fixers/<version>` (e.g. `fixers/5.0.3`) off the matching upstream tag — this is the fork PR upstream would see, kept in sync with F2's vendored copies.

Upgrading means: find the new tag, check which vendored files actually changed upstream, reapply only the KOMUNE hunks that survive (not a wholesale merge), and verify the patches are still load-bearing — in both repos, kept in sync.

## Setup

This skill lives inside the F2 repo, so all F2-side commands below just run from the current repo root — no variable needed for it.

The other checkout, the local clone of `komune-io/spring-cloud-function`, is machine-specific — never hardcode it. Set `$SPRING_CLOUD_FUNCTION_REPO` from the skill's `args` if supplied (e.g. `fork_repo=/path/to/spring-cloud-function`); if not supplied, ask the user for the path before running anything in step 3 onward. Every command below that touches the fork uses `$SPRING_CLOUD_FUNCTION_REPO` — don't substitute a literal path into the commands or into anything you write back to this file.

## The patches (current state — recheck after any upgrade, this drifts)

| File | Patch | Necessity (last verified) |
|---|---|---|
| `SimpleFunctionRegistry.java` | Typed `List<T>` collection deserialization (kotlinx.serialization needs the element type) | Necessary — 22+ test failures if removed |
| `SimpleFunctionRegistry.java` | Rethrow `ResponseStatusException` unwrapped on **input** conversion | Necessary — the only way a malformed body surfaces as 400 |
| `SimpleFunctionRegistry.java` | Same rethrow on **output** conversion | **Dead code as of v5.0.3** — nothing constructs `ResponseStatusException` on the output path anymore. Recheck each upgrade; don't assume it stays dead. |
| `JsonMessageConverter.java` | `DatabindException` → `ResponseStatusException(400)` when Content-Type is JSON | Necessary. Content-Type match is case-insensitive across `MessageHeaders.CONTENT_TYPE`/`Content-Type`/`content-type` — matches `SimpleFunctionRegistry.contentTypeHeaderValue`'s pattern. Don't regress to a single-key exact-match check. |
| `SmartCompositeMessageConverter.java` | Rethrow `ResponseStatusException` instead of swallow-and-try-next-converter | Necessary (upstream issue #901) — part of the chain above |
| `FunctionWebRequestProcessingHelper.java` | `OPTIONS` → `null` (falls through to normal routing instead of a 500) | Necessary |
| `FunctionWebRequestProcessingHelper.java` | Drop `onErrorContinue` on the result stream | Necessary — but only reproduces with a fixture that fails *inside* an operator (e.g. `.map()`); a source that throws directly doesn't exercise it at all, `onErrorContinue` can't intercept a source-terminal error |
| `KotlinLambdaToFunctionAutoConfiguration.java` | Relaxed suspend supplier/consumer/function arity detection + supertype-aware matching | Necessary — F2's DSL types erase to fewer Java arities than upstream's detector expects |
| `ContextFunctionCatalogAutoConfiguration.java` | `kSerialization` JsonMapper branch | F2-specific; the fork keeps it commented out (can't depend on F2's `f2.spring.KSerializationMapper`) — never try to make this identical between the two repos |
| `CoroutinesUtils.kt` | `invokeSuspendingSupplier` accepts a supplier returning a plain value, not only a `Flow` (wraps it with `flowOf`) | Necessary — `F2Supplier<T>` erases to a lambda returning the value itself; upstream's `suspendCoroutineUninterceptedOrReturn<Flow<Any>>` blows up on it |
| `CoroutinesUtils.kt` | `require(...)` argument checks in `invokeSuspendingFunction`/`Consumer`/`Supplier` instead of upstream's blind casts | Defensive — turns a bare `ClassCastException` from a mis-wired bean into a readable message |
| `CoroutinesUtils.kt` | `typealias` made public (upstream: `private typealias`) + `@Suppress("TooManyFunctions")`/`@Suppress("ReturnCount")` | Build-only — detekt runs over F2's copy, upstream's build doesn't |

Also: `spring-cloud-function-context/pom.xml` (fork only) comments out `spring-web`'s `<optional>true</optional>` — needed because `ResponseStatusException` lives in `spring-web`.

## Procedure

### 1. Find the target

```bash
git ls-remote --tags https://github.com/spring-cloud/spring-cloud-function.git | grep -oE 'v[0-9]+\.[0-9]+\.[0-9]+$' | sort -V | tail
```

Confirm F2's *actual resolved* version first — don't trust the declared property:
```bash
./gradlew :f2-spring:function:f2-spring-boot-starter-function:dependencies --configuration compileClasspath | grep spring-cloud-function
```

### 2. Find which BOM version resolves the target, and what else moves

F2 imports `spring-cloud-dependencies` (Spring Cloud's release-train BOM) in `f2-gradle/f2-gradle-bom/build.gradle.kts`, version pinned at `gradle/libs.versions.toml`'s `spring-cloud` property. Check each candidate BOM release's POM directly:

```bash
curl -fsSL "https://repo1.maven.org/maven2/org/springframework/cloud/spring-cloud-dependencies/<BOM-VERSION>/spring-cloud-dependencies-<BOM-VERSION>.pom" -o /tmp/scd-a.pom
curl -fsSL "https://repo1.maven.org/maven2/org/springframework/cloud/spring-cloud-dependencies/<OTHER-BOM-VERSION>/spring-cloud-dependencies-<OTHER-BOM-VERSION>.pom" -o /tmp/scd-b.pom
grep spring-cloud-function.version /tmp/scd-a.pom /tmp/scd-b.pom
/usr/bin/diff /tmp/scd-a.pom /tmp/scd-b.pom
```
`-f` makes a 4xx/5xx response fail the command instead of silently writing an error page into the `.pom` file.

**Always `diff` real files on disk, never `diff <(curl ...) <(curl ...)`.** In this sandboxed environment, process-substitution diffs — and separately, `diff` itself if shadowed by an `rtk` hook — have both produced false "files are identical" results on genuinely different inputs, twice, in the same session. This isn't a style preference; trusting it once already shipped a wrong claim into a commit message. If you must use `diff`, verify with `/usr/bin/diff` explicitly or by checking file sizes/specific properties with `grep` too.

Don't assume only `spring-cloud-function.version` moves — check every `*.version` property in the diff, and specifically `spring-boot.version`: if the BOM's Spring Boot version is higher than F2's own declared `spring-boot` in `libs.versions.toml`, Gradle's constraint resolution silently picks the BOM's (highest wins), leaving the declared pin dishonest. Verify with the `:dependencies` command from step 1, looking for a `X -> Y (c)` override arrow, and bump the declared pin to match if so. This only affects the `spring-boot` *library* version (a dependency constraint); the `org.springframework.boot` Gradle *plugin* version is a separate catalog entry resolved independently — bump it too, but don't assume moving one moves the other.

### 3. Diff every vendored file, current base tag → target tag

```bash
git -C "$SPRING_CLOUD_FUNCTION_REPO" fetch origin --tags 'refs/heads/fixers/*:refs/remotes/origin/fixers/*'
FILES=(
  "spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/catalog/SimpleFunctionRegistry.java"
  "spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/ContextFunctionCatalogAutoConfiguration.java"
  "spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/JsonMessageConverter.java"
  "spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/KotlinLambdaToFunctionAutoConfiguration.java"
  "spring-cloud-function-context/src/main/java/org/springframework/cloud/function/context/config/SmartCompositeMessageConverter.java"
  "spring-cloud-function-context/src/main/kotlin/org/springframework/cloud/function/context/config/CoroutinesUtils.kt"
  "spring-cloud-function-web/src/main/java/org/springframework/cloud/function/web/util/FunctionWebRequestProcessingHelper.java"
)
for f in "${FILES[@]}"; do
  n=$(/usr/bin/diff -w -B <(git -C "$SPRING_CLOUD_FUNCTION_REPO" show <OLD-TAG>:"$f") \
                  <(git -C "$SPRING_CLOUD_FUNCTION_REPO" show <NEW-TAG>:"$f") | grep -cE '^[<>]')
  echo "$(basename "$f") churn=$n"
done
```

(Process substitution is fine here — it's real-file-vs-real-file `git show` output, not the network-fetch case that misbehaved above; still worth spot-checking any surprising "0" against `diff /path/a /path/b` on saved files if the result matters.)

Also check both `pom.xml` files the same way — the fork's KOMUNE pom edit lives at `spring-cloud-function-context/pom.xml`.

For each file with nonzero churn, don't just check line-range overlap with the current KOMUNE markers — identify which *method* each upstream hunk lands in (`grep -n` around the diff's line numbers, read the surrounding function signature). Line ranges shift release to release; the method a hunk belongs to doesn't.

### 4. Reapply — regenerate-fresh, don't patch-merge

For a file with real upstream churn: extract the file fresh at the new tag, then reapply each KOMUNE hunk as an exact-text edit (never `patch`/`git apply` the old diff onto new content — upstream context around the hunk may have shifted even without touching the KOMUNE region itself).

```bash
git -C "$SPRING_CLOUD_FUNCTION_REPO" show <NEW-TAG>:"$f" > /tmp/fresh.java
```
Then apply each hunk from the table above (or from the file's current KOMUNE-marked regions) as a find-and-replace against `/tmp/fresh.java`, verify the result's content-only diff against the *old* KOMUNE-patched version shows exactly the upstream hunks and nothing else, then commit.

For a file with zero churn: just carry the existing KOMUNE-patched content forward unchanged (`git show origin/fixers/<old-version>:"$f" > "$f"`, using the remote-tracking ref fetched in step 3 — a plain `<old-branch>` name isn't guaranteed to exist locally) — safe *only* because you already confirmed zero upstream drift in step 3, not merely "it should be fine."

Fork uses tabs; F2 uses 4-space indentation. When porting the fork's finished file into F2, `expand -t4`.

### 5. Re-verify necessity, don't just assume prior conclusions still hold

For at least the file(s) that had real upstream churn, removal-proof each KOMUNE hunk against the new base: temporarily strip it, run the affected test suite, and record the result. If something fails, restore the hunk and confirm green again — still necessary. If tests stay green, the hunk is dead code against this base (as already happened once, see below) — remove it rather than restoring it. A prior "necessary" conclusion from an older base doesn't automatically transfer — this session found one previously-necessary hunk (`SimpleFunctionRegistry`'s output-side rethrow) had quietly become dead code, invisible from a static line-overlap argument alone, only caught by actually removing it and getting `BUILD SUCCESSFUL`.

### 6. Build/test both repos

Fork (Maven), run from `$SPRING_CLOUD_FUNCTION_REPO`. `repo.spring.io/release` 401s on some artifacts in this environment — always mirror to Central first:
```bash
cat > /tmp/settings-central.xml <<'EOF'
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0">
  <mirrors><mirror><id>central-only</id><name>Central mirror</name>
    <url>https://repo1.maven.org/maven2</url><mirrorOf>*</mirrorOf></mirror></mirrors>
</settings>
EOF
find ~/.m2/repository -name "*.lastUpdated" -delete
cd "$SPRING_CLOUD_FUNCTION_REPO"
./mvnw -s /tmp/settings-central.xml -B -pl spring-cloud-function-context,spring-cloud-function-web test
```
F2 (Gradle), run from this repo's root: `./gradlew test detekt`.

### 7. Confirm the two repos are still in sync — with the right expectation

```bash
/usr/bin/diff -w -B "<f2-path>" <(git -C "$SPRING_CLOUD_FUNCTION_REPO" show origin/fixers/<version>:"<fork-path>" | expand -t4)
```
Expect **0** for `SimpleFunctionRegistry.java` and `FunctionWebRequestProcessingHelper.java`. `CoroutinesUtils.kt` is out of scope for this check entirely: as of `fixers/5.0.3` the fork carries upstream's copy verbatim (no KOMUNE markers), so F2's version diverges heavily *by design* — diff it against **upstream** rather than the fork. The other four (`ContextFunctionCatalogAutoConfiguration.java`, `KotlinLambdaToFunctionAutoConfiguration.java`, `JsonMessageConverter.java`, `SmartCompositeMessageConverter.java`) legitimately show small nonzero diffs even when fully in sync — copyright-header years, one import's position, and F2's own `kSerialization` feature that the fork can't have. Don't treat those as a failure signal; confirm they're *pre-existing* by diffing the same files against the *previous* fork branch too — if the counts match, nothing regressed.

### 8. Branch, commit, push — only on explicit confirmation

Fork: `git checkout -b fixers/<new-version> v<new-version>`, commit the patch. F2: continue on whatever branch/PR is already tracking this vendoring work rather than opening a second one about the same files, unless told otherwise. Push nothing without being asked — this is a cheap, easily-reversed-if-wrong step to gate explicitly.

## Common mistakes

- **Trusting a network-fetched `diff <(...) <(...)`** — verified false-negative twice in one session (once during initial BOM investigation, once during "independent" re-verification of the same claim). Save to real files and diff those.
- **Assuming a hunk's necessity carries over** from the last time it was checked — re-verify by removal-proof against the new base, don't just cite the old table.
- **Treating the fork/F2 sync-check's expected nonzero files as a regression** — 4 of the 6 Java files have permanent, harmless cosmetic drift; only `SimpleFunctionRegistry.java` and `FunctionWebRequestProcessingHelper.java` should ever be byte-identical, and `CoroutinesUtils.kt` isn't mirrored in the fork at all.
- **Forgetting `CoroutinesUtils.kt` is vendored** — it's Kotlin, it lives under `src/main/kotlin`, it has no fork counterpart, and it uses a different marker style (`//KOMUNE Changes Start`/`End`), so every "the 6 Java files" shortcut silently skips it.
- **zsh array indexing** — a paired-array verification loop written assuming 0-indexed arrays can misbehave under zsh's default 1-indexed arrays. Prefer a single array of `"a::b"` pairs split with `${x%%::*}`/`${x##*::}` over two parallel indexed arrays, or just run under `bash` explicitly.
- **Forgetting the `spring-web` pom.xml hunk** on the fork side — it's easy to miss since it's not a `.java` file and doesn't show up in a vendored-file-only diff sweep.
