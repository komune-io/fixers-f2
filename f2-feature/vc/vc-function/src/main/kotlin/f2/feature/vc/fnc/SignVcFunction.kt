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
import f2.dsl.fnc.f2Function
import f2.feature.vc.fnc.config.CredentialsKey
import f2.vc.model.VCGen
import f2.vc.model.VCFunction
import f2.vc.model.command.VCSignFunction
import f2.vc.model.command.VCSignResult
import f2.vc.model.command.VCVerifyFunction
import f2.vc.model.command.VCVerifyResult
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.LocalDateTime
import java.util.UUID

@Configuration
class SignVcFunction(private val objectMapper: ObjectMapper,
	private val credentialsKey: CredentialsKey,
) : VCFunction {
	private val vcSign = VCSign()
	private val vcVerifier = VCVerifier()

	@Bean
	override fun sign(): VCSignFunction = f2Function { cmd ->
		val credentials = signVc(cmd.identifier, cmd.claims)
		val vc: String = objectMapper.writeValueAsString(credentials.asJson())
		val vcbase = Json {
			ignoreUnknownKeys = true
		}.decodeFromString<VCGen>(vc)
		VCSignResult(vcbase)
	}

	@Bean
	override fun verify(): VCVerifyFunction = f2Function { cmd ->
		val verifier = Verifier.rs256Verifier(credentialsKey.getRSAPublicKey())
		val map: MutableMap<String, Any> = objectMapper.convertValue(cmd.claims)
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
			.withId("https://credentials.mobilite.eco/travelaction/$identifier")
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
