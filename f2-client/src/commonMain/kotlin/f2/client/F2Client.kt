package f2.client

import kotlin.reflect.KClass

interface F2Client<ID> {
	suspend fun <RET : Any> invoke(route: String, command: String, kClass: KClass<RET>): RET
	suspend fun <RET : Any> fetch(route: String, id: ID, kClass: KClass<RET>): RET
}