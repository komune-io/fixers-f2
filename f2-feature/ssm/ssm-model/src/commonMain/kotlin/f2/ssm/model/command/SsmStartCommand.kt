package f2.ssm.model.command

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import f2.ssm.model.InvokeReturn
import f2.ssm.model.SsmSession

typealias SsmStartFunction = F2Function<SsmStartCommand, SsmStartedEvent>
typealias SsmStartRemoteFunction = F2FunctionRemote<SsmStartCommand, SsmStartedEvent>

class SsmStartCommand(
	val session: SsmSession
): Command

class SsmStartedEvent(
	val invokeReturn: InvokeReturn
): Event
