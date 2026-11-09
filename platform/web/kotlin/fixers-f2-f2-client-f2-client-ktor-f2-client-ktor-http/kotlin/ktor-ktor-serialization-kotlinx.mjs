import {
  ArrayList3it5z8td81qkl as ArrayList,
  Unit_instance3kz1zqli6gr1r as Unit_instance,
  orPromise1iucc2to2udt2 as orPromise,
  constructCallableReference23y65rf941mch as constructCallableReference,
  toString1pkumu07cwy4m as toString,
  IllegalStateExceptionkoljg5n0nrlr as IllegalStateException,
  THROW_CCE2g6jy02ryeudk as THROW_CCE,
  isInterface3d6p8outrmvmk as isInterface,
  $3l5vvh8yg0ier as $,
  equals2au1ep9vhcato as equals,
  FunctionAdapter3lcrrz3moet5b as FunctionAdapter,
  hashCodeq5arwsb9dgti as hashCode,
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
  VOID3gxj6tk5isa35 as VOID,
  IllegalArgumentException2asla15b5jaob as IllegalArgumentException,
  getKClassFromExpression348iqjl4fnx2f as getKClassFromExpression,
  KtMap140uvy3s5zad8 as KtMap,
  KtSetjrjc7fhfd6b9 as KtSet,
  firstOrNull1gk7vzkf4h3nq as firstOrNull,
  StringCompanionObject_instance2dx16b5pp3v3z as StringCompanionObject_instance,
  isArray1hxjqtqy632bc as isArray,
  KtList3hktaavzmj137 as KtList,
  Exceptiondt2hlxn7j7vw as Exception,
  joinToString1cxrrlmo0chqs as joinToString,
  to2cs3ny02qtbcb as to,
  filterNotNull3qfgcwmxhwfxe as filterNotNull,
  collectionSizeOrDefault36dulx8yinfqm as collectionSizeOrDefault,
  HashSet2dzve9y63nf0v as HashSet,
  singleOrNullrknfaxokm1sl as singleOrNull,
  Collection1k04j3hzsbod0 as Collection,
  KClass1cc9rfeybg8hs as KClass,
  emptyList1g2z5xcrvp2zy as emptyList,
} from './kotlin-kotlin-stdlib.mjs';
import {
  KSerializerzf77vz1967fq as KSerializer,
  BinaryFormat3f3aelhmz0ro1 as BinaryFormat,
  StringFormat2r2ka8mzcb3mi as StringFormat,
  SerializationExceptioneqrdve3ts2n9 as SerializationException,
  serializerOrNull31x2b6nu6gruj as serializerOrNull,
  serializer1rka18p0rjk4x as serializer,
  MapSerializer11kmegt3g5c1g as MapSerializer,
  SetSerializert3lb0yy9iftr as SetSerializer,
  serializer1x79l67jvwntn as serializer_0,
  ListSerializer1hxuk9dx5n9du as ListSerializer,
  get_nullable197rfua9r7fsz as get_nullable,
} from './kotlinx-serialization-kotlinx-serialization-core.mjs';
import {
  ByteArrayContent9zol65b22hp0 as ByteArrayContent,
  withCharsetIfNeeded3sz33ys0x9vfx as withCharsetIfNeeded,
  TextContent1rb6ftlpvl1d2 as TextContent,
} from './ktor-ktor-http.mjs';
import {
  FlowCollector26clgpmzihvke as FlowCollector,
  Flow3375h4584bu3w as Flow,
  asFlow3ngsnn5xpz8pw as asFlow,
  firstOrNull1sz36fdcyca21 as firstOrNull_0,
} from './kotlinx-coroutines-core.mjs';
import { KotlinxSerializerJsonSchemaInference2pgk3rid93x2d as KotlinxSerializerJsonSchemaInference } from './ktor-ktor-openapi-schema.mjs';
import {
  readBuffery45c0omfxhxc as readBuffer,
  ByteReadChannel2ndu7k90iil3s as ByteReadChannel,
  discard3ugntd47xyll6 as discard,
  readText27783kyxjxi1g as readText,
} from './ktor-ktor-io.mjs';
import { readByteArray1ri21h2rciakw as readByteArray } from './kotlinx-io-kotlinx-io-core.mjs';
import { JsonConvertExceptiongnc5x6xwaf77 as JsonConvertException } from './ktor-ktor-serialization.mjs';
//region block: imports
//endregion
//region block: pre-declaration
class sam$kotlinx_coroutines_flow_FlowCollector$0 {
  constructor(function_0) {
    this.w3y_1 = function_0;
  }
  *m29(value, $completion) {
    return yield* $(this.w3y_1(value, $completion), $completion);
  }
  r4() {
    return this.w3y_1;
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
class KotlinxSerializationConverter$serialize$$inlined$map$1 {
  constructor($this, $contentType, $charset, $typeInfo, $value) {
    this.x3y_1 = $this;
    this.y3y_1 = $contentType;
    this.z3y_1 = $charset;
    this.a3z_1 = $typeInfo;
    this.b3z_1 = $value;
  }
  *l29(collector, $completion) {
    var tmp = KotlinxSerializationConverter$serialize$o$collect$slambda(collector, this.y3y_1, this.z3y_1, this.a3z_1, this.b3z_1);
    yield* this.x3y_1.j29(new sam$kotlinx_coroutines_flow_FlowCollector$0(tmp), $completion);
    return Unit_instance;
  }
  j29(collector, $completion) {
    return this.l29(collector, $completion);
  }
}
class KotlinxSerializationConverter {
  constructor(format) {
    this.c3z_1 = format;
    this.d3z_1 = extensions(this.c3z_1);
    this.e3z_1 = new KotlinxSerializerJsonSchemaInference(this.c3z_1.jy());
    var tmp;
    var tmp_0 = this.c3z_1;
    if (isInterface(tmp_0, BinaryFormat)) {
      tmp = true;
    } else {
      var tmp_1 = this.c3z_1;
      tmp = isInterface(tmp_1, StringFormat);
    }
    // Inline function 'kotlin.require' call
    if (!tmp) {
      var message = 'Only binary and string formats are supported, ' + toString(this.c3z_1) + ' is not supported.';
      throw IllegalArgumentException.i2(toString(message));
    }
  }
  *f3z(contentType, charset, typeInfo, value, $completion) {
    // Inline function 'kotlinx.coroutines.flow.map' call
    // Inline function 'kotlinx.coroutines.flow.unsafeTransform' call
    var this_0 = asFlow(this.d3z_1);
    // Inline function 'kotlinx.coroutines.flow.internal.unsafeFlow' call
    var tmp = new KotlinxSerializationConverter$serialize$$inlined$map$1(this_0, contentType, charset, typeInfo, value);
    var fromExtension = yield* firstOrNull_0(tmp, KotlinxSerializationConverter$serialize$slambda(), $completion);
    if (!(fromExtension == null))
      return fromExtension;
    var tmp_0;
    try {
      tmp_0 = serializerForTypeInfo(this.c3z_1.jy(), typeInfo);
    } catch ($p) {
      var tmp_1;
      if ($p instanceof SerializationException) {
        var cause = $p;
        tmp_1 = guessSerializer(value, this.c3z_1.jy());
      } else {
        throw $p;
      }
      tmp_0 = tmp_1;
    }
    var serializer = tmp_0;
    return serializeContent(this, serializer, this.c3z_1, value, contentType, charset);
  }
  q3y(contentType, charset, typeInfo, value, $completion) {
    return this.f3z(contentType, charset, typeInfo, value, $completion);
  }
  *m3f(charset, typeInfo, content, $completion) {
    var contentPacket = yield* readBuffer(content, $completion);
    var _iterator__ex2g4s = this.d3z_1.l1();
    $l$loop: while (_iterator__ex2g4s.m1()) {
      var ext = _iterator__ex2g4s.n1();
      var tmp0_elvis_lhs = yield* ext.m3f(charset, typeInfo, ByteReadChannel(contentPacket), $completion);
      var tmp;
      if (tmp0_elvis_lhs == null) {
        continue $l$loop;
      } else {
        tmp = tmp0_elvis_lhs;
      }
      return tmp;
    }
    var serializer = serializerForTypeInfo(this.c3z_1.jy(), typeInfo);
    try {
      var tmp1_subject = this.c3z_1;
      var tmp_0;
      if (isInterface(tmp1_subject, StringFormat)) {
        tmp_0 = this.c3z_1.ou(serializer, readText(contentPacket, charset));
      } else {
        if (isInterface(tmp1_subject, BinaryFormat)) {
          tmp_0 = this.c3z_1.qu(serializer, readByteArray(contentPacket));
        } else {
          discard(contentPacket);
          // Inline function 'kotlin.error' call
          var message = 'Unsupported format ' + toString(this.c3z_1);
          throw IllegalStateException.o(toString(message));
        }
      }
      return tmp_0;
    } catch ($p) {
      if ($p instanceof Error) {
        var cause = $p;
        throw JsonConvertException.i3f('Illegal input: ' + cause.message, cause);
      } else {
        throw $p;
      }
    }
  }
}
//endregion
function extensions(format) {
  // Inline function 'kotlin.collections.mapNotNull' call
  var tmp0 = get_providers();
  // Inline function 'kotlin.collections.mapNotNullTo' call
  var destination = ArrayList.p2();
  // Inline function 'kotlin.collections.forEach' call
  var _iterator__ex2g4s = tmp0.l1();
  while (_iterator__ex2g4s.m1()) {
    var element = _iterator__ex2g4s.n1();
    var tmp0_safe_receiver = element.v3y(format);
    if (tmp0_safe_receiver == null)
      null;
    else {
      // Inline function 'kotlin.let' call
      destination.a2(tmp0_safe_receiver);
    }
  }
  return destination;
}
function serialization(_this__u8e3s4, contentType, format) {
  _this__u8e3s4.k3f(contentType, new KotlinxSerializationConverter(format));
}
function KotlinxSerializationConverter$serialize$o$collect$slambda($$this$unsafeFlow, $contentType, $charset, $typeInfo, $value) {
  return constructCallableReference((value, $completion) => orPromise($completion, function *($completion) {
    var tmp0 = $$this$unsafeFlow;
    var tmp$ret$1 = yield* value.q3y($contentType, $charset, $typeInfo, $value, $completion);
    yield* tmp0.m29(tmp$ret$1, $completion);
    return Unit_instance;
  }), 1);
}
function serializeContent($this, serializer, format, value, contentType, charset) {
  var tmp;
  if (isInterface(format, StringFormat)) {
    var content = format.nu(isInterface(serializer, KSerializer) ? serializer : THROW_CCE(), value);
    tmp = new TextContent(content, withCharsetIfNeeded(contentType, charset));
  } else {
    if (isInterface(format, BinaryFormat)) {
      var content_0 = format.pu(isInterface(serializer, KSerializer) ? serializer : THROW_CCE(), value);
      tmp = new ByteArrayContent(content_0, contentType);
    } else {
      // Inline function 'kotlin.error' call
      var message = 'Unsupported format ' + toString(format);
      throw IllegalStateException.o(toString(message));
    }
  }
  return tmp;
}
function KotlinxSerializationConverter$serialize$slambda() {
  return constructCallableReference((it, $completion) => orPromise($completion, function *($completion) {
    return !(it == null);
  }), 1);
}
function serializerForTypeInfo(_this__u8e3s4, typeInfo) {
  var module_0 = _this__u8e3s4;
  var tmp0_safe_receiver = typeInfo.v2r_1;
  var tmp;
  if (tmp0_safe_receiver == null) {
    tmp = null;
  } else {
    // Inline function 'kotlin.let' call
    var tmp_0;
    if (tmp0_safe_receiver.b1().j1()) {
      tmp_0 = null;
    } else {
      var tmp0_elvis_lhs = serializerOrNull(module_0, tmp0_safe_receiver);
      tmp_0 = tmp0_elvis_lhs == null ? checkTypeParameters(tmp0_safe_receiver, typeInfo, module_0) : tmp0_elvis_lhs;
    }
    tmp = tmp_0;
  }
  var tmp1_elvis_lhs = tmp;
  var tmp2_elvis_lhs = tmp1_elvis_lhs == null ? module_0.qv(typeInfo.t2r_1) : tmp1_elvis_lhs;
  var serializer_0 = tmp2_elvis_lhs == null ? serializer(typeInfo.t2r_1) : tmp2_elvis_lhs;
  return maybeNullable(serializer_0, typeInfo.u2r_1);
}
function guessSerializer(value, module_0) {
  var tmp;
  if (value == null) {
    tmp = get_nullable(serializer_0(StringCompanionObject_instance));
  } else {
    if (isInterface(value, KtList)) {
      tmp = ListSerializer(elementSerializer(value, module_0));
    } else {
      if (isArray(value)) {
        var tmp1_safe_receiver = firstOrNull(value);
        var tmp_0;
        if (tmp1_safe_receiver == null) {
          tmp_0 = null;
        } else {
          // Inline function 'kotlin.let' call
          tmp_0 = guessSerializer(tmp1_safe_receiver, module_0);
        }
        var tmp2_elvis_lhs = tmp_0;
        tmp = tmp2_elvis_lhs == null ? ListSerializer(serializer_0(StringCompanionObject_instance)) : tmp2_elvis_lhs;
      } else {
        if (isInterface(value, KtSet)) {
          tmp = SetSerializer(elementSerializer(value, module_0));
        } else {
          if (isInterface(value, KtMap)) {
            var keySerializer = elementSerializer(value.c4(), module_0);
            var valueSerializer = elementSerializer(value.d4(), module_0);
            tmp = MapSerializer(keySerializer, valueSerializer);
          } else {
            var tmp3_elvis_lhs = module_0.qv(getKClassFromExpression(value));
            tmp = tmp3_elvis_lhs == null ? serializer(getKClassFromExpression(value)) : tmp3_elvis_lhs;
          }
        }
      }
    }
  }
  var tmp_1 = tmp;
  return isInterface(tmp_1, KSerializer) ? tmp_1 : THROW_CCE();
}
function checkTypeParameters(type, typeInfo, module_0) {
  // Inline function 'kotlin.collections.mapNotNull' call
  var tmp0 = type.b1();
  // Inline function 'kotlin.collections.mapNotNullTo' call
  var destination = ArrayList.p2();
  // Inline function 'kotlin.collections.forEach' call
  var _iterator__ex2g4s = tmp0.l1();
  while (_iterator__ex2g4s.m1()) {
    var element = _iterator__ex2g4s.n1();
    var tmp;
    try {
      var tmp0_safe_receiver = element.mn_1;
      var tmp_0;
      if (tmp0_safe_receiver == null) {
        tmp_0 = null;
      } else {
        // Inline function 'kotlin.takeIf' call
        var tmp_1;
        if (serializerOrNull(module_0, tmp0_safe_receiver) == null) {
          tmp_1 = tmp0_safe_receiver;
        } else {
          tmp_1 = null;
        }
        tmp_0 = tmp_1;
      }
      tmp = tmp_0;
    } catch ($p) {
      var tmp_2;
      if ($p instanceof Exception) {
        var _unused_var__etf5q3 = $p;
        return null;
      } else {
        throw $p;
      }
    }
    if (tmp == null)
      null;
    else {
      // Inline function 'kotlin.let' call
      destination.a2(tmp);
    }
  }
  var nonSerializableArgs = destination;
  if (nonSerializableArgs.j1())
    return null;
  var argNames = joinToString(nonSerializableArgs, VOID, VOID, VOID, VOID, VOID, checkTypeParameters$lambda);
  var tmp_3;
  if (nonSerializableArgs.j2() === 1) {
    tmp_3 = to('', 'is');
  } else {
    tmp_3 = to('s', 'are');
  }
  var _destruct__k2r9zo = tmp_3;
  var s = _destruct__k2r9zo.cj();
  var be = _destruct__k2r9zo.dj();
  throw SerializationException.bv('Serializer for type argument' + s + ' ' + argNames + ' ' + be + " not found for '" + typeInfo.t2r_1.f1() + "'. " + ('Ensure that the listed type' + s + ' ' + be + " marked as '@Serializable'."));
}
function maybeNullable(_this__u8e3s4, nullable) {
  var tmp;
  if (nullable) {
    tmp = get_nullable(isInterface(_this__u8e3s4, KSerializer) ? _this__u8e3s4 : THROW_CCE());
  } else {
    tmp = _this__u8e3s4;
  }
  return tmp;
}
function elementSerializer(_this__u8e3s4, module_0) {
  // Inline function 'kotlin.collections.map' call
  var this_0 = filterNotNull(_this__u8e3s4);
  // Inline function 'kotlin.collections.mapTo' call
  var destination = ArrayList.z1(collectionSizeOrDefault(this_0, 10));
  var _iterator__ex2g4s = this_0.l1();
  while (_iterator__ex2g4s.m1()) {
    var item = _iterator__ex2g4s.n1();
    var tmp$ret$2 = guessSerializer(item, module_0);
    destination.a2(tmp$ret$2);
  }
  // Inline function 'kotlin.collections.distinctBy' call
  var set = HashSet.ba();
  var list = ArrayList.p2();
  var _iterator__ex2g4s_0 = destination.l1();
  while (_iterator__ex2g4s_0.m1()) {
    var e = _iterator__ex2g4s_0.n1();
    var key = e.ft().ju();
    if (set.a2(key)) {
      list.a2(e);
    }
  }
  var serializers = list;
  if (serializers.j2() > 1) {
    // Inline function 'kotlin.collections.map' call
    // Inline function 'kotlin.collections.mapTo' call
    var destination_0 = ArrayList.z1(collectionSizeOrDefault(serializers, 10));
    var _iterator__ex2g4s_1 = serializers.l1();
    while (_iterator__ex2g4s_1.m1()) {
      var item_0 = _iterator__ex2g4s_1.n1();
      var tmp$ret$7 = item_0.ft().ju();
      destination_0.a2(tmp$ret$7);
    }
    // Inline function 'kotlin.error' call
    var message = 'Serializing collections of different element types is not yet supported. ' + ('Selected serializers: ' + toString(destination_0));
    throw IllegalStateException.o(toString(message));
  }
  var tmp0_elvis_lhs = singleOrNull(serializers);
  var selected = tmp0_elvis_lhs == null ? serializer_0(StringCompanionObject_instance) : tmp0_elvis_lhs;
  if (selected.ft().uv()) {
    return selected;
  }
  var tmp$ret$9;
  $l$block_0: {
    // Inline function 'kotlin.collections.any' call
    var tmp;
    if (isInterface(_this__u8e3s4, Collection)) {
      tmp = _this__u8e3s4.j1();
    } else {
      tmp = false;
    }
    if (tmp) {
      tmp$ret$9 = false;
      break $l$block_0;
    }
    var _iterator__ex2g4s_2 = _this__u8e3s4.l1();
    while (_iterator__ex2g4s_2.m1()) {
      var element = _iterator__ex2g4s_2.n1();
      if (element == null) {
        tmp$ret$9 = true;
        break $l$block_0;
      }
    }
    tmp$ret$9 = false;
  }
  return maybeNullable(selected, tmp$ret$9);
}
function checkTypeParameters$lambda(it) {
  var clz = it.a1();
  var tmp;
  if (!(clz == null) ? isInterface(clz, KClass) : false) {
    tmp = "'" + clz.f1() + "'";
  } else {
    tmp = "'" + toString(it) + "'";
  }
  return tmp;
}
function get_providers() {
  return emptyList();
}
//region block: post-declaration
initMetadataForClass(sam$kotlinx_coroutines_flow_FlowCollector$0, 'sam$kotlinx_coroutines_flow_FlowCollector$0', VOID, VOID, [FlowCollector, FunctionAdapter], [1]);
initMetadataForClass(KotlinxSerializationConverter$serialize$$inlined$map$1, VOID, VOID, VOID, [Flow], [1]);
initMetadataForClass(KotlinxSerializationConverter, 'KotlinxSerializationConverter', VOID, VOID, VOID, [4, 3]);
//endregion
//region block: exports
export {
  serialization as serialization1fpeds7cruos4,
};
//endregion

//# sourceMappingURL=ktor-ktor-serialization-kotlinx.mjs.map
