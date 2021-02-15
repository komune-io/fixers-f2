import city.smartb.f2.client.ktor.F2ClientBuilder
import city.smartb.f2.client.ktor.Protocol
import city.smartb.f2.client.ktor.get
import f2.feature.ssm.SSMFunctionClient
import f2.ssm.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.promise
import kotlin.js.Promise


@JsName("ssmClient")
@JsExport
fun <T> ssmClient(protocol: Protocol, host: String, port: Int, path: String? = null):  Promise<SSMFunctionClientJs<T>> = GlobalScope.promise {
	val s2Client = F2ClientBuilder.get(protocol, host, port, path)
	val client = SSMFunctionClient<T>(s2Client)
	SSMFunctionClientJs(client)
}

@JsName("SSMFunctionClientJs")
@JsExport
open class SSMFunctionClientJs<T> (
	private val client: SSMFunctionClient<T>,
) {

	fun perform(cmd: SsmPerformCommand) = GlobalScope.promise {
		client.perform(cmd)
	}

	fun start(cmd: SsmStartCommand) = GlobalScope.promise {
		client.start(cmd)
	}

	fun init(cmd: SsmInitCommand) = GlobalScope.promise {
		client.init(cmd)
	}

}
