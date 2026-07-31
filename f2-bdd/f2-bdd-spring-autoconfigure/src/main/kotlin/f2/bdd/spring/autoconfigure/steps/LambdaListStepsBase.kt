package f2.bdd.spring.autoconfigure.steps

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver

abstract class LambdaListStepsBase<P, R> : LambdaStepsBase<List<P>, List<R>>() {

	abstract fun receiver(): ConsumerReceiver<P>

	override fun consumedItems(): Any = receiver().items
}
