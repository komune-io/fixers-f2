package f2.feature.ssm.function

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages= ["f2.feature.ssm.function"])
class TestApplication

fun main(args: Array<String>) {
	runApplication<TestApplication>(*args)
}