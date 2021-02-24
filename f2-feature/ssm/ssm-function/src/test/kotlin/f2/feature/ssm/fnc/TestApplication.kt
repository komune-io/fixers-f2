package f2.feature.ssm.fnc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages= ["f2.feature.ssm.fnc"])
class TestApplication

fun main(args: Array<String>) {
	runApplication<TestApplication>(*args)
}