package f2.spring.exception

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus

class F2HttpExceptionTest {

	@Test
	fun `should set status code from HttpStatus`() {
		val exception = F2HttpException(
			status = HttpStatus.BAD_REQUEST,
			message = "bad request",
			cause = null
		)
		assertThat(exception.status).isEqualTo(HttpStatus.BAD_REQUEST)
		assertThat(exception.error.code).isEqualTo(400)
	}

	@Test
	fun `should allow custom code different from status`() {
		val exception = F2HttpException(
			status = HttpStatus.BAD_REQUEST,
			code = 422,
			message = "unprocessable",
			cause = null
		)
		assertThat(exception.status).isEqualTo(HttpStatus.BAD_REQUEST)
		assertThat(exception.error.code).isEqualTo(422)
	}

	@Test
	fun `should propagate message to error and exception`() {
		val exception = F2HttpException(
			status = HttpStatus.INTERNAL_SERVER_ERROR,
			message = "something went wrong",
			cause = null
		)
		assertThat(exception.error.message).isEqualTo("something went wrong")
		assertThat(exception.message).isEqualTo("something went wrong")
	}

	@Test
	fun `should propagate cause`() {
		val rootCause = RuntimeException("root")
		val exception = F2HttpException(
			status = HttpStatus.INTERNAL_SERVER_ERROR,
			message = "wrapper",
			cause = rootCause
		)
		assertThat(exception.cause).isSameAs(rootCause)
	}

	@Test
	fun `should generate non-null id and timestamp`() {
		val exception = F2HttpException(
			status = HttpStatus.NOT_FOUND,
			message = "not found",
			cause = null
		)
		assertThat(exception.error.id).isNotBlank()
		assertThat(exception.error.timestamp).isNotBlank()
	}
}
