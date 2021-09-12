package f2.feature.vc.fnc.config

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class VCConfig {

	@Value("\${f2.vc.signer.name}")
	private lateinit var signerName: String

	@Value("\${f2.vc.signer.key}")
	private lateinit var signerKey: String


	@Bean
	fun signer(): CredentialsKey {
		return CredentialsKey.loadFromFile(signerName, signerKey)
	}

	@Bean
	fun objectMapper(): ObjectMapper {
		return ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
			.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false)
			.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_ENUMS, true)
			.registerModule(KotlinModule())
			.registerModule(JavaTimeModule())
			.setPropertyNamingStrategy(PropertyNamingStrategies.LOWER_CAMEL_CASE)
	}
}
