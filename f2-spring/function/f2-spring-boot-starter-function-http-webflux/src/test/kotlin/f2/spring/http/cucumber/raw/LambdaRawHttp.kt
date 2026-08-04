package f2.spring.http.cucumber.raw

import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import f2.dsl.fnc.f2Function
import f2.dsl.fnc.f2Supplier
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
open class LambdaRawHttp {

    /** Echoes its input. Used to drive raw requests at a POJO-typed function. */
    @Bean
    open fun rawEchoFunction(): F2Function<RawPayload, RawPayload> = f2Function { it }

    /** Emits one element then fails, to prove errors are propagated and not swallowed. */
    @Bean
    open fun rawBangSupplier(): F2Supplier<String> = f2Supplier {
        flow {
            emit("foo")
            throw IllegalStateException("Bang")
        }
    }
}

@Serializable
data class RawPayload(
    val name: String,
    val count: Int
)
