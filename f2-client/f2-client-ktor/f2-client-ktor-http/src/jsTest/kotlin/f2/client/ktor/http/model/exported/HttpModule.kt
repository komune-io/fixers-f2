@file:JsModule("./fixers-f2-f2-client-f2-client-ktor-f2-client-ktor-http.mjs")

package f2.client.ktor.http.model.exported

// The client and the upload types as JS sees them: imported by their exported names from the module the library compiles to.

external class F2FilePart {
	val name: String
	val contentType: String?

	companion object {
		fun fromBytes(name: String, bytes: ByteArray, contentType: String?): F2FilePart
	}
}

external class F2UploadSingleCommand(command: dynamic, file: F2FilePart) {
	val command: dynamic
	val file: F2FilePart
}

external interface HttpClientBuilder {
	fun build(urlBase: String): dynamic
}

external fun httpClientBuilderDefault(): HttpClientBuilder
external fun httpF2Supplier(client: dynamic, route: String): dynamic
external fun httpF2Function(client: dynamic, route: String): dynamic
external fun httpF2Consumer(client: dynamic, route: String): dynamic
