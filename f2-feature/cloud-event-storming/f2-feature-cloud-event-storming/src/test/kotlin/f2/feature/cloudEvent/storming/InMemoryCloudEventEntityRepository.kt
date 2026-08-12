package f2.feature.cloudEvent.storming

import f2.feature.cloudEvent.storming.entity.CloudEventEntity
import f2.feature.cloudEvent.storming.entity.CloudEventEntityRepository
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

/**
 * In-memory stand-in for the reactive repository, so the sinks and the supplier can be exercised
 * without a database. The module declares no R2DBC driver: the repository is built at runtime from
 * the host application's [org.springframework.data.repository.core.support.ReactiveRepositoryFactorySupport].
 *
 * `save` is deliberately lazy ([Mono.fromCallable]), like a real reactive repository: nothing is
 * written until the returned publisher is subscribed.
 */
open class InMemoryCloudEventEntityRepository : CloudEventEntityRepository {

    /** Entities actually written, i.e. for which the returned [Mono] has been subscribed. */
    val saved = mutableListOf<CloudEventEntity>()

    /** Number of `save` calls, whether or not the returned [Mono] has been subscribed. */
    var saveInvocations = 0

    override fun <S : CloudEventEntity> save(entity: S): Mono<S> {
        saveInvocations++
        return Mono.fromCallable {
            saved.add(entity)
            entity
        }
    }

    override fun <S : CloudEventEntity> saveAll(entities: MutableIterable<S>): Flux<S> =
        Flux.fromIterable(entities).flatMap { save(it) }

    override fun <S : CloudEventEntity> saveAll(entityStream: Publisher<S>): Flux<S> =
        Flux.from(entityStream).flatMap { save(it) }

    override fun findAll(): Flux<CloudEventEntity> = Flux.fromIterable(saved.toList())

    override fun findById(id: String): Mono<CloudEventEntity> =
        Mono.justOrEmpty(saved.firstOrNull { it.id.toString() == id })

    override fun findById(id: Publisher<String>): Mono<CloudEventEntity> =
        Mono.from(id).flatMap(::findById)

    override fun existsById(id: String): Mono<Boolean> = findById(id).hasElement()

    override fun existsById(id: Publisher<String>): Mono<Boolean> = findById(id).hasElement()

    override fun findAllById(ids: MutableIterable<String>): Flux<CloudEventEntity> =
        Flux.fromIterable(ids).flatMap(::findById)

    override fun findAllById(idStream: Publisher<String>): Flux<CloudEventEntity> =
        Flux.from(idStream).flatMap(::findById)

    override fun count(): Mono<Long> = Mono.fromCallable { saved.size.toLong() }

    override fun deleteById(id: String): Mono<Void> = Mono.fromRunnable {
        saved.removeIf { it.id.toString() == id }
    }

    override fun deleteById(id: Publisher<String>): Mono<Void> =
        Mono.from(id).flatMap(::deleteById).then()

    override fun delete(entity: CloudEventEntity): Mono<Void> = Mono.fromRunnable {
        saved.remove(entity)
    }

    override fun deleteAllById(ids: MutableIterable<String>): Mono<Void> =
        Flux.fromIterable(ids).flatMap(::deleteById).then()

    override fun deleteAll(entities: MutableIterable<CloudEventEntity>): Mono<Void> =
        Flux.fromIterable(entities).flatMap(::delete).then()

    override fun deleteAll(entityStream: Publisher<out CloudEventEntity>): Mono<Void> =
        Flux.from(entityStream).flatMap(::delete).then()

    override fun deleteAll(): Mono<Void> = Mono.fromRunnable { saved.clear() }
}
