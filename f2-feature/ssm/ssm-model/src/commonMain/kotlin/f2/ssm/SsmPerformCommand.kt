package f2.ssm

import city.smartb.f2.dsl.cqrs.Command
import city.smartb.f2.dsl.cqrs.Event

class SsmPerformCommand(
	val action: String,
	val context: SsmContext
): Command

class SsmPerformedEvent(
	val invokeReturn: InvokeReturn
): Event
