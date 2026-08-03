package f2.client.ktor.common

import kotlin.js.JsExport
import kotlinx.serialization.json.Json

@JsExport
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
