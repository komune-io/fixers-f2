package f2.dsl.cqrs.filter

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class MatchTest {

    @Test
    fun `exactMatch map transforms value and keeps negative flag`() {
        val match = ExactMatch(value = 1, isNegative = true).map { it.toString() }

        assertEquals(expected = ExactMatch(value = "1", isNegative = true), actual = match)
    }

    @Test
    fun `exactMatch not inverts negative flag`() {
        val match = ExactMatch(value = 1)

        assertTrue(match.not().isNegative)
        assertTrue((!match).isNegative)
    }

    @Test
    fun `and combines two matches into an AndMatch`() {
        val left = ExactMatch(value = 1)
        val right = ExactMatch(value = 2)

        val match = left and right

        assertEquals(expected = AndMatch(matches = listOf(left, right)), actual = match)
    }

    @Test
    fun `or combines two matches into an OrMatch`() {
        val left = ExactMatch(value = 1)
        val right = ExactMatch(value = 2)

        val match = left or right

        assertEquals(expected = OrMatch(matches = listOf(left, right)), actual = match)
    }

    @Test
    fun `andMatch map, not and and operate on children`() {
        val match = AndMatch(matches = listOf(ExactMatch(value = 1), ExactMatch(value = 2)))

        val mapped = match.map { it * 10 }
        assertEquals(
            expected = AndMatch(matches = listOf(ExactMatch(value = 10), ExactMatch(value = 20))),
            actual = mapped,
        )

        assertTrue(match.not().isNegative)

        val extended = match and ExactMatch(value = 3)
        assertEquals(expected = 3, actual = extended.matches.count())
    }

    @Test
    fun `orMatch map, not and or operate on children`() {
        val match = OrMatch(matches = listOf(ExactMatch(value = 1), ExactMatch(value = 2)))

        val mapped = match.map { it * 10 }
        assertEquals(
            expected = OrMatch(matches = listOf(ExactMatch(value = 10), ExactMatch(value = 20))),
            actual = mapped,
        )

        assertTrue(match.not().isNegative)

        val extended = match or ExactMatch(value = 3)
        assertEquals(expected = 3, actual = extended.matches.count())
    }

    @Test
    fun `collectionMatch map transforms all values`() {
        val match = CollectionMatch(values = listOf(1, 2, 3))

        val mapped = match.map { it.toString() }

        assertEquals(expected = CollectionMatch(values = listOf("1", "2", "3")), actual = mapped)
        assertTrue(match.not().isNegative)
    }

    @Test
    fun `stringMatch toString builds pattern by condition`() {
        assertEquals(expected = "abc", actual = StringMatch("abc", StringMatchCondition.EXACT).toString())
        assertEquals(expected = "abc%", actual = StringMatch("abc", StringMatchCondition.STARTS_WITH).toString())
        assertEquals(expected = "%abc", actual = StringMatch("abc", StringMatchCondition.ENDS_WITH).toString())
        assertEquals(expected = "%abc%", actual = StringMatch("abc", StringMatchCondition.CONTAINS).toString())
    }

    @Test
    fun `stringMatch map keeps StringMatch for string results`() {
        val match = StringMatch("abc", StringMatchCondition.EXACT)

        val mapped = match.map { it.uppercase() }

        assertEquals(expected = StringMatch("ABC", StringMatchCondition.EXACT), actual = mapped)
        assertTrue(match.not().isNegative)
    }

    @Test
    fun `stringMatch map falls back to ExactMatch for non-string results`() {
        val match = StringMatch("42", StringMatchCondition.EXACT)

        val mapped = match.map { it.toInt() }

        assertEquals(expected = ExactMatch(value = 42), actual = mapped)
    }

    @Test
    fun `comparableMatch map keeps condition`() {
        val match = ComparableMatch(value = 1, condition = ComparableMatchCondition.GT)

        val mapped = match.map { it * 2 }

        assertEquals(expected = ComparableMatch(value = 2, condition = ComparableMatchCondition.GT), actual = mapped)
        assertTrue(match.not().isNegative)
    }

    @Test
    fun `collectionMatchOf builds a CollectionMatch from values`() {
        assertEquals(expected = CollectionMatch(values = setOf(1, 2)), actual = collectionMatchOf(1, 2, 2))
    }

    @Test
    fun `collectionMatchOfNullable maps null literals to null`() {
        val match = collectionMatchOfNullable(listOf("a", "null", "NULL"))

        assertEquals(expected = listOf("a", null, null), actual = match.values.toList())
    }

    @Test
    fun `andMatchOf and orMatchOf build composite matches`() {
        assertEquals(expected = 2, actual = andMatchOf(ExactMatch(1), ExactMatch(2)).matches.count())
        assertEquals(expected = 2, actual = orMatchOf(ExactMatch(1), ExactMatch(2)).matches.count())
    }

    @Test
    fun `andMatchOfNotNull handles empty, single and multiple matches`() {
        assertNull(andMatchOfNotNull<Int>(null, null))
        assertEquals(expected = ExactMatch(1), actual = andMatchOfNotNull(ExactMatch(1), null))
        assertEquals(
            expected = AndMatch(listOf(ExactMatch(1), ExactMatch(2))),
            actual = andMatchOfNotNull(ExactMatch(1), ExactMatch(2)),
        )
    }

    @Test
    fun `orMatchOfNotNull handles empty, single and multiple matches`() {
        assertNull(orMatchOfNotNull<Int>(null, null))
        assertEquals(expected = ExactMatch(1), actual = orMatchOfNotNull(ExactMatch(1), null))
        assertEquals(
            expected = OrMatch(listOf(ExactMatch(1), ExactMatch(2))),
            actual = orMatchOfNotNull(ExactMatch(1), ExactMatch(2)),
        )
    }

    @Test
    fun `nullableExactMatchOf maps null literal to null value`() {
        assertEquals(expected = ExactMatch<String?>("value"), actual = nullableExactMatchOf("value"))
        assertEquals(expected = ExactMatch<String?>(null), actual = nullableExactMatchOf("null"))
    }
}
