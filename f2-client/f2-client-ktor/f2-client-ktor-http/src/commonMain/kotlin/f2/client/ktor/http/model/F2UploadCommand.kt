package f2.client.ktor.http.model

import io.ktor.utils.io.ByteReadChannel

typealias F2FileKey = String

interface F2UploadCommand<COMMAND> {
    val command: COMMAND
    val fileMap: Map<F2FileKey, List<F2FilePart>>
}

/** One command and its file. */
data class F2UploadSingleCommand<COMMAND>(
    override val command: COMMAND,
    val file: F2FilePart
): F2UploadCommand<COMMAND> {
    override val fileMap: Map<F2FileKey, List<F2FilePart>> = mapOf("file" to listOf(file))
}

/** One command and its files. */
data class F2UploadMultipleCommand<COMMAND>(
    override val command: COMMAND,
    val files: List<F2FilePart>
): F2UploadCommand<COMMAND> {
    override val fileMap: Map<F2FileKey, List<F2FilePart>> = mapOf("file" to files)
}

/** A file sent in a multipart upload. Not a data class: two channels never compare equal. */
@Suppress("UseDataClass") // two channels never compare equal, so a generated equals would never hold
class F2FilePart(
    val name: String,
    val content: ByteReadChannel,
    val contentType: String? = null
) {
    /** Builds a part from its bytes. */
    constructor(name: String, bytes: ByteArray, contentType: String? = null) :
        this(name, ByteReadChannel(bytes), contentType)
}
