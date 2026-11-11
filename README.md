# Fixers F2

F2 is a framework built using the Kotlin programming language that is built on top of [Spring Cloud Function](https://github.com/spring-cloud/spring-cloud-function) for the server side and [ktor](https://ktor.io/) for multiplatform client. It aims to make it easier to develop Command and Query Responsibility Segregation (CQRS) based applications.

## Overview

The goal of F2 is to help developers build event-driven, scalable, and resilient systems that are easy to understand and maintain. It provides tools and abstractions to handle the different parts of a CQRS-based architecture.

The Command and Query Responsibility Segregation (CQRS) architecture is a pattern that separates the responsibilities of handling commands, which change the state of an application, and queries, which retrieve information from the application.

HTTP is a widely-used protocol for sending and receiving information over the internet, and is a popular choice for building RESTful web services. By supporting HTTP, F2 and its client can easily integrate with a wide range of other technologies and services that use HTTP for communication.

## Requirements

- JDK 17+
- Docker (for Storybook/documentation)
- Node.js & Yarn (managed by Gradle for Storybook)

## Project Structure

The project is organized into several modules:

- **f2-bom**: Bill of Materials (BOM) that manages versions for all F2 modules and upstream dependencies (Spring Boot, Ktor, coroutines, etc.).
- **f2-version-catalog**: Published Gradle version catalog (`f2.versions.toml`) providing library aliases, plugin aliases, and the BOM reference for consumer projects.
- **f2-bdd**: Configuration and utilities for Behavior Driven Development (Cucumber).
- **f2-client**: Multiplatform clients (Ktor based) for HTTP.
- **f2-dsl**: Domain Specific Languages for Functions, CQRS, and Events.
- **f2-spring**: Spring Boot starters and integrations (Auth, Exception, OpenAPI, etc.).
- **f2-feature**: Reusable feature modules (Catalog, Version, etc.).
- **sample**: Example applications demonstrating F2 usage.
- **storybook**: Documentation and UI components.

## Using F2 in your project

### Setup

Add the F2 version catalog and BOM in your `settings.gradle.kts`:

```kotlin
dependencyResolutionManagement {
    versionCatalogs {
        create("f2") {
            from("io.komune.f2:f2-gradle-catalog:${VERSION}")
        }
    }
}
```

Apply the BOM to all subprojects in your root `build.gradle.kts`:

```kotlin
subprojects {
    pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
        dependencies {
            "api"(platform(f2.bom))
        }
    }
    pluginManager.withPlugin("org.jetbrains.kotlin.multiplatform") {
        dependencies {
            "commonMainApi"(platform(f2.bom))
        }
    }
}
```

### Using plugins

The version catalog provides plugin aliases with compatible versions:

```kotlin
plugins {
    alias(f2.plugins.fixers.gradle.config)
    alias(f2.plugins.fixers.gradle.check)
    alias(f2.plugins.kotlin.spring) apply false
    alias(f2.plugins.spring.boot) apply false
}
```

### Adding dependencies

With the BOM applied, F2 dependencies require no explicit version:

```kotlin
dependencies {
    implementation(f2.dsl.function)
    implementation(f2.dsl.cqrs)
    implementation(f2.spring.boot.starter.function.http.mvc)
}
```

Third-party dependencies managed by the BOM (Spring Boot, Ktor, coroutines, etc.) also resolve their versions automatically.

## Development

### Setup

Clone the repository:
```bash
git clone https://github.com/komune-io/fixers-f2.git
cd fixers-f2
```

### Scripts

The project uses `make` and `gradle` for build operations.

> **Note:** The `Makefile` in the root currently points to `libs.mk` and `docs.mk`. Use `make -f make_libs.mk <target>` or `make -f make_docs.mk <target>` if the default `make` command fails.

| Command | Description |
| --- | --- |
| `./gradlew build` | Build the project. |
| `./gradlew test` | Run unit tests. |
| `./gradlew detekt` | Run code linting. |
| `make -f make_libs.mk build` | Build libraries (clean, build, publishToMavenLocal). |
| `make -f make_libs.mk test` | Run tests via Make. |
| `make -f make_libs.mk lint` | Run linter via Make. |
| `make -f make_docs.mk build-storybook` | Build Storybook documentation. |

### Environment Variables

| Variable | Description |
| --- | --- |
| `VERSION` | Project version (default: `experimental-SNAPSHOT`). |
| `FIXERS_PUBLISH_NPM_GITHUB_TOKEN` | Auth token for NPM/Yarn against GitHub Packages (required for Storybook publishing). Renamed from `NPM_AUTH_TOKEN`. |

## License

This project is licensed under the Apache 2.0 License - see the [LICENSE](LICENSE) file for details.

---

# Usage Guide

# Dependencies

## Spring Cloud Function

Spring Cloud Function provides a functional programming model for building cloud-native applications, 
enabling you to write simple, single-purpose functions that can be easily composed and deployed in a cloud environment. 
It allows you to take advantage of the many features of the Spring framework, such as data access, transaction management, 
security, and more, while still enjoying the scalability and cost-effectiveness of a serverless architecture. 
It supports multiple communication protocols, including HTTP and gRPC, and the choice of protocol
will depend on the specific requirements of your application and the type of function being written.

## Ktor
F2 provides a multiplatform client using the Ktor client for HTTP.
The Ktor client is a high-performance HTTP client for the Kotlin language that makes it easy to perform HTTP requests
from your Kotlin applications. It supports both synchronous and asynchronous programming,
and provides a wide range of features for working with HTTP, including connection pooling,
request and response streaming, and automatic decompression of response bodies.

# Dsl

## Lambda
In Java, a Function, Supplier, and Consumer are functional interfaces that are part of the Java 8 functional programming API. 
They are used to represent functions that take one or more arguments, produce a result, or consume a value, respectively.
These functional interfaces can be used to implement functional programming concepts such as higher-order functions and lambda expressions, making it easier to write concise, readable, and maintainable code. For example, you could use a Function to map a list of integers to a list of strings, or a Consumer to iterate over a list of values and perform an action on each one. By using these functional interfaces, you can write code that is more functional and less imperative, resulting in a more declarative and maintainable codebase.


### Dependency
* gradle
```gradle
implementation("io.komune.f2:f2-dsl-function:${Versions.f2}")
```
* maven
```
<dependency>
  <groupId>io.komune.f2</groupId>
  <artifactId>f2-dsl-function</artifactId>
  <version>${Versions.f2}</version>
</dependency>
```


### F2Supplier

A `F2Supplier` does not take any input and produces a result. 
For example, you might write a Supplier that returns a random number. 
The get method of a Supplier returns the result.

```kotlin
fun interface F2Supplier<R> : suspend () -> Flow<R> {
	override suspend operator fun invoke(): Flow<R>
}
```

### F2Function
A `F2Function` takes one or more inputs and produces a result. 
For example, you might write a Function that takes an Integer and returns a String. 
The apply method of a Function takes the input and returns the result.

```kotlin
fun interface F2Function<T, R>: suspend (Flow<T>) -> Flow<R> {
  override suspend operator fun invoke(msgs: Flow<T>): Flow<R>
}
```
### F2Consumer

A `F2Consumer` takes a value as input and does not produce a result. 
For example, you might write a Consumer that prints a message to the console. 
The accept method of a Consumer takes the input and performs an action, 
but does not return a value.

```kotlin
fun interface F2Consumer<T>: suspend (Flow<T>) -> Unit {
  override suspend operator fun invoke(msg: Flow<T>)
}
```

### From JS

The three interfaces are Kotlin API, not exported: a `Flow` and a `suspend` call have no JS form. A JS caller
reaches an F2 server through the JS client of `f2-client-ktor-http`, whose functions are JS functions of JSON
messages to a promise of JSON results (`Json<T>` is the JSON of `T`, declared at the head of the contract):

```ts
import { httpF2JsClient } from "fixers-f2-f2-client-f2-client-ktor-f2-client-ktor-http"; // the package under ts/
import type { HttpF2JsFailure, Json, PageQuery, PageQueryResult } from "fixers-f2-f2-client-f2-client-ktor-f2-client-ktor-http";

const client = httpF2JsClient("https://f2.example");
const itemPage = client.function<PageQuery, PageQueryResult<Item>>("itemPage"); // (msgs: Json<PageQuery>[]) => Promise<Json<PageQueryResult<Item>>[]>
try {
  const [page] = await itemPage([{ pagination: { offset: 0, limit: 10 } }]);
  const items: Json<Item>[] = page.items;
} catch (e) {
  const failure = e as Json<HttpF2JsFailure>; // an Error carrying kind, message, status, error
  if (failure.kind === "F2") console.log(failure.error?.refusal?.parameters["id"]);
}
client.close();
```

`client.supplier<R>(route)` is a `() => Promise<Json<R>[]>`, `client.consumer<Q>(route)` a
`(msgs: Json<Q>[]) => Promise<void>`. Import values (`httpF2JsClient`) from the package that declares them, and the
rest with `import type`: a package's `.d.mts` declares its whole program, its `.mjs` exports only its own.

- **Type arguments**: always give them. TypeScript cannot require them on what Kotlin emits; without them a function
  takes any message and its results are `unknown`.
- **Messages** are plain JSON, an array of them: an array is required, and an instance of a class at any depth (an
  exported Kotlin object, a `Date`) rejects with a `TypeError` before any request.
- **Failures** reject with an `Error` carrying `Json<HttpF2JsFailure>`, no Kotlin object:

  | `kind` | When | `status` | `error` |
  |---|---|---|---|
  | `F2` | the server answered an error status: a refusal, a `500` with a text body (its text is `error.message`), a `404` with an empty body (`error.message` is `""`) | the status | the F2 error as JSON: `refusal.parameters` an object, `refusal.citers` an array |
  | `NETWORK` | no response: the server is unreachable or closed the connection | `null` | `null` |
  | `UNREADABLE` | a success status whose body is no JSON | the status | `null` |
  | `CLOSED` | the client was closed before or during the call | `null` | `null` |

- **Cancellation**: `client.function(route, signal)` (and `supplier`, `consumer`) take an optional `AbortSignal`;
  an aborted call rejects with the signal's reason, as `fetch` does. `client.close()` ends every pending call
  (`CLOSED`) and releases the connections. `AbortSignal` is the platform's: the DOM library or Node's types.

## CQRS

[Command Query Responsibility Segregation](https://martinfowler.com/bliki/CQRS.html)
is a pattern that performs segregate operations that read data from operations that update data by using separate interfaces.

The package `f2-dsl-cqrs` provide kotlin multplatform interfaces for `f2.dsl.cqrs.Event`, `f2.dsl.cqrs.Command`, `f2.dsl.cqrs.Query`
It also interface for querying a page o result `f2.dsl.cqrs.PageRequest` and `Pagef2.dsl.cqrs`

### Dependency
* gradle
```gradle
implementation("io.komune.f2:f2-dsl-cqrs:${Versions.f2}")
```
* maven
```
<dependency>
  <groupId>io.komune.f2</groupId>
  <artifactId>f2-dsl-cqrs</artifactId>
  <version>${Versions.f2}</version>
</dependency>
```

### Message
```kotlin
interface Message
interface Event: Message
interface Command: Message
interface Query : Message
```

### Pagination
```kotlin
sealed interface Pagination

class OffsetPagination(
  override val offset: Int,
  override val limit: Int,
) : OffsetPaginationDTO, Pagination

interface OffsetPaginationDTO {
	val offset: Int
	val limit: Int
}

class PagePagination(
  override val page: Int?,
  override val size: Int?
): PagePaginationDTO, Pagination

interface PagePaginationDTO {
	val page: Int?
	val size: Int?
}
```

### Page 
```kotlin
interface PageDTO<out OBJECT> {
	val total: Int
	val items: List<OBJECT>
}

class Page<out OBJECT>(
	override val total: Int,
	override val items: List<OBJECT>,
) : PageDTO<OBJECT>

```

### Page Query Function

```kotlin
interface PageQueryDTO : Query {
	val pagination: OffsetPaginationDTO?
}

interface PageQueryResultDTO<out OBJECT> : Event, PageDTO<OBJECT> {
	override val total: Int
	override val items: List<OBJECT>
	val pagination: OffsetPaginationDTO?
}

class PageQuery(
	override val pagination: OffsetPaginationDTO?,
) : PageQueryDTO

class PageQueryResult<out OBJECT>(
	override val pagination: OffsetPagination?,
	override val total: Int,
	override val items: List<OBJECT>,
) : PageQueryResultDTO<OBJECT>

```

### Page Extension
 * Multiplatform
```kotlin
fun <OBJECT> OffsetPaginationDTO.result(items: List<OBJECT>, total: Int): PageQueryResultDTO<OBJECT>
inline fun <T, reified R> PageDTO<T>.map(transform: (T) -> R): PageDTO<R>
inline fun <T, reified R> Page<T>.map(transform: (T) -> R): Page<R>
```

 * Jvm
```kotlin
inline fun <T, reified R: Any> PageDTO<T>.mapNotNull(transform: (T) -> R?): PageDTO<R>
inline fun OffsetPagination?.toPageRequest(): PageRequest
```

### Function Extension

```kotlin

fun <T, R> f2Function(fnc: suspend (t: T) -> R): F2Function<T, R>
suspend operator fun <T, R> F2Function<T, R>.invoke(t: T): R
suspend fun <T, R> T.invokeWith(f2: F2Function<T, R>): R

fun <R> f2Supplier(fnc: suspend () -> Flow<R>): F2Supplier<R>
fun <R> f2SupplierSingle(fnc: suspend () -> R): F2Supplier<R>
fun <R> Iterable<R>.asF2Supplier(): F2Supplier<R>

fun <R> f2Consumer(fnc: suspend (R) -> Unit): F2Consumer<R>
```

# Spring

F2 provide basic configuration for spring cloud function

## Dependency

### Dependency to implement function
* gradle
```gradle
implementation("io.komune.f2:f2-spring-boot-starter-function:${Versions.f2}")
```

* maven
```
<dependency>
  <groupId>io.komune.f2</groupId>
  <artifactId>f2-spring-boot-starter-function</artifactId>
  <version>${Versions.f2}</version>
</dependency>
```

### Dependency to bind function to http protocol 
* gradle
```gradle
implementation("io.komune.f2:f2-spring-boot-starter-function-http:${Versions.f2}")
```

* maven
```
<dependency>
  <groupId>io.komune.f2</groupId>
  <artifactId>f2-spring-boot-starter-function-http</artifactId>
  <version>${Versions.f2}</version>
</dependency>
```


## Example

* Function implementation
```kotlin
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import f2.dsl.fnc.f2Consumer
import f2.dsl.fnc.f2Function
import f2.dsl.fnc.f2Supplier
import f2.dsl.fnc.f2SupplierSingle
import java.security.MessageDigest
import java.util.Base64
import java.util.UUID
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class F2SampleHttpApp {
	
	@Bean
	fun sha256(): F2Function<String, String> = f2Function { bytes ->
		val md = MessageDigest.getInstance("SHA-256")
		val hash = md.digest(bytes.toByteArray())
		Base64.getEncoder().encodeToString(hash)
	}

	@Bean
	fun uuid(): F2Supplier<String> = f2SupplierSingle {
		UUID.randomUUID().toString()
	}
	@Bean
	fun uuids(): F2Supplier<String> = f2Supplier {
		ticker(1000).consumeAsFlow().map { UUID.randomUUID().toString() }
	}
	@Bean
	fun println(): F2Consumer<String> = f2Consumer(::println)
}

/**
 * Main method to start Spring Boot Application
 */
fun main(args: Array<String>) {
	runApplication<F2SampleHttpApp>(*args)
}
```

* Function Client implementation
```kotlin
expect fun didClient(url: String): F2Supplier<DIDFunctionClient>

open class DIDFunctionClient constructor(private val client: F2Client) : DidAggregate {
	override fun sha256(): F2Function<String, String> = client.function("sha256")
	override fun uuid(): F2Supplier<String> = client.function("uuid")
	override fun uuids(): F2Supplier<String> = client.function("uuids")
	override fun println(): F2Consumer<String> = client.function("println")
}
```

## Openapi

Integration with Springdoc to generate open api documentation.

* Maven 
```
<dependency>
  <groupId>io.komune.f2</groupId>
  <artifactId>f2-spring-boot-openapi</artifactId>
  <version>${Versions.f2}</version>
</dependency>
```

```gradle
implementation("io.komune.f2:f2-spring-boot-openapi:${Versions.f2}")
```


* Configuration can be done as describe in springdoc documentation
  https://springdoc.org/#features

## Serializer

Three serializer can be used via the spring parameter `spring.cloud.function.preferred-json-mapper`
 * jackson
 * gson
 * kSerialization

## Errors

`f2-spring-boot-exception-http-webflux` and `-mvc` answer every error with one body:

```json
{"timestamp":"…","path":"/itemDelete","status":409,"error":"Conflict","requestId":"…",
 "refusal":"item.CITED","message":"Cited by A: it stays","parameters":{"id":"i-1"},"citers":["A"]}
```

* `refusal` is always there: the rule a refusal names (`F2Refusal.code`), or the meaning of the status
  (`HttpRefusal`, `http.NOT_FOUND`…).
* `message`, `parameters` and `citers` travel only with a refusal — an `F2Exception` whose `F2Error` carries an
  `F2Refusal`. Any other error says nothing it was raised with, whatever `server.error.include-*` asks for.
* The status is the one the `F2Error` names, wherever the `F2Exception` sits in the cause chain.
* An unreadable request body is the refusal `http.INVALID_PAYLOAD`; a 401 or 403 of f2's security starters gets
  the same body, with `WWW-Authenticate: Bearer` alone.
* The body is written with the application's `JsonMapper` bean.

`spring.webflux.problemdetails.enabled` and `spring.mvc.problemdetails.enabled` must stay `false` (their
default): enabled, Spring's `ProblemDetail` handler answers the exceptions it knows (`ResponseStatusException`,
`ServerWebInputException`, `HttpMessageNotReadableException`…) raised in a controller before F2's error body is
written, with its own body and their `detail` message.

## BlockHound

`f2-bdd-blockhound` installs BlockHound for a JUnit session. The test JVM needs
`-XX:+AllowRedefinitionToAddDeleteMethods` (JDK 13 and later), or BlockHound cannot instrument the JDK:

```kotlin
tasks.withType<Test>().configureEach { jvmArgs("-XX:+AllowRedefinitionToAddDeleteMethods") }
```
