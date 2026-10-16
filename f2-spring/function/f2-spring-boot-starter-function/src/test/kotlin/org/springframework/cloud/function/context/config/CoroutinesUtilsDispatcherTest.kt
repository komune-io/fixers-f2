package org.springframework.cloud.function.context.config

import ch.qos.logback.classic.Logger
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.AppenderBase
import f2.spring.DEFAULT_DISPATCHER_TYPE
import f2.spring.F2FunctionDispatcher
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.reactor.ReactorContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import reactor.core.publisher.Flux
import reactor.util.context.Context

private const val USER_KEY = "user"
private const val WORKER = "f2-test-worker"
private const val COROUTINES_UTILS = "org.springframework.cloud.function.context.config.CoroutinesUtils"

class CoroutinesUtilsDispatcherTest {

    private val executor = Executors.newSingleThreadExecutor { Thread(it, WORKER) }

    @BeforeEach
    fun useTestDispatcher() {
        F2FunctionDispatcher.current = executor.asCoroutineDispatcher()
    }

    @AfterEach
    fun restoreDefault() {
        F2FunctionDispatcher.current = DEFAULT_DISPATCHER_TYPE.coroutineDispatcher
        executor.shutdownNow()
    }

    @Test
    fun `function body runs on the configured dispatcher and reads the subscriber context`() {
        val seen = mutableListOf<String>()
        val function: suspend (Flow<String>) -> Flow<String> = { input ->
            seen += "invoke ${threadName()} ${userInContext()}"
            input.map { "$it ${threadName()} ${userInContext()}" }
        }

        val result = invokeSuspendingFunction(function as Function2<*, *, *>, Flux.just("a"))
            .contextWrite(Context.of(USER_KEY, "alice"))
            .collectList().block()

        assertThat(seen).containsExactly("invoke $WORKER alice")
        assertThat(result).containsExactly("a $WORKER alice")
    }

    @Test
    fun `supplier flow runs on the configured dispatcher and reads the subscriber context`() {
        val supplier: suspend () -> Flow<String> = {
            flow { emit("${threadName()} ${userInContext()}") }
        }

        val result = invokeSuspendingSupplier(supplier as Function1<*, *>)
            .contextWrite(Context.of(USER_KEY, "bob"))
            .collectList().block()

        assertThat(result).containsExactly("$WORKER bob")
    }

    @Test
    fun `the IO dispatcher runs the body on an IO worker`() {
        F2FunctionDispatcher.current = Dispatchers.IO
        val function: suspend (Flow<String>) -> Flow<String> = { input ->
            input.map { threadName() }
        }

        val result = invokeSuspendingFunction(function as Function2<*, *, *>, Flux.just("a"))
            .collectList().block()

        assertThat(result!!.single() as String).startsWith("DefaultDispatcher-worker")
    }

    @Test
    fun `consumer runs on the configured dispatcher`() {
        val done = CountDownLatch(1)
        var thread: String? = null
        val consumer: suspend (Flow<String>) -> Unit = { input ->
            input.collect { thread = threadName() }
            done.countDown()
        }

        invokeSuspendingConsumer(consumer as Function2<*, *, *>, Flux.just("a"))

        assertThat(done.await(5, TimeUnit.SECONDS)).isTrue()
        assertThat(thread).isEqualTo(WORKER)
    }

    @Test
    fun `consumer failure is logged`() {
        val logged = CountDownLatch(1)
        val appender = object : AppenderBase<ILoggingEvent>() {
            override fun append(event: ILoggingEvent) {
                if (event.throwableProxy?.message == "boom") logged.countDown()
            }
        }
        val logger = LoggerFactory.getLogger(COROUTINES_UTILS) as Logger
        appender.start()
        logger.addAppender(appender)
        try {
            val consumer: suspend (Flow<String>) -> Unit = { error("boom") }

            invokeSuspendingConsumer(consumer as Function2<*, *, *>, Flux.just("a"))

            assertThat(logged.await(5, TimeUnit.SECONDS)).isTrue()
        } finally {
            logger.detachAppender(appender)
        }
    }

    private fun threadName() = Thread.currentThread().name.substringBefore(" @coroutine")

    private suspend fun userInContext(): String? =
        currentCoroutineContext()[ReactorContext]?.context?.getOrDefault<String>(USER_KEY, null)
}
