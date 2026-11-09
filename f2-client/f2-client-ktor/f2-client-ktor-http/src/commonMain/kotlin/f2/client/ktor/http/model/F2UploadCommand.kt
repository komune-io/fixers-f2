package f2.client.ktor.http.model

import io.ktor.utils.io.ByteReadChannel

typealias F2FileKey = String

interface F2UploadCommand<COMMAND> {
    val command: COMMAND
    val fileMap: Map<F2FileKey, Collection<F2FilePart>>
}

data class F2UploadSingleCommand<COMMAND>(
    override val command: COMMAND,
    val file: F2FilePart
): F2UploadCommand<COMMAND> {
    override val fileMap: Map<F2FileKey, Collection<F2FilePart>> = mapOf("file" to listOf(file))
}

data class F2UploadMultipleCommand<COMMAND>(
    override val command: COMMAND,
    val files: Collection<F2FilePart>
): F2UploadCommand<COMMAND> {
    override val fileMap: Map<F2FileKey, Collection<F2FilePart>> = mapOf("file" to files)
}

data class F2FilePart(
    val name: String,
    val content: ByteReadChannel,
    val contentType: String? = null
)
