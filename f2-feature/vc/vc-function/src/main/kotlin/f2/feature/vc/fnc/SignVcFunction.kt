package f2.feature.vc.fnc

import f2.function.spring.adapter.flow
import city.smartb.iris.crypto.rsa.signer.Signer
import city.smartb.iris.crypto.rsa.verifier.Verifier
import city.smartb.iris.ldproof.LdProofBuilder
import city.smartb.iris.vc.VerifiableCredential
import city.smartb.iris.vc.VerifiableCredentialBuilder
import city.smartb.iris.vc.signer.VCSign
import city.smartb.iris.vc.signer.VCVerifier
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.convertValue
import f2.feature.vc.fnc.config.CredentialsKey
import f2.vc.model.VCFunction
import f2.vc.model.command.VCSignFunction
import f2.vc.model.command.VCSignResult
import f2.vc.model.command.VCVerifyFunction
import f2.vc.model.command.VCVerifyResult
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class SignVcFunction(
	private val objectMapper: ObjectMapper,
	private val credentialsKey: CredentialsKey,
): VCFunction<Map<String, Any>> {
	private val vcSign = VCSign()
	private val vcVerifier = VCVerifier()

	@Bean
	override fun sign(): VCSignFunction<Map<String, Any>> = flow { cmd ->
		val credentials = signVc(cmd.identifier, cmd.claims)
		val vc: VCJson<Map<String, Any>> = objectMapper.convertValue(credentials.asJson())
		VCSignResult(vc)
	}

	@Bean
	override fun verify(): VCVerifyFunction<Map<String, Any>> = flow { cmd ->
		val verifier = Verifier.rs256Verifier(credentialsKey.getRSAPublicKey())
		val map: MutableMap<String, Any> = objectMapper.convertValue(cmd.claims)
		val credentials = VerifiableCredential(map)
		val isValid = vcVerifier.verify(credentials, verifier) ?: false
		VCVerifyResult(isValid)
	}

	private suspend fun <T> signVc(identifier: String, claim: T): VerifiableCredential {
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