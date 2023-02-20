# Welcome to F2

F2 is a framework built using the Kotlin programming language that 
is built on top of [Spring Cloud Function](https://github.com/spring-cloud/spring-cloud-function) for the server side 
and [ktor](https://ktor.io/) for multiplatform client. 
It aims to make it easier to develop Command and Query Responsibility Segregation (CQRS) 
based applications by providing a set of tools and abstractions to handle the different parts of a CQRS-based architecture.
The goal of F2 is to help developers build event-driven, scalable, and resilient systems that are easy to understand and maintain.

The Command and Query Responsibility Segregation (CQRS) architecture is a pattern
that separates the responsibilities of handling commands, which change the state of an application,
and queries, which retrieve information from the application.

HTTP is a widely-used protocol for sending and receiving information over the internet,
and is a popular choice for building RESTful web services. By supporting HTTP,
F2 and its client can easily integrate with a wide range of other technologies and services that use HTTP for communication.

RSocket, on the other hand, is a binary protocol for use on top of TCP or WebSockets.
It is designed for high-performance and low-latency communication, and provides features such as bi-directional streaming,
flow control, and error handling. By supporting RSocket, F2 and its client can provide high-performance
and efficient communication between serverless functions and their clients.


# Dependencies

## Spring Cloud Function

Spring Cloud Function provides a functional programming model for building cloud-native applications, 
enabling you to write simple, single-purpose functions that can be easily composed and deployed in a cloud environment. 
It allows you to take advantage of the many features of the Spring framework, such as data access, transaction management, 
security, and more, while still enjoying the scalability and cost-effectiveness of a serverless architecture. 
It supports multiple communication protocols, including HTTP, gRPC, and RSocket, and the choice of protocol 
will depend on the specific requirements of your application and the type of function being written.

## Ktor
F2 provides a multiplatform client using the Ktor client for HTTP and RSocket protocols. 
The Ktor client is a high-performance HTTP client for the Kotlin language that makes it easy to perform HTTP requests 
from your Kotlin applications. It supports both synchronous and asynchronous programming, 
and provides a wide range of features for working with HTTP and RSocket protocols, including connection pooling, 
request and response streaming, and automatic decompression of response bodies.
For `rsocket` implementation, we use [rsocket-kotlin](https://github.com/rsocket/rsocket-kotlin)

# Dsl

## Lambda
In Java, a Function, Supplier, and Consumer are functional interfaces that are part of the Java 8 functional programming API. 
They are used to represent functions that take one or more arguments, produce a result, or consume a value, respectively.
These functional interfaces can be used to implement functional programming concepts such as higher-order functions and lambda expressions, making it easier to write concise, readable, and maintainable code. For example, you could use a Function to map a list of integers to a list of strings, or a Consumer to iterate over a list of values and perform an action on each one. By using these functional interfaces, you can write code that is more functional and less imperative, resulting in a more declarative and maintainable codebase.


### Dependency
* gradle
```gradle
implementation("city.smartb.f2:f2-dsl-function:${Versions.f2}")
```
* maven
```
<dependency>
  <groupId>city.smartb.f2</groupId>
  <artifactId>f2-dsl-function</artifactId>
  <version>${Versions.f2}</version>
</dependency>
```


### F2Supplier

A `F2Supplier` does not take any input and produces a result. 
For example, you might write a Supplier that returns a random number. 
The get method of a Supplier returns the result.

 * JVM
```kotlin
actual fun interface F2Supplier<R> : suspend () -> Flow<R> {
	override suspend operator fun invoke(): Flow<R>
}
```
 * Js
```kotlin
actual interface F2Supplier<R> {
	fun invoke(): Promise<Array<R>>
}
```

### F2Function
A `F2Function` takes one or more inputs and produces a result. 
For example, you might write a Function that takes an Integer and returns a String. 
The apply method of a Function takes the input and returns the result.

* JVM
```kotlin
actual fun interface F2Function<T, R>: suspend (Flow<T>) -> Flow<R> {
  override suspend operator fun invoke(msgs: Flow<T>): Flow<R>
}
```
* Js
```kotlin
actual interface F2Function<T, R> {
  fun invoke(cmd: T): Promise<R>
}
```
### F2Consumer

A `F2Consumer` takes a value as input and does not produce a result. 
For example, you might write a Consumer that prints a message to the console. 
The accept method of a Consumer takes the input and performs an action, 
but does not return a value.

* JVM
```kotlin
actual fun interface F2Consumer<T>: suspend (Flow<T>) -> Unit {
  override suspend operator fun invoke(msg: Flow<T>)
}
```
* Js
```kotlin
actual interface F2Consumer<T> {
  fun invoke(cmd: T): Promise<Unit>
}
```

## CQRS

[Command Query Responsibility Segregation](https://martinfowler.com/bliki/CQRS.html)
is a pattern that performs segregate operations that read data from operations that update data by using separate interfaces.

The package `f2-dsl-cqrs` provide kotlin multplatform interfaces for `f2.dsl.cqrs.Event`, `f2.dsl.cqrs.Command`, `f2.dsl.cqrs.Query`
It also interface for querying a page o result `f2.dsl.cqrs.PageRequest` and `Pagef2.dsl.cqrs`

### Dependency
* gradle
```gradle
implementation("city.smartb.f2:f2-dsl-cqrs:${Versions.f2}")
```
* maven
```
<dependency>
  <groupId>city.smartb.f2</groupId>
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
implementation("city.smartb.f2:f2-spring-boot-starter-function:${Versions.f2}")
```

* maven
```
<dependency>
  <groupId>city.smartb.f2</groupId>
  <artifactId>f2-spring-boot-starter-function</artifactId>
  <version>${Versions.f2}</version>
</dependency>
```

### Dependency to bind function to http protocol 
* gradle
```gradle
implementation("city.smartb.f2:f2-spring-boot-starter-function-http:${Versions.f2}")
```

* maven
```
<dependency>
  <groupId>city.smartb.f2</groupId>
  <artifactId>f2-spring-boot-starter-function-http</artifactId>
  <version>${Versions.f2}</version>
</dependency>
```


### Dependency to bind function to rSocket protocol
* gradle
```gradle
implementation("city.smartb.f2:f2-spring-boot-starter-function-rsocket:${Versions.f2}")
```

* maven
```
<dependency>
  <groupId>city.smartb.f2</groupId>
  <artifactId>f2-spring-boot-starter-function-rsocket</artifactId>
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
expect fun didClient(protocol: Protocol, host: String, port: Int, path: String? = null): F2Supplier<DIDFunctionClient>

@JsName("DIDFunctionClient")
@JsExport
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
  <groupId>city.smartb.f2</groupId>
  <artifactId>f2-spring-boot-openapi</artifactId>
  <version>${Versions.f2}</version>
</dependency>
```

```gradle
implementation("city.smartb.f2:f2-spring-boot-openapi:${Versions.f2}")
```


* Configuration can be done as describe in springdoc documentation
  https://springdoc.org/#features

## Serializer

Three serializer can be used via the spring parameter `spring.cloud.function.preferred-json-mapper`
 * jackson
 * gson
 * kSerialization
