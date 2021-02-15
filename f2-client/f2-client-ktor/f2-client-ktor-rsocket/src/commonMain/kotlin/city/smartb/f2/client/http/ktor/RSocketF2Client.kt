package city.smartb.f2.client.http.ktor

import f2.client.F2Client
import io.ktor.utils.io.core.*

open class RSocketF2Client(
	private val rSocketClient: RSocketClient,
): F2Client {

	override suspend fun invoke(route: String, command: String): String {
		return rSocketClient.requestResponse(route, command).let { String(it) }
	}

}