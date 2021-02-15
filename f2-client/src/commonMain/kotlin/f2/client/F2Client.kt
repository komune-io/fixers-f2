package f2.client

interface F2Client {
	suspend fun invoke(route: String, command: String): String
}