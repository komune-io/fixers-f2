package f2.spring.exception

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ConflictExceptionTest {

	@Test
	fun `should format message with entity, property and value`() {
		val exception = ConflictException("User", "email", "test@example.com")
		assertThat(exception.message).isEqualTo("User with email [test@example.com] already exists")
	}

	@Test
	fun `should have 409 status code`() {
		val exception = ConflictException("Order", "ref", "ORD-001")
		assertThat(exception.error.code).isEqualTo(409)
		assertThat(exception.status.value()).isEqualTo(409)
	}

	@Test
	fun `should expose entity and property`() {
		val exception = ConflictException("Product", "sku", "SKU-123")
		assertThat(exception.entity).isEqualTo("Product")
		assertThat(exception.property).isEqualTo("sku")
		assertThat(exception.value).isEqualTo("SKU-123")
	}
}
