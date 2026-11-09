package f2.client.ktor.http.model

import io.ktor.utils.io.ByteReadChannel
import kotlin.js.JsExport
import kotlin.js.JsName

typealias F2FileKey = String

@JsExport
interface F2UploadCommand<COMMAND> {
    val command: COMMAND
    val fileMap: Map<F2FileKey, List<F2FilePart>>
}

@JsExport
data class F2UploadSingleCommand<COMMAND>(
    override val command: COMMAND,
    val file: F2FilePart
): F2UploadCommand<COMMAND> {
    override val fileMap: Map<F2FileKey, List<F2FilePart>> = mapOf("file" to listOf(file))
}

@JsExport
data class F2UploadMultipleCommand<COMMAND>(
    override val command: COMMAND,
    val files: List<F2FilePart>
): F2UploadCommand<COMMAND> {
    override val fileMap: Map<F2FileKey, List<F2FilePart>> = mapOf("file" to files)
}

/**
 * A file sent in a multipart upload. Not a data class: its generated copy() would expose
 * the ByteReadChannel to JS, and two channels never compare equal anyway.
 */
@JsExport
class F2FilePart @JsExport.Ignore constructor(
    val name: String,
    @property:JsExport.Ignore
    val content: ByteReadChannel,
    val contentType: String? = null
) {
    /** Builds a part from its bytes: the JS form of the constructor, a ByteReadChannel has none. */
    @JsName("fromBytes")
    constructor(name: String, bytes: ByteArray, contentType: String? = null) :
        this(name, ByteReadChannel(bytes), contentType)
}
