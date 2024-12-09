package f2.dsl.fnc.operators

import kotlin.js.JsExport
import kotlinx.coroutines.flow.DEFAULT_CONCURRENCY
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.Serializable

/**
 * Extension function to batch elements of a Flow into lists of a specified size and
 * apply a transformation function to each batch.
 *
 * @param batch An instance of [Batch] containing the size of each batch and the concurrency level.
 * @param fnc A suspend function to apply to each batch of elements.
 * @return A Flow emitting transformed elements.
 */
fun <T, R> Flow<T>.batch(
    batch: Batch,
    fnc: suspend (t: List<T>) -> List<R>
): Flow<R> = chunk(batch.size, fnc).flattenConcurrently(batch.concurrency)

/**
 * Default concurrency level for batching.
 */
val BATCH_DEFAULT_CONCURRENCY: Int = DEFAULT_CONCURRENCY

/**
 * Default concurrency level for batching.
 */
val BATCH_DEFAULT_SIZE: Int = CHUNK_DEFAULT_SIZE

/**
 * Data class representing the configuration for batching elements in a Flow.
 *
 * @property size The size of each batch.
 * @property concurrency The number of concurrently collected batches.
 */
@JsExport
@Serializable
data class Batch(
    val size: Int = BATCH_DEFAULT_SIZE,
    val concurrency: Int = BATCH_DEFAULT_CONCURRENCY
)
