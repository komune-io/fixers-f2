package f2.bdd.spring.http

import f2.bdd.spring.autoconfigure.steps.F2SpringContextBag

object F2SpringHttpCucumberConfig {
    fun urlBase(bag: F2SpringContextBag): String {
        val port = checkNotNull(bag.httpPort) { "No web server started for this scenario" }
        return "http://localhost:$port"
    }
}
