package f2.spring.exception

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.error.F2Refusal
import f2.dsl.cqrs.exception.F2Exception
import java.time.Instant
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import tools.jackson.core.JsonParser
import tools.jackson.core.exc.StreamReadException
import tools.jackson.databind.exc.MismatchedInputException
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.kotlin.KotlinInvalidNullException
import tools.jackson.module.kotlin.KotlinModule

class F2ErrorExtensionsTest {

	@Test
	fun `a refusal tells the caller its message, parameters and citers beside its code`() {
		val error = F2Error(
			message = "The item is cited by A, B: it stays",
			code = 409,
			refusal = F2Refusal("item.CITED", parameters = mapOf("id" to "i-1"), citers = listOf("A", "B")),
		)

		assertThat(refusalAttributes(error)).containsExactlyInAnyOrderEntriesOf(
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
		val error = F2Error(message = "A name is required", code = 400, refusal = F2Refusal("item.NAME_REQUIRED"))

		assertThat(refusalAttributes(error))
			.containsEntry("refusal", "item.NAME_REQUIRED")
			.containsEntry("message", "A name is required")
			.doesNotContainKeys("parameters", "citers")
	}

	@Test
	fun `an error that is not a refusal carries the meaning of its status and nothing it was raised with`() {
		val internal = F2Error(message = "jdbc:postgresql://db-internal:5432/app", code = 404)

		assertThat(refusalAttributes(internal)).containsExactlyEntriesOf(mapOf("refusal" to "http.NOT_FOUND"))
		assertThat(refusalAttributes(statusError(HttpStatus.SERVICE_UNAVAILABLE)))
			.containsExactlyEntriesOf(mapOf("refusal" to "http.UNAVAILABLE"))
		assertThat(refusalAttributes(statusError(HttpStatus.BAD_GATEWAY)))
			.containsExactlyEntriesOf(mapOf("refusal" to "http.UNEXPECTED"))
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
		assertThat(nothing.describeError()?.httpStatus()).isEqualTo(HttpStatus.BAD_REQUEST)
	}

	@Test
	fun `an unreadable body names the field the parser stopped at, and nothing it was parsed with`() {
		val mismatch = MismatchedInputException
			.from(null as JsonParser?, Int::class.java, "Cannot deserialize at db-internal")
			.apply { prependPath(Any(), "count") }

		val described = checkNotNull(
			IllegalStateException("Failed to convert input", RuntimeException(mismatch)).describeError()
		)

		assertThat(described.httpStatus()).isEqualTo(HttpStatus.BAD_REQUEST)
		assertThat(refusalAttributes(described)).containsExactlyInAnyOrderEntriesOf(
			mapOf(
				"refusal" to "http.INVALID_PAYLOAD",
				"message" to "Unreadable request body",
				"parameters" to mapOf("parameter" to "count"),
			)
		)
	}

	@Test
	fun `the error body has one shape, when and where then the status, its phrase and the refusal code`() {
		val body = errorBody("/items", HttpStatus.NOT_FOUND, statusError(HttpStatus.NOT_FOUND))

		assertThat(body.keys).containsExactly("timestamp", "path", "status", "error", "refusal")
		assertThat(Instant.parse(body["timestamp"].toString())).isNotNull()
		assertThat(body).containsEntry("refusal", "http.NOT_FOUND").containsEntry("status", 404)
	}

	@Test
	fun `describeError finds an F2 error anywhere in the cause chain`() {
		val deep = F2Error(message = "Cited", code = 409, refusal = F2Refusal("item.CITED"))
		val wrapped = IllegalStateException("outer", IllegalStateException("inner", F2Exception(deep)))

		assertThat(wrapped.describeError()).isSameAs(deep)
		assertThat(F2Exception(deep).describeError()).isSameAs(deep)
	}

	@Test
	fun `describeError names a missing parameter at any depth, before the unreadable body it also is`() {
		val missing = runCatching {
			JsonMapper.builder().addModule(KotlinModule.Builder().build()).build()
				.readValue("""{"description":null}""", Item::class.java)
		}.exceptionOrNull()
		assertThat(missing).isInstanceOf(KotlinInvalidNullException::class.java)
		val described = IllegalStateException("Failed to convert input", RuntimeException(missing)).describeError()

		assertThat(described?.refusal?.parameters).containsExactlyEntriesOf(mapOf("parameter" to "description"))
		assertThat(described?.message).isEqualTo("Missing parameter `description`")
	}

	@Test
	fun `describeError leaves a failure carrying nothing of F2 to the framework`() {
		assertThat(IllegalStateException("alone").describeError()).isNull()
		assertThat((null as Throwable?).describeError()).isNull()
	}

	private data class Item(val description: String)
}
