package f2.dsl.cqrs.filter

import kotlin.js.JsExport
import kotlin.js.JsName
import kotlinx.serialization.Serializable

@JsExport
@JsName("SortDTO")
interface SortDTO {
    val property: String
    val isAscending: Boolean
    val areNullsFirst: Boolean?
}

@Serializable
data class SortDTOBase(
    override val property: String,
    override val isAscending: Boolean,
    override val areNullsFirst: Boolean
): SortDTO

@Serializable
data class Sort<P: Enum<*>>(
    val property: P,
    val isAscending: Boolean,
    val areNullsFirst: Boolean
)

inline fun <reified E : Enum<E>> SortDTOBase.toSort() = Sort(
    property = enumValueOf<E>(property),
    isAscending = isAscending,
    areNullsFirst = areNullsFirst
)
