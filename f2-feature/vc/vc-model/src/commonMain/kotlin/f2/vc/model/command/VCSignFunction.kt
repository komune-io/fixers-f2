package f2.vc.model.command

import f2.dsl.cqrs.Command
import f2.dsl.fnc.F2Function
import f2.vc.model.VCGen
import kotlinx.serialization.Serializable

typealias VCSignFunction = F2Function<VCSignCommand, VCSignResult>

@Serializable
class VCSignCommand(
	val identifier: String,
	val claims: Map<String, @Serializable(with = AnySerializer::class) Any>,
) : Command

@Serializable
open class VCSignResult(
	val vc: VCGen,
) : Command
