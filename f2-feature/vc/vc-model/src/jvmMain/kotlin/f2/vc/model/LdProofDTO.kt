package f2.vc.model

import com.fasterxml.jackson.annotation.JsonProperty

actual interface LdProofDTO {
	actual val created: String
	actual val domain: String
	actual val challenge: String
	actual val proofPurpose: String
	actual val verificationMethod: String
	actual val type: String
	actual val jws: String
}

actual interface VCDTO<T> {
	@get:JsonProperty("@context")
	actual val context: List<String>?
	actual val type: String?
	actual val id: String
	actual val issuer: String
	actual val issuanceDate: String
	actual val credentialSubject: T
	actual val proof: LdProofDTO
}