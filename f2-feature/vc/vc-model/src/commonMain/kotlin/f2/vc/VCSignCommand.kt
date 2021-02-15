package f2.vc

import city.smartb.f2.dsl.cqrs.Command

class VCSignCommand<T>(
	val identifier: String,
	val claims: T
): Command
