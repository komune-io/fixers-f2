package consumer

import f2.spring.exception.config.F2ErrorAttributes
import f2.spring.exception.config.F2ExceptionHandler
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.webmvc.error.ErrorAttributes
import org.springframework.context.ApplicationContext

/** An application that neither scans `f2` nor imports anything from it. */
@SpringBootApplication
class ConsumerApp

@SpringBootTest(classes = [ConsumerApp::class], webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class F2ExceptionWebMvcAutoConfigurationTest(@Autowired private val context: ApplicationContext) {

    @Test
    fun `an application without any import gets the F2 exception handler and error attributes`() {
        assertThat(context.getBeansOfType(F2ExceptionHandler::class.java)).hasSize(1)
        assertThat(context.getBean(ErrorAttributes::class.java)).isInstanceOf(F2ErrorAttributes::class.java)
    }
}
