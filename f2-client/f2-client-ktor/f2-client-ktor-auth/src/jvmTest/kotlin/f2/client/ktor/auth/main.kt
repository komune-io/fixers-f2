package f2.client.ktor.auth

import f2.client.ktor.auth.impl.AuthRealmPassword
import f2.client.ktor.auth.impl.F2Auth
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.bodyAsText
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

fun main(args: Array<String>): Unit = runBlocking {
    val client = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }

        install(F2Auth) {
            auth = AuthRealmPassword(
                serverUrl = "http://localhost:8080/auth",
                realmId = "alveole",
                redirectUrl = "",
                clientId = "alveole-web",
                username = "admin@alveoleplus.fr",
                password = "alveoleplus"
            )
        }
    }
    println(
        client.post("http://localhost:8070/api/organizationGet") {
            header("Content-Type", ContentType.Application.Json)
            setBody(mapOf("id" to "c1d0b622-efd4-4e6b-9d0b-85c56fba9d24"))
        }.bodyAsText()
    )
}
