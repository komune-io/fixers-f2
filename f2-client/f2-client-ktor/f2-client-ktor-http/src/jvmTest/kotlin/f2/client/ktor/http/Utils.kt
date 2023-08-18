package f2.client.ktor.http

import java.util.Base64

fun ByteArray.toBase64() = Base64.getEncoder().encodeToString(this)
