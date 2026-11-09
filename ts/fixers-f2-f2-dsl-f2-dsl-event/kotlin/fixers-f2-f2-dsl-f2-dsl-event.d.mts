declare function KtSingleton<T>(): T & (abstract new() => any);
export declare class CloudEvent<T> {
    constructor(eventType: string, eventTypeVersion: string | null | undefined, cloudEventsVersion: string, source: string, eventID: string, eventTime: string | null | undefined, schemaURL: string | null | undefined, contentType: string | null | undefined, extensions: Record<string, any> | null | undefined, data: T);
    get eventType(): string;
    get eventTypeVersion(): string | null;
    get cloudEventsVersion(): string;
    get source(): string;
    get eventID(): string;
    get eventTime(): string | null;
    get schemaURL(): string | null;
    get contentType(): string | null;
    get extensions(): Record<string, any> | null;
    get data(): T;
}
export declare namespace CloudEvent {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new <T>() => CloudEvent<T>;
    }
}
export declare class SnapEvent<T> extends CloudEvent.$metadata$.constructor<T> {
    constructor(eventType: string, eventTypeVersion: string | null | undefined, cloudEventsVersion: string, source: string, eventID: string, eventTime: string | null | undefined, schemaURL: string | null | undefined, contentType: string | null | undefined, extensions: Record<string, any> | null | undefined, data: T);
}
export declare namespace SnapEvent {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new <T>() => SnapEvent<T>;
    }
}