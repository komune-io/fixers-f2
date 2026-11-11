package f2.client.ktor.http.model

import f2.client.ktor.http.model.exported.F2FilePart as JsF2FilePart
import f2.client.ktor.http.model.exported.F2UploadSingleCommand as JsF2UploadSingleCommand
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertSame

class F2UploadCommandJsTest {

    @Test
    fun aFilePartBuiltFromBytesInJsReadsBack() {
        val part = JsF2FilePart.fromBytes("a.txt", "hello".encodeToByteArray(), "text/plain")
        assertEquals("a.txt", part.name)
        assertEquals("text/plain", part.contentType)
    }

    @Test
    fun aSingleUploadBuiltInJsKeepsItsCommandAndFile() {
        val part = JsF2FilePart.fromBytes("b.bin", byteArrayOf(1, 2), null)
        val command = JsF2UploadSingleCommand(js("({ id: 'c1' })"), part)
        assertEquals("c1", command.command.id as String)
        assertSame(part, command.file)
        assertEquals("b.bin", command.file.name)
        assertEquals(null, command.file.contentType)
    }
}
