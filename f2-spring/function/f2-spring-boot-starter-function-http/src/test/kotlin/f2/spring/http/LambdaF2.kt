package f2.spring.http

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import f2.dsl.fnc.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class LambdaF2 {

	@Bean
	open fun receiverF2(): ConsumerReceiver<String> = StringConsumerReceiver()

	@Bean
	open fun functionF2(): F2Function<String, String> = f2Function { value ->
		value.reversed()
	}

	@Bean
	open fun supplierF2(): F2Supplier<String> = f2Supplier {
		"supplierF2Value"
	}

	@Bean
	open fun consumerF2(receiver: ConsumerReceiver<String>): F2Consumer<String> = f2Consumer { value ->
		receiver.items.add(value)
	}
}

class StringConsumerReceiver(
	override val items: MutableList<String> = mutableListOf()
) : ConsumerReceiver<String>
