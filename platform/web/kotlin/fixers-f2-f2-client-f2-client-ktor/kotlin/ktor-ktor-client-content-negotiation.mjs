import {
  KProperty1ca4yb4wlo496 as KProperty1,
  getPropertyCallableRef3hckxc0xueiaj as getPropertyCallableRef,
  constructCallableReference23y65rf941mch as constructCallableReference,
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
  lazy2hsh8ze7j6ikd as lazy,
  endsWith3cq61xxngobwh as endsWith,
  plus1ogy4liedzq5j as plus,
  toMutableSetjdpdbr9jsqq8 as toMutableSet,
  ArrayList3it5z8td81qkl as ArrayList,
  protoOf180f3jzyo7rfj as protoOf,
  Unit_instance3kz1zqli6gr1r as Unit_instance,
  VOID3gxj6tk5isa35 as VOID,
  equals2au1ep9vhcato as equals,
  FunctionAdapter3lcrrz3moet5b as FunctionAdapter,
  isInterface3d6p8outrmvmk as isInterface,
  hashCodeq5arwsb9dgti as hashCode,
  asSequence2phdjljfh9jhx as asSequence,
  flatMap1gb3cjy1dqv3d as flatMap,
  filter184huxd00uyfg as filter,
  emptySequence25zpp7hgtqwdd as emptySequence,
  initMetadataForCompanion1wyw17z38v6ac as initMetadataForCompanion,
  initMetadataForInterface1egvbzx539z91 as initMetadataForInterface,
  Exceptiondt2hlxn7j7vw as Exception,
  captureStack1fzi4aczwc4hg as captureStack,
  toString1pkumu07cwy4m as toString,
  Collection1k04j3hzsbod0 as Collection,
  emptyList1g2z5xcrvp2zy as emptyList,
  collectionSizeOrDefault36dulx8yinfqm as collectionSizeOrDefault,
  getKClassFromExpression348iqjl4fnx2f as getKClassFromExpression,
  Unitkvevlwgzwiuc as Unit,
  ensureNotNull1e947j3ixpazm as ensureNotNull,
  joinToString1cxrrlmo0chqs as joinToString,
  orPromise1iucc2to2udt2 as orPromise,
  to2cs3ny02qtbcb as to,
  mapOf1xd03cq9cnmy8 as mapOf,
  PrimitiveClasses_getInstance13wbaztpz3e2d as PrimitiveClasses_getInstance,
  getKClass3t8tygqu4lcxf as getKClass,
  setOf45ia9pnfhe90 as setOf,
  KtList3hktaavzmj137 as KtList,
  arrayOf1akklvh2at202 as arrayOf,
  createKType31ecntyyaay3k as createKType,
  createInvariantKTypeProjection3h5364czc0a8w as createInvariantKTypeProjection,
  LinkedHashSet2tkztfx86kyx2 as LinkedHashSet,
} from './kotlin-kotlin-stdlib.mjs';
import {
  register$default2ko31hm484mcz as register$default,
  Configuration20xgygxdzhlk5 as Configuration,
  deserialize3e6u4ptep3aeu as deserialize,
} from './ktor-ktor-serialization.mjs';
import {
  parseHeaderValuealpos28fgp6t as parseHeaderValue,
  Companion_getInstance2mdqh2piyf0f8 as Companion_getInstance,
  ContentType1svlpabm9v7iz as ContentType,
  BadContentTypeFormatException2lqc4nmtnbgvk as BadContentTypeFormatException,
  OutgoingContent3t2ohmyam9o76 as OutgoingContent,
  contentType2zzm38yxo3syt as contentType,
  charset1dribv3ku48b1 as charset,
  NullBody_instance22vwyocs8eppk as NullBody_instance,
  contentType317fn4f991q9a as contentType_0,
  Application_getInstance3vhi2qmce4cj0 as Application_getInstance,
  HttpStatusCode3o1wkms10pg4k as HttpStatusCode,
} from './ktor-ktor-http.mjs';
import {
  get_isTraceEnabled82xibuu04nxp as get_isTraceEnabled,
  KtorSimpleLogger1xdphsp5l4e48 as KtorSimpleLogger,
  TypeInfo2nbxsuf4v8os2 as TypeInfo,
  AttributeKey3aq8ytwgx54f7 as AttributeKey,
} from './ktor-ktor-utils.mjs';
import {
  accept2gi3b7wj4jds9 as accept,
  EmptyContent_getInstance1v4yj3cymlz7 as EmptyContent_getInstance,
  get_request3dwcif5y0fvf1 as get_request,
  ClientSSESession332rusa1q4gim as ClientSSESession,
  ClientSSESessionWithDeserialization37nab8162l18r as ClientSSESessionWithDeserialization,
  createClientPluginjwpvufjows5r as createClientPlugin,
} from './ktor-ktor-client-core.mjs';
import {
  Charsets_getInstancezrqwjk0yrlyw as Charsets_getInstance,
  ByteReadChannel2wzou76jce72d as ByteReadChannel,
} from './ktor-ktor-io.mjs';
//region block: imports
//endregion
//region block: pre-declaration
class ConverterRegistration {
  constructor(converter, contentTypeToSend, contentTypeMatcher) {
    this.n3y_1 = converter;
    this.o3y_1 = contentTypeToSend;
    this.p3y_1 = contentTypeMatcher;
  }
}
class ContentNegotiationConfig$defaultMatcher$1 {
  constructor($pattern) {
    this.m3y_1 = $pattern;
    var tmp = this;
    tmp.l3y_1 = lazy(ContentNegotiationConfig$defaultMatcher$o$subtypeSuffix$delegate$lambda($pattern));
  }
  q3y(contentType) {
    if (contentType.i2k(this.m3y_1)) {
      return true;
    }
    var tmp0_elvis_lhs = _get_subtypeSuffix__jfpkue(this);
    var tmp;
    if (tmp0_elvis_lhs == null) {
      return false;
    } else {
      tmp = tmp0_elvis_lhs;
    }
    var subtypeSuffix = tmp;
    return endsWith(contentType.g2i_1, subtypeSuffix, true);
  }
}
class ContentNegotiationConfig {
  constructor() {
    this.r3y_1 = toMutableSet(plus(get_DefaultIgnoredTypes(), get_DefaultCommonIgnoredTypes()));
    var tmp = this;
    // Inline function 'kotlin.collections.mutableListOf' call
    tmp.s3y_1 = ArrayList.p2();
    this.t3y_1 = null;
    this.u3y_1 = Companion_getInstance_0().v3y_1;
  }
  z33(contentType, converter, configuration) {
    this.x3y(contentType, converter, defaultMatcher(this, contentType), configuration);
  }
  x3y(contentTypeToSend, converter, contentTypeMatcher, configuration) {
    // Inline function 'kotlin.apply' call
    configuration(converter);
    var registration = new ConverterRegistration(converter, contentTypeToSend, contentTypeMatcher);
    this.s3y_1.a2(registration);
  }
}
class ContentTypeMergeStrategy {}
class sam$io_ktor_client_plugins_contentnegotiation_ContentTypeMergeStrategy$0 {
  constructor(function_0) {
    this.y3y_1 = function_0;
  }
  z3y(registeredContentTypes, acceptHeaders) {
    return this.y3y_1(registeredContentTypes, acceptHeaders);
  }
  r4() {
    return this.y3y_1;
  }
  equals(other) {
    var tmp;
    if (!(other == null) ? isInterface(other, ContentTypeMergeStrategy) : false) {
      var tmp_0;
      if (!(other == null) ? isInterface(other, FunctionAdapter) : false) {
        tmp_0 = equals(this.r4(), other.r4());
      } else {
        tmp_0 = false;
      }
      tmp = tmp_0;
    } else {
      tmp = false;
    }
    return tmp;
  }
  hashCode() {
    return hashCode(this.r4());
  }
}
class sam$io_ktor_client_plugins_contentnegotiation_ContentTypeMergeStrategy$0_0 {
  constructor(function_0) {
    this.a3z_1 = function_0;
  }
  z3y(registeredContentTypes, acceptHeaders) {
    return this.a3z_1(registeredContentTypes, acceptHeaders);
  }
  r4() {
    return this.a3z_1;
  }
  equals(other) {
    var tmp;
    if (!(other == null) ? isInterface(other, ContentTypeMergeStrategy) : false) {
      var tmp_0;
      if (!(other == null) ? isInterface(other, FunctionAdapter) : false) {
        tmp_0 = equals(this.r4(), other.r4());
      } else {
        tmp_0 = false;
      }
      tmp = tmp_0;
    } else {
      tmp = false;
    }
    return tmp;
  }
  hashCode() {
    return hashCode(this.r4());
  }
}
class Companion {
  constructor() {
    Companion_instance = this;
    var tmp = this;
    var tmp_0 = ContentTypeMergeStrategy$Companion$Default$lambda;
    tmp.v3y_1 = new sam$io_ktor_client_plugins_contentnegotiation_ContentTypeMergeStrategy$0(tmp_0);
    var tmp_1 = this;
    var tmp_2 = ContentTypeMergeStrategy$Companion$SkipIfPresent$lambda;
    tmp_1.w3y_1 = new sam$io_ktor_client_plugins_contentnegotiation_ContentTypeMergeStrategy$0_0(tmp_2);
  }
}
class ContentConverterException extends Exception {
  constructor(message) {
    return new.target.d3z(message);
  }
  static d3z(message) {
    var $this = this.id(message);
    captureStack($this, $this.c3z_1);
    return $this;
  }
}
//endregion
function get_supportedSuffixTypes() {
  _init_properties_ContentNegotiation_kt__o183go();
  return supportedSuffixTypes;
}
var supportedSuffixTypes;
function get_LOGGER() {
  _init_properties_ContentNegotiation_kt__o183go();
  return LOGGER;
}
var LOGGER;
function get_DefaultCommonIgnoredTypes() {
  _init_properties_ContentNegotiation_kt__o183go();
  return DefaultCommonIgnoredTypes;
}
var DefaultCommonIgnoredTypes;
function get_ExcludedContentTypes() {
  _init_properties_ContentNegotiation_kt__o183go();
  return ExcludedContentTypes;
}
var ExcludedContentTypes;
function get_ContentNegotiation() {
  _init_properties_ContentNegotiation_kt__o183go();
  return ContentNegotiation;
}
var ContentNegotiation;
function _get_subtypeSuffix__jfpkue($this) {
  var tmp0 = $this.l3y_1;
  var tmp = KProperty1;
  // Inline function 'kotlin.getValue' call
  getPropertyCallableRef('subtypeSuffix', 1, tmp, ContentNegotiationConfig$defaultMatcher$o$_get_subtypeSuffix_$ref_v9fn95(), null);
  return tmp0.w2();
}
function ContentNegotiationConfig$defaultMatcher$o$subtypeSuffix$delegate$lambda($pattern) {
  return () => {
    var tmp0_safe_receiver = get_supportedSuffixTypes().b4($pattern.h2k());
    var tmp;
    if (tmp0_safe_receiver == null) {
      tmp = null;
    } else {
      // Inline function 'kotlin.let' call
      tmp = '+' + tmp0_safe_receiver;
    }
    return tmp;
  };
}
function ContentNegotiationConfig$defaultMatcher$o$_get_subtypeSuffix_$ref_v9fn95() {
  return constructCallableReference((p0) => _get_subtypeSuffix__jfpkue(p0), 1, 0, 34);
}
function defaultMatcher($this, pattern) {
  return new ContentNegotiationConfig$defaultMatcher$1(pattern);
}
function ContentTypeMergeStrategy$Companion$Default$lambda(registered, headers) {
  var tmp = asSequence(headers);
  var parsedHeaders = flatMap(tmp, ContentTypeMergeStrategy$Companion$Default$lambda$lambda);
  var tmp_0 = asSequence(registered);
  return filter(tmp_0, ContentTypeMergeStrategy$Companion$Default$lambda$lambda_0(parsedHeaders));
}
function ContentTypeMergeStrategy$Companion$Default$lambda$lambda(it) {
  return parseHeaderValue(it);
}
function ContentTypeMergeStrategy$Companion$Default$lambda$lambda_0($parsedHeaders) {
  return (contentType) => {
    var tmp0 = $parsedHeaders;
    var tmp$ret$0;
    $l$block: {
      // Inline function 'kotlin.sequences.none' call
      var _iterator__ex2g4s = tmp0.l1();
      while (_iterator__ex2g4s.m1()) {
        var element = _iterator__ex2g4s.n1();
        var tmp;
        try {
          var acceptedType = Companion_getInstance().kr(element.r2h_1);
          tmp = ContentType.h2i(acceptedType.f2i_1, acceptedType.g2i_1, element.s2h_1).i2k(contentType);
        } catch ($p) {
          var tmp_0;
          if ($p instanceof BadContentTypeFormatException) {
            var e = $p;
            tmp_0 = false;
          } else {
            throw $p;
          }
          tmp = tmp_0;
        }
        if (tmp) {
          tmp$ret$0 = false;
          break $l$block;
        }
      }
      tmp$ret$0 = true;
    }
    return tmp$ret$0;
  };
}
function ContentTypeMergeStrategy$Companion$SkipIfPresent$lambda(registered, headers) {
  var tmp;
  // Inline function 'kotlin.collections.isNotEmpty' call
  if (!headers.j1()) {
    tmp = emptySequence();
  } else {
    tmp = Companion_getInstance_0().v3y_1.z3y(registered, headers);
  }
  return tmp;
}
var Companion_instance;
function Companion_getInstance_0() {
  if (Companion_instance === VOID)
    new Companion();
  return Companion_instance;
}
function ContentNegotiationConfig$_init_$ref_1ne3ob() {
  return constructCallableReference(() => new ContentNegotiationConfig(), 0, 0, 35, '<init>');
}
function ContentNegotiation$lambda($this$createClientPlugin) {
  _init_properties_ContentNegotiation_kt__o183go();
  var registrations = $this$createClientPlugin.c3b_1.s3y_1;
  var ignoredTypes = $this$createClientPlugin.c3b_1.r3y_1;
  $this$createClientPlugin.t3f(ContentNegotiation$lambda$slambda(registrations, $this$createClientPlugin, ignoredTypes));
  $this$createClientPlugin.m3d(ContentNegotiation$lambda$slambda_0(ignoredTypes, registrations, $this$createClientPlugin));
  return Unit_instance;
}
function ContentNegotiation$lambda$convertRequest$lambda(it) {
  _init_properties_ContentNegotiation_kt__o183go();
  return toString(it.n3y_1);
}
function *invoke$convertRequest(registrations, $this_createClientPlugin, ignoredTypes, request, body, $completion) {
  var tmp;
  if (request.m36_1.w29(get_ExcludedContentTypes())) {
    var excluded = request.m36_1.u29(get_ExcludedContentTypes());
    // Inline function 'kotlin.collections.filter' call
    // Inline function 'kotlin.collections.filterTo' call
    var destination = ArrayList.p2();
    var _iterator__ex2g4s = registrations.l1();
    while (_iterator__ex2g4s.m1()) {
      var element = _iterator__ex2g4s.n1();
      var tmp$ret$3;
      $l$block_0: {
        // Inline function 'kotlin.collections.none' call
        var tmp_0;
        if (isInterface(excluded, Collection)) {
          tmp_0 = excluded.j1();
        } else {
          tmp_0 = false;
        }
        if (tmp_0) {
          tmp$ret$3 = true;
          break $l$block_0;
        }
        var _iterator__ex2g4s_0 = excluded.l1();
        while (_iterator__ex2g4s_0.m1()) {
          var element_0 = _iterator__ex2g4s_0.n1();
          if (element.o3y_1.i2k(element_0)) {
            tmp$ret$3 = false;
            break $l$block_0;
          }
        }
        tmp$ret$3 = true;
      }
      if (tmp$ret$3) {
        destination.a2(element);
      }
    }
    tmp = destination;
  } else {
    tmp = registrations;
  }
  var requestRegistrations = tmp;
  // Inline function 'kotlin.collections.orEmpty' call
  var tmp0_elvis_lhs = request.j36_1.e2c('Accept');
  var acceptHeaders = tmp0_elvis_lhs == null ? emptyList() : tmp0_elvis_lhs;
  var tmp_1 = $this_createClientPlugin.c3b_1.u3y_1;
  // Inline function 'kotlin.collections.map' call
  // Inline function 'kotlin.collections.mapTo' call
  var destination_0 = ArrayList.z1(collectionSizeOrDefault(requestRegistrations, 10));
  var _iterator__ex2g4s_1 = requestRegistrations.l1();
  while (_iterator__ex2g4s_1.m1()) {
    var item = _iterator__ex2g4s_1.n1();
    var tmp$ret$8 = item.o3y_1;
    destination_0.a2(tmp$ret$8);
  }
  // Inline function 'kotlin.sequences.forEach' call
  var _iterator__ex2g4s_2 = tmp_1.z3y(destination_0, acceptHeaders).l1();
  while (_iterator__ex2g4s_2.m1()) {
    var element_1 = _iterator__ex2g4s_2.n1();
    var qValue = $this_createClientPlugin.c3b_1.t3y_1;
    var contentTypeToSend = qValue == null ? element_1 : element_1.f2k('q', qValue.toString());
    // Inline function 'io.ktor.util.logging.trace' call
    var this_0 = get_LOGGER();
    if (get_isTraceEnabled(this_0)) {
      var tmp$ret$12 = 'Adding Accept=' + contentTypeToSend.toString() + ' header for ' + request.h36_1.toString();
      this_0.p2g(tmp$ret$12);
    }
    accept(request, contentTypeToSend);
  }
  var tmp_2;
  if (body instanceof OutgoingContent) {
    tmp_2 = true;
  } else {
    var tmp$ret$13;
    $l$block_2: {
      // Inline function 'kotlin.collections.any' call
      var tmp_3;
      if (isInterface(ignoredTypes, Collection)) {
        tmp_3 = ignoredTypes.j1();
      } else {
        tmp_3 = false;
      }
      if (tmp_3) {
        tmp$ret$13 = false;
        break $l$block_2;
      }
      var _iterator__ex2g4s_3 = ignoredTypes.l1();
      while (_iterator__ex2g4s_3.m1()) {
        var element_2 = _iterator__ex2g4s_3.n1();
        if (element_2.le(body)) {
          tmp$ret$13 = true;
          break $l$block_2;
        }
      }
      tmp$ret$13 = false;
    }
    tmp_2 = tmp$ret$13;
  }
  if (tmp_2) {
    // Inline function 'io.ktor.util.logging.trace' call
    var this_1 = get_LOGGER();
    if (get_isTraceEnabled(this_1)) {
      var tmp$ret$16 = 'Body type ' + toString(getKClassFromExpression(body)) + ' is in ignored types. Skipping ContentNegotiation for ' + request.h36_1.toString() + '.';
      this_1.p2g(tmp$ret$16);
    }
    return null;
  }
  var tmp0_elvis_lhs_0 = contentType(request);
  var tmp_4;
  if (tmp0_elvis_lhs_0 == null) {
    // Inline function 'kotlin.run' call
    // Inline function 'io.ktor.util.logging.trace' call
    var this_2 = get_LOGGER();
    if (get_isTraceEnabled(this_2)) {
      var tmp$ret$20 = "Request doesn't have Content-Type header. Skipping ContentNegotiation for " + request.h36_1.toString() + '.';
      this_2.p2g(tmp$ret$20);
    }
    return null;
  } else {
    tmp_4 = tmp0_elvis_lhs_0;
  }
  var contentType_0 = tmp_4;
  if (body instanceof Unit) {
    // Inline function 'io.ktor.util.logging.trace' call
    var this_3 = get_LOGGER();
    if (get_isTraceEnabled(this_3)) {
      var tmp$ret$22 = 'Sending empty body for ' + request.h36_1.toString();
      this_3.p2g(tmp$ret$22);
    }
    request.j36_1.o2c('Content-Type');
    return EmptyContent_getInstance();
  }
  // Inline function 'kotlin.collections.filter' call
  // Inline function 'kotlin.collections.filterTo' call
  var destination_1 = ArrayList.p2();
  var _iterator__ex2g4s_4 = registrations.l1();
  while (_iterator__ex2g4s_4.m1()) {
    var element_3 = _iterator__ex2g4s_4.n1();
    if (element_3.p3y_1.q3y(contentType_0)) {
      destination_1.a2(element_3);
    }
  }
  // Inline function 'kotlin.takeIf' call
  var tmp_5;
  // Inline function 'kotlin.collections.isNotEmpty' call
  if (!destination_1.j1()) {
    tmp_5 = destination_1;
  } else {
    tmp_5 = null;
  }
  var tmp1_elvis_lhs = tmp_5;
  var tmp_6;
  if (tmp1_elvis_lhs == null) {
    // Inline function 'kotlin.run' call
    // Inline function 'io.ktor.util.logging.trace' call
    var this_4 = get_LOGGER();
    if (get_isTraceEnabled(this_4)) {
      var tmp$ret$32 = 'None of the registered converters match request Content-Type=' + contentType_0.toString() + '. ' + ('Skipping ContentNegotiation for ' + request.h36_1.toString() + '.');
      this_4.p2g(tmp$ret$32);
    }
    return null;
  } else {
    tmp_6 = tmp1_elvis_lhs;
  }
  var matchingRegistrations = tmp_6;
  if (request.g3g() == null) {
    // Inline function 'io.ktor.util.logging.trace' call
    var this_5 = get_LOGGER();
    if (get_isTraceEnabled(this_5)) {
      var tmp$ret$34 = 'Request has unknown body type. Skipping ContentNegotiation for ' + request.h36_1.toString() + '.';
      this_5.p2g(tmp$ret$34);
    }
    return null;
  }
  request.j36_1.o2c('Content-Type');
  var tmp$ret$35;
  $l$block_3: {
    // Inline function 'kotlin.collections.firstNotNullOfOrNull' call
    var _iterator__ex2g4s_5 = matchingRegistrations.l1();
    while (_iterator__ex2g4s_5.m1()) {
      var element_4 = _iterator__ex2g4s_5.n1();
      var tmp0_elvis_lhs_1 = charset(contentType_0);
      var tmp_7 = tmp0_elvis_lhs_1 == null ? Charsets_getInstance().q28_1 : tmp0_elvis_lhs_1;
      var tmp_8 = ensureNotNull(request.g3g());
      // Inline function 'kotlin.takeIf' call
      var tmp_9;
      if (!equals(body, NullBody_instance)) {
        tmp_9 = body;
      } else {
        tmp_9 = null;
      }
      var tmp$ret$37 = tmp_9;
      var result = yield* element_4.n3y_1.e3z(contentType_0, tmp_7, tmp_8, tmp$ret$37, $completion);
      if (!(result == null)) {
        // Inline function 'io.ktor.util.logging.trace' call
        var this_6 = get_LOGGER();
        if (get_isTraceEnabled(this_6)) {
          var tmp$ret$40 = 'Converted request body using ' + toString(element_4.n3y_1) + ' for ' + request.h36_1.toString();
          this_6.p2g(tmp$ret$40);
        }
      }
      var result_0 = result;
      if (!(result_0 == null)) {
        tmp$ret$35 = result_0;
        break $l$block_3;
      }
    }
    tmp$ret$35 = null;
  }
  var tmp2_elvis_lhs = tmp$ret$35;
  var tmp_10;
  if (tmp2_elvis_lhs == null) {
    var tmp_11 = "Can't convert " + toString(body) + ' with contentType ' + contentType_0.toString() + ' using converters ';
    throw ContentConverterException.d3z(tmp_11 + joinToString(matchingRegistrations, VOID, VOID, VOID, VOID, VOID, ContentNegotiation$lambda$convertRequest$lambda));
  } else {
    tmp_10 = tmp2_elvis_lhs;
  }
  var serializedContent = tmp_10;
  return serializedContent;
}
function *invoke$convertResponse(ignoredTypes, registrations, $this_createClientPlugin, requestUrl, info, body, responseContentType, charset, $completion) {
  charset = charset === VOID ? Charsets_getInstance().q28_1 : charset;
  if (!isInterface(body, ByteReadChannel)) {
    // Inline function 'io.ktor.util.logging.trace' call
    var this_0 = get_LOGGER();
    if (get_isTraceEnabled(this_0)) {
      var tmp$ret$1 = 'Response body is already transformed. Skipping ContentNegotiation for ' + requestUrl.toString() + '.';
      this_0.p2g(tmp$ret$1);
    }
    return null;
  }
  if (ignoredTypes.r3(info.c2g_1)) {
    // Inline function 'io.ktor.util.logging.trace' call
    var this_1 = get_LOGGER();
    if (get_isTraceEnabled(this_1)) {
      var tmp$ret$3 = 'Response body type ' + toString(info.c2g_1) + ' is in ignored types. Skipping ContentNegotiation for ' + requestUrl.toString() + '.';
      this_1.p2g(tmp$ret$3);
    }
    return null;
  }
  // Inline function 'kotlin.collections.filter' call
  // Inline function 'kotlin.collections.filterTo' call
  var destination = ArrayList.p2();
  var _iterator__ex2g4s = registrations.l1();
  while (_iterator__ex2g4s.m1()) {
    var element = _iterator__ex2g4s.n1();
    if (element.p3y_1.q3y(responseContentType)) {
      destination.a2(element);
    }
  }
  // Inline function 'kotlin.collections.map' call
  // Inline function 'kotlin.collections.mapTo' call
  var destination_0 = ArrayList.z1(collectionSizeOrDefault(destination, 10));
  var _iterator__ex2g4s_0 = destination.l1();
  while (_iterator__ex2g4s_0.m1()) {
    var item = _iterator__ex2g4s_0.n1();
    var tmp$ret$9 = item.n3y_1;
    destination_0.a2(tmp$ret$9);
  }
  // Inline function 'kotlin.takeIf' call
  var tmp;
  // Inline function 'kotlin.collections.isNotEmpty' call
  if (!destination_0.j1()) {
    tmp = destination_0;
  } else {
    tmp = null;
  }
  var tmp0_elvis_lhs = tmp;
  var tmp_0;
  if (tmp0_elvis_lhs == null) {
    // Inline function 'kotlin.run' call
    // Inline function 'io.ktor.util.logging.trace' call
    var this_2 = get_LOGGER();
    if (get_isTraceEnabled(this_2)) {
      var tmp$ret$16 = 'None of the registered converters match response with Content-Type=' + responseContentType.toString() + '. ' + ('Skipping ContentNegotiation for ' + requestUrl.toString() + '.');
      this_2.p2g(tmp$ret$16);
    }
    return null;
  } else {
    tmp_0 = tmp0_elvis_lhs;
  }
  var suitableConverters = tmp_0;
  var result = yield* deserialize(suitableConverters, body, info, charset, $completion);
  var tmp_1;
  if (get_isTraceEnabled(get_LOGGER())) {
    tmp_1 = !isInterface(result, ByteReadChannel);
  } else {
    tmp_1 = false;
  }
  if (tmp_1) {
    get_LOGGER().p2g('Response body was converted to ' + toString(getKClassFromExpression(result)) + ' for ' + requestUrl.toString() + '.');
  }
  return result;
}
function ContentNegotiation$lambda$slambda($registrations, $this_createClientPlugin, $ignoredTypes) {
  return constructCallableReference(($this$transformRequestBody, request, body, _unused_var__etf5q3, $completion) => orPromise($completion, function *($completion) {
    return yield* /*#__NOINLINE__*/invoke$convertRequest($registrations, $this_createClientPlugin, $ignoredTypes, request, body, $completion);
  }), 4);
}
function ContentNegotiation$lambda$slambda_0($ignoredTypes, $registrations, $this_createClientPlugin) {
  return constructCallableReference(($this$transformResponseBody, response, body, info, $completion) => orPromise($completion, function *($completion) {
    var tmp0_elvis_lhs = contentType_0(response);
    var tmp;
    if (tmp0_elvis_lhs == null) {
      return null;
    } else {
      tmp = tmp0_elvis_lhs;
    }
    var contentType = tmp;
    var tmp1_elvis_lhs = charset(contentType);
    var charset_0 = tmp1_elvis_lhs == null ? Charsets_getInstance().q28_1 : tmp1_elvis_lhs;
    return yield* /*#__NOINLINE__*/invoke$convertResponse($ignoredTypes, $registrations, $this_createClientPlugin, get_request(response).h37(), info, body, contentType, charset_0, $completion);
  }), 4);
}
var properties_initialized_ContentNegotiation_kt_1ayduy;
function _init_properties_ContentNegotiation_kt__o183go() {
  if (!properties_initialized_ContentNegotiation_kt_1ayduy) {
    properties_initialized_ContentNegotiation_kt_1ayduy = true;
    supportedSuffixTypes = mapOf([to(Application_getInstance().p2i_1, 'json'), to(Application_getInstance().v2i_1, 'xml'), to(ContentType.h2i('application', 'fastinfoset'), 'fastinfoset'), to(ContentType.h2i('application', 'vnd.wap.wbxml'), 'wbxml'), to(Application_getInstance().y2i_1, 'zip')]);
    LOGGER = KtorSimpleLogger('io.ktor.client.plugins.contentnegotiation.ContentNegotiation');
    DefaultCommonIgnoredTypes = setOf([PrimitiveClasses_getInstance().pf(), PrimitiveClasses_getInstance().lf(), getKClass(HttpStatusCode), getKClass(ByteReadChannel), getKClass(OutgoingContent), getKClass(ClientSSESession), getKClass(ClientSSESessionWithDeserialization)]);
    // Inline function 'io.ktor.util.AttributeKey' call
    var name = 'ExcludedContentTypesAttr';
    // Inline function 'io.ktor.util.reflect.typeInfo' call
    var tmp = getKClass(KtList);
    var tmp_0 = !(null == null) ? isInterface(null, KtList) : false;
    // Inline function 'io.ktor.util.reflect.typeOfOrNull' call
    var tmp_1;
    try {
      tmp_1 = createKType(getKClass(KtList), arrayOf([createInvariantKTypeProjection(createKType(getKClass(ContentType), arrayOf([]), false))]), false);
    } catch ($p) {
      var tmp_2;
      if ($p instanceof Error) {
        var _unused_var__etf5q3 = $p;
        tmp_2 = null;
      } else {
        throw $p;
      }
      tmp_1 = tmp_2;
    }
    var tmp$ret$2 = tmp_1;
    var tmp$ret$1 = new TypeInfo(tmp, tmp_0, tmp$ret$2);
    ExcludedContentTypes = new AttributeKey(name, tmp$ret$1);
    var tmp_3 = ContentNegotiationConfig$_init_$ref_1ne3ob();
    ContentNegotiation = createClientPlugin('ContentNegotiation', tmp_3, ContentNegotiation$lambda);
  }
}
function get_DefaultIgnoredTypes() {
  _init_properties_DefaultIgnoredTypes_nonJvm_kt__d0t56u();
  return DefaultIgnoredTypes;
}
var DefaultIgnoredTypes;
var properties_initialized_DefaultIgnoredTypes_nonJvm_kt_bmyzac;
function _init_properties_DefaultIgnoredTypes_nonJvm_kt__d0t56u() {
  if (!properties_initialized_DefaultIgnoredTypes_nonJvm_kt_bmyzac) {
    properties_initialized_DefaultIgnoredTypes_nonJvm_kt_bmyzac = true;
    // Inline function 'kotlin.collections.mutableSetOf' call
    DefaultIgnoredTypes = LinkedHashSet.m2();
  }
}
//region block: post-declaration
initMetadataForClass(ConverterRegistration, 'ConverterRegistration');
initMetadataForClass(ContentNegotiationConfig$defaultMatcher$1);
protoOf(ContentNegotiationConfig).a34 = register$default;
initMetadataForClass(ContentNegotiationConfig, 'ContentNegotiationConfig', ContentNegotiationConfig, VOID, [Configuration]);
initMetadataForInterface(ContentTypeMergeStrategy, 'ContentTypeMergeStrategy');
initMetadataForClass(sam$io_ktor_client_plugins_contentnegotiation_ContentTypeMergeStrategy$0, 'sam$io_ktor_client_plugins_contentnegotiation_ContentTypeMergeStrategy$0', VOID, VOID, [ContentTypeMergeStrategy, FunctionAdapter]);
initMetadataForClass(sam$io_ktor_client_plugins_contentnegotiation_ContentTypeMergeStrategy$0_0, 'sam$io_ktor_client_plugins_contentnegotiation_ContentTypeMergeStrategy$0', VOID, VOID, [ContentTypeMergeStrategy, FunctionAdapter]);
initMetadataForCompanion(Companion);
initMetadataForClass(ContentConverterException, 'ContentConverterException');
//endregion
//region block: exports
export {
  get_ContentNegotiation as get_ContentNegotiationcp59ye9rorjc,
};
//endregion

//# sourceMappingURL=ktor-ktor-client-content-negotiation.mjs.map
