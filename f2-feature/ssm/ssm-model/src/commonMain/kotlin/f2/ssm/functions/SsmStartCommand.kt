package f2.ssm.functions

import city.smartb.f2.dsl.cqrs.Command
import city.smartb.f2.dsl.cqrs.Event
import f2.dsl.F2Function
import f2.dsl.F2FunctionRemote
import f2.ssm.InvokeReturn
import f2.ssm.SsmSession

typealias SsmStartFunction = F2Function<SsmStartCommand, SsmStartedEvent>
typealias SsmStartRemoteFunction = F2FunctionRemote<SsmStartCommand, SsmStartedEvent>

class SsmStartCommand(
	val session: SsmSession
): Command

class SsmStartedEvent(
	val invokeReturn: InvokeReturn
): Event
