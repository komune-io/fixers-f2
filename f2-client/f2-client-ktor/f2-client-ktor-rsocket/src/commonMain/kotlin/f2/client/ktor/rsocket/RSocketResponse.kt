package f2.client.ktor.rsocket

data class RSocketResponse<T>(
    val paylaod: T
)
