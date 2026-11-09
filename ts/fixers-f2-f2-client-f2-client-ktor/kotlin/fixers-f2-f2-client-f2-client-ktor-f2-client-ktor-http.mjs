import {
  THROW_CCE2g6jy02ryeudk as THROW_CCE,
  isInterface3d6p8outrmvmk as isInterface,
  Unit_instance3kz1zqli6gr1r as Unit_instance,
  orPromise1iucc2to2udt2 as orPromise,
  constructCallableReference23y65rf941mch as constructCallableReference,
  KtList3hktaavzmj137 as KtList,
  getKClass3t8tygqu4lcxf as getKClass,
  VOID3gxj6tk5isa35 as VOID,
  listOfvhqybd2zx248 as listOf,
  $3l5vvh8yg0ier as $,
  equals2au1ep9vhcato as equals,
  FunctionAdapter3lcrrz3moet5b as FunctionAdapter,
  hashCodeq5arwsb9dgti as hashCode,
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
  arrayOf1akklvh2at202 as arrayOf,
  createKType31ecntyyaay3k as createKType,
  CancellationException3b36o9qz53rgr as CancellationException,
  Collection1k04j3hzsbod0 as Collection,
  ArrayList3it5z8td81qkl as ArrayList,
  copyToArray2j022khrow2yi as copyToArray,
  to2cs3ny02qtbcb as to,
  plus2lr02ok6jhhxu as plus,
  isBlank1dvkhjjvox3p0 as isBlank,
  initMetadataForInterface1egvbzx539z91 as initMetadataForInterface,
  mapOf2zpbbmyqk8xpf as mapOf,
  toString30pk9tzaqopn as toString,
  toString1pkumu07cwy4m as toString_0,
  createThis2j2avj17cvnv2 as createThis,
} from './kotlin-kotlin-stdlib.mjs';
import {
  toList34jp97o2ftj8p as toList,
  FlowCollector26clgpmzihvke as FlowCollector,
  Flow3375h4584bu3w as Flow,
  flow3tazazxj2t7g4 as flow,
  asFlow3ngsnn5xpz8pw as asFlow,
} from './kotlinx-coroutines-core.mjs';
import {
  F2Supplier337j3xv7pmrtg as F2Supplier,
  F2Function3an4847jcvd25 as F2Function,
  F2Consumer3qfli68gqmfcb as F2Consumer,
} from './fixers-f2-f2-dsl-f2-dsl-function.mjs';
import {
  HttpRequestBuilder15f2nnx9sjuv1 as HttpRequestBuilder,
  url2l6iw5ri21nxb as url,
  HttpStatement3zxb33q8lku as HttpStatement,
  bodyjegubbpnklge as body,
  setBody2ib1mt19187tm as setBody,
  MultiPartFormDataContent67pixgga9hu4 as MultiPartFormDataContent,
  bodyAsText193cd91fn5h1t as bodyAsText,
  ResponseException2ofl6x4wye9sn as ResponseException,
  FormBuilder2h86ly8mj760k as FormBuilder,
  formData16t43gxqji6d8 as formData,
  FormPartr3248ghp0ep0 as FormPart,
  ChannelProvidereiny599tk35h as ChannelProvider,
  Js_instance3n5zt8zzbodgt as Js_instance,
  HttpClient2x7qc5z8fmeal as HttpClient,
} from './ktor-ktor-client-core.mjs';
import {
  Companion_getInstance29j9s2wnmk34z as Companion_getInstance,
  isSuccess1pokn37fdu56v as isSuccess,
  Application_getInstance3vhi2qmce4cj0 as Application_getInstance,
  contentType1lwcfjsjo0z8g as contentType,
  OutgoingContent3t2ohmyam9o76 as OutgoingContent,
  NullBody_instance22vwyocs8eppk as NullBody_instance,
  Companion_getInstance72dans54qq1a as Companion_getInstance_0,
  HeadersBuilder3h7sn3kkvu98m as HeadersBuilder,
} from './ktor-ktor-http.mjs';
import { TypeInfo2nbxsuf4v8os2 as TypeInfo } from './ktor-ktor-utils.mjs';
import {
  get_F2DefaultJson2dz3s4djcnt9b as get_F2DefaultJson,
  applyConfigjp8itbzszckf as applyConfig,
} from './fixers-f2-f2-client-f2-client-ktor-f2-client-ktor-common.mjs';
import {
  F2ClientType_HTTP_getInstance1uppk423v5x3a as F2ClientType_HTTP_getInstance,
  F2Client19svmuxijhxn7 as F2Client,
} from './fixers-f2-f2-client-f2-client-core.mjs';
import {
  F2Exception1pktwbv7zijzw as F2Exception,
  F2Errorftgm09bnighg as F2Error,
  Companion_instancetosfn6crhoya as Companion_instance,
} from './fixers-f2-f2-dsl-f2-dsl-cqrs.mjs';
import {
  serializer1hwzc6m64v1op as serializer,
  KSerializerzf77vz1967fq as KSerializer,
  SerializationExceptioneqrdve3ts2n9 as SerializationException,
} from './kotlinx-serialization-kotlinx-serialization-core.mjs';
import {
  JsonObjectee06ihoeeiqj as JsonObject,
  JsonPrimitive3ttzjh2ft5dnx as JsonPrimitive,
  JsonNull2liwjj96vm0w2 as JsonNull,
  get_intOrNulld29i64b3udf as get_intOrNull,
  JsonPrimitive2fp8648nd60dn as JsonPrimitive_0,
} from './kotlinx-serialization-kotlinx-serialization-json.mjs';
import { ByteReadChannel1cb89sbyipkce as ByteReadChannel } from './ktor-ktor-io.mjs';
//region block: imports
var imul = Math.imul;
//endregion
//region block: pre-declaration
class sam$f2_dsl_fnc_F2Supplier$0 {
  constructor(function_0) {
    this.j43_1 = function_0;
  }
  *r20($completion) {
    return yield* $(this.j43_1($completion), $completion);
  }
  wc($completion) {
    return this.r20($completion);
  }
  r4() {
    return this.j43_1;
  }
  equals(other) {
    var tmp;
    if (!(other == null) ? isInterface(other, F2Supplier) : false) {
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
class sam$kotlinx_coroutines_flow_FlowCollector$0 {
  constructor(function_0) {
    this.k43_1 = function_0;
  }
  *g1a(value, $completion) {
    return yield* $(this.k43_1(value, $completion), $completion);
  }
  r4() {
    return this.k43_1;
  }
  equals(other) {
    var tmp;
    if (!(other == null) ? isInterface(other, FlowCollector) : false) {
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
class sam$f2_dsl_fnc_F2Function$0 {
  constructor(function_0) {
    this.l43_1 = function_0;
  }
  *q20(msgs, $completion) {
    return yield* $(this.l43_1(msgs, $completion), $completion);
  }
  xc(p1, $completion) {
    return this.q20((!(p1 == null) ? isInterface(p1, Flow) : false) ? p1 : THROW_CCE(), $completion);
  }
  r4() {
    return this.l43_1;
  }
  equals(other) {
    var tmp;
    if (!(other == null) ? isInterface(other, F2Function) : false) {
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
class sam$f2_dsl_fnc_F2Consumer$0 {
  constructor(function_0) {
    this.m43_1 = function_0;
  }
  *p20(msg, $completion) {
    return yield* $(this.m43_1(msg, $completion), $completion);
  }
  xc(p1, $completion) {
    return this.p20((!(p1 == null) ? isInterface(p1, Flow) : false) ? p1 : THROW_CCE(), $completion);
  }
  r4() {
    return this.m43_1;
  }
  equals(other) {
    var tmp;
    if (!(other == null) ? isInterface(other, F2Consumer) : false) {
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
class sam$kotlinx_coroutines_flow_FlowCollector$0_0 {
  constructor(function_0) {
    this.n43_1 = function_0;
  }
  *g1a(value, $completion) {
    return yield* $(this.n43_1(value, $completion), $completion);
  }
  r4() {
    return this.n43_1;
  }
  equals(other) {
    var tmp;
    if (!(other == null) ? isInterface(other, FlowCollector) : false) {
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
class HttpF2Client$doPost$$inlined$map$1 {
  constructor($this, this$0, $route, $queryTypeInfo) {
    this.o43_1 = $this;
    this.p43_1 = this$0;
    this.q43_1 = $route;
    this.r43_1 = $queryTypeInfo;
  }
  *f1a(collector, $completion) {
    var tmp = HttpF2Client$doPost$o$collect$slambda(collector, this.p43_1, this.q43_1, this.r43_1);
    yield* this.o43_1.d1a(new sam$kotlinx_coroutines_flow_FlowCollector$0_0(tmp), $completion);
    return Unit_instance;
  }
  d1a(collector, $completion) {
    return this.f1a(collector, $completion);
  }
}
class HttpF2Client$doPost$$inlined$map$2 {
  constructor($this, this$0, $route, $queryTypeInfo) {
    this.s43_1 = $this;
    this.t43_1 = this$0;
    this.u43_1 = $route;
    this.v43_1 = $queryTypeInfo;
  }
  *f1a(collector, $completion) {
    var tmp = HttpF2Client$doPost$o$collect$slambda_0(collector, this.t43_1, this.u43_1, this.v43_1);
    yield* this.s43_1.d1a(new sam$kotlinx_coroutines_flow_FlowCollector$0_0(tmp), $completion);
    return Unit_instance;
  }
  d1a(collector, $completion) {
    return this.f1a(collector, $completion);
  }
}
class HttpF2Client {
  constructor(httpClient, urlBase, json) {
    json = json === VOID ? get_F2DefaultJson() : json;
    this.g43_1 = httpClient;
    this.urlBase = urlBase;
    this.h43_1 = json;
    this.i43_1 = F2ClientType_HTTP_getInstance();
  }
  d44() {
    return this.g43_1;
  }
  e44() {
    return this.urlBase;
  }
  w3o() {
    return this.h43_1;
  }
  t2g() {
    return this.i43_1;
  }
  u2g(route, responseTypeInfo) {
    var tmp = HttpF2Client$supplier$slambda(this, responseTypeInfo, route);
    return new sam$f2_dsl_fnc_F2Supplier$0(tmp);
  }
  v2g(route, queryTypeInfo, responseTypeInfo) {
    var tmp = HttpF2Client$function$slambda(this, route, queryTypeInfo, responseTypeInfo);
    return new sam$f2_dsl_fnc_F2Function$0(tmp);
  }
  w2g(route, queryTypeInfo) {
    var tmp = HttpF2Client$consumer$slambda(this, route, queryTypeInfo);
    return new sam$f2_dsl_fnc_F2Consumer$0(tmp);
  }
  get type() {
    return this.t2g();
  }
}
class FormDataBodyBuilder {
  constructor(json, queryTypeInfo) {
    json = json === VOID ? get_F2DefaultJson() : json;
    this.w43_1 = json;
    var tmp = this;
    // Inline function 'kotlin.collections.mutableListOf' call
    tmp.x43_1 = ArrayList.p2();
    var tmp_0 = this;
    var tmp0_safe_receiver = queryTypeInfo.e2g_1;
    var tmp_1;
    if (tmp0_safe_receiver == null) {
      tmp_1 = null;
    } else {
      // Inline function 'kotlin.let' call
      tmp_1 = serializer(tmp0_safe_receiver);
    }
    var tmp_2 = tmp_1;
    tmp_0.y43_1 = (!(tmp_2 == null) ? isInterface(tmp_2, KSerializer) : false) ? tmp_2 : THROW_CCE();
  }
  c44() {
    // Inline function 'io.ktor.client.request.forms.formData' call
    // Inline function 'kotlin.apply' call
    var this_0 = new FormBuilder();
    // Inline function 'kotlin.collections.forEach' call
    var _iterator__ex2g4s = this.x43_1.l1();
    while (_iterator__ex2g4s.m1()) {
      var element = _iterator__ex2g4s.n1();
      this_0.s3j(element);
    }
    // Inline function 'kotlin.collections.toTypedArray' call
    var this_1 = this_0.t3j();
    var tmp$ret$5 = copyToArray(this_1);
    return formData(tmp$ret$5.slice());
  }
  f44(key, value, contentType) {
    var tmp;
    if (contentType == null) {
      tmp = null;
    } else {
      // Inline function 'kotlin.let' call
      // Inline function 'io.ktor.http.Companion.build' call
      Companion_getInstance_0();
      // Inline function 'kotlin.apply' call
      var this_0 = new HeadersBuilder();
      this_0.m2c('Content-Type', contentType);
      tmp = this_0.m2k();
    }
    var tmp1_elvis_lhs = tmp;
    var headers = tmp1_elvis_lhs == null ? Companion_getInstance_0().j2k_1 : tmp1_elvis_lhs;
    var tmp0 = new FormPart(key, value, headers);
    // Inline function 'kotlin.let' call
    this.x43_1.a2(tmp0);
  }
  z43(key, value) {
    this.f44(key, toJson(this, value), 'application/json');
  }
  b44(key, content, filename, contentType) {
    var tmp = new ChannelProvider(VOID, FormDataBodyBuilder$file$lambda(content));
    // Inline function 'io.ktor.http.Companion.build' call
    Companion_getInstance_0();
    // Inline function 'kotlin.apply' call
    var this_0 = new HeadersBuilder();
    this_0.m2c('Content-Disposition', 'filename=' + filename);
    if (contentType == null)
      null;
    else {
      // Inline function 'kotlin.let' call
      this_0.m2c('Content-Type', contentType);
    }
    var tmp$ret$0 = this_0.m2k();
    var tmp0 = new FormPart(key, tmp, tmp$ret$0);
    // Inline function 'kotlin.let' call
    this.x43_1.a2(tmp0);
  }
}
class F2UploadCommand {}
class F2UploadSingleCommand {
  constructor(command, file) {
    this.i44_1 = command;
    this.file = file;
    this.j44_1 = mapOf(to('file', listOf(this.file)));
  }
  g44() {
    return this.i44_1;
  }
  k44() {
    return this.file;
  }
  h44() {
    return this.j44_1;
  }
  cj() {
    return this.i44_1;
  }
  dj() {
    return this.file;
  }
  l44(command, file) {
    return new F2UploadSingleCommand(command, file);
  }
  copy(command, file, $super) {
    command = command === VOID ? this.i44_1 : command;
    file = file === VOID ? this.file : file;
    return $super === VOID ? this.l44(command, file) : $super.l44.call(this, command, file);
  }
  toString() {
    return 'F2UploadSingleCommand(command=' + toString(this.i44_1) + ', file=' + toString_0(this.file) + ')';
  }
  hashCode() {
    var result = this.i44_1 == null ? 0 : hashCode(this.i44_1);
    result = imul(result, 31) + hashCode(this.file) | 0;
    return result;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof F2UploadSingleCommand))
      return false;
    if (!equals(this.i44_1, other.i44_1))
      return false;
    if (!equals(this.file, other.file))
      return false;
    return true;
  }
  get command() {
    return this.g44();
  }
  get fileMap() {
    return this.h44();
  }
}
class F2UploadMultipleCommand {
  constructor(command, files) {
    this.m44_1 = command;
    this.files = files;
    this.n44_1 = mapOf(to('file', this.files));
  }
  g44() {
    return this.m44_1;
  }
  o44() {
    return this.files;
  }
  h44() {
    return this.n44_1;
  }
  cj() {
    return this.m44_1;
  }
  dj() {
    return this.files;
  }
  p44(command, files) {
    return new F2UploadMultipleCommand(command, files);
  }
  copy(command, files, $super) {
    command = command === VOID ? this.m44_1 : command;
    files = files === VOID ? this.files : files;
    return $super === VOID ? this.p44(command, files) : $super.p44.call(this, command, files);
  }
  toString() {
    return 'F2UploadMultipleCommand(command=' + toString(this.m44_1) + ', files=' + toString_0(this.files) + ')';
  }
  hashCode() {
    var result = this.m44_1 == null ? 0 : hashCode(this.m44_1);
    result = imul(result, 31) + hashCode(this.files) | 0;
    return result;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof F2UploadMultipleCommand))
      return false;
    if (!equals(this.m44_1, other.m44_1))
      return false;
    if (!equals(this.files, other.files))
      return false;
    return true;
  }
  get command() {
    return this.g44();
  }
  get fileMap() {
    return this.h44();
  }
}
class F2FilePart {
  constructor(name, content, contentType) {
    return new.target.q44(name, content, contentType);
  }
  static q44(name, content, contentType) {
    contentType = contentType === VOID ? null : contentType;
    var $this = createThis(this);
    $this.name = name;
    $this.a44_1 = content;
    $this.contentType = contentType;
    return $this;
  }
  d1() {
    return this.name;
  }
  i37() {
    return this.a44_1;
  }
  b2v() {
    return this.contentType;
  }
  static fromBytes(name, bytes, contentType) {
    contentType = contentType === VOID ? null : contentType;
    return this.q44(name, ByteReadChannel(bytes), contentType);
  }
}
class HttpClientBuilder {
  constructor(config) {
    var tmp;
    if (config === VOID) {
      tmp = HttpClientBuilder$_init_$lambda_3mg5wj;
    } else {
      tmp = config;
    }
    config = tmp;
    this.r44_1 = config;
  }
  build(urlBase) {
    var tmp0_httpClient = httpClient(this);
    return new HttpF2Client(tmp0_httpClient, urlBase);
  }
}
//endregion
function HttpF2Client$doPost$o$collect$slambda($$this$unsafeFlow, this$0, $route, $queryTypeInfo) {
  return constructCallableReference((value, $completion) => orPromise($completion, function *($completion) {
    var tmp0 = $$this$unsafeFlow;
    var msg = value;
    var tmp$ret$1 = yield* postFormData(this$0, $route, $queryTypeInfo, (!(msg == null) ? isInterface(msg, F2UploadCommand) : false) ? msg : THROW_CCE(), $completion);
    yield* tmp0.g1a(tmp$ret$1, $completion);
    return Unit_instance;
  }), 1);
}
function HttpF2Client$doPost$o$collect$slambda_0($$this$unsafeFlow, this$0, $route, $queryTypeInfo) {
  return constructCallableReference((value, $completion) => orPromise($completion, function *($completion) {
    var tmp0 = $$this$unsafeFlow;
    var msg = value;
    var tmp$ret$1 = yield* postJson(this$0, $route, $queryTypeInfo, msg, $completion);
    yield* tmp0.g1a(tmp$ret$1, $completion);
    return Unit_instance;
  }), 1);
}
function *doPost($this, route, queryTypeInfo, messages, $completion) {
  var isListType = queryTypeInfo.c2g_1.equals(getKClass(KtList));
  var isF2UploadCommand = queryTypeInfo.c2g_1.equals(getKClass(F2UploadCommand));
  var tmp;
  if (isF2UploadCommand) {
    // Inline function 'kotlinx.coroutines.flow.map' call
    // Inline function 'kotlinx.coroutines.flow.unsafeTransform' call
    // Inline function 'kotlinx.coroutines.flow.internal.unsafeFlow' call
    var tmp$ret$0 = new HttpF2Client$doPost$$inlined$map$1(messages, $this, route, queryTypeInfo);
    tmp = yield* toList(tmp$ret$0, VOID, $completion);
  } else if (isListType) {
    tmp = listOf(yield* postJson_0($this, route, queryTypeInfo, messages, $completion));
  } else {
    // Inline function 'kotlinx.coroutines.flow.map' call
    // Inline function 'kotlinx.coroutines.flow.unsafeTransform' call
    // Inline function 'kotlinx.coroutines.flow.internal.unsafeFlow' call
    var tmp$ret$3 = new HttpF2Client$doPost$$inlined$map$2(messages, $this, route, queryTypeInfo);
    tmp = yield* toList(tmp$ret$3, VOID, $completion);
  }
  return tmp;
}
function *postJson($this, route, queryTypeInfo, message, $completion) {
  return yield* send(HttpF2Client$postJson$slambda($this, route, message, queryTypeInfo), $completion);
}
function *postJson_0($this, route, queryTypeInfo, messages, $completion) {
  return yield* send(HttpF2Client$postJson$slambda_0($this, route, messages, queryTypeInfo), $completion);
}
function *postFormData($this, route, queryTypeInfo, message, $completion) {
  return yield* send(HttpF2Client$postFormData$slambda($this, route, queryTypeInfo, message), $completion);
}
function buildUrl($this, route) {
  return $this.urlBase + '/' + route;
}
function HttpF2Client$supplier$slambda$slambda(this$0, $route) {
  return constructCallableReference(($completion) => orPromise($completion, function *($completion) {
    var tmp0 = this$0.g43_1;
    // Inline function 'io.ktor.client.request.get' call
    var urlString = buildUrl(this$0, $route);
    // Inline function 'io.ktor.client.request.get' call
    // Inline function 'kotlin.apply' call
    var this_0 = new HttpRequestBuilder();
    url(this_0, urlString);
    // Inline function 'io.ktor.client.request.get' call
    this_0.i36_1 = Companion_getInstance().j2p_1;
    // Inline function 'io.ktor.client.request.request' call
    return yield* (new HttpStatement(this_0, tmp0)).y3k($completion);
  }), 0);
}
function HttpF2Client$supplier$slambda$slambda_0($response, $this, $typeInfo) {
  return constructCallableReference(($this$flow, $completion) => orPromise($completion, function *($completion) {
    var tmp;
    if (!isSuccess($response.p37())) {
      throw yield* responseError($this.h43_1, $response, $completion);
    }
    yield* emitPayload($this$flow, yield* body($response, $typeInfo, $completion), $completion);
    return Unit_instance;
  }), 1);
}
function HttpF2Client$supplier$slambda(this$0, $responseTypeInfo, $route) {
  return constructCallableReference(($completion) => orPromise($completion, function *($completion) {
    // Inline function 'kotlin.let' call
    var response = yield* send(HttpF2Client$supplier$slambda$slambda(this$0, $route), $completion);
    var tmp0 = this$0;
    // Inline function 'f2.client.ktor.http.HttpF2Client.handlePayloadResponse' call
    var typeInfo = $responseTypeInfo;
    return flow(HttpF2Client$supplier$slambda$slambda_0(response, tmp0, typeInfo));
  }), 0);
}
function HttpF2Client$function$slambda$slambda$slambda($this_flow, this$0, $responseTypeInfo) {
  return constructCallableReference((value, $completion) => orPromise($completion, function *($completion) {
    var tmp0 = $this_flow;
    var response = value;
    if (!isSuccess(response.p37())) {
      throw yield* responseError(this$0.h43_1, response, $completion);
    }
    yield* emitPayload(tmp0, yield* body(response, $responseTypeInfo, $completion), $completion);
    return Unit_instance;
  }), 1);
}
function HttpF2Client$function$slambda$slambda($this, this$0, $responseTypeInfo) {
  return constructCallableReference(($this$flow, $completion) => orPromise($completion, function *($completion) {
    var tmp = HttpF2Client$function$slambda$slambda$slambda($this$flow, this$0, $responseTypeInfo);
    yield* $this.d1a(new sam$kotlinx_coroutines_flow_FlowCollector$0(tmp), $completion);
    return Unit_instance;
  }), 1);
}
function HttpF2Client$function$slambda(this$0, $route, $queryTypeInfo, $responseTypeInfo) {
  return constructCallableReference((messageFlow, $completion) => orPromise($completion, function *($completion) {
    // Inline function 'kotlinx.coroutines.flow.transform' call
    var this_0 = asFlow(yield* /*#__NOINLINE__*/doPost(this$0, $route, $queryTypeInfo, messageFlow, $completion));
    return flow(HttpF2Client$function$slambda$slambda(this_0, this$0, $responseTypeInfo));
  }), 1);
}
function HttpF2Client$consumer$slambda(this$0, $route, $queryTypeInfo) {
  return constructCallableReference((messages, $completion) => orPromise($completion, function *($completion) {
    var tmp0 = yield* /*#__NOINLINE__*/doPost(this$0, $route, $queryTypeInfo, messages, $completion);
    var tmp$ret$0;
    $l$block: {
      // Inline function 'kotlin.collections.firstOrNull' call
      var _iterator__ex2g4s = tmp0.l1();
      while (_iterator__ex2g4s.m1()) {
        var element = _iterator__ex2g4s.n1();
        if (!isSuccess(element.p37())) {
          tmp$ret$0 = element;
          break $l$block;
        }
      }
      tmp$ret$0 = null;
    }
    var tmp0_safe_receiver = tmp$ret$0;
    if (tmp0_safe_receiver == null)
      null;
    else {
      // Inline function 'kotlin.let' call
      throw yield* responseError(this$0.h43_1, tmp0_safe_receiver, $completion);
    }
    return Unit_instance;
  }), 1);
}
function HttpF2Client$postJson$slambda(this$0, $route, $message, $queryTypeInfo) {
  return constructCallableReference(($completion) => orPromise($completion, function *($completion) {
    var tmp0 = this$0.g43_1;
    // Inline function 'io.ktor.client.request.post' call
    var urlString = buildUrl(this$0, $route);
    // Inline function 'io.ktor.client.request.post' call
    var builder = new HttpRequestBuilder();
    builder.i36_1 = Companion_getInstance().k2p_1;
    url(builder, urlString);
    contentType(builder, Application_getInstance().p2i_1);
    setBody(builder, $message, $queryTypeInfo);
    builder.i36_1 = Companion_getInstance().k2p_1;
    // Inline function 'io.ktor.client.request.request' call
    return yield* (new HttpStatement(builder, tmp0)).y3k($completion);
  }), 0);
}
function HttpF2Client$postJson$slambda_0(this$0, $route, $messages, $queryTypeInfo) {
  return constructCallableReference(($completion) => orPromise($completion, function *($completion) {
    var tmp0 = this$0.g43_1;
    // Inline function 'io.ktor.client.request.post' call
    var urlString = buildUrl(this$0, $route);
    // Inline function 'io.ktor.client.request.post' call
    var builder = new HttpRequestBuilder();
    builder.i36_1 = Companion_getInstance().k2p_1;
    url(builder, urlString);
    contentType(builder, Application_getInstance().p2i_1);
    setBody(builder, yield* toList($messages, VOID, $completion), $queryTypeInfo);
    builder.i36_1 = Companion_getInstance().k2p_1;
    // Inline function 'io.ktor.client.request.request' call
    return yield* (new HttpStatement(builder, tmp0)).y3k($completion);
  }), 0);
}
function HttpF2Client$postFormData$slambda(this$0, $route, $queryTypeInfo, $message) {
  return constructCallableReference(($completion) => orPromise($completion, function *($completion) {
    var tmp0 = this$0.g43_1;
    var tmp2 = buildUrl(this$0, $route);
    // Inline function 'kotlin.apply' call
    var this_0 = new FormDataBodyBuilder(this$0.h43_1, $queryTypeInfo);
    this_0.z43('command', $message.command);
    // Inline function 'kotlin.collections.forEach' call
    // Inline function 'kotlin.collections.iterator' call
    var _iterator__ex2g4s = $message.fileMap.u2().l1();
    while (_iterator__ex2g4s.m1()) {
      var element = _iterator__ex2g4s.n1();
      // Inline function 'kotlin.collections.component1' call
      var key = element.v2();
      // Inline function 'kotlin.collections.component2' call
      var files = element.w2();
      // Inline function 'kotlin.collections.forEach' call
      var _iterator__ex2g4s_0 = files.l1();
      while (_iterator__ex2g4s_0.m1()) {
        var element_0 = _iterator__ex2g4s_0.n1();
        this_0.b44(key, element_0.a44_1, element_0.name, element_0.contentType);
      }
    }
    // Inline function 'io.ktor.client.request.forms.submitFormWithBinaryData' call
    // Inline function 'io.ktor.client.request.forms.submitFormWithBinaryData' call
    var formData = this_0.c44();
    // Inline function 'io.ktor.client.request.request' call
    // Inline function 'kotlin.apply' call
    var this_1 = new HttpRequestBuilder();
    this_1.i36_1 = Companion_getInstance().k2p_1;
    // Inline function 'io.ktor.client.request.setBody' call
    var body = new MultiPartFormDataContent(formData);
    if (body == null) {
      this_1.k36_1 = NullBody_instance;
      // Inline function 'io.ktor.util.reflect.typeInfo' call
      var tmp = getKClass(MultiPartFormDataContent);
      var tmp_0 = null instanceof MultiPartFormDataContent;
      // Inline function 'io.ktor.util.reflect.typeOfOrNull' call
      var tmp_1;
      try {
        tmp_1 = createKType(getKClass(MultiPartFormDataContent), arrayOf([]), false);
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
      var tmp$ret$16 = tmp_1;
      var tmp$ret$15 = new TypeInfo(tmp, tmp_0, tmp$ret$16);
      this_1.b3a(tmp$ret$15);
    } else {
      if (body instanceof OutgoingContent) {
        this_1.k36_1 = body;
        this_1.b3a(null);
      } else {
        this_1.k36_1 = body;
        // Inline function 'io.ktor.util.reflect.typeInfo' call
        var tmp_3 = getKClass(MultiPartFormDataContent);
        var tmp_4 = null instanceof MultiPartFormDataContent;
        // Inline function 'io.ktor.util.reflect.typeOfOrNull' call
        var tmp_5;
        try {
          tmp_5 = createKType(getKClass(MultiPartFormDataContent), arrayOf([]), false);
        } catch ($p_0) {
          var tmp_6;
          if ($p_0 instanceof Error) {
            var _unused_var__etf5q3_0 = $p_0;
            tmp_6 = null;
          } else {
            throw $p_0;
          }
          tmp_5 = tmp_6;
        }
        var tmp$ret$18 = tmp_5;
        var tmp$ret$17 = new TypeInfo(tmp_3, tmp_4, tmp$ret$18);
        this_1.b3a(tmp$ret$17);
      }
    }
    url(this_1, tmp2);
    // Inline function 'io.ktor.client.request.request' call
    return yield* (new HttpStatement(this_1, tmp0)).y3k($completion);
  }), 0);
}
function *responseError(_this__u8e3s4, response, $completion) {
  var status = response.p37().d2q_1;
  var tmp;
  try {
    tmp = yield* bodyAsText(response, VOID, $completion);
  } catch ($p) {
    var tmp_0;
    if ($p instanceof CancellationException) {
      var e = $p;
      throw e;
    } else {
      if ($p instanceof Error) {
        var e_0 = $p;
        // Inline function 'kotlin.text.orEmpty' call
        var tmp0_elvis_lhs = e_0.message;
        var tmp$ret$0 = tmp0_elvis_lhs == null ? '' : tmp0_elvis_lhs;
        return F2Exception.e41(new F2Error(tmp$ret$0, VOID, VOID, status), e_0);
      } else {
        throw $p;
      }
    }
  }
  var body = tmp;
  return F2Exception.e41(decodeError(_this__u8e3s4, body, status));
}
function *emitPayload(_this__u8e3s4, result, $completion) {
  if (!(result == null) ? isInterface(result, Collection) : false) {
    // Inline function 'kotlin.collections.forEach' call
    var _iterator__ex2g4s = (isInterface(result, Collection) ? result : THROW_CCE()).l1();
    while (_iterator__ex2g4s.m1()) {
      var element = _iterator__ex2g4s.n1();
      yield* _this__u8e3s4.g1a(element, $completion);
    }
  } else {
    yield* _this__u8e3s4.g1a(result, $completion);
  }
  return Unit_instance;
}
function *send(request, $completion) {
  var tmp;
  try {
    tmp = yield* $(request($completion), $completion);
  } catch ($p) {
    var tmp_0;
    if ($p instanceof ResponseException) {
      var e = $p;
      if (isSuccess(e.j3b_1.p37()))
        throw e;
      tmp_0 = e.j3b_1;
    } else {
      throw $p;
    }
    tmp = tmp_0;
  }
  return tmp;
}
function toJson($this, $receiver) {
  return $this.w43_1.t1e($this.y43_1, $receiver);
}
function FormDataBodyBuilder$file$lambda($content) {
  return () => $content;
}
function decodeError(_this__u8e3s4, body, status) {
  var tmp;
  try {
    var tmp_0 = _this__u8e3s4.s3m(body);
    tmp = tmp_0 instanceof JsonObject ? tmp_0 : null;
  } catch ($p) {
    var tmp_1;
    if ($p instanceof SerializationException) {
      var _unused_var__etf5q3 = $p;
      tmp_1 = null;
    } else {
      throw $p;
    }
    tmp = tmp_1;
  }
  var structured = tmp;
  return structured == null ? new F2Error(body, VOID, VOID, status) : decodeError_0(_this__u8e3s4, structured, body, status);
}
function decodeError_0(_this__u8e3s4, structured, body, status) {
  var tmp = structured.o2b('code');
  var tmp0_safe_receiver = tmp instanceof JsonPrimitive ? tmp : null;
  var tmp_0;
  if (tmp0_safe_receiver == null) {
    tmp_0 = null;
  } else {
    // Inline function 'kotlin.takeUnless' call
    var tmp_1;
    if (!(tmp0_safe_receiver instanceof JsonNull)) {
      tmp_1 = tmp0_safe_receiver;
    } else {
      tmp_1 = null;
    }
    tmp_0 = tmp_1;
  }
  var rawCode = tmp_0;
  var tmp2_elvis_lhs = rawCode == null ? null : get_intOrNull(rawCode);
  var code = tmp2_elvis_lhs == null ? status : tmp2_elvis_lhs;
  var tmp_2;
  try {
    tmp_2 = _this__u8e3s4.r3m(Companion_instance.b3p(), new JsonObject(plus(structured, to('code', JsonPrimitive_0(code)))));
  } catch ($p) {
    var tmp_3;
    if ($p instanceof SerializationException) {
      var _unused_var__etf5q3 = $p;
      return new F2Error(body, VOID, VOID, code);
    } else {
      throw $p;
    }
  }
  var decoded = tmp_2;
  var tmp_4;
  if (rawCode == null) {
    tmp_4 = null;
  } else {
    // Inline function 'kotlin.takeIf' call
    var tmp_5;
    if (get_intOrNull(rawCode) == null) {
      tmp_5 = rawCode;
    } else {
      tmp_5 = null;
    }
    tmp_4 = tmp_5;
  }
  var tmp4_safe_receiver = tmp_4;
  var tmp5_safe_receiver = tmp4_safe_receiver == null ? null : tmp4_safe_receiver.i37();
  var tmp_6;
  if (tmp5_safe_receiver == null) {
    tmp_6 = null;
  } else {
    // Inline function 'kotlin.takeIf' call
    var tmp_7;
    // Inline function 'kotlin.text.isNotBlank' call
    if (!isBlank(tmp5_safe_receiver)) {
      tmp_7 = tmp5_safe_receiver;
    } else {
      tmp_7 = null;
    }
    tmp_6 = tmp_7;
  }
  var unreadCode = tmp_6;
  return unreadCode == null ? decoded : new F2Error(decoded.message + ' (code ' + unreadCode + ')', decoded.id, decoded.timestamp, decoded.code, decoded.requestId);
}
function httpClient($this) {
  var tmp = Js_instance;
  return HttpClient(tmp, HttpClientBuilder$httpClient$lambda($this));
}
function HttpClientBuilder$_init_$lambda_3mg5wj(_this__u8e3s4) {
  return Unit_instance;
}
function HttpClientBuilder$httpClient$lambda(this$0) {
  return ($this$HttpClient) => {
    applyConfig($this$HttpClient, this$0.r44_1);
    return Unit_instance;
  };
}
function httpClientBuilderDefault() {
  return new HttpClientBuilder();
}
function httpClientBuilderGenerics(config) {
  config = config === VOID ? null : config;
  return new HttpClientBuilder(config);
}
//region block: post-declaration
initMetadataForClass(sam$f2_dsl_fnc_F2Supplier$0, 'sam$f2_dsl_fnc_F2Supplier$0', VOID, VOID, [F2Supplier, FunctionAdapter], [0]);
initMetadataForClass(sam$kotlinx_coroutines_flow_FlowCollector$0, 'sam$kotlinx_coroutines_flow_FlowCollector$0', VOID, VOID, [FlowCollector, FunctionAdapter], [1]);
initMetadataForClass(sam$f2_dsl_fnc_F2Function$0, 'sam$f2_dsl_fnc_F2Function$0', VOID, VOID, [F2Function, FunctionAdapter], [1]);
initMetadataForClass(sam$f2_dsl_fnc_F2Consumer$0, 'sam$f2_dsl_fnc_F2Consumer$0', VOID, VOID, [F2Consumer, FunctionAdapter], [1]);
initMetadataForClass(sam$kotlinx_coroutines_flow_FlowCollector$0_0, 'sam$kotlinx_coroutines_flow_FlowCollector$0', VOID, VOID, [FlowCollector, FunctionAdapter], [1]);
initMetadataForClass(HttpF2Client$doPost$$inlined$map$1, VOID, VOID, VOID, [Flow], [1]);
initMetadataForClass(HttpF2Client$doPost$$inlined$map$2, VOID, VOID, VOID, [Flow], [1]);
initMetadataForClass(HttpF2Client, 'HttpF2Client', VOID, VOID, [F2Client], [2, 3]);
initMetadataForClass(FormDataBodyBuilder, 'FormDataBodyBuilder');
initMetadataForInterface(F2UploadCommand, 'F2UploadCommand');
initMetadataForClass(F2UploadSingleCommand, 'F2UploadSingleCommand', VOID, VOID, [F2UploadCommand]);
initMetadataForClass(F2UploadMultipleCommand, 'F2UploadMultipleCommand', VOID, VOID, [F2UploadCommand]);
initMetadataForClass(F2FilePart, 'F2FilePart');
initMetadataForClass(HttpClientBuilder, 'HttpClientBuilder', HttpClientBuilder);
//endregion
//region block: exports
export {
  HttpF2Client as HttpF2Client,
  F2UploadSingleCommand as F2UploadSingleCommand,
  F2UploadMultipleCommand as F2UploadMultipleCommand,
  F2FilePart as F2FilePart,
  HttpClientBuilder as HttpClientBuilder,
  httpClientBuilderDefault as httpClientBuilderDefault,
};
export {
  httpClientBuilderDefault as httpClientBuilderDefault3fuy994a7lxld,
  httpClientBuilderGenerics as httpClientBuilderGenerics3w104dkjbc3g3,
};
//endregion

//# sourceMappingURL=fixers-f2-f2-client-f2-client-ktor-f2-client-ktor-http.mjs.map
