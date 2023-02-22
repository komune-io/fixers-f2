package f2.sample.http

import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import f2.dsl.fnc.F2SupplierSingle
import f2.dsl.fnc.f2Consumer
import f2.dsl.fnc.f2Function
import f2.dsl.fnc.f2Supplier
import f2.dsl.fnc.f2SupplierSingle
import java.security.MessageDigest
import java.util.Base64
import java.util.UUID
import kotlinx.coroutines.channels.ticker
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

/**
 * Declare Spring Boot Application
 */
@SpringBootApplication
class F2SampleHttpApp {

	@Bean
	fun sha256(): F2Function<String, String> = f2Function { bytes ->
		val md = MessageDigest.getInstance("SHA-256")
		val hash = md.digest(bytes.toByteArray())
		Base64.getEncoder().encodeToString(hash)
	}

	@Bean
	fun uuid(): F2SupplierSingle<String> = f2SupplierSingle {
		UUID.randomUUID().toString()
	}
	@Bean
	fun uuids(): F2Supplier<String> = f2Supplier {
		ticker(1000).consumeAsFlow().map { UUID.randomUUID().toString() }
	}
	@Bean
	fun println(): F2Consumer<String> = f2Consumer(::println)
}

/**
 * Main method to start Spring Boot Application
 */
fun main(args: Array<String>) {
	runApplication<F2SampleHttpApp>(*args)
}
