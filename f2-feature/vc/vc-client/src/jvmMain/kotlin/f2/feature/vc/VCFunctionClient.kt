package f2.feature.vc

import city.smartb.f2.client.ktor.F2ClientBuilder
import city.smartb.f2.client.ktor.Protocol
import city.smartb.f2.client.ktor.get
import f2.client.F2Client
import f2.client.executeInvoke
import f2.vc.VCRemoteFunction
import f2.vc.functions.*

suspend fun <T> vcClient(protocol: Protocol, host: String, port: Int, path: String? = null):  VCFunctionClient<T> {
	val s2Client = F2ClientBuilder.get(protocol, host, port, path)
	return VCFunctionClient(s2Client)
}


actual open class VCFunctionClient<T> actual constructor(private val client: F2Client) : VCRemoteFunction<T> {
	override fun sign(): VCSignRemoteFunction<T> = object : VCSignRemoteFunction<T> {
		override suspend fun invoke(cmd: VCSignCommand<T>): VCSignResult<T> = client.executeInvoke("perform" ,cmd)
	}

	override fun verify(): VCVerifyRemoteFunction<T> = object : VCVerifyRemoteFunction<T> {
		override suspend fun invoke(cmd: VCVerifyCommand<T>): VCVerifyResult = client.executeInvoke("perform" ,cmd)
	}
}