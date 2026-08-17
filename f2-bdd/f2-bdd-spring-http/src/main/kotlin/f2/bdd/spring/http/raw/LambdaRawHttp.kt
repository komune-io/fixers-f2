package f2.bdd.spring.http.raw

import f2.dsl.fnc.F2Function
import f2.dsl.fnc.f2Function
import java.util.function.Supplier
import kotlinx.serialization.Serializable
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import reactor.core.publisher.Flux

@Configuration
open class LambdaRawHttp {

    /** Echoes its input. Used to drive raw requests at a POJO-typed function. */
    @Bean
    open fun rawEchoFunction(): F2Function<RawPayload, RawPayload> = f2Function { it }

    /**
     * A plain reactive (non-Kotlin-Flow) supplier whose `map` throws on the second
     * element. This mirrors upstream's own `bang()` test fixture, which is the shape
     * `onErrorContinue` actually intercepts (a per-element failure raised inside an
     * operator) — a Kotlin `Flow` that throws from its builder instead produces a
     * terminal source error, which `onErrorContinue` cannot resume from at all, so it
     * would not exercise this fix.
     */
    @Bean
    open fun rawBangSupplier(): Supplier<Flux<String>> = Supplier {
        Flux.fromArray(arrayOf("foo", "bar")).map { value ->
            check(value != "bar") { "Bang" }
            value
        }
    }
}

@Serializable
data class RawPayload(
    val name: String,
    val count: Int
)
