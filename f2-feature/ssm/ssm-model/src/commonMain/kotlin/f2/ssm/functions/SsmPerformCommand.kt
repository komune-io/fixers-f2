package f2.ssm.functions

import city.smartb.f2.dsl.cqrs.Command
import city.smartb.f2.dsl.cqrs.Event
import f2.dsl.F2Flow
import f2.dsl.F2Remote
import f2.ssm.InvokeReturn
import f2.ssm.SsmContext

typealias SsmPerformFunction = F2Flow<SsmPerformCommand, SsmPerformedEvent>
typealias SsmPerformRemoteFunction = F2Remote<SsmPerformCommand, SsmPerformedEvent>

class SsmPerformCommand(
	val action: String,
	val context: SsmContext
): Command

class SsmPerformedEvent(
	val invokeReturn: InvokeReturn
): Event
