package f2.feature.vc.fnc

import f2.vc.VCSignCommand
import f2.vc.VCVerifyCommand
import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.function.context.FunctionCatalog
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension::class)
@SpringBootTest
class SignVcFunctionTest {

	@Autowired
	lateinit var signVcFunction: SignVcFunction

	@Autowired
	lateinit var catalog: FunctionCatalog

	@Test
	fun checkFuntionIsRegistred() = runBlocking<Unit> {
		val signF2: Any = catalog.lookup("sign")
		Assertions.assertThat(signF2).isNotNull
	}

	@Test
	fun signVcClaims() = runBlocking<Unit> {
		val identifier = UUID.randomUUID().toString()
		val claimsValue = "theCLaims"
		val claims = mapOf("value" to claimsValue as Any)
		val cmd = VCSignCommand(identifier, claims)

		val vc = signVcFunction.sign(cmd)

		val verify = signVcFunction.verify(VCVerifyCommand(vc))

		Assertions.assertThat(vc.credentialSubject.get("value")).isEqualTo(claimsValue)
		Assertions.assertThat(verify).isTrue()
	}
}