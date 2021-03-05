package f2.ssm.model

import f2.ssm.model.command.*

interface SSMFunction {
	fun perform(): SsmPerformFunction
	fun start(): SsmStartFunction
	fun init(): SsmInitFunction
}

interface SSMRemoteFunction {
	fun perform(): SsmPerformRemoteFunction
	fun start(): SsmStartRemoteFunction
	fun init(): SsmInitRemoteFunction
}