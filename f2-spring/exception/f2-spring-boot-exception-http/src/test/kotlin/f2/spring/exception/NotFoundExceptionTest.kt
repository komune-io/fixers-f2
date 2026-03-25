package f2.spring.exception

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class NotFoundExceptionTest {

	@Test
	fun `should format message with name and id`() {
		val exception = NotFoundException("User", "123")
		assertThat(exception.message).isEqualTo("User [123] not found")
	}

	@Test
	fun `should have 404 status code`() {
		val exception = NotFoundException("Order", "abc")
		assertThat(exception.error.code).isEqualTo(404)
		assertThat(exception.status.value()).isEqualTo(404)
	}

	@Test
	fun `should expose name property`() {
		val exception = NotFoundException("Invoice", "42")
		assertThat(exception.name).isEqualTo("Invoice")
	}
}
