package f2.feature.ssm

import f2.client.F2Client
import f2.client.executeInvoke
import f2.ssm.*
import f2.ssm.functions.*

actual open class SSMFunctionClient actual constructor(val client: F2Client) : SSMRemoteFunction {
	override fun perform() = object : SsmPerformRemoteFunction {
		override suspend fun invoke(cmd: SsmPerformCommand): SsmPerformedEvent = client.executeInvoke("perform" ,cmd)
	}

	override fun start() = object : SsmStartRemoteFunction {
		override suspend fun invoke(cmd: SsmStartCommand): SsmStartedEvent = client.executeInvoke("start" ,cmd)
	}

	override fun init() = object : SsmInitRemoteFunction {
		override suspend fun invoke(cmd: SsmInitCommand): SsmInitedEvent = client.executeInvoke("start" ,cmd)
	}
}