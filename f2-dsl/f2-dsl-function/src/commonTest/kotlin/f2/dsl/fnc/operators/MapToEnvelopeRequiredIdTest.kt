package f2.dsl.fnc.operators

import f2.dsl.fnc.F2Function
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest

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
    fun `Flow mapToEnvelope preserves caller-supplied id`() = runTest {
        val cmds = flowOf(
            Cmd(msgId = "m-1", payload = "a"),
            Cmd(msgId = "m-2", payload = "b"),
            Cmd(msgId = "m-3", payload = "c"),
        )
        val envelopes = cmds.mapToEnvelope { it.msgId }.toList()
        assertEquals(expected = listOf("m-1", "m-2", "m-3"), actual = envelopes.map { it.id }.toList())
        assertEquals(expected = listOf("a", "b", "c"), actual = envelopes.map { it.data.payload }.toList())
    }

    @Test
    fun `Flow mapToEnvelope with type preserves caller-supplied id`() = runTest {
        val cmds = flowOf(Cmd(msgId = "m-1", payload = "a"), Cmd(msgId = "m-2", payload = "b"))
        val envelopes = cmds.mapToEnvelope(type = "Cmd") { it.msgId }.toList()
        assertEquals(expected = listOf("m-1", "m-2"), actual = envelopes.map { it.id }.toList())
        assertTrue(envelopes.all { it.type == "Cmd" })
    }

    @Test
    fun `F2Function mapToEnvelope preserves caller-supplied id`() = runTest {
        val fn = F2Function<Cmd, String> { flow -> flow.map { it.payload.uppercase() } }
        val envelopes = fn.mapToEnvelope { it.msgId }.invoke(
            flowOf(Cmd(msgId = "m-1", payload = "a"), Cmd(msgId = "m-2", payload = "b"))
        ).toList()
        assertEquals(expected = listOf("m-1", "m-2"), actual = envelopes.map { it.id }.toList())
        assertEquals(expected = listOf("A", "B"), actual = envelopes.map { it.data }.toList())
    }

    @Test
    fun `Flow mapToEnvelopeWithRandomId generates distinct UUIDs`() = runTest {
        val envelopes = flowOf(1, 2, 3).mapToEnvelopeWithRandomId().toList()
        assertEquals(expected = 3, actual = envelopes.map { it.id }.distinct().count())
        assertEquals(expected = listOf(1, 2, 3), actual = envelopes.map { it.data }.toList())
    }

    @Test
    fun `Flow mapToEnvelopeWithRandomId with type generates UUIDs and sets type`() = runTest {
        val envelopes = flowOf("x", "y").mapToEnvelopeWithRandomId(type = "Anon").toList()
        assertEquals(expected = 2, actual = envelopes.map { it.id }.distinct().count())
        assertTrue(envelopes.all { it.type == "Anon" })
    }

    @Test
    fun `F2Function mapToEnvelopeWithRandomId generates distinct UUIDs`() = runTest {
        val fn = F2Function<Int, String> { flow -> flow.map { it.toString() } }
        val envelopes = fn.mapToEnvelopeWithRandomId().invoke(flowOf(1, 2, 3)).toList()
        assertEquals(expected = 3, actual = envelopes.map { it.id }.distinct().count())
        assertEquals(expected = listOf("1", "2", "3"), actual = envelopes.map { it.data }.toList())
    }
}
