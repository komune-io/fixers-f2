package f2.vc.model.command

import f2.dsl.cqrs.Command
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import f2.vc.model.VC

typealias VCVerifyFunction<T> = F2Function<VCVerifyCommand<T>, VCVerifyResult>
typealias VCVerifyRemoteFunction<T> = F2FunctionRemote<VCVerifyCommand<T>, VCVerifyResult>

class VCVerifyCommand<T>(
	val claims: VC<T>
): Command

class VCVerifyResult(
	val isValid: Boolean
): Command
