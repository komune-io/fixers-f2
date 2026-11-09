import {
  initMetadataForInterface1egvbzx539z91 as initMetadataForInterface,
  VOID3gxj6tk5isa35 as VOID,
  initMetadataForCompanion1wyw17z38v6ac as initMetadataForCompanion,
  Unit_instance3kz1zqli6gr1r as Unit_instance,
  createThis2j2avj17cvnv2 as createThis,
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
  protoOf180f3jzyo7rfj as protoOf,
  initMetadataForObject1cxne3s9w65el as initMetadataForObject,
  Companion_getInstance2o9uk4k3kfl5 as Companion_getInstance,
  System_instance34rnn1q53nv02 as System_instance,
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
  throwMissingFieldException2cmke0v3ynf14 as throwMissingFieldException,
  SerializableWithd2dap36updxd as SerializableWith,
  StringSerializer_getInstance1p9zda6r6kb23 as StringSerializer_getInstance,
  get_nullable197rfua9r7fsz as get_nullable,
  IntSerializer_getInstance3jyhns9ue706g as IntSerializer_getInstance,
  typeParametersSerializers2likxjr48tr7y as typeParametersSerializers,
  GeneratedSerializer1f7t7hssdd2ws as GeneratedSerializer,
  PolymorphicSerializer3p3fzpdobi8hh as PolymorphicSerializer,
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
    tmp0_serialDesc.eh('id', false);
    tmp0_serialDesc.eh('data', false);
    tmp0_serialDesc.eh('type', false);
    tmp0_serialDesc.eh('datacontenttype', true);
    tmp0_serialDesc.eh('specversion', true);
    tmp0_serialDesc.eh('source', true);
    tmp0_serialDesc.eh('time', true);
    this.ci_1 = tmp0_serialDesc;
  }
}
class Envelope {
  constructor(id, data, type, datacontenttype, specversion, source, time) {
    Companion_getInstance_0();
    datacontenttype = datacontenttype === VOID ? null : datacontenttype;
    specversion = specversion === VOID ? null : specversion;
    source = source === VOID ? null : source;
    time = time === VOID ? null : time;
    this.di_1 = id;
    this.ei_1 = data;
    this.fi_1 = type;
    this.gi_1 = datacontenttype;
    this.hi_1 = specversion;
    this.ii_1 = source;
    this.ji_1 = time;
  }
  wh() {
    return this.di_1;
  }
  xh() {
    return this.ei_1;
  }
  sh() {
    return this.fi_1;
  }
  yh() {
    return this.gi_1;
  }
  zh() {
    return this.hi_1;
  }
  ai() {
    return this.ii_1;
  }
  bi() {
    return this.ji_1;
  }
  static ki(seen0, id, data, type, datacontenttype, specversion, source, time, serializationConstructorMarker) {
    Companion_getInstance_0();
    if (!(7 === (7 & seen0))) {
      throwMissingFieldException(seen0, 7, Companion_getInstance_0().ci_1);
    }
    var $this = createThis(this);
    $this.di_1 = id;
    $this.ei_1 = data;
    $this.fi_1 = type;
    if (0 === (seen0 & 8))
      $this.gi_1 = null;
    else
      $this.gi_1 = datacontenttype;
    if (0 === (seen0 & 16))
      $this.hi_1 = null;
    else
      $this.hi_1 = specversion;
    if (0 === (seen0 & 32))
      $this.ii_1 = null;
    else
      $this.ii_1 = source;
    if (0 === (seen0 & 64))
      $this.ji_1 = null;
    else
      $this.ji_1 = time;
    return $this;
  }
  get id() {
    return this.wh();
  }
  get data() {
    return this.xh();
  }
  get type() {
    return this.sh();
  }
  get datacontenttype() {
    return this.yh();
  }
  get specversion() {
    return this.zh();
  }
  get source() {
    return this.ai();
  }
  get time() {
    return this.bi();
  }
}
class F2ErrorDTO {}
class Companion_0 {}
class $serializer {
  constructor() {
    $serializer_instance = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.error.F2Error', this, 5);
    tmp0_serialDesc.eh('message', false);
    tmp0_serialDesc.eh('id', true);
    tmp0_serialDesc.eh('timestamp', true);
    tmp0_serialDesc.eh('code', true);
    tmp0_serialDesc.eh('requestId', true);
    this.oi_1 = tmp0_serialDesc;
  }
  fe() {
    return this.oi_1;
  }
  bh() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [StringSerializer_getInstance(), get_nullable(StringSerializer_getInstance()), StringSerializer_getInstance(), IntSerializer_getInstance(), get_nullable(StringSerializer_getInstance())];
  }
}
class F2Error {
  constructor(message, id, timestamp, code, requestId) {
    id = id === VOID ? Companion_getInstance().sd().toString() : id;
    timestamp = timestamp === VOID ? System_instance.wc().toString() : timestamp;
    code = code === VOID ? 500 : code;
    requestId = requestId === VOID ? null : requestId;
    this.pi_1 = message;
    this.qi_1 = id;
    this.ri_1 = timestamp;
    this.si_1 = code;
    this.ti_1 = requestId;
  }
  a9() {
    return this.pi_1;
  }
  wh() {
    return this.qi_1;
  }
  li() {
    return this.ri_1;
  }
  mi() {
    return this.si_1;
  }
  ni() {
    return this.ti_1;
  }
  toString() {
    return "F2Error(timestamp='" + this.timestamp + "', code=" + this.code + ", requestId='" + this.requestId + "', message='" + this.message + "')";
  }
  static ui(seen0, message, id, timestamp, code, requestId, serializationConstructorMarker) {
    if (!(1 === (1 & seen0))) {
      throwMissingFieldException(seen0, 1, $serializer_getInstance().oi_1);
    }
    var $this = createThis(this);
    $this.pi_1 = message;
    if (0 === (seen0 & 2))
      $this.qi_1 = Companion_getInstance().sd().toString();
    else
      $this.qi_1 = id;
    if (0 === (seen0 & 4))
      $this.ri_1 = System_instance.wc().toString();
    else
      $this.ri_1 = timestamp;
    if (0 === (seen0 & 8))
      $this.si_1 = 500;
    else
      $this.si_1 = code;
    if (0 === (seen0 & 16))
      $this.ti_1 = null;
    else
      $this.ti_1 = requestId;
    return $this;
  }
  get message() {
    return this.a9();
  }
  get id() {
    return this.wh();
  }
  get timestamp() {
    return this.li();
  }
  get code() {
    return this.mi();
  }
  get requestId() {
    return this.ni();
  }
}
class Companion_1 {
  vi(message, id, requestId, code, cause) {
    var tmp0_timestamp = System_instance.wc().toString();
    return F2Exception.zi(new F2Error(message, id, tmp0_timestamp, code, requestId), cause);
  }
  invoke(message, id, requestId, code, cause, $super) {
    id = id === VOID ? '' : id;
    requestId = requestId === VOID ? '' : requestId;
    code = code === VOID ? 500 : code;
    cause = cause === VOID ? null : cause;
    return $super === VOID ? this.vi(message, id, requestId, code, cause) : $super.vi.call(this, message, id, requestId, code, cause);
  }
}
class F2Exception extends RuntimeException {
  constructor(error, cause) {
    return new.target.zi(error, cause);
  }
  static zi(error, cause) {
    cause = cause === VOID ? null : cause;
    var $this = this.e9(error.message, cause);
    captureStack($this, $this.yi_1);
    $this.error = error;
    return $this;
  }
  aj() {
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
    this.cj_1 = matches;
    this.dj_1 = negative;
  }
  bj() {
    return this.dj_1;
  }
  map(transform) {
    // Inline function 'kotlin.collections.map' call
    var this_0 = this.cj_1;
    // Inline function 'kotlin.collections.mapTo' call
    var destination = ArrayList.m(collectionSizeOrDefault(this_0, 10));
    var _iterator__ex2g4s = this_0.f();
    while (_iterator__ex2g4s.g()) {
      var item = _iterator__ex2g4s.h();
      var tmp$ret$2 = item.map(transform);
      destination.o(tmp$ret$2);
    }
    return new AndMatch(destination, this.dj_1);
  }
  not() {
    return this.ej(VOID, !this.dj_1);
  }
  and(match) {
    return this.ej(plus(this.cj_1, match));
  }
  fj(matches, negative) {
    return new AndMatch(matches, negative);
  }
  ej(matches, negative, $super) {
    matches = matches === VOID ? this.cj_1 : matches;
    negative = negative === VOID ? this.dj_1 : negative;
    return $super === VOID ? this.fj(matches, negative) : $super.fj.call(this, matches, negative);
  }
  toString() {
    return 'AndMatch(matches=' + toString(this.cj_1) + ', negative=' + this.dj_1 + ')';
  }
  hashCode() {
    var result = hashCode(this.cj_1);
    result = imul(result, 31) + getBooleanHashCode(this.dj_1) | 0;
    return result;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof AndMatch))
      return false;
    if (!equals(this.cj_1, other.cj_1))
      return false;
    if (!(this.dj_1 === other.dj_1))
      return false;
    return true;
  }
  get negative() {
    return this.bj();
  }
}
class OrMatch {
  constructor(matches, negative) {
    negative = negative === VOID ? false : negative;
    this.gj_1 = matches;
    this.hj_1 = negative;
  }
  bj() {
    return this.hj_1;
  }
  map(transform) {
    // Inline function 'kotlin.collections.map' call
    var this_0 = this.gj_1;
    // Inline function 'kotlin.collections.mapTo' call
    var destination = ArrayList.m(collectionSizeOrDefault(this_0, 10));
    var _iterator__ex2g4s = this_0.f();
    while (_iterator__ex2g4s.g()) {
      var item = _iterator__ex2g4s.h();
      var tmp$ret$2 = item.map(transform);
      destination.o(tmp$ret$2);
    }
    return new OrMatch(destination, this.hj_1);
  }
  not() {
    return this.ij(VOID, !this.hj_1);
  }
  or(match) {
    return this.ij(plus(this.gj_1, match));
  }
  fj(matches, negative) {
    return new OrMatch(matches, negative);
  }
  ij(matches, negative, $super) {
    matches = matches === VOID ? this.gj_1 : matches;
    negative = negative === VOID ? this.hj_1 : negative;
    return $super === VOID ? this.fj(matches, negative) : $super.fj.call(this, matches, negative);
  }
  toString() {
    return 'OrMatch(matches=' + toString(this.gj_1) + ', negative=' + this.hj_1 + ')';
  }
  hashCode() {
    var result = hashCode(this.gj_1);
    result = imul(result, 31) + getBooleanHashCode(this.hj_1) | 0;
    return result;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof OrMatch))
      return false;
    if (!equals(this.gj_1, other.gj_1))
      return false;
    if (!(this.hj_1 === other.hj_1))
      return false;
    return true;
  }
  get negative() {
    return this.bj();
  }
}
class SortDTO {}
class PageDTO {}
class Companion_2 {
  constructor() {
    Companion_instance_2 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.Page', null, 2);
    tmp0_serialDesc.eh('total', false);
    tmp0_serialDesc.eh('items', false);
    this.oj_1 = tmp0_serialDesc;
  }
}
class Page {
  constructor(total, items) {
    Companion_getInstance_3();
    this.pj_1 = total;
    this.qj_1 = items;
  }
  mj() {
    return this.pj_1;
  }
  nj() {
    return this.qj_1;
  }
  static rj(seen0, total, items, serializationConstructorMarker) {
    Companion_getInstance_3();
    if (!(3 === (3 & seen0))) {
      throwMissingFieldException(seen0, 3, Companion_getInstance_3().oj_1);
    }
    var $this = createThis(this);
    $this.pj_1 = total;
    $this.qj_1 = items;
    return $this;
  }
  get total() {
    return this.mj();
  }
  get items() {
    return this.nj();
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
    tmp.tj_1 = [lazy(tmp_0, PageQuery$Companion$$childSerializers$_anonymous__54lki7)];
  }
}
class $serializer_0 {
  constructor() {
    $serializer_instance_0 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.PageQuery', this, 1);
    tmp0_serialDesc.eh('pagination', false);
    this.uj_1 = tmp0_serialDesc;
  }
  fe() {
    return this.uj_1;
  }
  bh() {
    var tmp0_cached = Companion_getInstance_4().tj_1;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [get_nullable(tmp0_cached[0].d1())];
  }
}
class PageQuery {
  constructor(pagination) {
    Companion_getInstance_4();
    this.vj_1 = pagination;
  }
  sj() {
    return this.vj_1;
  }
  static wj(seen0, pagination, serializationConstructorMarker) {
    Companion_getInstance_4();
    if (!(1 === (1 & seen0))) {
      throwMissingFieldException(seen0, 1, $serializer_getInstance_0().uj_1);
    }
    var $this = createThis(this);
    $this.vj_1 = pagination;
    return $this;
  }
  get pagination() {
    return this.sj();
  }
}
class Companion_4 {
  constructor() {
    Companion_instance_4 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.PageQueryResult', null, 3);
    tmp0_serialDesc.eh('pagination', false);
    tmp0_serialDesc.eh('total', false);
    tmp0_serialDesc.eh('items', false);
    this.xj_1 = tmp0_serialDesc;
  }
}
class PageQueryResult {
  constructor(pagination, total, items) {
    Companion_getInstance_5();
    this.yj_1 = pagination;
    this.zj_1 = total;
    this.ak_1 = items;
  }
  sj() {
    return this.yj_1;
  }
  mj() {
    return this.zj_1;
  }
  nj() {
    return this.ak_1;
  }
  static bk(seen0, pagination, total, items, serializationConstructorMarker) {
    Companion_getInstance_5();
    if (!(7 === (7 & seen0))) {
      throwMissingFieldException(seen0, 7, Companion_getInstance_5().xj_1);
    }
    var $this = createThis(this);
    $this.yj_1 = pagination;
    $this.zj_1 = total;
    $this.ak_1 = items;
    return $this;
  }
  get pagination() {
    return this.sj();
  }
  get total() {
    return this.mj();
  }
  get items() {
    return this.nj();
  }
}
class Companion_5 {}
class Pagination {}
class OffsetPaginationDTO {}
class PagePaginationDTO {}
class Companion_6 {}
class $serializer_1 {
  constructor() {
    $serializer_instance_1 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.OffsetPagination', this, 2);
    tmp0_serialDesc.eh('offset', false);
    tmp0_serialDesc.eh('limit', false);
    this.fk_1 = tmp0_serialDesc;
  }
  fe() {
    return this.fk_1;
  }
  bh() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [IntSerializer_getInstance(), IntSerializer_getInstance()];
  }
}
class OffsetPagination {
  constructor(offset, limit) {
    this.gk_1 = offset;
    this.hk_1 = limit;
  }
  ck() {
    return this.gk_1;
  }
  dk() {
    return this.hk_1;
  }
  static ik(seen0, offset, limit, serializationConstructorMarker) {
    if (!(3 === (3 & seen0))) {
      throwMissingFieldException(seen0, 3, $serializer_getInstance_1().fk_1);
    }
    var $this = createThis(this);
    $this.gk_1 = offset;
    $this.hk_1 = limit;
    return $this;
  }
  get offset() {
    return this.ck();
  }
  get limit() {
    return this.dk();
  }
}
class Companion_7 {}
class $serializer_2 {
  constructor() {
    $serializer_instance_2 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.PagePagination', this, 2);
    tmp0_serialDesc.eh('page', false);
    tmp0_serialDesc.eh('size', false);
    this.jk_1 = tmp0_serialDesc;
  }
  fe() {
    return this.jk_1;
  }
  bh() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [get_nullable(IntSerializer_getInstance()), get_nullable(IntSerializer_getInstance())];
  }
}
class PagePagination {
  constructor(page, size) {
    this.kk_1 = page;
    this.lk_1 = size;
  }
  ek() {
    return this.kk_1;
  }
  i() {
    return this.lk_1;
  }
  static mk(seen0, page, size, serializationConstructorMarker) {
    if (!(3 === (3 & seen0))) {
      throwMissingFieldException(seen0, 3, $serializer_getInstance_2().jk_1);
    }
    var $this = createThis(this);
    $this.kk_1 = page;
    $this.lk_1 = size;
    return $this;
  }
  get page() {
    return this.ek();
  }
  get size() {
    return this.i();
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
    new $serializer();
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
  return PolymorphicSerializer.ue(tmp, tmp$ret$0);
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
    new $serializer_0();
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
    new $serializer_1();
  return $serializer_instance_1;
}
var Companion_instance_7;
function Companion_getInstance_8() {
  return Companion_instance_7;
}
var $serializer_instance_2;
function $serializer_getInstance_2() {
  if ($serializer_instance_2 === VOID)
    new $serializer_2();
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
initMetadataForCompanion(Companion);
initMetadataForClass(Envelope, 'Envelope', VOID, VOID, [EnvelopeDTO], VOID, VOID, {0: Companion_getInstance_0});
initMetadataForInterface(F2ErrorDTO, 'F2ErrorDTO', VOID, VOID, [Problem]);
initMetadataForCompanion(Companion_0);
protoOf($serializer).ch = typeParametersSerializers;
initMetadataForObject($serializer, '$serializer', VOID, VOID, [GeneratedSerializer]);
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
initMetadataForCompanion(Companion_2);
initMetadataForClass(Page, 'Page', VOID, VOID, [PageDTO], VOID, VOID, {0: Companion_getInstance_3});
initMetadataForInterface(PageQueryDTO, 'PageQueryDTO', VOID, VOID, [Query]);
initMetadataForInterface(PageQueryResultDTO, 'PageQueryResultDTO', VOID, VOID, [Event, PageDTO]);
initMetadataForCompanion(Companion_3);
protoOf($serializer_0).ch = typeParametersSerializers;
initMetadataForObject($serializer_0, '$serializer', VOID, VOID, [GeneratedSerializer]);
initMetadataForClass(PageQuery, 'PageQuery', VOID, VOID, [PageQueryDTO], VOID, VOID, {0: $serializer_getInstance_0});
initMetadataForCompanion(Companion_4);
initMetadataForClass(PageQueryResult, 'PageQueryResult', VOID, VOID, [PageQueryResultDTO], VOID, VOID, {0: Companion_getInstance_5});
initMetadataForCompanion(Companion_5);
initMetadataForInterface(Pagination, 'Pagination', VOID, VOID, VOID, VOID, VOID, {0: Companion_getInstance_6});
initMetadataForInterface(OffsetPaginationDTO, 'OffsetPaginationDTO', VOID, VOID, [Pagination]);
initMetadataForInterface(PagePaginationDTO, 'PagePaginationDTO', VOID, VOID, [Pagination]);
initMetadataForCompanion(Companion_6);
protoOf($serializer_1).ch = typeParametersSerializers;
initMetadataForObject($serializer_1, '$serializer', VOID, VOID, [GeneratedSerializer]);
initMetadataForClass(OffsetPagination, 'OffsetPagination', VOID, VOID, [OffsetPaginationDTO], VOID, VOID, {0: $serializer_getInstance_1});
initMetadataForCompanion(Companion_7);
protoOf($serializer_2).ch = typeParametersSerializers;
initMetadataForObject($serializer_2, '$serializer', VOID, VOID, [GeneratedSerializer]);
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
//endregion

//# sourceMappingURL=fixers-f2-f2-dsl-f2-dsl-cqrs.mjs.map
