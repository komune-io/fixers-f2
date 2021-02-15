package city.smartb.f2.function.spring

import city.smartb.f2.function.spring.adapter.execute
import city.smartb.f2.function.spring.annotation.F2
import org.springframework.cloud.function.context.FunctionRegistration
import org.springframework.cloud.function.context.FunctionType
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.GenericApplicationContext
import org.springframework.core.ResolvableType
import reactor.core.publisher.Flux
import java.util.function.Function
import java.util.function.Supplier
import kotlin.reflect.full.callSuspend
import kotlin.reflect.full.findAnnotation

@Configuration
open class F2FunctionBeanConfig(
	private val context: GenericApplicationContext
)
{

	@Bean
	@ExperimentalStdlibApi
	open fun registerS2Function(): String? {
		val bean = context.getBeansWithAnnotation(F2::class.java)
		if(bean.values.size > 0) {
			val aggregateBean = bean.values.first()

			aggregateBean::class.members.forEach { kfunction ->
				val annotated = kfunction.findAnnotation<F2>()
				if (annotated != null) {
					val name = kfunction.name
					val javaMethod = aggregateBean::class.java.methods.find { it.name == kfunction.name }!!

					val from = ResolvableType.forClassWithGenerics(Flux::class.java, javaMethod.parameterTypes.first())
					val to = ResolvableType.forClassWithGenerics(Flux::class.java, javaMethod.returnType)
					val execution = execute<Any, Any> {
						kfunction.callSuspend(aggregateBean, it)!!
					}
					val fnc = FunctionType(
						ResolvableType.forClassWithGenerics(Function::class.java, from, to).getType()
					)
					context.registerBean(name, FunctionRegistration::class.java,
						Supplier {
							FunctionRegistration(execution).type(fnc)
						}
					)
				}
			}
		}
		return null
	}

}