/** A Kotlin value the export cannot express, such as an `Instant`: opaque to TypeScript. */
export declare interface KtOpaque<N extends string> {
    readonly __kotlinOpaque: N;
    readonly __doNotUseOrImplementIt: {
        readonly "kotlin.Opaque": unique symbol;
    };
}
/**
 * The JSON a Kotlin value of type `T` is sent as: a list or a set is an array, a map an object, a
 * `Long` a number, an enum its name, an opaque value a string, and a nullable property may be
 * absent. The exported types describe the Kotlin objects; a body read from or written to the wire
 * is a `Json<T>`, and the compiled code accepts none.
 */
export type Json<T> =
    T extends { asJsReadonlyArrayView(): ReadonlyArray<infer E> } ? Json<E>[]
    : T extends { asJsReadonlySetView(): ReadonlySet<infer E> } ? Json<E>[]
    : T extends { asJsReadonlyMapView(): ReadonlyMap<infer K, infer V> } ? Record<JsonKey<K>, Json<V>>
    : T extends { readonly __kotlinOpaque: string } ? string
    : T extends bigint ? number
    : T extends string | number | boolean | null | undefined ? T
    : T extends { readonly name: infer N extends string; readonly ordinal: infer O extends number }
        ? string extends N ? JsonObject<T> : number extends O ? JsonObject<T> : N
    : T extends ReadonlyArray<infer E> ? Json<E>[]
    : T extends (...args: never[]) => unknown ? never
    : T extends object ? JsonObject<T>
    : T;
type JsonKey<K> = Json<K> extends string | number ? Json<K> : string;
type JsonField<T, P extends keyof T> =
    P extends "__doNotUseOrImplementIt" ? never : T[P] extends (...args: never[]) => unknown ? never : P;
type JsonFields<T> = { [P in keyof T]-?: JsonField<T, P> }[keyof T];
type JsonOptional<T> = {
    [P in JsonFields<T>]-?: null extends T[P] ? P : undefined extends T[P] ? P : never
}[JsonFields<T>];
type JsonObject<T> =
    { [P in Exclude<JsonFields<T>, JsonOptional<T>>]: Json<T[P]> } & { [P in JsonOptional<T>]?: Json<T[P]> };
type Nullable<T> = T | null | undefined
declare function KtSingleton<T>(): T & (abstract new() => object);
export declare interface KtMap<K, out V> {
    asJsReadonlyMapView(): ReadonlyMap<K, V>;
    readonly __doNotUseOrImplementIt: {
        readonly "kotlin.collections.KtMap": unique symbol;
    };
}
export declare namespace KtMap {
    function fromJsMap<K, V>(map: ReadonlyMap<K, V>): KtMap<K, V>;
}
export declare class CloudEvent<T> {
    constructor(eventType: string, eventTypeVersion: Nullable<string> | undefined, cloudEventsVersion: string, source: string, eventID: string, eventTime: Nullable<string> | undefined, schemaURL: Nullable<string> | undefined, contentType: Nullable<string> | undefined, extensions: Nullable<KtMap<string, string>> | undefined, data: T);
    get eventType(): string;
    get eventTypeVersion(): Nullable<string>;
    get cloudEventsVersion(): string;
    get source(): string;
    get eventID(): string;
    get eventTime(): Nullable<string>;
    get schemaURL(): Nullable<string>;
    get contentType(): Nullable<string>;
    get extensions(): Nullable<KtMap<string, string>>;
    get data(): T;
}
export declare namespace CloudEvent {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new <T>() => CloudEvent<T>;
    }
}
export declare class SnapEvent<T> extends CloudEvent.$metadata$.constructor<T> {
    constructor(eventType: string, eventTypeVersion: Nullable<string> | undefined, cloudEventsVersion: string, source: string, eventID: string, eventTime: Nullable<string> | undefined, schemaURL: Nullable<string> | undefined, contentType: Nullable<string> | undefined, extensions: Nullable<KtMap<string, string>> | undefined, data: T);
}
export declare namespace SnapEvent {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new <T>() => SnapEvent<T>;
    }
}