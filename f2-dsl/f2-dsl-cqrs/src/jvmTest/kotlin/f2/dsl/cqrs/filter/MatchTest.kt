package f2.dsl.cqrs.filter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MatchTest {

    @Test
    fun `exactMatch map transforms value and keeps negative flag`() {
        val match = ExactMatch(value = 1, negative = true).map { it.toString() }

        assertThat(match).isEqualTo(ExactMatch(value = "1", negative = true))
    }

    @Test
    fun `exactMatch not inverts negative flag`() {
        val match = ExactMatch(value = 1)

        assertThat(match.not().negative).isTrue()
        assertThat((!match).negative).isTrue()
    }

    @Test
    fun `and combines two matches into an AndMatch`() {
        val left = ExactMatch(value = 1)
        val right = ExactMatch(value = 2)

        val match = left and right

        assertThat(match).isEqualTo(AndMatch(matches = listOf(left, right)))
    }

    @Test
    fun `or combines two matches into an OrMatch`() {
        val left = ExactMatch(value = 1)
        val right = ExactMatch(value = 2)

        val match = left or right

        assertThat(match).isEqualTo(OrMatch(matches = listOf(left, right)))
    }

    @Test
    fun `andMatch map, not and and operate on children`() {
        val match = AndMatch(matches = listOf(ExactMatch(value = 1), ExactMatch(value = 2)))

        val mapped = match.map { it * 10 }
        assertThat(mapped).isEqualTo(AndMatch(matches = listOf(ExactMatch(value = 10), ExactMatch(value = 20))))

        assertThat(match.not().negative).isTrue()

        val extended = match and ExactMatch(value = 3)
        assertThat(extended.matches).hasSize(3)
    }

    @Test
    fun `orMatch map, not and or operate on children`() {
        val match = OrMatch(matches = listOf(ExactMatch(value = 1), ExactMatch(value = 2)))

        val mapped = match.map { it * 10 }
        assertThat(mapped).isEqualTo(OrMatch(matches = listOf(ExactMatch(value = 10), ExactMatch(value = 20))))

        assertThat(match.not().negative).isTrue()

        val extended = match or ExactMatch(value = 3)
        assertThat(extended.matches).hasSize(3)
    }

    @Test
    fun `collectionMatch map transforms all values`() {
        val match = CollectionMatch(values = listOf(1, 2, 3))

        val mapped = match.map { it.toString() }

        assertThat(mapped).isEqualTo(CollectionMatch(values = listOf("1", "2", "3")))
        assertThat(match.not().negative).isTrue()
    }

    @Test
    fun `stringMatch toString builds pattern by condition`() {
        assertThat(StringMatch("abc", StringMatchCondition.EXACT).toString()).isEqualTo("abc")
        assertThat(StringMatch("abc", StringMatchCondition.STARTS_WITH).toString()).isEqualTo("abc%")
        assertThat(StringMatch("abc", StringMatchCondition.ENDS_WITH).toString()).isEqualTo("%abc")
        assertThat(StringMatch("abc", StringMatchCondition.CONTAINS).toString()).isEqualTo("%abc%")
    }

    @Test
    fun `stringMatch map keeps StringMatch for string results`() {
        val match = StringMatch("abc", StringMatchCondition.EXACT)

        val mapped = match.map { it.uppercase() }

        assertThat(mapped).isEqualTo(StringMatch("ABC", StringMatchCondition.EXACT))
        assertThat(match.not().negative).isTrue()
    }

    @Test
    fun `stringMatch map falls back to ExactMatch for non-string results`() {
        val match = StringMatch("42", StringMatchCondition.EXACT)

        val mapped = match.map { it.toInt() }

        assertThat(mapped).isEqualTo(ExactMatch(value = 42))
    }

    @Test
    fun `comparableMatch map keeps condition`() {
        val match = ComparableMatch(value = 1, condition = ComparableMatchCondition.GT)

        val mapped = match.map { it * 2 }

        assertThat(mapped).isEqualTo(ComparableMatch(value = 2, condition = ComparableMatchCondition.GT))
        assertThat(match.not().negative).isTrue()
    }

    @Test
    fun `collectionMatchOf builds a CollectionMatch from values`() {
        assertThat(collectionMatchOf(1, 2, 2)).isEqualTo(CollectionMatch(values = setOf(1, 2)))
    }

    @Test
    fun `collectionMatchOfNullable maps null literals to null`() {
        val match = collectionMatchOfNullable(listOf("a", "null", "NULL"))

        assertThat(match.values).containsExactly("a", null, null)
    }

    @Test
    fun `andMatchOf and orMatchOf build composite matches`() {
        assertThat(andMatchOf(ExactMatch(1), ExactMatch(2)).matches).hasSize(2)
        assertThat(orMatchOf(ExactMatch(1), ExactMatch(2)).matches).hasSize(2)
    }

    @Test
    fun `andMatchOfNotNull handles empty, single and multiple matches`() {
        assertThat(andMatchOfNotNull<Int>(null, null)).isNull()
        assertThat(andMatchOfNotNull(ExactMatch(1), null)).isEqualTo(ExactMatch(1))
        assertThat(andMatchOfNotNull(ExactMatch(1), ExactMatch(2)))
            .isEqualTo(AndMatch(listOf(ExactMatch(1), ExactMatch(2))))
    }

    @Test
    fun `orMatchOfNotNull handles empty, single and multiple matches`() {
        assertThat(orMatchOfNotNull<Int>(null, null)).isNull()
        assertThat(orMatchOfNotNull(ExactMatch(1), null)).isEqualTo(ExactMatch(1))
        assertThat(orMatchOfNotNull(ExactMatch(1), ExactMatch(2)))
            .isEqualTo(OrMatch(listOf(ExactMatch(1), ExactMatch(2))))
    }

    @Test
    fun `nullableExactMatchOf maps null literal to null value`() {
        assertThat(nullableExactMatchOf("value")).isEqualTo(ExactMatch<String?>("value"))
        assertThat(nullableExactMatchOf("null")).isEqualTo(ExactMatch<String?>(null))
    }
}
