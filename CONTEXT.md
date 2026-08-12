# fixers-f2 — Context

F2 is the **Function Framework** Komune uses to expose CQRS commands, queries, and events as Spring Cloud Functions over HTTP, with matching multiplatform clients (Ktor JVM / JS). It is the transport + message-shape layer the rest of the stack builds on.

Treat F2 as **CQRS-first**: every public capability in Komune's services is modelled as a Command (state-change), Query (read), or Event (result), and then exposed by F2 as a function with a typed in/out contract.

## Glossary

### F2 Function triad

- **`F2Supplier<R>`** — no-arg producer: `suspend () -> Flow<R>`.
- **`F2Function<T, R>`** — transformer: `suspend (Flow<T>) -> Flow<R>`.
- **`F2Consumer<T>`** — sink: `suspend (Flow<T>) -> Unit`.

All three are Kotlin `fun interface`s declared once in common code (`f2-dsl-function`, `f2.dsl.fnc`) over kotlinx-coroutines `Flow` — there are no platform-specific (Promise-based) variants; the same `Flow` signatures are exported to JS via `@JsExport`. A fourth shape, **`F2SupplierSingle<R>`** (`suspend () -> R`), supplies a single value instead of a stream.

These are F2's wire-level function shapes, reused by every other Komune library that publishes an HTTP-callable function.

### CQRS message triad

- **`Command`** — a request to change state.
- **`Query`** — a request to read state.
- **`Event`** — the result emitted by a command (also used in event-sourced flows).

Lives in `f2-dsl-cqrs` (`f2.dsl.cqrs`); all three are marker interfaces extending the root marker **`Message`**. Pairing rule: any F2 function exposed to the network has a Command-or-Query input and an Event-or-Result output. The pairing is reused by `s2`, `c2`, `connect-im`, `connect-fs` and rendered as documentation by `d2`.

The module also ships the standard paged-read contract: `PageQuery` (a `Query` carrying an `OffsetPagination`) paired with `PageQueryResult<T>` (an `Event` that is also a `PageDTO` — `total` + `items`).

_Avoid_: `Cmd`, `Qry`, `Evt` — the markers are spelled out in full in the code.

### Envelope

The CloudEvents-shaped wire wrapper for a single message (`f2-dsl-cqrs`, `f2.dsl.cqrs.envelope`): `Envelope<T>` carries a required `id`, the `data` payload, a required `type`, and optional `datacontenttype` / `specversion` / `source` / `time`. Built via the `asEnvelope` / `asEnvelopeWithType` extensions and the `mapToEnvelope` / `mapEnvelopes` flow operators (`f2-dsl-function`); `f2-dsl-function` also aliases enveloped function shapes as `F2FunctionEnveloped<T, R>`, `F2SupplierEnveloped<R>`, `F2ConsumerEnveloped<T>` and `EnvelopedFlow<T>` (= `Flow<Envelope<T>>`).

### CloudEvent / SnapEvent

`f2-dsl-event` — the third DSL module — defines `CloudEvent<T>`, a multiplatform class following the CloudEvents attribute set (`eventType`, `cloudEventsVersion`, `source`, `eventID`, `eventTime`, `schemaURL`, `contentType`, `extensions`, `data`), and `SnapEvent<T>`, a `CloudEvent` subtype marking a state snapshot. Consumed by `f2-feature-cloud-event-storming`. Distinct from both the CQRS `Event` marker and `Envelope` (the per-message wire wrapper in `f2-dsl-cqrs`).

### F2 Client

The Ktor-based HTTP client family (`f2-client-core`, `f2-client-domain`, `f2-client-ktor`) that consumes F2 functions from the other side of the wire. Multiplatform: same client code runs in JVM apps (Ktor Java engine) and JS apps (`fixers-g2` consumers, Ktor JS engine). Only JVM and JS targets are published — no native targets.

### F2 Spring

Spring Boot modules (`f2-spring/`) that mount F2 functions into a host application, grouped in four families: `function` (`f2-spring-boot-starter-function`, `-function-http`, `-function-http-mvc`, `-function-http-webflux`, plus `f2-spring-boot-starter-observability-opentelemetry`), `auth` (`f2-spring-boot-starter-auth`, `-auth-commons`, `-auth-keycloak`, `-auth-tenant` — Keycloak + multi-tenant), `exception` (`f2-spring-boot-exception-http`, `-http-mvc`, `-http-webflux` — WebMVC/WebFlux error mapping), `openapi` (`f2-spring-boot-openapi`, `-mvc`, `-webflux` — Springdoc integration). Note the exception and openapi artifacts are not named `starter`.

### F2 Feature

A drop-in F2 capability module — a Spring Boot autoconfigure starter that mounts a packaged set of F2 functions for a specific capability. Currently shipped:

- `f2-feature-catalog` — exposes the names of the functions/suppliers/consumers registered in the Spring Cloud `FunctionCatalog`
- `f2-feature-version` — `version` / `name` endpoints backed by Spring Boot `BuildProperties`
- `f2-feature-cloud-event-storming` — persists received `CloudEvent`s and serves them back for event-storming visualization

A Feature is **not** a Cucumber `.feature` file. (BDD lives in `f2-bdd`.)

### F2 BDD

Cucumber + Spring autoconfigure helpers used to write BDD tests against F2 functions. Separate concern from F2 Feature. Artifacts: `f2-bdd-config`, `f2-bdd-spring-autoconfigure`, `f2-bdd-spring-lambda`.

### F2 Gradle (BOM)

Consumer-side version alignment, three artifacts under `f2-gradle/`: `f2-gradle-bom` (a `java-platform` BOM, `io.komune.f2:f2-gradle-bom`, constraining all F2 modules and their upstream BOMs), `f2-gradle-catalog` (a published Gradle version catalog), and `f2-gradle-plugin` (the `io.komune.fixers.f2.bom` convention plugin, which applies the BOM platform to every Kotlin subproject). Note: distinct from `fixers-gradle`, which provides the build-time convention plugins F2 itself builds with.

## Boundary notes

- `f2-spring-boot-starter-function` and `-function-http` vendor **patched copies of Spring Cloud Function classes** (under `org.springframework.cloud.function.*`: `SimpleFunctionRegistry`, `ContextFunctionCatalogAutoConfiguration`, `KotlinLambdaToFunctionAutoConfiguration`, `CoroutinesUtils`, `JsonMessageConverter`, `SmartCompositeMessageConverter`, `FunctionWebRequestProcessingHelper`). These override the upstream jars on the classpath; they are an implementation detail of F2's function runtime, not part of its published vocabulary.
- F2 defines the function/message contract only — no state machines (that is `s2`, which depends on F2, never the reverse; see ADR-0001).

## Cross-references

- Layering and consumers: [../../CONTEXT-MAP.md](../../CONTEXT-MAP.md)
- Why F2 must not depend on S2: [../../docs/adr/0001-submodule-dependency-layers.md](../../docs/adr/0001-submodule-dependency-layers.md)
- The `Command` / `Query` / `Event` terms are also referenced by [../fixers-s2/CONTEXT.md](../fixers-s2/CONTEXT.md), [../fixers-c2/CONTEXT.md](../fixers-c2/CONTEXT.md), [../../connect/connect-im/CONTEXT.md](../../connect/connect-im/CONTEXT.md), [../../connect/connect-fs/CONTEXT.md](../../connect/connect-fs/CONTEXT.md).
