package city.smartb.f2.client.http.ktor

import city.smartb.f2.dsl.cqrs.S2CQRSClient
import io.ktor.utils.io.core.*

open class RSocketCQRSClient(
	private val rSocketClient: RSocketClient,
): S2CQRSClient {

	override suspend fun invoke(route: String, command: String): String {
		return rSocketClient.requestResponse(route, command).let { String(it) }
	}

	override suspend fun fetch(route: String, query: String): String {
		return invoke(route, query)
	}



}