package f2.vc

import f2.vc.functions.*

interface VCFunction<T> {
	fun sign(): VCSignFunction<T>
	fun verify(): VCVerifyFunction<T>
}

interface VCRemoteFunction<T> {
	fun sign(): VCSignRemoteFunction<T>
	fun verify(): VCVerifyRemoteFunction<T>
}