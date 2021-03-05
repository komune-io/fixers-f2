package f2.feature.ssm.function

import kotlinx.coroutines.future.await
import org.civis.blockchain.ssm.client.SsmClient
import org.civis.blockchain.ssm.client.domain.Agent
import org.civis.blockchain.ssm.client.domain.SignerAdmin
import org.civis.blockchain.ssm.client.domain.Ssm
import org.civis.blockchain.ssm.client.repository.InvokeReturn
import org.springframework.stereotype.Service


@Service
class SsmInitializer(
        private val ssmClient: SsmClient,
        private val signerAdmin: SignerAdmin
        ) {

    suspend fun init(agent: Agent, ssm: Ssm): List<InvokeReturn> {
        val retInitUser = initUser(agent)
        val retInitSsm = initSsm(ssm)
        return listOfNotNull(retInitUser, retInitSsm)
    }

    suspend fun initSsm(ssm: Ssm): InvokeReturn? {
        return createIfNotExist(ssm, { this.fetchSsm(ssm.name) }, { this.createSsm(it) })
    }

    suspend fun initUser(user: Agent): InvokeReturn? {
        return createIfNotExist(user, { this.fetchUser(user.name) }, { this.createUser(it) })
    }

    private suspend fun fetchSsm(name: String): Ssm? {
        return ssmClient.getSsm(name).await().orElse(null)
    }

    private suspend fun fetchUser(name: String): Agent? {
        return ssmClient.getAgent(name).await().orElse(null)
    }
    
    private suspend fun <T> createIfNotExist(objToCreate: T,  getFnc: suspend () -> T?, create:  suspend (T) -> InvokeReturn): InvokeReturn? {
        return if(getFnc()!= null) return null
        else
            create(objToCreate)
    }

    private suspend fun createSsm(ssm: Ssm): InvokeReturn {
        try {
            return ssmClient.create(signerAdmin, ssm).await()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }

    private suspend fun createUser(agent: Agent): InvokeReturn {
        try {
            return ssmClient.registerUser(signerAdmin, agent).await()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
