package f2.vc.functions

import city.smartb.f2.dsl.cqrs.Command
import f2.dsl.F2Function
import f2.dsl.F2FunctionRemote
import f2.vc.VC

typealias VCSignFunction<T> = F2Function<VCSignCommand<T>, VCSignResult<T>>
typealias VCSignRemoteFunction<T> = F2FunctionRemote<VCSignCommand<T>, VCSignResult<T>>

class VCSignCommand<T>(
	val identifier: String,
	val claims: T
): Command

open class VCSignResult<T>(
	val vc: VC<T>,
): Command
