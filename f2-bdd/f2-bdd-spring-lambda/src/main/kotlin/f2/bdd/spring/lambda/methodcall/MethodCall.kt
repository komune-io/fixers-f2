package f2.bdd.spring.lambda.methodcall

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class MethodCall {

	private val f2Object = MethodCallLambda()

	@Bean
	fun functionMethodCall(functionSimple: (String) -> String) = f2Object.functionF2(functionSimple)

	@Bean
	fun supplierMethodCall(supplierSimple: () -> String) = f2Object.supplierF2(supplierSimple)

	@Bean
	fun consumerMethodCall(receiver: ConsumerReceiver<String>) = f2Object.consumerF2(receiver)

}
