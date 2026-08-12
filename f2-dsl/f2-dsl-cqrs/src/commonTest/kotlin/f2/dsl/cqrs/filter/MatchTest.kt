package f2.dsl.cqrs.filter

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class MatchTest {

    @Test
    fun `exactMatch map transforms value and keeps negative flag`() {
        val match = ExactMatch(value = 1, negative = true).map { it.toString() }

        assertEquals(ExactMatch(value = "1", negative = true), match)
    }

    @Test
    fun `exactMatch not inverts negative flag`() {
        val match = ExactMatch(value = 1)

        assertTrue(match.not().negative)
        assertTrue((!match).negative)
    }

    @Test
    fun `and combines two matches into an AndMatch`() {
        val left = ExactMatch(value = 1)
        val right = ExactMatch(value = 2)

        val match = left and right

        assertEquals(AndMatch(matches = listOf(left, right)), match)
    }

    @Test
    fun `or combines two matches into an OrMatch`() {
        val left = ExactMatch(value = 1)
        val right = ExactMatch(value = 2)

        val match = left or right

        assertEquals(OrMatch(matches = listOf(left, right)), match)
    }

    @Test
    fun `andMatch map, not and and operate on children`() {
        val match = AndMatch(matches = listOf(ExactMatch(value = 1), ExactMatch(value = 2)))

        val mapped = match.map { it * 10 }
        assertEquals(AndMatch(matches = listOf(ExactMatch(value = 10), ExactMatch(value = 20))), mapped)

        assertTrue(match.not().negative)

        val extended = match and ExactMatch(value = 3)
        assertEquals(3, (extended.matches).count())
    }

    @Test
    fun `orMatch map, not and or operate on children`() {
        val match = OrMatch(matches = listOf(ExactMatch(value = 1), ExactMatch(value = 2)))

        val mapped = match.map { it * 10 }
        assertEquals(OrMatch(matches = listOf(ExactMatch(value = 10), ExactMatch(value = 20))), mapped)

        assertTrue(match.not().negative)

        val extended = match or ExactMatch(value = 3)
        assertEquals(3, (extended.matches).count())
    }

    @Test
    fun `collectionMatch map transforms all values`() {
        val match = CollectionMatch(values = listOf(1, 2, 3))

        val mapped = match.map { it.toString() }

        assertEquals(CollectionMatch(values = listOf("1", "2", "3")), mapped)
        assertTrue(match.not().negative)
    }

    @Test
    fun `stringMatch toString builds pattern by condition`() {
        assertEquals("abc", StringMatch("abc", StringMatchCondition.EXACT).toString())
        assertEquals("abc%", StringMatch("abc", StringMatchCondition.STARTS_WITH).toString())
        assertEquals("%abc", StringMatch("abc", StringMatchCondition.ENDS_WITH).toString())
        assertEquals("%abc%", StringMatch("abc", StringMatchCondition.CONTAINS).toString())
    }

    @Test
    fun `stringMatch map keeps StringMatch for string results`() {
        val match = StringMatch("abc", StringMatchCondition.EXACT)

        val mapped = match.map { it.uppercase() }

        assertEquals(StringMatch("ABC", StringMatchCondition.EXACT), mapped)
        assertTrue(match.not().negative)
    }

    @Test
    fun `stringMatch map falls back to ExactMatch for non-string results`() {
        val match = StringMatch("42", StringMatchCondition.EXACT)

        val mapped = match.map { it.toInt() }

        assertEquals(ExactMatch(value = 42), mapped)
    }

    @Test
    fun `comparableMatch map keeps condition`() {
        val match = ComparableMatch(value = 1, condition = ComparableMatchCondition.GT)

        val mapped = match.map { it * 2 }

        assertEquals(ComparableMatch(value = 2, condition = ComparableMatchCondition.GT), mapped)
        assertTrue(match.not().negative)
    }

    @Test
    fun `collectionMatchOf builds a CollectionMatch from values`() {
        assertEquals(CollectionMatch(values = setOf(1, 2)), collectionMatchOf(1, 2, 2))
    }

    @Test
    fun `collectionMatchOfNullable maps null literals to null`() {
        val match = collectionMatchOfNullable(listOf("a", "null", "NULL"))

        assertEquals(listOf("a", null, null), (match.values).toList())
    }

    @Test
    fun `andMatchOf and orMatchOf build composite matches`() {
        assertEquals(2, (andMatchOf(ExactMatch(1), ExactMatch(2)).matches).count())
        assertEquals(2, (orMatchOf(ExactMatch(1), ExactMatch(2)).matches).count())
    }

    @Test
    fun `andMatchOfNotNull handles empty, single and multiple matches`() {
        assertNull(andMatchOfNotNull<Int>(null, null))
        assertEquals(ExactMatch(1), andMatchOfNotNull(ExactMatch(1), null))
        assertEquals(AndMatch(listOf(ExactMatch(1), ExactMatch(2))), andMatchOfNotNull(ExactMatch(1), ExactMatch(2)))
    }

    @Test
    fun `orMatchOfNotNull handles empty, single and multiple matches`() {
        assertNull(orMatchOfNotNull<Int>(null, null))
        assertEquals(ExactMatch(1), orMatchOfNotNull(ExactMatch(1), null))
        assertEquals(OrMatch(listOf(ExactMatch(1), ExactMatch(2))), orMatchOfNotNull(ExactMatch(1), ExactMatch(2)))
    }

    @Test
    fun `nullableExactMatchOf maps null literal to null value`() {
        assertEquals(ExactMatch<String?>("value"), nullableExactMatchOf("value"))
        assertEquals(ExactMatch<String?>(null), nullableExactMatchOf("null"))
    }
}
