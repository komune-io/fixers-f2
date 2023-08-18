package f2.client.ktor.http.server

import f2.client.ktor.http.HttpF2Client
import f2.client.ktor.http.server.command.ServerConsumeFunction
import f2.client.ktor.http.server.command.ServerUploadFunction

class ServerClient(
    private val client: HttpF2Client
) {
    fun uploadFile(): ServerUploadFunction = client.function("uploadFile")
    fun consume(): ServerConsumeFunction = client.function("consume")
}
