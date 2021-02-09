///*
// * Copyright 2012-2019 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package city.smartb.f2.spring.function
//
//import city.smartb.f2.spring.function.adapter.execute
//import org.apache.commons.logging.LogFactory
//import org.springframework.beans.BeansException
//import org.springframework.beans.factory.BeanFactory
//import org.springframework.beans.factory.BeanFactoryAware
//import org.springframework.beans.factory.BeanNameAware
//import org.springframework.beans.factory.FactoryBean
//import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition
//import org.springframework.beans.factory.config.BeanDefinition
//import org.springframework.beans.factory.config.BeanFactoryPostProcessor
//import org.springframework.beans.factory.config.ConfigurableListableBeanFactory
//import org.springframework.beans.factory.config.ConstructorArgumentValues
//import org.springframework.beans.factory.support.BeanDefinitionRegistry
//import org.springframework.beans.factory.support.RootBeanDefinition
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass
//import org.springframework.cloud.function.context.FunctionRegistration
//import org.springframework.cloud.function.context.config.FunctionContextUtils
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.core.ResolvableType
//import org.springframework.util.ObjectUtils
//import reactor.core.publisher.Flux
//import java.lang.Exception
//import java.lang.UnsupportedOperationException
//import java.lang.reflect.ParameterizedType
//import java.util.function.Consumer
//import java.util.function.Function
//import java.util.function.Supplier
//import kotlin.Function0
//import kotlin.Function1
//import kotlin.Function2
//import kotlin.Function3
//import kotlin.Function4
//import kotlin.coroutines.Continuation
//import kotlin.reflect.KFunction
//import kotlin.reflect.full.callSuspend
//import kotlin.reflect.jvm.reflect
//
///**
// * Configuration class which defines the required infrastructure to bootstrap Kotlin
// * lambdas as invocable functions within the context of the framework.
// *
// * @author Oleg Zhurakousky
// * @since 2.0
// */
//@Configuration
////@ConditionalOnClass(name = ["kotlin.jvm.functions.Function0"])
//open class KotlinLambdaToFunctionAutoConfiguration {
//	protected val logger = LogFactory.getLog(javaClass)
//
//	/**
//	 * Will transform all discovered Kotlin's Function lambdas to java
//	 * Supplier, Function and Consumer, retaining the original Kotlin type
//	 * characteristics.
//	 *
//	 * @return the bean factory post processor
//	 */
//	@Bean
//	open fun kotlinToFunctionTransformer(): BeanFactoryPostProcessor? {
//		return BeanFactoryPostProcessor { beanFactory ->
//			val beanDefinitionNames = beanFactory.beanDefinitionNames
//			for (beanDefinitionName in beanDefinitionNames) {
//				val beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName)
//				if (beanDefinition is AnnotatedBeanDefinition) {
//					val methodMetadata = (beanDefinition as AnnotatedBeanDefinition).factoryMethodMetadata
//					if (methodMetadata != null && methodMetadata.returnTypeName.startsWith("kotlin.jvm.functions.Function")) {
//						val cbd =
//							RootBeanDefinition(KotlinFunctionWrapper::class.java)
//						val ca = ConstructorArgumentValues()
//						ca.addGenericArgumentValue(beanDefinition)
//						cbd.constructorArgumentValues = ca
//						(beanFactory as BeanDefinitionRegistry).registerBeanDefinition(
//							beanDefinitionName + FunctionRegistration.REGISTRATION_NAME_SUFFIX,
//							cbd
//						)
//					}
//				}
//			}
//		}
//	}
//
//	private class KotlinFunctionWrapper private constructor(private val kotlinLambdaTarget: Any) :
//		Function<Any?, Any?>, Supplier<Any?>,
//		Consumer<Array<Any?>?>, Function0<Any?>,
//		Function1<Any?, Any?>,
//		Function2<Any?, Any?, Any?>,
//		Function3<Any?, Any?, Any?, Any?>,
//		Function4<Any?, Any?, Any?, Any?, Any?>,
//		FactoryBean<FunctionRegistration<*>>, BeanNameAware, BeanFactoryAware {
//		private var name: String? = null
//		private var beanFactory: ConfigurableListableBeanFactory? = null
//		override fun apply(input: Any?): Any? {
//			return if (ObjectUtils.isEmpty(input)) {
//				this.invoke()
//			} else if (ObjectUtils.isArray(input)) {
//				null
//			} else {
//				this.invoke(input)
//			}
//		}
//
//		override operator fun invoke(arg0: Any?, arg1: Any?, arg2: Any?, arg3: Any?): Any? {
//			val kfunction = (kotlinLambdaTarget as Function4<Any?, Any?, Any?, Any?, Any?>).reflect()
//			return execute(kfunction, arg0, arg1, arg2)
//		}
//
//		override operator fun invoke(arg0: Any?, arg1: Any?, arg2: Any?): Any? {
//			val kfunction = (kotlinLambdaTarget as Function3<Any?, Any?, Any?, Any?>).reflect()
//			return execute(kfunction, arg0, arg1, arg2)
//		}
//
//		override operator fun invoke(arg0: Any?, arg1: Any?): Any? {
//			if(kotlinLambdaTarget is Function2<*, *, *>){
//				val kfunction = (kotlinLambdaTarget as Function2<Any?, Any?, Any?>).reflect()
//				return execute(kfunction, arg0, arg1)
//			}
//			val kfunction = (kotlinLambdaTarget as Function1<Any?, Any?>).reflect()
//			return execute(kfunction, arg0, arg1)
//		}
//
//		override operator fun invoke(arg0: Any?): Any? {
//			val kfunction = (kotlinLambdaTarget as Function1<Any?, Any?>).reflect()
////			return kotlinLambdaTarget.invoke(arg0)
//			return execute(kfunction, arg0)
//		}
//
//		override fun invoke(): Any? {
//			val kfunction = (kotlinLambdaTarget as kotlin.Function<Any?>).reflect()
//			return execute(kfunction)
//		}
//
//		private fun execute(kFunction: KFunction<Any?>?, vararg args: Any?): Any? {
//			if(kFunction == null) return null
//			val isSuspend = kFunction.isSuspend
//			if(isSuspend) {
//				return execute<Any, Any?> {
//					kFunction.callSuspend(args)
//				}
//			}
//			return kFunction.call(args)
//		}
//
//		override fun accept(input: Array<Any?>?) {
//			this.apply(input)
//		}
//
//		override fun get(): Any? {
//			return this.apply(null)
//		}
//
//		@Throws(Exception::class)
//		override fun getObject(): FunctionRegistration<*> {
//			val name = if (name!!.endsWith(FunctionRegistration.REGISTRATION_NAME_SUFFIX)) name!!.replace(
//				FunctionRegistration.REGISTRATION_NAME_SUFFIX,
//				""
//			) else name!!
//			var functionType = FunctionContextUtils.findType(name, beanFactory)
//			var registration: FunctionRegistration<*> = FunctionRegistration(this, name)
//			val types = (functionType as ParameterizedType).actualTypeArguments
//			functionType = if (functionType.getTypeName().contains("Function0")) {
//				ResolvableType.forClassWithGenerics(
//					Supplier::class.java, ResolvableType.forType(
//						types[0]
//					)
//				)
//					.type
//			} else if (functionType.getTypeName().contains("Function1")) {
//				ResolvableType.forClassWithGenerics(
//					Function::class.java, ResolvableType.forType(types[0]),
//					ResolvableType.forType(types[1])
//				).type
//			}else if (functionType.getTypeName().contains("Function2") && types[1].typeName.startsWith("kotlin.coroutines.Continuation")) {
//				ResolvableType.forClassWithGenerics(Flux::class.java, ResolvableType.forType(types[0]))
//				ResolvableType.forClassWithGenerics(
//					Function::class.java,
//					ResolvableType.forClassWithGenerics(Flux::class.java, ResolvableType.forType(types[0])),
//					ResolvableType.forClassWithGenerics(Flux::class.java, ResolvableType.forType(types[1])),
//				).type
//			} else {
//				throw UnsupportedOperationException("Multi argument Kotlin functions are not currently supported")
//			}
//			registration = registration.type(functionType)
//			return registration
//		}
//
//		override fun getObjectType(): Class<*> {
//			return FunctionRegistration::class.java
//		}
//
//		override fun setBeanName(name: String) {
//			this.name = name
//		}
//
//		@Throws(BeansException::class)
//		override fun setBeanFactory(beanFactory: BeanFactory) {
//			this.beanFactory = beanFactory as ConfigurableListableBeanFactory
//		}
//
//	}
//}