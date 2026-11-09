declare function KtSingleton<T>(): T & (abstract new() => any);
/** @deprecated  */
export declare const initHook: {
    get(): any;
};
export declare interface F2Consumer<T> {
}
export declare interface F2Function<in T, out R> {
}
export declare interface F2Supplier<R> {
}
export declare interface F2SupplierSingle<R> {
}
export declare class Batch {
    constructor(size?: number, concurrency?: number);
    get size(): number;
    get concurrency(): number;
    copy(size?: number, concurrency?: number): Batch;
    toString(): string;
    hashCode(): number;
    equals(other?: any): boolean;
}
export declare namespace Batch {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new () => Batch;
    }
}
export declare function invokeF2Function<T, R>(fnc: F2Function<T, R>, msgs: Array<T>): Promise<Array<R>>;
export declare function invokeF2Consumer<T>(fnc: F2Consumer<T>, msgs: Array<T>): Promise<void>;
export declare function invokeF2Supplier<R>(fnc: F2Supplier<R>): Promise<Array<R>>;
export declare function invokeF2SupplierSingle<R>(fnc: F2SupplierSingle<R>): Promise<R>;
export type F2ClientType = "HTTP";
export declare namespace F2ClientType {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new () => F2ClientType;
    }
}
export declare interface F2Client {
    readonly type: F2ClientType;
}
export declare abstract class AuthRealm {
    private constructor();
    get serverUrl(): string;
    get realmId(): string;
    get clientId(): string;
    get redirectUrl(): string | null;
}
export declare namespace AuthRealm {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new () => AuthRealm;
    }
}
export declare class AuthRealmPassword extends AuthRealm.$metadata$.constructor {
    constructor(serverUrl: string, realmId: string, redirectUrl: string, clientId: string, username: string, password: string);
    get serverUrl(): string;
    get realmId(): string;
    get redirectUrl(): string;
    get clientId(): string;
    get username(): string;
    get password(): string;
    copy(serverUrl?: string, realmId?: string, redirectUrl?: string, clientId?: string, username?: string, password?: string): AuthRealmPassword;
    toString(): string;
    hashCode(): number;
    equals(other?: any): boolean;
}
export declare namespace AuthRealmPassword {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new () => AuthRealmPassword;
    }
}
export declare class AuthRealmClientSecret extends AuthRealm.$metadata$.constructor {
    constructor(serverUrl: string, realmId: string, clientId: string, redirectUrl: string | null | undefined, clientSecret: string, isPublic?: boolean);
    get serverUrl(): string;
    get realmId(): string;
    get clientId(): string;
    get redirectUrl(): string | null;
    get clientSecret(): string;
    get isPublic(): boolean;
    copy(serverUrl?: string, realmId?: string, clientId?: string, redirectUrl?: string | null, clientSecret?: string, isPublic?: boolean): AuthRealmClientSecret;
    toString(): string;
    hashCode(): number;
    equals(other?: any): boolean;
}
export declare namespace AuthRealmClientSecret {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new () => AuthRealmClientSecret;
    }
}
export declare class TokenInfo {
    constructor(accessToken: string, expiresIn: number, refreshExpiresIn: number | null | undefined, tokenType: string, scope?: string | null, idToken?: string | null, refreshToken?: string | null);
    get accessToken(): string;
    get expiresIn(): number;
    get refreshExpiresIn(): number | null;
    get tokenType(): string;
    get scope(): string | null;
    get idToken(): string | null;
    get refreshToken(): string | null;
    copy(accessToken?: string, expiresIn?: number, refreshExpiresIn?: number | null, tokenType?: string, scope?: string | null, idToken?: string | null, refreshToken?: string | null): TokenInfo;
    toString(): string;
    hashCode(): number;
    equals(other?: any): boolean;
}
export declare namespace TokenInfo {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new () => TokenInfo;
    }
}
export declare interface Command extends Message {
}
export declare interface Event extends Message {
}
export declare interface Message {
}
export declare interface Problem extends Message {
}
export declare interface Query extends Message {
}
export declare interface EnvelopeDTO<T> extends WithEnvelopeId, WithEnvelopeData<T>, Message {
    readonly id: string;
    readonly data: T;
    readonly type: string;
    readonly datacontenttype?: string | null;
    readonly specversion?: string | null;
    readonly source?: string | null;
    readonly time?: string | null;
}
export declare class Envelope<T> implements EnvelopeDTO<T> {
    constructor(id: string, data: T, type: string, datacontenttype?: string | null, specversion?: string | null, source?: string | null, time?: string | null);
    get id(): string;
    get data(): T;
    get type(): string;
    get datacontenttype(): string | null;
    get specversion(): string | null;
    get source(): string | null;
    get time(): string | null;
}
export declare namespace Envelope {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new <T>() => Envelope<T>;
    }
}
export declare interface WithEnvelopeData<T> {
    readonly data: T;
}
export declare interface WithEnvelopeId {
    readonly id: string;
}
export declare interface F2ErrorDTO extends Problem {
    readonly id?: string | null;
    readonly timestamp: string;
    readonly code: number;
    readonly requestId?: string | null;
    readonly message: string;
}
export declare class F2Error implements F2ErrorDTO {
    constructor(message: string, id?: string | null, timestamp?: string, code?: number, requestId?: string | null);
    get message(): string;
    get id(): string | null;
    get timestamp(): string;
    get code(): number;
    get requestId(): string | null;
    toString(): string;
}
export declare namespace F2Error {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new () => F2Error;
    }
}
export declare class F2Exception extends /* RuntimeException */ Error {
    constructor(error: F2ErrorDTO, cause?: Error | null);
    get error(): F2ErrorDTO;
}
export declare namespace F2Exception {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new () => F2Exception;
    }
    abstract class Companion extends KtSingleton<Companion.$metadata$.constructor>() {
        private constructor();
    }
    namespace Companion {
        /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
        namespace $metadata$ {
            abstract class constructor {
                invoke(message: string, id?: string, requestId?: string, code?: number, cause?: Error | null): F2Exception;
                private constructor();
            }
        }
    }
}
export declare interface Match<T> {
    readonly negative: boolean;
    map<R>(transform: (value: T) => R): Match<R>;
    not(): Match<T>;
    and(match: Match<T>): Match<T>;
    or(match: Match<T>): Match<T>;
}
export declare interface SortDTO {
    readonly property: string;
    readonly ascending: boolean;
    readonly nullsFirst?: boolean | null;
}
export declare interface PageDTO<out OBJECT> {
    readonly total: number;
    readonly items: OBJECT[];
}
export declare class Page<out OBJECT> implements PageDTO<OBJECT> {
    constructor(total: number, items: OBJECT[]);
    get total(): number;
    get items(): OBJECT[];
}
export declare namespace Page {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new <OBJECT>() => Page<OBJECT>;
    }
}
export declare interface PageQueryDTO extends Query {
    readonly pagination?: OffsetPaginationDTO | null;
}
export declare interface PageQueryResultDTO<out OBJECT> extends Event, PageDTO<OBJECT> {
    readonly total: number;
    readonly items: OBJECT[];
    readonly pagination?: OffsetPaginationDTO | null;
}
export declare class PageQuery implements PageQueryDTO {
    constructor(pagination?: OffsetPaginationDTO | null);
    get pagination(): OffsetPaginationDTO | null;
}
export declare namespace PageQuery {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new () => PageQuery;
    }
}
export declare class PageQueryResult<out OBJECT> implements PageQueryResultDTO<OBJECT> {
    constructor(pagination: OffsetPagination | null, total: number, items: OBJECT[]);
    get pagination(): OffsetPagination | null;
    get total(): number;
    get items(): OBJECT[];
}
export declare namespace PageQueryResult {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new <OBJECT>() => PageQueryResult<OBJECT>;
    }
}
export declare interface Pagination {
}
export declare interface OffsetPaginationDTO extends Pagination {
    readonly offset: number;
    readonly limit: number;
}
export declare interface PagePaginationDTO extends Pagination {
    readonly page?: number | null;
    readonly size?: number | null;
}
export declare class OffsetPagination implements OffsetPaginationDTO {
    constructor(offset: number, limit: number);
    get offset(): number;
    get limit(): number;
}
export declare namespace OffsetPagination {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new () => OffsetPagination;
    }
}
export declare class PagePagination implements PagePaginationDTO {
    constructor(page?: number | null, size?: number | null);
    get page(): number | null;
    get size(): number | null;
}
export declare namespace PagePagination {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new () => PagePagination;
    }
}
export declare interface HttpClientBuilder {
    build(urlBase: string): HttpF2Client;
}
export declare class HttpF2Client implements F2Client {
    private constructor();
    get urlBase(): string;
    get type(): F2ClientType;
}
export declare namespace HttpF2Client {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new () => HttpF2Client;
    }
}
export declare interface F2UploadCommand<COMMAND> {
    readonly command: COMMAND;
    readonly fileMap: Record<string, F2FilePart[]>;
}
export declare class F2UploadSingleCommand<COMMAND> implements F2UploadCommand<COMMAND> {
    constructor(command: COMMAND, file: F2FilePart);
    get command(): COMMAND;
    get file(): F2FilePart;
    get fileMap(): Record<string, F2FilePart[]>;
    copy(command?: COMMAND, file?: F2FilePart): F2UploadSingleCommand<COMMAND>;
    toString(): string;
    hashCode(): number;
    equals(other?: any): boolean;
}
export declare namespace F2UploadSingleCommand {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new <COMMAND>() => F2UploadSingleCommand<COMMAND>;
    }
}
export declare class F2UploadMultipleCommand<COMMAND> implements F2UploadCommand<COMMAND> {
    constructor(command: COMMAND, files: F2FilePart[]);
    get command(): COMMAND;
    get files(): F2FilePart[];
    get fileMap(): Record<string, F2FilePart[]>;
    copy(command?: COMMAND, files?: F2FilePart[]): F2UploadMultipleCommand<COMMAND>;
    toString(): string;
    hashCode(): number;
    equals(other?: any): boolean;
}
export declare namespace F2UploadMultipleCommand {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new <COMMAND>() => F2UploadMultipleCommand<COMMAND>;
    }
}
export declare class F2FilePart {
    private constructor();
    get name(): string;
    get contentType(): string | null;
    static fromBytes(name: string, bytes: Int8Array, contentType?: string | null): F2FilePart;
}
export declare namespace F2FilePart {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new () => F2FilePart;
    }
}
export declare function httpClientBuilderDefault(): HttpClientBuilder;