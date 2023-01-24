package f2.bdd.spring.lambda.catalog

import f2.bdd.spring.autoconfigure.steps.FunctionCatalogStepsBase
import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import f2.bdd.spring.lambda.single.StringConsumerReceiver
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En

class FunctionCatalogSteps : FunctionCatalogStepsBase<String, String>(""), En {

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
