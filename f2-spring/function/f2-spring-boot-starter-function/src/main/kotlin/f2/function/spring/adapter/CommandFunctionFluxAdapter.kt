//package city.smartb.f2.function.spring.adapter
//
//import kotlinx.coroutines.reactor.mono
//import reactor.core.publisher.Flux
//import java.util.function.Function
//
//fun <T, R> execute(fnc: suspend (t: T) -> R): Function<Flux<T>, Flux<R>> = Function { commands ->
//	commands.flatMap { command ->
//		mono {
//			fnc(command)
//		}
//	}
//}
//
//fun <T, R> executeList(fnc: suspend (t: T) -> List<R>?): Function<Flux<T>, Flux<R>> = Function { commands ->
//	commands.flatMap { command ->
//		mono {
//			fnc(command)
//		}.flatMapIterable { it }
//	}
//}
