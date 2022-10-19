import f2.client.F2ClientType
import f2.client.jsonF2Config
import f2.client.ktor.http.HttpF2Client
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions
import kotlin.test.Test

class HttpF2ClientTest {

	@Test
	fun test(): Unit = runTest {
		val client = HttpF2Client(
			httpClient =  HttpClient(CIO) {
				install(ContentNegotiation) {
					json(jsonF2Config)
				}
			},
			"https://www.google.fr",
		)
		val result = client.getT("search?q=smartb").invoke().first()
		Assertions.assertThat(result).isNotNull
	}
}