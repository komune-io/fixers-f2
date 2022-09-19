# Welcome to F2


F2 is kotlin framework to implement and query function as a service.
Functions implementation and deployement is based on [Spring Cloud Function](https://github.com/spring-cloud/spring-cloud-function). 
Function client is based on ktor

## Dsl

### Lambda

 * Supplier
```
F2Supplier.Signle<PAYLOAD> -> 0 to 1 -> suspend () -> T
F2Supplier.Flow<PAYLOAD>   -> 0 to * -> suspend () -> Flow<T>
```
 * Function
```
F2Function.Single<PAYLOAD, RESPONSE> -> 1 to 1 -> suspend (T) -> T
F2Function.Flow<PAYLOAD, RESPONSE> -> 1 to * -> suspend (T) -> Flow<T>
F2Function.Channel<PAYLOAD, RESPONSE> -> * to * -> suspend (Flow<T>) -> Flow<T>
```
 * Consumer
 ```
F2Supplier.Signle<PAYLOAD> -> 1 to 0 suspend (T) ->Unit
F2Supplier.Flow<PAYLOAD>   -> * to 0 suspend (Flow<T>) ->Unit
```

### Function
F2 builds on top of the 3 core functional:
 * `f2.dsl.fnc.F2Function<I, O>` apply change on data
 * `f2.dsl.fnc.F2Supplier<O>` supply a stream of data
 * `f2.dsl.fnc.F2Consumer<I>` consume data



### CQRS

[Command Query Responsibility Segregation](https://martinfowler.com/bliki/CQRS.html)
is a pattern that performs segregate operations that read data from operations that update data by using separate interfaces. T

The package `f2-dsl-cqrs` provide kotlin multplatform interfaces for `f2.dsl.cqrs.Event`, `f2.dsl.cqrs.Command`, `f2.dsl.cqrs.Query`
It also interface for querying a page o result `f2.dsl.cqrs.PageRequest` and `Pagef2.dsl.cqrs
`
```
<dependency>
  <groupId>city.smartb.f2</groupId>
  <artifactId>f2-dsl-cqrs</artifactId>
  <version>${Versions.f2}</version>
</dependency>
```


### Spring

F2 provide basic configuration for spring cloud function


 * Depencencies to deploy Standalone function binded to http protocol 
```
<dependency>
  <groupId>city.smartb.f2</groupId>
  <artifactId>f2-spring-boot-starter-function-http</artifactId>
  <version>${Versions.f2}</version>
</dependency>
```

 * Depencencies to deploy Standalone function binded to rscoket protocol 
```
<dependency>
  <groupId>city.smartb.f2</groupId>
  <artifactId>f2-spring-boot-starter-function-rsocker</artifactId>
  <version>${Versions.f2}</version>
</dependency>
```

#### Openapi

Integration with Springdoc to generate open api documentation.

* Depencencies to deploy springdoc with swagger ui
```
<dependency>
  <groupId>city.smartb.f2</groupId>
  <artifactId>f2-spring-boot-openapi</artifactId>
  <version>${Versions.f2}</version>
</dependency>
```

* Configuration can be done as describe in springdoc documentation  
  https://springdoc.org/#features


## RSOCKET 


* Request-Respond (1 to 1)
* Fire And Forget (1 to 0)
* Request Stream (1 to many)
* Request Channel (many to many)

* Supplier (0 - 1) 
* Consumer (1 - 0) => Fire And Forget
* Function (1 - 1) => (many to many) or (1 to 1) 

Manque Request Stream