package f2.spring

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatCode
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.catalog.SimpleFunctionRegistry

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
}
