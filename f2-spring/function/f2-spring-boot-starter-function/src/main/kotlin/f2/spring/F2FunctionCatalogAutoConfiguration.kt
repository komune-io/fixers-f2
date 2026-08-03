package f2.spring

import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import java.lang.reflect.ParameterizedType
import java.util.function.Function
import java.util.function.Supplier
import org.springframework.beans.factory.SmartInitializingSingleton
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
import org.springframework.cloud.function.context.FunctionRegistration
import org.springframework.cloud.function.context.FunctionRegistry
import org.springframework.cloud.function.context.config.FunctionContextUtils
import org.springframework.context.annotation.Bean
import org.springframework.core.ResolvableType
import reactor.core.publisher.Flux

/**
 * Registers every [F2Function] and [F2Supplier] bean in the function catalog as a
 * Flux-based Function/Supplier, bridging Flux <-> Flow through [invokeFlowFunction] and
 * [invokeFlowSupplier].
 *
 * Eager registration (before the first lookup) keeps Spring Cloud Function's Kotlin-lambda
 * discovery from wrapping F2 beans itself: since F2 interfaces are no longer suspend
 * function types, that generic path would invoke them with raw payloads instead of Flows.
 */
@AutoConfiguration
@ConditionalOnClass(FunctionRegistry::class)
class F2FunctionCatalogAutoConfiguration {

    @Bean
    fun f2FunctionCatalogRegistrar(
        beanFactory: ConfigurableListableBeanFactory,
        functionRegistry: FunctionRegistry,
    ) = SmartInitializingSingleton {
        beanFactory.getBeanNamesForType(F2Function::class.java).forEach { name ->
            registerFunction(beanFactory, functionRegistry, name)
        }
        beanFactory.getBeanNamesForType(F2Supplier::class.java).forEach { name ->
            registerSupplier(beanFactory, functionRegistry, name)
        }
    }

    private fun registerFunction(
        beanFactory: ConfigurableListableBeanFactory,
        functionRegistry: FunctionRegistry,
        name: String,
    ) {
        val target = beanFactory.getBean(name, F2Function::class.java)
        val generics = beanFactory.resolveGenerics(name)
        val type = ResolvableType.forClassWithGenerics(
            Function::class.java,
            ResolvableType.forClassWithGenerics(Flux::class.java, generics[0]),
            ResolvableType.forClassWithGenerics(Flux::class.java, generics[1]),
        ).type
        val function = Function<Flux<*>, Flux<Any>> { flux -> invokeFlowFunction(target, flux) }
        functionRegistry.register(FunctionRegistration(function, name).type(type))
    }

    private fun registerSupplier(
        beanFactory: ConfigurableListableBeanFactory,
        functionRegistry: FunctionRegistry,
        name: String,
    ) {
        val target = beanFactory.getBean(name, F2Supplier::class.java)
        val generics = beanFactory.resolveGenerics(name)
        val type = ResolvableType.forClassWithGenerics(
            Supplier::class.java,
            ResolvableType.forClassWithGenerics(Flux::class.java, generics[0]),
        ).type
        val supplier = Supplier<Flux<Any>> { invokeFlowSupplier(target) }
        functionRegistry.register(FunctionRegistration(supplier, name).type(type))
    }

    private fun ConfigurableListableBeanFactory.resolveGenerics(name: String): Array<ResolvableType> {
        val functionType = FunctionContextUtils.findType(name, this) as ParameterizedType
        return functionType.actualTypeArguments.map(ResolvableType::forType).toTypedArray()
    }
}
