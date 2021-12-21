package f2.spring.catalog

import f2.bdd.spring.autoconfigure.steps.FunctionCatalogStepsBase
import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import f2.spring.single.StringConsumerReceiver
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En

class FunctionCatalogSteps<P, R> : FunctionCatalogStepsBase<String, String>(""), En {

	init {
		prepareFunctionCatalogSteps()
	}

	override fun transform(dataTable: DataTable): List<String> {
		return dataTable.asList()
	}

	override fun consumerReceiver(): List<String> {
		return (bag.applicationContext!!.getBean(StringConsumerReceiver::class.java) as ConsumerReceiver<String>).items
	}
}