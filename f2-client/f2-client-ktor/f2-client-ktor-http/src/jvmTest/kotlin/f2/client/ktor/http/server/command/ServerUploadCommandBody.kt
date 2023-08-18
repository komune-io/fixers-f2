package f2.client.ktor.http.server.command

import f2.client.ktor.http.model.F2UploadSingleCommand
import f2.dsl.fnc.F2Function
import kotlinx.serialization.Serializable

typealias ServerUploadFunction = F2Function<ServerUploadCommand, ServerUploadedEvent>

typealias ServerUploadCommand = F2UploadSingleCommand<ServerUploadCommandBody>

data class ServerUploadCommandBody(
    val someData: String
)

@Serializable
data class ServerUploadedEvent(
    val base64: String
)
