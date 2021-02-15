package f2.feature.ssm.fnc.extentions

import org.civis.blockchain.ssm.client.domain.Agent
import org.civis.blockchain.ssm.client.domain.Signer

fun Signer.asAgent(): Agent {
    return Agent(
            this.name,
            this.pair.public.encoded
    )
}