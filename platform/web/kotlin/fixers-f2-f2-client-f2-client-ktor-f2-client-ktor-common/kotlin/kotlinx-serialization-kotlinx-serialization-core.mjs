import {
  initMetadataForInterface1egvbzx539z91 as initMetadataForInterface,
  StringCompanionObject_instance2dx16b5pp3v3z as StringCompanionObject_instance,
  Unit_instance3kz1zqli6gr1r as Unit_instance,
  constructCallableReference23y65rf941mch as constructCallableReference,
  emptyList1g2z5xcrvp2zy as emptyList,
  LazyThreadSafetyMode_PUBLICATION_getInstance23f213579at67 as LazyThreadSafetyMode_PUBLICATION_getInstance,
  lazy1261dae0bgscp as lazy,
  asList2ho2pewtsfvv as asList,
  KProperty1ca4yb4wlo496 as KProperty1,
  getPropertyCallableRef3hckxc0xueiaj as getPropertyCallableRef,
  toString1pkumu07cwy4m as toString,
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
  captureStack1fzi4aczwc4hg as captureStack,
  IllegalArgumentException2asla15b5jaob as IllegalArgumentException,
  THROW_CCE2g6jy02ryeudk as THROW_CCE,
  isInterface3d6p8outrmvmk as isInterface,
  equals2au1ep9vhcato as equals,
  getStringHashCode26igk1bx568vk as getStringHashCode,
  VOID3gxj6tk5isa35 as VOID,
  isBlank1dvkhjjvox3p0 as isBlank,
  toList383f556t1dixk as toList,
  ArrayList3it5z8td81qkl as ArrayList,
  HashSet2dzve9y63nf0v as HashSet,
  toHashSet1qrcsl3g8ugc8 as toHashSet,
  copyToArray2j022khrow2yi as copyToArray,
  toBooleanArray2u3qw7fjwsmuh as toBooleanArray,
  withIndex3s8q7w1g0hyfn as withIndex,
  collectionSizeOrDefault36dulx8yinfqm as collectionSizeOrDefault,
  to2cs3ny02qtbcb as to,
  toMap1vec9topfei08 as toMap,
  lazy2hsh8ze7j6ikd as lazy_0,
  contentEqualsaf55p28mnw74 as contentEquals,
  protoOf180f3jzyo7rfj as protoOf,
  initMetadataForObject1cxne3s9w65el as initMetadataForObject,
  getKClassFromExpression348iqjl4fnx2f as getKClassFromExpression,
  ensureNotNull1e947j3ixpazm as ensureNotNull,
  createThis2j2avj17cvnv2 as createThis,
  hashCodeq5arwsb9dgti as hashCode,
  HashMap1a0ld5kgwhmhv as HashMap,
  booleanArray2jdug9b51huk7 as booleanArray,
  emptyMapr06gerzljqtm as emptyMap,
  contentHashCode2i020q5tbeh2s as contentHashCode,
  until1jbpn0z3f8lbg as until,
  joinToString1cxrrlmo0chqs as joinToString,
  IllegalStateExceptionkoljg5n0nrlr as IllegalStateException,
  IndexOutOfBoundsException1qfr429iumro0 as IndexOutOfBoundsException,
  get_indicesc04v40g017hw as get_indices,
} from './kotlin-kotlin-stdlib.mjs';
//region block: imports
var imul = Math.imul;
//endregion
//region block: pre-declaration
class KSerializer {}
class AbstractPolymorphicSerializer {
  static te() {
    return createThis(this);
  }
}
class PolymorphicSerializer extends AbstractPolymorphicSerializer {
  constructor(baseClass) {
    return new.target.se(baseClass);
  }
  static se(baseClass) {
    var $this = this.te();
    $this.pe_1 = baseClass;
    $this.qe_1 = emptyList();
    var tmp = $this;
    var tmp_0 = LazyThreadSafetyMode_PUBLICATION_getInstance();
    tmp.re_1 = lazy(tmp_0, PolymorphicSerializer$descriptor$delegate$lambda($this));
    return $this;
  }
  static ue(baseClass, classAnnotations) {
    var $this = this.se(baseClass);
    $this.qe_1 = asList(classAnnotations);
    return $this;
  }
  fe() {
    var tmp0 = this.re_1;
    var tmp = KProperty1;
    // Inline function 'kotlin.getValue' call
    getPropertyCallableRef('descriptor', 1, tmp, PolymorphicSerializer$_get_descriptor_$ref_8tw9if(), null);
    return tmp0.d1();
  }
  toString() {
    return 'kotlinx.serialization.PolymorphicSerializer(baseClass: ' + toString(this.pe_1) + ')';
  }
}
class SerializationException extends IllegalArgumentException {
  static ze() {
    var $this = this.c9();
    init_kotlinx_serialization_SerializationException($this);
    return $this;
  }
  static af(message, cause) {
    var $this = this.d9(message, cause);
    init_kotlinx_serialization_SerializationException($this);
    return $this;
  }
}
class MissingFieldException extends SerializationException {
  constructor(message, cause, missingFields, serialName) {
    return new.target.if(message, cause, missingFields, serialName);
  }
  static if(message, cause, missingFields, serialName) {
    var $this = this.af(message, cause);
    captureStack($this, $this.hf_1);
    $this.ff_1 = missingFields;
    $this.gf_1 = serialName;
    return $this;
  }
  static jf(missingFields, serialName) {
    var tmp0_message = missingFields.i() === 1 ? "Field '" + missingFields.w(0) + "' is required for type with serial name '" + serialName + "', but it was missing" : 'Fields ' + toString(missingFields) + " are required for type with serial name '" + serialName + "', but they were missing";
    return this.if(tmp0_message, null, missingFields, serialName);
  }
}
class SerialDescriptor {}
function get_isNullable() {
  return false;
}
class ContextDescriptor {
  constructor(original, kClass) {
    this.lf_1 = original;
    this.mf_1 = kClass;
    this.nf_1 = this.lf_1.of() + '<' + this.mf_1.x9() + '>';
  }
  of() {
    return this.nf_1;
  }
  equals(other) {
    var tmp0_elvis_lhs = other instanceof ContextDescriptor ? other : null;
    var tmp;
    if (tmp0_elvis_lhs == null) {
      return false;
    } else {
      tmp = tmp0_elvis_lhs;
    }
    var another = tmp;
    return equals(this.lf_1, another.lf_1) && another.mf_1.equals(this.mf_1);
  }
  hashCode() {
    var result = this.mf_1.hashCode();
    result = imul(31, result) + getStringHashCode(this.nf_1) | 0;
    return result;
  }
  toString() {
    return 'ContextDescriptor(kClass: ' + toString(this.mf_1) + ', original: ' + toString(this.lf_1) + ')';
  }
  pf() {
    return this.lf_1.pf();
  }
  kf() {
    return this.lf_1.kf();
  }
  qf() {
    return this.lf_1.qf();
  }
  rf(index) {
    return this.lf_1.rf(index);
  }
  sf(index) {
    return this.lf_1.sf(index);
  }
}
class elementDescriptors$1 {
  constructor($this_elementDescriptors) {
    this.uf_1 = $this_elementDescriptors;
    this.tf_1 = $this_elementDescriptors.qf();
  }
  g() {
    return this.tf_1 > 0;
  }
  h() {
    var tmp = this.uf_1.qf();
    var _unary__edvuaz = this.tf_1;
    this.tf_1 = _unary__edvuaz - 1 | 0;
    return this.uf_1.sf(tmp - _unary__edvuaz | 0);
  }
}
class elementDescriptors$$inlined$Iterable$1 {
  constructor($this_elementDescriptors) {
    this.vf_1 = $this_elementDescriptors;
  }
  f() {
    return new elementDescriptors$1(this.vf_1);
  }
}
class ClassSerialDescriptorBuilder {
  constructor(serialName) {
    this.ge_1 = serialName;
    this.he_1 = false;
    this.ie_1 = emptyList();
    this.je_1 = ArrayList.l4();
    this.ke_1 = HashSet.a6();
    this.le_1 = ArrayList.l4();
    this.me_1 = ArrayList.l4();
    this.ne_1 = ArrayList.l4();
  }
  wf(elementName, descriptor, annotations, isOptional) {
    // Inline function 'kotlin.require' call
    if (!this.ke_1.o(elementName)) {
      var message = "Element with name '" + elementName + "' is already registered in " + this.ge_1;
      throw IllegalArgumentException.q2(toString(message));
    }
    // Inline function 'kotlin.collections.plusAssign' call
    this.je_1.o(elementName);
    // Inline function 'kotlin.collections.plusAssign' call
    this.le_1.o(descriptor);
    // Inline function 'kotlin.collections.plusAssign' call
    this.me_1.o(annotations);
    // Inline function 'kotlin.collections.plusAssign' call
    this.ne_1.o(isOptional);
  }
  oe(elementName, descriptor, annotations, isOptional, $super) {
    annotations = annotations === VOID ? emptyList() : annotations;
    isOptional = isOptional === VOID ? false : isOptional;
    var tmp;
    if ($super === VOID) {
      this.wf(elementName, descriptor, annotations, isOptional);
      tmp = Unit_instance;
    } else {
      tmp = $super.wf.call(this, elementName, descriptor, annotations, isOptional);
    }
    return tmp;
  }
}
class CachedNames {}
class SerialDescriptorImpl {
  constructor(serialName, kind, elementsCount, typeParameters, builder) {
    this.xf_1 = serialName;
    this.yf_1 = kind;
    this.zf_1 = elementsCount;
    this.ag_1 = builder.ie_1;
    this.bg_1 = toHashSet(builder.je_1);
    var tmp = this;
    // Inline function 'kotlin.collections.toTypedArray' call
    var this_0 = builder.je_1;
    tmp.cg_1 = copyToArray(this_0);
    this.dg_1 = compactArray(builder.le_1);
    var tmp_0 = this;
    // Inline function 'kotlin.collections.toTypedArray' call
    var this_1 = builder.me_1;
    tmp_0.eg_1 = copyToArray(this_1);
    this.fg_1 = toBooleanArray(builder.ne_1);
    var tmp_1 = this;
    // Inline function 'kotlin.collections.map' call
    var this_2 = withIndex(this.cg_1);
    // Inline function 'kotlin.collections.mapTo' call
    var destination = ArrayList.m(collectionSizeOrDefault(this_2, 10));
    var _iterator__ex2g4s = this_2.f();
    while (_iterator__ex2g4s.g()) {
      var item = _iterator__ex2g4s.h();
      var tmp$ret$4 = to(item.xb_1, item.wb_1);
      destination.o(tmp$ret$4);
    }
    tmp_1.gg_1 = toMap(destination);
    this.hg_1 = compactArray(typeParameters);
    var tmp_2 = this;
    tmp_2.ig_1 = lazy_0(SerialDescriptorImpl$_hashCode$delegate$lambda(this));
  }
  of() {
    return this.xf_1;
  }
  pf() {
    return this.yf_1;
  }
  qf() {
    return this.zf_1;
  }
  jg() {
    return this.bg_1;
  }
  rf(index) {
    return getChecked(this.cg_1, index);
  }
  sf(index) {
    return getChecked(this.dg_1, index);
  }
  equals(other) {
    var tmp$ret$0;
    $l$block_5: {
      // Inline function 'kotlinx.serialization.internal.equalsImpl' call
      if (this === other) {
        tmp$ret$0 = true;
        break $l$block_5;
      }
      if (!(other instanceof SerialDescriptorImpl)) {
        tmp$ret$0 = false;
        break $l$block_5;
      }
      if (!(this.of() === other.of())) {
        tmp$ret$0 = false;
        break $l$block_5;
      }
      if (!contentEquals(this.hg_1, other.hg_1)) {
        tmp$ret$0 = false;
        break $l$block_5;
      }
      if (!(this.qf() === other.qf())) {
        tmp$ret$0 = false;
        break $l$block_5;
      }
      var inductionVariable = 0;
      var last = this.qf();
      if (inductionVariable < last)
        do {
          var index = inductionVariable;
          inductionVariable = inductionVariable + 1 | 0;
          if (!(this.sf(index).of() === other.sf(index).of())) {
            tmp$ret$0 = false;
            break $l$block_5;
          }
          if (!equals(this.sf(index).pf(), other.sf(index).pf())) {
            tmp$ret$0 = false;
            break $l$block_5;
          }
        }
         while (inductionVariable < last);
      tmp$ret$0 = true;
    }
    return tmp$ret$0;
  }
  hashCode() {
    return _get__hashCode__tgwhef(this);
  }
  toString() {
    return toStringImpl(this);
  }
}
class SerialKind {
  toString() {
    return ensureNotNull(getKClassFromExpression(this).x9());
  }
  hashCode() {
    return getStringHashCode(this.toString());
  }
}
class CONTEXTUAL extends SerialKind {
  constructor() {
    CONTEXTUAL_instance = null;
    super();
    CONTEXTUAL_instance = this;
  }
}
class StructureKind extends SerialKind {}
class CLASS extends StructureKind {
  constructor() {
    CLASS_instance = null;
    super();
    CLASS_instance = this;
  }
}
class PrimitiveKind extends SerialKind {}
class INT extends PrimitiveKind {
  constructor() {
    INT_instance = null;
    super();
    INT_instance = this;
  }
}
class STRING extends PrimitiveKind {
  constructor() {
    STRING_instance = null;
    super();
    STRING_instance = this;
  }
}
class PolymorphicKind extends SerialKind {}
class OPEN extends PolymorphicKind {
  constructor() {
    OPEN_instance = null;
    super();
    OPEN_instance = this;
  }
}
class NullableSerializer {
  constructor(serializer) {
    this.kg_1 = serializer;
    this.lg_1 = new SerialDescriptorForNullable(this.kg_1.fe());
  }
  fe() {
    return this.lg_1;
  }
  equals(other) {
    if (this === other)
      return true;
    if (other == null || !getKClassFromExpression(this).equals(getKClassFromExpression(other)))
      return false;
    if (!(other instanceof NullableSerializer))
      THROW_CCE();
    if (!equals(this.kg_1, other.kg_1))
      return false;
    return true;
  }
  hashCode() {
    return hashCode(this.kg_1);
  }
}
class SerialDescriptorForNullable {
  constructor(original) {
    this.mg_1 = original;
    this.ng_1 = this.mg_1.of() + '?';
    this.og_1 = cachedSerialNames(this.mg_1);
  }
  of() {
    return this.ng_1;
  }
  jg() {
    return this.og_1;
  }
  kf() {
    return true;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof SerialDescriptorForNullable))
      return false;
    if (!equals(this.mg_1, other.mg_1))
      return false;
    return true;
  }
  toString() {
    return toString(this.mg_1) + '?';
  }
  hashCode() {
    return imul(hashCode(this.mg_1), 31);
  }
  pf() {
    return this.mg_1.pf();
  }
  qf() {
    return this.mg_1.qf();
  }
  rf(index) {
    return this.mg_1.rf(index);
  }
  sf(index) {
    return this.mg_1.sf(index);
  }
}
class PluginGeneratedSerialDescriptor {
  constructor(serialName, generatedSerializer, elementsCount) {
    generatedSerializer = generatedSerializer === VOID ? null : generatedSerializer;
    this.pg_1 = serialName;
    this.qg_1 = generatedSerializer;
    this.rg_1 = elementsCount;
    this.sg_1 = -1;
    var tmp = this;
    var tmp_0 = 0;
    var tmp_1 = this.rg_1;
    // Inline function 'kotlin.arrayOfNulls' call
    var tmp_2 = Array(tmp_1);
    while (tmp_0 < tmp_1) {
      tmp_2[tmp_0] = '[UNINITIALIZED]';
      tmp_0 = tmp_0 + 1 | 0;
    }
    tmp.tg_1 = tmp_2;
    var tmp_3 = this;
    // Inline function 'kotlin.arrayOfNulls' call
    var size = this.rg_1;
    tmp_3.ug_1 = Array(size);
    this.vg_1 = null;
    this.wg_1 = booleanArray(this.rg_1);
    this.xg_1 = emptyMap();
    var tmp_4 = this;
    var tmp_5 = LazyThreadSafetyMode_PUBLICATION_getInstance();
    tmp_4.yg_1 = lazy(tmp_5, PluginGeneratedSerialDescriptor$childSerializers$delegate$lambda(this));
    var tmp_6 = this;
    var tmp_7 = LazyThreadSafetyMode_PUBLICATION_getInstance();
    tmp_6.zg_1 = lazy(tmp_7, PluginGeneratedSerialDescriptor$typeParameterDescriptors$delegate$lambda(this));
    var tmp_8 = this;
    var tmp_9 = LazyThreadSafetyMode_PUBLICATION_getInstance();
    tmp_8.ah_1 = lazy(tmp_9, PluginGeneratedSerialDescriptor$_hashCode$delegate$lambda(this));
  }
  of() {
    return this.pg_1;
  }
  qf() {
    return this.rg_1;
  }
  pf() {
    return CLASS_getInstance();
  }
  jg() {
    return this.xg_1.g1();
  }
  dh() {
    var tmp0 = this.zg_1;
    var tmp = KProperty1;
    // Inline function 'kotlin.getValue' call
    getPropertyCallableRef('typeParameterDescriptors', 1, tmp, PluginGeneratedSerialDescriptor$_get_typeParameterDescriptors_$ref_jk3pka(), null);
    return tmp0.d1();
  }
  eh(name, isOptional) {
    this.sg_1 = this.sg_1 + 1 | 0;
    this.tg_1[this.sg_1] = name;
    this.wg_1[this.sg_1] = isOptional;
    this.ug_1[this.sg_1] = null;
    if (this.sg_1 === (this.rg_1 - 1 | 0)) {
      this.xg_1 = buildIndices(this);
    }
  }
  sf(index) {
    return getChecked(_get_childSerializers__7vnyfa(this), index).fe();
  }
  rf(index) {
    return getChecked(this.tg_1, index);
  }
  equals(other) {
    var tmp$ret$0;
    $l$block_5: {
      // Inline function 'kotlinx.serialization.internal.equalsImpl' call
      if (this === other) {
        tmp$ret$0 = true;
        break $l$block_5;
      }
      if (!(other instanceof PluginGeneratedSerialDescriptor)) {
        tmp$ret$0 = false;
        break $l$block_5;
      }
      if (!(this.of() === other.of())) {
        tmp$ret$0 = false;
        break $l$block_5;
      }
      if (!contentEquals(this.dh(), other.dh())) {
        tmp$ret$0 = false;
        break $l$block_5;
      }
      if (!(this.qf() === other.qf())) {
        tmp$ret$0 = false;
        break $l$block_5;
      }
      var inductionVariable = 0;
      var last = this.qf();
      if (inductionVariable < last)
        do {
          var index = inductionVariable;
          inductionVariable = inductionVariable + 1 | 0;
          if (!(this.sf(index).of() === other.sf(index).of())) {
            tmp$ret$0 = false;
            break $l$block_5;
          }
          if (!equals(this.sf(index).pf(), other.sf(index).pf())) {
            tmp$ret$0 = false;
            break $l$block_5;
          }
        }
         while (inductionVariable < last);
      tmp$ret$0 = true;
    }
    return tmp$ret$0;
  }
  hashCode() {
    return _get__hashCode__tgwhef_0(this);
  }
  toString() {
    return toStringImpl(this);
  }
}
class GeneratedSerializer {}
function typeParametersSerializers() {
  return get_EMPTY_SERIALIZER_ARRAY();
}
class IntSerializer {
  constructor() {
    IntSerializer_instance = this;
    this.fh_1 = new PrimitiveSerialDescriptor('kotlin.Int', INT_getInstance());
  }
  fe() {
    return this.fh_1;
  }
}
class StringSerializer {
  constructor() {
    StringSerializer_instance = this;
    this.gh_1 = new PrimitiveSerialDescriptor('kotlin.String', STRING_getInstance());
  }
  fe() {
    return this.gh_1;
  }
}
class PrimitiveSerialDescriptor {
  constructor(serialName, kind) {
    this.hh_1 = serialName;
    this.ih_1 = kind;
  }
  of() {
    return this.hh_1;
  }
  pf() {
    return this.ih_1;
  }
  qf() {
    return 0;
  }
  rf(index) {
    error(this);
  }
  sf(index) {
    error(this);
  }
  toString() {
    return 'PrimitiveDescriptor(' + this.hh_1 + ')';
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof PrimitiveSerialDescriptor))
      return false;
    if (this.hh_1 === other.hh_1 && equals(this.ih_1, other.ih_1))
      return true;
    return false;
  }
  hashCode() {
    return getStringHashCode(this.hh_1) + imul(31, this.ih_1.hashCode()) | 0;
  }
}
class SerializableWith {}
//endregion
function PolymorphicSerializer$descriptor$delegate$lambda$lambda(this$0) {
  return ($this$buildSerialDescriptor) => {
    $this$buildSerialDescriptor.oe('type', serializer(StringCompanionObject_instance).fe());
    $this$buildSerialDescriptor.oe('value', buildSerialDescriptor('kotlinx.serialization.Polymorphic<' + this$0.pe_1.x9() + '>', CONTEXTUAL_getInstance(), []));
    $this$buildSerialDescriptor.ie_1 = this$0.qe_1;
    return Unit_instance;
  };
}
function PolymorphicSerializer$descriptor$delegate$lambda(this$0) {
  return () => {
    var tmp = OPEN_getInstance();
    return withContext(buildSerialDescriptor('kotlinx.serialization.Polymorphic', tmp, [], PolymorphicSerializer$descriptor$delegate$lambda$lambda(this$0)), this$0.pe_1);
  };
}
function PolymorphicSerializer$_get_descriptor_$ref_8tw9if() {
  return constructCallableReference((p0) => p0.fe(), 1, 0, 1);
}
function init_kotlinx_serialization_SerializationException(_this__u8e3s4) {
  captureStack(_this__u8e3s4, _this__u8e3s4.ye_1);
}
function get_nullable(_this__u8e3s4) {
  var tmp;
  if (_this__u8e3s4.fe().kf()) {
    tmp = isInterface(_this__u8e3s4, KSerializer) ? _this__u8e3s4 : THROW_CCE();
  } else {
    tmp = new NullableSerializer(_this__u8e3s4);
  }
  return tmp;
}
function serializer(_this__u8e3s4) {
  return StringSerializer_getInstance();
}
function withContext(_this__u8e3s4, context) {
  return new ContextDescriptor(_this__u8e3s4, context);
}
function get_elementDescriptors(_this__u8e3s4) {
  // Inline function 'kotlin.collections.Iterable' call
  return new elementDescriptors$$inlined$Iterable$1(_this__u8e3s4);
}
function buildSerialDescriptor(serialName, kind, typeParameters, builder) {
  var tmp;
  if (builder === VOID) {
    tmp = buildSerialDescriptor$lambda;
  } else {
    tmp = builder;
  }
  builder = tmp;
  // Inline function 'kotlin.text.isNotBlank' call
  // Inline function 'kotlin.require' call
  if (!!isBlank(serialName)) {
    var message = 'Blank serial names are prohibited';
    throw IllegalArgumentException.q2(toString(message));
  }
  // Inline function 'kotlin.require' call
  if (!!equals(kind, CLASS_getInstance())) {
    var message_0 = "For StructureKind.CLASS please use 'buildClassSerialDescriptor' instead";
    throw IllegalArgumentException.q2(toString(message_0));
  }
  var sdBuilder = new ClassSerialDescriptorBuilder(serialName);
  builder(sdBuilder);
  return new SerialDescriptorImpl(serialName, kind, sdBuilder.je_1.i(), toList(typeParameters), sdBuilder);
}
function _get__hashCode__tgwhef($this) {
  var tmp0 = $this.ig_1;
  var tmp = KProperty1;
  // Inline function 'kotlin.getValue' call
  getPropertyCallableRef('_hashCode', 1, tmp, SerialDescriptorImpl$_get__hashCode_$ref_2v7wzp(), null);
  return tmp0.d1();
}
function SerialDescriptorImpl$_hashCode$delegate$lambda(this$0) {
  return () => hashCodeImpl(this$0, this$0.hg_1);
}
function SerialDescriptorImpl$_get__hashCode_$ref_2v7wzp() {
  return constructCallableReference((p0) => _get__hashCode__tgwhef(p0), 1, 0, 2);
}
function buildSerialDescriptor$lambda(_this__u8e3s4) {
  return Unit_instance;
}
var CONTEXTUAL_instance;
function CONTEXTUAL_getInstance() {
  if (CONTEXTUAL_instance === VOID)
    new CONTEXTUAL();
  return CONTEXTUAL_instance;
}
var CLASS_instance;
function CLASS_getInstance() {
  if (CLASS_instance === VOID)
    new CLASS();
  return CLASS_instance;
}
var INT_instance;
function INT_getInstance() {
  if (INT_instance === VOID)
    new INT();
  return INT_instance;
}
var STRING_instance;
function STRING_getInstance() {
  if (STRING_instance === VOID)
    new STRING();
  return STRING_instance;
}
var OPEN_instance;
function OPEN_getInstance() {
  if (OPEN_instance === VOID)
    new OPEN();
  return OPEN_instance;
}
function get_EMPTY_DESCRIPTOR_ARRAY() {
  _init_properties_Platform_common_kt__3qzecs();
  return EMPTY_DESCRIPTOR_ARRAY;
}
var EMPTY_DESCRIPTOR_ARRAY;
function compactArray(_this__u8e3s4) {
  _init_properties_Platform_common_kt__3qzecs();
  // Inline function 'kotlin.takeUnless' call
  var tmp;
  // Inline function 'kotlin.collections.isNullOrEmpty' call
  if (!(_this__u8e3s4 == null || _this__u8e3s4.s())) {
    tmp = _this__u8e3s4;
  } else {
    tmp = null;
  }
  var tmp0_safe_receiver = tmp;
  var tmp_0;
  if (tmp0_safe_receiver == null) {
    tmp_0 = null;
  } else {
    // Inline function 'kotlin.collections.toTypedArray' call
    tmp_0 = copyToArray(tmp0_safe_receiver);
  }
  var tmp1_elvis_lhs = tmp_0;
  return tmp1_elvis_lhs == null ? get_EMPTY_DESCRIPTOR_ARRAY() : tmp1_elvis_lhs;
}
function cachedSerialNames(_this__u8e3s4) {
  _init_properties_Platform_common_kt__3qzecs();
  if (isInterface(_this__u8e3s4, CachedNames))
    return _this__u8e3s4.jg();
  var result = HashSet.q(_this__u8e3s4.qf());
  var inductionVariable = 0;
  var last = _this__u8e3s4.qf();
  if (inductionVariable < last)
    do {
      var i = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      // Inline function 'kotlin.collections.plusAssign' call
      var element = _this__u8e3s4.rf(i);
      result.o(element);
    }
     while (inductionVariable < last);
  return result;
}
var properties_initialized_Platform_common_kt_i7q4ty;
function _init_properties_Platform_common_kt__3qzecs() {
  if (!properties_initialized_Platform_common_kt_i7q4ty) {
    properties_initialized_Platform_common_kt_i7q4ty = true;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    EMPTY_DESCRIPTOR_ARRAY = [];
  }
}
function throwMissingFieldException(seen, goldenMask, descriptor) {
  // Inline function 'kotlin.collections.mutableListOf' call
  var missingFields = ArrayList.l4();
  var missingFieldsBits = goldenMask & ~seen;
  var inductionVariable = 0;
  if (inductionVariable < 32)
    do {
      var i = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      if (!((missingFieldsBits & 1) === 0)) {
        // Inline function 'kotlin.collections.plusAssign' call
        var element = descriptor.rf(i);
        missingFields.o(element);
      }
      missingFieldsBits = missingFieldsBits >>> 1 | 0;
    }
     while (inductionVariable < 32);
  throw MissingFieldException.jf(missingFields, descriptor.of());
}
function _get_childSerializers__7vnyfa($this) {
  var tmp0 = $this.yg_1;
  var tmp = KProperty1;
  // Inline function 'kotlin.getValue' call
  getPropertyCallableRef('childSerializers', 1, tmp, PluginGeneratedSerialDescriptor$_get_childSerializers_$ref_e7suca(), null);
  return tmp0.d1();
}
function _get__hashCode__tgwhef_0($this) {
  var tmp0 = $this.ah_1;
  var tmp = KProperty1;
  // Inline function 'kotlin.getValue' call
  getPropertyCallableRef('_hashCode', 1, tmp, PluginGeneratedSerialDescriptor$_get__hashCode_$ref_cmj4vz(), null);
  return tmp0.d1();
}
function buildIndices($this) {
  var indices = HashMap.u4();
  var inductionVariable = 0;
  var last = $this.tg_1.length - 1 | 0;
  if (inductionVariable <= last)
    do {
      var i = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      // Inline function 'kotlin.collections.set' call
      var key = $this.tg_1[i];
      indices.f4(key, i);
    }
     while (inductionVariable <= last);
  return indices;
}
function PluginGeneratedSerialDescriptor$childSerializers$delegate$lambda(this$0) {
  return () => {
    var tmp0_safe_receiver = this$0.qg_1;
    var tmp1_elvis_lhs = tmp0_safe_receiver == null ? null : tmp0_safe_receiver.bh();
    return tmp1_elvis_lhs == null ? get_EMPTY_SERIALIZER_ARRAY() : tmp1_elvis_lhs;
  };
}
function PluginGeneratedSerialDescriptor$_get_childSerializers_$ref_e7suca() {
  return constructCallableReference((p0) => _get_childSerializers__7vnyfa(p0), 1, 0, 3);
}
function PluginGeneratedSerialDescriptor$typeParameterDescriptors$delegate$lambda(this$0) {
  return () => {
    var tmp0_safe_receiver = this$0.qg_1;
    var tmp1_safe_receiver = tmp0_safe_receiver == null ? null : tmp0_safe_receiver.ch();
    var tmp;
    if (tmp1_safe_receiver == null) {
      tmp = null;
    } else {
      // Inline function 'kotlin.collections.map' call
      // Inline function 'kotlin.collections.mapTo' call
      var destination = ArrayList.m(tmp1_safe_receiver.length);
      var inductionVariable = 0;
      var last = tmp1_safe_receiver.length;
      while (inductionVariable < last) {
        var item = tmp1_safe_receiver[inductionVariable];
        inductionVariable = inductionVariable + 1 | 0;
        var tmp$ret$2 = item.fe();
        destination.o(tmp$ret$2);
      }
      tmp = destination;
    }
    return compactArray(tmp);
  };
}
function PluginGeneratedSerialDescriptor$_get_typeParameterDescriptors_$ref_jk3pka() {
  return constructCallableReference((p0) => p0.dh(), 1, 0, 4);
}
function PluginGeneratedSerialDescriptor$_hashCode$delegate$lambda(this$0) {
  return () => hashCodeImpl(this$0, this$0.dh());
}
function PluginGeneratedSerialDescriptor$_get__hashCode_$ref_cmj4vz() {
  return constructCallableReference((p0) => _get__hashCode__tgwhef_0(p0), 1, 0, 5);
}
function hashCodeImpl(_this__u8e3s4, typeParams) {
  var result = getStringHashCode(_this__u8e3s4.of());
  result = imul(31, result) + contentHashCode(typeParams) | 0;
  var elementDescriptors = get_elementDescriptors(_this__u8e3s4);
  // Inline function 'kotlinx.serialization.internal.elementsHashCodeBy' call
  // Inline function 'kotlin.collections.fold' call
  var accumulator = 1;
  var _iterator__ex2g4s = elementDescriptors.f();
  while (_iterator__ex2g4s.g()) {
    var element = _iterator__ex2g4s.h();
    var hash = accumulator;
    var tmp = imul(31, hash);
    // Inline function 'kotlin.hashCode' call
    var tmp0_safe_receiver = element.of();
    var tmp1_elvis_lhs = tmp0_safe_receiver == null ? null : hashCode(tmp0_safe_receiver);
    accumulator = tmp + (tmp1_elvis_lhs == null ? 0 : tmp1_elvis_lhs) | 0;
  }
  var namesHash = accumulator;
  // Inline function 'kotlinx.serialization.internal.elementsHashCodeBy' call
  // Inline function 'kotlin.collections.fold' call
  var accumulator_0 = 1;
  var _iterator__ex2g4s_0 = elementDescriptors.f();
  while (_iterator__ex2g4s_0.g()) {
    var element_0 = _iterator__ex2g4s_0.h();
    var hash_0 = accumulator_0;
    var tmp_0 = imul(31, hash_0);
    // Inline function 'kotlin.hashCode' call
    var tmp0_safe_receiver_0 = element_0.pf();
    var tmp1_elvis_lhs_0 = tmp0_safe_receiver_0 == null ? null : hashCode(tmp0_safe_receiver_0);
    accumulator_0 = tmp_0 + (tmp1_elvis_lhs_0 == null ? 0 : tmp1_elvis_lhs_0) | 0;
  }
  var kindHash = accumulator_0;
  result = imul(31, result) + namesHash | 0;
  result = imul(31, result) + kindHash | 0;
  return result;
}
function toStringImpl(_this__u8e3s4) {
  var tmp = until(0, _this__u8e3s4.qf());
  var tmp_0 = _this__u8e3s4.of() + '(';
  return joinToString(tmp, ', ', tmp_0, ')', VOID, VOID, toStringImpl$lambda(_this__u8e3s4));
}
function toStringImpl$lambda($this_toStringImpl) {
  return (i) => $this_toStringImpl.rf(i) + ': ' + $this_toStringImpl.sf(i).of();
}
function get_EMPTY_SERIALIZER_ARRAY() {
  _init_properties_PluginHelperInterfaces_kt__xgvzfp();
  return EMPTY_SERIALIZER_ARRAY;
}
var EMPTY_SERIALIZER_ARRAY;
var properties_initialized_PluginHelperInterfaces_kt_ap8in1;
function _init_properties_PluginHelperInterfaces_kt__xgvzfp() {
  if (!properties_initialized_PluginHelperInterfaces_kt_ap8in1) {
    properties_initialized_PluginHelperInterfaces_kt_ap8in1 = true;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    EMPTY_SERIALIZER_ARRAY = [];
  }
}
var IntSerializer_instance;
function IntSerializer_getInstance() {
  if (IntSerializer_instance === VOID)
    new IntSerializer();
  return IntSerializer_instance;
}
var StringSerializer_instance;
function StringSerializer_getInstance() {
  if (StringSerializer_instance === VOID)
    new StringSerializer();
  return StringSerializer_instance;
}
function error($this) {
  throw IllegalStateException.e2('Primitive descriptor ' + $this.hh_1 + ' does not have elements');
}
function getChecked(_this__u8e3s4, index) {
  if (!(0 <= index ? index <= (_this__u8e3s4.length - 1 | 0) : false))
    throw IndexOutOfBoundsException.n9('Index ' + index + ' out of bounds ' + get_indices(_this__u8e3s4).toString());
  return _this__u8e3s4[index];
}
//region block: post-declaration
initMetadataForInterface(KSerializer, 'KSerializer');
initMetadataForClass(AbstractPolymorphicSerializer, 'AbstractPolymorphicSerializer', VOID, VOID, [KSerializer]);
initMetadataForClass(PolymorphicSerializer, 'PolymorphicSerializer');
initMetadataForClass(SerializationException, 'SerializationException', SerializationException.ze);
initMetadataForClass(MissingFieldException, 'MissingFieldException');
initMetadataForInterface(SerialDescriptor, 'SerialDescriptor');
initMetadataForClass(ContextDescriptor, 'ContextDescriptor', VOID, VOID, [SerialDescriptor]);
initMetadataForClass(elementDescriptors$1);
initMetadataForClass(elementDescriptors$$inlined$Iterable$1);
initMetadataForClass(ClassSerialDescriptorBuilder, 'ClassSerialDescriptorBuilder');
initMetadataForInterface(CachedNames, 'CachedNames');
protoOf(SerialDescriptorImpl).kf = get_isNullable;
initMetadataForClass(SerialDescriptorImpl, 'SerialDescriptorImpl', VOID, VOID, [SerialDescriptor, CachedNames]);
initMetadataForClass(SerialKind, 'SerialKind');
initMetadataForObject(CONTEXTUAL, 'CONTEXTUAL');
initMetadataForClass(StructureKind, 'StructureKind');
initMetadataForObject(CLASS, 'CLASS');
initMetadataForClass(PrimitiveKind, 'PrimitiveKind');
initMetadataForObject(INT, 'INT');
initMetadataForObject(STRING, 'STRING');
initMetadataForClass(PolymorphicKind, 'PolymorphicKind');
initMetadataForObject(OPEN, 'OPEN');
initMetadataForClass(NullableSerializer, 'NullableSerializer', VOID, VOID, [KSerializer]);
initMetadataForClass(SerialDescriptorForNullable, 'SerialDescriptorForNullable', VOID, VOID, [SerialDescriptor, CachedNames]);
protoOf(PluginGeneratedSerialDescriptor).kf = get_isNullable;
initMetadataForClass(PluginGeneratedSerialDescriptor, 'PluginGeneratedSerialDescriptor', VOID, VOID, [SerialDescriptor, CachedNames]);
initMetadataForInterface(GeneratedSerializer, 'GeneratedSerializer', VOID, VOID, [KSerializer]);
initMetadataForObject(IntSerializer, 'IntSerializer', VOID, VOID, [KSerializer]);
initMetadataForObject(StringSerializer, 'StringSerializer', VOID, VOID, [KSerializer]);
protoOf(PrimitiveSerialDescriptor).kf = get_isNullable;
initMetadataForClass(PrimitiveSerialDescriptor, 'PrimitiveSerialDescriptor', VOID, VOID, [SerialDescriptor]);
initMetadataForClass(SerializableWith, 'SerializableWith', VOID, VOID, VOID, VOID, 0);
//endregion
//region block: exports
export {
  IntSerializer_getInstance as IntSerializer_getInstance3jyhns9ue706g,
  StringSerializer_getInstance as StringSerializer_getInstance1p9zda6r6kb23,
  get_nullable as get_nullable197rfua9r7fsz,
  typeParametersSerializers as typeParametersSerializers2likxjr48tr7y,
  GeneratedSerializer as GeneratedSerializer1f7t7hssdd2ws,
  PluginGeneratedSerialDescriptor as PluginGeneratedSerialDescriptorqdzeg5asqhfg,
  throwMissingFieldException as throwMissingFieldException2cmke0v3ynf14,
  PolymorphicSerializer as PolymorphicSerializer3p3fzpdobi8hh,
  SerializableWith as SerializableWithd2dap36updxd,
};
//endregion

//# sourceMappingURL=kotlinx-serialization-kotlinx-serialization-core.mjs.map
