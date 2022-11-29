package f2.sample.http

import f2.dsl.fnc.F2Function
import f2.dsl.fnc.f2Function
import java.security.MessageDigest
import java.util.Base64
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

/**
 * Declare Spring Boot Application
 */
@SpringBootApplication
class F2SampleHttpApp {

	/**
	 * Declare function to calculate sha256 and return it in Base64
	 * f2Function is an helper to implement coroutine function
	 * and return suspend (Flow<T>) -> Flow<R>
	 */
	@Bean
	fun sha256(): F2Function<String, String> = f2Function<String, String> { bytes ->
		val md = MessageDigest.getInstance("SHA-256")
		val hash = md.digest(bytes.toByteArray())
		Base64.getEncoder().encodeToString(hash)
	}
}

/**
 * Main method to start Spring Boot Application
 */
fun main(args: Array<String>) {
	runApplication<F2SampleHttpApp>(*args)
}
