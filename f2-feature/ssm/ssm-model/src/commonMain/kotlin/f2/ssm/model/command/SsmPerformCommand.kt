package f2.ssm.model.command

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import f2.ssm.model.InvokeReturn
import f2.ssm.model.SsmContext

typealias SsmPerformFunction = F2Function<SsmPerformCommand, SsmPerformedEvent>
typealias SsmPerformRemoteFunction = F2FunctionRemote<SsmPerformCommand, SsmPerformedEvent>

class SsmPerformCommand(
	val action: String,
	val context: SsmContext
): Command

class SsmPerformedEvent(
	val invokeReturn: InvokeReturn
): Event
