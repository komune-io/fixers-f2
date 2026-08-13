package f2.feature.cloudEvent.storming

import f2.feature.cloudEvent.storming.entity.CloudEventEntity
import java.util.UUID
import kotlinx.coroutines.flow.toList
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
     * whatever [ReactiveRepositoryFactorySupport] the host application exposes. This stub only fills
     * in the storage-specific hooks and leaves `getRepository` to the real factory machinery, so the
     * test proves that the reactive factory can build a proxy for a
     * [org.springframework.data.repository.kotlin.CoroutineCrudRepository]-based interface.
     */
    private val repositoryFactory = object : ReactiveRepositoryFactorySupport() {
        override fun getRepositoryBaseClass(metadata: RepositoryMetadata): Class<*> =
            InMemoryCloudEventEntityRepository::class.java

        override fun getTargetRepository(metadata: RepositoryInformation): Any = repository

        override fun getEntityInformation(
            metadata: RepositoryMetadata
        ): EntityInformation<*, *> = object : EntityInformation<CloudEventEntity, UUID> {
            override fun isNew(entity: CloudEventEntity): Boolean = true
            override fun getId(entity: CloudEventEntity): UUID = entity.id
            override fun getIdType(): Class<UUID> = UUID::class.java
            override fun getJavaType(): Class<CloudEventEntity> = CloudEventEntity::class.java
        }
    }

    private val config = F2StormingCloudEventConfig()

    @Test
    suspend fun `cloudEventEntityRepository should be materialised by the reactive repository factory`() {
        val proxied = config.cloudEventEntityRepository(repositoryFactory)

        // a real proxy is built around the target, not the target itself handed back
        assertThat(proxied).isNotSameAs(repository)

        // the suspending and Flow-returning methods round-trip through the proxy
        val entity = CloudEventEntity(id = UUID.randomUUID(), event = cloudEvent("event-1"))
        proxied.save(entity)

        assertThat(repository.saved).containsExactly(entity)
        assertThat(proxied.findAll().toList()).containsExactly(entity)
        assertThat(proxied.findById(entity.id)).isSameAs(entity)
    }

    @Test
    suspend fun `stormingCommandSink should be wired with the repository`() {
        val objectMapper = JsonMapper.builder().addModule(KotlinModule.Builder().build()).build()

        config.stormingCommandSink(repository, objectMapper)
            .storeCommand(CreateUserCommand(name = "john", age = 42))

        assertThat(repository.saved).hasSize(1)
    }

    @Test
    suspend fun `stormingCloudEventSink should be wired with the repository`() {
        val event = cloudEvent("event-1")

        config.stormingCloudEventSink(repository).storeCommand(event)

        assertThat(repository.saved.single().event).isSameAs(event)
    }

    private fun cloudEvent(id: String) = f2.dsl.event.CloudEvent(
        eventType = "UserCreated",
        cloudEventsVersion = "1",
        source = "S2",
        eventID = id,
        data = """{"name":"john"}"""
    )
}
