package f2.dsl.cqrs.error

import f2.dsl.cqrs.error.exported.refusalCiters
import f2.dsl.cqrs.error.exported.refusalParameters
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class F2RefusalJsTest {

    private val refusal = F2Refusal(code = "item.CITED", parameters = mapOf("id" to "i-1"), citers = listOf("A", "B"))

    @Test
    fun refusalParametersIsAJsMapOfTheValues() {
        val parameters = refusalParameters(refusal)

        assertEquals(expected = "i-1", actual = parameters.get("id") as String)
        assertEquals(expected = 1, actual = parameters.size as Int)
        assertTrue(actual = parameters.has("id") as Boolean)
    }

    @Test
    fun refusalCitersIsAJsArrayOfTheNames() {
        val citers = refusalCiters(refusal)

        assertEquals(expected = 2, actual = citers.length as Int)
        assertEquals(expected = "A", actual = citers[0] as String)
        assertEquals(expected = "B", actual = citers[1] as String)
    }
}
