package f2.feature.cloudEvent.storming

import f2.feature.cloudEvent.storming.entity.CloudEventEntity
import f2.feature.cloudEvent.storming.entity.CloudEventEntityRepository
import java.util.UUID
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.onEach

/**
 * In-memory stand-in for the coroutine repository, so the sinks and the supplier can be exercised
 * without a database. The module declares no R2DBC driver: the repository is built at runtime from
 * the host application's [org.springframework.data.repository.core.support.ReactiveRepositoryFactorySupport].
 *
 * `save` is a plain suspending call: the entity is written by the time it returns. The former
 * reactive interface returned a cold `Mono`, which forced this stub to distinguish "save invoked"
 * from "save subscribed" — that distinction no longer exists, so [saved] is the whole story.
 */
open class InMemoryCloudEventEntityRepository : CloudEventEntityRepository {

    /** Entities written by [save]. */
    val saved = mutableListOf<CloudEventEntity>()

    override suspend fun <S : CloudEventEntity> save(entity: S): CloudEventEntity {
        saved.add(entity)
        return entity
    }

    override fun <S : CloudEventEntity> saveAll(entities: Iterable<S>): Flow<S> =
        entities.asFlow().onEach { save(it) }

    override fun <S : CloudEventEntity> saveAll(entityStream: Flow<S>): Flow<S> =
        entityStream.onEach { save(it) }

    override fun findAll(): Flow<CloudEventEntity> = saved.toList().asFlow()

    override suspend fun findById(id: UUID): CloudEventEntity? =
        saved.firstOrNull { it.id == id }

    override suspend fun existsById(id: UUID): Boolean = findById(id) != null

    override fun findAllById(ids: Iterable<UUID>): Flow<CloudEventEntity> =
        ids.asFlow().mapNotNull { findById(it) }

    override fun findAllById(ids: Flow<UUID>): Flow<CloudEventEntity> =
        ids.mapNotNull { findById(it) }

    override suspend fun count(): Long = saved.size.toLong()

    override suspend fun deleteById(id: UUID) {
        saved.removeIf { it.id == id }
    }

    override suspend fun delete(entity: CloudEventEntity) {
        saved.remove(entity)
    }

    override suspend fun deleteAllById(ids: Iterable<UUID>) = ids.forEach { deleteById(it) }

    override suspend fun deleteAll(entities: Iterable<CloudEventEntity>) = entities.forEach { delete(it) }

    override suspend fun <S : CloudEventEntity> deleteAll(entityStream: Flow<S>) =
        entityStream.collect { delete(it) }

    override suspend fun deleteAll() = saved.clear()
}
