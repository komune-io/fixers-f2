package f2.dsl.cqrs.filter

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlinx.serialization.json.Json

class SortWireTest {

    @Test
    fun `a sort is written with the keys an earlier client reads`() {
        val json = Json.encodeToString(
            SortDTOBase.serializer(),
            SortDTOBase(property = "name", ascending = true, nullsFirst = false),
        )

        assertEquals(expected = """{"property":"name","ascending":true,"nullsFirst":false}""", actual = json)
    }

    @Test
    fun `a sort an earlier client wrote is read`() {
        val sort = Json.decodeFromString(
            SortDTOBase.serializer(),
            """{"property":"name","ascending":false,"nullsFirst":true}""",
        )

        assertEquals(expected = SortDTOBase(property = "name", ascending = false, nullsFirst = true), actual = sort)
    }
}
