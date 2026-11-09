import {
  initMetadataForInterface1egvbzx539z91 as initMetadataForInterface,
  VOID3gxj6tk5isa35 as VOID,
  initMetadataForCompanion1wyw17z38v6ac as initMetadataForCompanion,
  createThis2j2avj17cvnv2 as createThis,
  Unit_instance3kz1zqli6gr1r as Unit_instance,
  THROW_CCE2g6jy02ryeudk as THROW_CCE,
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
  Companion_getInstance2o9uk4k3kfl5 as Companion_getInstance,
  System_instance34rnn1q53nv02 as System_instance,
  protoOf180f3jzyo7rfj as protoOf,
  initMetadataForObject1cxne3s9w65el as initMetadataForObject,
  RuntimeException1r3t0zl97011n as RuntimeException,
  captureStack1fzi4aczwc4hg as captureStack,
  defineProp3ur6h3slcvq4x as defineProp,
  listOf1jh22dvmctj1r as listOf,
  ArrayList3it5z8td81qkl as ArrayList,
  collectionSizeOrDefault36dulx8yinfqm as collectionSizeOrDefault,
  plus20p0vtfmu0596 as plus,
  toString1pkumu07cwy4m as toString,
  hashCodeq5arwsb9dgti as hashCode,
  getBooleanHashCode1bbj3u6b3v0a7 as getBooleanHashCode,
  equals2au1ep9vhcato as equals,
  getKClass3t8tygqu4lcxf as getKClass,
  LazyThreadSafetyMode_PUBLICATION_getInstance23f213579at67 as LazyThreadSafetyMode_PUBLICATION_getInstance,
  lazy1261dae0bgscp as lazy,
} from './kotlin-kotlin-stdlib.mjs';
import {
  PluginGeneratedSerialDescriptorqdzeg5asqhfg as PluginGeneratedSerialDescriptor,
  SerializerFactory1qv9hivitncuv as SerializerFactory,
  StringSerializer_getInstance1p9zda6r6kb23 as StringSerializer_getInstance,
  UnknownFieldExceptiona60e3a6v1xqo as UnknownFieldException,
  get_nullable197rfua9r7fsz as get_nullable,
  GeneratedSerializer1f7t7hssdd2ws as GeneratedSerializer,
  throwMissingFieldException2cmke0v3ynf14 as throwMissingFieldException,
  SerializableWithd2dap36updxd as SerializableWith,
  IntSerializer_getInstance3jyhns9ue706g as IntSerializer_getInstance,
  typeParametersSerializers2likxjr48tr7y as typeParametersSerializers,
  ArrayListSerializer7k5wnrulb3y6 as ArrayListSerializer,
  PolymorphicSerializer3p3fzpdobi8hh as PolymorphicSerializer,
  SealedClassSerializeriwipiibk55zc as SealedClassSerializer,
} from './kotlinx-serialization-kotlinx-serialization-core.mjs';
//region block: imports
var imul = Math.imul;
//endregion
//region block: pre-declaration
class Message {}
class Command {}
class Event {}
class Problem {}
class Query {}
class WithEnvelopeId {}
class WithEnvelopeData {}
class EnvelopeDTO {}
class Companion {
  constructor() {
    Companion_instance = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.envelope.Envelope', null, 7);
    tmp0_serialDesc.f15('id', false);
    tmp0_serialDesc.f15('data', false);
    tmp0_serialDesc.f15('type', false);
    tmp0_serialDesc.f15('datacontenttype', true);
    tmp0_serialDesc.f15('specversion', true);
    tmp0_serialDesc.f15('source', true);
    tmp0_serialDesc.f15('time', true);
    this.s41_1 = tmp0_serialDesc;
  }
  t41(typeSerial0) {
    return $serializer.w41(typeSerial0);
  }
  g16(typeParamsSerializers) {
    return this.t41(typeParamsSerializers[0]);
  }
}
class $serializer {
  constructor() {
    return new.target.x41();
  }
  static x41() {
    var $this = createThis(this);
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.envelope.Envelope', $this, 7);
    tmp0_serialDesc.f15('id', false);
    tmp0_serialDesc.f15('data', false);
    tmp0_serialDesc.f15('type', false);
    tmp0_serialDesc.f15('datacontenttype', true);
    tmp0_serialDesc.f15('specversion', true);
    tmp0_serialDesc.f15('source', true);
    tmp0_serialDesc.f15('time', true);
    $this.u41_1 = tmp0_serialDesc;
    return $this;
  }
  y41(encoder, value) {
    var tmp0_desc = this.u41_1;
    var tmp1_output = encoder.ux(tmp0_desc);
    tmp1_output.kz(tmp0_desc, 0, value.z41_1);
    tmp1_output.mz(tmp0_desc, 1, this.v41_1, value.a42_1);
    tmp1_output.kz(tmp0_desc, 2, value.b42_1);
    if (tmp1_output.sz(tmp0_desc, 3) ? true : !(value.c42_1 == null)) {
      tmp1_output.oz(tmp0_desc, 3, StringSerializer_getInstance(), value.c42_1);
    }
    if (tmp1_output.sz(tmp0_desc, 4) ? true : !(value.d42_1 == null)) {
      tmp1_output.oz(tmp0_desc, 4, StringSerializer_getInstance(), value.d42_1);
    }
    if (tmp1_output.sz(tmp0_desc, 5) ? true : !(value.e42_1 == null)) {
      tmp1_output.oz(tmp0_desc, 5, StringSerializer_getInstance(), value.e42_1);
    }
    if (tmp1_output.sz(tmp0_desc, 6) ? true : !(value.f42_1 == null)) {
      tmp1_output.oz(tmp0_desc, 6, StringSerializer_getInstance(), value.f42_1);
    }
    tmp1_output.vx(tmp0_desc);
  }
  gt(encoder, value) {
    return this.y41(encoder, value instanceof Envelope ? value : THROW_CCE());
  }
  ht(decoder) {
    var tmp0_desc = this.u41_1;
    var tmp1_flag = true;
    var tmp2_index = 0;
    var tmp3_bitMask0 = 0;
    var tmp4_local0 = null;
    var tmp5_local1 = null;
    var tmp6_local2 = null;
    var tmp7_local3 = null;
    var tmp8_local4 = null;
    var tmp9_local5 = null;
    var tmp10_local6 = null;
    var tmp11_input = decoder.ux(tmp0_desc);
    if (tmp11_input.ky()) {
      tmp4_local0 = tmp11_input.ey(tmp0_desc, 0);
      tmp3_bitMask0 = tmp3_bitMask0 | 1;
      tmp5_local1 = tmp11_input.gy(tmp0_desc, 1, this.v41_1, tmp5_local1);
      tmp3_bitMask0 = tmp3_bitMask0 | 2;
      tmp6_local2 = tmp11_input.ey(tmp0_desc, 2);
      tmp3_bitMask0 = tmp3_bitMask0 | 4;
      tmp7_local3 = tmp11_input.iy(tmp0_desc, 3, StringSerializer_getInstance(), tmp7_local3);
      tmp3_bitMask0 = tmp3_bitMask0 | 8;
      tmp8_local4 = tmp11_input.iy(tmp0_desc, 4, StringSerializer_getInstance(), tmp8_local4);
      tmp3_bitMask0 = tmp3_bitMask0 | 16;
      tmp9_local5 = tmp11_input.iy(tmp0_desc, 5, StringSerializer_getInstance(), tmp9_local5);
      tmp3_bitMask0 = tmp3_bitMask0 | 32;
      tmp10_local6 = tmp11_input.iy(tmp0_desc, 6, StringSerializer_getInstance(), tmp10_local6);
      tmp3_bitMask0 = tmp3_bitMask0 | 64;
    } else
      while (tmp1_flag) {
        tmp2_index = tmp11_input.ly(tmp0_desc);
        switch (tmp2_index) {
          case -1:
            tmp1_flag = false;
            break;
          case 0:
            tmp4_local0 = tmp11_input.ey(tmp0_desc, 0);
            tmp3_bitMask0 = tmp3_bitMask0 | 1;
            break;
          case 1:
            tmp5_local1 = tmp11_input.gy(tmp0_desc, 1, this.v41_1, tmp5_local1);
            tmp3_bitMask0 = tmp3_bitMask0 | 2;
            break;
          case 2:
            tmp6_local2 = tmp11_input.ey(tmp0_desc, 2);
            tmp3_bitMask0 = tmp3_bitMask0 | 4;
            break;
          case 3:
            tmp7_local3 = tmp11_input.iy(tmp0_desc, 3, StringSerializer_getInstance(), tmp7_local3);
            tmp3_bitMask0 = tmp3_bitMask0 | 8;
            break;
          case 4:
            tmp8_local4 = tmp11_input.iy(tmp0_desc, 4, StringSerializer_getInstance(), tmp8_local4);
            tmp3_bitMask0 = tmp3_bitMask0 | 16;
            break;
          case 5:
            tmp9_local5 = tmp11_input.iy(tmp0_desc, 5, StringSerializer_getInstance(), tmp9_local5);
            tmp3_bitMask0 = tmp3_bitMask0 | 32;
            break;
          case 6:
            tmp10_local6 = tmp11_input.iy(tmp0_desc, 6, StringSerializer_getInstance(), tmp10_local6);
            tmp3_bitMask0 = tmp3_bitMask0 | 64;
            break;
          default:
            throw UnknownFieldException.cv(tmp2_index);
        }
      }
    tmp11_input.vx(tmp0_desc);
    return Envelope.g42(tmp3_bitMask0, tmp4_local0, tmp5_local1, tmp6_local2, tmp7_local3, tmp8_local4, tmp9_local5, tmp10_local6, null);
  }
  ft() {
    return this.u41_1;
  }
  u15() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [StringSerializer_getInstance(), this.v41_1, StringSerializer_getInstance(), get_nullable(StringSerializer_getInstance()), get_nullable(StringSerializer_getInstance()), get_nullable(StringSerializer_getInstance()), get_nullable(StringSerializer_getInstance())];
  }
  v15() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [this.v41_1];
  }
  static w41(typeSerial0) {
    var $this = this.x41();
    $this.v41_1 = typeSerial0;
    return $this;
  }
}
class Envelope {
  constructor(id, data, type, datacontenttype, specversion, source, time) {
    Companion_getInstance_0();
    datacontenttype = datacontenttype === VOID ? null : datacontenttype;
    specversion = specversion === VOID ? null : specversion;
    source = source === VOID ? null : source;
    time = time === VOID ? null : time;
    this.z41_1 = id;
    this.a42_1 = data;
    this.b42_1 = type;
    this.c42_1 = datacontenttype;
    this.d42_1 = specversion;
    this.e42_1 = source;
    this.f42_1 = time;
  }
  m41() {
    return this.z41_1;
  }
  n41() {
    return this.a42_1;
  }
  q3z() {
    return this.b42_1;
  }
  o41() {
    return this.c42_1;
  }
  p41() {
    return this.d42_1;
  }
  q41() {
    return this.e42_1;
  }
  r41() {
    return this.f42_1;
  }
  static g42(seen0, id, data, type, datacontenttype, specversion, source, time, serializationConstructorMarker) {
    Companion_getInstance_0();
    if (!(7 === (7 & seen0))) {
      throwMissingFieldException(seen0, 7, Companion_getInstance_0().s41_1);
    }
    var $this = createThis(this);
    $this.z41_1 = id;
    $this.a42_1 = data;
    $this.b42_1 = type;
    if (0 === (seen0 & 8))
      $this.c42_1 = null;
    else
      $this.c42_1 = datacontenttype;
    if (0 === (seen0 & 16))
      $this.d42_1 = null;
    else
      $this.d42_1 = specversion;
    if (0 === (seen0 & 32))
      $this.e42_1 = null;
    else
      $this.e42_1 = source;
    if (0 === (seen0 & 64))
      $this.f42_1 = null;
    else
      $this.f42_1 = time;
    return $this;
  }
  get id() {
    return this.m41();
  }
  get data() {
    return this.n41();
  }
  get type() {
    return this.q3z();
  }
  get datacontenttype() {
    return this.o41();
  }
  get specversion() {
    return this.p41();
  }
  get source() {
    return this.q41();
  }
  get time() {
    return this.r41();
  }
}
class F2ErrorDTO {}
class Companion_0 {
  f1j() {
    return $serializer_getInstance();
  }
}
class $serializer_0 {
  constructor() {
    $serializer_instance = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.error.F2Error', this, 5);
    tmp0_serialDesc.f15('message', false);
    tmp0_serialDesc.f15('id', true);
    tmp0_serialDesc.f15('timestamp', true);
    tmp0_serialDesc.f15('code', true);
    tmp0_serialDesc.f15('requestId', true);
    this.k42_1 = tmp0_serialDesc;
  }
  l42(encoder, value) {
    var tmp0_desc = this.k42_1;
    var tmp1_output = encoder.ux(tmp0_desc);
    tmp1_output.kz(tmp0_desc, 0, value.message);
    if (tmp1_output.sz(tmp0_desc, 1) ? true : !(value.id === Companion_getInstance().lr().toString())) {
      tmp1_output.oz(tmp0_desc, 1, StringSerializer_getInstance(), value.id);
    }
    if (tmp1_output.sz(tmp0_desc, 2) ? true : !(value.timestamp === System_instance.gp().toString())) {
      tmp1_output.kz(tmp0_desc, 2, value.timestamp);
    }
    if (tmp1_output.sz(tmp0_desc, 3) ? true : !(value.code === 500)) {
      tmp1_output.fz(tmp0_desc, 3, value.code);
    }
    if (tmp1_output.sz(tmp0_desc, 4) ? true : !(value.requestId == null)) {
      tmp1_output.oz(tmp0_desc, 4, StringSerializer_getInstance(), value.requestId);
    }
    tmp1_output.vx(tmp0_desc);
  }
  gt(encoder, value) {
    return this.l42(encoder, value instanceof F2Error ? value : THROW_CCE());
  }
  ht(decoder) {
    var tmp0_desc = this.k42_1;
    var tmp1_flag = true;
    var tmp2_index = 0;
    var tmp3_bitMask0 = 0;
    var tmp4_local0 = null;
    var tmp5_local1 = null;
    var tmp6_local2 = null;
    var tmp7_local3 = 0;
    var tmp8_local4 = null;
    var tmp9_input = decoder.ux(tmp0_desc);
    if (tmp9_input.ky()) {
      tmp4_local0 = tmp9_input.ey(tmp0_desc, 0);
      tmp3_bitMask0 = tmp3_bitMask0 | 1;
      tmp5_local1 = tmp9_input.iy(tmp0_desc, 1, StringSerializer_getInstance(), tmp5_local1);
      tmp3_bitMask0 = tmp3_bitMask0 | 2;
      tmp6_local2 = tmp9_input.ey(tmp0_desc, 2);
      tmp3_bitMask0 = tmp3_bitMask0 | 4;
      tmp7_local3 = tmp9_input.zx(tmp0_desc, 3);
      tmp3_bitMask0 = tmp3_bitMask0 | 8;
      tmp8_local4 = tmp9_input.iy(tmp0_desc, 4, StringSerializer_getInstance(), tmp8_local4);
      tmp3_bitMask0 = tmp3_bitMask0 | 16;
    } else
      while (tmp1_flag) {
        tmp2_index = tmp9_input.ly(tmp0_desc);
        switch (tmp2_index) {
          case -1:
            tmp1_flag = false;
            break;
          case 0:
            tmp4_local0 = tmp9_input.ey(tmp0_desc, 0);
            tmp3_bitMask0 = tmp3_bitMask0 | 1;
            break;
          case 1:
            tmp5_local1 = tmp9_input.iy(tmp0_desc, 1, StringSerializer_getInstance(), tmp5_local1);
            tmp3_bitMask0 = tmp3_bitMask0 | 2;
            break;
          case 2:
            tmp6_local2 = tmp9_input.ey(tmp0_desc, 2);
            tmp3_bitMask0 = tmp3_bitMask0 | 4;
            break;
          case 3:
            tmp7_local3 = tmp9_input.zx(tmp0_desc, 3);
            tmp3_bitMask0 = tmp3_bitMask0 | 8;
            break;
          case 4:
            tmp8_local4 = tmp9_input.iy(tmp0_desc, 4, StringSerializer_getInstance(), tmp8_local4);
            tmp3_bitMask0 = tmp3_bitMask0 | 16;
            break;
          default:
            throw UnknownFieldException.cv(tmp2_index);
        }
      }
    tmp9_input.vx(tmp0_desc);
    return F2Error.r42(tmp3_bitMask0, tmp4_local0, tmp5_local1, tmp6_local2, tmp7_local3, tmp8_local4, null);
  }
  ft() {
    return this.k42_1;
  }
  u15() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [StringSerializer_getInstance(), get_nullable(StringSerializer_getInstance()), StringSerializer_getInstance(), IntSerializer_getInstance(), get_nullable(StringSerializer_getInstance())];
  }
}
class F2Error {
  constructor(message, id, timestamp, code, requestId) {
    id = id === VOID ? Companion_getInstance().lr().toString() : id;
    timestamp = timestamp === VOID ? System_instance.gp().toString() : timestamp;
    code = code === VOID ? 500 : code;
    requestId = requestId === VOID ? null : requestId;
    this.m42_1 = message;
    this.n42_1 = id;
    this.o42_1 = timestamp;
    this.p42_1 = code;
    this.q42_1 = requestId;
  }
  r() {
    return this.m42_1;
  }
  m41() {
    return this.n42_1;
  }
  h42() {
    return this.o42_1;
  }
  i42() {
    return this.p42_1;
  }
  j42() {
    return this.q42_1;
  }
  toString() {
    return "F2Error(timestamp='" + this.timestamp + "', code=" + this.code + ", requestId='" + this.requestId + "', message='" + this.message + "')";
  }
  static r42(seen0, message, id, timestamp, code, requestId, serializationConstructorMarker) {
    if (!(1 === (1 & seen0))) {
      throwMissingFieldException(seen0, 1, $serializer_getInstance().k42_1);
    }
    var $this = createThis(this);
    $this.m42_1 = message;
    if (0 === (seen0 & 2))
      $this.n42_1 = Companion_getInstance().lr().toString();
    else
      $this.n42_1 = id;
    if (0 === (seen0 & 4))
      $this.o42_1 = System_instance.gp().toString();
    else
      $this.o42_1 = timestamp;
    if (0 === (seen0 & 8))
      $this.p42_1 = 500;
    else
      $this.p42_1 = code;
    if (0 === (seen0 & 16))
      $this.q42_1 = null;
    else
      $this.q42_1 = requestId;
    return $this;
  }
  get message() {
    return this.r();
  }
  get id() {
    return this.m41();
  }
  get timestamp() {
    return this.h42();
  }
  get code() {
    return this.i42();
  }
  get requestId() {
    return this.j42();
  }
}
class Companion_1 {
  s42(message, id, requestId, code, cause) {
    var tmp0_timestamp = System_instance.gp().toString();
    return F2Exception.w42(new F2Error(message, id, tmp0_timestamp, code, requestId), cause);
  }
  invoke(message, id, requestId, code, cause, $super) {
    id = id === VOID ? '' : id;
    requestId = requestId === VOID ? '' : requestId;
    code = code === VOID ? 500 : code;
    cause = cause === VOID ? null : cause;
    return $super === VOID ? this.s42(message, id, requestId, code, cause) : $super.s42.call(this, message, id, requestId, code, cause);
  }
}
class F2Exception extends RuntimeException {
  constructor(error, cause) {
    return new.target.w42(error, cause);
  }
  static w42(error, cause) {
    cause = cause === VOID ? null : cause;
    var $this = this.fd(error.message, cause);
    captureStack($this, $this.v42_1);
    $this.error = error;
    return $this;
  }
  x42() {
    return this.error;
  }
}
class Match {}
function and(match) {
  return new AndMatch(listOf([this, match]));
}
function or(match) {
  return new OrMatch(listOf([this, match]));
}
class AndMatch {
  constructor(matches, negative) {
    negative = negative === VOID ? false : negative;
    this.z42_1 = matches;
    this.a43_1 = negative;
  }
  y42() {
    return this.a43_1;
  }
  map(transform) {
    // Inline function 'kotlin.collections.map' call
    var this_0 = this.z42_1;
    // Inline function 'kotlin.collections.mapTo' call
    var destination = ArrayList.z1(collectionSizeOrDefault(this_0, 10));
    var _iterator__ex2g4s = this_0.l1();
    while (_iterator__ex2g4s.m1()) {
      var item = _iterator__ex2g4s.n1();
      var tmp$ret$2 = item.map(transform);
      destination.a2(tmp$ret$2);
    }
    return new AndMatch(destination, this.a43_1);
  }
  not() {
    return this.b43(VOID, !this.a43_1);
  }
  and(match) {
    return this.b43(plus(this.z42_1, match));
  }
  c43(matches, negative) {
    return new AndMatch(matches, negative);
  }
  b43(matches, negative, $super) {
    matches = matches === VOID ? this.z42_1 : matches;
    negative = negative === VOID ? this.a43_1 : negative;
    return $super === VOID ? this.c43(matches, negative) : $super.c43.call(this, matches, negative);
  }
  toString() {
    return 'AndMatch(matches=' + toString(this.z42_1) + ', negative=' + this.a43_1 + ')';
  }
  hashCode() {
    var result = hashCode(this.z42_1);
    result = imul(result, 31) + getBooleanHashCode(this.a43_1) | 0;
    return result;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof AndMatch))
      return false;
    if (!equals(this.z42_1, other.z42_1))
      return false;
    if (!(this.a43_1 === other.a43_1))
      return false;
    return true;
  }
  get negative() {
    return this.y42();
  }
}
class OrMatch {
  constructor(matches, negative) {
    negative = negative === VOID ? false : negative;
    this.d43_1 = matches;
    this.e43_1 = negative;
  }
  y42() {
    return this.e43_1;
  }
  map(transform) {
    // Inline function 'kotlin.collections.map' call
    var this_0 = this.d43_1;
    // Inline function 'kotlin.collections.mapTo' call
    var destination = ArrayList.z1(collectionSizeOrDefault(this_0, 10));
    var _iterator__ex2g4s = this_0.l1();
    while (_iterator__ex2g4s.m1()) {
      var item = _iterator__ex2g4s.n1();
      var tmp$ret$2 = item.map(transform);
      destination.a2(tmp$ret$2);
    }
    return new OrMatch(destination, this.e43_1);
  }
  not() {
    return this.f43(VOID, !this.e43_1);
  }
  or(match) {
    return this.f43(plus(this.d43_1, match));
  }
  c43(matches, negative) {
    return new OrMatch(matches, negative);
  }
  f43(matches, negative, $super) {
    matches = matches === VOID ? this.d43_1 : matches;
    negative = negative === VOID ? this.e43_1 : negative;
    return $super === VOID ? this.c43(matches, negative) : $super.c43.call(this, matches, negative);
  }
  toString() {
    return 'OrMatch(matches=' + toString(this.d43_1) + ', negative=' + this.e43_1 + ')';
  }
  hashCode() {
    var result = hashCode(this.d43_1);
    result = imul(result, 31) + getBooleanHashCode(this.e43_1) | 0;
    return result;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof OrMatch))
      return false;
    if (!equals(this.d43_1, other.d43_1))
      return false;
    if (!(this.e43_1 === other.e43_1))
      return false;
    return true;
  }
  get negative() {
    return this.y42();
  }
}
class SortDTO {}
class PageDTO {}
class Companion_2 {
  constructor() {
    Companion_instance_2 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.Page', null, 2);
    tmp0_serialDesc.f15('total', false);
    tmp0_serialDesc.f15('items', false);
    this.l43_1 = tmp0_serialDesc;
  }
  m43(typeSerial0) {
    return $serializer_1.p43(typeSerial0);
  }
  g16(typeParamsSerializers) {
    return this.m43(typeParamsSerializers[0]);
  }
}
class $serializer_1 {
  constructor() {
    return new.target.q43();
  }
  static q43() {
    var $this = createThis(this);
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.Page', $this, 2);
    tmp0_serialDesc.f15('total', false);
    tmp0_serialDesc.f15('items', false);
    $this.n43_1 = tmp0_serialDesc;
    return $this;
  }
  r43(encoder, value) {
    var tmp0_desc = this.n43_1;
    var tmp1_output = encoder.ux(tmp0_desc);
    tmp1_output.fz(tmp0_desc, 0, value.s43_1);
    tmp1_output.mz(tmp0_desc, 1, new ArrayListSerializer(this.o43_1), value.t43_1);
    tmp1_output.vx(tmp0_desc);
  }
  gt(encoder, value) {
    return this.r43(encoder, value instanceof Page ? value : THROW_CCE());
  }
  ht(decoder) {
    var tmp0_desc = this.n43_1;
    var tmp1_flag = true;
    var tmp2_index = 0;
    var tmp3_bitMask0 = 0;
    var tmp4_local0 = 0;
    var tmp5_local1 = null;
    var tmp6_input = decoder.ux(tmp0_desc);
    if (tmp6_input.ky()) {
      tmp4_local0 = tmp6_input.zx(tmp0_desc, 0);
      tmp3_bitMask0 = tmp3_bitMask0 | 1;
      tmp5_local1 = tmp6_input.gy(tmp0_desc, 1, new ArrayListSerializer(this.o43_1), tmp5_local1);
      tmp3_bitMask0 = tmp3_bitMask0 | 2;
    } else
      while (tmp1_flag) {
        tmp2_index = tmp6_input.ly(tmp0_desc);
        switch (tmp2_index) {
          case -1:
            tmp1_flag = false;
            break;
          case 0:
            tmp4_local0 = tmp6_input.zx(tmp0_desc, 0);
            tmp3_bitMask0 = tmp3_bitMask0 | 1;
            break;
          case 1:
            tmp5_local1 = tmp6_input.gy(tmp0_desc, 1, new ArrayListSerializer(this.o43_1), tmp5_local1);
            tmp3_bitMask0 = tmp3_bitMask0 | 2;
            break;
          default:
            throw UnknownFieldException.cv(tmp2_index);
        }
      }
    tmp6_input.vx(tmp0_desc);
    return Page.u43(tmp3_bitMask0, tmp4_local0, tmp5_local1, null);
  }
  ft() {
    return this.n43_1;
  }
  u15() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [IntSerializer_getInstance(), new ArrayListSerializer(this.o43_1)];
  }
  v15() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [this.o43_1];
  }
  static p43(typeSerial0) {
    var $this = this.q43();
    $this.o43_1 = typeSerial0;
    return $this;
  }
}
class Page {
  constructor(total, items) {
    Companion_getInstance_3();
    this.s43_1 = total;
    this.t43_1 = items;
  }
  j43() {
    return this.s43_1;
  }
  k43() {
    return this.t43_1;
  }
  static u43(seen0, total, items, serializationConstructorMarker) {
    Companion_getInstance_3();
    if (!(3 === (3 & seen0))) {
      throwMissingFieldException(seen0, 3, Companion_getInstance_3().l43_1);
    }
    var $this = createThis(this);
    $this.s43_1 = total;
    $this.t43_1 = items;
    return $this;
  }
  get total() {
    return this.j43();
  }
  get items() {
    return this.k43();
  }
}
class PageQueryDTO {}
class PageQueryResultDTO {}
class Companion_3 {
  constructor() {
    Companion_instance_3 = this;
    var tmp = this;
    var tmp_0 = LazyThreadSafetyMode_PUBLICATION_getInstance();
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp.w43_1 = [lazy(tmp_0, PageQuery$Companion$$childSerializers$_anonymous__54lki7)];
  }
}
class $serializer_2 {
  constructor() {
    $serializer_instance_0 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.PageQuery', this, 1);
    tmp0_serialDesc.f15('pagination', false);
    this.x43_1 = tmp0_serialDesc;
  }
  y43(encoder, value) {
    var tmp0_desc = this.x43_1;
    var tmp1_output = encoder.ux(tmp0_desc);
    var tmp2_cached = Companion_getInstance_4().w43_1;
    tmp1_output.oz(tmp0_desc, 0, tmp2_cached[0].w2(), value.z43_1);
    tmp1_output.vx(tmp0_desc);
  }
  gt(encoder, value) {
    return this.y43(encoder, value instanceof PageQuery ? value : THROW_CCE());
  }
  ht(decoder) {
    var tmp0_desc = this.x43_1;
    var tmp1_flag = true;
    var tmp2_index = 0;
    var tmp3_bitMask0 = 0;
    var tmp4_local0 = null;
    var tmp5_input = decoder.ux(tmp0_desc);
    var tmp6_cached = Companion_getInstance_4().w43_1;
    if (tmp5_input.ky()) {
      tmp4_local0 = tmp5_input.iy(tmp0_desc, 0, tmp6_cached[0].w2(), tmp4_local0);
      tmp3_bitMask0 = tmp3_bitMask0 | 1;
    } else
      while (tmp1_flag) {
        tmp2_index = tmp5_input.ly(tmp0_desc);
        switch (tmp2_index) {
          case -1:
            tmp1_flag = false;
            break;
          case 0:
            tmp4_local0 = tmp5_input.iy(tmp0_desc, 0, tmp6_cached[0].w2(), tmp4_local0);
            tmp3_bitMask0 = tmp3_bitMask0 | 1;
            break;
          default:
            throw UnknownFieldException.cv(tmp2_index);
        }
      }
    tmp5_input.vx(tmp0_desc);
    return PageQuery.a44(tmp3_bitMask0, tmp4_local0, null);
  }
  ft() {
    return this.x43_1;
  }
  u15() {
    var tmp0_cached = Companion_getInstance_4().w43_1;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [get_nullable(tmp0_cached[0].w2())];
  }
}
class PageQuery {
  constructor(pagination) {
    Companion_getInstance_4();
    this.z43_1 = pagination;
  }
  v43() {
    return this.z43_1;
  }
  static a44(seen0, pagination, serializationConstructorMarker) {
    Companion_getInstance_4();
    if (!(1 === (1 & seen0))) {
      throwMissingFieldException(seen0, 1, $serializer_getInstance_0().x43_1);
    }
    var $this = createThis(this);
    $this.z43_1 = pagination;
    return $this;
  }
  get pagination() {
    return this.v43();
  }
}
class Companion_4 {
  constructor() {
    Companion_instance_4 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.PageQueryResult', null, 3);
    tmp0_serialDesc.f15('pagination', false);
    tmp0_serialDesc.f15('total', false);
    tmp0_serialDesc.f15('items', false);
    this.b44_1 = tmp0_serialDesc;
  }
  m43(typeSerial0) {
    return $serializer_3.e44(typeSerial0);
  }
  g16(typeParamsSerializers) {
    return this.m43(typeParamsSerializers[0]);
  }
}
class $serializer_3 {
  constructor() {
    return new.target.f44();
  }
  static f44() {
    var $this = createThis(this);
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.PageQueryResult', $this, 3);
    tmp0_serialDesc.f15('pagination', false);
    tmp0_serialDesc.f15('total', false);
    tmp0_serialDesc.f15('items', false);
    $this.c44_1 = tmp0_serialDesc;
    return $this;
  }
  g44(encoder, value) {
    var tmp0_desc = this.c44_1;
    var tmp1_output = encoder.ux(tmp0_desc);
    tmp1_output.oz(tmp0_desc, 0, $serializer_getInstance_1(), value.h44_1);
    tmp1_output.fz(tmp0_desc, 1, value.i44_1);
    tmp1_output.mz(tmp0_desc, 2, new ArrayListSerializer(this.d44_1), value.j44_1);
    tmp1_output.vx(tmp0_desc);
  }
  gt(encoder, value) {
    return this.g44(encoder, value instanceof PageQueryResult ? value : THROW_CCE());
  }
  ht(decoder) {
    var tmp0_desc = this.c44_1;
    var tmp1_flag = true;
    var tmp2_index = 0;
    var tmp3_bitMask0 = 0;
    var tmp4_local0 = null;
    var tmp5_local1 = 0;
    var tmp6_local2 = null;
    var tmp7_input = decoder.ux(tmp0_desc);
    if (tmp7_input.ky()) {
      tmp4_local0 = tmp7_input.iy(tmp0_desc, 0, $serializer_getInstance_1(), tmp4_local0);
      tmp3_bitMask0 = tmp3_bitMask0 | 1;
      tmp5_local1 = tmp7_input.zx(tmp0_desc, 1);
      tmp3_bitMask0 = tmp3_bitMask0 | 2;
      tmp6_local2 = tmp7_input.gy(tmp0_desc, 2, new ArrayListSerializer(this.d44_1), tmp6_local2);
      tmp3_bitMask0 = tmp3_bitMask0 | 4;
    } else
      while (tmp1_flag) {
        tmp2_index = tmp7_input.ly(tmp0_desc);
        switch (tmp2_index) {
          case -1:
            tmp1_flag = false;
            break;
          case 0:
            tmp4_local0 = tmp7_input.iy(tmp0_desc, 0, $serializer_getInstance_1(), tmp4_local0);
            tmp3_bitMask0 = tmp3_bitMask0 | 1;
            break;
          case 1:
            tmp5_local1 = tmp7_input.zx(tmp0_desc, 1);
            tmp3_bitMask0 = tmp3_bitMask0 | 2;
            break;
          case 2:
            tmp6_local2 = tmp7_input.gy(tmp0_desc, 2, new ArrayListSerializer(this.d44_1), tmp6_local2);
            tmp3_bitMask0 = tmp3_bitMask0 | 4;
            break;
          default:
            throw UnknownFieldException.cv(tmp2_index);
        }
      }
    tmp7_input.vx(tmp0_desc);
    return PageQueryResult.k44(tmp3_bitMask0, tmp4_local0, tmp5_local1, tmp6_local2, null);
  }
  ft() {
    return this.c44_1;
  }
  u15() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [get_nullable($serializer_getInstance_1()), IntSerializer_getInstance(), new ArrayListSerializer(this.d44_1)];
  }
  v15() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [this.d44_1];
  }
  static e44(typeSerial0) {
    var $this = this.f44();
    $this.d44_1 = typeSerial0;
    return $this;
  }
}
class PageQueryResult {
  constructor(pagination, total, items) {
    Companion_getInstance_5();
    this.h44_1 = pagination;
    this.i44_1 = total;
    this.j44_1 = items;
  }
  v43() {
    return this.h44_1;
  }
  j43() {
    return this.i44_1;
  }
  k43() {
    return this.j44_1;
  }
  static k44(seen0, pagination, total, items, serializationConstructorMarker) {
    Companion_getInstance_5();
    if (!(7 === (7 & seen0))) {
      throwMissingFieldException(seen0, 7, Companion_getInstance_5().b44_1);
    }
    var $this = createThis(this);
    $this.h44_1 = pagination;
    $this.i44_1 = total;
    $this.j44_1 = items;
    return $this;
  }
  get pagination() {
    return this.v43();
  }
  get total() {
    return this.j43();
  }
  get items() {
    return this.k43();
  }
}
class Companion_5 {
  f1j() {
    var tmp = getKClass(Pagination);
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_0 = [getKClass(OffsetPaginationDTO), getKClass(PagePaginationDTO)];
    var tmp_1 = getKClass(OffsetPaginationDTO);
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp$ret$3 = [];
    var tmp_2 = PolymorphicSerializer.xt(tmp_1, tmp$ret$3);
    var tmp_3 = getKClass(PagePaginationDTO);
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp$ret$6 = [];
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_4 = [tmp_2, PolymorphicSerializer.xt(tmp_3, tmp$ret$6)];
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp$ret$12 = [];
    return SealedClassSerializer.mu('f2.dsl.cqrs.page.Pagination', tmp, tmp_0, tmp_4, tmp$ret$12);
  }
  g16(typeParamsSerializers) {
    return this.f1j();
  }
}
class Pagination {}
class OffsetPaginationDTO {}
class PagePaginationDTO {}
class Companion_6 {}
class $serializer_4 {
  constructor() {
    $serializer_instance_1 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.OffsetPagination', this, 2);
    tmp0_serialDesc.f15('offset', false);
    tmp0_serialDesc.f15('limit', false);
    this.o44_1 = tmp0_serialDesc;
  }
  p44(encoder, value) {
    var tmp0_desc = this.o44_1;
    var tmp1_output = encoder.ux(tmp0_desc);
    tmp1_output.fz(tmp0_desc, 0, value.q44_1);
    tmp1_output.fz(tmp0_desc, 1, value.r44_1);
    tmp1_output.vx(tmp0_desc);
  }
  gt(encoder, value) {
    return this.p44(encoder, value instanceof OffsetPagination ? value : THROW_CCE());
  }
  ht(decoder) {
    var tmp0_desc = this.o44_1;
    var tmp1_flag = true;
    var tmp2_index = 0;
    var tmp3_bitMask0 = 0;
    var tmp4_local0 = 0;
    var tmp5_local1 = 0;
    var tmp6_input = decoder.ux(tmp0_desc);
    if (tmp6_input.ky()) {
      tmp4_local0 = tmp6_input.zx(tmp0_desc, 0);
      tmp3_bitMask0 = tmp3_bitMask0 | 1;
      tmp5_local1 = tmp6_input.zx(tmp0_desc, 1);
      tmp3_bitMask0 = tmp3_bitMask0 | 2;
    } else
      while (tmp1_flag) {
        tmp2_index = tmp6_input.ly(tmp0_desc);
        switch (tmp2_index) {
          case -1:
            tmp1_flag = false;
            break;
          case 0:
            tmp4_local0 = tmp6_input.zx(tmp0_desc, 0);
            tmp3_bitMask0 = tmp3_bitMask0 | 1;
            break;
          case 1:
            tmp5_local1 = tmp6_input.zx(tmp0_desc, 1);
            tmp3_bitMask0 = tmp3_bitMask0 | 2;
            break;
          default:
            throw UnknownFieldException.cv(tmp2_index);
        }
      }
    tmp6_input.vx(tmp0_desc);
    return OffsetPagination.s44(tmp3_bitMask0, tmp4_local0, tmp5_local1, null);
  }
  ft() {
    return this.o44_1;
  }
  u15() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [IntSerializer_getInstance(), IntSerializer_getInstance()];
  }
}
class OffsetPagination {
  constructor(offset, limit) {
    this.q44_1 = offset;
    this.r44_1 = limit;
  }
  l44() {
    return this.q44_1;
  }
  m44() {
    return this.r44_1;
  }
  static s44(seen0, offset, limit, serializationConstructorMarker) {
    if (!(3 === (3 & seen0))) {
      throwMissingFieldException(seen0, 3, $serializer_getInstance_1().o44_1);
    }
    var $this = createThis(this);
    $this.q44_1 = offset;
    $this.r44_1 = limit;
    return $this;
  }
  get offset() {
    return this.l44();
  }
  get limit() {
    return this.m44();
  }
}
class Companion_7 {}
class $serializer_5 {
  constructor() {
    $serializer_instance_2 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.PagePagination', this, 2);
    tmp0_serialDesc.f15('page', false);
    tmp0_serialDesc.f15('size', false);
    this.t44_1 = tmp0_serialDesc;
  }
  u44(encoder, value) {
    var tmp0_desc = this.t44_1;
    var tmp1_output = encoder.ux(tmp0_desc);
    tmp1_output.oz(tmp0_desc, 0, IntSerializer_getInstance(), value.v44_1);
    tmp1_output.oz(tmp0_desc, 1, IntSerializer_getInstance(), value.w44_1);
    tmp1_output.vx(tmp0_desc);
  }
  gt(encoder, value) {
    return this.u44(encoder, value instanceof PagePagination ? value : THROW_CCE());
  }
  ht(decoder) {
    var tmp0_desc = this.t44_1;
    var tmp1_flag = true;
    var tmp2_index = 0;
    var tmp3_bitMask0 = 0;
    var tmp4_local0 = null;
    var tmp5_local1 = null;
    var tmp6_input = decoder.ux(tmp0_desc);
    if (tmp6_input.ky()) {
      tmp4_local0 = tmp6_input.iy(tmp0_desc, 0, IntSerializer_getInstance(), tmp4_local0);
      tmp3_bitMask0 = tmp3_bitMask0 | 1;
      tmp5_local1 = tmp6_input.iy(tmp0_desc, 1, IntSerializer_getInstance(), tmp5_local1);
      tmp3_bitMask0 = tmp3_bitMask0 | 2;
    } else
      while (tmp1_flag) {
        tmp2_index = tmp6_input.ly(tmp0_desc);
        switch (tmp2_index) {
          case -1:
            tmp1_flag = false;
            break;
          case 0:
            tmp4_local0 = tmp6_input.iy(tmp0_desc, 0, IntSerializer_getInstance(), tmp4_local0);
            tmp3_bitMask0 = tmp3_bitMask0 | 1;
            break;
          case 1:
            tmp5_local1 = tmp6_input.iy(tmp0_desc, 1, IntSerializer_getInstance(), tmp5_local1);
            tmp3_bitMask0 = tmp3_bitMask0 | 2;
            break;
          default:
            throw UnknownFieldException.cv(tmp2_index);
        }
      }
    tmp6_input.vx(tmp0_desc);
    return PagePagination.x44(tmp3_bitMask0, tmp4_local0, tmp5_local1, null);
  }
  ft() {
    return this.t44_1;
  }
  u15() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [get_nullable(IntSerializer_getInstance()), get_nullable(IntSerializer_getInstance())];
  }
}
class PagePagination {
  constructor(page, size) {
    this.v44_1 = page;
    this.w44_1 = size;
  }
  n44() {
    return this.v44_1;
  }
  j2() {
    return this.w44_1;
  }
  static x44(seen0, page, size, serializationConstructorMarker) {
    if (!(3 === (3 & seen0))) {
      throwMissingFieldException(seen0, 3, $serializer_getInstance_2().t44_1);
    }
    var $this = createThis(this);
    $this.v44_1 = page;
    $this.w44_1 = size;
    return $this;
  }
  get page() {
    return this.n44();
  }
  get size() {
    return this.j2();
  }
}
//endregion
var Companion_instance;
function Companion_getInstance_0() {
  if (Companion_instance === VOID)
    new Companion();
  return Companion_instance;
}
var Companion_instance_0;
function Companion_getInstance_1() {
  return Companion_instance_0;
}
var $serializer_instance;
function $serializer_getInstance() {
  if ($serializer_instance === VOID)
    new $serializer_0();
  return $serializer_instance;
}
var Companion_instance_1;
function Companion_getInstance_2() {
  return Companion_instance_1;
}
var Companion_instance_2;
function Companion_getInstance_3() {
  if (Companion_instance_2 === VOID)
    new Companion_2();
  return Companion_instance_2;
}
function PageQuery$Companion$$childSerializers$_anonymous__54lki7() {
  var tmp = getKClass(OffsetPaginationDTO);
  // Inline function 'kotlin.arrayOf' call
  // Inline function 'kotlin.js.unsafeCast' call
  // Inline function 'kotlin.js.asDynamic' call
  var tmp$ret$0 = [];
  return PolymorphicSerializer.xt(tmp, tmp$ret$0);
}
var Companion_instance_3;
function Companion_getInstance_4() {
  if (Companion_instance_3 === VOID)
    new Companion_3();
  return Companion_instance_3;
}
var $serializer_instance_0;
function $serializer_getInstance_0() {
  if ($serializer_instance_0 === VOID)
    new $serializer_2();
  return $serializer_instance_0;
}
var Companion_instance_4;
function Companion_getInstance_5() {
  if (Companion_instance_4 === VOID)
    new Companion_4();
  return Companion_instance_4;
}
var Companion_instance_5;
function Companion_getInstance_6() {
  return Companion_instance_5;
}
var Companion_instance_6;
function Companion_getInstance_7() {
  return Companion_instance_6;
}
var $serializer_instance_1;
function $serializer_getInstance_1() {
  if ($serializer_instance_1 === VOID)
    new $serializer_4();
  return $serializer_instance_1;
}
var Companion_instance_7;
function Companion_getInstance_8() {
  return Companion_instance_7;
}
var $serializer_instance_2;
function $serializer_getInstance_2() {
  if ($serializer_instance_2 === VOID)
    new $serializer_5();
  return $serializer_instance_2;
}
//region block: post-declaration
initMetadataForInterface(Message, 'Message');
initMetadataForInterface(Command, 'Command', VOID, VOID, [Message]);
initMetadataForInterface(Event, 'Event', VOID, VOID, [Message]);
initMetadataForInterface(Problem, 'Problem', VOID, VOID, [Message]);
initMetadataForInterface(Query, 'Query', VOID, VOID, [Message]);
initMetadataForInterface(WithEnvelopeId, 'WithEnvelopeId');
initMetadataForInterface(WithEnvelopeData, 'WithEnvelopeData');
initMetadataForInterface(EnvelopeDTO, 'EnvelopeDTO', VOID, VOID, [WithEnvelopeId, WithEnvelopeData, Message]);
initMetadataForCompanion(Companion, VOID, [SerializerFactory]);
initMetadataForClass($serializer, '$serializer', VOID, VOID, [GeneratedSerializer]);
initMetadataForClass(Envelope, 'Envelope', VOID, VOID, [EnvelopeDTO], VOID, VOID, {0: Companion_getInstance_0});
initMetadataForInterface(F2ErrorDTO, 'F2ErrorDTO', VOID, VOID, [Problem]);
initMetadataForCompanion(Companion_0);
protoOf($serializer_0).v15 = typeParametersSerializers;
initMetadataForObject($serializer_0, '$serializer', VOID, VOID, [GeneratedSerializer]);
initMetadataForClass(F2Error, 'F2Error', VOID, VOID, [F2ErrorDTO], VOID, VOID, {0: $serializer_getInstance});
initMetadataForCompanion(Companion_1);
initMetadataForClass(F2Exception, 'F2Exception');
initMetadataForInterface(Match, 'Match');
protoOf(AndMatch).or = or;
initMetadataForClass(AndMatch, 'AndMatch', VOID, VOID, [Match]);
protoOf(OrMatch).and = and;
initMetadataForClass(OrMatch, 'OrMatch', VOID, VOID, [Match]);
initMetadataForInterface(SortDTO, 'SortDTO');
initMetadataForInterface(PageDTO, 'PageDTO');
initMetadataForCompanion(Companion_2, VOID, [SerializerFactory]);
initMetadataForClass($serializer_1, '$serializer', VOID, VOID, [GeneratedSerializer]);
initMetadataForClass(Page, 'Page', VOID, VOID, [PageDTO], VOID, VOID, {0: Companion_getInstance_3});
initMetadataForInterface(PageQueryDTO, 'PageQueryDTO', VOID, VOID, [Query]);
initMetadataForInterface(PageQueryResultDTO, 'PageQueryResultDTO', VOID, VOID, [Event, PageDTO]);
initMetadataForCompanion(Companion_3);
protoOf($serializer_2).v15 = typeParametersSerializers;
initMetadataForObject($serializer_2, '$serializer', VOID, VOID, [GeneratedSerializer]);
initMetadataForClass(PageQuery, 'PageQuery', VOID, VOID, [PageQueryDTO], VOID, VOID, {0: $serializer_getInstance_0});
initMetadataForCompanion(Companion_4, VOID, [SerializerFactory]);
initMetadataForClass($serializer_3, '$serializer', VOID, VOID, [GeneratedSerializer]);
initMetadataForClass(PageQueryResult, 'PageQueryResult', VOID, VOID, [PageQueryResultDTO], VOID, VOID, {0: Companion_getInstance_5});
initMetadataForCompanion(Companion_5, VOID, [SerializerFactory]);
initMetadataForInterface(Pagination, 'Pagination', VOID, VOID, VOID, VOID, VOID, {0: Companion_getInstance_6});
initMetadataForInterface(OffsetPaginationDTO, 'OffsetPaginationDTO', VOID, VOID, [Pagination]);
initMetadataForInterface(PagePaginationDTO, 'PagePaginationDTO', VOID, VOID, [Pagination]);
initMetadataForCompanion(Companion_6);
protoOf($serializer_4).v15 = typeParametersSerializers;
initMetadataForObject($serializer_4, '$serializer', VOID, VOID, [GeneratedSerializer]);
initMetadataForClass(OffsetPagination, 'OffsetPagination', VOID, VOID, [OffsetPaginationDTO], VOID, VOID, {0: $serializer_getInstance_1});
initMetadataForCompanion(Companion_7);
protoOf($serializer_5).v15 = typeParametersSerializers;
initMetadataForObject($serializer_5, '$serializer', VOID, VOID, [GeneratedSerializer]);
initMetadataForClass(PagePagination, 'PagePagination', VOID, VOID, [PagePaginationDTO], VOID, VOID, {0: $serializer_getInstance_2});
//endregion
//region block: init
Companion_instance_0 = new Companion_0();
Companion_instance_1 = new Companion_1();
Companion_instance_5 = new Companion_5();
Companion_instance_6 = new Companion_6();
Companion_instance_7 = new Companion_7();
//endregion
//region block: exports
defineProp(F2Exception, 'Companion', Companion_getInstance_2, VOID, true);
export {
  Envelope as Envelope,
  F2Error as F2Error,
  F2Exception as F2Exception,
  Page as Page,
  PageQuery as PageQuery,
  PageQueryResult as PageQueryResult,
  OffsetPagination as OffsetPagination,
  PagePagination as PagePagination,
};
export {
  F2Error as F2Errorftgm09bnighg,
  F2Exception as F2Exception1pktwbv7zijzw,
  Companion_instance_0 as Companion_instancetosfn6crhoya,
};
//endregion

//# sourceMappingURL=fixers-f2-f2-dsl-f2-dsl-cqrs.mjs.map
