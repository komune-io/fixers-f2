package f2.spring.http.cucumber.exception

import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import f2.dsl.fnc.f2Function
import f2.dsl.fnc.f2Supplier
import f2.spring.exception.ForbiddenAccessException
import f2.spring.exception.NotFoundException
import kotlinx.serialization.Serializable
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class LambdaF2Exceptions {

	@Bean
	open fun basicFunction(): F2Function<BasicParams, BasicParams> = f2Function { it }

	@Bean
	open fun notFoundFunction(): F2Supplier<BasicParams> = f2Supplier {
		throw NotFoundException("BasicParams", "Unknown")
	}

	@Bean
	open fun forbiddenFunction(): F2Supplier<BasicParams> = f2Supplier {
		throw ForbiddenAccessException("You are not authorized to call this function")
	}
}


@Serializable
data class BasicParams(
	val name: String,
	val description: String
)
