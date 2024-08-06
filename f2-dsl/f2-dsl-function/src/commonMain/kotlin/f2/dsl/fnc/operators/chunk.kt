package f2.dsl.fnc.operators

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

/**
 * Extension function to chunk elements of a Flow into lists
 * of a specified size and apply a transformation function to each chunk.
 *
 * @param props An instance of [InvokeChunk] containing the size of each chunk.
 * @param fnc A suspend function to apply to each chunk of elements.
 * @return A Flow emitting lists of transformed elements.
 */
fun <T, R> Flow<T>.chunk(
    props: InvokeChunk,
    fnc: suspend (t: List<T>) -> List<R>
): Flow<List<R>> = flow {
    val buffer = mutableListOf<T>()
    collect { value ->
        buffer.add(value)
        if (buffer.size == props.size) {
            emit(fnc(ArrayList(buffer))) // Apply fnc to the chunk and emit the result
            buffer.clear()
        }
    }
    if (buffer.isNotEmpty()) {
        emit(fnc(ArrayList(buffer))) // Apply fnc to remaining elements and emit the result
    }
}

/**
 * Data class representing the properties for chunking a Flow.
 *
 * @property size The size of each chunk.
 */
class InvokeChunk(
    val size: Int = 128
)


/**
 * Extension function to chunk elements of a Flow into lists of a specified size.
 *
 * @param props An instance of [InvokeChunk] containing the size of each chunk.
 * @return A Flow emitting lists of elements.
 */
fun <T> Flow<T>.chunk(props: InvokeChunk): Flow<List<T>> = chunk(props, {it})

