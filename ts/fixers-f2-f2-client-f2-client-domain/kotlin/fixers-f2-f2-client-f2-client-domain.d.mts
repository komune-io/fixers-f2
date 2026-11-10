declare function KtSingleton<T>(): T & (abstract new() => unknown);
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
    toString(): string;
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
    toString(): string;
}
export declare namespace AuthRealmClientSecret {
    /** @deprecated $metadata$ is used for internal purposes, please don't use it in your code, because it can be removed at any moment */
    namespace $metadata$ {
        const constructor: abstract new () => AuthRealmClientSecret;
    }
}