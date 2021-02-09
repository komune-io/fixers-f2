package city.smartb.f2.client.ktor

import city.smartb.f2.dsl.cqrs.S2CQRSClient
import f2.client.F2Client
import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer
import kotlin.reflect.KClass


class S2AggregateKtorClient<ID>(
	val f2Client: S2CQRSClient
) :F2Client<ID> {

	suspend fun <RET : Any> invokeString(route: String, command: String, kClass: KClass<RET>): String {
		return f2Client.invoke(route = route, command = command)
	}

	@InternalSerializationApi
	override suspend fun <RET : Any> invoke(route: String, command: String, kClass: KClass<RET>): RET {
		val result: String = f2Client.invoke(route = route, command = command)
		return Json.decodeFromString(kClass.serializer(), result)
	}

	@InternalSerializationApi
	override suspend fun <RET : Any> fetch(route: String, id: ID, kClass: KClass<RET>): RET {
		val result: String = f2Client.fetch(route = route, id.toString())
		return Json.decodeFromString(kClass.serializer(), result)
	}

}
