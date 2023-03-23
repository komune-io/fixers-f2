package f2.dsl.cqrs.filter

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable

@JsExport
@JsName("SortDTO")
interface SortDTO {
    val property: String
    val ascending: Boolean
    val nullsFirst: Boolean?
}

@Serializable
data class SortDTOBase(
    override val property: String,
    override val ascending: Boolean,
    override val nullsFirst: Boolean
): SortDTO

@Serializable
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
