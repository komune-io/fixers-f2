package f2.feature.vc

import f2.client.F2Client
import f2.client.execute
import f2.vc.VCRemoteFunction
import f2.vc.functions.*

actual open class VCFunctionClient<T> actual constructor(private val client: F2Client) : VCRemoteFunction<T> {
	override fun sign(): VCSignRemoteFunction<T> = object : VCSignRemoteFunction<T> {
		override suspend fun invoke(cmd: VCSignCommand<T>): VCSignResult<T> = client.execute("perform" ,cmd)
	}

	override fun verify(): VCVerifyRemoteFunction<T> = object : VCVerifyRemoteFunction<T> {
		override suspend fun invoke(cmd: VCVerifyCommand<T>): VCVerifyResult = client.execute("perform" ,cmd)
	}
}