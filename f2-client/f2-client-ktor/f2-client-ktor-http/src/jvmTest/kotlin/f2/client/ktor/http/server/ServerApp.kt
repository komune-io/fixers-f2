package f2.client.ktor.http.server

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["f2.client.ktor.http.server"])
open class ServerApp

fun main(args: Array<String>) {
    runApplication<ServerApp>(*args)
}
