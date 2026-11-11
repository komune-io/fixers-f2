package f2.bdd.spring.lambda.f2

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import f2.dsl.fnc.f2Consumer
import f2.dsl.fnc.f2Function
import f2.dsl.fnc.f2Supplier
import kotlinx.coroutines.flow.flowOf
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LambdaF2 {

    @Bean
    fun functionBasic(functionSimple: (String) -> String): F2Function<String, String> = f2Function { value ->
        functionSimple(value)
    }

    @Bean
    fun supplierBasic(supplierSimple: () -> String): F2Supplier<String> = f2Supplier {
        flowOf(supplierSimple())
    }

    @Bean
    fun consumerBasic(receiver: ConsumerReceiver<String>): F2Consumer<String> = f2Consumer { value ->
        receiver.items.add(value)
    }
}
