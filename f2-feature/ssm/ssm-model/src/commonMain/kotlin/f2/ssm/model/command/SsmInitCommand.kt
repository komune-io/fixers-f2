package f2.ssm.model.command

import f2.dsl.cqrs.Command
import f2.dsl.cqrs.Event
import f2.dsl.function.F2Function
import f2.dsl.function.F2FunctionRemote
import f2.ssm.model.InvokeReturn
import f2.ssm.model.Ssm

typealias SsmInitFunction = F2Function<SsmInitCommand, SsmInitedEvent>
typealias SsmInitRemoteFunction = F2FunctionRemote<SsmInitCommand, SsmInitedEvent>

class SsmInitCommand(
	val ssm: Ssm
): Command


class SsmInitedEvent(
	val invokeReturns: List<InvokeReturn>
): Event
