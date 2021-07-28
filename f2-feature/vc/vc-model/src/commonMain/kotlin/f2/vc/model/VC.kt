package f2.vc.model

import f2.vc.model.command.AnySerializer
import kotlinx.serialization.Serializable
import kotlin.js.JsExport
import kotlin.js.JsName

expect interface VC<T> {
	//	val context: List<String>?
//		get() = listOf("https://www.w3.org/2018/credentials/v1")
	val type: String?
	val id: String
	val issuer: String
	val issuanceDate: String
	val credentialSubject: T
	val proof: LdProof
}

@JsName("LdProof")
expect interface LdProof {
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
//	@SerialName("@context")
//	override val context: List<String>?,
	override val type: String?,
	override val id: String,
	override val issuer: String,
	override val issuanceDate: String,
	override val credentialSubject: T,
	override val proof: LdProofBase,
) : VC<T>

@Serializable
@JsExport
@JsName("VCBaseGen")
class VCBaseGen(
//	@SerialName("@context")
//	override val context: List<String>?,
	override val type: String?,
	override val id: String,
	override val issuer: String,
	override val issuanceDate: String,
	override val credentialSubject: Map<String, @Serializable(with = AnySerializer::class) Any>,
	override val proof: LdProofBase,
) : VC<Map<String, Any>>

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
	override val jws: String,
) : LdProof