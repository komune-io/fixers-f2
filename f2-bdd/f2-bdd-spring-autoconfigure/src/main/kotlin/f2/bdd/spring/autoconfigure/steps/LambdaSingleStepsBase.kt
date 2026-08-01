package f2.bdd.spring.autoconfigure.steps

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver

abstract class LambdaSingleStepsBase<P, R> : LambdaStepsBase<P, R>() {

	abstract fun receiver(): ConsumerReceiver<P>

	override fun consumedItems(): Any = receiver().items.first() as Any
}
