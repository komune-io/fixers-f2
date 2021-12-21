package f2.spring.f2

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import f2.dsl.fnc.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class LambdaF2 {

	@Bean
	open fun functionF2(functionSimple: (String) -> String): F2Function<String, String> = f2Function { value ->
		functionSimple(value)
	}

	@Bean
	open fun supplierF2(supplierSimple: () -> String): F2Supplier<String> = f2Supplier {
		supplierSimple()
	}

	@Bean
	open fun consumerF2(receiver: ConsumerReceiver<String>): F2Consumer<String> = f2Consumer { value ->
		receiver.items.add(value)
	}

}