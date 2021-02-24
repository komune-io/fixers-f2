package f2.feature.ssm.fnc

import city.smartb.f2.function.spring.adapter.flow
import f2.dsl.F2Flow
import f2.feature.ssm.fnc.extentions.asAgent
import f2.ssm.*
import f2.ssm.functions.*
import kotlinx.coroutines.future.await
import org.civis.blockchain.ssm.client.SsmClient
import org.civis.blockchain.ssm.client.domain.Context
import org.civis.blockchain.ssm.client.domain.Session
import org.civis.blockchain.ssm.client.domain.Signer
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Service


@Service
class SsmFunctionImpl(
	private val ssmClient: SsmClient,
	private val initializer: SsmInitializer,
	private val signer: Signer,
) : SSMFunction {

	@Bean
	override fun perform(): F2Flow<SsmPerformCommand, SsmPerformedEvent> = flow { cmd ->
		val invokeReturn = ssmClient.perform(signer, cmd.action, cmd.context.toSsmContext()).await()
		SsmPerformedEvent(
			InvokeReturn(
				status = invokeReturn.status,
				info = invokeReturn.info,
				transactionId = invokeReturn.transactionId
			)
		)
	}

	@Bean
	override fun start(): F2Flow<SsmStartCommand, SsmStartedEvent> = flow { cmd ->
		val invokeReturn = ssmClient.start(signer, cmd.session.toSession()).await()
		SsmStartedEvent(
			InvokeReturn(
				status = invokeReturn.status,
				info = invokeReturn.info,
				transactionId = invokeReturn.transactionId
			)
		)
	}


	@Bean
	override fun init(): F2Flow<SsmInitCommand, SsmInitedEvent> = flow { cmd ->
		val invokeReturns = initializer.init(signer.asAgent(), cmd.ssm.toSsm())
		SsmInitedEvent(
			invokeReturns = invokeReturns.toInvokeReturns()
		)
	}


}

fun SsmContext.toSsmContext() = Context(
	this.session, this.public, this.iteration, this.private
)

fun SsmSession.toSession() = Session(
	this.ssm, this.session, this.publicMessage, this.roles, this.privateMessage
)

fun Ssm.toSsm() = org.civis.blockchain.ssm.client.domain.Ssm(
	this.name, this.transitions.toTransitions()
)

fun List<SsmTransition>.toTransitions() = map { it.toTransition() }


fun SsmTransition.toTransition() = org.civis.blockchain.ssm.client.domain.Ssm.Transition(
	this.from, this.to, this.role, this.command
)

fun org.civis.blockchain.ssm.client.repository.InvokeReturn.toInvokeReturn() = InvokeReturn(
	status = this.status,
	info = this.info,
	transactionId = this.transactionId
)

fun List<org.civis.blockchain.ssm.client.repository.InvokeReturn>.toInvokeReturns() = map { it.toInvokeReturn() }

