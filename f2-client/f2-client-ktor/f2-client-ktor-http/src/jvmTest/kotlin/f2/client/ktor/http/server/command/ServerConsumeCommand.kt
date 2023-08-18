package f2.client.ktor.http.server.command

import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable

typealias ServerConsumeFunction = F2Function<ServerConsumeCommand, ServerConsumedEvent>

@Serializable
data class ServerConsumeCommand(
    val stuff: String
)

typealias ServerConsumedEvent = ServerConsumeCommand
