/** A Kotlin value the export cannot express, such as an `Instant`: opaque to TypeScript. */
export declare interface KtOpaque<N extends string> {
    readonly __kotlinOpaque: N;
    readonly __doNotUseOrImplementIt: {
        readonly "kotlin.Opaque": unique symbol;
    };
}
/**
 * The JSON a Kotlin value of type `T` is sent as: a list or a set is an array, a map an object
 * (whose keys are optional when they are an enum's names), a `Long` a number, an enum its name,
 * an opaque value a string, and a nullable property may be absent. The exported types describe
 * the Kotlin objects; a body read from or written to the wire is a `Json<T>`, and the compiled
 * code accepts none.
 *
 * What it does not say: a sealed or polymorphic type carries no discriminant here, so a
 * serializer's `type` field is not typed; a nullable property is absent from the JSON only where
 * the serializer omits nulls (F2's `explicitNulls = false`, Jackson), a default kotlinx `Json`
 * writes `null`; and a `Long` beyond 2^53 loses its precision as a JavaScript number.
 */
export type Json<T> =
    T extends { asJsReadonlyArrayView(): ReadonlyArray<infer E> } ? Json<E>[]
    : T extends { asJsReadonlySetView(): ReadonlySet<infer E> } ? Json<E>[]
    : T extends { asJsReadonlyMapView(): ReadonlyMap<infer K, infer V> } ? JsonMap<JsonKey<K>, Json<V>>
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
// A map holds the entries it has: keys out of a finite set, such as an enum's names, are optional.
type JsonMap<K extends string | number, V> =
    string extends K ? Record<string, V> : number extends K ? Record<number, V> : { [P in K]?: V };
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
