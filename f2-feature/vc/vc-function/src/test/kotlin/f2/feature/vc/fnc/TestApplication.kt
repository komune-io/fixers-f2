package f2.feature.vc.fnc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["f2.feature.vc.fnc"])
class TestApplication

fun main(args: Array<String>) {
	runApplication<TestApplication>(*args)
}