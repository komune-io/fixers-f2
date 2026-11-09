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
    tmp0_serialDesc.l1p('id', false);
    tmp0_serialDesc.l1p('data', false);
    tmp0_serialDesc.l1p('type', false);
    tmp0_serialDesc.l1p('datacontenttype', true);
    tmp0_serialDesc.l1p('specversion', true);
    tmp0_serialDesc.l1p('source', true);
    tmp0_serialDesc.l1p('time', true);
    this.a40_1 = tmp0_serialDesc;
  }
  b40(typeSerial0) {
    return $serializer.e40(typeSerial0);
  }
  m1q(typeParamsSerializers) {
    return this.b40(typeParamsSerializers[0]);
  }
}
class $serializer {
  constructor() {
    return new.target.f40();
  }
  static f40() {
    var $this = createThis(this);
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.envelope.Envelope', $this, 7);
    tmp0_serialDesc.l1p('id', false);
    tmp0_serialDesc.l1p('data', false);
    tmp0_serialDesc.l1p('type', false);
    tmp0_serialDesc.l1p('datacontenttype', true);
    tmp0_serialDesc.l1p('specversion', true);
    tmp0_serialDesc.l1p('source', true);
    tmp0_serialDesc.l1p('time', true);
    $this.c40_1 = tmp0_serialDesc;
    return $this;
  }
  g40(encoder, value) {
    var tmp0_desc = this.c40_1;
    var tmp1_output = encoder.a1i(tmp0_desc);
    tmp1_output.q1j(tmp0_desc, 0, value.h40_1);
    tmp1_output.s1j(tmp0_desc, 1, this.d40_1, value.i40_1);
    tmp1_output.q1j(tmp0_desc, 2, value.j40_1);
    if (tmp1_output.y1j(tmp0_desc, 3) ? true : !(value.k40_1 == null)) {
      tmp1_output.u1j(tmp0_desc, 3, StringSerializer_getInstance(), value.k40_1);
    }
    if (tmp1_output.y1j(tmp0_desc, 4) ? true : !(value.l40_1 == null)) {
      tmp1_output.u1j(tmp0_desc, 4, StringSerializer_getInstance(), value.l40_1);
    }
    if (tmp1_output.y1j(tmp0_desc, 5) ? true : !(value.m40_1 == null)) {
      tmp1_output.u1j(tmp0_desc, 5, StringSerializer_getInstance(), value.m40_1);
    }
    if (tmp1_output.y1j(tmp0_desc, 6) ? true : !(value.n40_1 == null)) {
      tmp1_output.u1j(tmp0_desc, 6, StringSerializer_getInstance(), value.n40_1);
    }
    tmp1_output.b1i(tmp0_desc);
  }
  m1d(encoder, value) {
    return this.g40(encoder, value instanceof Envelope ? value : THROW_CCE());
  }
  n1d(decoder) {
    var tmp0_desc = this.c40_1;
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
    var tmp11_input = decoder.a1i(tmp0_desc);
    if (tmp11_input.q1i()) {
      tmp4_local0 = tmp11_input.k1i(tmp0_desc, 0);
      tmp3_bitMask0 = tmp3_bitMask0 | 1;
      tmp5_local1 = tmp11_input.m1i(tmp0_desc, 1, this.d40_1, tmp5_local1);
      tmp3_bitMask0 = tmp3_bitMask0 | 2;
      tmp6_local2 = tmp11_input.k1i(tmp0_desc, 2);
      tmp3_bitMask0 = tmp3_bitMask0 | 4;
      tmp7_local3 = tmp11_input.o1i(tmp0_desc, 3, StringSerializer_getInstance(), tmp7_local3);
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
            tmp5_local1 = tmp11_input.m1i(tmp0_desc, 1, this.d40_1, tmp5_local1);
            tmp3_bitMask0 = tmp3_bitMask0 | 2;
            break;
          case 2:
            tmp6_local2 = tmp11_input.k1i(tmp0_desc, 2);
            tmp3_bitMask0 = tmp3_bitMask0 | 4;
            break;
          case 3:
            tmp7_local3 = tmp11_input.o1i(tmp0_desc, 3, StringSerializer_getInstance(), tmp7_local3);
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
    return Envelope.o40(tmp3_bitMask0, tmp4_local0, tmp5_local1, tmp6_local2, tmp7_local3, tmp8_local4, tmp9_local5, tmp10_local6, null);
  }
  l1d() {
    return this.c40_1;
  }
  a1q() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [StringSerializer_getInstance(), this.d40_1, StringSerializer_getInstance(), get_nullable(StringSerializer_getInstance()), get_nullable(StringSerializer_getInstance()), get_nullable(StringSerializer_getInstance()), get_nullable(StringSerializer_getInstance())];
  }
  b1q() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [this.d40_1];
  }
  static e40(typeSerial0) {
    var $this = this.f40();
    $this.d40_1 = typeSerial0;
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
    this.h40_1 = id;
    this.i40_1 = data;
    this.j40_1 = type;
    this.k40_1 = datacontenttype;
    this.l40_1 = specversion;
    this.m40_1 = source;
    this.n40_1 = time;
  }
  u3z() {
    return this.h40_1;
  }
  v3z() {
    return this.i40_1;
  }
  t2g() {
    return this.j40_1;
  }
  w3z() {
    return this.k40_1;
  }
  x3z() {
    return this.l40_1;
  }
  y3z() {
    return this.m40_1;
  }
  z3z() {
    return this.n40_1;
  }
  static o40(seen0, id, data, type, datacontenttype, specversion, source, time, serializationConstructorMarker) {
    Companion_getInstance_0();
    if (!(7 === (7 & seen0))) {
      throwMissingFieldException(seen0, 7, Companion_getInstance_0().a40_1);
    }
    var $this = createThis(this);
    $this.h40_1 = id;
    $this.i40_1 = data;
    $this.j40_1 = type;
    if (0 === (seen0 & 8))
      $this.k40_1 = null;
    else
      $this.k40_1 = datacontenttype;
    if (0 === (seen0 & 16))
      $this.l40_1 = null;
    else
      $this.l40_1 = specversion;
    if (0 === (seen0 & 32))
      $this.m40_1 = null;
    else
      $this.m40_1 = source;
    if (0 === (seen0 & 64))
      $this.n40_1 = null;
    else
      $this.n40_1 = time;
    return $this;
  }
  get id() {
    return this.u3z();
  }
  get data() {
    return this.v3z();
  }
  get type() {
    return this.t2g();
  }
  get datacontenttype() {
    return this.w3z();
  }
  get specversion() {
    return this.x3z();
  }
  get source() {
    return this.y3z();
  }
  get time() {
    return this.z3z();
  }
}
class F2ErrorDTO {}
class Companion_0 {
  b3p() {
    return $serializer_getInstance();
  }
}
class $serializer_0 {
  constructor() {
    $serializer_instance = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.error.F2Error', this, 5);
    tmp0_serialDesc.l1p('message', false);
    tmp0_serialDesc.l1p('id', true);
    tmp0_serialDesc.l1p('timestamp', true);
    tmp0_serialDesc.l1p('code', true);
    tmp0_serialDesc.l1p('requestId', true);
    this.s40_1 = tmp0_serialDesc;
  }
  t40(encoder, value) {
    var tmp0_desc = this.s40_1;
    var tmp1_output = encoder.a1i(tmp0_desc);
    tmp1_output.q1j(tmp0_desc, 0, value.message);
    if (tmp1_output.y1j(tmp0_desc, 1) ? true : !(value.id === Companion_getInstance().lr().toString())) {
      tmp1_output.u1j(tmp0_desc, 1, StringSerializer_getInstance(), value.id);
    }
    if (tmp1_output.y1j(tmp0_desc, 2) ? true : !(value.timestamp === System_instance.gp().toString())) {
      tmp1_output.q1j(tmp0_desc, 2, value.timestamp);
    }
    if (tmp1_output.y1j(tmp0_desc, 3) ? true : !(value.code === 500)) {
      tmp1_output.l1j(tmp0_desc, 3, value.code);
    }
    if (tmp1_output.y1j(tmp0_desc, 4) ? true : !(value.requestId == null)) {
      tmp1_output.u1j(tmp0_desc, 4, StringSerializer_getInstance(), value.requestId);
    }
    tmp1_output.b1i(tmp0_desc);
  }
  m1d(encoder, value) {
    return this.t40(encoder, value instanceof F2Error ? value : THROW_CCE());
  }
  n1d(decoder) {
    var tmp0_desc = this.s40_1;
    var tmp1_flag = true;
    var tmp2_index = 0;
    var tmp3_bitMask0 = 0;
    var tmp4_local0 = null;
    var tmp5_local1 = null;
    var tmp6_local2 = null;
    var tmp7_local3 = 0;
    var tmp8_local4 = null;
    var tmp9_input = decoder.a1i(tmp0_desc);
    if (tmp9_input.q1i()) {
      tmp4_local0 = tmp9_input.k1i(tmp0_desc, 0);
      tmp3_bitMask0 = tmp3_bitMask0 | 1;
      tmp5_local1 = tmp9_input.o1i(tmp0_desc, 1, StringSerializer_getInstance(), tmp5_local1);
      tmp3_bitMask0 = tmp3_bitMask0 | 2;
      tmp6_local2 = tmp9_input.k1i(tmp0_desc, 2);
      tmp3_bitMask0 = tmp3_bitMask0 | 4;
      tmp7_local3 = tmp9_input.f1i(tmp0_desc, 3);
      tmp3_bitMask0 = tmp3_bitMask0 | 8;
      tmp8_local4 = tmp9_input.o1i(tmp0_desc, 4, StringSerializer_getInstance(), tmp8_local4);
      tmp3_bitMask0 = tmp3_bitMask0 | 16;
    } else
      while (tmp1_flag) {
        tmp2_index = tmp9_input.r1i(tmp0_desc);
        switch (tmp2_index) {
          case -1:
            tmp1_flag = false;
            break;
          case 0:
            tmp4_local0 = tmp9_input.k1i(tmp0_desc, 0);
            tmp3_bitMask0 = tmp3_bitMask0 | 1;
            break;
          case 1:
            tmp5_local1 = tmp9_input.o1i(tmp0_desc, 1, StringSerializer_getInstance(), tmp5_local1);
            tmp3_bitMask0 = tmp3_bitMask0 | 2;
            break;
          case 2:
            tmp6_local2 = tmp9_input.k1i(tmp0_desc, 2);
            tmp3_bitMask0 = tmp3_bitMask0 | 4;
            break;
          case 3:
            tmp7_local3 = tmp9_input.f1i(tmp0_desc, 3);
            tmp3_bitMask0 = tmp3_bitMask0 | 8;
            break;
          case 4:
            tmp8_local4 = tmp9_input.o1i(tmp0_desc, 4, StringSerializer_getInstance(), tmp8_local4);
            tmp3_bitMask0 = tmp3_bitMask0 | 16;
            break;
          default:
            throw UnknownFieldException.k1f(tmp2_index);
        }
      }
    tmp9_input.b1i(tmp0_desc);
    return F2Error.z40(tmp3_bitMask0, tmp4_local0, tmp5_local1, tmp6_local2, tmp7_local3, tmp8_local4, null);
  }
  l1d() {
    return this.s40_1;
  }
  a1q() {
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
    this.u40_1 = message;
    this.v40_1 = id;
    this.w40_1 = timestamp;
    this.x40_1 = code;
    this.y40_1 = requestId;
  }
  r() {
    return this.u40_1;
  }
  u3z() {
    return this.v40_1;
  }
  p40() {
    return this.w40_1;
  }
  q40() {
    return this.x40_1;
  }
  r40() {
    return this.y40_1;
  }
  toString() {
    return "F2Error(timestamp='" + this.timestamp + "', code=" + this.code + ", requestId='" + this.requestId + "', message='" + this.message + "')";
  }
  static z40(seen0, message, id, timestamp, code, requestId, serializationConstructorMarker) {
    if (!(1 === (1 & seen0))) {
      throwMissingFieldException(seen0, 1, $serializer_getInstance().s40_1);
    }
    var $this = createThis(this);
    $this.u40_1 = message;
    if (0 === (seen0 & 2))
      $this.v40_1 = Companion_getInstance().lr().toString();
    else
      $this.v40_1 = id;
    if (0 === (seen0 & 4))
      $this.w40_1 = System_instance.gp().toString();
    else
      $this.w40_1 = timestamp;
    if (0 === (seen0 & 8))
      $this.x40_1 = 500;
    else
      $this.x40_1 = code;
    if (0 === (seen0 & 16))
      $this.y40_1 = null;
    else
      $this.y40_1 = requestId;
    return $this;
  }
  get message() {
    return this.r();
  }
  get id() {
    return this.u3z();
  }
  get timestamp() {
    return this.p40();
  }
  get code() {
    return this.q40();
  }
  get requestId() {
    return this.r40();
  }
}
class Companion_1 {
  a41(message, id, requestId, code, cause) {
    var tmp0_timestamp = System_instance.gp().toString();
    return F2Exception.e41(new F2Error(message, id, tmp0_timestamp, code, requestId), cause);
  }
  invoke(message, id, requestId, code, cause, $super) {
    id = id === VOID ? '' : id;
    requestId = requestId === VOID ? '' : requestId;
    code = code === VOID ? 500 : code;
    cause = cause === VOID ? null : cause;
    return $super === VOID ? this.a41(message, id, requestId, code, cause) : $super.a41.call(this, message, id, requestId, code, cause);
  }
}
class F2Exception extends RuntimeException {
  constructor(error, cause) {
    return new.target.e41(error, cause);
  }
  static e41(error, cause) {
    cause = cause === VOID ? null : cause;
    var $this = this.fd(error.message, cause);
    captureStack($this, $this.d41_1);
    $this.error = error;
    return $this;
  }
  f41() {
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
    this.h41_1 = matches;
    this.i41_1 = negative;
  }
  g41() {
    return this.i41_1;
  }
  map(transform) {
    // Inline function 'kotlin.collections.map' call
    var this_0 = this.h41_1;
    // Inline function 'kotlin.collections.mapTo' call
    var destination = ArrayList.z1(collectionSizeOrDefault(this_0, 10));
    var _iterator__ex2g4s = this_0.l1();
    while (_iterator__ex2g4s.m1()) {
      var item = _iterator__ex2g4s.n1();
      var tmp$ret$2 = item.map(transform);
      destination.a2(tmp$ret$2);
    }
    return new AndMatch(destination, this.i41_1);
  }
  not() {
    return this.j41(VOID, !this.i41_1);
  }
  and(match) {
    return this.j41(plus(this.h41_1, match));
  }
  k41(matches, negative) {
    return new AndMatch(matches, negative);
  }
  j41(matches, negative, $super) {
    matches = matches === VOID ? this.h41_1 : matches;
    negative = negative === VOID ? this.i41_1 : negative;
    return $super === VOID ? this.k41(matches, negative) : $super.k41.call(this, matches, negative);
  }
  toString() {
    return 'AndMatch(matches=' + toString(this.h41_1) + ', negative=' + this.i41_1 + ')';
  }
  hashCode() {
    var result = hashCode(this.h41_1);
    result = imul(result, 31) + getBooleanHashCode(this.i41_1) | 0;
    return result;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof AndMatch))
      return false;
    if (!equals(this.h41_1, other.h41_1))
      return false;
    if (!(this.i41_1 === other.i41_1))
      return false;
    return true;
  }
  get negative() {
    return this.g41();
  }
}
class OrMatch {
  constructor(matches, negative) {
    negative = negative === VOID ? false : negative;
    this.l41_1 = matches;
    this.m41_1 = negative;
  }
  g41() {
    return this.m41_1;
  }
  map(transform) {
    // Inline function 'kotlin.collections.map' call
    var this_0 = this.l41_1;
    // Inline function 'kotlin.collections.mapTo' call
    var destination = ArrayList.z1(collectionSizeOrDefault(this_0, 10));
    var _iterator__ex2g4s = this_0.l1();
    while (_iterator__ex2g4s.m1()) {
      var item = _iterator__ex2g4s.n1();
      var tmp$ret$2 = item.map(transform);
      destination.a2(tmp$ret$2);
    }
    return new OrMatch(destination, this.m41_1);
  }
  not() {
    return this.n41(VOID, !this.m41_1);
  }
  or(match) {
    return this.n41(plus(this.l41_1, match));
  }
  k41(matches, negative) {
    return new OrMatch(matches, negative);
  }
  n41(matches, negative, $super) {
    matches = matches === VOID ? this.l41_1 : matches;
    negative = negative === VOID ? this.m41_1 : negative;
    return $super === VOID ? this.k41(matches, negative) : $super.k41.call(this, matches, negative);
  }
  toString() {
    return 'OrMatch(matches=' + toString(this.l41_1) + ', negative=' + this.m41_1 + ')';
  }
  hashCode() {
    var result = hashCode(this.l41_1);
    result = imul(result, 31) + getBooleanHashCode(this.m41_1) | 0;
    return result;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof OrMatch))
      return false;
    if (!equals(this.l41_1, other.l41_1))
      return false;
    if (!(this.m41_1 === other.m41_1))
      return false;
    return true;
  }
  get negative() {
    return this.g41();
  }
}
class SortDTO {}
class PageDTO {}
class Companion_2 {
  constructor() {
    Companion_instance_2 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.Page', null, 2);
    tmp0_serialDesc.l1p('total', false);
    tmp0_serialDesc.l1p('items', false);
    this.t41_1 = tmp0_serialDesc;
  }
  u41(typeSerial0) {
    return $serializer_1.x41(typeSerial0);
  }
  m1q(typeParamsSerializers) {
    return this.u41(typeParamsSerializers[0]);
  }
}
class $serializer_1 {
  constructor() {
    return new.target.y41();
  }
  static y41() {
    var $this = createThis(this);
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.Page', $this, 2);
    tmp0_serialDesc.l1p('total', false);
    tmp0_serialDesc.l1p('items', false);
    $this.v41_1 = tmp0_serialDesc;
    return $this;
  }
  z41(encoder, value) {
    var tmp0_desc = this.v41_1;
    var tmp1_output = encoder.a1i(tmp0_desc);
    tmp1_output.l1j(tmp0_desc, 0, value.a42_1);
    tmp1_output.s1j(tmp0_desc, 1, new ArrayListSerializer(this.w41_1), value.b42_1);
    tmp1_output.b1i(tmp0_desc);
  }
  m1d(encoder, value) {
    return this.z41(encoder, value instanceof Page ? value : THROW_CCE());
  }
  n1d(decoder) {
    var tmp0_desc = this.v41_1;
    var tmp1_flag = true;
    var tmp2_index = 0;
    var tmp3_bitMask0 = 0;
    var tmp4_local0 = 0;
    var tmp5_local1 = null;
    var tmp6_input = decoder.a1i(tmp0_desc);
    if (tmp6_input.q1i()) {
      tmp4_local0 = tmp6_input.f1i(tmp0_desc, 0);
      tmp3_bitMask0 = tmp3_bitMask0 | 1;
      tmp5_local1 = tmp6_input.m1i(tmp0_desc, 1, new ArrayListSerializer(this.w41_1), tmp5_local1);
      tmp3_bitMask0 = tmp3_bitMask0 | 2;
    } else
      while (tmp1_flag) {
        tmp2_index = tmp6_input.r1i(tmp0_desc);
        switch (tmp2_index) {
          case -1:
            tmp1_flag = false;
            break;
          case 0:
            tmp4_local0 = tmp6_input.f1i(tmp0_desc, 0);
            tmp3_bitMask0 = tmp3_bitMask0 | 1;
            break;
          case 1:
            tmp5_local1 = tmp6_input.m1i(tmp0_desc, 1, new ArrayListSerializer(this.w41_1), tmp5_local1);
            tmp3_bitMask0 = tmp3_bitMask0 | 2;
            break;
          default:
            throw UnknownFieldException.k1f(tmp2_index);
        }
      }
    tmp6_input.b1i(tmp0_desc);
    return Page.c42(tmp3_bitMask0, tmp4_local0, tmp5_local1, null);
  }
  l1d() {
    return this.v41_1;
  }
  a1q() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [IntSerializer_getInstance(), new ArrayListSerializer(this.w41_1)];
  }
  b1q() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [this.w41_1];
  }
  static x41(typeSerial0) {
    var $this = this.y41();
    $this.w41_1 = typeSerial0;
    return $this;
  }
}
class Page {
  constructor(total, items) {
    Companion_getInstance_3();
    this.a42_1 = total;
    this.b42_1 = items;
  }
  r41() {
    return this.a42_1;
  }
  s41() {
    return this.b42_1;
  }
  static c42(seen0, total, items, serializationConstructorMarker) {
    Companion_getInstance_3();
    if (!(3 === (3 & seen0))) {
      throwMissingFieldException(seen0, 3, Companion_getInstance_3().t41_1);
    }
    var $this = createThis(this);
    $this.a42_1 = total;
    $this.b42_1 = items;
    return $this;
  }
  get total() {
    return this.r41();
  }
  get items() {
    return this.s41();
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
    tmp.e42_1 = [lazy(tmp_0, PageQuery$Companion$$childSerializers$_anonymous__54lki7)];
  }
}
class $serializer_2 {
  constructor() {
    $serializer_instance_0 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.PageQuery', this, 1);
    tmp0_serialDesc.l1p('pagination', false);
    this.f42_1 = tmp0_serialDesc;
  }
  g42(encoder, value) {
    var tmp0_desc = this.f42_1;
    var tmp1_output = encoder.a1i(tmp0_desc);
    var tmp2_cached = Companion_getInstance_4().e42_1;
    tmp1_output.u1j(tmp0_desc, 0, tmp2_cached[0].w2(), value.h42_1);
    tmp1_output.b1i(tmp0_desc);
  }
  m1d(encoder, value) {
    return this.g42(encoder, value instanceof PageQuery ? value : THROW_CCE());
  }
  n1d(decoder) {
    var tmp0_desc = this.f42_1;
    var tmp1_flag = true;
    var tmp2_index = 0;
    var tmp3_bitMask0 = 0;
    var tmp4_local0 = null;
    var tmp5_input = decoder.a1i(tmp0_desc);
    var tmp6_cached = Companion_getInstance_4().e42_1;
    if (tmp5_input.q1i()) {
      tmp4_local0 = tmp5_input.o1i(tmp0_desc, 0, tmp6_cached[0].w2(), tmp4_local0);
      tmp3_bitMask0 = tmp3_bitMask0 | 1;
    } else
      while (tmp1_flag) {
        tmp2_index = tmp5_input.r1i(tmp0_desc);
        switch (tmp2_index) {
          case -1:
            tmp1_flag = false;
            break;
          case 0:
            tmp4_local0 = tmp5_input.o1i(tmp0_desc, 0, tmp6_cached[0].w2(), tmp4_local0);
            tmp3_bitMask0 = tmp3_bitMask0 | 1;
            break;
          default:
            throw UnknownFieldException.k1f(tmp2_index);
        }
      }
    tmp5_input.b1i(tmp0_desc);
    return PageQuery.i42(tmp3_bitMask0, tmp4_local0, null);
  }
  l1d() {
    return this.f42_1;
  }
  a1q() {
    var tmp0_cached = Companion_getInstance_4().e42_1;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [get_nullable(tmp0_cached[0].w2())];
  }
}
class PageQuery {
  constructor(pagination) {
    Companion_getInstance_4();
    this.h42_1 = pagination;
  }
  d42() {
    return this.h42_1;
  }
  static i42(seen0, pagination, serializationConstructorMarker) {
    Companion_getInstance_4();
    if (!(1 === (1 & seen0))) {
      throwMissingFieldException(seen0, 1, $serializer_getInstance_0().f42_1);
    }
    var $this = createThis(this);
    $this.h42_1 = pagination;
    return $this;
  }
  get pagination() {
    return this.d42();
  }
}
class Companion_4 {
  constructor() {
    Companion_instance_4 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.PageQueryResult', null, 3);
    tmp0_serialDesc.l1p('pagination', false);
    tmp0_serialDesc.l1p('total', false);
    tmp0_serialDesc.l1p('items', false);
    this.j42_1 = tmp0_serialDesc;
  }
  u41(typeSerial0) {
    return $serializer_3.m42(typeSerial0);
  }
  m1q(typeParamsSerializers) {
    return this.u41(typeParamsSerializers[0]);
  }
}
class $serializer_3 {
  constructor() {
    return new.target.n42();
  }
  static n42() {
    var $this = createThis(this);
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.PageQueryResult', $this, 3);
    tmp0_serialDesc.l1p('pagination', false);
    tmp0_serialDesc.l1p('total', false);
    tmp0_serialDesc.l1p('items', false);
    $this.k42_1 = tmp0_serialDesc;
    return $this;
  }
  o42(encoder, value) {
    var tmp0_desc = this.k42_1;
    var tmp1_output = encoder.a1i(tmp0_desc);
    tmp1_output.u1j(tmp0_desc, 0, $serializer_getInstance_1(), value.p42_1);
    tmp1_output.l1j(tmp0_desc, 1, value.q42_1);
    tmp1_output.s1j(tmp0_desc, 2, new ArrayListSerializer(this.l42_1), value.r42_1);
    tmp1_output.b1i(tmp0_desc);
  }
  m1d(encoder, value) {
    return this.o42(encoder, value instanceof PageQueryResult ? value : THROW_CCE());
  }
  n1d(decoder) {
    var tmp0_desc = this.k42_1;
    var tmp1_flag = true;
    var tmp2_index = 0;
    var tmp3_bitMask0 = 0;
    var tmp4_local0 = null;
    var tmp5_local1 = 0;
    var tmp6_local2 = null;
    var tmp7_input = decoder.a1i(tmp0_desc);
    if (tmp7_input.q1i()) {
      tmp4_local0 = tmp7_input.o1i(tmp0_desc, 0, $serializer_getInstance_1(), tmp4_local0);
      tmp3_bitMask0 = tmp3_bitMask0 | 1;
      tmp5_local1 = tmp7_input.f1i(tmp0_desc, 1);
      tmp3_bitMask0 = tmp3_bitMask0 | 2;
      tmp6_local2 = tmp7_input.m1i(tmp0_desc, 2, new ArrayListSerializer(this.l42_1), tmp6_local2);
      tmp3_bitMask0 = tmp3_bitMask0 | 4;
    } else
      while (tmp1_flag) {
        tmp2_index = tmp7_input.r1i(tmp0_desc);
        switch (tmp2_index) {
          case -1:
            tmp1_flag = false;
            break;
          case 0:
            tmp4_local0 = tmp7_input.o1i(tmp0_desc, 0, $serializer_getInstance_1(), tmp4_local0);
            tmp3_bitMask0 = tmp3_bitMask0 | 1;
            break;
          case 1:
            tmp5_local1 = tmp7_input.f1i(tmp0_desc, 1);
            tmp3_bitMask0 = tmp3_bitMask0 | 2;
            break;
          case 2:
            tmp6_local2 = tmp7_input.m1i(tmp0_desc, 2, new ArrayListSerializer(this.l42_1), tmp6_local2);
            tmp3_bitMask0 = tmp3_bitMask0 | 4;
            break;
          default:
            throw UnknownFieldException.k1f(tmp2_index);
        }
      }
    tmp7_input.b1i(tmp0_desc);
    return PageQueryResult.s42(tmp3_bitMask0, tmp4_local0, tmp5_local1, tmp6_local2, null);
  }
  l1d() {
    return this.k42_1;
  }
  a1q() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [get_nullable($serializer_getInstance_1()), IntSerializer_getInstance(), new ArrayListSerializer(this.l42_1)];
  }
  b1q() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [this.l42_1];
  }
  static m42(typeSerial0) {
    var $this = this.n42();
    $this.l42_1 = typeSerial0;
    return $this;
  }
}
class PageQueryResult {
  constructor(pagination, total, items) {
    Companion_getInstance_5();
    this.p42_1 = pagination;
    this.q42_1 = total;
    this.r42_1 = items;
  }
  d42() {
    return this.p42_1;
  }
  r41() {
    return this.q42_1;
  }
  s41() {
    return this.r42_1;
  }
  static s42(seen0, pagination, total, items, serializationConstructorMarker) {
    Companion_getInstance_5();
    if (!(7 === (7 & seen0))) {
      throwMissingFieldException(seen0, 7, Companion_getInstance_5().j42_1);
    }
    var $this = createThis(this);
    $this.p42_1 = pagination;
    $this.q42_1 = total;
    $this.r42_1 = items;
    return $this;
  }
  get pagination() {
    return this.d42();
  }
  get total() {
    return this.r41();
  }
  get items() {
    return this.s41();
  }
}
class Companion_5 {
  b3p() {
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
    var tmp_2 = PolymorphicSerializer.d1e(tmp_1, tmp$ret$3);
    var tmp_3 = getKClass(PagePaginationDTO);
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp$ret$6 = [];
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_4 = [tmp_2, PolymorphicSerializer.d1e(tmp_3, tmp$ret$6)];
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp$ret$12 = [];
    return SealedClassSerializer.s1e('f2.dsl.cqrs.page.Pagination', tmp, tmp_0, tmp_4, tmp$ret$12);
  }
  m1q(typeParamsSerializers) {
    return this.b3p();
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
    tmp0_serialDesc.l1p('offset', false);
    tmp0_serialDesc.l1p('limit', false);
    this.w42_1 = tmp0_serialDesc;
  }
  x42(encoder, value) {
    var tmp0_desc = this.w42_1;
    var tmp1_output = encoder.a1i(tmp0_desc);
    tmp1_output.l1j(tmp0_desc, 0, value.y42_1);
    tmp1_output.l1j(tmp0_desc, 1, value.z42_1);
    tmp1_output.b1i(tmp0_desc);
  }
  m1d(encoder, value) {
    return this.x42(encoder, value instanceof OffsetPagination ? value : THROW_CCE());
  }
  n1d(decoder) {
    var tmp0_desc = this.w42_1;
    var tmp1_flag = true;
    var tmp2_index = 0;
    var tmp3_bitMask0 = 0;
    var tmp4_local0 = 0;
    var tmp5_local1 = 0;
    var tmp6_input = decoder.a1i(tmp0_desc);
    if (tmp6_input.q1i()) {
      tmp4_local0 = tmp6_input.f1i(tmp0_desc, 0);
      tmp3_bitMask0 = tmp3_bitMask0 | 1;
      tmp5_local1 = tmp6_input.f1i(tmp0_desc, 1);
      tmp3_bitMask0 = tmp3_bitMask0 | 2;
    } else
      while (tmp1_flag) {
        tmp2_index = tmp6_input.r1i(tmp0_desc);
        switch (tmp2_index) {
          case -1:
            tmp1_flag = false;
            break;
          case 0:
            tmp4_local0 = tmp6_input.f1i(tmp0_desc, 0);
            tmp3_bitMask0 = tmp3_bitMask0 | 1;
            break;
          case 1:
            tmp5_local1 = tmp6_input.f1i(tmp0_desc, 1);
            tmp3_bitMask0 = tmp3_bitMask0 | 2;
            break;
          default:
            throw UnknownFieldException.k1f(tmp2_index);
        }
      }
    tmp6_input.b1i(tmp0_desc);
    return OffsetPagination.a43(tmp3_bitMask0, tmp4_local0, tmp5_local1, null);
  }
  l1d() {
    return this.w42_1;
  }
  a1q() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [IntSerializer_getInstance(), IntSerializer_getInstance()];
  }
}
class OffsetPagination {
  constructor(offset, limit) {
    this.y42_1 = offset;
    this.z42_1 = limit;
  }
  t42() {
    return this.y42_1;
  }
  u42() {
    return this.z42_1;
  }
  static a43(seen0, offset, limit, serializationConstructorMarker) {
    if (!(3 === (3 & seen0))) {
      throwMissingFieldException(seen0, 3, $serializer_getInstance_1().w42_1);
    }
    var $this = createThis(this);
    $this.y42_1 = offset;
    $this.z42_1 = limit;
    return $this;
  }
  get offset() {
    return this.t42();
  }
  get limit() {
    return this.u42();
  }
}
class Companion_7 {}
class $serializer_5 {
  constructor() {
    $serializer_instance_2 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.PagePagination', this, 2);
    tmp0_serialDesc.l1p('page', false);
    tmp0_serialDesc.l1p('size', false);
    this.b43_1 = tmp0_serialDesc;
  }
  c43(encoder, value) {
    var tmp0_desc = this.b43_1;
    var tmp1_output = encoder.a1i(tmp0_desc);
    tmp1_output.u1j(tmp0_desc, 0, IntSerializer_getInstance(), value.d43_1);
    tmp1_output.u1j(tmp0_desc, 1, IntSerializer_getInstance(), value.e43_1);
    tmp1_output.b1i(tmp0_desc);
  }
  m1d(encoder, value) {
    return this.c43(encoder, value instanceof PagePagination ? value : THROW_CCE());
  }
  n1d(decoder) {
    var tmp0_desc = this.b43_1;
    var tmp1_flag = true;
    var tmp2_index = 0;
    var tmp3_bitMask0 = 0;
    var tmp4_local0 = null;
    var tmp5_local1 = null;
    var tmp6_input = decoder.a1i(tmp0_desc);
    if (tmp6_input.q1i()) {
      tmp4_local0 = tmp6_input.o1i(tmp0_desc, 0, IntSerializer_getInstance(), tmp4_local0);
      tmp3_bitMask0 = tmp3_bitMask0 | 1;
      tmp5_local1 = tmp6_input.o1i(tmp0_desc, 1, IntSerializer_getInstance(), tmp5_local1);
      tmp3_bitMask0 = tmp3_bitMask0 | 2;
    } else
      while (tmp1_flag) {
        tmp2_index = tmp6_input.r1i(tmp0_desc);
        switch (tmp2_index) {
          case -1:
            tmp1_flag = false;
            break;
          case 0:
            tmp4_local0 = tmp6_input.o1i(tmp0_desc, 0, IntSerializer_getInstance(), tmp4_local0);
            tmp3_bitMask0 = tmp3_bitMask0 | 1;
            break;
          case 1:
            tmp5_local1 = tmp6_input.o1i(tmp0_desc, 1, IntSerializer_getInstance(), tmp5_local1);
            tmp3_bitMask0 = tmp3_bitMask0 | 2;
            break;
          default:
            throw UnknownFieldException.k1f(tmp2_index);
        }
      }
    tmp6_input.b1i(tmp0_desc);
    return PagePagination.f43(tmp3_bitMask0, tmp4_local0, tmp5_local1, null);
  }
  l1d() {
    return this.b43_1;
  }
  a1q() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [get_nullable(IntSerializer_getInstance()), get_nullable(IntSerializer_getInstance())];
  }
}
class PagePagination {
  constructor(page, size) {
    this.d43_1 = page;
    this.e43_1 = size;
  }
  v42() {
    return this.d43_1;
  }
  j2() {
    return this.e43_1;
  }
  static f43(seen0, page, size, serializationConstructorMarker) {
    if (!(3 === (3 & seen0))) {
      throwMissingFieldException(seen0, 3, $serializer_getInstance_2().b43_1);
    }
    var $this = createThis(this);
    $this.d43_1 = page;
    $this.e43_1 = size;
    return $this;
  }
  get page() {
    return this.v42();
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
  return PolymorphicSerializer.d1e(tmp, tmp$ret$0);
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
protoOf($serializer_0).b1q = typeParametersSerializers;
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
protoOf($serializer_2).b1q = typeParametersSerializers;
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
protoOf($serializer_4).b1q = typeParametersSerializers;
initMetadataForObject($serializer_4, '$serializer', VOID, VOID, [GeneratedSerializer]);
initMetadataForClass(OffsetPagination, 'OffsetPagination', VOID, VOID, [OffsetPaginationDTO], VOID, VOID, {0: $serializer_getInstance_1});
initMetadataForCompanion(Companion_7);
protoOf($serializer_5).b1q = typeParametersSerializers;
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
