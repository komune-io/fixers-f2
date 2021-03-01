package f2.ssm.functions

import city.smartb.f2.dsl.cqrs.Command
import city.smartb.f2.dsl.cqrs.Event
import f2.dsl.F2Function
import f2.dsl.F2FunctionRemote
import f2.ssm.InvokeReturn
import f2.ssm.SsmContext

typealias SsmPerformFunction = F2Function<SsmPerformCommand, SsmPerformedEvent>
typealias SsmPerformRemoteFunction = F2FunctionRemote<SsmPerformCommand, SsmPerformedEvent>

class SsmPerformCommand(
	val action: String,
	val context: SsmContext
): Command

class SsmPerformedEvent(
	val invokeReturn: InvokeReturn
): Event
