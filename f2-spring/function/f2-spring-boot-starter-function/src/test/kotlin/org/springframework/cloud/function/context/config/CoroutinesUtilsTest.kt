package org.springframework.cloud.function.context.config

import java.lang.reflect.ParameterizedType
import java.lang.reflect.WildcardType
import kotlin.coroutines.Continuation
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import reactor.core.publisher.Flux

class CoroutinesUtilsTest {

    @Test
    fun `isFlowType returns true for Flow type`() {
        val flowType = object : ParameterizedType {
            override fun getActualTypeArguments() = arrayOf<java.lang.reflect.Type>(String::class.java)
            override fun getRawType() = Flow::class.java
            override fun getOwnerType() = null
            override fun getTypeName() = "kotlinx.coroutines.flow.Flow<java.lang.String>"
        }
        assertTrue(isFlowType(flowType))
    }

    @Test
    fun `isFlowType returns false for non-Flow type`() {
        assertFalse(isFlowType(String::class.java))
    }

    @Test
    fun `isContinuationType returns true for Continuation type`() {
        val continuationType = object : ParameterizedType {
            override fun getActualTypeArguments() = arrayOf<java.lang.reflect.Type>(String::class.java)
            override fun getRawType() = Continuation::class.java
            override fun getOwnerType() = null
            override fun getTypeName() = "kotlin.coroutines.Continuation<java.lang.String>"
        }
        assertTrue(isContinuationType(continuationType))
    }

    @Test
    fun `isContinuationType returns false for non-Continuation type`() {
        assertFalse(isContinuationType(String::class.java))
    }

    @Test
    fun `isContinuationUnitType returns true for Continuation of Unit`() {
        val continuationType = object : ParameterizedType {
            override fun getActualTypeArguments() = arrayOf<java.lang.reflect.Type>(Unit::class.java)
            override fun getRawType() = Continuation::class.java
            override fun getOwnerType() = null
            override fun getTypeName() = "kotlin.coroutines.Continuation<kotlin.Unit>"
        }
        assertTrue(isContinuationUnitType(continuationType))
    }

    @Test
    fun `isContinuationUnitType returns false for Continuation of non-Unit`() {
        val continuationType = object : ParameterizedType {
            override fun getActualTypeArguments() = arrayOf<java.lang.reflect.Type>(String::class.java)
            override fun getRawType() = Continuation::class.java
            override fun getOwnerType() = null
            override fun getTypeName() = "kotlin.coroutines.Continuation<java.lang.String>"
        }
        assertFalse(isContinuationUnitType(continuationType))
    }

    @Test
    fun `isContinuationFlowType returns true for Continuation of Flow`() {
        val continuationType = object : ParameterizedType {
            override fun getActualTypeArguments() = arrayOf<java.lang.reflect.Type>(Flow::class.java)
            override fun getRawType() = Continuation::class.java
            override fun getOwnerType() = null
            override fun getTypeName() =
                "kotlin.coroutines.Continuation<kotlinx.coroutines.flow.Flow<java.lang.String>>"
        }
        assertTrue(isContinuationFlowType(continuationType))
    }

    @Test
    fun `isContinuationFlowType returns false for Continuation of non-Flow`() {
        val continuationType = object : ParameterizedType {
            override fun getActualTypeArguments() = arrayOf<java.lang.reflect.Type>(String::class.java)
            override fun getRawType() = Continuation::class.java
            override fun getOwnerType() = null
            override fun getTypeName() = "kotlin.coroutines.Continuation<java.lang.String>"
        }
        assertFalse(isContinuationFlowType(continuationType))
    }

    @Test
    fun `getFlowTypeArguments returns inner type for Flow`() {
        val flowType = object : ParameterizedType {
            override fun getActualTypeArguments() = arrayOf<java.lang.reflect.Type>(String::class.java)
            override fun getRawType() = Flow::class.java
            override fun getOwnerType() = null
            override fun getTypeName() = "kotlinx.coroutines.flow.Flow<java.lang.String>"
        }
        assertEquals(String::class.java, getFlowTypeArguments(flowType))
    }

    @Test
    fun `getFlowTypeArguments returns same type for non-Flow`() {
        assertEquals(String::class.java, getFlowTypeArguments(String::class.java))
    }

