package f2.spring.exception

import f2.dsl.cqrs.error.F2Error
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class F2ErrorExtensionsTest {

	@Test
	fun `toAttributeMap should expose id, code, message and timestamp`() {
		val error = F2Error(
			message = "boom",
			id = "error-id",
			timestamp = "2026-01-01T00:00:00Z",
			code = 500,
		)

		val attributes = error.toAttributeMap()

		assertThat(attributes).containsExactlyInAnyOrderEntriesOf(
			mapOf(
				"id" to "error-id",
				"code" to 500,
				"message" to "boom",
				"timestamp" to "2026-01-01T00:00:00Z",
			)
		)
	}

	@Test
	fun `toAttributeMap should keep a null id entry`() {
		val error = F2Error(
			message = "boom",
			id = null,
			timestamp = "2026-01-01T00:00:00Z",
			code = 400,
		)

		val attributes = error.toAttributeMap()

		assertThat(attributes).containsEntry("id", null)
		assertThat(attributes.filterValues { it != null }).doesNotContainKey("id")
	}

	@Test
	fun `missingParameterError should build a 400 error naming the parameter`() {
		val error = missingParameterError("userId")

		assertThat(error.code).isEqualTo(400)
		assertThat(error.message).isEqualTo("Missing parameter `userId`")
		assertThat(error.id).isNotBlank()
		assertThat(error.timestamp).isNotBlank()
	}
}
