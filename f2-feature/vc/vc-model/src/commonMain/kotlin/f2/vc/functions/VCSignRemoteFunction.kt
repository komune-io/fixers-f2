package f2.vc.functions

import city.smartb.f2.dsl.cqrs.Command
import f2.dsl.F2Function
import f2.dsl.F2FunctionRemote
import f2.vc.VC

typealias VCVerifyFunction<T> = F2Function<VCVerifyCommand<T>, VCVerifyResult>
typealias VCVerifyRemoteFunction<T> = F2FunctionRemote<VCVerifyCommand<T>, VCVerifyResult>

class VCVerifyCommand<T>(
	val claims: VC<T>
): Command

class VCVerifyResult(
	val isValid: Boolean
): Command
