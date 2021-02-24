package f2.vc.functions

import city.smartb.f2.dsl.cqrs.Command
import f2.dsl.F2Flow
import f2.dsl.F2Remote
import f2.vc.VC

typealias VCSignFunction<T> = F2Flow<VCSignCommand<T>, VCSignResult<T>>
typealias VCSignRemoteFunction<T> = F2Remote<VCSignCommand<T>, VCSignResult<T>>

class VCSignCommand<T>(
	val identifier: String,
	val claims: T
): Command

open class VCSignResult<T>(
	val vc: VC<T>,
): Command
