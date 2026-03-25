package f2.spring.openapi

import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import f2.dsl.fnc.f2Consumer
import f2.dsl.fnc.f2Function
import f2.dsl.fnc.f2Supplier
import kotlinx.coroutines.flow.flowOf
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class F2OpenApiTestApp {

    @Bean
    fun testFunction(): F2Function<String, String> = f2Function { it.uppercase() }

    @Bean
    fun testSupplier(): F2Supplier<String> = f2Supplier { flowOf("hello") }

    @Bean
    fun testConsumer(): F2Consumer<String> = f2Consumer { }
}
