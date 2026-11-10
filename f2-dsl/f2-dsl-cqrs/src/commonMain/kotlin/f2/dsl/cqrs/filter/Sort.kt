package f2.dsl.cqrs.filter

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable

// `ascending` and `nullsFirst` are wire names: kotlinx, Jackson and a JS caller's object all read and write the
// property name, and a client of an earlier F2 sends these. An `is` prefix would change the JSON.
@JsExport
@JsName("SortDTO")
@Suppress("BooleanPropertyNaming") // wire names, see above
interface SortDTO {
    val property: String
    val ascending: Boolean
    val nullsFirst: Boolean?
}

@Serializable
@Suppress("BooleanPropertyNaming") // wire names, see SortDTO
data class SortDTOBase(
    override val property: String,
    override val ascending: Boolean,
    override val nullsFirst: Boolean
): SortDTO

@Serializable
@Suppress("BooleanPropertyNaming") // wire names, see SortDTO
data class Sort<P: Enum<*>>(
    val property: P,
    val ascending: Boolean,
    val nullsFirst: Boolean
)

inline fun <reified E : Enum<E>> SortDTOBase.toSort() = Sort(
    property = enumValueOf<E>(property),
    ascending = ascending,
    nullsFirst = nullsFirst
)
