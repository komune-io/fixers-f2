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
    tmp0_serialDesc.pf('id', false);
    tmp0_serialDesc.pf('data', false);
    tmp0_serialDesc.pf('type', false);
    tmp0_serialDesc.pf('datacontenttype', true);
    tmp0_serialDesc.pf('specversion', true);
    tmp0_serialDesc.pf('source', true);
    tmp0_serialDesc.pf('time', true);
    this.bg_1 = tmp0_serialDesc;
  }
}
class Envelope {
  constructor(id, data, type, datacontenttype, specversion, source, time) {
    Companion_getInstance_0();
    datacontenttype = datacontenttype === VOID ? null : datacontenttype;
    specversion = specversion === VOID ? null : specversion;
    source = source === VOID ? null : source;
    time = time === VOID ? null : time;
    this.cg_1 = id;
    this.dg_1 = data;
    this.eg_1 = type;
    this.fg_1 = datacontenttype;
    this.gg_1 = specversion;
    this.hg_1 = source;
    this.ig_1 = time;
  }
  uf() {
    return this.cg_1;
  }
  vf() {
    return this.dg_1;
  }
  wf() {
    return this.eg_1;
  }
  xf() {
    return this.fg_1;
  }
  yf() {
    return this.gg_1;
  }
  zf() {
    return this.hg_1;
  }
  ag() {
    return this.ig_1;
  }
  static jg(seen0, id, data, type, datacontenttype, specversion, source, time, serializationConstructorMarker) {
    Companion_getInstance_0();
    if (!(7 === (7 & seen0))) {
      throwMissingFieldException(seen0, 7, Companion_getInstance_0().bg_1);
    }
    var $this = createThis(this);
    $this.cg_1 = id;
    $this.dg_1 = data;
    $this.eg_1 = type;
    if (0 === (seen0 & 8))
      $this.fg_1 = null;
    else
      $this.fg_1 = datacontenttype;
    if (0 === (seen0 & 16))
      $this.gg_1 = null;
    else
      $this.gg_1 = specversion;
    if (0 === (seen0 & 32))
      $this.hg_1 = null;
    else
      $this.hg_1 = source;
    if (0 === (seen0 & 64))
      $this.ig_1 = null;
    else
      $this.ig_1 = time;
    return $this;
  }
  get id() {
    return this.uf();
  }
  get data() {
    return this.vf();
  }
  get type() {
    return this.wf();
  }
  get datacontenttype() {
    return this.xf();
  }
  get specversion() {
    return this.yf();
  }
  get source() {
    return this.zf();
  }
  get time() {
    return this.ag();
  }
}
class F2ErrorDTO {}
class Companion_0 {}
class $serializer {
  constructor() {
    $serializer_instance = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.error.F2Error', this, 5);
    tmp0_serialDesc.pf('message', false);
    tmp0_serialDesc.pf('id', true);
    tmp0_serialDesc.pf('timestamp', true);
    tmp0_serialDesc.pf('code', true);
    tmp0_serialDesc.pf('requestId', true);
    this.ng_1 = tmp0_serialDesc;
  }
  qc() {
    return this.ng_1;
  }
  mf() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [StringSerializer_getInstance(), get_nullable(StringSerializer_getInstance()), StringSerializer_getInstance(), IntSerializer_getInstance(), get_nullable(StringSerializer_getInstance())];
  }
}
class F2Error {
  constructor(message, id, timestamp, code, requestId) {
    id = id === VOID ? Companion_getInstance().kc().toString() : id;
    timestamp = timestamp === VOID ? System_instance.ob().toString() : timestamp;
    code = code === VOID ? 500 : code;
    requestId = requestId === VOID ? null : requestId;
    this.og_1 = message;
    this.pg_1 = id;
    this.qg_1 = timestamp;
    this.rg_1 = code;
    this.sg_1 = requestId;
  }
  w7() {
    return this.og_1;
  }
  uf() {
    return this.pg_1;
  }
  kg() {
    return this.qg_1;
  }
  lg() {
    return this.rg_1;
  }
  mg() {
    return this.sg_1;
  }
  toString() {
    return "F2Error(timestamp='" + this.timestamp + "', code=" + this.code + ", requestId='" + this.requestId + "', message='" + this.message + "')";
  }
  static tg(seen0, message, id, timestamp, code, requestId, serializationConstructorMarker) {
    if (!(1 === (1 & seen0))) {
      throwMissingFieldException(seen0, 1, $serializer_getInstance().ng_1);
    }
    var $this = createThis(this);
    $this.og_1 = message;
    if (0 === (seen0 & 2))
      $this.pg_1 = Companion_getInstance().kc().toString();
    else
      $this.pg_1 = id;
    if (0 === (seen0 & 4))
      $this.qg_1 = System_instance.ob().toString();
    else
      $this.qg_1 = timestamp;
    if (0 === (seen0 & 8))
      $this.rg_1 = 500;
    else
      $this.rg_1 = code;
    if (0 === (seen0 & 16))
      $this.sg_1 = null;
    else
      $this.sg_1 = requestId;
    return $this;
  }
  get message() {
    return this.w7();
  }
  get id() {
    return this.uf();
  }
  get timestamp() {
    return this.kg();
  }
  get code() {
    return this.lg();
  }
  get requestId() {
    return this.mg();
  }
}
class Companion_1 {
  ug(message, id, requestId, code, cause) {
    var tmp0_timestamp = System_instance.ob().toString();
    return F2Exception.yg(new F2Error(message, id, tmp0_timestamp, code, requestId), cause);
  }
  invoke(message, id, requestId, code, cause, $super) {
    id = id === VOID ? '' : id;
    requestId = requestId === VOID ? '' : requestId;
    code = code === VOID ? 500 : code;
    cause = cause === VOID ? null : cause;
    return $super === VOID ? this.ug(message, id, requestId, code, cause) : $super.ug.call(this, message, id, requestId, code, cause);
  }
}
class F2Exception extends RuntimeException {
  constructor(error, cause) {
    return new.target.yg(error, cause);
  }
  static yg(error, cause) {
    cause = cause === VOID ? null : cause;
    var $this = this.a8(error.message, cause);
    captureStack($this, $this.xg_1);
    $this.error = error;
    return $this;
  }
  zg() {
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
    this.bh_1 = matches;
    this.ch_1 = negative;
  }
  ah() {
    return this.ch_1;
  }
  map(transform) {
    // Inline function 'kotlin.collections.map' call
    var this_0 = this.bh_1;
    // Inline function 'kotlin.collections.mapTo' call
    var destination = ArrayList.m(collectionSizeOrDefault(this_0, 10));
    var _iterator__ex2g4s = this_0.f();
    while (_iterator__ex2g4s.g()) {
      var item = _iterator__ex2g4s.h();
      var tmp$ret$2 = item.map(transform);
      destination.o(tmp$ret$2);
    }
    return new AndMatch(destination, this.ch_1);
  }
  not() {
    return this.dh(VOID, !this.ch_1);
  }
  and(match) {
    return this.dh(plus(this.bh_1, match));
  }
  eh(matches, negative) {
    return new AndMatch(matches, negative);
  }
  dh(matches, negative, $super) {
    matches = matches === VOID ? this.bh_1 : matches;
    negative = negative === VOID ? this.ch_1 : negative;
    return $super === VOID ? this.eh(matches, negative) : $super.eh.call(this, matches, negative);
  }
  toString() {
    return 'AndMatch(matches=' + toString(this.bh_1) + ', negative=' + this.ch_1 + ')';
  }
  hashCode() {
    var result = hashCode(this.bh_1);
    result = imul(result, 31) + getBooleanHashCode(this.ch_1) | 0;
    return result;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof AndMatch))
      return false;
    if (!equals(this.bh_1, other.bh_1))
      return false;
    if (!(this.ch_1 === other.ch_1))
      return false;
    return true;
  }
  get negative() {
    return this.ah();
  }
}
class OrMatch {
  constructor(matches, negative) {
    negative = negative === VOID ? false : negative;
    this.fh_1 = matches;
    this.gh_1 = negative;
  }
  ah() {
    return this.gh_1;
  }
  map(transform) {
    // Inline function 'kotlin.collections.map' call
    var this_0 = this.fh_1;
    // Inline function 'kotlin.collections.mapTo' call
    var destination = ArrayList.m(collectionSizeOrDefault(this_0, 10));
    var _iterator__ex2g4s = this_0.f();
    while (_iterator__ex2g4s.g()) {
      var item = _iterator__ex2g4s.h();
      var tmp$ret$2 = item.map(transform);
      destination.o(tmp$ret$2);
    }
    return new OrMatch(destination, this.gh_1);
  }
  not() {
    return this.hh(VOID, !this.gh_1);
  }
  or(match) {
    return this.hh(plus(this.fh_1, match));
  }
  eh(matches, negative) {
    return new OrMatch(matches, negative);
  }
  hh(matches, negative, $super) {
    matches = matches === VOID ? this.fh_1 : matches;
    negative = negative === VOID ? this.gh_1 : negative;
    return $super === VOID ? this.eh(matches, negative) : $super.eh.call(this, matches, negative);
  }
  toString() {
    return 'OrMatch(matches=' + toString(this.fh_1) + ', negative=' + this.gh_1 + ')';
  }
  hashCode() {
    var result = hashCode(this.fh_1);
    result = imul(result, 31) + getBooleanHashCode(this.gh_1) | 0;
    return result;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof OrMatch))
      return false;
    if (!equals(this.fh_1, other.fh_1))
      return false;
    if (!(this.gh_1 === other.gh_1))
      return false;
    return true;
  }
  get negative() {
    return this.ah();
  }
}
class SortDTO {}
class PageDTO {}
class Companion_2 {
  constructor() {
    Companion_instance_2 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.Page', null, 2);
    tmp0_serialDesc.pf('total', false);
    tmp0_serialDesc.pf('items', false);
    this.nh_1 = tmp0_serialDesc;
  }
}
class Page {
  constructor(total, items) {
    Companion_getInstance_3();
    this.oh_1 = total;
    this.ph_1 = items;
  }
  lh() {
    return this.oh_1;
  }
  mh() {
    return this.ph_1;
  }
  static qh(seen0, total, items, serializationConstructorMarker) {
    Companion_getInstance_3();
    if (!(3 === (3 & seen0))) {
      throwMissingFieldException(seen0, 3, Companion_getInstance_3().nh_1);
    }
    var $this = createThis(this);
    $this.oh_1 = total;
    $this.ph_1 = items;
    return $this;
  }
  get total() {
    return this.lh();
  }
  get items() {
    return this.mh();
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
    tmp.sh_1 = [lazy(tmp_0, PageQuery$Companion$$childSerializers$_anonymous__54lki7)];
  }
}
class $serializer_0 {
  constructor() {
    $serializer_instance_0 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.PageQuery', this, 1);
    tmp0_serialDesc.pf('pagination', false);
    this.th_1 = tmp0_serialDesc;
  }
  qc() {
    return this.th_1;
  }
  mf() {
    var tmp0_cached = Companion_getInstance_4().sh_1;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [get_nullable(tmp0_cached[0].d1())];
  }
}
class PageQuery {
  constructor(pagination) {
    Companion_getInstance_4();
    this.uh_1 = pagination;
  }
  rh() {
    return this.uh_1;
  }
  static vh(seen0, pagination, serializationConstructorMarker) {
    Companion_getInstance_4();
    if (!(1 === (1 & seen0))) {
      throwMissingFieldException(seen0, 1, $serializer_getInstance_0().th_1);
    }
    var $this = createThis(this);
    $this.uh_1 = pagination;
    return $this;
  }
  get pagination() {
    return this.rh();
  }
}
class Companion_4 {
  constructor() {
    Companion_instance_4 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.PageQueryResult', null, 3);
    tmp0_serialDesc.pf('pagination', false);
    tmp0_serialDesc.pf('total', false);
    tmp0_serialDesc.pf('items', false);
    this.wh_1 = tmp0_serialDesc;
  }
}
class PageQueryResult {
  constructor(pagination, total, items) {
    Companion_getInstance_5();
    this.xh_1 = pagination;
    this.yh_1 = total;
    this.zh_1 = items;
  }
  rh() {
    return this.xh_1;
  }
  lh() {
    return this.yh_1;
  }
  mh() {
    return this.zh_1;
  }
  static ai(seen0, pagination, total, items, serializationConstructorMarker) {
    Companion_getInstance_5();
    if (!(7 === (7 & seen0))) {
      throwMissingFieldException(seen0, 7, Companion_getInstance_5().wh_1);
    }
    var $this = createThis(this);
    $this.xh_1 = pagination;
    $this.yh_1 = total;
    $this.zh_1 = items;
    return $this;
  }
  get pagination() {
    return this.rh();
  }
  get total() {
    return this.lh();
  }
  get items() {
    return this.mh();
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
    tmp0_serialDesc.pf('offset', false);
    tmp0_serialDesc.pf('limit', false);
    this.ei_1 = tmp0_serialDesc;
  }
  qc() {
    return this.ei_1;
  }
  mf() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [IntSerializer_getInstance(), IntSerializer_getInstance()];
  }
}
class OffsetPagination {
  constructor(offset, limit) {
    this.fi_1 = offset;
    this.gi_1 = limit;
  }
  bi() {
    return this.fi_1;
  }
  ci() {
    return this.gi_1;
  }
  static hi(seen0, offset, limit, serializationConstructorMarker) {
    if (!(3 === (3 & seen0))) {
      throwMissingFieldException(seen0, 3, $serializer_getInstance_1().ei_1);
    }
    var $this = createThis(this);
    $this.fi_1 = offset;
    $this.gi_1 = limit;
    return $this;
  }
  get offset() {
    return this.bi();
  }
  get limit() {
    return this.ci();
  }
}
class Companion_7 {}
class $serializer_2 {
  constructor() {
    $serializer_instance_2 = this;
    var tmp0_serialDesc = new PluginGeneratedSerialDescriptor('f2.dsl.cqrs.page.PagePagination', this, 2);
    tmp0_serialDesc.pf('page', false);
    tmp0_serialDesc.pf('size', false);
    this.ii_1 = tmp0_serialDesc;
  }
  qc() {
    return this.ii_1;
  }
  mf() {
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    return [get_nullable(IntSerializer_getInstance()), get_nullable(IntSerializer_getInstance())];
  }
}
class PagePagination {
  constructor(page, size) {
    this.ji_1 = page;
    this.ki_1 = size;
  }
  di() {
    return this.ji_1;
  }
  i() {
    return this.ki_1;
  }
  static li(seen0, page, size, serializationConstructorMarker) {
    if (!(3 === (3 & seen0))) {
      throwMissingFieldException(seen0, 3, $serializer_getInstance_2().ii_1);
    }
    var $this = createThis(this);
    $this.ji_1 = page;
    $this.ki_1 = size;
    return $this;
  }
  get page() {
    return this.di();
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
  return PolymorphicSerializer.fd(tmp, tmp$ret$0);
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
protoOf($serializer).nf = typeParametersSerializers;
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
protoOf($serializer_0).nf = typeParametersSerializers;
initMetadataForObject($serializer_0, '$serializer', VOID, VOID, [GeneratedSerializer]);
initMetadataForClass(PageQuery, 'PageQuery', VOID, VOID, [PageQueryDTO], VOID, VOID, {0: $serializer_getInstance_0});
initMetadataForCompanion(Companion_4);
initMetadataForClass(PageQueryResult, 'PageQueryResult', VOID, VOID, [PageQueryResultDTO], VOID, VOID, {0: Companion_getInstance_5});
initMetadataForCompanion(Companion_5);
initMetadataForInterface(Pagination, 'Pagination', VOID, VOID, VOID, VOID, VOID, {0: Companion_getInstance_6});
initMetadataForInterface(OffsetPaginationDTO, 'OffsetPaginationDTO', VOID, VOID, [Pagination]);
initMetadataForInterface(PagePaginationDTO, 'PagePaginationDTO', VOID, VOID, [Pagination]);
initMetadataForCompanion(Companion_6);
protoOf($serializer_1).nf = typeParametersSerializers;
initMetadataForObject($serializer_1, '$serializer', VOID, VOID, [GeneratedSerializer]);
initMetadataForClass(OffsetPagination, 'OffsetPagination', VOID, VOID, [OffsetPaginationDTO], VOID, VOID, {0: $serializer_getInstance_1});
initMetadataForCompanion(Companion_7);
protoOf($serializer_2).nf = typeParametersSerializers;
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
