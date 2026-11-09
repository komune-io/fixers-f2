package f2.bdd.spring.http

import f2.bdd.spring.autoconfigure.steps.F2SpringContextBag
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class F2SpringHttpCucumberConfigTest {

	@Test
	fun `urlBase targets the port the web server bound`() {
		val bag = F2SpringContextBag().apply { httpPort = 6123 }

		assertThat(F2SpringHttpCucumberConfig.urlBase(bag)).isEqualTo("http://localhost:6123")
	}

	@Test
	fun `urlBase fails when no web server started`() {
		assertThatThrownBy { F2SpringHttpCucumberConfig.urlBase(F2SpringContextBag()) }
			.isInstanceOf(IllegalStateException::class.java)
			.hasMessage("No web server started for this scenario")
	}
}
