package f2.spring

import f2.bdd.spring.autoconfigure.ApplicationContextBuilder
import f2.bdd.spring.autoconfigure.ApplicationContextRunnerBuilder
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.cloud.function.context.FunctionCatalog


class ApplicationContextRunnerTest {

	@Test
	fun `spring context runner must must start`() {
		ApplicationContextRunnerBuilder()
			.buildContext().run { context ->
				assertThat(context).hasSingleBean(FunctionCatalog::class.java)
			}
	}

	@Test
	fun `spring context must must start`() {
		val context = ApplicationContextBuilder().create(
			types = arrayOf(ApplicationContextBuilder.SimpleConfiguration::class.java),
		)
		assertThat(context.getBean(FunctionCatalog::class.java)).isNotNull
	}
}
