package f2.dsl.cqrs.filter

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.kotlin.KotlinModule
import tools.jackson.module.kotlin.readValue

/** What a Spring server answering with Jackson reads and writes: the same keys as kotlinx. */
class SortJacksonWireTest {

    private val mapper = JsonMapper.builder().addModule(KotlinModule.Builder().build()).build()

    @Test
    fun `Jackson writes a sort with the keys an earlier client reads`() {
        val json = mapper.writeValueAsString(SortDTOBase(property = "name", ascending = true, nullsFirst = false))

        assertThat(mapper.readTree(json).propertyNames())
            .containsExactlyInAnyOrder("property", "ascending", "nullsFirst")
    }

    @Test
    fun `Jackson reads a sort an earlier client wrote`() {
        val sort = mapper.readValue<SortDTOBase>("""{"property":"name","ascending":false,"nullsFirst":true}""")

        assertThat(sort).isEqualTo(SortDTOBase(property = "name", ascending = false, nullsFirst = true))
    }
}
