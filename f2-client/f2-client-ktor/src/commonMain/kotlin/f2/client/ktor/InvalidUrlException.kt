package f2.client.ktor


/** A URL an F2 client cannot call: it starts with neither `http:` nor `https:`. */
class InvalidUrlException(url: String) :
    IllegalArgumentException("Invalid Url[$url] must start with one of http:, https:")
