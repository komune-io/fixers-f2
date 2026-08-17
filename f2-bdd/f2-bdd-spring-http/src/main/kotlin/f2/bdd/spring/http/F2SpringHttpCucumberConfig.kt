package f2.bdd.spring.http

import f2.bdd.spring.autoconfigure.steps.F2SpringContextBag

object F2SpringHttpCucumberConfig {
	fun urlBase(bag: F2SpringContextBag) = "http://localhost:${bag.httpPort}"
}
