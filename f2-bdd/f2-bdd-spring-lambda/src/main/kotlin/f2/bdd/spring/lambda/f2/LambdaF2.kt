package f2.bdd.spring.lambda.f2

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import f2.dsl.fnc.f2Function
import f2.dsl.fnc.f2Supplier
import f2.dsl.fnc.f2Consumer
import kotlinx.coroutines.flow.flowOf
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class LambdaF2 {

	@Bean
	@Deprecated("Use Basic instead")
	open fun functionF2(functionSimple: (String) -> String): F2Function<String, String> = f2Function { value ->
		functionSimple(value)
	}

	@Bean
	@Deprecated("Use Basic instead")
	open fun supplierF2(supplierSimple: () -> String): F2Supplier<String> = f2Supplier {
		flowOf(supplierSimple())
	}

	@Bean
	@Deprecated("Use Basic instead")
	open fun consumerF2(receiver: ConsumerReceiver<String>): F2Consumer<String> = f2Consumer { value ->
		receiver.items.add(value)
	}

	@Bean
	open fun functionBasic(functionSimple: (String) -> String): F2Function<String, String> = f2Function { value ->
		functionSimple(value)
	}

	@Bean
	open fun supplierBasic(supplierSimple: () -> String): F2Supplier<String> = f2Supplier {
		flowOf(supplierSimple())
	}

	@Bean
	open fun consumerBasic(receiver: ConsumerReceiver<String>): F2Consumer<String> = f2Consumer { value ->
		receiver.items.add(value)
	}
}
