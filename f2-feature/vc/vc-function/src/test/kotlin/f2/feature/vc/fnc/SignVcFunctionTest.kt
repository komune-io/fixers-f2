package f2.feature.vc.fnc

import f2.vc.functions.VCSignCommand
import f2.vc.functions.VCSignFunction
import f2.vc.functions.VCSignResult
import f2.vc.functions.VCVerifyCommand
import kotlinx.coroutines.flow.*
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
		val verifyF2: Any = catalog.lookup("verify")
		Assertions.assertThat(verifyF2).isNotNull
	}

	@Test
	fun signVcClaims() = runBlocking<Unit> {
		val identifier = UUID.randomUUID().toString()
		val claimsValue = "theCLaims"
		val claims = mapOf("value" to claimsValue as Any)
		val cmd = VCSignCommand(identifier, claims)

		val result = signVcFunction.sign().invoke(listOf(cmd).asFlow()).first()

		val verify = signVcFunction.verify().invoke(listOf(VCVerifyCommand(result.vc)).asFlow()).first()

		Assertions.assertThat(result.vc.credentialSubject.get("value")).isEqualTo(claimsValue)
		Assertions.assertThat(verify.isValid).isTrue()
	}
}