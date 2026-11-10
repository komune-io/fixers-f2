declare function KtSingleton<T>(): T & (abstract new() => any);
export declare class CloudEventAttributes {
    constructor(cloudEventsVersion: string, eventTypeVersion?: string | null, eventTime?: string | null, schemaURL?: string | null, contentType?: string | null, extensions?: Record<string, string> | null);
    get cloudEventsVersion(): string;
    get eventTypeVersion(): string | null;
    get eventTime(): string | null;
    get schemaURL(): string | null;
    get contentType(): string | null;
    get extensions(): Record<string, string> | null;
}
export declare namespace CloudEventAttributes {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new () => CloudEventAttributes;
    }
}
export declare class CloudEvent<T> {
    constructor(eventType: string, source: string, eventID: string, attributes: CloudEventAttributes, data: T);
    get eventType(): string;
    get source(): string;
    get eventID(): string;
    get attributes(): CloudEventAttributes;
    get data(): T;
}
export declare namespace CloudEvent {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new <T>() => CloudEvent<T>;
    }
}
export declare class SnapEvent<T> extends CloudEvent.$metadata$.constructor<T> {
    constructor(eventType: string, source: string, eventID: string, attributes: CloudEventAttributes, data: T);
}
export declare namespace SnapEvent {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new <T>() => SnapEvent<T>;
    }
}