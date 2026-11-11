package f2.client.ktor.http.model

import io.ktor.utils.io.ByteReadChannel
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class F2UploadCommandTest {

    @Test
    fun `F2UploadSingleCommand has single file in fileMap`() {
        val filePart = F2FilePart(
            name = "test.txt",
            content = ByteReadChannel("test content".toByteArray())
        )
        val command = F2UploadSingleCommand(
            command = "testCommand",
            file = filePart
        )

        assertThat(command.command).isEqualTo("testCommand")
        assertThat(command.fileMap).hasSize(1)
        assertThat(command.fileMap["file"]).containsExactly(filePart)
    }

    @Test
    fun `F2UploadMultipleCommand has multiple files in fileMap`() {
        val file1 = F2FilePart(name = "file1.txt", content = ByteReadChannel("content1".toByteArray()))
        val file2 = F2FilePart(name = "file2.txt", content = ByteReadChannel("content2".toByteArray()))
        val command = F2UploadMultipleCommand(
            command = "multiCommand",
            files = listOf(file1, file2)
        )

        assertThat(command.command).isEqualTo("multiCommand")
        assertThat(command.fileMap).hasSize(1)
        assertThat(command.fileMap["file"]).containsExactly(file1, file2)
    }

    @Test
    fun `F2FilePart stores file metadata`() {
        val content = ByteReadChannel("file content".toByteArray())
        val filePart = F2FilePart(
            name = "document.pdf",
            content = content,
            contentType = "application/pdf"
        )

        assertThat(filePart.name).isEqualTo("document.pdf")
        assertThat(filePart.contentType).isEqualTo("application/pdf")
    }

    @Test
    fun `F2FilePart contentType defaults to null`() {
        val filePart = F2FilePart(
            name = "file.bin",
            content = ByteReadChannel(ByteArray(0))
        )

        assertThat(filePart.contentType).isNull()
    }

    @Test
    fun `F2UploadSingleCommand file property matches fileMap entry`() {
        val file = F2FilePart(name = "single.txt", content = ByteReadChannel("data".toByteArray()))
        val command = F2UploadSingleCommand(command = "cmd", file = file)

        assertThat(command.file).isEqualTo(file)
        assertThat(command.fileMap["file"]?.first()).isEqualTo(file)
    }

    @Test
    fun `F2UploadMultipleCommand files property matches fileMap`() {
        val files = listOf(
            F2FilePart(name = "a.txt", content = ByteReadChannel("a".toByteArray())),
            F2FilePart(name = "b.txt", content = ByteReadChannel("b".toByteArray())),
            F2FilePart(name = "c.txt", content = ByteReadChannel("c".toByteArray()))
        )
        val command = F2UploadMultipleCommand(command = "multi", files = files)

        assertThat(command.files).isEqualTo(files)
        assertThat(command.fileMap["file"]).containsExactlyElementsOf(files)
    }
}
