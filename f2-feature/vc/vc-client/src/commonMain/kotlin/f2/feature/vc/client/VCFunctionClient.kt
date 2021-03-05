package f2.feature.vc.client

import f2.client.F2Client
import f2.vc.model.VCRemoteFunction

expect open class VCFunctionClient<T>(client: F2Client) : VCRemoteFunction<T>