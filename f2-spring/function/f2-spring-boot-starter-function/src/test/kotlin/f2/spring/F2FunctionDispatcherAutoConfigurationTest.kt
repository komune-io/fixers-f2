package f2.spring

import kotlinx.coroutines.Dispatchers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner

class F2FunctionDispatcherAutoConfigurationTest {

    private val runner = ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(F2FunctionDispatcherAutoConfiguration::class.java))

    @AfterEach
    fun restoreDefault() {
        F2FunctionDispatcher.current = DEFAULT_DISPATCHER_TYPE.coroutineDispatcher
    }

    @Test
    fun `IO when the property is absent`() {
        F2FunctionDispatcher.current = Dispatchers.Unconfined
        runner.run {
            assertThat(F2FunctionDispatcher.current).isSameAs(Dispatchers.IO)
        }
    }

    @Test
    fun `the property selects the dispatcher`() {
        runner.withPropertyValues("f2.function.dispatcher=default").run {
            assertThat(F2FunctionDispatcher.current).isSameAs(Dispatchers.Default)
        }
    }
}
