package f2.client.ktor.http.server

import f2.client.ktor.http.server.command.ServerConsumeFunction
import f2.client.ktor.http.server.command.ServerUploadCommandBody
import f2.client.ktor.http.server.command.ServerUploadedEvent
import f2.client.ktor.http.toBase64
import f2.dsl.fnc.f2Function
import java.io.IOException
import java.io.InputStream
import java.io.PipedInputStream
import java.io.PipedOutputStream
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DataBufferUtils
import org.springframework.http.codec.multipart.FilePart
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.scheduler.Schedulers

@RestController
@RequestMapping
@Configuration
open class ServerEndpoint {

    @PostMapping("/uploadFile")
    //, consumes = [MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE])
    fun uploadFile(
        @RequestPart("command") command: ServerUploadCommandBody,
        @RequestPart("file") file: FilePart
    ): ServerUploadedEvent {
        println("uploadFile: $command")
        return ServerUploadedEvent(
            base64 = file.content().asInputStream().readAllBytes().toBase64()
        )
    }

    @Bean
    open fun consume(): ServerConsumeFunction = f2Function { command ->
        println("consume: $command")
        command
    }
    
    private fun Flux<DataBuffer>.asInputStream(): InputStream {
        val osPipe = PipedOutputStream()
        val isPipe = PipedInputStream(osPipe)

        DataBufferUtils.write(this, osPipe)
            .subscribeOn(Schedulers.boundedElastic())
            .doOnComplete {
                try {
                    osPipe.close()
                } catch (ignored: IOException) {
                }
            }
            .subscribe(DataBufferUtils.releaseConsumer())
        return isPipe
    }
}
