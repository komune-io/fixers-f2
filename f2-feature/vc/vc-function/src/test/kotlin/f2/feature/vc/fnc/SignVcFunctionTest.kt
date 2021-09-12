package f2.feature.vc.fnc

import f2.client.ktor.HTTP
import f2.feature.vc.client.vcClient
import f2.vc.model.command.VCSignCommand
import f2.vc.model.command.VCVerifyCommand
import java.util.UUID
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.cloud.function.context.FunctionCatalog
import org.springframework.test.context.junit.jupiter.SpringExtension

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SignVcFunctionTest {

	@LocalServerPort
	protected var port: Int = 0

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
		val claims = buildJsonObject {
			put("value", claimsValue)
		}
		val cmd = VCSignCommand(identifier, claims)

		val result = signVcFunction.sign().invoke(listOf(cmd).asFlow()).first()

		val verify = signVcFunction.verify().invoke(listOf(VCVerifyCommand(result.vc)).asFlow()).first()

//		Assertions.assertThat(result.vc.credentialSubject.get("value")).isEqualTo(claimsValue)
		Assertions.assertThat(verify.isValid).isTrue()
	}

	@Test
	fun signVcClaimsClient() = runBlocking {
		val client = vcClient(HTTP, "localhost", port)
		val identifier = UUID.randomUUID().toString()
		val claimsValue = "theCLaims"
		val claims = mapOf("value" to claimsValue)
		val cmd = VCSignCommand(identifier, claims)
		val signed = client.sign()
		val cmdFlow = listOf(cmd).asFlow()
		val result = signed.invoke(cmdFlow)
		result.collect()

//		val verify = signVcFunction.verify().invoke(listOf(VCVerifyCommand(result.vc)).asFlow()).first()
//
//		Assertions.assertThat(result.vc.credentialSubject.get("value")).isEqualTo(claimsValue)
//		Assertions.assertThat(verify.isValid).isTrue()
	}
}
