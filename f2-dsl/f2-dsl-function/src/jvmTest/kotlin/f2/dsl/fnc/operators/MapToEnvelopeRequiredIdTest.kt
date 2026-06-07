package f2.dsl.fnc.operators

import f2.dsl.fnc.F2Function
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

/**
 * mapToEnvelope no longer accepts a default random-id. Callers must either
 *  - provide an `id` extractor (preserves caller-supplied correlation), or
 *  - opt in explicitly via `mapToEnvelopeWithRandomId(...)`.
 *
 * These tests pin the new behaviour. The fix that removes the default would
 * break correlation across the s2/ssm/plateform pipeline if a downstream
 * caller silently reverted to random ids — these tests trip first.
 */
class MapToEnvelopeRequiredIdTest {

    private data class Cmd(val msgId: String, val payload: String)

    @Test
    suspend fun `Flow mapToEnvelope preserves caller-supplied id`() {
        val cmds = flowOf(Cmd("m-1", "a"), Cmd("m-2", "b"), Cmd("m-3", "c"))
        val envelopes = cmds.mapToEnvelope { it.msgId }.toList()
        assertThat(envelopes.map { it.id }).containsExactly("m-1", "m-2", "m-3")
        assertThat(envelopes.map { it.data.payload }).containsExactly("a", "b", "c")
    }

    @Test
    suspend fun `Flow mapToEnvelope with type preserves caller-supplied id`() {
        val cmds = flowOf(Cmd("m-1", "a"), Cmd("m-2", "b"))
        val envelopes = cmds.mapToEnvelope(type = "Cmd") { it.msgId }.toList()
        assertThat(envelopes.map { it.id }).containsExactly("m-1", "m-2")
        assertThat(envelopes).allMatch { it.type == "Cmd" }
    }

    @Test
    suspend fun `F2Function mapToEnvelope preserves caller-supplied id`() {
        val fn = F2Function<Cmd, String> { flow -> flow.map { it.payload.uppercase() } }
        val envelopes = fn.mapToEnvelope { it.msgId }.invoke(
            flowOf(Cmd("m-1", "a"), Cmd("m-2", "b"))
        ).toList()
        assertThat(envelopes.map { it.id }).containsExactly("m-1", "m-2")
        assertThat(envelopes.map { it.data }).containsExactly("A", "B")
    }

    @Test
    suspend fun `Flow mapToEnvelopeWithRandomId generates distinct UUIDs`() {
        val envelopes = flowOf(1, 2, 3).mapToEnvelopeWithRandomId().toList()
        assertThat(envelopes.map { it.id }.distinct()).hasSize(3)
        assertThat(envelopes.map { it.data }).containsExactly(1, 2, 3)
    }

    @Test
    suspend fun `Flow mapToEnvelopeWithRandomId with type generates UUIDs and sets type`() {
        val envelopes = flowOf("x", "y").mapToEnvelopeWithRandomId(type = "Anon").toList()
        assertThat(envelopes.map { it.id }.distinct()).hasSize(2)
        assertThat(envelopes).allMatch { it.type == "Anon" }
    }

    @Test
    suspend fun `F2Function mapToEnvelopeWithRandomId generates distinct UUIDs`() {
        val fn = F2Function<Int, String> { flow -> flow.map { it.toString() } }
        val envelopes = fn.mapToEnvelopeWithRandomId().invoke(flowOf(1, 2, 3)).toList()
        assertThat(envelopes.map { it.id }.distinct()).hasSize(3)
        assertThat(envelopes.map { it.data }).containsExactly("1", "2", "3")
    }
}
