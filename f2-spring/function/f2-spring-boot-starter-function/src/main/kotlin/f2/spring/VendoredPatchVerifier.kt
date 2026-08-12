package f2.spring

import org.slf4j.LoggerFactory
import org.springframework.cloud.function.context.catalog.SimpleFunctionRegistry

/**
 * Guards against F2's vendored spring-cloud-function classes being shadowed by the upstream ones.
 *
 * `f2-spring-boot-starter-function` ships patched copies of several `spring-cloud-function-context`
 * classes at their original fully qualified names. Both jars therefore declare the same FQCNs and
 * which copy the JVM loads depends purely on classpath ordering. If upstream wins, all KOMUNE
 * patches (typed `List` deserialization, `ResponseStatusException` propagation, Kotlin coroutine
 * handling) silently disappear and message conversion misbehaves at runtime instead of at startup.
 *
 * This verifier looks for the [SimpleFunctionRegistry.KOMUNE_PATCH_MARKER] field, which only exists
 * on F2's copy.
 *
 * This is an interim guard. The durable fix is to publish `komune-io/spring-cloud-function` branch
 * `fixers/<version>` as a real artifact and substitute it via Gradle instead of shadowing FQCNs.
 */
object VendoredPatchVerifier {

    /** Name of the marker field declared only by F2's vendored [SimpleFunctionRegistry]. */
    const val MARKER_FIELD = "KOMUNE_PATCH_MARKER"

    private val logger = LoggerFactory.getLogger(VendoredPatchVerifier::class.java)

    /**
     * @return true when the loaded [SimpleFunctionRegistry] is F2's patched copy.
     */
    fun isPatchedCopyLoaded(): Boolean = runCatching {
        SimpleFunctionRegistry::class.java.getDeclaredField(MARKER_FIELD)
    }.isSuccess

    /**
     * Fails fast when the loaded [SimpleFunctionRegistry] is the upstream (unpatched) copy.
     *
     * @throws IllegalStateException when the patch was lost
     */
    fun verify() {
        if (isPatchedCopyLoaded()) {
            logger.debug("F2 vendored spring-cloud-function patches are active (loaded from {})", loadedFrom())
            return
        }
        val message = errorMessage()
        logger.error(message)
        throw IllegalStateException(message)
    }

    private fun loadedFrom(): String = runCatching {
        SimpleFunctionRegistry::class.java.protectionDomain?.codeSource?.location?.toString()
    }.getOrNull() ?: "unknown location"

    private fun errorMessage() = """
        F2 vendored spring-cloud-function patches are NOT active.
        '${SimpleFunctionRegistry::class.java.name}' was loaded from ${loadedFrom()}, which is not the
        KOMUNE-patched copy shipped by f2-spring-boot-starter-function (marker field '$MARKER_FIELD' is missing).
        The upstream spring-cloud-function-context jar won the classpath ordering, so typed List deserialization,
        ResponseStatusException propagation and Kotlin coroutine handling are all silently disabled.
        Remediation: make sure f2-spring-boot-starter-function precedes spring-cloud-function-context on the
        classpath (for a Spring Boot fat jar, check the dependency order of your build), and do not repackage
        F2 in a way that drops org/springframework/cloud/function/**.
        To boot anyway at your own risk, set f2.function.vendored-patch-check.enabled=false.
    """.trimIndent()
}
