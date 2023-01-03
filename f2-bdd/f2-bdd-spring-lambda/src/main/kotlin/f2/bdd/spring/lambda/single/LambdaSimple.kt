package f2.bdd.spring.lambda.single

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LambdaSimple {

	@Bean
	fun lambdaSingleReceiver(): StringConsumerReceiver = StringConsumerReceiver()

	@Bean
	fun functionSingle(): (String) -> String = { value ->
		value.reversed()
	}

	@Bean
	fun supplierSingle(): () -> String = {
		"supplierValuePureKotlinValue"
	}

	@Bean
	fun consumerSingle(receiver: ConsumerReceiver<String>): (String) -> Unit = { value ->
		receiver.items.add(value)
	}
}

class StringConsumerReceiver(
	override val items: MutableList<String> = mutableListOf()
	) : ConsumerReceiver<String>
