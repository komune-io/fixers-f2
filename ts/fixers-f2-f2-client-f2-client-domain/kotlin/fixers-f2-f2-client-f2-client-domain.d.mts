declare function KtSingleton<T>(): T & (abstract new() => any);
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