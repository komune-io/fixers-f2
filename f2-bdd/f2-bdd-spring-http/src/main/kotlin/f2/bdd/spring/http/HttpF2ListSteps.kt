package f2.bdd.spring.http

import io.cucumber.datatable.DataTable
import io.cucumber.java8.En

class HttpF2ListSteps : StringHttpF2Steps("List: "), En {

	init {
		prepareFunctionCatalogSteps()
	}

	override fun transform(dataTable: DataTable): List<String> {
		return dataTable.asList()
			.flatMap { it.split(",") }
			.map { it.trim() }
			.filter { it.isNotEmpty() }
	}
}
