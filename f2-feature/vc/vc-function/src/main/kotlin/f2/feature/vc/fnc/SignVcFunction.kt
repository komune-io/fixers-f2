package f2.feature.vc.fnc

import city.smartb.iris.crypto.rsa.signer.Signer
import city.smartb.iris.crypto.rsa.verifier.Verifier
import city.smartb.iris.ldproof.LdProofBuilder
import city.smartb.iris.vc.VerifiableCredential
import city.smartb.iris.vc.VerifiableCredentialBuilder
import city.smartb.iris.vc.signer.VCSign
import city.smartb.iris.vc.signer.VCVerifier
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import f2.dsl.fnc.*
import f2.feature.vc.fnc.config.CredentialsKey
import f2.vc.model.VCBase
import f2.vc.model.VCBaseGen
import f2.vc.model.VCFunction
import f2.vc.model.command.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.*
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.util.*
import kotlin.reflect.full.memberProperties

@Configuration
class SignVcFunction(
	private val objectMapper: ObjectMapper,
	private val credentialsKey: CredentialsKey,
): VCFunction {
	private val vcSign = VCSign()
	private val vcVerifier = VCVerifier()

	@Bean
	fun signFunction(): F2Function<VCSignCommand, VCSignResult> = sign().expose()
	@Bean
	fun verifyFunction(): F2Function<VCVerifyCommand, VCVerifyResult> = verify().expose()

	@Bean
	fun testFnc(): F2Function<String, String> = {
		test().invoke(it)
	}

	@Bean
	fun testFnc2(): F2Function<String, String> = test().expose()

	fun test(): F2FunctionDeclaration<String, String> = declaration { cmd ->
		cmd.toUpperCase()
	}

	override fun sign(): VCSignFunction = declaration { cmd ->
		val credentials = signVc(cmd.identifier, cmd.claims)
		val vc: String = objectMapper.writeValueAsString(credentials.asJson())
		val vcbase = Json {
			ignoreUnknownKeys = true
		}.decodeFromString<VCBaseGen>(vc)
		VCSignResult(vcbase)
	}

	override fun verify(): VCVerifyFunction = declaration { cmd ->
		val verifier = Verifier.rs256Verifier(credentialsKey.getRSAPublicKey())
		val map: MutableMap<String, Any> = objectMapper.convertValue(cmd.claims)
		map[VerifiableCredential.JSON_LD_CONTEXT] = listOf("https://www.w3.org/2018/credentials/v1")
		val credentials = VerifiableCredential(map)
		val isValid = vcVerifier.verify(credentials, verifier) ?: false
		VCVerifyResult(isValid)
	}

	private fun <T> signVc(identifier: String, claim: T): VerifiableCredential {
		val created = LocalDateTime.now()
		val nonce: String = UUID.randomUUID().toString()

		val signer: Signer = Signer.rs256Signer(credentialsKey.getRSAPrivateKey())
		val vcBuild = VerifiableCredentialBuilder
			.create<T>()
			.withContextDefault()
			.withId("https://credentials.mobilite.eco/travelaction/${identifier}")
			.withType("VerifiableCredential")
			.withIssuanceDate(created.toString())
			.withIssuer("https://mobilite.eco")
			.withCredentialSubject(claim)

		val proofBuilder = LdProofBuilder.builder()
			.withCreated(created)
			.withDomain("mobilite.eco")
			.withChallenge(nonce)
			.withProofPurpose("assertionMethod")
			.withVerificationMethod("https://tripstampr.mobilite.eco/keys/1")

		return vcSign.sign(vcBuild, proofBuilder, signer)
	}

}