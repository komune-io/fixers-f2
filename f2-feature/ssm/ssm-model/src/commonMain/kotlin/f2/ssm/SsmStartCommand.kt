package f2.ssm

import city.smartb.f2.dsl.cqrs.Command
import city.smartb.f2.dsl.cqrs.Event

class SsmStartCommand(
	val session: SsmSession
): Command

class SsmStartedEvent(
	val invokeReturn: InvokeReturn
): Event
