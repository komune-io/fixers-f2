@file:JsModule("./fixers-f2-f2-client-f2-client-ktor-f2-client-ktor-http.mjs")

package f2.client.ktor.http.model.exported

// The upload types as JS sees them: imported by their exported names from the module the library compiles to.

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
