package f2.bdd.blockhound

import java.util.concurrent.atomic.AtomicReference
import org.junit.platform.launcher.LauncherSession
import org.junit.platform.launcher.LauncherSessionListener
import reactor.blockhound.BlockHound
import reactor.blockhound.BlockingMethod
import reactor.blockhound.BlockingOperationError
import reactor.blockhound.integration.BlockHoundIntegration

/**
 * Installs BlockHound once per JUnit session, as soon as this module is on the test runtime classpath.
 * The test JVM needs `-XX:+AllowRedefinitionToAddDeleteMethods` (JDK 13 and later).
 */
class BlockHoundSession : LauncherSessionListener {
	override fun launcherSessionOpened(session: LauncherSession) {
		BlockHound.install(KnownAllowances)
	}
}

/** Blocking calls that are safe on a non-blocking thread, found in Kotlin, the JDK and Lettuce. */
object KnownAllowances : BlockHoundIntegration {
	override fun applyTo(builder: BlockHound.Builder) {
		// read once per JVM
		builder.allowBlockingCallsInside("kotlin.reflect.jvm.internal.impl.builtins.KotlinBuiltIns", "createBuiltInsModule")
		// cached after the first read
		builder.allowBlockingCallsInside("java.util.ResourceBundle", "getBundle")
		// /dev/urandom never waits
		builder.allowBlockingCallsInside("sun.security.provider.NativePRNG\$RandomIO", "implNextBytes")
		// Lettuce's write lock, held for a counter update or a channel (de)activation
		builder.allowBlockingCallsInside("io.lettuce.core.protocol.SharedLock", "incrementWriters")
		builder.allowBlockingCallsInside("io.lettuce.core.protocol.SharedLock", "doExclusive")
		builder.blockingMethodCallback { method -> BlockingCalls.report(blockingCall(method)) }
	}
}

/** BlockHound's own error, its trace starting at the blocking method rather than in BlockHound. */
private fun blockingCall(method: BlockingMethod) = BlockingOperationError(method).apply {
	val probe = stackTrace.indexOfFirst {
		it.className == "reactor.blockhound.BlockHoundRuntime" && it.methodName == "checkBlocking"
	}
	if (probe >= 0) stackTrace = stackTrace.copyOfRange(probe + 1, stackTrace.size)
}

/**
 * Thrown, BlockHound's error leaves the library that made the call in whatever state it reached, which can hang
 * every later caller; [RECORD_ONLY] lets the call through instead, and the next [requireNone] fails with it.
 */
object BlockingCalls {
	const val RECORD_ONLY = "f2.blockhound.record-only"

	private val first = AtomicReference<BlockingOperationError?>()

	internal fun report(error: BlockingOperationError) {
		first.compareAndSet(null, error)
		if (!java.lang.Boolean.getBoolean(RECORD_ONLY)) throw error
	}

	fun requireNone() {
		first.getAndSet(null)?.let { throw IllegalStateException("A blocking call was made: ${it.message}", it) }
	}
}
