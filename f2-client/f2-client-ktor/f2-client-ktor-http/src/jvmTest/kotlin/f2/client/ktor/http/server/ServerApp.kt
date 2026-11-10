package f2.client.ktor.http.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["f2.client.ktor.http.server"])
open class ServerApp

@Suppress("MissingUseCall") // the context is the server: it lives until the process exits
fun main(args: Array<String>) {
    runApplication<ServerApp>(*args)
}
