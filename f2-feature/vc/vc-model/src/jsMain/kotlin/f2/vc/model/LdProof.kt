package f2.vc.model

@JsExport
@JsName("LdProof")
actual external interface LdProof {
	actual val created: String
	actual val domain: String
	actual val challenge: String
	actual val proofPurpose: String
	actual val verificationMethod: String
	actual val type: String
	actual val jws: String
}

@JsExport
@JsName("VC")
actual external interface VC<T> {
	actual val type: String?
	actual val id: String
	actual val issuer: String
	actual val issuanceDate: String
	actual val credentialSubject: T
	actual val proof: LdProof
}