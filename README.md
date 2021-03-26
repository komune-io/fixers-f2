# Welcome to F2


F2 is kotlin framework to implement and query function as a service.
Functions implementation and deployement is based on (Spring Cloud Function)[https://github.com/spring-cloud/spring-cloud-function]. 
Function client is based on ktor

## Dsl

### Function
F2 builds on top of the 3 core functional:
 * `f2.dsl.function.F2Function<I, O>` apply change on data
 * `f2.dsl.function.F2Supplier<O>` supply a stream of data
 * `f2.dsl.function.F2Consumer<I>` consume data



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
  <version>0.1.0-SNAPSHOT</version>
</dependency>
```


### Spring

F2 provide basic configuration for spring cloud function


 * Depencencies to deploy Standalone function binded to http protocol 
```
<dependency>
  <groupId>city.smartb.f2</groupId>
  <artifactId>f2-spring-boot-starter-function-http</artifactId>
  <version>0.1.0-SNAPSHOT</version>
</dependency>
```

 * Depencencies to deploy Standalone function binded to rscoket protocol 
```
<dependency>
  <groupId>city.smartb.f2</groupId>
  <artifactId>f2-spring-boot-starter-function-rsocker</artifactId>
  <version>0.1.0-SNAPSHOT</version>
</dependency>
```