package f2.spring.exception

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.error.missingParameterError
import f2.dsl.cqrs.exception.F2Exception
import java.time.Instant
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import tools.jackson.core.JsonParser
import tools.jackson.core.exc.StreamReadException
import tools.jackson.databind.exc.MismatchedInputException

class F2ErrorExtensionsTest {

	@Test
	fun `a refusal tells the caller its message, parameters and citers beside its code`() {
		val error = F2Error(
			message = "The item is cited by A, B: it stays",
			code = 409,
			refusal = "item.CITED",
			parameters = mapOf("id" to "i-1"),
			citers = listOf("A", "B"),
		)

		assertThat(refusalAttributes(409, error)).containsExactlyInAnyOrderEntriesOf(
			mapOf(
				"refusal" to "item.CITED",
				"message" to "The item is cited by A, B: it stays",
				"parameters" to mapOf("id" to "i-1"),
				"citers" to listOf("A", "B"),
			)
		)
	}

	@Test
	fun `a refusal without parameters or citers leaves both keys out`() {
		val error = F2Error(message = "A name is required", code = 400, refusal = "item.NAME_REQUIRED")

		assertThat(refusalAttributes(400, error))
			.containsEntry("refusal", "item.NAME_REQUIRED")
			.containsEntry("message", "A name is required")
			.doesNotContainKeys("parameters", "citers")
	}

	@Test
	fun `an error that is not a refusal carries the meaning of its status and nothing it was raised with`() {
		val internal = F2Error(message = "jdbc:postgresql://db-internal:5432/app", code = 404)

		assertThat(refusalAttributes(404, internal)).containsExactlyEntriesOf(mapOf("refusal" to "http.NOT_FOUND"))
		assertThat(refusalAttributes(503, null)).containsExactlyEntriesOf(mapOf("refusal" to "http.UNAVAILABLE"))
		assertThat(refusalAttributes(502, null)).containsExactlyEntriesOf(mapOf("refusal" to "http.UNEXPECTED"))
	}

	@Test
	fun `unreadableBody finds the parser failure only under the input conversion wrapper`() {
		val parser = StreamReadException(null as JsonParser?, "Unexpected end-of-input")

		assertThat(IllegalStateException("Failed to convert input", RuntimeException(parser)).unreadableBody())
			.isSameAs(parser)
		assertThat(IllegalStateException("reading the cache", parser).unreadableBody()).isNull()
		assertThat(IllegalStateException("Failed to convert input").unreadableBody()).isNull()
	}

	@Test
	fun `unreadableBody finds an input that converted to nothing`() {
		val nothing = IllegalArgumentException("Failed to convert input: {} to class Item")

		assertThat(nothing.unreadableBody()).isSameAs(nothing)
		assertThat(IllegalArgumentException("Failed to convert the cache").unreadableBody()).isNull()
		assertThat(nothing.describeError(HttpStatus.INTERNAL_SERVER_ERROR).status).isEqualTo(HttpStatus.BAD_REQUEST)
	}

	@Test
	fun `an unreadable body names the field the parser stopped at, and nothing it was parsed with`() {
		val mismatch = MismatchedInputException
			.from(null as JsonParser?, Int::class.java, "Cannot deserialize at db-internal")
			.apply { prependPath(Any(), "count") }

		val described = IllegalStateException("Failed to convert input", RuntimeException(mismatch))
			.describeError(HttpStatus.INTERNAL_SERVER_ERROR)

		assertThat(described.status).isEqualTo(HttpStatus.BAD_REQUEST)
		assertThat(refusalAttributes(400, described.error)).containsExactlyInAnyOrderEntriesOf(
			mapOf("refusal" to "http.INVALID_PAYLOAD", "parameters" to mapOf("parameter" to "count"))
		)
	}

	@Test
	fun `the error body has one shape, when and where then the status, its phrase and the refusal code`() {
		val body = errorBody("/items", HttpStatus.NOT_FOUND, null)

		assertThat(body.keys).containsExactly("timestamp", "path", "status", "error", "refusal")
		assertThat(Instant.parse(body["timestamp"].toString())).isNotNull()
		assertThat(body).containsEntry("refusal", "http.NOT_FOUND").containsEntry("status", 404)
	}

	@Test
	fun `findF2Exception should return the exception itself`() {
		val exception = F2Exception(message = "boom", id = "self", code = 500)

		assertThat(exception.findF2Exception()).isSameAs(exception)
	}

	@Test
	fun `findF2Exception should unwrap a direct cause`() {
		val cause = F2Exception(message = "boom", id = "cause", code = 409)

		assertThat(IllegalStateException("wrapper", cause).findF2Exception()).isSameAs(cause)
	}

	@Test
	fun `findF2Exception should ignore deeper causes and unrelated throwables`() {
		val deep = F2Exception(message = "boom", id = "deep", code = 409)
		val wrapped = IllegalStateException("inner", deep)

		assertThat(IllegalStateException("outer", wrapped).findF2Exception()).isNull()
		assertThat(IllegalStateException("alone").findF2Exception()).isNull()
		assertThat((null as Throwable?).findF2Exception()).isNull()
	}

	@Test
	fun `missingParameterError should build a 400 refusal naming the parameter`() {
		val error = missingParameterError("userId")

		assertThat(error.code).isEqualTo(400)
		assertThat(error.message).isEqualTo("Missing parameter `userId`")
		assertThat(error.refusal).isEqualTo("http.INVALID_PAYLOAD")
		assertThat(error.parameters).containsExactlyEntriesOf(mapOf("parameter" to "userId"))
		assertThat(error.id).isNotBlank()
		assertThat(Instant.parse(error.timestamp)).isNotNull()
	}
}
