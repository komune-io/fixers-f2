package f2.feature.vc.client

import f2.client.F2Client
import f2.client.declaration
import f2.client.ktor.F2ClientBuilder
import f2.client.ktor.Protocol
import f2.client.ktor.get
import f2.vc.model.VCFunction
import f2.vc.model.command.VCSignFunction
import f2.vc.model.command.VCVerifyFunction
import kotlin.js.Promise


@JsName("vcClient")
@JsExport
fun <T> vcClient(protocol: Protocol, host: String, port: Int, path: String? = null): Promise<VCFunctionClient> {
	return F2ClientBuilder.get(protocol, host, port, path).then { s2Client ->
		VCFunctionClient(s2Client)
	}

}

@JsName("VCFunctionClientJs")
@JsExport
actual open class VCFunctionClient actual constructor(private val client: F2Client) : VCFunction {

	override fun sign(): VCSignFunction = client.declaration("sign")

	override fun verify(): VCVerifyFunction = client.declaration("verify")

}