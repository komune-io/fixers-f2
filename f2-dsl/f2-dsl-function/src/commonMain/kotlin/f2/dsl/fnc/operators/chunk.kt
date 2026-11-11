package f2.dsl.fnc.operators

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Default size of each chunk.
 */
const val CHUNK_DEFAULT_SIZE: Int = 128

/**
 * Extension function to chunk elements of a Flow into lists
 * of a specified size and apply a transformation function to each chunk.
 *
 * @param T The type of the elements.
 * @param R The type of the transformed elements.
 * @param size The size of each chunk.
 * @param fnc A suspend function to apply to each chunk of elements.
 * @return A Flow emitting lists of transformed elements.
 */
fun <T, R> Flow<T>.chunk(
    size: Int = CHUNK_DEFAULT_SIZE,
    fnc: suspend (t: List<T>) -> List<R>
): Flow<List<R>> = flow {
    val buffer = mutableListOf<T>()
    collect { value ->
        buffer.add(value)
        if (buffer.size == size) {
            emit(fnc(ArrayList(buffer))) // Apply fnc to the chunk and emit the result
            buffer.clear()
        }
    }
    if (buffer.isNotEmpty()) {
        emit(fnc(ArrayList(buffer))) // Apply fnc to remaining elements and emit the result
    }
}

/**
 * Extension function to chunk elements of a Flow into lists of a specified size
 * and apply a transformation function to each chunk.
 *
 * @param T The type of the elements.
 * @param R The type of the transformed elements.
 * @param size The size of each chunk.
 * @param fnc A suspend function to apply to each chunk of elements.
 * @return A Flow emitting lists of transformed elements.
 */
fun <T, R> Flow<T>.chunkFlow(
    size: Int = CHUNK_DEFAULT_SIZE,
    fnc: suspend (t: List<T>) -> Flow<R>
): Flow<Flow<R>> = flow {
    val buffer = mutableListOf<T>()
    collect { value ->
        buffer.add(value)
        if (buffer.size == size) {
            emit(fnc(buffer))
            buffer.clear()
        }
    }
    if (buffer.isNotEmpty()) {
        emit(fnc(buffer))
    }
}

/**
 * Extension function to chunk elements of a Flow into lists of a specified size.
 *
 * @param T The type of the elements.
 * @param size The size of each chunk.
 * @return A Flow emitting lists of elements.
 */
fun <T> Flow<T>.chunk(size: Int = CHUNK_DEFAULT_SIZE): Flow<List<T>> = chunk(size) { it }
