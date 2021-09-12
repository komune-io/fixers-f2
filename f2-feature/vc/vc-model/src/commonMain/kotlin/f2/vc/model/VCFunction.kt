package f2.vc.model

import f2.vc.model.command.VCSignFunction
import f2.vc.model.command.VCVerifyFunction

interface VCFunction {
	fun sign(): VCSignFunction
	fun verify(): VCVerifyFunction
}
