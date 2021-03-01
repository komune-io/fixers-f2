package f2.ssm.functions

import city.smartb.f2.dsl.cqrs.Command
import city.smartb.f2.dsl.cqrs.Event
import f2.dsl.F2Function
import f2.dsl.F2FunctionRemote
import f2.ssm.InvokeReturn
import f2.ssm.Ssm

typealias SsmInitFunction = F2Function<SsmInitCommand, SsmInitedEvent>
typealias SsmInitRemoteFunction = F2FunctionRemote<SsmInitCommand, SsmInitedEvent>

class SsmInitCommand(
	val ssm: Ssm
): Command


class SsmInitedEvent(
	val invokeReturns: List<InvokeReturn>
): Event
