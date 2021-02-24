package f2.feature.ssm

import f2.client.F2Client
import f2.client.execute
import f2.ssm.*
import f2.ssm.functions.*

actual open class SSMFunctionClient actual constructor(val client: F2Client) : SSMRemoteFunction {
	override fun perform() = object : SsmPerformRemoteFunction {
		override suspend fun invoke(cmd: SsmPerformCommand): SsmPerformedEvent = client.execute("perform" ,cmd)
	}

	override fun start() = object : SsmStartRemoteFunction {
		override suspend fun invoke(cmd: SsmStartCommand): SsmStartedEvent = client.execute("start" ,cmd)
	}

	override fun init() = object : SsmInitRemoteFunction {
		override suspend fun invoke(cmd: SsmInitCommand): SsmInitedEvent = client.execute("start" ,cmd)
	}
}