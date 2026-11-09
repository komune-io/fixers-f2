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
  contentHashCode2i020q5tbeh2s as contentHashCode,
  until1jbpn0z3f8lbg as until,
  joinToString1cxrrlmo0chqs as joinToString,
  HashMap1a0ld5kgwhmhv as HashMap,
  booleanArray2jdug9b51huk7 as booleanArray,
  emptyMapr06gerzljqtm as emptyMap,
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
  static ed() {
    return createThis(this);
  }
}
class PolymorphicSerializer extends AbstractPolymorphicSerializer {
  constructor(baseClass) {
    return new.target.dd(baseClass);
  }
  static dd(baseClass) {
    var $this = this.ed();
    $this.ad_1 = baseClass;
    $this.bd_1 = emptyList();
    var tmp = $this;
    var tmp_0 = LazyThreadSafetyMode_PUBLICATION_getInstance();
    tmp.cd_1 = lazy(tmp_0, PolymorphicSerializer$descriptor$delegate$lambda($this));
    return $this;
  }
  static fd(baseClass, classAnnotations) {
    var $this = this.dd(baseClass);
    $this.bd_1 = asList(classAnnotations);
    return $this;
  }
  qc() {
    var tmp0 = this.cd_1;
    var tmp = KProperty1;
    // Inline function 'kotlin.getValue' call
    getPropertyCallableRef('descriptor', 1, tmp, PolymorphicSerializer$_get_descriptor_$ref_8tw9if(), null);
    return tmp0.d1();
  }
  toString() {
    return 'kotlinx.serialization.PolymorphicSerializer(baseClass: ' + toString(this.ad_1) + ')';
  }
}
class SerializationException extends IllegalArgumentException {
  static kd() {
    var $this = this.y7();
    init_kotlinx_serialization_SerializationException($this);
    return $this;
  }
  static ld(message, cause) {
    var $this = this.z7(message, cause);
    init_kotlinx_serialization_SerializationException($this);
    return $this;
  }
}
class MissingFieldException extends SerializationException {
  constructor(message, cause, missingFields, serialName) {
    return new.target.td(message, cause, missingFields, serialName);
  }
  static td(message, cause, missingFields, serialName) {
    var $this = this.ld(message, cause);
    captureStack($this, $this.sd_1);
    $this.qd_1 = missingFields;
    $this.rd_1 = serialName;
    return $this;
  }
  static ud(missingFields, serialName) {
    var tmp0_message = missingFields.i() === 1 ? "Field '" + missingFields.w(0) + "' is required for type with serial name '" + serialName + "', but it was missing" : 'Fields ' + toString(missingFields) + " are required for type with serial name '" + serialName + "', but they were missing";
    return this.td(tmp0_message, null, missingFields, serialName);
  }
}
class SerialDescriptor {}
function get_isNullable() {
  return false;
}
class ContextDescriptor {
  constructor(original, kClass) {
    this.wd_1 = original;
    this.xd_1 = kClass;
    this.yd_1 = this.wd_1.zd() + '<' + this.xd_1.q8() + '>';
  }
  zd() {
    return this.yd_1;
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
    return equals(this.wd_1, another.wd_1) && another.xd_1.equals(this.xd_1);
  }
  hashCode() {
    var result = this.xd_1.hashCode();
    result = imul(31, result) + getStringHashCode(this.yd_1) | 0;
    return result;
  }
  toString() {
    return 'ContextDescriptor(kClass: ' + toString(this.xd_1) + ', original: ' + toString(this.wd_1) + ')';
  }
  ae() {
    return this.wd_1.ae();
  }
  vd() {
    return this.wd_1.vd();
  }
  be() {
    return this.wd_1.be();
  }
  ce(index) {
    return this.wd_1.ce(index);
  }
  de(index) {
    return this.wd_1.de(index);
  }
}
class elementDescriptors$1 {
  constructor($this_elementDescriptors) {
    this.fe_1 = $this_elementDescriptors;
    this.ee_1 = $this_elementDescriptors.be();
  }
  g() {
    return this.ee_1 > 0;
  }
  h() {
    var tmp = this.fe_1.be();
    var _unary__edvuaz = this.ee_1;
    this.ee_1 = _unary__edvuaz - 1 | 0;
    return this.fe_1.de(tmp - _unary__edvuaz | 0);
  }
}
class elementDescriptors$$inlined$Iterable$1 {
  constructor($this_elementDescriptors) {
    this.ge_1 = $this_elementDescriptors;
  }
  f() {
    return new elementDescriptors$1(this.ge_1);
  }
}
class ClassSerialDescriptorBuilder {
  constructor(serialName) {
    this.rc_1 = serialName;
    this.sc_1 = false;
    this.tc_1 = emptyList();
    this.uc_1 = ArrayList.i4();
    this.vc_1 = HashSet.x5();
    this.wc_1 = ArrayList.i4();
    this.xc_1 = ArrayList.i4();
    this.yc_1 = ArrayList.i4();
  }
  he(elementName, descriptor, annotations, isOptional) {
    // Inline function 'kotlin.require' call
    if (!this.vc_1.o(elementName)) {
      var message = "Element with name '" + elementName + "' is already registered in " + this.rc_1;
      throw IllegalArgumentException.o2(toString(message));
    }
    // Inline function 'kotlin.collections.plusAssign' call
    this.uc_1.o(elementName);
    // Inline function 'kotlin.collections.plusAssign' call
    this.wc_1.o(descriptor);
    // Inline function 'kotlin.collections.plusAssign' call
    this.xc_1.o(annotations);
    // Inline function 'kotlin.collections.plusAssign' call
    this.yc_1.o(isOptional);
  }
  zc(elementName, descriptor, annotations, isOptional, $super) {
    annotations = annotations === VOID ? emptyList() : annotations;
    isOptional = isOptional === VOID ? false : isOptional;
    var tmp;
    if ($super === VOID) {
      this.he(elementName, descriptor, annotations, isOptional);
      tmp = Unit_instance;
    } else {
      tmp = $super.he.call(this, elementName, descriptor, annotations, isOptional);
    }
    return tmp;
  }
}
class CachedNames {}
class SerialDescriptorImpl {
  constructor(serialName, kind, elementsCount, typeParameters, builder) {
    this.ie_1 = serialName;
    this.je_1 = kind;
    this.ke_1 = elementsCount;
    this.le_1 = builder.tc_1;
    this.me_1 = toHashSet(builder.uc_1);
    var tmp = this;
    // Inline function 'kotlin.collections.toTypedArray' call
    var this_0 = builder.uc_1;
    tmp.ne_1 = copyToArray(this_0);
    this.oe_1 = compactArray(builder.wc_1);
    var tmp_0 = this;
    // Inline function 'kotlin.collections.toTypedArray' call
    var this_1 = builder.xc_1;
    tmp_0.pe_1 = copyToArray(this_1);
    this.qe_1 = toBooleanArray(builder.yc_1);
    var tmp_1 = this;
    // Inline function 'kotlin.collections.map' call
    var this_2 = withIndex(this.ne_1);
    // Inline function 'kotlin.collections.mapTo' call
    var destination = ArrayList.m(collectionSizeOrDefault(this_2, 10));
    var _iterator__ex2g4s = this_2.f();
    while (_iterator__ex2g4s.g()) {
      var item = _iterator__ex2g4s.h();
      var tmp$ret$4 = to(item.qa_1, item.pa_1);
      destination.o(tmp$ret$4);
    }
    tmp_1.re_1 = toMap(destination);
    this.se_1 = compactArray(typeParameters);
    var tmp_2 = this;
    tmp_2.te_1 = lazy_0(SerialDescriptorImpl$_hashCode$delegate$lambda(this));
  }
  zd() {
    return this.ie_1;
  }
  ae() {
    return this.je_1;
  }
  be() {
    return this.ke_1;
  }
  ue() {
    return this.me_1;
  }
  ce(index) {
    return getChecked(this.ne_1, index);
  }
  de(index) {
    return getChecked(this.oe_1, index);
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
      if (!(this.zd() === other.zd())) {
        tmp$ret$0 = false;
        break $l$block_5;
      }
      if (!contentEquals(this.se_1, other.se_1)) {
        tmp$ret$0 = false;
        break $l$block_5;
      }
      if (!(this.be() === other.be())) {
        tmp$ret$0 = false;
        break $l$block_5;
      }
      var inductionVariable = 0;
      var last = this.be();
      if (inductionVariable < last)
        do {
          var index = inductionVariable;
          inductionVariable = inductionVariable + 1 | 0;
          if (!(this.de(index).zd() === other.de(index).zd())) {
            tmp$ret$0 = false;
            break $l$block_5;
          }
          if (!equals(this.de(index).ae(), other.de(index).ae())) {
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
    return ensureNotNull(getKClassFromExpression(this).q8());
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
class PolymorphicKind extends SerialKind {}
class OPEN extends PolymorphicKind {
  constructor() {
    OPEN_instance = null;
    super();
    OPEN_instance = this;
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
class StructureKind extends SerialKind {}
class CLASS extends StructureKind {
  constructor() {
    CLASS_instance = null;
    super();
    CLASS_instance = this;
  }
}
class NullableSerializer {
  constructor(serializer) {
    this.ve_1 = serializer;
    this.we_1 = new SerialDescriptorForNullable(this.ve_1.qc());
  }
  qc() {
    return this.we_1;
  }
  equals(other) {
    if (this === other)
      return true;
    if (other == null || !getKClassFromExpression(this).equals(getKClassFromExpression(other)))
      return false;
    if (!(other instanceof NullableSerializer))
      THROW_CCE();
    if (!equals(this.ve_1, other.ve_1))
      return false;
    return true;
  }
  hashCode() {
    return hashCode(this.ve_1);
  }
}
class SerialDescriptorForNullable {
  constructor(original) {
    this.xe_1 = original;
    this.ye_1 = this.xe_1.zd() + '?';
    this.ze_1 = cachedSerialNames(this.xe_1);
  }
  zd() {
    return this.ye_1;
  }
  ue() {
    return this.ze_1;
  }
  vd() {
    return true;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof SerialDescriptorForNullable))
      return false;
    if (!equals(this.xe_1, other.xe_1))
      return false;
    return true;
  }
  toString() {
    return toString(this.xe_1) + '?';
  }
  hashCode() {
    return imul(hashCode(this.xe_1), 31);
  }
  ae() {
    return this.xe_1.ae();
  }
  be() {
    return this.xe_1.be();
  }
  ce(index) {
    return this.xe_1.ce(index);
  }
  de(index) {
    return this.xe_1.de(index);
  }
}
class PluginGeneratedSerialDescriptor {
  constructor(serialName, generatedSerializer, elementsCount) {
    generatedSerializer = generatedSerializer === VOID ? null : generatedSerializer;
    this.af_1 = serialName;
    this.bf_1 = generatedSerializer;
    this.cf_1 = elementsCount;
    this.df_1 = -1;
    var tmp = this;
    var tmp_0 = 0;
    var tmp_1 = this.cf_1;
    // Inline function 'kotlin.arrayOfNulls' call
    var tmp_2 = Array(tmp_1);
    while (tmp_0 < tmp_1) {
      tmp_2[tmp_0] = '[UNINITIALIZED]';
      tmp_0 = tmp_0 + 1 | 0;
    }
    tmp.ef_1 = tmp_2;
    var tmp_3 = this;
    // Inline function 'kotlin.arrayOfNulls' call
    var size = this.cf_1;
    tmp_3.ff_1 = Array(size);
    this.gf_1 = null;
    this.hf_1 = booleanArray(this.cf_1);
    this.if_1 = emptyMap();
    var tmp_4 = this;
    var tmp_5 = LazyThreadSafetyMode_PUBLICATION_getInstance();
    tmp_4.jf_1 = lazy(tmp_5, PluginGeneratedSerialDescriptor$childSerializers$delegate$lambda(this));
    var tmp_6 = this;
    var tmp_7 = LazyThreadSafetyMode_PUBLICATION_getInstance();
    tmp_6.kf_1 = lazy(tmp_7, PluginGeneratedSerialDescriptor$typeParameterDescriptors$delegate$lambda(this));
    var tmp_8 = this;
    var tmp_9 = LazyThreadSafetyMode_PUBLICATION_getInstance();
    tmp_8.lf_1 = lazy(tmp_9, PluginGeneratedSerialDescriptor$_hashCode$delegate$lambda(this));
  }
  zd() {
    return this.af_1;
  }
  be() {
    return this.cf_1;
  }
  ae() {
    return CLASS_getInstance();
  }
  ue() {
    return this.if_1.g1();
  }
  of() {
    var tmp0 = this.kf_1;
    var tmp = KProperty1;
    // Inline function 'kotlin.getValue' call
    getPropertyCallableRef('typeParameterDescriptors', 1, tmp, PluginGeneratedSerialDescriptor$_get_typeParameterDescriptors_$ref_jk3pka(), null);
    return tmp0.d1();
  }
  pf(name, isOptional) {
    this.df_1 = this.df_1 + 1 | 0;
    this.ef_1[this.df_1] = name;
    this.hf_1[this.df_1] = isOptional;
    this.ff_1[this.df_1] = null;
    if (this.df_1 === (this.cf_1 - 1 | 0)) {
      this.if_1 = buildIndices(this);
    }
  }
  de(index) {
    return getChecked(_get_childSerializers__7vnyfa(this), index).qc();
  }
  ce(index) {
    return getChecked(this.ef_1, index);
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
      if (!(this.zd() === other.zd())) {
        tmp$ret$0 = false;
        break $l$block_5;
      }
      if (!contentEquals(this.of(), other.of())) {
        tmp$ret$0 = false;
        break $l$block_5;
      }
      if (!(this.be() === other.be())) {
        tmp$ret$0 = false;
        break $l$block_5;
      }
      var inductionVariable = 0;
      var last = this.be();
      if (inductionVariable < last)
        do {
          var index = inductionVariable;
          inductionVariable = inductionVariable + 1 | 0;
          if (!(this.de(index).zd() === other.de(index).zd())) {
            tmp$ret$0 = false;
            break $l$block_5;
          }
          if (!equals(this.de(index).ae(), other.de(index).ae())) {
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
class StringSerializer {
  constructor() {
    StringSerializer_instance = this;
    this.qf_1 = new PrimitiveSerialDescriptor('kotlin.String', STRING_getInstance());
  }
  qc() {
    return this.qf_1;
  }
}
class IntSerializer {
  constructor() {
    IntSerializer_instance = this;
    this.rf_1 = new PrimitiveSerialDescriptor('kotlin.Int', INT_getInstance());
  }
  qc() {
    return this.rf_1;
  }
}
class PrimitiveSerialDescriptor {
  constructor(serialName, kind) {
    this.sf_1 = serialName;
    this.tf_1 = kind;
  }
  zd() {
    return this.sf_1;
  }
  ae() {
    return this.tf_1;
  }
  be() {
    return 0;
  }
  ce(index) {
    error(this);
  }
  de(index) {
    error(this);
  }
  toString() {
    return 'PrimitiveDescriptor(' + this.sf_1 + ')';
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof PrimitiveSerialDescriptor))
      return false;
    if (this.sf_1 === other.sf_1 && equals(this.tf_1, other.tf_1))
      return true;
    return false;
  }
  hashCode() {
    return getStringHashCode(this.sf_1) + imul(31, this.tf_1.hashCode()) | 0;
  }
}
class SerializableWith {}
//endregion
function PolymorphicSerializer$descriptor$delegate$lambda$lambda(this$0) {
  return ($this$buildSerialDescriptor) => {
    $this$buildSerialDescriptor.zc('type', serializer(StringCompanionObject_instance).qc());
    $this$buildSerialDescriptor.zc('value', buildSerialDescriptor('kotlinx.serialization.Polymorphic<' + this$0.ad_1.q8() + '>', CONTEXTUAL_getInstance(), []));
    $this$buildSerialDescriptor.tc_1 = this$0.bd_1;
    return Unit_instance;
  };
}
function PolymorphicSerializer$descriptor$delegate$lambda(this$0) {
  return () => {
    var tmp = OPEN_getInstance();
    return withContext(buildSerialDescriptor('kotlinx.serialization.Polymorphic', tmp, [], PolymorphicSerializer$descriptor$delegate$lambda$lambda(this$0)), this$0.ad_1);
  };
}
function PolymorphicSerializer$_get_descriptor_$ref_8tw9if() {
  return constructCallableReference((p0) => p0.qc(), 1, 0, 1);
}
function init_kotlinx_serialization_SerializationException(_this__u8e3s4) {
  captureStack(_this__u8e3s4, _this__u8e3s4.jd_1);
}
function get_nullable(_this__u8e3s4) {
  var tmp;
  if (_this__u8e3s4.qc().vd()) {
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
    throw IllegalArgumentException.o2(toString(message));
  }
  // Inline function 'kotlin.require' call
  if (!!equals(kind, CLASS_getInstance())) {
    var message_0 = "For StructureKind.CLASS please use 'buildClassSerialDescriptor' instead";
    throw IllegalArgumentException.o2(toString(message_0));
  }
  var sdBuilder = new ClassSerialDescriptorBuilder(serialName);
  builder(sdBuilder);
  return new SerialDescriptorImpl(serialName, kind, sdBuilder.uc_1.i(), toList(typeParameters), sdBuilder);
}
function _get__hashCode__tgwhef($this) {
  var tmp0 = $this.te_1;
  var tmp = KProperty1;
  // Inline function 'kotlin.getValue' call
  getPropertyCallableRef('_hashCode', 1, tmp, SerialDescriptorImpl$_get__hashCode_$ref_2v7wzp(), null);
  return tmp0.d1();
}
function SerialDescriptorImpl$_hashCode$delegate$lambda(this$0) {
  return () => hashCodeImpl(this$0, this$0.se_1);
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
var OPEN_instance;
function OPEN_getInstance() {
  if (OPEN_instance === VOID)
    new OPEN();
  return OPEN_instance;
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
var CLASS_instance;
function CLASS_getInstance() {
  if (CLASS_instance === VOID)
    new CLASS();
  return CLASS_instance;
}
function get_EMPTY_DESCRIPTOR_ARRAY() {
  _init_properties_Platform_common_kt__3qzecs();
  return EMPTY_DESCRIPTOR_ARRAY;
}
var EMPTY_DESCRIPTOR_ARRAY;
function cachedSerialNames(_this__u8e3s4) {
  _init_properties_Platform_common_kt__3qzecs();
  if (isInterface(_this__u8e3s4, CachedNames))
    return _this__u8e3s4.ue();
  var result = HashSet.q(_this__u8e3s4.be());
  var inductionVariable = 0;
  var last = _this__u8e3s4.be();
  if (inductionVariable < last)
    do {
      var i = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      // Inline function 'kotlin.collections.plusAssign' call
      var element = _this__u8e3s4.ce(i);
      result.o(element);
    }
     while (inductionVariable < last);
  return result;
}
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
  var missingFields = ArrayList.i4();
  var missingFieldsBits = goldenMask & ~seen;
  var inductionVariable = 0;
  if (inductionVariable < 32)
    do {
      var i = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      if (!((missingFieldsBits & 1) === 0)) {
        // Inline function 'kotlin.collections.plusAssign' call
        var element = descriptor.ce(i);
        missingFields.o(element);
      }
      missingFieldsBits = missingFieldsBits >>> 1 | 0;
    }
     while (inductionVariable < 32);
  throw MissingFieldException.ud(missingFields, descriptor.zd());
}
function hashCodeImpl(_this__u8e3s4, typeParams) {
  var result = getStringHashCode(_this__u8e3s4.zd());
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
    var tmp0_safe_receiver = element.zd();
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
    var tmp0_safe_receiver_0 = element_0.ae();
    var tmp1_elvis_lhs_0 = tmp0_safe_receiver_0 == null ? null : hashCode(tmp0_safe_receiver_0);
    accumulator_0 = tmp_0 + (tmp1_elvis_lhs_0 == null ? 0 : tmp1_elvis_lhs_0) | 0;
  }
  var kindHash = accumulator_0;
  result = imul(31, result) + namesHash | 0;
  result = imul(31, result) + kindHash | 0;
  return result;
}
function toStringImpl(_this__u8e3s4) {
  var tmp = until(0, _this__u8e3s4.be());
  var tmp_0 = _this__u8e3s4.zd() + '(';
  return joinToString(tmp, ', ', tmp_0, ')', VOID, VOID, toStringImpl$lambda(_this__u8e3s4));
}
function _get_childSerializers__7vnyfa($this) {
  var tmp0 = $this.jf_1;
  var tmp = KProperty1;
  // Inline function 'kotlin.getValue' call
  getPropertyCallableRef('childSerializers', 1, tmp, PluginGeneratedSerialDescriptor$_get_childSerializers_$ref_e7suca(), null);
  return tmp0.d1();
}
function _get__hashCode__tgwhef_0($this) {
  var tmp0 = $this.lf_1;
  var tmp = KProperty1;
  // Inline function 'kotlin.getValue' call
  getPropertyCallableRef('_hashCode', 1, tmp, PluginGeneratedSerialDescriptor$_get__hashCode_$ref_cmj4vz(), null);
  return tmp0.d1();
}
function buildIndices($this) {
  var indices = HashMap.r4();
  var inductionVariable = 0;
  var last = $this.ef_1.length - 1 | 0;
  if (inductionVariable <= last)
    do {
      var i = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      // Inline function 'kotlin.collections.set' call
      var key = $this.ef_1[i];
      indices.c4(key, i);
    }
     while (inductionVariable <= last);
  return indices;
}
function PluginGeneratedSerialDescriptor$childSerializers$delegate$lambda(this$0) {
  return () => {
    var tmp0_safe_receiver = this$0.bf_1;
    var tmp1_elvis_lhs = tmp0_safe_receiver == null ? null : tmp0_safe_receiver.mf();
    return tmp1_elvis_lhs == null ? get_EMPTY_SERIALIZER_ARRAY() : tmp1_elvis_lhs;
  };
}
function PluginGeneratedSerialDescriptor$_get_childSerializers_$ref_e7suca() {
  return constructCallableReference((p0) => _get_childSerializers__7vnyfa(p0), 1, 0, 3);
}
function PluginGeneratedSerialDescriptor$typeParameterDescriptors$delegate$lambda(this$0) {
  return () => {
    var tmp0_safe_receiver = this$0.bf_1;
    var tmp1_safe_receiver = tmp0_safe_receiver == null ? null : tmp0_safe_receiver.nf();
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
        var tmp$ret$2 = item.qc();
        destination.o(tmp$ret$2);
      }
      tmp = destination;
    }
    return compactArray(tmp);
  };
}
function PluginGeneratedSerialDescriptor$_get_typeParameterDescriptors_$ref_jk3pka() {
  return constructCallableReference((p0) => p0.of(), 1, 0, 4);
}
function PluginGeneratedSerialDescriptor$_hashCode$delegate$lambda(this$0) {
  return () => hashCodeImpl(this$0, this$0.of());
}
function PluginGeneratedSerialDescriptor$_get__hashCode_$ref_cmj4vz() {
  return constructCallableReference((p0) => _get__hashCode__tgwhef_0(p0), 1, 0, 5);
}
function toStringImpl$lambda($this_toStringImpl) {
  return (i) => $this_toStringImpl.ce(i) + ': ' + $this_toStringImpl.de(i).zd();
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
var StringSerializer_instance;
function StringSerializer_getInstance() {
  if (StringSerializer_instance === VOID)
    new StringSerializer();
  return StringSerializer_instance;
}
var IntSerializer_instance;
function IntSerializer_getInstance() {
  if (IntSerializer_instance === VOID)
    new IntSerializer();
  return IntSerializer_instance;
}
function error($this) {
  throw IllegalStateException.c2('Primitive descriptor ' + $this.sf_1 + ' does not have elements');
}
function getChecked(_this__u8e3s4, index) {
  if (!(0 <= index ? index <= (_this__u8e3s4.length - 1 | 0) : false))
    throw IndexOutOfBoundsException.j8('Index ' + index + ' out of bounds ' + get_indices(_this__u8e3s4).toString());
  return _this__u8e3s4[index];
}
//region block: post-declaration
initMetadataForInterface(KSerializer, 'KSerializer');
initMetadataForClass(AbstractPolymorphicSerializer, 'AbstractPolymorphicSerializer', VOID, VOID, [KSerializer]);
initMetadataForClass(PolymorphicSerializer, 'PolymorphicSerializer');
initMetadataForClass(SerializationException, 'SerializationException', SerializationException.kd);
initMetadataForClass(MissingFieldException, 'MissingFieldException');
initMetadataForInterface(SerialDescriptor, 'SerialDescriptor');
initMetadataForClass(ContextDescriptor, 'ContextDescriptor', VOID, VOID, [SerialDescriptor]);
initMetadataForClass(elementDescriptors$1);
initMetadataForClass(elementDescriptors$$inlined$Iterable$1);
initMetadataForClass(ClassSerialDescriptorBuilder, 'ClassSerialDescriptorBuilder');
initMetadataForInterface(CachedNames, 'CachedNames');
protoOf(SerialDescriptorImpl).vd = get_isNullable;
initMetadataForClass(SerialDescriptorImpl, 'SerialDescriptorImpl', VOID, VOID, [SerialDescriptor, CachedNames]);
initMetadataForClass(SerialKind, 'SerialKind');
initMetadataForObject(CONTEXTUAL, 'CONTEXTUAL');
initMetadataForClass(PolymorphicKind, 'PolymorphicKind');
initMetadataForObject(OPEN, 'OPEN');
initMetadataForClass(PrimitiveKind, 'PrimitiveKind');
initMetadataForObject(INT, 'INT');
initMetadataForObject(STRING, 'STRING');
initMetadataForClass(StructureKind, 'StructureKind');
initMetadataForObject(CLASS, 'CLASS');
initMetadataForClass(NullableSerializer, 'NullableSerializer', VOID, VOID, [KSerializer]);
initMetadataForClass(SerialDescriptorForNullable, 'SerialDescriptorForNullable', VOID, VOID, [SerialDescriptor, CachedNames]);
protoOf(PluginGeneratedSerialDescriptor).vd = get_isNullable;
initMetadataForClass(PluginGeneratedSerialDescriptor, 'PluginGeneratedSerialDescriptor', VOID, VOID, [SerialDescriptor, CachedNames]);
initMetadataForInterface(GeneratedSerializer, 'GeneratedSerializer', VOID, VOID, [KSerializer]);
initMetadataForObject(StringSerializer, 'StringSerializer', VOID, VOID, [KSerializer]);
initMetadataForObject(IntSerializer, 'IntSerializer', VOID, VOID, [KSerializer]);
protoOf(PrimitiveSerialDescriptor).vd = get_isNullable;
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
