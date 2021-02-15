package f2.ssm

interface SSMFunction {
	suspend fun perform(cmd: SsmPerformCommand): SsmPerformedEvent
	suspend fun start(cmd: SsmStartCommand): SsmStartedEvent
	suspend fun init(cmd: SsmInitCommand): SsmInitedEvent
}