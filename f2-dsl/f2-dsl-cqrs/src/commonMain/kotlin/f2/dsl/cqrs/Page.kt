package f2.dsl.cqrs

import kotlinx.serialization.Serializable

@Serializable
expect interface Page<OBJECT> {
	val page: Int
	val size: Int
	val total: Long
	val list: List<OBJECT>
}
