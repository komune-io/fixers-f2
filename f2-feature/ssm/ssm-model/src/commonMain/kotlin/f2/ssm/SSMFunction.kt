package f2.ssm

import f2.ssm.functions.*

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