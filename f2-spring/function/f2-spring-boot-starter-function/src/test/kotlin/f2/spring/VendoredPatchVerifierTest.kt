package f2.spring

import java.lang.reflect.InvocationTargetException
import java.net.URI
import java.security.CodeSource
import java.security.ProtectionDomain
import java.security.cert.Certificate
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import org.springframework.asm.ClassWriter
import org.springframework.asm.Opcodes
import org.springframework.boot.autoconfigure.AutoConfigurations
import org.springframework.boot.test.context.runner.ApplicationContextRunner
import org.springframework.cloud.function.context.catalog.SimpleFunctionRegistry

private const val REGISTRY_CLASS_NAME = "org.springframework.cloud.function.context.catalog.SimpleFunctionRegistry"
private const val VERIFIER_CLASS_NAME = "f2.spring.VendoredPatchVerifier"
private const val OPT_OUT_PROPERTY = "f2.function.vendored-patch-check.enabled"
private const val UPSTREAM_JAR = "file:/fake/repo/spring-cloud-function-context.jar"

class VendoredPatchVerifierTest {

    @Test
    fun `the loaded SimpleFunctionRegistry is F2's patched copy`() {
        assertThat(VendoredPatchVerifier.isPatchedCopyLoaded()).isTrue()
    }

    @Test
    fun `verify passes on the current classpath`() {
        assertThatCode { VendoredPatchVerifier.verify() }.doesNotThrowAnyException()
    }

    @Test
    fun `the marker field is public static final and readable`() {
        val field = SimpleFunctionRegistry::class.java.getDeclaredField(VendoredPatchVerifier.MARKER_FIELD)
        assertThat(field.get(null)).isEqualTo("f2-spring-boot-starter-function")
    }

    @Test
    fun `startup auto configuration runs the verification`() {
        assertThatCode { VendoredPatchVerifierAutoConfiguration().afterPropertiesSet() }
            .doesNotThrowAnyException()
    }

    @Test
    fun `isPatchedCopyLoaded is false when the loaded registry does not declare the marker`() {
        val verifier = IsolatedVerifier(withMarker = false)
        assertThat(verifier.call("isPatchedCopyLoaded")).isEqualTo(false)
    }

    @Test
    fun `isPatchedCopyLoaded is true when the loaded registry declares the marker`() {
        val verifier = IsolatedVerifier(withMarker = true)
        assertThat(verifier.call("isPatchedCopyLoaded")).isEqualTo(true)
    }

    @Test
    fun `verify fails fast when the upstream registry shadowed the vendored one`() {
        val verifier = IsolatedVerifier(withMarker = false)
        assertThatThrownBy { verifier.call("verify") }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining(REGISTRY_CLASS_NAME)
            .hasMessageContaining(UPSTREAM_JAR)
            .hasMessageContaining(VendoredPatchVerifier.MARKER_FIELD)
            .hasMessageContaining("$OPT_OUT_PROPERTY=false")
    }

    @Test
    fun `the failure message falls back to an unknown location when the registry has no code source`() {
        val verifier = IsolatedVerifier(withMarker = false, registryLocation = null)
        assertThatThrownBy { verifier.call("verify") }
            .isInstanceOf(IllegalStateException::class.java)
            .hasMessageContaining("was loaded from unknown location")
    }

    @Test
    fun `verify passes as soon as the loaded registry declares the marker`() {
        val verifier = IsolatedVerifier(withMarker = true)
        assertThatCode { verifier.call("verify") }.doesNotThrowAnyException()
    }

    @Test
    fun `the auto configuration is applied when the opt out property is absent`() {
        contextRunner().run { context ->
            assertThat(context).hasNotFailed()
            assertThat(context).hasSingleBean(VendoredPatchVerifierAutoConfiguration::class.java)
        }
    }

    @Test
    fun `the auto configuration is applied when the opt out property is true`() {
        contextRunner().withPropertyValues("$OPT_OUT_PROPERTY=true").run { context ->
            assertThat(context).hasSingleBean(VendoredPatchVerifierAutoConfiguration::class.java)
        }
    }

