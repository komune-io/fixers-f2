package f2.feature.vc.fnc

import com.fasterxml.jackson.annotation.JsonProperty
import f2.vc.LdProofBase
import f2.vc.VC

class VCJson<T>(
	@JsonProperty("@context")
	override val context: List<String>,
	override val type: String,
	override val id: String,
	override val issuer: String,
	override val issuanceDate: String,
	override val credentialSubject: T,
	override val proof: LdProofBase
) : VC<T>