    @Test
    fun `isValidSuspendingFunction returns true for valid function and Flux`() {
        val function: (Any?, Any?) -> Any? = { _, _ -> null }
        val flux = Flux.just("test")
        assertTrue(isValidSuspendingFunction(function, flux))
    }

    @Test
    fun `isValidSuspendingFunction returns false for non-Function2`() {
        val notAFunction = "not a function"
        val flux = Flux.just("test")
        assertFalse(isValidSuspendingFunction(notAFunction, flux))
    }

    @Test
    fun `isValidSuspendingFunction returns false for non-Flux arg`() {
        val function: (Any?, Any?) -> Any? = { _, _ -> null }
        val notAFlux = "not a flux"
        assertFalse(isValidSuspendingFunction(function, notAFlux))
    }

    @Test
    fun `isValidSuspendingSupplier returns true for Function1`() {
        val supplier: (Any?) -> Any? = { null }
        assertTrue(isValidSuspendingSupplier(supplier))
    }

    @Test
    fun `isValidSuspendingSupplier returns false for non-Function1`() {
        val notAFunction = "not a function"
        assertFalse(isValidSuspendingSupplier(notAFunction))
    }

    @Test
    fun `invokeSuspendingFunction throws for invalid kotlinLambdaTarget`() {
        val invalidTarget = "not a function"
        val flux = Flux.just("test")

        val exception = assertThrows<IllegalArgumentException> {
            invokeSuspendingFunction(invalidTarget, flux)
        }
        assertTrue(exception.message?.contains("must be a Function2") == true)
    }

    @Test
    fun `invokeSuspendingFunction throws for invalid arg0`() {
        val function: (Any?, Any?) -> Any? = { _, _ -> null }
        val invalidArg = "not a flux"

        val exception = assertThrows<IllegalArgumentException> {
            invokeSuspendingFunction(function, invalidArg)
        }
        assertTrue(exception.message?.contains("must be a Flux") == true)
    }

    @Test
    fun `invokeSuspendingSupplier throws for invalid kotlinLambdaTarget`() {
        val invalidTarget = "not a function"

        val exception = assertThrows<IllegalArgumentException> {
            invokeSuspendingSupplier(invalidTarget)
        }
        assertTrue(exception.message?.contains("must be a Function1") == true)
    }

    @Test
    fun `invokeSuspendingConsumer throws for invalid kotlinLambdaTarget`() {
        val invalidTarget = "not a function"
        val flux = Flux.just("test")

        val exception = assertThrows<IllegalArgumentException> {
            invokeSuspendingConsumer(invalidTarget, flux)
        }
        assertTrue(exception.message?.contains("must be a Function2") == true)
    }

    @Test
    fun `invokeSuspendingConsumer throws for invalid arg0`() {
        val function: (Any?, Any?) -> Unit? = { _, _ -> null }
        val invalidArg = "not a flux"

        val exception = assertThrows<IllegalArgumentException> {
            invokeSuspendingConsumer(function, invalidArg)
        }
        assertTrue(exception.message?.contains("must be a Flux") == true)
    }

    @Test
    fun `invokeSuspendingSupplier works with Flow result`() {
        val supplier: suspend () -> Flow<String> = {
            flowOf("a", "b", "c")
        }

        val result = invokeSuspendingSupplier(supplier as Function1<*, *>)
        val collected = result.collectList().block()

        assertEquals(listOf("a", "b", "c"), collected)
    }

    @Test
    fun `invokeSuspendingSupplier works with single value result`() {
        val supplier: suspend () -> String = {
            "single"
        }

        val result = invokeSuspendingSupplier(supplier as Function1<*, *>)
        val collected = result.collectList().block()

        assertEquals(listOf("single"), collected)
    }

    @Test
    fun `getSuspendingFunctionArgType delegates to getFlowTypeArguments`() {
        val flowType = object : ParameterizedType {
            override fun getActualTypeArguments() = arrayOf<java.lang.reflect.Type>(String::class.java)
            override fun getRawType() = Flow::class.java
            override fun getOwnerType() = null
            override fun getTypeName() = "kotlinx.coroutines.flow.Flow<java.lang.String>"
        }
        assertEquals(String::class.java, getSuspendingFunctionArgType(flowType))
    }

