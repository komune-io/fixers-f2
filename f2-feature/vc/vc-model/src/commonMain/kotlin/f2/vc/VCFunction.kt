package f2.vc

interface VCFunction<T> {
	suspend fun sign(cmd: VCSignCommand<T>): VC<T>
	suspend fun verify(cmd: VCVerifyCommand<T>): Boolean
}