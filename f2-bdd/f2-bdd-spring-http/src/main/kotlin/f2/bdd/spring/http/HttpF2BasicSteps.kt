package f2.bdd.spring.http

import io.cucumber.java8.En

class HttpF2BasicSteps : StringHttpF2Steps("Basic: "), En {

	init {
		prepareFunctionCatalogSteps()
	}
}
