package f2.spring.list

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class LambdaList {

	@Bean
	open fun functionList(): (List<String>) -> List<String> = { values ->
		values.map { it.reversed() }
	}

	@Bean
	open fun supplierList(): () -> List<String> = {
		listOf("supplierValuePureKotlinValue")
	}

	@Bean
	open fun consumerList(receiver: ConsumerReceiver<String>): (List<String>) -> Unit = {
		receiver.items.addAll(it)
	}
}
