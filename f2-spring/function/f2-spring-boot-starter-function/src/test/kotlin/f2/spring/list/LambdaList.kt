package f2.spring.list

import f2.spring.single.LambdaPureKotlinReceiver
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
	open fun consumerList(receiver: LambdaPureKotlinReceiver): (String) -> Unit = {
		receiver.items.add(it)
	}
}
