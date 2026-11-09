//region block: polyfills
if (typeof Math.imul === 'undefined') {
  Math.imul = function imul(a, b) {
    return (a & 4.29490176E9) * (b & 65535) + (a & 65535) * (b | 0) | 0;
  };
}
if (typeof ArrayBuffer.isView === 'undefined') {
  ArrayBuffer.isView = function (a) {
    return a != null && a.__proto__ != null && a.__proto__.__proto__ === Int8Array.prototype.__proto__;
  };
}
if (typeof Array.prototype.fill === 'undefined') {
  // Polyfill from https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Array/fill#Polyfill
  Object.defineProperty(Array.prototype, 'fill', {value: function (value) {
    // Steps 1-2.
    if (this == null) {
      throw new TypeError('this is null or not defined');
    }
    var O = Object(this); // Steps 3-5.
    var len = O.length >>> 0; // Steps 6-7.
    var start = arguments[1];
    var relativeStart = start >> 0; // Step 8.
    var k = relativeStart < 0 ? Math.max(len + relativeStart, 0) : Math.min(relativeStart, len); // Steps 9-10.
    var end = arguments[2];
    var relativeEnd = end === undefined ? len : end >> 0; // Step 11.
    var finalValue = relativeEnd < 0 ? Math.max(len + relativeEnd, 0) : Math.min(relativeEnd, len); // Step 12.
    while (k < finalValue) {
      O[k] = value;
      k++;
    }
    ; // Step 13.
    return O;
  }});
}
[Int8Array, Int16Array, Uint16Array, Int32Array, Float32Array, Float64Array].forEach(function (TypedArray) {
  if (typeof TypedArray.prototype.fill === 'undefined') {
    Object.defineProperty(TypedArray.prototype, 'fill', {value: Array.prototype.fill});
  }
});
if (typeof Math.clz32 === 'undefined') {
  Math.clz32 = function (log, LN2) {
    return function (x) {
      var asUint = x >>> 0;
      if (asUint === 0) {
        return 32;
      }
      return 31 - (log(asUint) / LN2 | 0) | 0; // the "| 0" acts like math.floor
    };
  }(Math.log, Math.LN2);
}
//endregion
//region block: imports
var imul_0 = Math.imul;
var isView = ArrayBuffer.isView;
var clz32 = Math.clz32;
//endregion
//region block: pre-declaration
class CharSequence {}
class Char {}
class Collection {}
class KtSet {}
class Entry {}
class Companion {
  m(map) {
    // Inline function 'kotlin.js.collections.toMap' call
    return createMapFrom(map);
  }
}
class KtMap {}
function asJsReadonlyMapView() {
  return createJsReadonlyMapViewFrom(this);
}
class JsMapView extends Map {}
class Unit {
  toString() {
    return 'kotlin.Unit';
  }
}
class AbstractCollection {
  constructor($box) {
    boxApply(this, $box);
  }
  i(element) {
    var tmp$ret$0;
    $l$block_0: {
      // Inline function 'kotlin.collections.any' call
      var tmp;
      if (isInterface(this, Collection)) {
        tmp = this.h();
      } else {
        tmp = false;
      }
      if (tmp) {
        tmp$ret$0 = false;
        break $l$block_0;
      }
      var _iterator__ex2g4s = this.d();
      while (_iterator__ex2g4s.e()) {
        var element_0 = _iterator__ex2g4s.f();
        if (equals(element_0, element)) {
          tmp$ret$0 = true;
          break $l$block_0;
        }
      }
      tmp$ret$0 = false;
    }
    return tmp$ret$0;
  }
  j(elements) {
    var tmp$ret$0;
    $l$block_0: {
      // Inline function 'kotlin.collections.all' call
      var tmp;
      if (isInterface(elements, Collection)) {
        tmp = elements.h();
      } else {
        tmp = false;
      }
      if (tmp) {
        tmp$ret$0 = true;
        break $l$block_0;
      }
      var _iterator__ex2g4s = elements.d();
      while (_iterator__ex2g4s.e()) {
        var element = _iterator__ex2g4s.f();
        if (!this.i(element)) {
          tmp$ret$0 = false;
          break $l$block_0;
        }
      }
      tmp$ret$0 = true;
    }
    return tmp$ret$0;
  }
  h() {
    return this.g() === 0;
  }
  toString() {
    return joinToString(this, ', ', '[', ']', VOID, VOID, AbstractCollection$toString$lambda(this));
  }
  toArray() {
    return collectionToArray(this);
  }
}
class AbstractMutableCollection extends AbstractCollection {
  toJSON() {
    return this.toArray();
  }
}
class AbstractMap {
  static d2() {
    var $this = createThis(this);
    $this.b2_1 = null;
    $this.c2_1 = null;
    return $this;
  }
  n(key) {
    return !(implFindEntry(this, key) == null);
  }
  o(value) {
    var tmp0 = this.s();
    var tmp$ret$0;
    $l$block_0: {
      // Inline function 'kotlin.collections.any' call
      var tmp;
      if (isInterface(tmp0, Collection)) {
        tmp = tmp0.h();
      } else {
        tmp = false;
      }
      if (tmp) {
        tmp$ret$0 = false;
        break $l$block_0;
      }
      var _iterator__ex2g4s = tmp0.d();
      while (_iterator__ex2g4s.e()) {
        var element = _iterator__ex2g4s.f();
        if (equals(element.l(), value)) {
          tmp$ret$0 = true;
          break $l$block_0;
        }
      }
      tmp$ret$0 = false;
    }
    return tmp$ret$0;
  }
  g2(entry) {
    if (!(!(entry == null) ? isInterface(entry, Entry) : false))
      return false;
    var key = entry.k();
    var value = entry.l();
    // Inline function 'kotlin.collections.get' call
    var ourValue = (isInterface(this, KtMap) ? this : THROW_CCE()).p(key);
    if (!equals(value, ourValue)) {
      return false;
    }
    var tmp;
    if (ourValue == null) {
      // Inline function 'kotlin.collections.containsKey' call
      tmp = !(isInterface(this, KtMap) ? this : THROW_CCE()).n(key);
    } else {
      tmp = false;
    }
    if (tmp) {
      return false;
    }
    return true;
  }
  equals(other) {
    if (other === this)
      return true;
    if (!(!(other == null) ? isInterface(other, KtMap) : false))
      return false;
    if (!(this.g() === other.g()))
      return false;
    var tmp0 = other.s();
    var tmp$ret$0;
    $l$block_0: {
      // Inline function 'kotlin.collections.all' call
      var tmp;
      if (isInterface(tmp0, Collection)) {
        tmp = tmp0.h();
      } else {
        tmp = false;
      }
      if (tmp) {
        tmp$ret$0 = true;
        break $l$block_0;
      }
      var _iterator__ex2g4s = tmp0.d();
      while (_iterator__ex2g4s.e()) {
        var element = _iterator__ex2g4s.f();
        if (!this.g2(element)) {
          tmp$ret$0 = false;
          break $l$block_0;
        }
      }
      tmp$ret$0 = true;
    }
    return tmp$ret$0;
  }
  p(key) {
    var tmp0_safe_receiver = implFindEntry(this, key);
    return tmp0_safe_receiver == null ? null : tmp0_safe_receiver.l();
  }
  hashCode() {
    return hashCode(this.s());
  }
  h() {
    return this.g() === 0;
  }
  g() {
    return this.s().g();
  }
  q() {
    if (this.b2_1 == null) {
      var tmp = this;
      tmp.b2_1 = new AbstractMap$keys$1(this);
    }
    return ensureNotNull(this.b2_1);
  }
  toString() {
    var tmp = this.s();
    return joinToString(tmp, ', ', '{', '}', VOID, VOID, AbstractMap$toString$lambda(this));
  }
  r() {
    if (this.c2_1 == null) {
      var tmp = this;
      tmp.c2_1 = new AbstractMap$values$1(this);
    }
    return ensureNotNull(this.c2_1);
  }
}
class AbstractMutableMap extends AbstractMap {
  static a2() {
    var $this = this.d2();
    $this.y1_1 = null;
    $this.z1_1 = null;
    return $this;
  }
  e2() {
    return new HashMapKeysDefault(this);
  }
  f2() {
    return new HashMapValuesDefault(this);
  }
  q() {
    var tmp0_elvis_lhs = this.y1_1;
    var tmp;
    if (tmp0_elvis_lhs == null) {
      // Inline function 'kotlin.also' call
      var this_0 = this.e2();
      this.y1_1 = this_0;
      tmp = this_0;
    } else {
      tmp = tmp0_elvis_lhs;
    }
    return tmp;
  }
  r() {
    var tmp0_elvis_lhs = this.z1_1;
    var tmp;
    if (tmp0_elvis_lhs == null) {
      // Inline function 'kotlin.also' call
      var this_0 = this.f2();
      this.z1_1 = this_0;
      tmp = this_0;
    } else {
      tmp = tmp0_elvis_lhs;
    }
    return tmp;
  }
}
class AbstractMutableSet extends AbstractMutableCollection {
  equals(other) {
    if (other === this)
      return true;
    if (!(!(other == null) ? isInterface(other, KtSet) : false))
      return false;
    return Companion_instance_3.h2(this, other);
  }
  hashCode() {
    return Companion_instance_3.i2(this);
  }
}
class HashMap extends AbstractMutableMap {
  static p2(internalMap) {
    var $this = this.a2();
    init_kotlin_collections_HashMap($this);
    $this.n2_1 = internalMap;
    return $this;
  }
  static q2() {
    return this.p2(InternalHashMap.b3());
  }
  n(key) {
    return this.n2_1.c3(key);
  }
  o(value) {
    return this.n2_1.o(value);
  }
  e2() {
    return new HashMapKeys(this.n2_1);
  }
  f2() {
    return new HashMapValues(this.n2_1);
  }
  s() {
    var tmp0_elvis_lhs = this.o2_1;
    var tmp;
    if (tmp0_elvis_lhs == null) {
      // Inline function 'kotlin.also' call
      var this_0 = new HashMapEntrySet(this.n2_1);
      this.o2_1 = this_0;
      tmp = this_0;
    } else {
      tmp = tmp0_elvis_lhs;
    }
    return tmp;
  }
  p(key) {
    return this.n2_1.p(key);
  }
  f1(key, value) {
    return this.n2_1.f1(key, value);
  }
  g() {
    return this.n2_1.g();
  }
}
class HashMapKeys extends AbstractMutableSet {
  constructor(backing) {
    super();
    this.d3_1 = backing;
  }
  g() {
    return this.d3_1.g();
  }
  h() {
    return this.d3_1.g() === 0;
  }
  i(element) {
    return this.d3_1.c3(element);
  }
  d() {
    return this.d3_1.e3();
  }
}
class HashMapValues extends AbstractMutableCollection {
  constructor(backing) {
    super();
    this.f3_1 = backing;
  }
  g() {
    return this.f3_1.g();
  }
  h() {
    return this.f3_1.g() === 0;
  }
  g3(element) {
    return this.f3_1.o(element);
  }
  i(element) {
    if (!true)
      return false;
    return this.g3(element);
  }
  d() {
    return this.f3_1.h3();
  }
}
class HashMapEntrySetBase extends AbstractMutableSet {
  constructor(backing) {
    super();
    this.j3_1 = backing;
  }
  g() {
    return this.j3_1.g();
  }
  h() {
    return this.j3_1.g() === 0;
  }
  l3(element) {
    return this.j3_1.m3(element);
  }
  i(element) {
    if (!(!(element == null) ? isInterface(element, Entry) : false))
      return false;
    return this.l3((!(element == null) ? isInterface(element, Entry) : false) ? element : THROW_CCE());
  }
  j(elements) {
    return this.j3_1.n3(elements);
  }
}
class HashMapEntrySet extends HashMapEntrySetBase {
  d() {
    return this.j3_1.k3();
  }
}
class HashMapKeysDefault$iterator$1 {
  constructor($entryIterator) {
    this.o3_1 = $entryIterator;
  }
  e() {
    return this.o3_1.e();
  }
  f() {
    return this.o3_1.f().k();
  }
}
class HashMapKeysDefault extends AbstractMutableSet {
  constructor(backingMap) {
    super();
    this.p3_1 = backingMap;
  }
  c3(element) {
    return this.p3_1.n(element);
  }
  i(element) {
    if (!true)
      return false;
    return this.c3(element);
  }
  d() {
    var entryIterator = this.p3_1.s().d();
    return new HashMapKeysDefault$iterator$1(entryIterator);
  }
  g() {
    return this.p3_1.g();
  }
}
class HashMapValuesDefault$iterator$1 {
  constructor($entryIterator) {
    this.q3_1 = $entryIterator;
  }
  e() {
    return this.q3_1.e();
  }
  f() {
    return this.q3_1.f().l();
  }
}
class HashMapValuesDefault extends AbstractMutableCollection {
  constructor(backingMap) {
    super();
    this.r3_1 = backingMap;
  }
  g3(element) {
    return this.r3_1.o(element);
  }
  i(element) {
    if (!true)
      return false;
    return this.g3(element);
  }
  d() {
    var entryIterator = this.r3_1.s().d();
    return new HashMapValuesDefault$iterator$1(entryIterator);
  }
  g() {
    return this.r3_1.g();
  }
}
class Companion_0 {
  constructor() {
    this.f4_1 = -1640531527;
    this.g4_1 = 8;
    this.h4_1 = 2;
    this.i4_1 = -1;
  }
}
class Itr {
  constructor(map) {
    this.j4_1 = map;
    this.k4_1 = 0;
    this.l4_1 = -1;
    this.m4_1 = this.j4_1.y2_1;
    this.n4();
  }
  n4() {
    while (this.k4_1 < this.j4_1.w2_1 && this.j4_1.t2_1[this.k4_1] < 0) {
      this.k4_1 = this.k4_1 + 1 | 0;
    }
  }
  e() {
    return this.k4_1 < this.j4_1.w2_1;
  }
  o4() {
    if (!(this.j4_1.y2_1 === this.m4_1))
      throw ConcurrentModificationException.p4();
  }
}
class KeysItr extends Itr {
  f() {
    this.o4();
    if (this.k4_1 >= this.j4_1.w2_1)
      throw NoSuchElementException.x4();
    var tmp = this;
    var _unary__edvuaz = this.k4_1;
    this.k4_1 = _unary__edvuaz + 1 | 0;
    tmp.l4_1 = _unary__edvuaz;
    var result = this.j4_1.r2_1[this.l4_1];
    this.n4();
    return result;
  }
}
class ValuesItr extends Itr {
  f() {
    this.o4();
    if (this.k4_1 >= this.j4_1.w2_1)
      throw NoSuchElementException.x4();
    var tmp = this;
    var _unary__edvuaz = this.k4_1;
    this.k4_1 = _unary__edvuaz + 1 | 0;
    tmp.l4_1 = _unary__edvuaz;
    var result = ensureNotNull(this.j4_1.s2_1)[this.l4_1];
    this.n4();
    return result;
  }
}
class EntriesItr extends Itr {
  f() {
    this.o4();
    if (this.k4_1 >= this.j4_1.w2_1)
      throw NoSuchElementException.x4();
    var tmp = this;
    var _unary__edvuaz = this.k4_1;
    this.k4_1 = _unary__edvuaz + 1 | 0;
    tmp.l4_1 = _unary__edvuaz;
    var result = new EntryRef(this.j4_1, this.l4_1);
    this.n4();
    return result;
  }
  g5() {
    if (this.k4_1 >= this.j4_1.w2_1)
      throw NoSuchElementException.x4();
    var tmp = this;
    var _unary__edvuaz = this.k4_1;
    this.k4_1 = _unary__edvuaz + 1 | 0;
    tmp.l4_1 = _unary__edvuaz;
    // Inline function 'kotlin.hashCode' call
    var tmp0_safe_receiver = this.j4_1.r2_1[this.l4_1];
    var tmp1_elvis_lhs = tmp0_safe_receiver == null ? null : hashCode(tmp0_safe_receiver);
    var tmp_0 = tmp1_elvis_lhs == null ? 0 : tmp1_elvis_lhs;
    // Inline function 'kotlin.hashCode' call
    var tmp0_safe_receiver_0 = ensureNotNull(this.j4_1.s2_1)[this.l4_1];
    var tmp1_elvis_lhs_0 = tmp0_safe_receiver_0 == null ? null : hashCode(tmp0_safe_receiver_0);
    var result = tmp_0 ^ (tmp1_elvis_lhs_0 == null ? 0 : tmp1_elvis_lhs_0);
    this.n4();
    return result;
  }
  h5(sb) {
    if (this.k4_1 >= this.j4_1.w2_1)
      throw NoSuchElementException.x4();
    var tmp = this;
    var _unary__edvuaz = this.k4_1;
    this.k4_1 = _unary__edvuaz + 1 | 0;
    tmp.l4_1 = _unary__edvuaz;
    var key = this.j4_1.r2_1[this.l4_1];
    if (equals(key, this.j4_1))
      sb.j5('(this Map)');
    else
      sb.i5(key);
    sb.k5(_Char___init__impl__6a9atx(61));
    var value = ensureNotNull(this.j4_1.s2_1)[this.l4_1];
    if (equals(value, this.j4_1))
      sb.j5('(this Map)');
    else
      sb.i5(value);
    this.n4();
  }
}
class EntryRef {
  constructor(map, index) {
    this.w3_1 = map;
    this.x3_1 = index;
    this.y3_1 = this.w3_1.y2_1;
  }
  k() {
    checkForComodification(this);
    return this.w3_1.r2_1[this.x3_1];
  }
  l() {
    checkForComodification(this);
    return ensureNotNull(this.w3_1.s2_1)[this.x3_1];
  }
  equals(other) {
    var tmp;
    var tmp_0;
    if (!(other == null) ? isInterface(other, Entry) : false) {
      tmp_0 = equals(other.k(), this.k());
    } else {
      tmp_0 = false;
    }
    if (tmp_0) {
      tmp = equals(other.l(), this.l());
    } else {
      tmp = false;
    }
    return tmp;
  }
  hashCode() {
    // Inline function 'kotlin.hashCode' call
    var tmp0_safe_receiver = this.k();
    var tmp1_elvis_lhs = tmp0_safe_receiver == null ? null : hashCode(tmp0_safe_receiver);
    var tmp = tmp1_elvis_lhs == null ? 0 : tmp1_elvis_lhs;
    // Inline function 'kotlin.hashCode' call
    var tmp0_safe_receiver_0 = this.l();
    var tmp1_elvis_lhs_0 = tmp0_safe_receiver_0 == null ? null : hashCode(tmp0_safe_receiver_0);
    return tmp ^ (tmp1_elvis_lhs_0 == null ? 0 : tmp1_elvis_lhs_0);
  }
  toString() {
    return toString_0(this.k()) + '=' + toString_0(this.l());
  }
}
class InternalMap {}
function containsAllEntries(m) {
  var tmp$ret$0;
  $l$block_0: {
    // Inline function 'kotlin.collections.all' call
    var tmp;
    if (isInterface(m, Collection)) {
      tmp = m.h();
    } else {
      tmp = false;
    }
    if (tmp) {
      tmp$ret$0 = true;
      break $l$block_0;
    }
    var _iterator__ex2g4s = m.d();
    while (_iterator__ex2g4s.e()) {
      var element = _iterator__ex2g4s.f();
      // Inline function 'kotlin.js.unsafeCast' call
      // Inline function 'kotlin.js.asDynamic' call
      var entry = element;
      var tmp_0;
      if (!(entry == null) ? isInterface(entry, Entry) : false) {
        tmp_0 = this.p5(entry);
      } else {
        tmp_0 = false;
      }
      if (!tmp_0) {
        tmp$ret$0 = false;
        break $l$block_0;
      }
    }
    tmp$ret$0 = true;
  }
  return tmp$ret$0;
}
class InternalHashMap {
  constructor(keysArray, valuesArray, presenceArray, hashArray, maxProbeDistance, length) {
    return new.target.l5(keysArray, valuesArray, presenceArray, hashArray, maxProbeDistance, length);
  }
  static l5(keysArray, valuesArray, presenceArray, hashArray, maxProbeDistance, length) {
    var $this = createThis(this);
    $this.r2_1 = keysArray;
    $this.s2_1 = valuesArray;
    $this.t2_1 = presenceArray;
    $this.u2_1 = hashArray;
    $this.v2_1 = maxProbeDistance;
    $this.w2_1 = length;
    $this.x2_1 = computeShift(Companion_instance_0, _get_hashSize__tftcho($this));
    $this.y2_1 = 0;
    $this.z2_1 = 0;
    $this.a3_1 = false;
    return $this;
  }
  g() {
    return this.z2_1;
  }
  static b3() {
    return this.m5(8);
  }
  static m5(initialCapacity) {
    return this.l5(arrayOfUninitializedElements(initialCapacity), null, new Int32Array(initialCapacity), new Int32Array(computeHashSize(Companion_instance_0, initialCapacity)), 2, 0);
  }
  n5() {
    this.e4();
    this.a3_1 = true;
  }
  o(value) {
    return findValue(this, value) >= 0;
  }
  p(key) {
    var index = findKey(this, key);
    if (index < 0)
      return null;
    return ensureNotNull(this.s2_1)[index];
  }
  c3(key) {
    return findKey(this, key) >= 0;
  }
  f1(key, value) {
    var index = addKey(this, key);
    var valuesArray = allocateValuesArray(this);
    if (index < 0) {
      var oldValue = valuesArray[(-index | 0) - 1 | 0];
      valuesArray[(-index | 0) - 1 | 0] = value;
      return oldValue;
    } else {
      valuesArray[index] = value;
      return null;
    }
  }
  equals(other) {
    var tmp;
    if (other === this) {
      tmp = true;
    } else {
      var tmp_0;
      if (!(other == null) ? isInterface(other, KtMap) : false) {
        tmp_0 = contentEquals(this, other);
      } else {
        tmp_0 = false;
      }
      tmp = tmp_0;
    }
    return tmp;
  }
  hashCode() {
    var result = 0;
    var it = this.k3();
    while (it.e()) {
      result = result + it.g5() | 0;
    }
    return result;
  }
  toString() {
    var sb = StringBuilder.o5(2 + imul_0(this.z2_1, 3) | 0);
    sb.j5('{');
    var i = 0;
    var it = this.k3();
    while (it.e()) {
      if (i > 0) {
        sb.j5(', ');
      }
      it.h5(sb);
      i = i + 1 | 0;
    }
    sb.j5('}');
    return sb.toString();
  }
  e4() {
    if (this.a3_1)
      throw UnsupportedOperationException.e1();
  }
  m3(entry) {
    var index = findKey(this, entry.k());
    if (index < 0)
      return false;
    return equals(ensureNotNull(this.s2_1)[index], entry.l());
  }
  p5(entry) {
    return this.m3(isInterface(entry, Entry) ? entry : THROW_CCE());
  }
  e3() {
    return new KeysItr(this);
  }
  h3() {
    return new ValuesItr(this);
  }
  k3() {
    return new EntriesItr(this);
  }
}
class EmptyHolder {
  constructor() {
    EmptyHolder_instance = this;
    var tmp = this;
    // Inline function 'kotlin.also' call
    var this_0 = InternalHashMap.m5(0);
    this_0.n5();
    tmp.q5_1 = LinkedHashMap.r5(this_0);
  }
}
class LinkedHashMap extends HashMap {
  static z() {
    var $this = this.q2();
    init_kotlin_collections_LinkedHashMap($this);
    return $this;
  }
  static r5(internalMap) {
    var $this = this.p2(internalMap);
    init_kotlin_collections_LinkedHashMap($this);
    return $this;
  }
  a1() {
    this.n2_1.n5();
    var tmp;
    if (this.g() > 0) {
      tmp = this;
    } else {
      // Inline function 'kotlin.js.unsafeCast' call
      // Inline function 'kotlin.js.asDynamic' call
      tmp = EmptyHolder_getInstance().q5_1;
    }
    return tmp;
  }
}
class Exception extends Error {
  static x5() {
    var $this = createExternalThis(this, Error, [null]);
    setPropertiesToThrowableInstance($this);
    init_kotlin_Exception($this);
    return $this;
  }
  static y5(message) {
    var $this = createExternalThis(this, Error, [message]);
    setPropertiesToThrowableInstance($this, message);
    init_kotlin_Exception($this);
    return $this;
  }
}
class RuntimeException extends Exception {
  static s5() {
    var $this = this.x5();
    init_kotlin_RuntimeException($this);
    return $this;
  }
  static b4(message) {
    var $this = this.y5(message);
    init_kotlin_RuntimeException($this);
    return $this;
  }
}
class UnsupportedOperationException extends RuntimeException {
  static e1() {
    var $this = this.s5();
    init_kotlin_UnsupportedOperationException($this);
    return $this;
  }
}
class IllegalArgumentException extends RuntimeException {
  static v5() {
    var $this = this.s5();
    init_kotlin_IllegalArgumentException($this);
    return $this;
  }
  static v1(message) {
    var $this = this.b4(message);
    init_kotlin_IllegalArgumentException($this);
    return $this;
  }
}
class NoSuchElementException extends RuntimeException {
  static x4() {
    var $this = this.s5();
    init_kotlin_NoSuchElementException($this);
    return $this;
  }
}
class IllegalStateException extends RuntimeException {
  static z5() {
    var $this = this.s5();
    init_kotlin_IllegalStateException($this);
    return $this;
  }
  static j1(message) {
    var $this = this.b4(message);
    init_kotlin_IllegalStateException($this);
    return $this;
  }
}
class ConcurrentModificationException extends RuntimeException {
  static p4() {
    var $this = this.s5();
    init_kotlin_ConcurrentModificationException($this);
    return $this;
  }
  static v3(message) {
    var $this = this.b4(message);
    init_kotlin_ConcurrentModificationException($this);
    return $this;
  }
}
class NullPointerException extends RuntimeException {
  static n1() {
    var $this = this.s5();
    init_kotlin_NullPointerException($this);
    return $this;
  }
}
class ClassCastException extends RuntimeException {
  static r1() {
    var $this = this.s5();
    init_kotlin_ClassCastException($this);
    return $this;
  }
}
class KClassImpl {
  equals(other) {
    var tmp;
    if (other instanceof NothingKClassImpl) {
      tmp = false;
    } else {
      if (other instanceof KClassImpl) {
        tmp = (equals(this.d6(), other.d6()) && this.e6() == other.e6());
      } else {
        tmp = false;
      }
    }
    return tmp;
  }
  hashCode() {
    var tmp0_safe_receiver = this.e6();
    var tmp1_elvis_lhs = tmp0_safe_receiver == null ? null : getStringHashCode(tmp0_safe_receiver);
    return tmp1_elvis_lhs == null ? 0 : tmp1_elvis_lhs;
  }
  toString() {
    return 'class ' + this.e6();
  }
}
class PrimitiveKClassImpl extends KClassImpl {
  constructor(jClass, simpleName, isInstanceFunction) {
    super();
    this.a6_1 = jClass;
    this.b6_1 = simpleName;
    this.c6_1 = isInstanceFunction;
  }
  d6() {
    return this.a6_1;
  }
  e6() {
    return this.b6_1;
  }
}
class NothingKClassImpl extends KClassImpl {}
class StringBuilder {
  constructor(content) {
    return new.target.f6(content);
  }
  static f6(content) {
    var $this = createThis(this);
    $this.a_1 = content;
    return $this;
  }
  static o5(capacity) {
    return this.b();
  }
  static b() {
    return this.f6('');
  }
  k5(value) {
    this.a_1 = this.a_1 + toString(value);
    return this;
  }
  c(value) {
    this.a_1 = this.a_1 + toString_0(value);
    return this;
  }
  i5(value) {
    this.a_1 = this.a_1 + toString_0(value);
    return this;
  }
  j5(value) {
    var tmp = this;
    var tmp_0 = this.a_1;
    tmp.a_1 = tmp_0 + (value == null ? 'null' : value);
    return this;
  }
  toString() {
    return this.a_1;
  }
}
class Companion_1 {
  constructor() {
    this.c4_1 = 2147483639;
  }
  d4(oldCapacity, minCapacity) {
    var newCapacity = oldCapacity + (oldCapacity >> 1) | 0;
    if ((newCapacity - minCapacity | 0) < 0)
      newCapacity = minCapacity;
    if ((newCapacity - 2147483639 | 0) > 0)
      newCapacity = minCapacity > 2147483639 ? 2147483647 : 2147483639;
    return newCapacity;
  }
}
class AbstractMap$keys$1$iterator$1 {
  constructor($entryIterator) {
    this.g6_1 = $entryIterator;
  }
  e() {
    return this.g6_1.e();
  }
  f() {
    return this.g6_1.f().k();
  }
}
class AbstractMap$values$1$iterator$1 {
  constructor($entryIterator) {
    this.h6_1 = $entryIterator;
  }
  e() {
    return this.h6_1.e();
  }
  f() {
    return this.h6_1.f().l();
  }
}
class Companion_2 {}
class AbstractSet extends AbstractCollection {
  equals(other) {
    if (other === this)
      return true;
    if (!(!(other == null) ? isInterface(other, KtSet) : false))
      return false;
    return Companion_instance_3.h2(this, other);
  }
  hashCode() {
    return Companion_instance_3.i2(this);
  }
}
class AbstractMap$keys$1 extends AbstractSet {
  constructor(this$0, $box) {
    if ($box === VOID)
      $box = {};
    $box.i6_1 = this$0;
    super($box);
  }
  c3(element) {
    return this.i6_1.n(element);
  }
  i(element) {
    if (!true)
      return false;
    return this.c3(element);
  }
  d() {
    var entryIterator = this.i6_1.s().d();
    return new AbstractMap$keys$1$iterator$1(entryIterator);
  }
  g() {
    return this.i6_1.g();
  }
}
class AbstractMap$values$1 extends AbstractCollection {
  constructor(this$0, $box) {
    if ($box === VOID)
      $box = {};
    $box.j6_1 = this$0;
    super($box);
  }
  g3(element) {
    return this.j6_1.o(element);
  }
  i(element) {
    if (!true)
      return false;
    return this.g3(element);
  }
  d() {
    var entryIterator = this.j6_1.s().d();
    return new AbstractMap$values$1$iterator$1(entryIterator);
  }
  g() {
    return this.j6_1.g();
  }
}
class Companion_3 {
  i2(c) {
    var hashCode_0 = 0;
    var _iterator__ex2g4s = c.d();
    while (_iterator__ex2g4s.e()) {
      var element = _iterator__ex2g4s.f();
      var tmp = hashCode_0;
      var tmp1_elvis_lhs = element == null ? null : hashCode(element);
      hashCode_0 = tmp + (tmp1_elvis_lhs == null ? 0 : tmp1_elvis_lhs) | 0;
    }
    return hashCode_0;
  }
  h2(c, other) {
    if (!(c.g() === other.g()))
      return false;
    return c.j(other);
  }
}
//endregion
function joinToString(_this__u8e3s4, separator, prefix, postfix, limit, truncated, transform) {
  separator = separator === VOID ? ', ' : separator;
  prefix = prefix === VOID ? '' : prefix;
  postfix = postfix === VOID ? '' : postfix;
  limit = limit === VOID ? -1 : limit;
  truncated = truncated === VOID ? '...' : truncated;
  transform = transform === VOID ? null : transform;
  return joinTo(_this__u8e3s4, StringBuilder.b(), separator, prefix, postfix, limit, truncated, transform).toString();
}
function joinTo(_this__u8e3s4, buffer, separator, prefix, postfix, limit, truncated, transform) {
  separator = separator === VOID ? ', ' : separator;
  prefix = prefix === VOID ? '' : prefix;
  postfix = postfix === VOID ? '' : postfix;
  limit = limit === VOID ? -1 : limit;
  truncated = truncated === VOID ? '...' : truncated;
  transform = transform === VOID ? null : transform;
  buffer.c(prefix);
  var count = 0;
  var _iterator__ex2g4s = _this__u8e3s4.d();
  $l$loop: while (_iterator__ex2g4s.e()) {
    var element = _iterator__ex2g4s.f();
    count = count + 1 | 0;
    if (count > 1) {
      buffer.c(separator);
    }
    if (limit < 0 || count <= limit) {
      appendElement(buffer, element, transform);
    } else
      break $l$loop;
  }
  if (limit >= 0 && count > limit) {
    buffer.c(truncated);
  }
  buffer.c(postfix);
  return buffer;
}
function coerceAtMost(_this__u8e3s4, maximumValue) {
  return _this__u8e3s4 > maximumValue ? maximumValue : _this__u8e3s4;
}
function coerceAtLeast(_this__u8e3s4, minimumValue) {
  return _this__u8e3s4 < minimumValue ? minimumValue : _this__u8e3s4;
}
function _Char___init__impl__6a9atx(value) {
  return value;
}
function _get_value__a43j40($this) {
  return $this;
}
function toString($this) {
  // Inline function 'kotlin.js.unsafeCast' call
  return String.fromCharCode(_get_value__a43j40($this));
}
var Companion_instance;
function Companion_getInstance() {
  return Companion_instance;
}
function fromJsMap(map) {
  return Companion_instance.m(map);
}
function toString_0(_this__u8e3s4) {
  var tmp1_elvis_lhs = _this__u8e3s4 == null ? null : toString_1(_this__u8e3s4);
  return tmp1_elvis_lhs == null ? 'null' : tmp1_elvis_lhs;
}
function abs(_this__u8e3s4) {
  var tmp;
  // Inline function 'kotlin.js.internal.isNegative' call
  if (_this__u8e3s4 < 0) {
    // Inline function 'kotlin.js.internal.unaryMinus' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp = -_this__u8e3s4;
  } else {
    tmp = _this__u8e3s4;
  }
  return tmp;
}
function get_buf() {
  _init_properties_bitUtils_kt__nfcg4k();
  return buf;
}
var buf;
function get_bufFloat64() {
  _init_properties_bitUtils_kt__nfcg4k();
  return bufFloat64;
}
var bufFloat64;
var bufFloat32;
function get_bufInt32() {
  _init_properties_bitUtils_kt__nfcg4k();
  return bufInt32;
}
var bufInt32;
function get_lowIndex() {
  _init_properties_bitUtils_kt__nfcg4k();
  return lowIndex;
}
var lowIndex;
function get_highIndex() {
  _init_properties_bitUtils_kt__nfcg4k();
  return highIndex;
}
var highIndex;
function getNumberHashCode(obj) {
  _init_properties_bitUtils_kt__nfcg4k();
  // Inline function 'kotlin.js.jsBitwiseOr' call
  // Inline function 'kotlin.js.unsafeCast' call
  // Inline function 'kotlin.js.asDynamic' call
  if ((obj | 0) === obj) {
    return numberToInt(obj);
  }
  get_bufFloat64()[0] = obj;
  return imul_0(get_bufInt32()[get_highIndex()], 31) + get_bufInt32()[get_lowIndex()] | 0;
}
var properties_initialized_bitUtils_kt_i2bo3e;
function _init_properties_bitUtils_kt__nfcg4k() {
  if (!properties_initialized_bitUtils_kt_i2bo3e) {
    properties_initialized_bitUtils_kt_i2bo3e = true;
    buf = new ArrayBuffer(8);
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    bufFloat64 = new Float64Array(get_buf());
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    bufFloat32 = new Float32Array(get_buf());
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    bufInt32 = new Int32Array(get_buf());
    // Inline function 'kotlin.run' call
    get_bufFloat64()[0] = -1.0;
    lowIndex = !(get_bufInt32()[0] === 0) ? 1 : 0;
    highIndex = 1 - get_lowIndex() | 0;
  }
}
function contentEqualsInternal(_this__u8e3s4, other) {
  // Inline function 'kotlin.js.asDynamic' call
  var a = _this__u8e3s4;
  // Inline function 'kotlin.js.asDynamic' call
  var b = other;
  if (a === b)
    return true;
  if (a == null || b == null || !isArrayish(b) || a.length != b.length)
    return false;
  var inductionVariable = 0;
  var last = a.length;
  if (inductionVariable < last)
    do {
      var i = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      if (!equals(a[i], b[i])) {
        return false;
      }
    }
     while (inductionVariable < last);
  return true;
}
function contentHashCodeInternal(_this__u8e3s4) {
  // Inline function 'kotlin.js.asDynamic' call
  var a = _this__u8e3s4;
  if (a == null)
    return 0;
  var result = 1;
  var inductionVariable = 0;
  var last = a.length;
  if (inductionVariable < last)
    do {
      var i = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      result = imul_0(result, 31) + hashCode(a[i]) | 0;
    }
     while (inductionVariable < last);
  return result;
}
function createJsReadonlyMapViewFrom(map) {
  var tmp = createJsReadonlyMapViewFrom$lambda(map);
  var tmp_0 = createJsReadonlyMapViewFrom$lambda_0(map);
  var tmp_1 = createJsReadonlyMapViewFrom$lambda_1(map);
  // Inline function 'kotlin.js.asDynamic' call
  var tmp_2 = UNSUPPORTED_OPERATION$ref();
  // Inline function 'kotlin.js.asDynamic' call
  var tmp_3 = UNSUPPORTED_OPERATION$ref();
  // Inline function 'kotlin.js.asDynamic' call
  var tmp_4 = UNSUPPORTED_OPERATION$ref();
  var tmp_5 = createJsReadonlyMapViewFrom$lambda_2(map);
  var tmp_6 = createJsReadonlyMapViewFrom$lambda_3(map);
  var tmp_7 = createJsReadonlyMapViewFrom$lambda_4(map);
  return createJsMapViewWith(tmp, tmp_0, tmp_1, tmp_2, tmp_3, tmp_4, tmp_5, tmp_6, tmp_7, createJsReadonlyMapViewFrom$lambda_5);
}
function createMapFrom(map) {
  // Inline function 'kotlin.collections.buildMapInternal' call
  // Inline function 'kotlin.apply' call
  var this_0 = LinkedHashMap.z();
  forEach(createMapFrom$lambda(this_0), map);
  return this_0.a1();
}
function UNSUPPORTED_OPERATION() {
  throw UnsupportedOperationException.e1();
}
function createJsIteratorFrom(iterator, transform) {
  var tmp;
  if (transform === VOID) {
    tmp = createJsIteratorFrom$lambda;
  } else {
    tmp = transform;
  }
  transform = tmp;
  var iteratorNext = createJsIteratorFrom$lambda_0(iterator);
  var iteratorHasNext = createJsIteratorFrom$lambda_1(iterator);
  var iteratorConstructor = typeof Iterator === 'function' ? Iterator : Object;
  var jsIterator = Object.create(iteratorConstructor.prototype);
  jsIterator.next = function () {
    var result = {done: !iteratorHasNext()};
    if (!result.done)
      result.value = transform(iteratorNext());
    return result;
  };
  jsIterator[Symbol.iterator] = function () {
    return this;
  };
  return jsIterator;
}
function forEach(cb, collection, thisArg) {
  thisArg = thisArg === VOID ? undefined : thisArg;
  var iterator = collection.entries();
  var result = iterator.next();
  while (!result.done) {
    var value = result.value;
    // Inline function 'kotlin.js.asDynamic' call
    cb.call(thisArg, value[1], value[0], collection);
    result = iterator.next();
  }
}
function createJsMapViewWith(mapSize, mapGet, mapContains, mapPut, mapRemove, mapClear, keysIterator, valuesIterator, entriesIterator, forEach) {
  // Inline function 'kotlin.also' call
  var this_0 = objectCreate(protoOf(JsMapView));
  this_0[Symbol.iterator] = entriesIterator;
  defineProp(this_0, 'size', mapSize, VOID, true);
  var mapView = this_0;
  return Object.assign(mapView, {get: mapGet, set: function (key, value) {
    mapPut(key, value);
    return this;
  }, 'delete': mapRemove, clear: mapClear, has: mapContains, keys: keysIterator, values: valuesIterator, entries: entriesIterator, forEach: function (cb, thisArg) {
    forEach(cb, mapView, thisArg);
  }});
}
function createJsReadonlyMapViewFrom$lambda($map) {
  return () => $map.g();
}
function createJsReadonlyMapViewFrom$lambda_0($map) {
  return (k) => $map.p(k);
}
function createJsReadonlyMapViewFrom$lambda_1($map) {
  return (k) => $map.n(k);
}
function UNSUPPORTED_OPERATION$ref() {
  return constructCallableReference(() => {
    UNSUPPORTED_OPERATION();
    return Unit_instance;
  }, 0, 0, 0, 'UNSUPPORTED_OPERATION');
}
function createJsReadonlyMapViewFrom$lambda_2($map) {
  return () => createJsIteratorFrom($map.q().d());
}
function createJsReadonlyMapViewFrom$lambda_3($map) {
  return () => createJsIteratorFrom($map.r().d());
}
function createJsReadonlyMapViewFrom$lambda$lambda(it) {
  // Inline function 'kotlin.arrayOf' call
  // Inline function 'kotlin.js.unsafeCast' call
  // Inline function 'kotlin.js.asDynamic' call
  return [it.k(), it.l()];
}
function createJsReadonlyMapViewFrom$lambda_4($map) {
  return () => {
    var tmp = $map.s().d();
    return createJsIteratorFrom(tmp, createJsReadonlyMapViewFrom$lambda$lambda);
  };
}
function createJsReadonlyMapViewFrom$lambda_5(callback, map, thisArg) {
  forEach(callback, map, thisArg);
  return Unit_instance;
}
function createMapFrom$lambda($$this$buildMapInternal) {
  return (value, key, _unused_var__etf5q3) => {
    $$this$buildMapInternal.f1(key, value);
    return Unit_instance;
  };
}
function createJsIteratorFrom$lambda(it) {
  return it;
}
function createJsIteratorFrom$lambda_0($iterator) {
  return () => $iterator.f();
}
function createJsIteratorFrom$lambda_1($iterator) {
  return () => $iterator.e();
}
function objectCreate(proto) {
  proto = proto === VOID ? null : proto;
  return Object.create(proto);
}
function defineProp(obj, name, getter, setter, enumerable) {
  return Object.defineProperty(obj, name, {configurable: true, get: getter, set: setter, enumerable: enumerable});
}
function getObjectHashCode(obj) {
  // Inline function 'kotlin.js.jsIn' call
  if (!('kotlinHashCodeValue$' in obj)) {
    var hash = calculateRandomHash();
    var descriptor = new Object();
    descriptor.value = hash;
    descriptor.enumerable = false;
    Object.defineProperty(obj, 'kotlinHashCodeValue$', descriptor);
  }
  // Inline function 'kotlin.js.unsafeCast' call
  return obj['kotlinHashCodeValue$'];
}
function calculateRandomHash() {
  // Inline function 'kotlin.js.jsBitwiseOr' call
  return Math.random() * 4.294967296E9 | 0;
}
function toString_1(o) {
  var tmp;
  if (o == null) {
    tmp = 'null';
  } else if (isArrayish(o)) {
    tmp = '[...]';
  } else if (!(typeof o.toString === 'function')) {
    tmp = anyToString(o);
  } else {
    // Inline function 'kotlin.js.unsafeCast' call
    tmp = o.toString();
  }
  return tmp;
}
function anyToString(o) {
  return Object.prototype.toString.call(o);
}
function equals(obj1, obj2) {
  if (obj1 == null) {
    return obj2 == null;
  }
  if (obj2 == null) {
    return false;
  }
  if (typeof obj1 === 'object' && typeof obj1.equals === 'function') {
    return obj1.equals(obj2);
  }
  if (obj1 !== obj1) {
    return obj2 !== obj2;
  }
  if (typeof obj1 === 'number' && typeof obj2 === 'number') {
    var tmp;
    if (obj1 === obj2) {
      var tmp_0;
      if (obj1 !== 0) {
        tmp_0 = true;
      } else {
        // Inline function 'kotlin.js.asDynamic' call
        var tmp_1 = 1 / obj1;
        // Inline function 'kotlin.js.asDynamic' call
        tmp_0 = tmp_1 === 1 / obj2;
      }
      tmp = tmp_0;
    } else {
      tmp = false;
    }
    return tmp;
  }
  if (isCallableReference(obj1) && isCallableReference(obj2)) {
    if (obj1 === obj2)
      return true;
    if (obj1.$id != obj2.$id)
      return false;
    if (obj1.$flags != obj2.$flags)
      return false;
    if (obj1.$arity != obj2.$arity)
      return false;
    if (obj1.$bound == null && obj2.$bound == null)
      return true;
    if (obj1.$bound === obj2.$bound)
      return true;
    if (!isJsArray(obj1.$bound) || !isJsArray(obj2.$bound))
      return false;
    // Inline function 'kotlin.js.unsafeCast' call
    var bound1 = obj1.$bound;
    // Inline function 'kotlin.js.unsafeCast' call
    var bound2 = obj2.$bound;
    return contentEqualsInternal(bound1, bound2);
  }
  return obj1 === obj2;
}
function hashCode(obj) {
  if (obj == null)
    return 0;
  var typeOf = typeof obj;
  var tmp;
  switch (typeOf) {
    case 'object':
      tmp = 'function' === typeof obj.hashCode ? obj.hashCode() : getObjectHashCode(obj);
      break;
    case 'function':
      tmp = isCallableReference(obj) ? getCallableReferenceHashCode(obj) : getObjectHashCode(obj);
      break;
    case 'number':
      tmp = getNumberHashCode(obj);
      break;
    case 'boolean':
      // Inline function 'kotlin.js.unsafeCast' call

      tmp = getBooleanHashCode(obj);
      break;
    case 'string':
      tmp = getStringHashCode(String(obj));
      break;
    case 'bigint':
      // Inline function 'kotlin.js.unsafeCast' call

      tmp = getBigIntHashCode(obj);
      break;
    case 'symbol':
      tmp = getSymbolHashCode(obj);
      break;
    default:
      tmp = function () {
        throw new Error('Unexpected typeof `' + typeOf + '`');
      }();
      break;
  }
  return tmp;
}
function getCallableReferenceHashCode(obj) {
  // Inline function 'kotlin.js.unsafeCast' call
  var hash = obj.$flags;
  hash = imul_0(31, hash) + hashCode(obj.$id) | 0;
  var tmp = imul_0(31, hash);
  var tmp0_elvis_lhs = obj.$arity;
  // Inline function 'kotlin.js.unsafeCast' call
  hash = tmp + (tmp0_elvis_lhs == null ? -1 : tmp0_elvis_lhs) | 0;
  var bound = obj.$bound;
  if (bound != null && isJsArray(bound)) {
    // Inline function 'kotlin.js.unsafeCast' call
    var boundArray = bound;
    hash = imul_0(31, hash) + contentHashCodeInternal(boundArray) | 0;
  }
  return hash;
}
function getBooleanHashCode(value) {
  return value ? 1231 : 1237;
}
function getStringHashCode(str) {
  var hash = 0;
  var length = str.length;
  var inductionVariable = 0;
  var last = length - 1 | 0;
  if (inductionVariable <= last)
    do {
      var i = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      // Inline function 'kotlin.js.asDynamic' call
      var code = str.charCodeAt(i);
      hash = imul_0(hash, 31) + code | 0;
    }
     while (!(i === last));
  return hash;
}
function getBigIntHashCode(value) {
  var shiftNumber = BigInt(32);
  var mask = BigInt(4.294967295E9);
  var bigNumber = abs(value);
  var hashCode = 0;
  var tmp;
  // Inline function 'kotlin.js.internal.isNegative' call
  if (value < 0) {
    tmp = -1;
  } else {
    tmp = 1;
  }
  var signum = tmp;
  $l$loop: while (true) {
    // Inline function 'kotlin.js.internal.isZero' call
    if (!!(bigNumber == 0)) {
      break $l$loop;
    }
    // Inline function 'kotlin.js.internal.and' call
    // Inline function 'kotlin.js.jsBitwiseAnd' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'kotlin.js.internal.toNumber' call
    var self_0 = bigNumber & mask;
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var chunk = Number(self_0);
    hashCode = imul_0(31, hashCode) + chunk | 0;
    // Inline function 'kotlin.js.internal.shr' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    bigNumber = bigNumber >> shiftNumber;
  }
  return imul_0(hashCode, signum);
}
function getSymbolHashCode(value) {
  var hashCodeMap = symbolIsSharable(value) ? getSymbolMap() : getSymbolWeakMap();
  var cachedHashCode = hashCodeMap.get(value);
  if (cachedHashCode !== VOID)
    return cachedHashCode;
  var hash = calculateRandomHash();
  hashCodeMap.set(value, hash);
  return hash;
}
function symbolIsSharable(symbol) {
  return Symbol.keyFor(symbol) != VOID;
}
function getSymbolMap() {
  if (symbolMap === VOID) {
    symbolMap = new Map();
  }
  return symbolMap;
}
function getSymbolWeakMap() {
  if (symbolWeakMap === VOID) {
    symbolWeakMap = new WeakMap();
  }
  return symbolWeakMap;
}
var symbolMap;
var symbolWeakMap;
function unboxIntrinsic(x) {
  // Inline function 'kotlin.error' call
  var message = 'Should be lowered';
  throw IllegalStateException.j1(toString_1(message));
}
function captureStack(instance, constructorFunction) {
  if (Error.captureStackTrace != null) {
    Error.captureStackTrace(instance, constructorFunction);
  } else {
    // Inline function 'kotlin.js.asDynamic' call
    instance.stack = (new Error()).stack;
  }
}
function protoOf(constructor) {
  return constructor.prototype;
}
function createThis(ctor, box) {
  var self_0 = Object.create(ctor.prototype);
  boxApply(self_0, box);
  return self_0;
}
function boxApply(self_0, box) {
  if (box !== VOID) {
    Object.assign(self_0, box);
  }
}
function createExternalThis(ctor, superExternalCtor, parameters, box) {
  var tmp;
  if (box === VOID) {
    tmp = ctor;
  } else {
    var newCtor = class extends ctor {}
    Object.assign(newCtor.prototype, box);
    newCtor.constructor = ctor;
    tmp = newCtor;
  }
  var selfCtor = tmp;
  return Reflect.construct(superExternalCtor, parameters, selfCtor);
}
function isUndefined(value) {
  return value === VOID;
}
function setPropertiesToThrowableInstance(this_, message, cause) {
  this_.name = Object.getPrototypeOf(this_).constructor.name;
  if (message == null) {
    var tmp;
    if (isUndefined(message)) {
      var tmp1_elvis_lhs = cause == null ? null : cause.toString();
      tmp = tmp1_elvis_lhs == null ? VOID : tmp1_elvis_lhs;
    } else {
      tmp = VOID;
    }
    this_.message = tmp;
  }
}
function THROW_NPE() {
  throw NullPointerException.n1();
}
function THROW_CCE() {
  throw ClassCastException.r1();
}
function ensureNotNull(v) {
  var tmp;
  if (v == null) {
    THROW_NPE();
  } else {
    tmp = v;
  }
  return tmp;
}
function jsGenerateInterfaceSymbol() {
  return generateInterfaceSymbolById();
}
var longArrayClass;
function truncating(_this__u8e3s4, bitSize) {
  _init_properties_longAsBigInt_kt__j3nkxv();
  var tmp = BigInt;
  // Inline function 'kotlin.js.unsafeCast' call
  // Inline function 'kotlin.js.asDynamic' call
  // Inline function 'kotlin.js.internal.toNumber' call
  var self_0 = tmp.asIntN(bitSize, _this__u8e3s4);
  // Inline function 'kotlin.js.unsafeCast' call
  return Number(self_0);
}
function convertToInt(_this__u8e3s4) {
  _init_properties_longAsBigInt_kt__j3nkxv();
  // Inline function 'kotlin.js.unsafeCast' call
  // Inline function 'kotlin.js.asDynamic' call
  return truncating(_this__u8e3s4, 32);
}
function isLongArray(a) {
  _init_properties_longAsBigInt_kt__j3nkxv();
  // Inline function 'kotlin.js.jsInstanceOf' call
  return a instanceof BigInt64Array;
}
function longArrayClass$lambda(it) {
  _init_properties_longAsBigInt_kt__j3nkxv();
  return !(it == null) ? isLongArray(it) : false;
}
var properties_initialized_longAsBigInt_kt_s7aby9;
function _init_properties_longAsBigInt_kt__j3nkxv() {
  if (!properties_initialized_longAsBigInt_kt_s7aby9) {
    properties_initialized_longAsBigInt_kt_s7aby9 = true;
    // Inline function 'kotlin.js.unsafeCast' call
    var tmp = BigInt64Array;
    longArrayClass = new PrimitiveKClassImpl(tmp, 'LongArray', longArrayClass$lambda);
  }
}
function createMetadata(kind, name, defaultConstructor, associatedObjectKey, associatedObjects, suspendArity) {
  var undef = VOID;
  return {kind: kind, simpleName: name, associatedObjectKey: associatedObjectKey, associatedObjects: associatedObjects, suspendArity: suspendArity, $kClass$: undef, defaultConstructor: defaultConstructor};
}
function initMetadataForClass(ctor, name, defaultConstructor, parent, interfaces, suspendArity, associatedObjectKey, associatedObjects) {
  var kind = 'class';
  initMetadataFor(kind, ctor, name, defaultConstructor, parent, interfaces, suspendArity, associatedObjectKey, associatedObjects);
}
function initMetadataFor(kind, ctor, name, defaultConstructor, parent, interfaces, suspendArity, associatedObjectKey, associatedObjects) {
  if (!(parent == null)) {
    ctor.prototype = Object.create(parent.prototype);
    ctor.prototype.constructor = ctor;
  }
  var metadata = createMetadata(kind, name, defaultConstructor, associatedObjectKey, associatedObjects, suspendArity);
  ctor.$metadata$ = metadata;
  var prototype = ctor.prototype;
  if (!(interfaces == null)) {
    var inductionVariable = 0;
    var last = interfaces.length;
    while (inductionVariable < last) {
      var i = interfaces[inductionVariable];
      inductionVariable = inductionVariable + 1 | 0;
      Object.assign(prototype, i.prototype);
      prototype[i.Symbol] = true;
    }
  }
  if (kind === 'interface') {
    ctor.Symbol = Symbol();
  }
}
function generateInterfaceSymbolById() {
  return '#__interface_' + generateInterfaceId();
}
function generateInterfaceId() {
  if (globalInterfaceId === VOID) {
    globalInterfaceId = 0;
  }
  // Inline function 'kotlin.js.unsafeCast' call
  globalInterfaceId = globalInterfaceId + 1 | 0;
  return globalInterfaceId;
}
var globalInterfaceId;
function initMetadataForObject(ctor, name, defaultConstructor, parent, interfaces, suspendArity, associatedObjectKey, associatedObjects) {
  var kind = 'object';
  initMetadataFor(kind, ctor, name, defaultConstructor, parent, interfaces, suspendArity, associatedObjectKey, associatedObjects);
}
function initMetadataForInterface(ctor, name, defaultConstructor, parent, interfaces, suspendArity, associatedObjectKey, associatedObjects) {
  var kind = 'interface';
  initMetadataFor(kind, ctor, name, defaultConstructor, parent, interfaces, suspendArity, associatedObjectKey, associatedObjects);
}
function initMetadataForLambda(ctor, parent, interfaces, suspendArity) {
  initMetadataForClass(ctor, 'Lambda', VOID, parent, interfaces, suspendArity, VOID, VOID);
}
function initMetadataForCoroutine(ctor, parent, interfaces, suspendArity) {
  initMetadataForClass(ctor, 'Coroutine', VOID, parent, interfaces, suspendArity, VOID, VOID);
}
function initMetadataForFunctionReference(ctor, parent, interfaces, suspendArity) {
  initMetadataForClass(ctor, 'FunctionReference', VOID, parent, interfaces, suspendArity, VOID, VOID);
}
function initMetadataForCompanion(ctor, parent, interfaces, suspendArity) {
  initMetadataForObject(ctor, 'Companion', VOID, parent, interfaces, suspendArity, VOID, VOID);
}
function numberToInt(a) {
  var tmp;
  if (!(a == null) ? typeof a === 'bigint' : false) {
    tmp = convertToInt(a);
  } else {
    tmp = doubleToInt(a);
  }
  return tmp;
}
function doubleToInt(a) {
  var tmp;
  if (a > 2147483647) {
    tmp = 2147483647;
  } else if (a < -2147483648) {
    tmp = -2147483648;
  } else {
    // Inline function 'kotlin.js.jsBitwiseOr' call
    tmp = a | 0;
  }
  return tmp;
}
var propertyRefClassMetadataCache;
function metadataObject() {
  _init_properties_reflectRuntime_kt__5r4uu3();
  return createMetadata('class', VOID, VOID, VOID, VOID, VOID);
}
function constructCallableReference(callable, arity, flags, signatureId, name, bounds) {
  _init_properties_reflectRuntime_kt__5r4uu3();
  callable.callableName = name;
  callable.$flags = flags;
  callable.$arity = arity;
  callable.$id = signatureId;
  callable.$bound = bounds;
  return callable;
}
var properties_initialized_reflectRuntime_kt_inkhwd;
function _init_properties_reflectRuntime_kt__5r4uu3() {
  if (!properties_initialized_reflectRuntime_kt_inkhwd) {
    properties_initialized_reflectRuntime_kt_inkhwd = true;
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp = [metadataObject(), metadataObject()];
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    var tmp_0 = [metadataObject(), metadataObject()];
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    // Inline function 'kotlin.arrayOf' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    propertyRefClassMetadataCache = [tmp, tmp_0, [metadataObject(), metadataObject()]];
  }
}
function isArrayish(o) {
  return isJsArray(o) || isView(o);
}
function isJsArray(obj) {
  // Inline function 'kotlin.js.unsafeCast' call
  return Array.isArray(obj);
}
function isCallableReference(value) {
  return typeof value === 'function' && value.$flags != null && value.$arity != null;
}
function isInterface(obj, iface) {
  return obj[iface.Symbol] === true;
}
function isCharSequence(value) {
  return typeof value === 'string' || isInterface(value, CharSequence);
}
function get_VOID() {
  _init_properties_void_kt__3zg9as();
  return VOID;
}
var VOID;
var properties_initialized_void_kt_e4ret2;
function _init_properties_void_kt__3zg9as() {
  if (!properties_initialized_void_kt_e4ret2) {
    properties_initialized_void_kt_e4ret2 = true;
    VOID = void 0;
  }
}
function copyOf(_this__u8e3s4, newSize) {
  // Inline function 'kotlin.require' call
  if (!(newSize >= 0)) {
    var message = 'Invalid new array size: ' + newSize + '.';
    throw IllegalArgumentException.v1(toString_1(message));
  }
  var size = _this__u8e3s4.length;
  var tmp;
  if (newSize < 16 || size < 16) {
    tmp = fillFrom(_this__u8e3s4, new Int32Array(newSize));
  } else if (newSize > size) {
    // Inline function 'kotlin.also' call
    var this_0 = new Int32Array(newSize);
    // Inline function 'kotlin.js.asDynamic' call
    this_0.set(_this__u8e3s4);
    tmp = this_0;
  } else {
    // Inline function 'kotlin.js.asDynamic' call
    tmp = _this__u8e3s4.slice(0, newSize);
  }
  return tmp;
}
function copyOf_0(_this__u8e3s4, newSize) {
  // Inline function 'kotlin.require' call
  if (!(newSize >= 0)) {
    var message = 'Invalid new array size: ' + newSize + '.';
    throw IllegalArgumentException.v1(toString_1(message));
  }
  return arrayCopyResize(_this__u8e3s4, newSize, null);
}
function takeHighestOneBit(_this__u8e3s4) {
  var tmp;
  if (_this__u8e3s4 === 0) {
    tmp = 0;
  } else {
    // Inline function 'kotlin.countLeadingZeroBits' call
    tmp = 1 << (31 - clz32(_this__u8e3s4) | 0);
  }
  return tmp;
}
var Unit_instance;
function Unit_getInstance() {
  return Unit_instance;
}
function collectionToArray(collection) {
  return collectionToArrayCommonImpl(collection);
}
function arrayOfUninitializedElements(capacity) {
  // Inline function 'kotlin.require' call
  if (!(capacity >= 0)) {
    var message = 'capacity must be non-negative.';
    throw IllegalArgumentException.v1(toString_1(message));
  }
  // Inline function 'kotlin.arrayOfNulls' call
  // Inline function 'kotlin.js.unsafeCast' call
  // Inline function 'kotlin.js.asDynamic' call
  return Array(capacity);
}
function resetRange(_this__u8e3s4, fromIndex, toIndex) {
  // Inline function 'kotlin.js.nativeFill' call
  // Inline function 'kotlin.js.asDynamic' call
  _this__u8e3s4.fill(null, fromIndex, toIndex);
}
function copyOfUninitializedElements(_this__u8e3s4, newSize) {
  // Inline function 'kotlin.js.unsafeCast' call
  // Inline function 'kotlin.js.asDynamic' call
  return copyOf_0(_this__u8e3s4, newSize);
}
function init_kotlin_collections_HashMap(_this__u8e3s4) {
  _this__u8e3s4.o2_1 = null;
}
function computeHashSize($this, capacity) {
  return takeHighestOneBit(imul_0(coerceAtLeast(capacity, 1), 3));
}
function computeShift($this, hashSize) {
  // Inline function 'kotlin.countLeadingZeroBits' call
  return clz32(hashSize) + 1 | 0;
}
function checkForComodification($this) {
  if (!($this.w3_1.y2_1 === $this.y3_1))
    throw ConcurrentModificationException.v3('The backing map has been modified after this entry was obtained.');
}
function _get_capacity__a9k9f3($this) {
  return $this.r2_1.length;
}
function _get_hashSize__tftcho($this) {
  return $this.u2_1.length;
}
function registerModification($this) {
  $this.y2_1 = $this.y2_1 + 1 | 0;
}
function ensureExtraCapacity($this, n) {
  if (shouldCompact($this, n)) {
    compact($this, true);
  } else {
    ensureCapacity($this, $this.w2_1 + n | 0);
  }
}
function shouldCompact($this, extraCapacity) {
  var spareCapacity = _get_capacity__a9k9f3($this) - $this.w2_1 | 0;
  var gaps = $this.w2_1 - $this.g() | 0;
  return spareCapacity < extraCapacity && (gaps + spareCapacity | 0) >= extraCapacity && gaps >= (_get_capacity__a9k9f3($this) / 4 | 0);
}
function ensureCapacity($this, minCapacity) {
  if (minCapacity < 0)
    throw RuntimeException.b4('too many elements');
  if (minCapacity > _get_capacity__a9k9f3($this)) {
    var newSize = Companion_instance_1.d4(_get_capacity__a9k9f3($this), minCapacity);
    $this.r2_1 = copyOfUninitializedElements($this.r2_1, newSize);
    var tmp = $this;
    var tmp0_safe_receiver = $this.s2_1;
    tmp.s2_1 = tmp0_safe_receiver == null ? null : copyOfUninitializedElements(tmp0_safe_receiver, newSize);
    $this.t2_1 = copyOf($this.t2_1, newSize);
    var newHashSize = computeHashSize(Companion_instance_0, newSize);
    if (newHashSize > _get_hashSize__tftcho($this)) {
      rehash($this, newHashSize);
    }
  }
}
function allocateValuesArray($this) {
  var curValuesArray = $this.s2_1;
  if (!(curValuesArray == null))
    return curValuesArray;
  var newValuesArray = arrayOfUninitializedElements(_get_capacity__a9k9f3($this));
  $this.s2_1 = newValuesArray;
  return newValuesArray;
}
function hash($this, key) {
  return key == null ? 0 : imul_0(hashCode(key), -1640531527) >>> $this.x2_1 | 0;
}
function compact($this, updateHashArray) {
  var i = 0;
  var j = 0;
  var valuesArray = $this.s2_1;
  while (i < $this.w2_1) {
    var hash = $this.t2_1[i];
    if (hash >= 0) {
      $this.r2_1[j] = $this.r2_1[i];
      if (!(valuesArray == null)) {
        valuesArray[j] = valuesArray[i];
      }
      if (updateHashArray) {
        $this.t2_1[j] = hash;
        $this.u2_1[hash] = j + 1 | 0;
      }
      j = j + 1 | 0;
    }
    i = i + 1 | 0;
  }
  resetRange($this.r2_1, j, $this.w2_1);
  if (valuesArray == null)
    null;
  else {
    resetRange(valuesArray, j, $this.w2_1);
  }
  $this.w2_1 = j;
}
function rehash($this, newHashSize) {
  registerModification($this);
  if ($this.w2_1 > $this.z2_1) {
    compact($this, false);
  }
  $this.u2_1 = new Int32Array(newHashSize);
  $this.x2_1 = computeShift(Companion_instance_0, newHashSize);
  var i = 0;
  while (i < $this.w2_1) {
    var _unary__edvuaz = i;
    i = _unary__edvuaz + 1 | 0;
    if (!putRehash($this, _unary__edvuaz)) {
      throw IllegalStateException.j1('This cannot happen with fixed magic multiplier and grow-only hash array. Have object hashCodes changed?');
    }
  }
}
function putRehash($this, i) {
  var hash_0 = hash($this, $this.r2_1[i]);
  var probesLeft = $this.v2_1;
  while (true) {
    var index = $this.u2_1[hash_0];
    if (index === 0) {
      $this.u2_1[hash_0] = i + 1 | 0;
      $this.t2_1[i] = hash_0;
      return true;
    }
    probesLeft = probesLeft - 1 | 0;
    if (probesLeft < 0)
      return false;
    var _unary__edvuaz = hash_0;
    hash_0 = _unary__edvuaz - 1 | 0;
    if (_unary__edvuaz === 0)
      hash_0 = _get_hashSize__tftcho($this) - 1 | 0;
  }
}
function findKey($this, key) {
  var hash_0 = hash($this, key);
  var probesLeft = $this.v2_1;
  while (true) {
    var index = $this.u2_1[hash_0];
    if (index === 0)
      return -1;
    if (equals($this.r2_1[index - 1 | 0], key))
      return index - 1 | 0;
    probesLeft = probesLeft - 1 | 0;
    if (probesLeft < 0)
      return -1;
    var _unary__edvuaz = hash_0;
    hash_0 = _unary__edvuaz - 1 | 0;
    if (_unary__edvuaz === 0)
      hash_0 = _get_hashSize__tftcho($this) - 1 | 0;
  }
}
function findValue($this, value) {
  var i = $this.w2_1;
  $l$loop: while (true) {
    i = i - 1 | 0;
    if (!(i >= 0)) {
      break $l$loop;
    }
    if ($this.t2_1[i] >= 0 && equals(ensureNotNull($this.s2_1)[i], value))
      return i;
  }
  return -1;
}
function addKey($this, key) {
  $this.e4();
  retry: while (true) {
    var hash_0 = hash($this, key);
    var tentativeMaxProbeDistance = coerceAtMost(imul_0($this.v2_1, 2), _get_hashSize__tftcho($this) / 2 | 0);
    var probeDistance = 0;
    while (true) {
      var index = $this.u2_1[hash_0];
      if (index === 0) {
        if ($this.w2_1 >= _get_capacity__a9k9f3($this)) {
          ensureExtraCapacity($this, 1);
          continue retry;
        }
        var _unary__edvuaz = $this.w2_1;
        $this.w2_1 = _unary__edvuaz + 1 | 0;
        var putIndex = _unary__edvuaz;
        $this.r2_1[putIndex] = key;
        $this.t2_1[putIndex] = hash_0;
        $this.u2_1[hash_0] = putIndex + 1 | 0;
        $this.z2_1 = $this.z2_1 + 1 | 0;
        registerModification($this);
        if (probeDistance > $this.v2_1)
          $this.v2_1 = probeDistance;
        return putIndex;
      }
      if (equals($this.r2_1[index - 1 | 0], key)) {
        return -index | 0;
      }
      probeDistance = probeDistance + 1 | 0;
      if (probeDistance > tentativeMaxProbeDistance) {
        rehash($this, imul_0(_get_hashSize__tftcho($this), 2));
        continue retry;
      }
      var _unary__edvuaz_0 = hash_0;
      hash_0 = _unary__edvuaz_0 - 1 | 0;
      if (_unary__edvuaz_0 === 0)
        hash_0 = _get_hashSize__tftcho($this) - 1 | 0;
    }
  }
}
function contentEquals($this, other) {
  return $this.z2_1 === other.g() && $this.n3(other.s());
}
var Companion_instance_0;
function Companion_getInstance_0() {
  return Companion_instance_0;
}
var EmptyHolder_instance;
function EmptyHolder_getInstance() {
  if (EmptyHolder_instance === VOID)
    new EmptyHolder();
  return EmptyHolder_instance;
}
function init_kotlin_collections_LinkedHashMap(_this__u8e3s4) {
}
function init_kotlin_UnsupportedOperationException(_this__u8e3s4) {
  captureStack(_this__u8e3s4, _this__u8e3s4.d1_1);
}
function init_kotlin_IllegalArgumentException(_this__u8e3s4) {
  captureStack(_this__u8e3s4, _this__u8e3s4.u1_1);
}
function init_kotlin_RuntimeException(_this__u8e3s4) {
  captureStack(_this__u8e3s4, _this__u8e3s4.a4_1);
}
function init_kotlin_Exception(_this__u8e3s4) {
  captureStack(_this__u8e3s4, _this__u8e3s4.w5_1);
}
function init_kotlin_NoSuchElementException(_this__u8e3s4) {
  captureStack(_this__u8e3s4, _this__u8e3s4.w4_1);
}
function init_kotlin_IllegalStateException(_this__u8e3s4) {
  captureStack(_this__u8e3s4, _this__u8e3s4.i1_1);
}
function init_kotlin_ConcurrentModificationException(_this__u8e3s4) {
  captureStack(_this__u8e3s4, _this__u8e3s4.u3_1);
}
function init_kotlin_NullPointerException(_this__u8e3s4) {
  captureStack(_this__u8e3s4, _this__u8e3s4.m1_1);
}
function init_kotlin_ClassCastException(_this__u8e3s4) {
  captureStack(_this__u8e3s4, _this__u8e3s4.q1_1);
}
function fillFrom(src, dst) {
  var srcLen = src.length;
  var dstLen = dst.length;
  var index = 0;
  // Inline function 'kotlin.js.unsafeCast' call
  var arr = dst;
  while (index < srcLen && index < dstLen) {
    var tmp = index;
    var _unary__edvuaz = index;
    index = _unary__edvuaz + 1 | 0;
    arr[tmp] = src[_unary__edvuaz];
  }
  return dst;
}
function arrayCopyResize(source, newSize, defaultValue) {
  // Inline function 'kotlin.js.unsafeCast' call
  var result = source.slice(0, newSize);
  // Inline function 'kotlin.copyArrayType' call
  if (source.$type$ !== undefined) {
    result.$type$ = source.$type$;
  }
  var index = source.length;
  if (newSize > index) {
    // Inline function 'kotlin.js.asDynamic' call
    result.length = newSize;
    while (index < newSize) {
      var _unary__edvuaz = index;
      index = _unary__edvuaz + 1 | 0;
      result[_unary__edvuaz] = defaultValue;
    }
  }
  return result;
}
function AbstractCollection$toString$lambda(this$0) {
  return (it) => it === this$0 ? '(this Collection)' : toString_0(it);
}
var Companion_instance_1;
function Companion_getInstance_1() {
  return Companion_instance_1;
}
function toString_2($this, entry) {
  return toString_3($this, entry.k()) + '=' + toString_3($this, entry.l());
}
function toString_3($this, o) {
  return o === $this ? '(this Map)' : toString_0(o);
}
function implFindEntry($this, key) {
  var tmp0 = $this.s();
  var tmp$ret$0;
  $l$block: {
    // Inline function 'kotlin.collections.firstOrNull' call
    var _iterator__ex2g4s = tmp0.d();
    while (_iterator__ex2g4s.e()) {
      var element = _iterator__ex2g4s.f();
      if (equals(element.k(), key)) {
        tmp$ret$0 = element;
        break $l$block;
      }
    }
    tmp$ret$0 = null;
  }
  return tmp$ret$0;
}
var Companion_instance_2;
function Companion_getInstance_2() {
  return Companion_instance_2;
}
function AbstractMap$toString$lambda(this$0) {
  return (it) => toString_2(this$0, it);
}
var Companion_instance_3;
function Companion_getInstance_3() {
  return Companion_instance_3;
}
function collectionToArrayCommonImpl(collection) {
  if (collection.h()) {
    // Inline function 'kotlin.emptyArray' call
    return [];
  }
  // Inline function 'kotlin.arrayOfNulls' call
  var size = collection.g();
  var destination = Array(size);
  var iterator = collection.d();
  var index = 0;
  while (iterator.e()) {
    var _unary__edvuaz = index;
    index = _unary__edvuaz + 1 | 0;
    destination[_unary__edvuaz] = iterator.f();
  }
  return destination;
}
function appendElement(_this__u8e3s4, element, transform) {
  if (!(transform == null))
    _this__u8e3s4.c(transform(element));
  else {
    if (element == null ? true : isCharSequence(element))
      _this__u8e3s4.c(element);
    else {
      if (element instanceof Char)
        _this__u8e3s4.k5(element.k6_1);
      else {
        _this__u8e3s4.c(toString_1(element));
      }
    }
  }
}
//region block: post-declaration
initMetadataForInterface(CharSequence, 'CharSequence');
initMetadataForClass(Char, 'Char');
initMetadataForInterface(Collection, 'Collection');
initMetadataForInterface(KtSet, 'Set', VOID, VOID, [Collection]);
initMetadataForInterface(Entry, 'Entry');
initMetadataForCompanion(Companion);
initMetadataForInterface(KtMap, 'Map');
initMetadataForClass(JsMapView, 'JsMapView', JsMapView);
initMetadataForObject(Unit, 'Unit');
initMetadataForClass(AbstractCollection, 'AbstractCollection', VOID, VOID, [Collection]);
initMetadataForClass(AbstractMutableCollection, 'AbstractMutableCollection', VOID, VOID, [Collection]);
protoOf(AbstractMap).asJsReadonlyMapView = asJsReadonlyMapView;
initMetadataForClass(AbstractMap, 'AbstractMap', VOID, VOID, [KtMap]);
initMetadataForClass(AbstractMutableMap, 'AbstractMutableMap', VOID, VOID, [KtMap]);
initMetadataForClass(AbstractMutableSet, 'AbstractMutableSet', VOID, VOID, [Collection, KtSet]);
initMetadataForClass(HashMap, 'HashMap', HashMap.q2, VOID, [KtMap]);
initMetadataForClass(HashMapKeys, 'HashMapKeys', VOID, VOID, [Collection, KtSet]);
initMetadataForClass(HashMapValues, 'HashMapValues', VOID, VOID, [Collection]);
initMetadataForClass(HashMapEntrySetBase, 'HashMapEntrySetBase', VOID, VOID, [Collection, KtSet]);
initMetadataForClass(HashMapEntrySet, 'HashMapEntrySet');
initMetadataForClass(HashMapKeysDefault$iterator$1);
initMetadataForClass(HashMapKeysDefault, 'HashMapKeysDefault');
initMetadataForClass(HashMapValuesDefault$iterator$1);
initMetadataForClass(HashMapValuesDefault, 'HashMapValuesDefault');
initMetadataForCompanion(Companion_0);
initMetadataForClass(Itr, 'Itr');
initMetadataForClass(KeysItr, 'KeysItr');
initMetadataForClass(ValuesItr, 'ValuesItr');
initMetadataForClass(EntriesItr, 'EntriesItr');
initMetadataForClass(EntryRef, 'EntryRef', VOID, VOID, [Entry]);
initMetadataForInterface(InternalMap, 'InternalMap');
protoOf(InternalHashMap).n3 = containsAllEntries;
initMetadataForClass(InternalHashMap, 'InternalHashMap', InternalHashMap.b3, VOID, [InternalMap]);
initMetadataForObject(EmptyHolder, 'EmptyHolder');
initMetadataForClass(LinkedHashMap, 'LinkedHashMap', LinkedHashMap.z, VOID, [KtMap]);
initMetadataForClass(Exception, 'Exception', Exception.x5);
initMetadataForClass(RuntimeException, 'RuntimeException', RuntimeException.s5);
initMetadataForClass(UnsupportedOperationException, 'UnsupportedOperationException', UnsupportedOperationException.e1);
initMetadataForClass(IllegalArgumentException, 'IllegalArgumentException', IllegalArgumentException.v5);
initMetadataForClass(NoSuchElementException, 'NoSuchElementException', NoSuchElementException.x4);
initMetadataForClass(IllegalStateException, 'IllegalStateException', IllegalStateException.z5);
initMetadataForClass(ConcurrentModificationException, 'ConcurrentModificationException', ConcurrentModificationException.p4);
initMetadataForClass(NullPointerException, 'NullPointerException', NullPointerException.n1);
initMetadataForClass(ClassCastException, 'ClassCastException', ClassCastException.r1);
initMetadataForClass(KClassImpl, 'KClassImpl');
initMetadataForClass(PrimitiveKClassImpl, 'PrimitiveKClassImpl');
initMetadataForObject(NothingKClassImpl, 'NothingKClassImpl');
initMetadataForClass(StringBuilder, 'StringBuilder', StringBuilder.b, VOID, [CharSequence]);
initMetadataForCompanion(Companion_1);
initMetadataForClass(AbstractMap$keys$1$iterator$1);
initMetadataForClass(AbstractMap$values$1$iterator$1);
initMetadataForCompanion(Companion_2);
initMetadataForClass(AbstractSet, 'AbstractSet', VOID, VOID, [KtSet]);
initMetadataForClass(AbstractMap$keys$1);
initMetadataForClass(AbstractMap$values$1);
initMetadataForCompanion(Companion_3);
//endregion
//region block: init
Companion_instance = new Companion();
Unit_instance = new Unit();
Companion_instance_0 = new Companion_0();
Companion_instance_1 = new Companion_1();
Companion_instance_2 = new Companion_2();
Companion_instance_3 = new Companion_3();
//endregion
//region block: exports
var KtMap_0 = {};
KtMap_0.fromJsMap = fromJsMap;
export {
  KtMap_0 as KtMap,
};
export {
  VOID as VOID3gxj6tk5isa35,
  initMetadataForClass as initMetadataForClassbxx6q50dy2s7,
};
//endregion

//# sourceMappingURL=kotlin-kotlin-stdlib.mjs.map
