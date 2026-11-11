package f2.bdd.spring.lambda.catalog

import f2.bdd.spring.autoconfigure.steps.FunctionCatalogStepsBase
import f2.bdd.spring.lambda.single.StringConsumerReceiver
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En

class FunctionCatalogSteps : FunctionCatalogStepsBase<String, String>(""), En {

    init {
        prepareFunctionCatalogSteps()
    }

    override fun transform(dataTable: DataTable): List<String> = dataTable.asList().map { requireNotNull(it) }

    @Suppress("MissingUseCall") // the scenario owns the context: F2SpringStep's After hook stops it
    override fun consumerReceiver(): List<String> =
        bag.requireApplicationContext().getBean(StringConsumerReceiver::class.java).items
}
