# fixers-f2 — Context

F2 is the **Function Framework** Komune uses to expose CQRS commands, queries, and events as Spring Cloud Functions over HTTP, with matching multiplatform clients (Ktor JVM / JS). It is the transport + message-shape layer the rest of the stack builds on.

Treat F2 as **CQRS-first**: every public capability in Komune's services is modelled as a Command (state-change), Query (read), or Event (result), and then exposed by F2 as a function with a typed in/out contract.

## Glossary

### F2 Function triad

- **`F2Supplier<R>`** — no-arg producer returning `Flow<R>` (JVM) / `Promise<R>` (JS).
- **`F2Function<T, R>`** — `Flow<T> → Flow<R>` (JVM) / `T → Promise<R>` (JS).
- **`F2Consumer<T>`** — `Flow<T> → Unit` (JVM) / `T → Promise<Unit>` (JS).

These are F2's wire-level function shapes. They are multiplatform (`f2-dsl-function`) and reused by every other Komune library that publishes an HTTP-callable function.

### CQRS message triad

- **Command** — a request to change state.
- **Query** — a request to read state.
- **Event** — the result emitted by a command (also used in event-sourced flows).

Lives in `f2-dsl-cqrs`. Pairing rule: any F2 function exposed to the network has a Command-or-Query input and an Event-or-Result output. The pairing is reused by `s2`, `c2`, `connect-im`, `connect-fs` and rendered as documentation by `d2`.

### F2 Client

The Ktor-based HTTP client family (`f2-client-*`) that consumes F2 functions from the other side of the wire. Multiplatform: same client code runs in JVM apps, JS browser apps (`fixers-g2` consumers), and native targets via Ktor engines.

### F2 Spring

Spring Boot starters (`f2-spring-boot-starter-*`) that mount F2 functions into a host application: `function` (HTTP exposure), `auth` (Keycloak + multi-tenant), `exception` (WebMVC/WebFlux error mapping), `openapi` (Springdoc integration).

### F2 Feature

A drop-in F2 capability module — a Spring Boot autoconfigure starter that mounts a packaged set of F2 functions for a specific capability. Currently shipped:

- `f2-feature-catalog` — catalog browsing
- `f2-feature-version` — version-info endpoint
- `f2-feature-cloud-event-storming` — event-storming visualization

A Feature is **not** a Cucumber `.feature` file. (BDD lives in `f2-bdd`.)

### F2 BDD

Cucumber + Spring autoconfigure helpers used to write BDD tests against F2 functions. Separate concern from F2 Feature. Artifacts: `f2-bdd-config`, `f2-bdd-spring-autoconfigure`, `f2-bdd-spring-lambda`.

### F2 Gradle (BOM)

The `io.komune.fixers.f2.bom` Gradle plugin + `f2-gradle-catalog` BOM that consumers apply to align their F2 artifact versions. Note: distinct from `fixers-gradle` — F2 publishes its **own** version-catalog plugin because it is itself a producer of multiplatform artifacts. See ADR-0001.

## Cross-references

- Layering and consumers: [../../CONTEXT-MAP.md](../../CONTEXT-MAP.md)
- Why F2 must not depend on S2: [../../docs/adr/0001-submodule-dependency-layers.md](../../docs/adr/0001-submodule-dependency-layers.md)
- The `Command` / `Query` / `Event` terms are also referenced by [../fixers-s2/CONTEXT.md](../fixers-s2/CONTEXT.md), [../fixers-c2/CONTEXT.md](../fixers-c2/CONTEXT.md), [../../connect/connect-im/CONTEXT.md](../../connect/connect-im/CONTEXT.md), [../../connect/connect-fs/CONTEXT.md](../../connect/connect-fs/CONTEXT.md).
