package f2.client.ktor.rsocket

import f2.client.F2Client
import io.ktor.utils.io.core.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

open class RSocketF2Client(
	private val rSocketClient: RSocketClient,
): F2Client {
	override suspend fun get(route: String): Flow<String> {
		return rSocketClient.requestStream(route).map { String(it) }
	}

	override suspend fun accept(route: String, command: String) {
		return rSocketClient.fireAndForget(route, command)
	}

	override suspend fun invoke(route: String, command: String): String {
		return rSocketClient.requestResponse(route, command).let { String(it) }
	}

}