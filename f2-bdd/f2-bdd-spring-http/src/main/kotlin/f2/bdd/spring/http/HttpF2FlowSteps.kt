package f2.bdd.spring.http

import io.cucumber.java8.En

class HttpF2FlowSteps : StringHttpF2Steps("Flow: "), En {

	init {
		prepareFunctionCatalogSteps()
	}
}
