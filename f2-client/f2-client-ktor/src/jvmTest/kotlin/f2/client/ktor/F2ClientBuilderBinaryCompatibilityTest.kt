package f2.client.ktor

import f2.client.F2Client
import f2.client.F2ClientType
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import kotlin.coroutines.Continuation
import kotlin.coroutines.EmptyCoroutineContext
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * Callers compiled when `get` was `suspend` link to these JVM signatures; each is called
 * the way such a caller calls it, through the method it was compiled against.
 */
class F2ClientBuilderBinaryCompatibilityTest {

    private val continuation = Continuation<Any?>(EmptyCoroutineContext) { }
    private val config: (Any) -> Unit = { }
    private val extensions = Class.forName("f2.client.ktor.GetKt")

    @Test
    fun `get with config keeps its suspend signature`() {
        val get = F2ClientBuilder::class.java.method(
            "get", String::class.java, Function1::class.java, Continuation::class.java
        )

        assertThat(get.call(F2ClientBuilder, URL, config, continuation).type).isEqualTo(F2ClientType.HTTP)
    }

    @Test
    fun `get with config keeps its suspend signature with default arguments`() {
        val get = F2ClientBuilder::class.java.method("get\$default", *DEFAULT_PARAMETERS)

        assertThat(get.call(null, F2ClientBuilder, URL, null, continuation, CONFIG_DEFAULTED, null).type)
            .isEqualTo(F2ClientType.HTTP)
    }

    @Test
    fun `get without config keeps its suspend signature`() {
        val get = F2ClientBuilder::class.java.method("get", String::class.java, Continuation::class.java)

        assertThat(get.call(F2ClientBuilder, URL, continuation).type).isEqualTo(F2ClientType.HTTP)
    }

    @Test
    fun `get extension keeps its suspend signature`() {
        val get = extensions.method(
            "get", F2ClientBuilder::class.java, String::class.java, Function1::class.java, Continuation::class.java
        )

        assertThat(get.call(null, F2ClientBuilder, URL, config, continuation).type).isEqualTo(F2ClientType.HTTP)
    }

    @Test
    fun `get extension keeps its suspend signature with default arguments`() {
        val get = extensions.method("get\$default", *DEFAULT_PARAMETERS)

        assertThat(get.call(null, F2ClientBuilder, URL, null, continuation, CONFIG_DEFAULTED, null).type)
            .isEqualTo(F2ClientType.HTTP)
    }

    @Test
    fun `suspend get still rejects unsupported protocols`() {
        val get = F2ClientBuilder::class.java.method("get", String::class.java, Continuation::class.java)

        val thrown = runCatching { get.call(F2ClientBuilder, "ftp://localhost", continuation) }.exceptionOrNull()

        assertThat(thrown).isInstanceOf(IllegalArgumentException::class.java)
    }

    private fun Class<*>.method(name: String, vararg parameters: Class<*>): Method =
        getMethod(name, *parameters)

    private fun Method.call(receiver: Any?, vararg arguments: Any?): F2Client =
        try {
            invoke(receiver, *arguments) as F2Client
        } catch (e: InvocationTargetException) {
            throw e.targetException
        }

    private companion object {
        const val URL = "http://localhost:8080"
        const val CONFIG_DEFAULTED = 0b10
        val DEFAULT_PARAMETERS = arrayOf(
            F2ClientBuilder::class.java,
            String::class.java,
            Function1::class.java,
            Continuation::class.java,
            Int::class.javaPrimitiveType!!,
            Any::class.java,
        )
    }
}
