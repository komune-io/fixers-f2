import city.smartb.f2.client.ktor.F2ClientBuilder
import city.smartb.f2.client.ktor.Protocol
import city.smartb.f2.client.ktor.get
import f2.feature.vc.VCFunctionClient
import f2.vc.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlin.js.Promise


@JsName("vcClient")
@JsExport
fun <T> vcClient(protocol: Protocol, host: String, port: Int, path: String? = null):  Promise<VCFunctionClientJs<T>> = GlobalScope.promise {
	val s2Client = F2ClientBuilder.get(protocol, host, port, path)
	val client = VCFunctionClient<T>(s2Client)
	VCFunctionClientJs(client)
}

@JsName("VCFunctionClientJs")
@JsExport
open class VCFunctionClientJs<T> (
	private val client: VCFunctionClient<T>,
) {

	fun sign(cmd: VCSignCommand<T>) = GlobalScope.promise {
		client.sign(cmd)
	}

	fun verify(cmd: VCVerifyCommand<T>) = GlobalScope.promise {
		client.verify(cmd)
	}

}
