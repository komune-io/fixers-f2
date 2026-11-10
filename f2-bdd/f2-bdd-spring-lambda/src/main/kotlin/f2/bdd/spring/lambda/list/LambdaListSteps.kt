package f2.bdd.spring.lambda.list

import f2.bdd.spring.autoconfigure.steps.LambdaListStepsBase
import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import f2.bdd.spring.lambda.single.StringConsumerReceiver
import io.cucumber.datatable.DataTable
import io.cucumber.java8.En
import org.assertj.core.api.Assertions

abstract class LambdaListSteps : LambdaListStepsBase<String, String>() {

    fun En.prepareLambdaSteps() {
        prepareLambdaSteps(
            functionName = LambdaList::functionList.name,
            supplierName = LambdaList::supplierList.name,
            consumerName = LambdaList::consumerList.name
        )

        Then("The result for {string} is") { value: String, dataTable: DataTable ->
            Assertions.assertThat(bag.result[value]).isEqualTo(dataTable.asList())
        }
    }


    override fun transform(dataTable: DataTable): List<String> = dataTable.asList().map { requireNotNull(it) }

    override fun receiver(): ConsumerReceiver<String> =
        bag.requireApplicationContext().getBean(StringConsumerReceiver::class.java)
}
