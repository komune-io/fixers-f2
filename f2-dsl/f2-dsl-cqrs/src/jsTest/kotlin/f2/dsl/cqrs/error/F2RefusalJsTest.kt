package f2.dsl.cqrs.error

import f2.dsl.cqrs.error.exported.F2Refusal as JsF2Refusal
import kotlin.test.Test
import kotlin.test.assertEquals

/** A refusal's values as JS reads them: through its exported properties, which are a Kotlin map and list. */
class F2RefusalJsTest {

    private val refusal = F2Refusal(code = "item.CITED", parameters = mapOf("id" to "i-1"), citers = listOf("A", "B"))
        .unsafeCast<JsF2Refusal>()

    @Test
    fun theParametersAreAKotlinMapJsReadsThroughItsView() {
        val parameters = refusal.parameters.asJsReadonlyMapView()

        assertEquals(expected = "i-1", actual = parameters.get("id") as String)
        assertEquals(expected = 1, actual = parameters.size as Int)
    }

    @Test
    fun theCitersAreAKotlinListJsReadsThroughItsView() {
        val citers = refusal.citers.asJsReadonlyArrayView()

        assertEquals(expected = 2, actual = citers.length as Int)
        assertEquals(expected = "A", actual = citers[0] as String)
        assertEquals(expected = "B", actual = citers[1] as String)
    }
}
