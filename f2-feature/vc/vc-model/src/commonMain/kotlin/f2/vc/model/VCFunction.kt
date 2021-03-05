package f2.vc.model

import f2.vc.model.command.VCSignFunction
import f2.vc.model.command.VCSignRemoteFunction
import f2.vc.model.command.VCVerifyFunction
import f2.vc.model.command.VCVerifyRemoteFunction

interface VCFunction<T> {
	fun sign(): VCSignFunction<T>
	fun verify(): VCVerifyFunction<T>
}

interface VCRemoteFunction<T> {
	fun sign(): VCSignRemoteFunction<T>
	fun verify(): VCVerifyRemoteFunction<T>
}