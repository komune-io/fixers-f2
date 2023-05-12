package f2.client.ktor.http

import kotlinx.serialization.json.Json

val F2DefaultJson: Json = Json {
    encodeDefaults = true
    isLenient = true
    allowSpecialFloatingPointValues = true
    allowStructuredMapKeys = true
    prettyPrint = false
    useArrayPolymorphism = false
    ignoreUnknownKeys = true
    explicitNulls = false
}
