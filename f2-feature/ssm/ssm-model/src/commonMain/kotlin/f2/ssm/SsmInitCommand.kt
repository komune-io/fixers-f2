package f2.ssm

import city.smartb.f2.dsl.cqrs.Command
import city.smartb.f2.dsl.cqrs.Event

class SsmInitCommand(
	val ssm: Ssm
): Command


class SsmInitedEvent(
	val invokeReturns: List<InvokeReturn>
): Event
