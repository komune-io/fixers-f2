package f2.vc

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

@Serializable
@JsExport
@JsName("VC")
interface VC<T> {
	@SerialName("@context")
	val context: List<String>
		get() = listOf("https://www.w3.org/2018/credentials/v1")
	val type: String
		get() = "VerifiableCredential"
	val id: String
	val issuer: String
	val issuanceDate: String
	val credentialSubject: T
	val proof: LdProof
}

@Serializable
@JsExport
@JsName("LdProof")
interface LdProof {
	val created: String
	val domain: String
	val challenge: String
	val proofPurpose: String
	val verificationMethod: String
	val type: String
	val jws: String
}

@Serializable
@JsExport
@JsName("VCBase")
class VCBase<T>(
	override val id: String,
	override val issuer: String,
	override val issuanceDate: String,
	override val credentialSubject: T,
	override val proof: LdProofBase
): VC<T>

@Serializable
@JsExport
@JsName("LdProofBase")
class LdProofBase(
	override val created: String,
	override val domain: String,
	override val challenge: String,
	override val proofPurpose: String,
	override val verificationMethod: String,
	override val type: String,
	override val jws: String
): LdProof