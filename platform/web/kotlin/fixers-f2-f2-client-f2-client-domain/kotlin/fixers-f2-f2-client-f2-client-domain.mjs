import {
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
  VOID3gxj6tk5isa35 as VOID,
  getStringHashCode26igk1bx568vk as getStringHashCode,
  getBooleanHashCode1bbj3u6b3v0a7 as getBooleanHashCode,
  initMetadataForCompanion1wyw17z38v6ac as initMetadataForCompanion,
  protoOf180f3jzyo7rfj as protoOf,
  initMetadataForObject1cxne3s9w65el as initMetadataForObject,
  Unit_instance3kz1zqli6gr1r as Unit_instance,
  createThis2j2avj17cvnv2 as createThis,
} from './kotlin-kotlin-stdlib.mjs';
import {
  PluginGeneratedSerialDescriptorqdzeg5asqhfg as PluginGeneratedSerialDescriptor,
  StringSerializer_getInstance1p9zda6r6kb23 as StringSerializer_getInstance,
  IntSerializer_getInstance3jyhns9ue706g as IntSerializer_getInstance,
  get_nullable197rfua9r7fsz as get_nullable,
  typeParametersSerializers2likxjr48tr7y as typeParametersSerializers,
  GeneratedSerializer1f7t7hssdd2ws as GeneratedSerializer,
  throwMissingFieldException2cmke0v3ynf14 as throwMissingFieldException,
  SerializableWithd2dap36updxd as SerializableWith,
} from './kotlinx-serialization-kotlinx-serialization-core.mjs';
//region block: imports
var imul = Math.imul;
//endregion
//region block: pre-declaration
class AuthRealm {
  constructor(serverUrl, realmId, clientId, redirectUrl) {
    this.bb_1 = serverUrl;
    this.cb_1 = realmId;
    this.db_1 = clientId;
    this.eb_1 = redirectUrl;
  }
  fb() {
    return this.bb_1;
  }
  gb() {
    return this.cb_1;
  }
  hb() {
    return this.db_1;
  }
  ib() {
    return this.eb_1;
  }
  get serverUrl() {
    return this.fb();
  }
  get realmId() {
    return this.gb();
  }
  get clientId() {
    return this.hb();
  }
  get redirectUrl() {
    return this.ib();
  }
}
class AuthRealmPassword extends AuthRealm {
  constructor(serverUrl, realmId, redirectUrl, clientId, username, password) {
    super(serverUrl, realmId, clientId, redirectUrl);
    this.nb_1 = serverUrl;
    this.ob_1 = realmId;
    this.pb_1 = redirectUrl;
    this.qb_1 = clientId;
    this.username = username;
    this.password = password;
  }
  fb() {
    return this.nb_1;
  }
  gb() {
    return this.ob_1;
  }
  ib() {
    return this.pb_1;
  }
  hb() {
    return this.qb_1;
  }
  rb() {
    return this.username;
  }
  sb() {
    return this.password;
  }
  tb() {
    return this.nb_1;
  }
  ub() {
    return this.ob_1;
  }
  vb() {
    return this.pb_1;
  }
  wb() {
    return this.qb_1;
  }
  xb() {
    return this.username;
  }
  yb() {
    return this.password;
  }
  zb(serverUrl, realmId, redirectUrl, clientId, username, password) {
    return new AuthRealmPassword(serverUrl, realmId, redirectUrl, clientId, username, password);
  }
  copy(serverUrl, realmId, redirectUrl, clientId, username, password, $super) {
    serverUrl = serverUrl === VOID ? this.nb_1 : serverUrl;
    realmId = realmId === VOID ? this.ob_1 : realmId;
    redirectUrl = redirectUrl === VOID ? this.pb_1 : redirectUrl;
    clientId = clientId === VOID ? this.qb_1 : clientId;
    username = username === VOID ? this.username : username;
    password = password === VOID ? this.password : password;
    return $super === VOID ? this.zb(serverUrl, realmId, redirectUrl, clientId, username, password) : $super.zb.call(this, serverUrl, realmId, redirectUrl, clientId, username, password);
  }
  toString() {
    return 'AuthRealmPassword(serverUrl=' + this.nb_1 + ', realmId=' + this.ob_1 + ', redirectUrl=' + this.pb_1 + ', clientId=' + this.qb_1 + ', username=' + this.username + ', password=' + this.password + ')';
  }
  hashCode() {
    var result = getStringHashCode(this.nb_1);
    result = imul(result, 31) + getStringHashCode(this.ob_1) | 0;
    result = imul(result, 31) + getStringHashCode(this.pb_1) | 0;
    result = imul(result, 31) + getStringHashCode(this.qb_1) | 0;
    result = imul(result, 31) + getStringHashCode(this.username) | 0;
    result = imul(result, 31) + getStringHashCode(this.password) | 0;
    return result;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof AuthRealmPassword))
      return false;
    if (!(this.nb_1 === other.nb_1))
      return false;
    if (!(this.ob_1 === other.ob_1))
      return false;
    if (!(this.pb_1 === other.pb_1))
      return false;
    if (!(this.qb_1 === other.qb_1))
      return false;
    if (!(this.username === other.username))
      return false;
    if (!(this.password === other.password))
      return false;
    return true;
  }
}
class AuthRealmClientSecret extends AuthRealm {
  constructor(serverUrl, realmId, clientId, redirectUrl, clientSecret, isPublic) {
    redirectUrl = redirectUrl === VOID ? null : redirectUrl;
    isPublic = isPublic === VOID ? false : isPublic;
    super(serverUrl, realmId, clientId, redirectUrl);
    this.ec_1 = serverUrl;
    this.fc_1 = realmId;
    this.gc_1 = clientId;
    this.hc_1 = redirectUrl;
    this.clientSecret = clientSecret;
    this.isPublic = isPublic;
  }
  fb() {
    return this.ec_1;
  }
  gb() {
    return this.fc_1;
  }
  hb() {
    return this.gc_1;
  }
  ib() {
    return this.hc_1;
  }
  ic() {
    return this.clientSecret;
  }
  jc() {
    return this.isPublic;
  }
  tb() {
    return this.ec_1;
  }
  ub() {
    return this.fc_1;
  }
  vb() {
    return this.gc_1;
  }
  wb() {
    return this.hc_1;
  }
  xb() {
    return this.clientSecret;
  }
  yb() {
    return this.isPublic;
  }
  kc(serverUrl, realmId, clientId, redirectUrl, clientSecret, isPublic) {
    return new AuthRealmClientSecret(serverUrl, realmId, clientId, redirectUrl, clientSecret, isPublic);
  }
  copy(serverUrl, realmId, clientId, redirectUrl, clientSecret, isPublic, $super) {
    serverUrl = serverUrl === VOID ? this.ec_1 : serverUrl;
    realmId = realmId === VOID ? this.fc_1 : realmId;
    clientId = clientId === VOID ? this.gc_1 : clientId;
    redirectUrl = redirectUrl === VOID ? this.hc_1 : redirectUrl;
    clientSecret = clientSecret === VOID ? this.clientSecret : clientSecret;
    isPublic = isPublic === VOID ? this.isPublic : isPublic;
    return $super === VOID ? this.kc(serverUrl, realmId, clientId, redirectUrl, clientSecret, isPublic) : $super.kc.call(this, serverUrl, realmId, clientId, redirectUrl, clientSecret, isPublic);
  }
  toString() {
    return 'AuthRealmClientSecret(serverUrl=' + this.ec_1 + ', realmId=' + this.fc_1 + ', clientId=' + this.gc_1 + ', redirectUrl=' + this.hc_1 + ', clientSecret=' + this.clientSecret + ', isPublic=' + this.isPublic + ')';
  }
  hashCode() {
    var result = getStringHashCode(this.ec_1);
    result = imul(result, 31) + getStringHashCode(this.fc_1) | 0;
    result = imul(result, 31) + getStringHashCode(this.gc_1) | 0;
    result = imul(result, 31) + (this.hc_1 == null ? 0 : getStringHashCode(this.hc_1)) | 0;
    result = imul(result, 31) + getStringHashCode(this.clientSecret) | 0;
    result = imul(result, 31) + getBooleanHashCode(this.isPublic) | 0;
    return result;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof AuthRealmClientSecret))
      return false;
    if (!(this.ec_1 === other.ec_1))
      return false;
    if (!(this.fc_1 === other.fc_1))
      return false;
    if (!(this.gc_1 === other.gc_1))
      return false;
    if (!(this.hc_1 == other.hc_1))
      return false;
    if (!(this.clientSecret === other.clientSecret))
      return false;
    if (!(this.isPublic === other.isPublic))
      return false;
    return true;
  }
}
class Companion {}
class $serializer {
  constructor() {
    $serializer_instance = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.client.domain.TokenInfo', this, 7);
    tmp0_serialDesc.wa('access_token', false);
    tmp0_serialDesc.wa('expires_in', false);
    tmp0_serialDesc.wa('refresh_expires_in', true);
    tmp0_serialDesc.wa('token_type', false);
    tmp0_serialDesc.wa('scope', false);
    tmp0_serialDesc.wa('id_token', true);
    tmp0_serialDesc.wa('refresh_token', true);
    this.lc_1 = tmp0_serialDesc;
  }
  c9() {
    return this.lc_1;
  }
  ta() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [StringSerializer_getInstance(), IntSerializer_getInstance(), get_nullable(IntSerializer_getInstance()), StringSerializer_getInstance(), get_nullable(StringSerializer_getInstance()), get_nullable(StringSerializer_getInstance()), get_nullable(StringSerializer_getInstance())];
  }
}
class TokenInfo {
  constructor(accessToken, expiresIn, refreshExpiresIn, tokenType, scope, idToken, refreshToken) {
    refreshExpiresIn = refreshExpiresIn === VOID ? null : refreshExpiresIn;
    idToken = idToken === VOID ? null : idToken;
    refreshToken = refreshToken === VOID ? null : refreshToken;
    this.accessToken = accessToken;
    this.expiresIn = expiresIn;
    this.refreshExpiresIn = refreshExpiresIn;
    this.tokenType = tokenType;
    this.scope = scope;
    this.idToken = idToken;
    this.refreshToken = refreshToken;
  }
  mc() {
    return this.accessToken;
  }
  nc() {
    return this.expiresIn;
  }
  oc() {
    return this.refreshExpiresIn;
  }
  pc() {
    return this.tokenType;
  }
  qc() {
    return this.scope;
  }
  rc() {
    return this.idToken;
  }
  sc() {
    return this.refreshToken;
  }
  tb() {
    return this.accessToken;
  }
  ub() {
    return this.expiresIn;
  }
  vb() {
    return this.refreshExpiresIn;
  }
  wb() {
    return this.tokenType;
  }
  xb() {
    return this.scope;
  }
  yb() {
    return this.idToken;
  }
  tc() {
    return this.refreshToken;
  }
  uc(accessToken, expiresIn, refreshExpiresIn, tokenType, scope, idToken, refreshToken) {
    return new TokenInfo(accessToken, expiresIn, refreshExpiresIn, tokenType, scope, idToken, refreshToken);
  }
  copy(accessToken, expiresIn, refreshExpiresIn, tokenType, scope, idToken, refreshToken, $super) {
    accessToken = accessToken === VOID ? this.accessToken : accessToken;
    expiresIn = expiresIn === VOID ? this.expiresIn : expiresIn;
    refreshExpiresIn = refreshExpiresIn === VOID ? this.refreshExpiresIn : refreshExpiresIn;
    tokenType = tokenType === VOID ? this.tokenType : tokenType;
    scope = scope === VOID ? this.scope : scope;
    idToken = idToken === VOID ? this.idToken : idToken;
    refreshToken = refreshToken === VOID ? this.refreshToken : refreshToken;
    return $super === VOID ? this.uc(accessToken, expiresIn, refreshExpiresIn, tokenType, scope, idToken, refreshToken) : $super.uc.call(this, accessToken, expiresIn, refreshExpiresIn, tokenType, scope, idToken, refreshToken);
  }
  toString() {
    return 'TokenInfo(accessToken=' + this.accessToken + ', expiresIn=' + this.expiresIn + ', refreshExpiresIn=' + this.refreshExpiresIn + ', tokenType=' + this.tokenType + ', scope=' + this.scope + ', idToken=' + this.idToken + ', refreshToken=' + this.refreshToken + ')';
  }
  hashCode() {
    var result = getStringHashCode(this.accessToken);
    result = imul(result, 31) + this.expiresIn | 0;
    result = imul(result, 31) + (this.refreshExpiresIn == null ? 0 : this.refreshExpiresIn) | 0;
    result = imul(result, 31) + getStringHashCode(this.tokenType) | 0;
    result = imul(result, 31) + (this.scope == null ? 0 : getStringHashCode(this.scope)) | 0;
    result = imul(result, 31) + (this.idToken == null ? 0 : getStringHashCode(this.idToken)) | 0;
    result = imul(result, 31) + (this.refreshToken == null ? 0 : getStringHashCode(this.refreshToken)) | 0;
    return result;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof TokenInfo))
      return false;
    if (!(this.accessToken === other.accessToken))
      return false;
    if (!(this.expiresIn === other.expiresIn))
      return false;
    if (!(this.refreshExpiresIn == other.refreshExpiresIn))
      return false;
    if (!(this.tokenType === other.tokenType))
      return false;
    if (!(this.scope == other.scope))
      return false;
    if (!(this.idToken == other.idToken))
      return false;
    if (!(this.refreshToken == other.refreshToken))
      return false;
    return true;
  }
  static vc(seen0, accessToken, expiresIn, refreshExpiresIn, tokenType, scope, idToken, refreshToken, serializationConstructorMarker) {
    if (!(27 === (27 & seen0))) {
      throwMissingFieldException(seen0, 27, $serializer_getInstance().lc_1);
    }
    var $this = createThis(this);
    $this.accessToken = accessToken;
    $this.expiresIn = expiresIn;
    if (0 === (seen0 & 4))
      $this.refreshExpiresIn = null;
    else
      $this.refreshExpiresIn = refreshExpiresIn;
    $this.tokenType = tokenType;
    $this.scope = scope;
    if (0 === (seen0 & 32))
      $this.idToken = null;
    else
      $this.idToken = idToken;
    if (0 === (seen0 & 64))
      $this.refreshToken = null;
    else
      $this.refreshToken = refreshToken;
    return $this;
  }
}
//endregion
var Companion_instance;
function Companion_getInstance() {
  return Companion_instance;
}
var $serializer_instance;
function $serializer_getInstance() {
  if ($serializer_instance === VOID)
    new $serializer();
  return $serializer_instance;
}
//region block: post-declaration
initMetadataForClass(AuthRealm, 'AuthRealm');
initMetadataForClass(AuthRealmPassword, 'AuthRealmPassword');
initMetadataForClass(AuthRealmClientSecret, 'AuthRealmClientSecret');
initMetadataForCompanion(Companion);
protoOf($serializer).ua = typeParametersSerializers;
initMetadataForObject($serializer, '$serializer', VOID, VOID, [GeneratedSerializer]);
initMetadataForClass(TokenInfo, 'TokenInfo', VOID, VOID, VOID, VOID, VOID, {0: $serializer_getInstance});
//endregion
//region block: init
Companion_instance = new Companion();
//endregion
//region block: exports
export {
  AuthRealm as AuthRealm,
  AuthRealmPassword as AuthRealmPassword,
  AuthRealmClientSecret as AuthRealmClientSecret,
  TokenInfo as TokenInfo,
};
//endregion

//# sourceMappingURL=fixers-f2-f2-client-f2-client-domain.mjs.map
