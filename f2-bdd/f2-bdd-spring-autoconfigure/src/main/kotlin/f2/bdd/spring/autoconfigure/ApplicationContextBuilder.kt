package f2.bdd.spring.autoconfigure

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.GenericApplicationContext

class ApplicationContextBuilder {

    fun create(
        types: Array<Class<*>>,
        profile: Array<String> = emptyArray(),
        config: Map<String, String> = emptyMap()
    ): GenericApplicationContext {
        val sources = types.fold(SpringApplicationBuilder()) { builder, type -> builder.sources(type) }
        return profile.fold(sources) { builder, name -> builder.profiles(name) }
            .properties(config)
            .run() as GenericApplicationContext
    }

    @EnableAutoConfiguration
    @Configuration
    @ComponentScan("f2")
    class SimpleConfiguration
}
