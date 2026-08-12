package f2.feature.cloudEvent.storming

import f2.feature.cloudEvent.storming.entity.CloudEventEntityRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.data.repository.core.EntityInformation
import org.springframework.data.repository.core.RepositoryInformation
import org.springframework.data.repository.core.RepositoryMetadata
import org.springframework.data.repository.core.support.ReactiveRepositoryFactorySupport
import tools.jackson.databind.json.JsonMapper
import tools.jackson.module.kotlin.KotlinModule

class F2StormingCloudEventConfigTest {

    private val repository = InMemoryCloudEventEntityRepository()

    /**
     * The module does not bring a Spring Data module of its own: the repository is materialised from
     * whatever [ReactiveRepositoryFactorySupport] the host application exposes. This stub only has to
     * hand back a repository proxy for the requested interface.
     */
    private val repositoryFactory = object : ReactiveRepositoryFactorySupport() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : Any> getRepository(repositoryInterface: Class<T>): T {
            check(repositoryInterface == CloudEventEntityRepository::class.java) {
                "unexpected repository interface $repositoryInterface"
            }
            return repository as T
        }

        override fun getRepositoryBaseClass(metadata: RepositoryMetadata): Class<*> =
            InMemoryCloudEventEntityRepository::class.java

        override fun getTargetRepository(metadata: RepositoryInformation): Any = repository

        override fun getEntityInformation(
            metadata: RepositoryMetadata
        ): EntityInformation<*, *> = throw UnsupportedOperationException("not needed by the test")
    }

    private val config = F2StormingCloudEventConfig()

    @Test
    fun `cloudEventEntityRepository should be built from the reactive repository factory`() {
        assertThat(config.cloudEventEntityRepository(repositoryFactory)).isSameAs(repository)
    }

    @Test
    fun `stormingCommandSink should be wired with the repository`() {
        val objectMapper = JsonMapper.builder().addModule(KotlinModule.Builder().build()).build()

        config.stormingCommandSink(repository, objectMapper)
            .storeCommand(CreateUserCommand(name = "john", age = 42))
            .block()

        assertThat(repository.saved).hasSize(1)
    }

    @Test
    fun `stormingCloudEventSink should be wired with the repository`() {
        val event = f2.dsl.event.CloudEvent(
            eventType = "UserCreated",
            cloudEventsVersion = "1",
            source = "S2",
            eventID = "event-1",
            data = """{"name":"john"}"""
        )

        config.stormingCloudEventSink(repository).storeCommand(event).block()

        assertThat(repository.saved.single().event).isSameAs(event)
    }
}
