package f2.feature.vc.client

import f2.client.F2Client
import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.Protocol
import f2.client.ktor.get
import f2.client.promise
import f2.vc.model.VCRemoteFunction
import f2.vc.model.command.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlin.js.Promise


@JsName("vcClient")
@JsExport
fun <T> vcClient(protocol: Protocol, host: String, port: Int, path: String? = null):  Promise<VCFunctionClient<T>> = GlobalScope.promise {
	val s2Client = F2ClientBuilder.get(protocol, host, port, path)
	VCFunctionClient(s2Client)
}

@JsName("VCFunctionClientJs")
@JsExport
actual open class VCFunctionClient<T> actual constructor(private val client: F2Client) : VCRemoteFunction<T> {

	override fun sign(): VCSignRemoteFunction<T> = object : VCSignRemoteFunction<T> {
		override fun invoke(cmd: VCSignCommand<T>): Promise<VCSignResult<T>> = client.promise("perform" ,cmd)
	}

	override fun verify(): VCVerifyRemoteFunction<T> = object : VCVerifyRemoteFunction<T> {
		override fun invoke(cmd: VCVerifyCommand<T>): Promise<VCVerifyResult> = client.promise("perform" ,cmd)
	}

}