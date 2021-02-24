package f2.feature.vc

import f2.client.F2Client
import f2.vc.VC
import f2.vc.VCFunction
import f2.vc.VCRemoteFunction
import f2.vc.functions.VCSignCommand
import f2.vc.functions.VCSignFunction
import f2.vc.functions.VCVerifyCommand
import f2.vc.functions.VCVerifyFunction
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

expect open class VCFunctionClient<T>(client: F2Client) : VCRemoteFunction<T>