package f2.bdd.spring.http

import io.cucumber.java8.En

class HttpMethodCallSteps : StringHttpF2Steps("MethodCall: "), En {

	init {
		prepareFunctionCatalogSteps()
	}
}
