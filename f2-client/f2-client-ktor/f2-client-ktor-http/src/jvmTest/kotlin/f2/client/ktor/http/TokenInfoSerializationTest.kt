package f2.client.ktor.http

import f2.client.domain.TokenInfo
import f2.client.ktor.common.F2DefaultJson
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class TokenInfoSerializationTest {

    @Test
    fun empty() {
        val token = """
            {"access_token":"ACCESS-TOKEN","expires_in":28800,"refresh_expires_in":0,"token_type":"Bearer","not-before-policy":0,"scope":"profile email"}
        """.trimIndent()
        val tokenInfo = F2DefaultJson.decodeFromString<TokenInfo>(token)
        Assertions.assertThat(tokenInfo.accessToken).isEqualTo("ACCESS-TOKEN")
    }
}