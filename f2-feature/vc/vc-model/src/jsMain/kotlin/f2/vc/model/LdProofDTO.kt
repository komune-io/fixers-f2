package f2.vc.model

import f2.vc.model.command.AnySerializer
import kotlinx.serialization.SerialName

@JsExport
@JsName("LdProofDTO")
actual external interface LdProofDTO {
	actual val created: String
	actual val domain: String
	actual val challenge: String
	actual val proofPurpose: String
	actual val verificationMethod: String
	actual val type: String
	actual val jws: String
}

@JsExport
@JsName("VCDTO")
actual external interface VCDTO<T> {
	@SerialName("@context")
	actual val context: List<String>?
	actual val type: String?
	actual val id: String
	actual val issuer: String
	actual val issuanceDate: String
	actual val credentialSubject: T
	actual val proof: LdProofDTO
}
