import {
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
  VOID3gxj6tk5isa35 as VOID,
  getStringHashCode26igk1bx568vk as getStringHashCode,
  getBooleanHashCode1bbj3u6b3v0a7 as getBooleanHashCode,
  initMetadataForCompanion1wyw17z38v6ac as initMetadataForCompanion,
  Unit_instance3kz1zqli6gr1r as Unit_instance,
  THROW_CCE2g6jy02ryeudk as THROW_CCE,
  protoOf180f3jzyo7rfj as protoOf,
  initMetadataForObject1cxne3s9w65el as initMetadataForObject,
  createThis2j2avj17cvnv2 as createThis,
} from './kotlin-kotlin-stdlib.mjs';
import {
  PluginGeneratedSerialDescriptorqdzeg5asqhfg as PluginGeneratedSerialDescriptor,
  IntSerializer_getInstance3jyhns9ue706g as IntSerializer_getInstance,
  StringSerializer_getInstance1p9zda6r6kb23 as StringSerializer_getInstance,
  UnknownFieldExceptiona60e3a6v1xqo as UnknownFieldException,
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
    this.s44_1 = serverUrl;
    this.t44_1 = realmId;
    this.u44_1 = clientId;
    this.v44_1 = redirectUrl;
  }
  w44() {
    return this.s44_1;
  }
  x44() {
    return this.t44_1;
  }
  y44() {
    return this.u44_1;
  }
  z44() {
    return this.v44_1;
  }
  get serverUrl() {
    return this.w44();
  }
  get realmId() {
    return this.x44();
  }
  get clientId() {
    return this.y44();
  }
  get redirectUrl() {
    return this.z44();
  }
}
class AuthRealmPassword extends AuthRealm {
  constructor(serverUrl, realmId, redirectUrl, clientId, username, password) {
    super(serverUrl, realmId, clientId, redirectUrl);
    this.e45_1 = serverUrl;
    this.f45_1 = realmId;
    this.g45_1 = redirectUrl;
    this.h45_1 = clientId;
    this.username = username;
    this.password = password;
  }
  w44() {
    return this.e45_1;
  }
  x44() {
    return this.f45_1;
  }
  z44() {
    return this.g45_1;
  }
  y44() {
    return this.h45_1;
  }
  i45() {
    return this.username;
  }
  f2u() {
    return this.password;
  }
  cj() {
    return this.e45_1;
  }
  dj() {
    return this.f45_1;
  }
  q3j() {
    return this.g45_1;
  }
  j45() {
    return this.h45_1;
  }
  k45() {
    return this.username;
  }
  l45() {
    return this.password;
  }
  m45(serverUrl, realmId, redirectUrl, clientId, username, password) {
    return new AuthRealmPassword(serverUrl, realmId, redirectUrl, clientId, username, password);
  }
  copy(serverUrl, realmId, redirectUrl, clientId, username, password, $super) {
    serverUrl = serverUrl === VOID ? this.e45_1 : serverUrl;
    realmId = realmId === VOID ? this.f45_1 : realmId;
    redirectUrl = redirectUrl === VOID ? this.g45_1 : redirectUrl;
    clientId = clientId === VOID ? this.h45_1 : clientId;
    username = username === VOID ? this.username : username;
    password = password === VOID ? this.password : password;
    return $super === VOID ? this.m45(serverUrl, realmId, redirectUrl, clientId, username, password) : $super.m45.call(this, serverUrl, realmId, redirectUrl, clientId, username, password);
  }
  toString() {
    return 'AuthRealmPassword(serverUrl=' + this.e45_1 + ', realmId=' + this.f45_1 + ', redirectUrl=' + this.g45_1 + ', clientId=' + this.h45_1 + ', username=' + this.username + ', password=' + this.password + ')';
  }
  hashCode() {
    var result = getStringHashCode(this.e45_1);
    result = imul(result, 31) + getStringHashCode(this.f45_1) | 0;
    result = imul(result, 31) + getStringHashCode(this.g45_1) | 0;
    result = imul(result, 31) + getStringHashCode(this.h45_1) | 0;
    result = imul(result, 31) + getStringHashCode(this.username) | 0;
    result = imul(result, 31) + getStringHashCode(this.password) | 0;
    return result;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof AuthRealmPassword))
      return false;
    if (!(this.e45_1 === other.e45_1))
      return false;
    if (!(this.f45_1 === other.f45_1))
      return false;
    if (!(this.g45_1 === other.g45_1))
      return false;
    if (!(this.h45_1 === other.h45_1))
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
    this.r45_1 = serverUrl;
    this.s45_1 = realmId;
    this.t45_1 = clientId;
    this.u45_1 = redirectUrl;
    this.clientSecret = clientSecret;
    this.isPublic = isPublic;
  }
  w44() {
    return this.r45_1;
  }
  x44() {
    return this.s45_1;
  }
  y44() {
    return this.t45_1;
  }
  z44() {
    return this.u45_1;
  }
  v45() {
    return this.clientSecret;
  }
  w45() {
    return this.isPublic;
  }
  cj() {
    return this.r45_1;
  }
  dj() {
    return this.s45_1;
  }
  q3j() {
    return this.t45_1;
  }
  j45() {
    return this.u45_1;
  }
  k45() {
    return this.clientSecret;
  }
  l45() {
    return this.isPublic;
  }
  x45(serverUrl, realmId, clientId, redirectUrl, clientSecret, isPublic) {
    return new AuthRealmClientSecret(serverUrl, realmId, clientId, redirectUrl, clientSecret, isPublic);
  }
  copy(serverUrl, realmId, clientId, redirectUrl, clientSecret, isPublic, $super) {
    serverUrl = serverUrl === VOID ? this.r45_1 : serverUrl;
    realmId = realmId === VOID ? this.s45_1 : realmId;
    clientId = clientId === VOID ? this.t45_1 : clientId;
    redirectUrl = redirectUrl === VOID ? this.u45_1 : redirectUrl;
    clientSecret = clientSecret === VOID ? this.clientSecret : clientSecret;
    isPublic = isPublic === VOID ? this.isPublic : isPublic;
    return $super === VOID ? this.x45(serverUrl, realmId, clientId, redirectUrl, clientSecret, isPublic) : $super.x45.call(this, serverUrl, realmId, clientId, redirectUrl, clientSecret, isPublic);
  }
  toString() {
    return 'AuthRealmClientSecret(serverUrl=' + this.r45_1 + ', realmId=' + this.s45_1 + ', clientId=' + this.t45_1 + ', redirectUrl=' + this.u45_1 + ', clientSecret=' + this.clientSecret + ', isPublic=' + this.isPublic + ')';
  }
  hashCode() {
    var result = getStringHashCode(this.r45_1);
    result = imul(result, 31) + getStringHashCode(this.s45_1) | 0;
    result = imul(result, 31) + getStringHashCode(this.t45_1) | 0;
    result = imul(result, 31) + (this.u45_1 == null ? 0 : getStringHashCode(this.u45_1)) | 0;
    result = imul(result, 31) + getStringHashCode(this.clientSecret) | 0;
    result = imul(result, 31) + getBooleanHashCode(this.isPublic) | 0;
    return result;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof AuthRealmClientSecret))
      return false;
    if (!(this.r45_1 === other.r45_1))
      return false;
    if (!(this.s45_1 === other.s45_1))
      return false;
    if (!(this.t45_1 === other.t45_1))
      return false;
    if (!(this.u45_1 == other.u45_1))
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
    tmp0_serialDesc.l1p('access_token', false);
    tmp0_serialDesc.l1p('expires_in', false);
    tmp0_serialDesc.l1p('refresh_expires_in', true);
    tmp0_serialDesc.l1p('token_type', false);
    tmp0_serialDesc.l1p('scope', false);
    tmp0_serialDesc.l1p('id_token', true);
    tmp0_serialDesc.l1p('refresh_token', true);
    this.y45_1 = tmp0_serialDesc;
  }
  z45(encoder, value) {
    var tmp0_desc = this.y45_1;
    var tmp1_output = encoder.a1i(tmp0_desc);
    tmp1_output.q1j(tmp0_desc, 0, value.accessToken);
    tmp1_output.l1j(tmp0_desc, 1, value.expiresIn);
    if (tmp1_output.y1j(tmp0_desc, 2) ? true : !(value.refreshExpiresIn == null)) {
      tmp1_output.u1j(tmp0_desc, 2, IntSerializer_getInstance(), value.refreshExpiresIn);
    }
    tmp1_output.q1j(tmp0_desc, 3, value.tokenType);
    tmp1_output.u1j(tmp0_desc, 4, StringSerializer_getInstance(), value.scope);
    if (tmp1_output.y1j(tmp0_desc, 5) ? true : !(value.idToken == null)) {
      tmp1_output.u1j(tmp0_desc, 5, StringSerializer_getInstance(), value.idToken);
    }
    if (tmp1_output.y1j(tmp0_desc, 6) ? true : !(value.refreshToken == null)) {
      tmp1_output.u1j(tmp0_desc, 6, StringSerializer_getInstance(), value.refreshToken);
    }
    tmp1_output.b1i(tmp0_desc);
  }
  m1d(encoder, value) {
    return this.z45(encoder, value instanceof TokenInfo ? value : THROW_CCE());
  }
  n1d(decoder) {
    var tmp0_desc = this.y45_1;
    var tmp1_flag = true;
    var tmp2_index = 0;
    var tmp3_bitMask0 = 0;
    var tmp4_local0 = null;
    var tmp5_local1 = 0;
    var tmp6_local2 = null;
    var tmp7_local3 = null;
    var tmp8_local4 = null;
    var tmp9_local5 = null;
    var tmp10_local6 = null;
    var tmp11_input = decoder.a1i(tmp0_desc);
    if (tmp11_input.q1i()) {
      tmp4_local0 = tmp11_input.k1i(tmp0_desc, 0);
      tmp3_bitMask0 = tmp3_bitMask0 | 1;
      tmp5_local1 = tmp11_input.f1i(tmp0_desc, 1);
      tmp3_bitMask0 = tmp3_bitMask0 | 2;
      tmp6_local2 = tmp11_input.o1i(tmp0_desc, 2, IntSerializer_getInstance(), tmp6_local2);
      tmp3_bitMask0 = tmp3_bitMask0 | 4;
      tmp7_local3 = tmp11_input.k1i(tmp0_desc, 3);
      tmp3_bitMask0 = tmp3_bitMask0 | 8;
      tmp8_local4 = tmp11_input.o1i(tmp0_desc, 4, StringSerializer_getInstance(), tmp8_local4);
      tmp3_bitMask0 = tmp3_bitMask0 | 16;
      tmp9_local5 = tmp11_input.o1i(tmp0_desc, 5, StringSerializer_getInstance(), tmp9_local5);
      tmp3_bitMask0 = tmp3_bitMask0 | 32;
      tmp10_local6 = tmp11_input.o1i(tmp0_desc, 6, StringSerializer_getInstance(), tmp10_local6);
      tmp3_bitMask0 = tmp3_bitMask0 | 64;
    } else
      while (tmp1_flag) {
        tmp2_index = tmp11_input.r1i(tmp0_desc);
        switch (tmp2_index) {
          case -1:
            tmp1_flag = false;
            break;
          case 0:
            tmp4_local0 = tmp11_input.k1i(tmp0_desc, 0);
            tmp3_bitMask0 = tmp3_bitMask0 | 1;
            break;
          case 1:
            tmp5_local1 = tmp11_input.f1i(tmp0_desc, 1);
            tmp3_bitMask0 = tmp3_bitMask0 | 2;
            break;
          case 2:
            tmp6_local2 = tmp11_input.o1i(tmp0_desc, 2, IntSerializer_getInstance(), tmp6_local2);
            tmp3_bitMask0 = tmp3_bitMask0 | 4;
            break;
          case 3:
            tmp7_local3 = tmp11_input.k1i(tmp0_desc, 3);
            tmp3_bitMask0 = tmp3_bitMask0 | 8;
            break;
          case 4:
            tmp8_local4 = tmp11_input.o1i(tmp0_desc, 4, StringSerializer_getInstance(), tmp8_local4);
            tmp3_bitMask0 = tmp3_bitMask0 | 16;
            break;
          case 5:
            tmp9_local5 = tmp11_input.o1i(tmp0_desc, 5, StringSerializer_getInstance(), tmp9_local5);
            tmp3_bitMask0 = tmp3_bitMask0 | 32;
            break;
          case 6:
            tmp10_local6 = tmp11_input.o1i(tmp0_desc, 6, StringSerializer_getInstance(), tmp10_local6);
            tmp3_bitMask0 = tmp3_bitMask0 | 64;
            break;
          default:
            throw UnknownFieldException.k1f(tmp2_index);
        }
      }
    tmp11_input.b1i(tmp0_desc);
    return TokenInfo.a46(tmp3_bitMask0, tmp4_local0, tmp5_local1, tmp6_local2, tmp7_local3, tmp8_local4, tmp9_local5, tmp10_local6, null);
  }
  l1d() {
    return this.y45_1;
  }
  a1q() {
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
  b46() {
    return this.accessToken;
  }
  c46() {
    return this.expiresIn;
  }
  d46() {
    return this.refreshExpiresIn;
  }
  e46() {
    return this.tokenType;
  }
  f46() {
    return this.scope;
  }
  g46() {
    return this.idToken;
  }
  h46() {
    return this.refreshToken;
  }
  cj() {
    return this.accessToken;
  }
  dj() {
    return this.expiresIn;
  }
  q3j() {
    return this.refreshExpiresIn;
  }
  j45() {
    return this.tokenType;
  }
  k45() {
    return this.scope;
  }
  l45() {
    return this.idToken;
  }
  i46() {
    return this.refreshToken;
  }
  j46(accessToken, expiresIn, refreshExpiresIn, tokenType, scope, idToken, refreshToken) {
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
    return $super === VOID ? this.j46(accessToken, expiresIn, refreshExpiresIn, tokenType, scope, idToken, refreshToken) : $super.j46.call(this, accessToken, expiresIn, refreshExpiresIn, tokenType, scope, idToken, refreshToken);
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
  static a46(seen0, accessToken, expiresIn, refreshExpiresIn, tokenType, scope, idToken, refreshToken, serializationConstructorMarker) {
    if (!(27 === (27 & seen0))) {
      throwMissingFieldException(seen0, 27, $serializer_getInstance().y45_1);
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
protoOf($serializer).b1q = typeParametersSerializers;
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