    @Test
    fun `the opt out property short circuits the check`() {
        contextRunner().withPropertyValues("$OPT_OUT_PROPERTY=false").run { context ->
            assertThat(context).hasNotFailed()
            assertThat(context).doesNotHaveBean(VendoredPatchVerifierAutoConfiguration::class.java)
        }
    }

    private fun contextRunner() = ApplicationContextRunner()
        .withConfiguration(AutoConfigurations.of(VendoredPatchVerifierAutoConfiguration::class.java))
}

/**
 * Reflective handle on a copy of [VendoredPatchVerifier] re-defined by [ShadowedRegistryClassLoader], so that it
 * resolves `SimpleFunctionRegistry` to a stub instead of the one on the test classpath.
 *
 * @param withMarker whether the stub declares [VendoredPatchVerifier.MARKER_FIELD], i.e. stands for F2's vendored
 * copy (`true`) or for the upstream one (`false`).
 * @param registryLocation code source the stub is loaded from, `null` for a class with no known location.
 */
private class IsolatedVerifier(withMarker: Boolean, registryLocation: String? = UPSTREAM_JAR) {

    private val type = ShadowedRegistryClassLoader(registryStub(withMarker), registryLocation)
        .loadClass(VERIFIER_CLASS_NAME)
    private val instance = type.getField("INSTANCE").get(null)

    fun call(method: String): Any? = try {
        type.getMethod(method).invoke(instance)
    } catch (e: InvocationTargetException) {
        throw e.targetException
    }
}

/**
 * Reproduces the "upstream jar won the classpath ordering" scenario the verifier guards against: it re-defines
 * [VendoredPatchVerifier] against the supplied `SimpleFunctionRegistry` bytes and delegates everything else to
 * the regular test class loader.
 */
private class ShadowedRegistryClassLoader(
    private val registryBytes: ByteArray,
    registryLocation: String?
) : ClassLoader(VendoredPatchVerifierTest::class.java.classLoader) {

    private val registryDomain = registryLocation?.let { location ->
        ProtectionDomain(CodeSource(URI(location).toURL(), null as Array<Certificate>?), null)
    }

    override fun loadClass(name: String, resolve: Boolean): Class<*> {
        val shadowed = findLoadedClass(name) ?: shadowedDefinitionOf(name)?.let { (bytes, domain) ->
            defineClass(name, bytes, 0, bytes.size, domain)
        }
        if (shadowed != null && resolve) {
            resolveClass(shadowed)
        }
        return shadowed ?: super.loadClass(name, resolve)
    }

    private fun shadowedDefinitionOf(name: String): Pair<ByteArray, ProtectionDomain?>? = when (name) {
        REGISTRY_CLASS_NAME -> registryBytes to registryDomain
        // Re-defined with the test's own protection domain: a class without a code source location would be
        // skipped by the coverage agent, hiding the very branches these tests exercise.
        VERIFIER_CLASS_NAME -> classFileOf(name) to VendoredPatchVerifierTest::class.java.protectionDomain
        else -> null
    }

    private fun classFileOf(name: String): ByteArray {
        val path = "${name.replace('.', '/')}.class"
        val stream = checkNotNull(parent.getResourceAsStream(path)) { "Cannot locate class file $path" }
        return stream.use { it.readBytes() }
    }
}

/**
 * Bytes of a bare `SimpleFunctionRegistry` standing in for either copy of the class: the upstream one has no
 * marker field, F2's vendored one has it.
 */
private fun registryStub(withMarker: Boolean): ByteArray {
    val writer = ClassWriter(0)
    writer.visit(
        Opcodes.V17,
        Opcodes.ACC_PUBLIC or Opcodes.ACC_SUPER,
        REGISTRY_CLASS_NAME.replace('.', '/'),
        null,
        "java/lang/Object",
        null
    )
    if (withMarker) {
        writer.visitField(
            Opcodes.ACC_PUBLIC or Opcodes.ACC_STATIC or Opcodes.ACC_FINAL,
            VendoredPatchVerifier.MARKER_FIELD,
            "Ljava/lang/String;",
            null,
            "f2-spring-boot-starter-function"
        ).visitEnd()
    }
    writer.visitEnd()
    return writer.toByteArray()
}
