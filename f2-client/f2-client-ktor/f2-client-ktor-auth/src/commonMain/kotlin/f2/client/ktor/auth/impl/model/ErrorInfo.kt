package f2.client.ktor.auth.impl.model

import kotlinx.serialization.*

class AuthException(
    val errorDetails: ErrorDetails,
    cause: Throwable? = null,
): Exception(errorDetails.message, cause)

@Serializable
data class ErrorInfo(val error: ErrorDetails)

@Serializable
data class ErrorDetails(
    val code: Int,
    val message: String,
    val status: String,
)
