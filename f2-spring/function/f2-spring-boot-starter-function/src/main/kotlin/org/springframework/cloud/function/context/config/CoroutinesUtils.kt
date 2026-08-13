/*
 * Copyright 2021-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

@file:JvmName("CoroutinesUtils")
//KOMUNE Changes Start
// detekt runs over this vendored file, upstream's build does not
@file:Suppress("TooManyFunctions")
//KOMUNE Changes End
package org.springframework.cloud.function.context.config

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.lang.reflect.WildcardType
import kotlin.coroutines.Continuation
import kotlin.coroutines.intrinsics.suspendCoroutineUninterceptedOrReturn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.reactive.asFlow
import kotlinx.coroutines.reactor.asFlux
import kotlinx.coroutines.reactor.mono
import reactor.core.publisher.Flux

/**
 * @author Adrien Poupard
 *
 */

fun getSuspendingFunctionArgType(type: Type): Type {
	return getFlowTypeArguments(type)
}

//KOMUNE Changes Start
// detekt runs over this vendored file, upstream's build does not
@Suppress("ReturnCount")
//KOMUNE Changes End
fun getFlowTypeArguments(type: Type): Type {
	if(!isFlowType(type)) {
		return type
	}
	val parameterizedLowerType = type as ParameterizedType
 	if(parameterizedLowerType.actualTypeArguments.isEmpty()) {
		 return parameterizedLowerType
	}

	val actualTypeArgument = parameterizedLowerType.actualTypeArguments[0]
	return if(actualTypeArgument is WildcardType) {
		val wildcardTypeLower = parameterizedLowerType.actualTypeArguments[0] as WildcardType
		wildcardTypeLower.upperBounds[0]
	} else {
		actualTypeArgument
	}
}

fun isFlowType(type: Type): Boolean {
	return type.typeName.startsWith(Flow::class.qualifiedName!!)
}

fun getSuspendingFunctionReturnType(type: Type): Type {
	val lower = getContinuationTypeArguments(type)
	return getFlowTypeArguments(lower)
}

fun isContinuationType(type: Type): Boolean {
	return type.typeName.startsWith(Continuation::class.qualifiedName!!)
}

fun isContinuationUnitType(type: Type): Boolean {
	return isContinuationType(type) && type.typeName.contains(Unit::class.qualifiedName!!)
}

fun isContinuationFlowType(type: Type): Boolean {
	return isContinuationType(type) && type.typeName.contains(Flow::class.qualifiedName!!)
}

private fun getContinuationTypeArguments(type: Type): Type {
	if(!isContinuationType(type)) {
		return type
	}
	val parameterizedType = type as ParameterizedType
	val wildcardType = parameterizedType.actualTypeArguments[0] as WildcardType
	return wildcardType.lowerBounds[0]
}

//KOMUNE Changes Start
// Upstream blind-casts its arguments; F2 checks them first so a wrongly wired bean fails with a
// readable message instead of a bare ClassCastException.
// invokeSuspendingSupplier additionally accepts a supplier that returns a plain value rather than a
// Flow (F2Supplier<T> erases to that) and wraps it with flowOf; upstream assumes a Flow and blows up.
@Suppress("UNCHECKED_CAST")
fun invokeSuspendingFunction(kotlinLambdaTarget: Any, arg0: Any): Flux<Any> {
	require(kotlinLambdaTarget is Function2<*, *, *>) {
		"kotlinLambdaTarget must be a Function2, but was ${kotlinLambdaTarget::class.qualifiedName}"
	}
	require(arg0 is Flux<*>) {
		"arg0 must be a Flux, but was ${arg0::class.qualifiedName}"
	}
	val function = kotlinLambdaTarget as SuspendFunction
	val flux = arg0 as Flux<Any>
	return mono(Dispatchers.Unconfined) {
		suspendCoroutineUninterceptedOrReturn<Flow<Any>> {
			function.invoke(flux.asFlow(), it)
		}
	}.flatMapMany {
		it.asFlux()
	}
}

@Suppress("UNCHECKED_CAST")
fun invokeSuspendingSupplier(kotlinLambdaTarget: Any): Flux<Any> {
	require(kotlinLambdaTarget is Function1<*, *>) {
		"kotlinLambdaTarget must be a Function1, but was ${kotlinLambdaTarget::class.qualifiedName}"
	}
	val supplier = kotlinLambdaTarget as SuspendSupplier
	return mono(Dispatchers.Unconfined) {
		val result = suspendCoroutineUninterceptedOrReturn<Any> {
			val result = supplier.invoke(it)
			result
		}
		val resultFlow: Flow<Any> = if(result is Flow<*>) {
			result as Flow<Any>
		} else {
			flowOf(result)
		}
		resultFlow
	}.flatMapMany {
		it.asFlux()
	}
}
//KOMUNE Changes End

//KOMUNE Changes Start
// Same argument checking as above; `arg0.asFlow()` relies on the smart cast from the require, where
// upstream casts arg0 to Flux<Any> unchecked.
@Suppress("UNCHECKED_CAST")
fun invokeSuspendingConsumer(kotlinLambdaTarget: Any, arg0: Any) {
	require(kotlinLambdaTarget is Function2<*, *, *>) {
		"kotlinLambdaTarget must be a Function2, but was ${kotlinLambdaTarget::class.qualifiedName}"
	}
	require(arg0 is Flux<*>) {
		"arg0 must be a Flux, but was ${arg0::class.qualifiedName}"
	}
	val consumer = kotlinLambdaTarget as SuspendConsumer
	mono(Dispatchers.Unconfined) {
		suspendCoroutineUninterceptedOrReturn<Unit> {
			consumer.invoke(arg0.asFlow(), it)
		}
	}.subscribe()
}
//KOMUNE Changes End

fun isValidSuspendingFunction(kotlinLambdaTarget: Any, arg0: Any): Boolean {
	return arg0 is Flux<*> && kotlinLambdaTarget is Function2<*, *, *>
}

fun isValidSuspendingSupplier(kotlinLambdaTarget: Any): Boolean {
	return kotlinLambdaTarget is Function1<*, *>
}

//KOMUNE Changes Start
// Upstream declares these `private typealias`; F2 keeps them public.
typealias SuspendFunction = (Any?, Any?) -> Any?
typealias SuspendConsumer = (Any?, Any?) -> Unit?
typealias SuspendSupplier = (Any?) -> Any?
//KOMUNE Changes End
