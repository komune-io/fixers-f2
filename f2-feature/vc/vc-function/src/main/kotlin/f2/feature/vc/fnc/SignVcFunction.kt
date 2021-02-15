package f2.feature.vc.fnc

import city.smartb.f2.function.spring.annotation.F2
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
import f2.vc.VCFunction
import f2.vc.VCSignCommand
import f2.vc.VCVerifyCommand
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@F2
@Service
class SignVcFunction(
	private val objectMapper: ObjectMapper,
	private val credentialsKey: CredentialsKey,
): VCFunction<Map<String, out Any>> {
	private val vcSign = VCSign()
	private val vcVerifier = VCVerifier()

	@F2
	override suspend fun sign(cmd: VCSignCommand<Map<String, Any>>): VCJson<Map<String, Any>>  {
		val credentials = signVc(cmd.identifier, cmd.claims)
		return objectMapper.convertValue(credentials.asJson())
	}

	@F2
	override suspend fun verify(cmd: VCVerifyCommand<Map<String, Any>>): Boolean {
		val verifier = Verifier.rs256Verifier(credentialsKey.getRSAPublicKey())
		val map: MutableMap<String, Any> = objectMapper.convertValue(cmd.claims)
		val credentials = VerifiableCredential(map)
		return vcVerifier.verify(credentials, verifier) ?: false
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