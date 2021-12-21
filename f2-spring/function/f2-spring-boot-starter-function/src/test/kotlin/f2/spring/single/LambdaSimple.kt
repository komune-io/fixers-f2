package f2.spring.single

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class LambdaSimple {

	@Bean
	open fun lambdaSingleReceiver(): StringConsumerReceiver = StringConsumerReceiver()

	@Bean
	open fun functionSingle(): (String) -> String = { value ->
		value.reversed()
	}

	@Bean
	open fun supplierSingle(): () -> String = {
		"supplierValuePureKotlinValue"
	}

	@Bean
	open fun consumerSingle(receiver: ConsumerReceiver<String>): (String) -> Unit = {
		receiver.items.add(it)
	}
}

class StringConsumerReceiver(
	override val items: MutableList<String> = mutableListOf()
	) : ConsumerReceiver<String>
