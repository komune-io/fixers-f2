package f2.vc.model.command

import f2.dsl.cqrs.Command
import f2.dsl.fnc.F2FunctionDeclaration
import f2.vc.model.VCBase
import f2.vc.model.VCBaseGen
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

typealias VCSignFunction = F2FunctionDeclaration<VCSignCommand, VCSignResult>

@Serializable
class VCSignCommand(
	val identifier: String,
	val claims: Map<String, @Serializable(with = AnySerializer::class) Any>
): Command

@Serializable
open class VCSignResult(
	val vc: VCBaseGen,
): Command
