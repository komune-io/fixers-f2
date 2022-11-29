import f2.client.ktor.http.HttpF2Client
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlin.test.Test
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.assertj.core.api.Assertions

class HttpF2ClientTest {

	@Test
	fun test(): Unit = runTest {
		val client = HttpF2Client(
			httpClient =  HttpClient(CIO) {
				install(ContentNegotiation) {
					json()
				}
			},
			"https://www.google.fr",
		)
		val result = client.getT("search?q=smartb").invoke().first()
		Assertions.assertThat(result).isNotNull
	}
}