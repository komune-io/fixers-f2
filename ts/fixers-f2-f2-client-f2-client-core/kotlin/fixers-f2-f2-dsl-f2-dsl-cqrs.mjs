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
    tmp0_serialDesc.ch('id', false);
    tmp0_serialDesc.ch('data', false);
    tmp0_serialDesc.ch('type', false);
    tmp0_serialDesc.ch('datacontenttype', true);
    tmp0_serialDesc.ch('specversion', true);
    tmp0_serialDesc.ch('source', true);
    tmp0_serialDesc.ch('time', true);
    this.vh_1 = tmp0_serialDesc;
  }
}
class Envelope {
  constructor(id, data, type, datacontenttype, specversion, source, time) {
    Companion_getInstance_0();
    datacontenttype = datacontenttype === VOID ? null : datacontenttype;
    specversion = specversion === VOID ? null : specversion;
    source = source === VOID ? null : source;
    time = time === VOID ? null : time;
    this.wh_1 = id;
    this.xh_1 = data;
    this.yh_1 = type;
    this.zh_1 = datacontenttype;
    this.ai_1 = specversion;
    this.bi_1 = source;
    this.ci_1 = time;
  }
  oh() {
    return this.wh_1;
  }
  ph() {
    return this.xh_1;
  }
  qh() {
    return this.yh_1;
  }
  rh() {
    return this.zh_1;
  }
  sh() {
    return this.ai_1;
  }
  th() {
    return this.bi_1;
  }
  uh() {
    return this.ci_1;
  }
  static di(seen0, id, data, type, datacontenttype, specversion, source, time, serializationConstructorMarker) {
    Companion_getInstance_0();
    if (!(7 === (7 & seen0))) {
      throwMissingFieldException(seen0, 7, Companion_getInstance_0().vh_1);
    }
    var $this = createThis(this);
    $this.wh_1 = id;
    $this.xh_1 = data;
    $this.yh_1 = type;
    if (0 === (seen0 & 8))
      $this.zh_1 = null;
    else
      $this.zh_1 = datacontenttype;
    if (0 === (seen0 & 16))
      $this.ai_1 = null;
    else
      $this.ai_1 = specversion;
    if (0 === (seen0 & 32))
      $this.bi_1 = null;
    else
      $this.bi_1 = source;
    if (0 === (seen0 & 64))
      $this.ci_1 = null;
    else
      $this.ci_1 = time;
    return $this;
  }
  get id() {
    return this.oh();
  }
  get data() {
    return this.ph();
  }
  get type() {
    return this.qh();
  }
  get datacontenttype() {
    return this.rh();
  }
  get specversion() {
    return this.sh();
  }
  get source() {
    return this.th();
  }
  get time() {
    return this.uh();
  }
}
class F2ErrorDTO {}
class Companion_0 {}
class $serializer {
  constructor() {
    $serializer_instance = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.error.F2Error', this, 5);
    tmp0_serialDesc.ch('message', false);
    tmp0_serialDesc.ch('id', true);
    tmp0_serialDesc.ch('timestamp', true);
    tmp0_serialDesc.ch('code', true);
    tmp0_serialDesc.ch('requestId', true);
    this.hi_1 = tmp0_serialDesc;
  }
  de() {
    return this.hi_1;
  }
  zg() {
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
    this.ii_1 = message;
    this.ji_1 = id;
    this.ki_1 = timestamp;
    this.li_1 = code;
    this.mi_1 = requestId;
  }
  a9() {
    return this.ii_1;
  }
  oh() {
    return this.ji_1;
  }
  ei() {
    return this.ki_1;
  }
  fi() {
    return this.li_1;
  }
  gi() {
    return this.mi_1;
  }
  toString() {
    return "F2Error(timestamp='" + this.timestamp + "', code=" + this.code + ", requestId='" + this.requestId + "', message='" + this.message + "')";
  }
  static ni(seen0, message, id, timestamp, code, requestId, serializationConstructorMarker) {
    if (!(1 === (1 & seen0))) {
      throwMissingFieldException(seen0, 1, $serializer_getInstance().hi_1);
    }
    var $this = createThis(this);
    $this.ii_1 = message;
    if (0 === (seen0 & 2))
      $this.ji_1 = Companion_getInstance().sd().toString();
    else
      $this.ji_1 = id;
    if (0 === (seen0 & 4))
      $this.ki_1 = System_instance.wc().toString();
    else
      $this.ki_1 = timestamp;
    if (0 === (seen0 & 8))
      $this.li_1 = 500;
    else
      $this.li_1 = code;
    if (0 === (seen0 & 16))
      $this.mi_1 = null;
    else
      $this.mi_1 = requestId;
    return $this;
  }
  get message() {
    return this.a9();
  }
  get id() {
    return this.oh();
  }
  get timestamp() {
    return this.ei();
  }
  get code() {
    return this.fi();
  }
  get requestId() {
    return this.gi();
  }
}
class Companion_1 {
  oi(message, id, requestId, code, cause) {
    var tmp0_timestamp = System_instance.wc().toString();
    return F2Exception.si(new F2Error(message, id, tmp0_timestamp, code, requestId), cause);
  }
  invoke(message, id, requestId, code, cause, $super) {
    id = id === VOID ? '' : id;
    requestId = requestId === VOID ? '' : requestId;
    code = code === VOID ? 500 : code;
    cause = cause === VOID ? null : cause;
    return $super === VOID ? this.oi(message, id, requestId, code, cause) : $super.oi.call(this, message, id, requestId, code, cause);
  }
}
class F2Exception extends RuntimeException {
  constructor(error, cause) {
    return new.target.si(error, cause);
  }
  static si(error, cause) {
    cause = cause === VOID ? null : cause;
    var $this = this.e9(error.message, cause);
    captureStack($this, $this.ri_1);
    $this.error = error;
    return $this;
  }
  ti() {
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
    this.vi_1 = matches;
    this.wi_1 = negative;
  }
  ui() {
    return this.wi_1;
  }
  map(transform) {
    // Inline function 'kotlin.collections.map' call
    var this_0 = this.vi_1;
    // Inline function 'kotlin.collections.mapTo' call
    var destination = ArrayList.m(collectionSizeOrDefault(this_0, 10));
    var _iterator__ex2g4s = this_0.f();
    while (_iterator__ex2g4s.g()) {
      var item = _iterator__ex2g4s.h();
      var tmp$ret$2 = item.map(transform);
      destination.o(tmp$ret$2);
    }
    return new AndMatch(destination, this.wi_1);
  }
  not() {
    return this.xi(VOID, !this.wi_1);
  }
  and(match) {
    return this.xi(plus(this.vi_1, match));
  }
  yi(matches, negative) {
    return new AndMatch(matches, negative);
  }
  xi(matches, negative, $super) {
    matches = matches === VOID ? this.vi_1 : matches;
    negative = negative === VOID ? this.wi_1 : negative;
    return $super === VOID ? this.yi(matches, negative) : $super.yi.call(this, matches, negative);
  }
  toString() {
    return 'AndMatch(matches=' + toString(this.vi_1) + ', negative=' + this.wi_1 + ')';
  }
  hashCode() {
    var result = hashCode(this.vi_1);
    result = imul(result, 31) + getBooleanHashCode(this.wi_1) | 0;
    return result;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof AndMatch))
      return false;
    if (!equals(this.vi_1, other.vi_1))
      return false;
    if (!(this.wi_1 === other.wi_1))
      return false;
    return true;
  }
  get negative() {
    return this.ui();
  }
}
class OrMatch {
  constructor(matches, negative) {
    negative = negative === VOID ? false : negative;
    this.zi_1 = matches;
    this.aj_1 = negative;
  }
  ui() {
    return this.aj_1;
  }
  map(transform) {
    // Inline function 'kotlin.collections.map' call
    var this_0 = this.zi_1;
    // Inline function 'kotlin.collections.mapTo' call
    var destination = ArrayList.m(collectionSizeOrDefault(this_0, 10));
    var _iterator__ex2g4s = this_0.f();
    while (_iterator__ex2g4s.g()) {
      var item = _iterator__ex2g4s.h();
      var tmp$ret$2 = item.map(transform);
      destination.o(tmp$ret$2);
    }
    return new OrMatch(destination, this.aj_1);
  }
  not() {
    return this.bj(VOID, !this.aj_1);
  }
  or(match) {
    return this.bj(plus(this.zi_1, match));
  }
  yi(matches, negative) {
    return new OrMatch(matches, negative);
  }
  bj(matches, negative, $super) {
    matches = matches === VOID ? this.zi_1 : matches;
    negative = negative === VOID ? this.aj_1 : negative;
    return $super === VOID ? this.yi(matches, negative) : $super.yi.call(this, matches, negative);
  }
  toString() {
    return 'OrMatch(matches=' + toString(this.zi_1) + ', negative=' + this.aj_1 + ')';
  }
  hashCode() {
    var result = hashCode(this.zi_1);
    result = imul(result, 31) + getBooleanHashCode(this.aj_1) | 0;
    return result;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof OrMatch))
      return false;
    if (!equals(this.zi_1, other.zi_1))
      return false;
    if (!(this.aj_1 === other.aj_1))
      return false;
    return true;
  }
  get negative() {
    return this.ui();
  }
}
class SortDTO {}
class PageDTO {}
class Companion_2 {
  constructor() {
    Companion_instance_2 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.Page', null, 2);
    tmp0_serialDesc.ch('total', false);
    tmp0_serialDesc.ch('items', false);
    this.hj_1 = tmp0_serialDesc;
  }
}
class Page {
  constructor(total, items) {
    Companion_getInstance_3();
    this.ij_1 = total;
    this.jj_1 = items;
  }
  fj() {
    return this.ij_1;
  }
  gj() {
    return this.jj_1;
  }
  static kj(seen0, total, items, serializationConstructorMarker) {
    Companion_getInstance_3();
    if (!(3 === (3 & seen0))) {
      throwMissingFieldException(seen0, 3, Companion_getInstance_3().hj_1);
    }
    var $this = createThis(this);
    $this.ij_1 = total;
    $this.jj_1 = items;
    return $this;
  }
  get total() {
    return this.fj();
  }
  get items() {
    return this.gj();
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
    tmp.mj_1 = [lazy(tmp_0, PageQuery$Companion$$childSerializers$_anonymous__54lki7)];
  }
}
class $serializer_0 {
  constructor() {
    $serializer_instance_0 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.PageQuery', this, 1);
    tmp0_serialDesc.ch('pagination', false);
    this.nj_1 = tmp0_serialDesc;
  }
  de() {
    return this.nj_1;
  }
  zg() {
    var tmp0_cached = Companion_getInstance_4().mj_1;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [get_nullable(tmp0_cached[0].d1())];
  }
}
class PageQuery {
  constructor(pagination) {
    Companion_getInstance_4();
    this.oj_1 = pagination;
  }
  lj() {
    return this.oj_1;
  }
  static pj(seen0, pagination, serializationConstructorMarker) {
    Companion_getInstance_4();
    if (!(1 === (1 & seen0))) {
      throwMissingFieldException(seen0, 1, $serializer_getInstance_0().nj_1);
    }
    var $this = createThis(this);
    $this.oj_1 = pagination;
    return $this;
  }
  get pagination() {
    return this.lj();
  }
}
class Companion_4 {
  constructor() {
    Companion_instance_4 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.PageQueryResult', null, 3);
    tmp0_serialDesc.ch('pagination', false);
    tmp0_serialDesc.ch('total', false);
    tmp0_serialDesc.ch('items', false);
    this.qj_1 = tmp0_serialDesc;
  }
}
class PageQueryResult {
  constructor(pagination, total, items) {
    Companion_getInstance_5();
    this.rj_1 = pagination;
    this.sj_1 = total;
    this.tj_1 = items;
  }
  lj() {
    return this.rj_1;
  }
  fj() {
    return this.sj_1;
  }
  gj() {
    return this.tj_1;
  }
  static uj(seen0, pagination, total, items, serializationConstructorMarker) {
    Companion_getInstance_5();
    if (!(7 === (7 & seen0))) {
      throwMissingFieldException(seen0, 7, Companion_getInstance_5().qj_1);
    }
    var $this = createThis(this);
    $this.rj_1 = pagination;
    $this.sj_1 = total;
    $this.tj_1 = items;
    return $this;
  }
  get pagination() {
    return this.lj();
  }
  get total() {
    return this.fj();
  }
  get items() {
    return this.gj();
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
    tmp0_serialDesc.ch('offset', false);
    tmp0_serialDesc.ch('limit', false);
    this.yj_1 = tmp0_serialDesc;
  }
  de() {
    return this.yj_1;
  }
  zg() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [IntSerializer_getInstance(), IntSerializer_getInstance()];
  }
}
class OffsetPagination {
  constructor(offset, limit) {
    this.zj_1 = offset;
    this.ak_1 = limit;
  }
  vj() {
    return this.zj_1;
  }
  wj() {
    return this.ak_1;
  }
  static bk(seen0, offset, limit, serializationConstructorMarker) {
    if (!(3 === (3 & seen0))) {
      throwMissingFieldException(seen0, 3, $serializer_getInstance_1().yj_1);
    }
    var $this = createThis(this);
    $this.zj_1 = offset;
    $this.ak_1 = limit;
    return $this;
  }
  get offset() {
    return this.vj();
  }
  get limit() {
    return this.wj();
  }
}
class Companion_7 {}
class $serializer_2 {
  constructor() {
    $serializer_instance_2 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.PagePagination', this, 2);
    tmp0_serialDesc.ch('page', false);
    tmp0_serialDesc.ch('size', false);
    this.ck_1 = tmp0_serialDesc;
  }
  de() {
    return this.ck_1;
  }
  zg() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [get_nullable(IntSerializer_getInstance()), get_nullable(IntSerializer_getInstance())];
  }
}
class PagePagination {
  constructor(page, size) {
    this.dk_1 = page;
    this.ek_1 = size;
  }
  xj() {
    return this.dk_1;
  }
  i() {
    return this.ek_1;
  }
  static fk(seen0, page, size, serializationConstructorMarker) {
    if (!(3 === (3 & seen0))) {
      throwMissingFieldException(seen0, 3, $serializer_getInstance_2().ck_1);
    }
    var $this = createThis(this);
    $this.dk_1 = page;
    $this.ek_1 = size;
    return $this;
  }
  get page() {
    return this.xj();
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
  return PolymorphicSerializer.se(tmp, tmp$ret$0);
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
protoOf($serializer).ah = typeParametersSerializers;
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
protoOf($serializer_0).ah = typeParametersSerializers;
initMetadataForObject($serializer_0, '$serializer', VOID, VOID, [GeneratedSerializer]);
initMetadataForClass(PageQuery, 'PageQuery', VOID, VOID, [PageQueryDTO], VOID, VOID, {0: $serializer_getInstance_0});
initMetadataForCompanion(Companion_4);
initMetadataForClass(PageQueryResult, 'PageQueryResult', VOID, VOID, [PageQueryResultDTO], VOID, VOID, {0: Companion_getInstance_5});
initMetadataForCompanion(Companion_5);
initMetadataForInterface(Pagination, 'Pagination', VOID, VOID, VOID, VOID, VOID, {0: Companion_getInstance_6});
initMetadataForInterface(OffsetPaginationDTO, 'OffsetPaginationDTO', VOID, VOID, [Pagination]);
initMetadataForInterface(PagePaginationDTO, 'PagePaginationDTO', VOID, VOID, [Pagination]);
initMetadataForCompanion(Companion_6);
protoOf($serializer_1).ah = typeParametersSerializers;
initMetadataForObject($serializer_1, '$serializer', VOID, VOID, [GeneratedSerializer]);
initMetadataForClass(OffsetPagination, 'OffsetPagination', VOID, VOID, [OffsetPaginationDTO], VOID, VOID, {0: $serializer_getInstance_1});
initMetadataForCompanion(Companion_7);
protoOf($serializer_2).ah = typeParametersSerializers;
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
