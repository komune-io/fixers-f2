package f2.client.ktor.http

import f2.client.F2Client
import f2.dsl.fnc.F2FunctionDeclaration
import f2.dsl.fnc.F2SupplierDeclaration

expect open class HttpF2Client: F2Client {
	override fun get(route: String) : F2SupplierDeclaration<String>
	override fun invoke(route: String): F2FunctionDeclaration<String, String>
}