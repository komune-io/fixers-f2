package f2.bdd.spring.lambda.methodcall

import f2.bdd.spring.autoconfigure.utils.ConsumerReceiver
import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import f2.dsl.fnc.f2Consumer
import f2.dsl.fnc.f2Function
import f2.dsl.fnc.f2Supplier
import kotlinx.coroutines.flow.flowOf

open class MethodCallLambda {

    open fun functionF2(functionSimple: (String) -> String): MethodCallFunction = f2Function { value ->
        functionSimple(value)
    }

    open fun supplierF2(supplierSimple: () -> String): MethodCallSupplier = f2Supplier {
        flowOf(supplierSimple())
    }

    open fun consumerF2(receiver: ConsumerReceiver<String>): MethodCallConsumer = f2Consumer { value ->
        receiver.items.add(value)
    }

}
typealias MethodCallFunction = F2Function<String, String>
typealias MethodCallSupplier = F2Supplier<String>
typealias MethodCallConsumer = F2Consumer<String>
