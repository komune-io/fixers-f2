package f2.spring

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties

/**
 * Selects the dispatcher of suspending F2 functions with `f2.function.dispatcher`:
 * `io`, `default` or `unconfined`; [DEFAULT_DISPATCHER_TYPE] when absent.
 */
@AutoConfiguration
@EnableConfigurationProperties(F2FunctionProperties::class)
class F2FunctionDispatcherAutoConfiguration(
    private val properties: F2FunctionProperties,
) : InitializingBean {

    override fun afterPropertiesSet() {
        F2FunctionDispatcher.current = properties.dispatcher.coroutineDispatcher
    }
}

@ConfigurationProperties(prefix = "f2.function")
class F2FunctionProperties(
    val dispatcher: F2DispatcherType = DEFAULT_DISPATCHER_TYPE,
)

enum class F2DispatcherType(val coroutineDispatcher: CoroutineDispatcher) {
    IO(Dispatchers.IO),
    DEFAULT(Dispatchers.Default),
    UNCONFINED(Dispatchers.Unconfined),
}
