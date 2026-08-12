package f2.spring

import org.springframework.beans.factory.InitializingBean
import org.springframework.boot.autoconfigure.AutoConfiguration
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty

/**
 * Runs [VendoredPatchVerifier] at startup.
 *
 * Lives in `f2.spring` (a package F2 owns exclusively) on purpose: a check hosted by one of the
 * vendored `org.springframework.cloud.function` classes would itself be shadowed away in exactly
 * the scenario it is meant to detect.
 *
 * Opt out with `f2.function.vendored-patch-check.enabled=false`.
 */
@AutoConfiguration
@ConditionalOnProperty(
    prefix = "f2.function.vendored-patch-check",
    name = ["enabled"],
    havingValue = "true",
    matchIfMissing = true
)
class VendoredPatchVerifierAutoConfiguration : InitializingBean {

    override fun afterPropertiesSet() {
        VendoredPatchVerifier.verify()
    }
}
