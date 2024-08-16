package f2.dsl.fnc.operators

import kotlin.jvm.JvmName
import kotlinx.coroutines.flow.DEFAULT_CONCURRENCY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flattenMerge

/**
 * Extension function to flatten a Flow of Flows concurrently with a specified concurrency level.
 *
 * @param concurrency The maximum number of concurrent flows to be collected. Defaults to [DEFAULT_CONCURRENCY].
 * @return A Flow emitting elements from the inner flows.
 */
fun <R> Flow<Flow<R>>.flattenConcurrently(
    concurrency: Int = DEFAULT_CONCURRENCY
): Flow<R> = flattenMerge(concurrency)

/**
 * Extension function to flatten a Flow of Lists concurrently with a specified concurrency level.
 *
 * @param concurrency The maximum number of concurrent lists to be collected. Defaults to [DEFAULT_CONCURRENCY].
 * @return A Flow emitting elements from the inner lists.
 */
@JvmName("flattenConcurrentlyList")
fun <R> Flow<List<R>>.flattenConcurrently(
    concurrency: Int = DEFAULT_CONCURRENCY
): Flow<R> = flatMapMerge(concurrency) { list ->
    list.asFlow()
}
