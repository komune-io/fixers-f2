package f2.ssm.functions

import city.smartb.f2.dsl.cqrs.Command
import city.smartb.f2.dsl.cqrs.Event
import f2.dsl.F2Flow
import f2.dsl.F2Remote
import f2.ssm.InvokeReturn
import f2.ssm.SsmSession

typealias SsmStartFunction = F2Flow<SsmStartCommand, SsmStartedEvent>
typealias SsmStartRemoteFunction = F2Remote<SsmStartCommand, SsmStartedEvent>

class SsmStartCommand(
	val session: SsmSession
): Command

class SsmStartedEvent(
	val invokeReturn: InvokeReturn
): Event
