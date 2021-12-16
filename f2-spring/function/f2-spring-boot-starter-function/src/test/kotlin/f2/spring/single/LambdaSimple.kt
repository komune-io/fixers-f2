package f2.spring.single

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class LambdaSimple {

	@Bean
	open fun lambdaSingleReceiver(): LambdaPureKotlinReceiver = LambdaPureKotlinReceiver()

	@Bean
	open fun functionSingle(): (String) -> String = { value ->
		value.reversed()
	}

	@Bean
	open fun supplierSingle(): () -> String = {
		"supplierValuePureKotlinValue"
	}

	@Bean
	open fun consumerSingle(receiver: LambdaPureKotlinReceiver): (String) -> Unit = {
		receiver.items.add(it)
	}

}

data class LambdaPureKotlinReceiver(
	val items: MutableList<String> = mutableListOf()
)