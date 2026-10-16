package f2.spring

import kotlinx.coroutines.CoroutineDispatcher

internal val DEFAULT_DISPATCHER_TYPE = F2DispatcherType.IO

/**
 * The dispatcher F2 starts suspending functions, suppliers and consumers on, and collects their
 * result flow on. Set once at startup from `f2.function.dispatcher`; process-wide, because
 * spring-cloud-function reaches `CoroutinesUtils` through static calls.
 */
internal object F2FunctionDispatcher {
    @Volatile
    var current: CoroutineDispatcher = DEFAULT_DISPATCHER_TYPE.coroutineDispatcher
}