    @Test
    fun `getSuspendingFunctionArgType returns same type for non-Flow`() {
        assertEquals(String::class.java, getSuspendingFunctionArgType(String::class.java))
    }

    @Test
    fun `getFlowTypeArguments handles WildcardType`() {
        val wildcardType = object : WildcardType {
            override fun getUpperBounds() = arrayOf<java.lang.reflect.Type>(String::class.java)
            override fun getLowerBounds() = arrayOf<java.lang.reflect.Type>()
        }
        val flowType = object : ParameterizedType {
            override fun getActualTypeArguments() = arrayOf<java.lang.reflect.Type>(wildcardType)
            override fun getRawType() = Flow::class.java
            override fun getOwnerType() = null
            override fun getTypeName() = "kotlinx.coroutines.flow.Flow<? extends java.lang.String>"
        }
        assertEquals(String::class.java, getFlowTypeArguments(flowType))
    }

    @Test
    fun `getFlowTypeArguments returns parameterized type when actualTypeArguments is empty`() {
        val flowType = object : ParameterizedType {
            override fun getActualTypeArguments() = arrayOf<java.lang.reflect.Type>()
            override fun getRawType() = Flow::class.java
            override fun getOwnerType() = null
            override fun getTypeName() = "kotlinx.coroutines.flow.Flow"
        }
        assertEquals(flowType, getFlowTypeArguments(flowType))
    }

    @Test
    fun `getSuspendingFunctionReturnType extracts type from Continuation of Flow`() {
        val wildcardType = object : WildcardType {
            override fun getUpperBounds() = arrayOf<java.lang.reflect.Type>(Any::class.java)
            override fun getLowerBounds(): Array<java.lang.reflect.Type> {
                val innerFlowType = object : ParameterizedType {
                    override fun getActualTypeArguments() =
                        arrayOf<java.lang.reflect.Type>(String::class.java)
                    override fun getRawType() = Flow::class.java
                    override fun getOwnerType() = null
                    override fun getTypeName() = "kotlinx.coroutines.flow.Flow<java.lang.String>"
                }
                return arrayOf(innerFlowType)
            }
        }
        val continuationType = object : ParameterizedType {
            override fun getActualTypeArguments() = arrayOf<java.lang.reflect.Type>(wildcardType)
            override fun getRawType() = Continuation::class.java
            override fun getOwnerType() = null
            override fun getTypeName() =
                "kotlin.coroutines.Continuation<kotlinx.coroutines.flow.Flow<java.lang.String>>"
        }
        assertEquals(String::class.java, getSuspendingFunctionReturnType(continuationType))
    }

    @Test
    fun `getSuspendingFunctionReturnType returns same type for non-Continuation`() {
        assertEquals(String::class.java, getSuspendingFunctionReturnType(String::class.java))
    }

    @Test
    fun `isContinuationUnitType returns false for non-Continuation type`() {
        assertFalse(isContinuationUnitType(String::class.java))
    }

    @Test
    fun `isContinuationFlowType returns false for non-Continuation type`() {
        assertFalse(isContinuationFlowType(String::class.java))
    }

    @Test
    fun `invokeSuspendingFunction transforms Flow input to Flux output`() {
        val function: suspend (Flow<String>) -> Flow<String> = { flow ->
            flow.map { it.uppercase() }
        }

        val inputFlux = Flux.just("hello", "world")
        val result = invokeSuspendingFunction(function as Function2<*, *, *>, inputFlux)
        val collected = result.collectList().block()

        assertEquals(listOf("HELLO", "WORLD"), collected)
    }

    @Test
    suspend fun `invokeSuspendingConsumer processes Flow input`() {
        val processed = mutableListOf<String>()
        val consumer: suspend (Flow<String>) -> Unit = { flow ->
            runBlocking {
                flow.toList(processed)
            }
        }

        val inputFlux = Flux.just("a", "b", "c")
        invokeSuspendingConsumer(consumer as Function2<*, *, *>, inputFlux)

        delay(100)
        assertEquals(listOf("a", "b", "c"), processed)
    }
}
