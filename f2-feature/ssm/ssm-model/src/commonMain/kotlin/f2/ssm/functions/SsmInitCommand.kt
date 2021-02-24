package f2.ssm.functions

import city.smartb.f2.dsl.cqrs.Command
import city.smartb.f2.dsl.cqrs.Event
import f2.dsl.F2Flow
import f2.dsl.F2Remote
import f2.ssm.InvokeReturn
import f2.ssm.Ssm

typealias SsmInitFunction = F2Flow<SsmInitCommand, SsmInitedEvent>
typealias SsmInitRemoteFunction = F2Remote<SsmInitCommand, SsmInitedEvent>

class SsmInitCommand(
	val ssm: Ssm
): Command


class SsmInitedEvent(
	val invokeReturns: List<InvokeReturn>
): Event
