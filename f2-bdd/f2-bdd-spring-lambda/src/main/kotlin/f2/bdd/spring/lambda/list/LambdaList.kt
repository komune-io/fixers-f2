package f2.bdd.spring.lambda.list

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class LambdaList {

	@Bean
	fun functionList(): (List<String>) -> List<String> = { values ->
		values.map { it.reversed() }
	}

	@Bean
	fun supplierList(): () -> List<String> = {
		listOf("supplierValuePureKotlinValue")
	}

	@Bean
	fun consumerList(receiver: ConsumerReceiver<String>): (List<String>) -> Unit = {
		receiver.items.addAll(it)
	}
}
