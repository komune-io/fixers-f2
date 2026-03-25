package f2.spring.exception

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ForbiddenAccessExceptionTest {

	@Test
	fun `should use provided message as-is`() {
		val exception = ForbiddenAccessException("Custom forbidden message")
		assertThat(exception.message).isEqualTo("Custom forbidden message")
		assertThat(exception.error.message).isEqualTo("Custom forbidden message")
	}

	@Test
	fun `should have 403 status code`() {
		val exception = ForbiddenAccessException("denied")
		assertThat(exception.error.code).isEqualTo(403)
		assertThat(exception.status.value()).isEqualTo(403)
	}
}
