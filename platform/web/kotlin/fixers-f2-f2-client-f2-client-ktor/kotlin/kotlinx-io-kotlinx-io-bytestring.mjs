import {
  toCharArray32huqyw9tt7kx as toCharArray,
  initMetadataForCompanion1wyw17z38v6ac as initMetadataForCompanion,
  VOID3gxj6tk5isa35 as VOID,
  createThis2j2avj17cvnv2 as createThis,
  copyOfRange3alro60z4hhf8 as copyOfRange,
  getKClassFromExpression348iqjl4fnx2f as getKClassFromExpression,
  THROW_CCE2g6jy02ryeudk as THROW_CCE,
  Unit_instance3kz1zqli6gr1r as Unit_instance,
  contentEquals1cdp6c846cfdi as contentEquals,
  contentHashCode25jw6rgkgywwr as contentHashCode,
  IndexOutOfBoundsException1qfr429iumro0 as IndexOutOfBoundsException,
  _UByte___init__impl__g9hnc49zztd2brjj2m as _UByte___init__impl__g9hnc4,
  _UByte___get_data__impl__jof9qr100nrznmlnqj as _UByte___get_data__impl__jof9qr,
  compareTo3ankvs086tmwq as compareTo,
  StringBuildermazzzhj6kkai as StringBuilder,
  _Char___init__impl__6a9atxrzocepzq3zgi as _Char___init__impl__6a9atx,
  Comparable198qfk8pnblz0 as Comparable,
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
  decodeToString1x4faah2liw2p as decodeToString,
  initMetadataForObject1cxne3s9w65el as initMetadataForObject,
} from './kotlin-kotlin-stdlib.mjs';
//region block: imports
var imul = Math.imul;
//endregion
//region block: pre-declaration
class Companion {
  constructor() {
    Companion_instance = this;
    this.x20_1 = ByteString.b21(new Int8Array(0), null);
    this.y20_1 = toCharArray('0123456789abcdef');
  }
  c21(byteArray) {
    return ByteString.b21(byteArray, null);
  }
}
class ByteString {
  constructor(data, dummy) {
    return new.target.b21(data, dummy);
  }
  static b21(data, dummy) {
    Companion_getInstance();
    var $this = createThis(this);
    $this.z20_1 = data;
    $this.a21_1 = 0;
    return $this;
  }
  static d21(data, startIndex, endIndex) {
    Companion_getInstance();
    startIndex = startIndex === VOID ? 0 : startIndex;
    endIndex = endIndex === VOID ? data.length : endIndex;
    return this.b21(copyOfRange(data, startIndex, endIndex), null);
  }
  j2() {
    return this.z20_1.length;
  }
  equals(other) {
    if (this === other)
      return true;
    if (other == null || !getKClassFromExpression(this).equals(getKClassFromExpression(other)))
      return false;
    if (!(other instanceof ByteString))
      THROW_CCE();
    if (!(other.z20_1.length === this.z20_1.length))
      return false;
    if (!(other.a21_1 === 0) && !(this.a21_1 === 0) && !(other.a21_1 === this.a21_1))
      return false;
    return contentEquals(this.z20_1, other.z20_1);
  }
  hashCode() {
    var hc = this.a21_1;
    if (hc === 0) {
      hc = contentHashCode(this.z20_1);
      this.a21_1 = hc;
    }
    return hc;
  }
  l2(index) {
    if (index < 0 || index >= this.j2())
      throw IndexOutOfBoundsException.pd('index (' + index + ') is out of byte string bounds: [0..' + this.j2() + ')');
    return this.z20_1[index];
  }
  e21(startIndex, endIndex) {
    var tmp;
    if (startIndex === endIndex) {
      tmp = Companion_getInstance().x20_1;
    } else {
      tmp = ByteString.d21(this.z20_1, startIndex, endIndex);
    }
    return tmp;
  }
  f21(startIndex, endIndex, $super) {
    endIndex = endIndex === VOID ? this.j2() : endIndex;
    return $super === VOID ? this.e21(startIndex, endIndex) : $super.e21.call(this, startIndex, endIndex);
  }
  g21(other) {
    if (other === this)
      return 0;
    var localData = this.z20_1;
    var otherData = other.z20_1;
    var inductionVariable = 0;
    var tmp0 = this.j2();
    // Inline function 'kotlin.math.min' call
    var b = other.j2();
    var last = Math.min(tmp0, b);
    if (inductionVariable < last)
      do {
        var i = inductionVariable;
        inductionVariable = inductionVariable + 1 | 0;
        // Inline function 'kotlin.toUByte' call
        var this_0 = localData[i];
        var tmp0_0 = _UByte___init__impl__g9hnc4(this_0);
        // Inline function 'kotlin.toUByte' call
        var this_1 = otherData[i];
        // Inline function 'kotlin.UByte.compareTo' call
        var other_0 = _UByte___init__impl__g9hnc4(this_1);
        // Inline function 'kotlin.UByte.toInt' call
        var tmp = _UByte___get_data__impl__jof9qr(tmp0_0) & 255;
        // Inline function 'kotlin.UByte.toInt' call
        var tmp$ret$5 = _UByte___get_data__impl__jof9qr(other_0) & 255;
        var cmp = compareTo(tmp, tmp$ret$5);
        if (!(cmp === 0))
          return cmp;
      }
       while (inductionVariable < last);
    return compareTo(this.j2(), other.j2());
  }
  d(other) {
    return this.g21(other instanceof ByteString ? other : THROW_CCE());
  }
  toString() {
    if (isEmpty(this)) {
      return 'ByteString(size=0)';
    }
    var sizeStr = this.j2().toString();
    var len = (22 + sizeStr.length | 0) + imul(this.j2(), 2) | 0;
    // Inline function 'kotlin.with' call
    var $this$with = StringBuilder.rb(len);
    $this$with.i1('ByteString(size=');
    $this$with.i1(sizeStr);
    $this$with.i1(' hex=');
    var localData = this.z20_1;
    var inductionVariable = 0;
    var last = this.j2();
    if (inductionVariable < last)
      do {
        var i = inductionVariable;
        inductionVariable = inductionVariable + 1 | 0;
        var b = localData[i];
        $this$with.k1(Companion_getInstance().y20_1[(b >>> 4 | 0) & 15]);
        $this$with.k1(Companion_getInstance().y20_1[b & 15]);
      }
       while (inductionVariable < last);
    return $this$with.k1(_Char___init__impl__6a9atx(41)).toString();
  }
  h21() {
    return this.z20_1;
  }
}
class UnsafeByteStringOperations {
  i21(array) {
    return Companion_getInstance().c21(array);
  }
}
//endregion
var Companion_instance;
function Companion_getInstance() {
  if (Companion_instance === VOID)
    new Companion();
  return Companion_instance;
}
function ByteString_0(bytes) {
  var tmp;
  // Inline function 'kotlin.collections.isEmpty' call
  if (bytes.length === 0) {
    tmp = Companion_getInstance().x20_1;
  } else {
    tmp = Companion_getInstance().c21(bytes);
  }
  return tmp;
}
function decodeToString_0(_this__u8e3s4) {
  return decodeToString(_this__u8e3s4.h21());
}
function isEmpty(_this__u8e3s4) {
  return _this__u8e3s4.j2() === 0;
}
var UnsafeByteStringOperations_instance;
function UnsafeByteStringOperations_getInstance() {
  return UnsafeByteStringOperations_instance;
}
//region block: post-declaration
initMetadataForCompanion(Companion);
initMetadataForClass(ByteString, 'ByteString', VOID, VOID, [Comparable]);
initMetadataForObject(UnsafeByteStringOperations, 'UnsafeByteStringOperations');
//endregion
//region block: init
UnsafeByteStringOperations_instance = new UnsafeByteStringOperations();
//endregion
//region block: exports
export {
  UnsafeByteStringOperations_instance as UnsafeByteStringOperations_instance2qzwn5iz9dqfo,
  ByteString_0 as ByteString3c9fk8lsh3lvs,
  ByteString as ByteString10sanmoo66key,
  decodeToString_0 as decodeToString2ede220pr5xir,
};
//endregion

//# sourceMappingURL=kotlinx-io-kotlinx-io-bytestring.mjs.map
