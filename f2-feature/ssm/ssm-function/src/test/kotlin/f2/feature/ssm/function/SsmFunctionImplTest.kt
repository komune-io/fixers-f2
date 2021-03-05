package f2.feature.ssm.function

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.cloud.function.context.FunctionCatalog
import org.springframework.test.context.junit.jupiter.SpringExtension

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(SpringExtension::class)
@SpringBootTest
class SsmFunctionImplTest {

	@Autowired
	lateinit var catalog: FunctionCatalog

	@Test
	fun checkFuntionIsRegistred() {
		val performF2: Any = catalog.lookup("perform")
		Assertions.assertThat(performF2).isNotNull
		val startF2: Any = catalog.lookup("start")
		Assertions.assertThat(startF2).isNotNull
		val initF2: Any = catalog.lookup("init")
		Assertions.assertThat(initF2).isNotNull
	}
}