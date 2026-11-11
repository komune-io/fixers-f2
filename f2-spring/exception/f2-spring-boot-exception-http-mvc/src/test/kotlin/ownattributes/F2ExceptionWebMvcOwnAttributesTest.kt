package ownattributes

import f2.spring.exception.config.F2ErrorAttributes
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.error.ErrorAttributeOptions
import org.springframework.boot.webmvc.error.DefaultErrorAttributes
import org.springframework.boot.webmvc.error.ErrorAttributes
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.web.context.request.WebRequest

/** An application that declares its own error attributes. */
@SpringBootApplication
class OwnAttributesApp {
    @Bean
    fun ownErrorAttributes(): ErrorAttributes = object : DefaultErrorAttributes() {
        override fun getErrorAttributes(webRequest: WebRequest, options: ErrorAttributeOptions) =
            super.getErrorAttributes(webRequest, options).apply { this["own"] = "yes" }
    }
}

@SpringBootTest(classes = [OwnAttributesApp::class], webEnvironment = SpringBootTest.WebEnvironment.MOCK)
class F2ExceptionWebMvcOwnAttributesTest(@Autowired private val context: ApplicationContext) {

    @Test
    fun `the application's own error attributes are kept, and F2's are not registered beside them`() {
        val attributes = context.getBeansOfType(ErrorAttributes::class.java).values

        assertThat(attributes).hasSize(1)
        assertThat(attributes.single()).isNotInstanceOf(F2ErrorAttributes::class.java)
    }
}
