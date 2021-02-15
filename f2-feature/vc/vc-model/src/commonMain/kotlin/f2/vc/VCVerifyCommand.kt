package f2.vc

import city.smartb.f2.dsl.cqrs.Command

class VCVerifyCommand<T>(
	val claims: VC<T>
): Command
