package f2.feature.ssm

import city.smartb.f2.client.ktor.F2ClientBuilder
import city.smartb.f2.client.ktor.Protocol
import city.smartb.f2.client.ktor.get
import f2.client.F2Client
import f2.client.promise
import f2.ssm.*
import f2.ssm.functions.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlin.js.Promise

@JsName("ssmClient")
@JsExport
fun ssmClient(protocol: Protocol, host: String, port: Int, path: String? = null):  Promise<SSMFunctionClient> = GlobalScope.promise {
	val s2Client = F2ClientBuilder.get(protocol, host, port, path)
	SSMFunctionClient(s2Client)
}

actual open class SSMFunctionClient actual constructor(val client: F2Client) : SSMRemoteFunction {

	override fun perform() = object : SsmPerformRemoteFunction {
		override fun invoke(cmd: SsmPerformCommand): Promise<SsmPerformedEvent> = client.promise("perform" ,cmd)
	}

	override fun start() = object : SsmStartRemoteFunction {
		override fun invoke(cmd: SsmStartCommand): Promise<SsmStartedEvent> = client.promise("perform" ,cmd)
	}

	override fun init() = object : SsmInitRemoteFunction {
		override fun invoke(cmd: SsmInitCommand): Promise<SsmInitedEvent> = client.promise("perform" ,cmd)
	}

}