package f2.client.ktor

class InvalidUrlException(url: String): Exception("$url is invalid")
