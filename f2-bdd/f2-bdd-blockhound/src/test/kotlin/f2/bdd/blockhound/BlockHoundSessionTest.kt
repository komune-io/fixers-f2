package f2.bdd.blockhound

import java.util.UUID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import reactor.blockhound.BlockingOperationError
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

// These tests block on purpose, on the dispatchers BlockHound watches.
@Suppress("SleepInsteadOfDelay", "InjectDispatcher")
class BlockHoundSessionTest {

    private fun clearPendingCall() {
        try {
            BlockingCalls.requireNone()
        } catch (_: IllegalStateException) {
            // a call left by an earlier test
        }
    }

    private suspend fun blockingErrorOnDefault(): BlockingOperationError? = try {
        withContext(Dispatchers.Default) { Thread.sleep(1) }
        null
    } catch (error: BlockingOperationError) {
        error
    }

    @Test
    suspend fun `a blocking call on Dispatchers Default fails`() {
        val thrown = blockingErrorOnDefault()
        assertThat(thrown).isInstanceOf(BlockingOperationError::class.java)
        assertThat(thrown!!.stackTrace.first().className).isEqualTo("java.lang.Thread")
    }

    @Test
    fun `a blocking call on a Reactor non-blocking scheduler fails`() {
        assertThatThrownBy {
            Mono.fromCallable { Thread.sleep(1) }.subscribeOn(Schedulers.parallel()).block()
        }.hasCauseInstanceOf(BlockingOperationError::class.java)
    }

    @Test
    suspend fun `a random UUID drawn on Dispatchers Default passes`() {
        assertThat(withContext(Dispatchers.Default) { UUID.randomUUID() }).isNotNull
    }

    @Test
    suspend fun `a blocking call on Dispatchers IO passes`() {
        assertThat(withContext(Dispatchers.IO) { Thread.sleep(1); "slept" }).isEqualTo("slept")
    }

    @Test
    suspend fun `a blocking call fails the next check only, with the call as its cause`() {
        clearPendingCall()
        assertThat(blockingErrorOnDefault())
            .isInstanceOf(BlockingOperationError::class.java)
        assertThatThrownBy { BlockingCalls.requireNone() }
            .isInstanceOf(IllegalStateException::class.java)
            .cause()
            .isInstanceOf(BlockingOperationError::class.java)
        BlockingCalls.requireNone()
    }

    @Test
    suspend fun `in record-only mode a blocking call runs through, and the next check fails with it`() {
        clearPendingCall()
        System.setProperty(BlockingCalls.RECORD_ONLY, "true")
        try {
            assertThat(withContext(Dispatchers.Default) { Thread.sleep(1); "slept" }).isEqualTo("slept")
        } finally {
            System.clearProperty(BlockingCalls.RECORD_ONLY)
        }
        assertThatThrownBy { BlockingCalls.requireNone() }
            .cause()
            .isInstanceOf(BlockingOperationError::class.java)
            .extracting { it.stackTrace.first().className }
            .isEqualTo("java.lang.Thread")
    }
}
