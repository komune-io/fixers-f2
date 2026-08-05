package f2.spring

import kotlinx.serialization.Serializable

@Serializable
data class TestData(val name: String, val value: Int)

@Serializable
data class RequiredFieldData(val required: String)

data class NonAnnotatedData(val name: String, val value: Int)
