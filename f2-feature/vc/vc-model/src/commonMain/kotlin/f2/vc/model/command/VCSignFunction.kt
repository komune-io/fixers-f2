package f2.vc.model.command

import f2.dsl.cqrs.Command
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import f2.vc.model.VC

typealias VCSignFunction<T> = F2Function<VCSignCommand<T>, VCSignResult<T>>
typealias VCSignRemoteFunction<T> = F2FunctionRemote<VCSignCommand<T>, VCSignResult<T>>

class VCSignCommand<T>(
	val identifier: String,
	val claims: T
): Command

open class VCSignResult<T>(
	val vc: VC<T>,
): Command
