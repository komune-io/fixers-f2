package f2.feature.ssm.fnc.config

import com.fasterxml.jackson.databind.*
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.civis.blockchain.ssm.client.SsmClient
import org.civis.blockchain.ssm.client.SsmClientConfig
import org.civis.blockchain.ssm.client.SsmRequester
import org.civis.blockchain.ssm.client.domain.Signer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SSMConfig {

	@Value("\${f2.ssm.signer.name}")
	private lateinit var signerName: String

	@Value("\${f2.ssm.signer.key}")
	private lateinit var signerKey: String

	@Value("\${f2.ssm.baseUrl}")
	private lateinit var ssmBaseUrl: String

	@Bean
	fun signer(): Signer {
		return Signer.loadFromFile(signerName, signerKey)
	}

	@Bean
	fun ssmClient(): SsmClient {
		val ssmClientConfig = SsmClientConfig(ssmBaseUrl)
		return SsmClient.fromConfig(ssmClientConfig)
	}

	@Bean
	protected fun objectMapper(): ObjectMapper {
		return ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
			.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
			.registerModule(KotlinModule())
			.setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
	}

}