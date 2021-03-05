package f2.dsl.function

data class FunctionMeta(
	val name: String,
	val desc: String,
	val params: List<ParameterMeta>,
	val ret: ParameterMeta
)

data class ParameterMeta(
	val name: String,
	val description: String,
	val type: String,
	val nullable: Boolean
)

