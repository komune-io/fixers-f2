package city.smartb.f2.dsl.cqrs

interface S2CQRSClient {

	suspend fun invoke(route: String, command: String): String

	suspend fun fetch(route: String, query: String): String

}