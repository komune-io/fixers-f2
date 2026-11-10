package consumer

import f2.dsl.cqrs.error.F2Error
import f2.dsl.cqrs.error.F2Refusal
import f2.dsl.cqrs.exception.F2Exception
import f2.spring.exception.config.F2ErrorAttributes
import f2.spring.exception.config.F2ExceptionResolver
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.system.CapturedOutput
import org.springframework.boot.test.system.OutputCaptureExtension
import org.springframework.boot.webmvc.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.WebApplicationContext

/** An application that neither scans `f2` nor imports anything from it. */
@SpringBootApplication
class ConsumerApp {
    @RestController
    class WrappingController {
        /** What Spring Cloud Function does to a function's failure: wrap it once. */
        @GetMapping("/wrapped")
        fun wrapped(): String = throw IllegalStateException(
            "wrapper",
            F2Exception(error = F2Error(message = "Cited: it stays", code = 409, refusal = F2Refusal("item.CITED"))),
        )

        @GetMapping("/own")
        fun own(): String = throw IllegalArgumentException("the application's own")
    }

    /** The application's own handler for a type F2's wrappers share. */
    @RestControllerAdvice
    class OwnAdvice {
        @ExceptionHandler(IllegalArgumentException::class)
        fun own(ex: IllegalArgumentException): ResponseEntity<String> =
            ResponseEntity.status(HttpStatus.GONE).body("own: ${ex.message}")
    }
}

@SpringBootTest(classes = [ConsumerApp::class], webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ExtendWith(OutputCaptureExtension::class)
class F2ExceptionWebMvcAutoConfigurationTest(@Autowired private val context: ApplicationContext) {

    @Test
    fun `an application without any import gets the F2 exception resolver and error attributes`() {
        assertThat(context.getBeansOfType(F2ExceptionResolver::class.java)).hasSize(1)
        assertThat(context.getBean(ErrorAttributes::class.java)).isInstanceOf(F2ErrorAttributes::class.java)
    }

    @Test
    fun `a wrapped refusal answers its own HTTP status, not the wrapper's 500`() {
        MockMvcBuilders.webAppContextSetup(context as WebApplicationContext).build()
            .get("/wrapped")
            .andExpect {
                status { isConflict() }
                jsonPath("$.status") { value(409) }
                jsonPath("$.refusal") { value("item.CITED") }
                jsonPath("$.message") { value("Cited: it stays") }
            }
    }

    @Test
    fun `the application's own handler for the same exception type is still served, without a warning`(
        output: CapturedOutput,
    ) {
        MockMvcBuilders.webAppContextSetup(context as WebApplicationContext).build()
            .get("/own")
            .andExpect {
                status { isGone() }
                content { string("own: the application's own") }
            }
        assertThat(output.all).doesNotContain("Failure in @ExceptionHandler")
    }
}
