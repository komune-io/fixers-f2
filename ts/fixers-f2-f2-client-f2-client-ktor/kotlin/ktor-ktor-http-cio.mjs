import {
  VOID3gxj6tk5isa35 as VOID,
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
  Unit_instance3kz1zqli6gr1r as Unit_instance,
  IllegalArgumentException2asla15b5jaob as IllegalArgumentException,
  toString1pkumu07cwy4m as toString,
  abs1kdzbjes1idip as abs,
  StringBuildermazzzhj6kkai as StringBuilder,
  orPromise1iucc2to2udt2 as orPromise,
  constructCallableReference23y65rf941mch as constructCallableReference,
  ArrayList3it5z8td81qkl as ArrayList,
  sequence2vgswtrxvqoa7 as sequence,
  fill2542d4m9l93pn as fill,
  protoOf180f3jzyo7rfj as protoOf,
  THROW_CCE2g6jy02ryeudk as THROW_CCE,
  isIntArrayeijsubfngq38 as isIntArray,
  IllegalStateExceptionkoljg5n0nrlr as IllegalStateException,
  captureStack1fzi4aczwc4hg as captureStack,
  _Char___init__impl__6a9atxrzocepzq3zgi as _Char___init__impl__6a9atx,
  endsWith1a79dp5rc3sfv as endsWith,
  charSequenceLength3278n89t01tmv as charSequenceLength,
  charSequenceGet1vxk1y5n17t1z as charSequenceGet,
  Char19o2r8palgjof as Char,
  Char__compareTo_impl_ypi4mb3bl0kdwnivnp6 as Char__compareTo_impl_ypi4mb,
  contains2el4s70rdq4ld as contains,
  charSequenceSubSequence1iwpdba8s3jc7 as charSequenceSubSequence,
  Char__toInt_impl_vasixd28v1p2u6jau5g as Char__toInt_impl_vasixd,
  setOf45ia9pnfhe90 as setOf,
  listOf1jh22dvmctj1r as listOf,
  toString1h6jjoch8cjt8 as toString_0,
  toByte4i43936u611k as toByte,
  copyOfRange3alro60z4hhf8 as copyOfRange,
  startsWith641pyr7vf687 as startsWith,
  add2suhfggl4zvkk as add,
  fromInt2ii0rejb1w62w as fromInt,
  CancellationException3b36o9qz53rgr as CancellationException,
  subtract2orl8z9upxd9l as subtract,
  convertToInty04h231mmjoh as convertToInt,
  LinkedHashMap1zhqxkxv3xnkl as LinkedHashMap,
  compareTo3ankvs086tmwq as compareTo,
  NoSuchElementException679xzhnp5bpj as NoSuchElementException,
  Collection1k04j3hzsbod0 as Collection,
  isInterface3d6p8outrmvmk as isInterface,
  emptyList1g2z5xcrvp2zy as emptyList,
  initMetadataForCompanion1wyw17z38v6ac as initMetadataForCompanion,
  ensureNotNull1e947j3ixpazm as ensureNotNull,
  isCharSequence1ju9jr1w86plq as isCharSequence,
  getStringHashCode26igk1bx568vk as getStringHashCode,
  CharSequence1ceii1xorfwh7 as CharSequence,
  IndexOutOfBoundsException1qfr429iumro0 as IndexOutOfBoundsException,
  charArray2ujmm1qusno00 as charArray,
  shiftLeft3tsh2sstjchzn as shiftLeft,
  NumberFormatException3bgsm2s9o4t55 as NumberFormatException,
  toString1n9qg6fb0b5i8 as toString_1,
  charCodeAt1yspne1d8erbm as charCodeAt,
  numberRangeToNumber25vse2rgp6rs8 as numberRangeToNumber,
  collectionSizeOrDefault36dulx8yinfqm as collectionSizeOrDefault,
  numberToLong2pakxeg38estk as numberToLong,
  toLongArray23ixicpzp5r3w as toLongArray,
  Char__plus_impl_qi7pgj12gj4qbkhmj1y as Char__plus_impl_qi7pgj,
  Char__minus_impl_a2frrh4e9xgjqbj3za as Char__minus_impl_a2frrh,
  toByteArray3caw0hip00os as toByteArray,
  isWhitespace25occ8z1ed1s9 as isWhitespace,
} from './kotlin-kotlin-stdlib.mjs';
import {
  MultiPartData57syw40llxls as MultiPartData,
  MultiPart_getInstance5h1nld8q49pe as MultiPart_getInstance,
  Companion_getInstance29j9s2wnmk34z as Companion_getInstance,
} from './ktor-ktor-http.mjs';
import {
  DefaultPool2gb1fm4epwgu9 as DefaultPool,
  readLineStrictTo1b1nwo5trwh7s as readLineStrictTo,
  Companion_getInstance325d2evlpmaqk as Companion_getInstance_0,
  LineEndingMode__plus_impl_ttpz2j3huakwvr4g74l as LineEndingMode__plus_impl_ttpz2j,
  readUntil2ls7o0c1kzd4x as readUntil,
  copyTo2t9ovypu2ttur as copyTo,
  skipIfFoundarb6fjv6fvg7 as skipIfFound,
  counted3iniv3aql3f9n as counted,
  writer1eia5its2a1fh as writer,
  readBuffery45c0omfxhxc as readBuffer,
  get_remaining1lapv95kcmm0y as get_remaining,
  ByteChannel3cxdguezl3lu7 as ByteChannel,
  close3semq7pafb42g as close,
  readPacket290wjvm56mmfy as readPacket,
  toByteArray1i3ns5jnoqlv6 as toByteArray_0,
  NoPoolImplgos9n8jphzjp as NoPoolImpl,
} from './ktor-ktor-io.mjs';
import {
  ByteString10sanmoo66key as ByteString,
  ByteString3c9fk8lsh3lvs as ByteString_0,
} from './kotlinx-io-kotlinx-io-bytestring.mjs';
import {
  IOException1wyutdmfe71nu as IOException,
  EOFExceptionh831u25jcq9n as EOFException,
} from './kotlinx-io-kotlinx-io-core.mjs';
import {
  produce1iljho2c8bp6o as produce,
  CompletableDeferred2lnqvsbvx74d3 as CompletableDeferred,
} from './kotlinx-coroutines-core.mjs';
//region block: imports
var imul = Math.imul;
//endregion
//region block: pre-declaration
class CIOMultipartDataBase {
  constructor(coroutineContext, channel, contentType, contentLength, formFieldLimit) {
    formFieldLimit = formFieldLimit === VOID ? 65536n : formFieldLimit;
    this.p2w_1 = coroutineContext;
    this.q2w_1 = null;
    this.r2w_1 = parseMultipart(this, channel, contentType, contentLength, formFieldLimit);
  }
  st() {
    return this.p2w_1;
  }
}
class HttpHeadersMap {
  constructor(builder) {
    this.s2w_1 = builder;
    this.t2w_1 = 0;
    this.u2w_1 = 0;
    this.v2w_1 = get_HeadersDataPool().e29();
  }
  o2b(name) {
    if (this.t2w_1 === 0)
      return null;
    // Inline function 'kotlin.math.absoluteValue' call
    var this_0 = hashCodeLowerCase(name);
    var hash = abs(this_0);
    var headerIndex = hash % this.u2w_1 | 0;
    while (!(this.v2w_1.a2x(imul(headerIndex, 6) + 0 | 0) === -1)) {
      if (headerHasName(this, name, imul(headerIndex, 6))) {
        return this.c2x(imul(headerIndex, 6));
      }
      headerIndex = (headerIndex + 1 | 0) % this.u2w_1 | 0;
    }
    return null;
  }
  d2x() {
    return this.v2w_1.z2w();
  }
  b2x(nameStartIndex, nameEndIndex, valueStartIndex, valueEndIndex) {
    if (thresholdReached(this)) {
      resize(this);
    }
    // Inline function 'kotlin.math.absoluteValue' call
    var this_0 = hashCodeLowerCase(this.s2w_1, nameStartIndex, nameEndIndex);
    var hash = abs(this_0);
    var name = this.s2w_1.c(nameStartIndex, nameEndIndex);
    var headerIndex = hash % this.u2w_1 | 0;
    var sameNameHeaderIndex = -1;
    while (!(this.v2w_1.a2x(imul(headerIndex, 6) + 0 | 0) === -1)) {
      if (headerHasName(this, name, imul(headerIndex, 6))) {
        sameNameHeaderIndex = headerIndex;
      }
      headerIndex = (headerIndex + 1 | 0) % this.u2w_1 | 0;
    }
    var headerOffset = imul(headerIndex, 6);
    this.v2w_1.l2x(headerOffset + 0 | 0, hash);
    this.v2w_1.l2x(headerOffset + 1 | 0, nameStartIndex);
    this.v2w_1.l2x(headerOffset + 2 | 0, nameEndIndex);
    this.v2w_1.l2x(headerOffset + 3 | 0, valueStartIndex);
    this.v2w_1.l2x(headerOffset + 4 | 0, valueEndIndex);
    this.v2w_1.l2x(headerOffset + 5 | 0, -1);
    if (!(sameNameHeaderIndex === -1)) {
      this.v2w_1.l2x(imul(sameNameHeaderIndex, 6) + 5 | 0, headerIndex);
    }
    this.t2w_1 = this.t2w_1 + 1 | 0;
  }
  m2x(headerOffset) {
    var nameStartIndex = this.v2w_1.a2x(headerOffset + 1 | 0);
    var nameEndIndex = this.v2w_1.a2x(headerOffset + 2 | 0);
    return this.s2w_1.c(nameStartIndex, nameEndIndex);
  }
  c2x(headerOffset) {
    var valueStartIndex = this.v2w_1.a2x(headerOffset + 3 | 0);
    var valueEndIndex = this.v2w_1.a2x(headerOffset + 4 | 0);
    return this.s2w_1.c(valueStartIndex, valueEndIndex);
  }
  n2x() {
    this.t2w_1 = 0;
    this.u2w_1 = 0;
    get_HeadersDataPool().f29(this.v2w_1);
    this.v2w_1 = get_HeadersDataPool().e29();
  }
  toString() {
    // Inline function 'kotlin.text.buildString' call
    // Inline function 'kotlin.apply' call
    var this_0 = StringBuilder.h1();
    dumpTo(this, '', this_0);
    return this_0.toString();
  }
}
class HeadersData {
  constructor() {
    var tmp = this;
    // Inline function 'kotlin.collections.mutableListOf' call
    tmp.w2w_1 = ArrayList.p2();
  }
  x2w() {
    return this.w2w_1.j2();
  }
  y2w(subArraysCount) {
    // Inline function 'kotlin.repeat' call
    var inductionVariable = 0;
    if (inductionVariable < subArraysCount)
      do {
        var index = inductionVariable;
        inductionVariable = inductionVariable + 1 | 0;
        this.w2w_1.a2(get_IntArrayPool().e29());
      }
       while (inductionVariable < subArraysCount);
  }
  a2x(index) {
    return this.w2w_1.l2(index / 768 | 0)[index % 768 | 0];
  }
  l2x(index, value) {
    this.w2w_1.l2(index / 768 | 0)[index % 768 | 0] = value;
  }
  z2w() {
    return sequence(HeadersData$headersStarts$slambda(this));
  }
  n2x() {
    var _iterator__ex2g4s = this.w2w_1.l1();
    while (_iterator__ex2g4s.m1()) {
      var array = _iterator__ex2g4s.n1();
      get_IntArrayPool().f29(array);
    }
    this.w2w_1.e4();
  }
}
class IntArrayPool$1 extends DefaultPool {
  constructor() {
    super(1000);
  }
  x28() {
    var tmp = 0;
    var tmp_0 = new Int32Array(768);
    while (tmp < 768) {
      tmp_0[tmp] = -1;
      tmp = tmp + 1 | 0;
    }
    return tmp_0;
  }
  r2x(instance) {
    fill(instance, -1);
    return protoOf(DefaultPool).c29.call(this, instance);
  }
  c29(instance) {
    return this.r2x(isIntArray(instance) ? instance : THROW_CCE());
  }
}
class HeadersDataPool$1 extends DefaultPool {
  constructor() {
    super(1000);
  }
  x28() {
    return new HeadersData();
  }
  v2x(instance) {
    instance.n2x();
    return protoOf(DefaultPool).c29.call(this, instance);
  }
  c29(instance) {
    return this.v2x(instance instanceof HeadersData ? instance : THROW_CCE());
  }
}
class ParserException extends IllegalStateException {
  constructor(message) {
    return new.target.a2y(message);
  }
  static a2y(message) {
    var $this = this.o(message);
    captureStack($this, $this.z2x_1);
    return $this;
  }
}
class MultipartEvent {}
class Preamble extends MultipartEvent {
  constructor(body) {
    super();
    this.e2y_1 = body;
  }
}
class MultipartPart extends MultipartEvent {
  constructor(headers, body) {
    super();
    this.f2y_1 = headers;
    this.g2y_1 = body;
  }
}
class Epilogue extends MultipartEvent {
  constructor(body) {
    super();
    this.h2y_1 = body;
  }
}
class Node {
  constructor(ch, exact, children) {
    this.m2y_1 = ch;
    this.n2y_1 = exact;
    this.o2y_1 = children;
    var tmp = this;
    var tmp_0 = 0;
    // Inline function 'kotlin.arrayOfNulls' call
    var tmp_1 = Array(256);
    while (tmp_0 < 256) {
      var tmp_2 = tmp_0;
      var tmp0 = this.o2y_1;
      var tmp$ret$2;
      $l$block_0: {
        // Inline function 'kotlin.collections.singleOrNull' call
        var single = null;
        var found = false;
        var _iterator__ex2g4s = tmp0.l1();
        while (_iterator__ex2g4s.m1()) {
          var element = _iterator__ex2g4s.n1();
          // Inline function 'kotlin.code' call
          var this_0 = element.m2y_1;
          if (Char__toInt_impl_vasixd(this_0) === tmp_2) {
            if (found) {
              tmp$ret$2 = null;
              break $l$block_0;
            }
            single = element;
            found = true;
          }
        }
        if (!found) {
          tmp$ret$2 = null;
          break $l$block_0;
        }
        tmp$ret$2 = single;
      }
      tmp_1[tmp_2] = tmp$ret$2;
      tmp_0 = tmp_0 + 1 | 0;
    }
    tmp.p2y_1 = tmp_1;
  }
}
class Companion {
  d2y(from) {
    var tmp = AsciiCharTree$Companion$build$lambda;
    return this.q2y(from, tmp, AsciiCharTree$Companion$build$lambda_0);
  }
  q2y(from, length, charAt) {
    var tmp$ret$0;
    $l$block_0: {
      // Inline function 'kotlin.collections.maxByOrNull' call
      var iterator = from.l1();
      if (!iterator.m1()) {
        tmp$ret$0 = null;
        break $l$block_0;
      }
      var maxElem = iterator.n1();
      if (!iterator.m1()) {
        tmp$ret$0 = maxElem;
        break $l$block_0;
      }
      var maxValue = length(maxElem);
      do {
        var e = iterator.n1();
        var v = length(e);
        if (compareTo(maxValue, v) < 0) {
          maxElem = e;
          maxValue = v;
        }
      }
       while (iterator.m1());
      tmp$ret$0 = maxElem;
    }
    var tmp0_safe_receiver = tmp$ret$0;
    var tmp;
    if (tmp0_safe_receiver == null) {
      tmp = null;
    } else {
      // Inline function 'kotlin.let' call
      tmp = length(tmp0_safe_receiver);
    }
    var tmp1_elvis_lhs = tmp;
    var tmp_0;
    if (tmp1_elvis_lhs == null) {
      throw NoSuchElementException.e2('Unable to build char tree from an empty list');
    } else {
      tmp_0 = tmp1_elvis_lhs;
    }
    var maxLen = tmp_0;
    var tmp$ret$2;
    $l$block_2: {
      // Inline function 'kotlin.collections.any' call
      var tmp_1;
      if (isInterface(from, Collection)) {
        tmp_1 = from.j1();
      } else {
        tmp_1 = false;
      }
      if (tmp_1) {
        tmp$ret$2 = false;
        break $l$block_2;
      }
      var _iterator__ex2g4s = from.l1();
      while (_iterator__ex2g4s.m1()) {
        var element = _iterator__ex2g4s.n1();
        if (length(element) === 0) {
          tmp$ret$2 = true;
          break $l$block_2;
        }
      }
      tmp$ret$2 = false;
    }
    if (tmp$ret$2)
      throw IllegalArgumentException.i2('There should be no empty entries');
    var root = ArrayList.p2();
    build(this, root, from, maxLen, 0, length, charAt);
    root.b8();
    return new AsciiCharTree(new Node(_Char___init__impl__6a9atx(0), emptyList(), root));
  }
}
class AsciiCharTree {
  constructor(root) {
    this.r2y_1 = root;
  }
}
class SubSequenceImpl {
  constructor($outer, start, end) {
    this.v2y_1 = $outer;
    this.s2y_1 = start;
    this.t2y_1 = end;
    this.u2y_1 = null;
  }
  a() {
    return this.t2y_1 - this.s2y_1 | 0;
  }
  b(index) {
    var withOffset = index + this.s2y_1 | 0;
    // Inline function 'kotlin.require' call
    if (!(index >= 0)) {
      var message = 'index is negative: ' + index;
      throw IllegalArgumentException.i2(toString(message));
    }
    // Inline function 'kotlin.require' call
    if (!(withOffset < this.t2y_1)) {
      var message_0 = 'index (' + index + ') should be less than length (' + this.a() + ')';
      throw IllegalArgumentException.i2(toString(message_0));
    }
    return getImpl(this.v2y_1, withOffset);
  }
  c(startIndex, endIndex) {
    // Inline function 'kotlin.require' call
    if (!(startIndex >= 0)) {
      var message = 'start is negative: ' + startIndex;
      throw IllegalArgumentException.i2(toString(message));
    }
    // Inline function 'kotlin.require' call
    if (!(startIndex <= endIndex)) {
      var message_0 = 'start (' + startIndex + ') should be less or equal to end (' + endIndex + ')';
      throw IllegalArgumentException.i2(toString(message_0));
    }
    // Inline function 'kotlin.require' call
    if (!(endIndex <= (this.t2y_1 - this.s2y_1 | 0))) {
      var message_1 = 'end should be less than length (' + this.a() + ')';
      throw IllegalArgumentException.i2(toString(message_1));
    }
    if (startIndex === endIndex)
      return '';
    return new SubSequenceImpl(this.v2y_1, this.s2y_1 + startIndex | 0, this.s2y_1 + endIndex | 0);
  }
  toString() {
    var tmp0_elvis_lhs = this.u2y_1;
    var tmp;
    if (tmp0_elvis_lhs == null) {
      // Inline function 'kotlin.also' call
      var this_0 = toString(copy(this.v2y_1, this.s2y_1, this.t2y_1));
      this.u2y_1 = this_0;
      tmp = this_0;
    } else {
      tmp = tmp0_elvis_lhs;
    }
    return tmp;
  }
  equals(other) {
    if (!(!(other == null) ? isCharSequence(other) : false))
      return false;
    if (!(charSequenceLength(other) === this.a()))
      return false;
    return rangeEqualsImpl(this.v2y_1, this.s2y_1, other, 0, this.a());
  }
  hashCode() {
    var tmp0_safe_receiver = this.u2y_1;
    var tmp1_elvis_lhs = tmp0_safe_receiver == null ? null : getStringHashCode(tmp0_safe_receiver);
    return tmp1_elvis_lhs == null ? hashCodeImpl(this.v2y_1, this.s2y_1, this.t2y_1) : tmp1_elvis_lhs;
  }
}
class CharArrayBuilder {
  constructor(pool) {
    pool = pool === VOID ? get_CharArrayPool() : pool;
    this.e2x_1 = pool;
    this.f2x_1 = null;
    this.g2x_1 = null;
    this.h2x_1 = null;
    this.i2x_1 = false;
    this.j2x_1 = 0;
    this.k2x_1 = 0;
  }
  a() {
    return this.k2x_1;
  }
  b(index) {
    // Inline function 'kotlin.require' call
    if (!(index >= 0)) {
      var message = 'index is negative: ' + index;
      throw IllegalArgumentException.i2(toString(message));
    }
    // Inline function 'kotlin.require' call
    if (!(index < this.k2x_1)) {
      var message_0 = 'index ' + index + ' is not in range [0, ' + this.k2x_1 + ')';
      throw IllegalArgumentException.i2(toString(message_0));
    }
    return getImpl(this, index);
  }
  c(startIndex, endIndex) {
    // Inline function 'kotlin.require' call
    if (!(startIndex <= endIndex)) {
      var message = 'startIndex (' + startIndex + ') should be less or equal to endIndex (' + endIndex + ')';
      throw IllegalArgumentException.i2(toString(message));
    }
    // Inline function 'kotlin.require' call
    if (!(startIndex >= 0)) {
      var message_0 = 'startIndex is negative: ' + startIndex;
      throw IllegalArgumentException.i2(toString(message_0));
    }
    // Inline function 'kotlin.require' call
    if (!(endIndex <= this.k2x_1)) {
      var message_1 = 'endIndex (' + endIndex + ') is greater than length (' + this.k2x_1 + ')';
      throw IllegalArgumentException.i2(toString(message_1));
    }
    return new SubSequenceImpl(this, startIndex, endIndex);
  }
  toString() {
    var tmp0_elvis_lhs = this.h2x_1;
    var tmp;
    if (tmp0_elvis_lhs == null) {
      // Inline function 'kotlin.also' call
      var this_0 = toString(copy(this, 0, this.k2x_1));
      this.h2x_1 = this_0;
      tmp = this_0;
    } else {
      tmp = tmp0_elvis_lhs;
    }
    return tmp;
  }
  equals(other) {
    if (!(!(other == null) ? isCharSequence(other) : false))
      return false;
    if (!(this.k2x_1 === charSequenceLength(other)))
      return false;
    return rangeEqualsImpl(this, 0, other, 0, this.k2x_1);
  }
  hashCode() {
    var tmp0_safe_receiver = this.h2x_1;
    var tmp1_elvis_lhs = tmp0_safe_receiver == null ? null : getStringHashCode(tmp0_safe_receiver);
    return tmp1_elvis_lhs == null ? hashCodeImpl(this, 0, this.k2x_1) : tmp1_elvis_lhs;
  }
  k1(value) {
    nonFullBuffer(this)[ensureNotNull(this.g2x_1).length - this.j2x_1 | 0] = value;
    this.h2x_1 = null;
    this.j2x_1 = this.j2x_1 - 1 | 0;
    this.k2x_1 = this.k2x_1 + 1 | 0;
    return this;
  }
  bg(value, startIndex, endIndex) {
    if (value == null)
      return this;
    var current = startIndex;
    while (current < endIndex) {
      var buffer = nonFullBuffer(this);
      var offset = buffer.length - this.j2x_1 | 0;
      var tmp0 = endIndex - current | 0;
      // Inline function 'kotlin.math.min' call
      var b = this.j2x_1;
      var bytesToCopy = Math.min(tmp0, b);
      var inductionVariable = 0;
      if (inductionVariable < bytesToCopy)
        do {
          var i = inductionVariable;
          inductionVariable = inductionVariable + 1 | 0;
          buffer[offset + i | 0] = charSequenceGet(value, current + i | 0);
        }
         while (inductionVariable < bytesToCopy);
      current = current + bytesToCopy | 0;
      this.j2x_1 = this.j2x_1 - bytesToCopy | 0;
    }
    this.h2x_1 = null;
    this.k2x_1 = this.k2x_1 + (endIndex - startIndex | 0) | 0;
    return this;
  }
  t1(value) {
    if (value == null)
      return this;
    return this.bg(value, 0, charSequenceLength(value));
  }
  n2x() {
    var list = this.f2x_1;
    if (!(list == null)) {
      this.g2x_1 = null;
      var inductionVariable = 0;
      var last = list.j2();
      if (inductionVariable < last)
        do {
          var i = inductionVariable;
          inductionVariable = inductionVariable + 1 | 0;
          this.e2x_1.f29(list.l2(i));
        }
         while (inductionVariable < last);
    } else {
      var tmp0_safe_receiver = this.g2x_1;
      if (tmp0_safe_receiver == null)
        null;
      else {
        // Inline function 'kotlin.let' call
        this.e2x_1.f29(tmp0_safe_receiver);
      }
      this.g2x_1 = null;
    }
    this.i2x_1 = true;
    this.f2x_1 = null;
    this.h2x_1 = null;
    this.k2x_1 = 0;
    this.j2x_1 = 0;
  }
}
class CharArrayPool$1 extends NoPoolImpl {
  e29() {
    return charArray(2048);
  }
}
class CharArrayPool$2 extends DefaultPool {
  constructor() {
    super(4096);
  }
  x28() {
    return charArray(2048);
  }
}
class UnsupportedMediaTypeExceptionCIO extends IOException {
  constructor(message) {
    return new.target.l2y(message);
  }
  static l2y(message) {
    var $this = this.u24(message);
    captureStack($this, $this.k2y_1);
    return $this;
  }
}
class MutableRange {
  constructor(start, end) {
    this.b2y_1 = start;
    this.c2y_1 = end;
  }
  toString() {
    return 'MutableRange(start=' + this.b2y_1 + ', end=' + this.c2y_1 + ')';
  }
}
//endregion
function get_IntArrayPool() {
  _init_properties_HttpHeadersMap_kt__hwatby();
  return IntArrayPool;
}
var IntArrayPool;
function get_HeadersDataPool() {
  _init_properties_HttpHeadersMap_kt__hwatby();
  return HeadersDataPool;
}
var HeadersDataPool;
function thresholdReached($this) {
  return $this.t2w_1 >= $this.u2w_1 * 0.75;
}
function resize($this) {
  var prevSize = $this.t2w_1;
  var prevData = $this.v2w_1;
  $this.t2w_1 = 0;
  $this.u2w_1 = imul($this.u2w_1, 2) | 128;
  var tmp = $this;
  // Inline function 'kotlin.apply' call
  var this_0 = get_HeadersDataPool().e29();
  this_0.y2w(imul(prevData.x2w(), 2) | 1);
  tmp.v2w_1 = this_0;
  var _iterator__ex2g4s = prevData.z2w().l1();
  while (_iterator__ex2g4s.m1()) {
    var headerOffset = _iterator__ex2g4s.n1();
    $this.b2x(prevData.a2x(headerOffset + 1 | 0), prevData.a2x(headerOffset + 2 | 0), prevData.a2x(headerOffset + 3 | 0), prevData.a2x(headerOffset + 4 | 0));
  }
  get_HeadersDataPool().f29(prevData);
  // Inline function 'kotlin.require' call
  // Inline function 'kotlin.require' call
  if (!(prevSize === $this.t2w_1)) {
    var message = 'Failed requirement.';
    throw IllegalArgumentException.i2(toString(message));
  }
}
function headerHasName($this, name, headerOffset) {
  var nameStartIndex = $this.v2w_1.a2x(headerOffset + 1 | 0);
  var nameEndIndex = $this.v2w_1.a2x(headerOffset + 2 | 0);
  return equalsLowerCase($this.s2w_1, nameStartIndex, nameEndIndex, name);
}
function HeadersData$headersStarts$slambda(this$0) {
  return constructCallableReference(($this$sequence, $completion) => orPromise($completion, function *($completion) {
    var joinedIndex = 0;
    var _iterator__ex2g4s = this$0.w2w_1.l1();
    while (_iterator__ex2g4s.m1()) {
      var arr = _iterator__ex2g4s.n1();
      var localIndex = 0;
      while (localIndex < arr.length) {
        if (!(this$0.a2x(joinedIndex + 0 | 0) === -1)) {
          yield* $this$sequence.hj(joinedIndex, $completion);
        }
        localIndex = localIndex + 6 | 0;
        joinedIndex = joinedIndex + 6 | 0;
      }
    }
    return Unit_instance;
  }), 1);
}
function dumpTo(_this__u8e3s4, indent, out) {
  _init_properties_HttpHeadersMap_kt__hwatby();
  var _iterator__ex2g4s = _this__u8e3s4.d2x().l1();
  while (_iterator__ex2g4s.m1()) {
    var offset = _iterator__ex2g4s.n1();
    out.t1(indent);
    out.t1(_this__u8e3s4.m2x(offset));
    out.t1(' => ');
    out.t1(_this__u8e3s4.c2x(offset));
    out.t1('\n');
  }
}
var properties_initialized_HttpHeadersMap_kt_kotj4w;
function _init_properties_HttpHeadersMap_kt__hwatby() {
  if (!properties_initialized_HttpHeadersMap_kt_kotj4w) {
    properties_initialized_HttpHeadersMap_kt_kotj4w = true;
    IntArrayPool = new IntArrayPool$1();
    HeadersDataPool = new HeadersDataPool$1();
  }
}
function get_hostForbiddenSymbols() {
  _init_properties_HttpParser_kt__gbdom1();
  return hostForbiddenSymbols;
}
var hostForbiddenSymbols;
var httpLineEndings;
var versions;
function *parseHeaders(input, builder, range, $completion) {
  range = range === VOID ? new MutableRange(0, 0) : range;
  var headers = new HttpHeadersMap(builder);
  try {
    $l$loop: while (true) {
      if ((yield* readLineStrictTo(input, builder, 8192n, VOID, $completion)) === -1n) {
        headers.n2x();
        return null;
      }
      range.c2y_1 = builder.k2x_1;
      var rangeLength = range.c2y_1 - range.b2y_1 | 0;
      if (rangeLength === 0)
        break $l$loop;
      if (rangeLength >= 8192) {
        // Inline function 'kotlin.error' call
        var message = 'Header line length limit exceeded';
        throw IllegalStateException.o(toString(message));
      }
      var nameStart = range.b2y_1;
      var nameEnd = parseHeaderName(builder, range);
      var headerEnd = range.c2y_1;
      parseHeaderValue(builder, range);
      var valueStart = range.b2y_1;
      var valueEnd = range.c2y_1;
      range.b2y_1 = headerEnd;
      headers.b2x(nameStart, nameEnd, valueStart, valueEnd);
    }
    var host = headers.o2b('Host');
    if (!(host == null)) {
      validateHostHeader(host);
    }
    return headers;
  } catch ($p) {
    if ($p instanceof Error) {
      var t = $p;
      headers.n2x();
      throw t;
    } else {
      throw $p;
    }
  }
}
function parseHeaderName(text, range) {
  _init_properties_HttpParser_kt__gbdom1();
  var index = range.b2y_1;
  var end = range.c2y_1;
  while (index < end) {
    var ch = text.b(index);
    if (ch === _Char___init__impl__6a9atx(58) && !(index === range.b2y_1)) {
      range.b2y_1 = index + 1 | 0;
      return index;
    }
    if (isDelimiter(ch)) {
      parseHeaderNameFailed(text, index, range.b2y_1, ch);
    }
    index = index + 1 | 0;
  }
  noColonFound(text, range);
}
function parseHeaderValue(text, range) {
  _init_properties_HttpParser_kt__gbdom1();
  var start = range.b2y_1;
  var end = range.c2y_1;
  var index = start;
  index = skipSpacesAndHorizontalTabs(text, index, end);
  if (index >= end) {
    range.b2y_1 = end;
    return Unit_instance;
  }
  var valueStart = index;
  var valueLastIndex = index;
  while (index < end) {
    var ch = text.b(index);
    if (ch !== _Char___init__impl__6a9atx(9) && ch !== _Char___init__impl__6a9atx(32))
      if (ch === _Char___init__impl__6a9atx(13) || ch === _Char___init__impl__6a9atx(10)) {
        characterIsNotAllowed(text, ch);
      } else
        valueLastIndex = index;
    index = index + 1 | 0;
  }
  range.b2y_1 = valueStart;
  range.c2y_1 = valueLastIndex + 1 | 0;
}
function validateHostHeader(host) {
  _init_properties_HttpParser_kt__gbdom1();
  if (endsWith(host, ':')) {
    throw ParserException.a2y("Host header with ':' should contains port: " + toString(host));
  }
  var tmp$ret$0;
  $l$block: {
    // Inline function 'kotlin.text.any' call
    var inductionVariable = 0;
    while (inductionVariable < charSequenceLength(host)) {
      var element = charSequenceGet(host, inductionVariable);
      inductionVariable = inductionVariable + 1 | 0;
      if (get_hostForbiddenSymbols().r3(new Char(element))) {
        tmp$ret$0 = true;
        break $l$block;
      }
    }
    tmp$ret$0 = false;
  }
  if (tmp$ret$0) {
    throw ParserException.a2y('Host cannot contain any of the following symbols: ' + toString(get_hostForbiddenSymbols()));
  }
}
function isDelimiter(ch) {
  _init_properties_HttpParser_kt__gbdom1();
  return Char__compareTo_impl_ypi4mb(ch, _Char___init__impl__6a9atx(32)) <= 0 || contains('"(),/:;<=>?@[\\]{}', ch);
}
function parseHeaderNameFailed(text, index, start, ch) {
  _init_properties_HttpParser_kt__gbdom1();
  if (ch === _Char___init__impl__6a9atx(58)) {
    throw ParserException.a2y('Empty header names are not allowed as per RFC7230.');
  }
  if (index === start) {
    throw ParserException.a2y('Multiline headers via line folding is not supported since it is deprecated as per RFC7230.');
  }
  characterIsNotAllowed(text, ch);
}
function noColonFound(text, range) {
  _init_properties_HttpParser_kt__gbdom1();
  var tmp2 = range.b2y_1;
  // Inline function 'kotlin.text.substring' call
  var endIndex = range.c2y_1;
  var tmp$ret$0 = toString(charSequenceSubSequence(text, tmp2, endIndex));
  throw ParserException.a2y('No colon in HTTP header in ' + tmp$ret$0 + ' in builder: \n' + toString(text));
}
function characterIsNotAllowed(text, ch) {
  _init_properties_HttpParser_kt__gbdom1();
  // Inline function 'kotlin.code' call
  var tmp$ret$0 = Char__toInt_impl_vasixd(ch);
  throw ParserException.a2y('Character with code ' + (tmp$ret$0 & 255) + ' is not allowed in header names, \n' + toString(text));
}
var properties_initialized_HttpParser_kt_uedryv;
function _init_properties_HttpParser_kt__gbdom1() {
  if (!properties_initialized_HttpParser_kt_uedryv) {
    properties_initialized_HttpParser_kt_uedryv = true;
    hostForbiddenSymbols = setOf([new Char(_Char___init__impl__6a9atx(47)), new Char(_Char___init__impl__6a9atx(63)), new Char(_Char___init__impl__6a9atx(35)), new Char(_Char___init__impl__6a9atx(64))]);
    httpLineEndings = LineEndingMode__plus_impl_ttpz2j(Companion_getInstance_0().g28_1, Companion_getInstance_0().f28_1);
    versions = Companion_instance.d2y(listOf(['HTTP/1.0', 'HTTP/1.1']));
  }
}
function get_CrLf() {
  _init_properties_Multipart_kt__ato98a();
  return CrLf;
}
var CrLf;
function get_PrefixString() {
  _init_properties_Multipart_kt__ato98a();
  return PrefixString;
}
var PrefixString;
function parseMultipart(_this__u8e3s4, input, contentType, contentLength, maxPartSize) {
  maxPartSize = maxPartSize === VOID ? 9223372036854775807n : maxPartSize;
  _init_properties_Multipart_kt__ato98a();
  if (!MultiPart_getInstance().s2j(contentType)) {
    throw UnsupportedMediaTypeExceptionCIO.l2y('Failed to parse multipart: Content-Type should be multipart/* but it is ' + toString(contentType));
  }
  var boundaryByteBuffer = parseBoundaryInternal(contentType);
  var boundaryBytes = ByteString.d21(boundaryByteBuffer);
  return parseMultipart_0(_this__u8e3s4, boundaryBytes, input, contentLength, maxPartSize);
}
function parseBoundaryInternal(contentType) {
  _init_properties_Multipart_kt__ato98a();
  var boundaryParameter = findBoundary(contentType);
  if (boundaryParameter === -1) {
    throw IOException.u24("Failed to parse multipart: Content-Type's boundary parameter is missing");
  }
  var boundaryStart = boundaryParameter + 9 | 0;
  var boundaryBytes = new Int8Array(74);
  var position = {_v: 0};
  parseBoundaryInternal$put(position, boundaryBytes, 13);
  parseBoundaryInternal$put(position, boundaryBytes, 10);
  parseBoundaryInternal$put(position, boundaryBytes, 45);
  parseBoundaryInternal$put(position, boundaryBytes, 45);
  var state = 0;
  var inductionVariable = boundaryStart;
  var last = charSequenceLength(contentType);
  if (inductionVariable < last)
    loop: do {
      var i = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      var ch = charSequenceGet(contentType, i);
      // Inline function 'kotlin.code' call
      var v = Char__toInt_impl_vasixd(ch) & 65535;
      if ((v & 65535) > 127) {
        throw IOException.u24('Failed to parse multipart: wrong boundary byte 0x' + toString_0(v, 16) + ' - should be 7bit character');
      }
      switch (state) {
        case 0:
          if (ch !== _Char___init__impl__6a9atx(32))
            if (ch === _Char___init__impl__6a9atx(34)) {
              state = 2;
            } else if (ch === _Char___init__impl__6a9atx(59) || ch === _Char___init__impl__6a9atx(44)) {
              break loop;
            } else {
              state = 1;
              parseBoundaryInternal$put(position, boundaryBytes, toByte(v));
            }

          break;
        case 1:
          if (ch === _Char___init__impl__6a9atx(32) || ch === _Char___init__impl__6a9atx(44) || ch === _Char___init__impl__6a9atx(59)) {
            break loop;
          } else {
            parseBoundaryInternal$put(position, boundaryBytes, toByte(v));
          }

          break;
        case 2:
          if (ch === _Char___init__impl__6a9atx(92)) {
            state = 3;
          } else if (ch === _Char___init__impl__6a9atx(34)) {
            break loop;
          } else {
            parseBoundaryInternal$put(position, boundaryBytes, toByte(v));
          }

          break;
        case 3:
          parseBoundaryInternal$put(position, boundaryBytes, toByte(v));
          state = 2;
          break;
      }
    }
     while (inductionVariable < last);
  if (position._v === 4) {
    throw IOException.u24('Empty multipart boundary is not allowed');
  }
  return copyOfRange(boundaryBytes, 0, position._v);
}
function parseMultipart_0(_this__u8e3s4, boundaryPrefixed, input, totalLength, maxPartSize) {
  _init_properties_Multipart_kt__ato98a();
  return produce(_this__u8e3s4, VOID, VOID, parseMultipart$slambda(input, boundaryPrefixed, maxPartSize, totalLength));
}
function findBoundary(contentType) {
  _init_properties_Multipart_kt__ato98a();
  var state = 0;
  var paramNameCount = 0;
  var inductionVariable = 0;
  var last = charSequenceLength(contentType) - 1 | 0;
  if (inductionVariable <= last)
    do {
      var i = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      var ch = charSequenceGet(contentType, i);
      switch (state) {
        case 0:
          if (ch === _Char___init__impl__6a9atx(59)) {
            state = 1;
            paramNameCount = 0;
          }

          break;
        case 1:
          if (ch === _Char___init__impl__6a9atx(61)) {
            state = 2;
          } else if (ch === _Char___init__impl__6a9atx(59)) {
            paramNameCount = 0;
          } else if (ch === _Char___init__impl__6a9atx(44)) {
            state = 0;
          } else if (ch !== _Char___init__impl__6a9atx(32))
            if (paramNameCount === 0 && startsWith(contentType, 'boundary=', i, true)) {
              return i;
            } else {
              paramNameCount = paramNameCount + 1 | 0;
            }

          break;
        case 2:
          if (ch === _Char___init__impl__6a9atx(34))
            state = 3;
          else if (ch === _Char___init__impl__6a9atx(44))
            state = 0;
          else if (ch === _Char___init__impl__6a9atx(59)) {
            state = 1;
            paramNameCount = 0;
          }

          break;
        case 3:
          if (ch === _Char___init__impl__6a9atx(34)) {
            state = 1;
            paramNameCount = 0;
          } else if (ch === _Char___init__impl__6a9atx(92)) {
            state = 4;
          }

          break;
        case 4:
          state = 3;
          break;
      }
    }
     while (inductionVariable <= last);
  return -1;
}
function *parsePreambleImpl(boundary, input, output, limit, $completion) {
  limit = limit === VOID ? 9223372036854775807n : limit;
  return yield* readUntil(input, boundary, output, limit, true, $completion);
}
function *parsePartHeadersImpl(input, $completion) {
  var builder = new CharArrayBuilder();
  try {
    var tmp0_elvis_lhs = yield* parseHeaders(input, builder, VOID, $completion);
    var tmp;
    if (tmp0_elvis_lhs == null) {
      throw EOFException.p21('Failed to parse multipart headers: unexpected end of stream');
    } else {
      tmp = tmp0_elvis_lhs;
    }
    return tmp;
  } catch ($p) {
    if ($p instanceof Error) {
      var t = $p;
      builder.n2x();
      throw t;
    } else {
      throw $p;
    }
  }
}
function *parsePartBodyImpl(boundaryPrefixed, input, output, headers, limit, $completion) {
  var tmp0_safe_receiver = headers.o2b('Content-Length');
  var contentLength = tmp0_safe_receiver == null ? null : parseDecLong(tmp0_safe_receiver);
  var tmp;
  if (contentLength == null) {
    tmp = yield* readUntil(input, boundaryPrefixed, output, limit, true, $completion);
  } else {
    if (0n <= contentLength ? contentLength <= limit : false) {
      tmp = add(yield* copyTo(input, output, contentLength, $completion), yield* skipIfFoundReadCount(input, boundaryPrefixed, $completion));
    } else {
      throwLimitExceeded(contentLength, limit);
    }
  }
  var byteCount = tmp;
  yield* output.x26($completion);
  return byteCount;
}
function *skipIfFoundReadCount(_this__u8e3s4, prefix, $completion) {
  var tmp;
  if (yield* skipIfFound(_this__u8e3s4, prefix, $completion)) {
    tmp = fromInt(prefix.j2());
  } else {
    tmp = 0n;
  }
  return tmp;
}
function throwLimitExceeded(actual, limit) {
  _init_properties_Multipart_kt__ato98a();
  throw IOException.u24('Multipart content length exceeds limit ' + actual.toString() + ' > ' + limit.toString() + '; ' + "limit is defined using 'formFieldLimit' argument");
}
function parseBoundaryInternal$put(position, boundaryBytes, value) {
  if (position._v >= boundaryBytes.length) {
    throw IOException.u24("Failed to parse multipart: boundary shouldn't be longer than 70 characters");
  }
  var _unary__edvuaz = position._v;
  position._v = _unary__edvuaz + 1 | 0;
  boundaryBytes[_unary__edvuaz] = value;
}
function parseMultipart$slambda$slambda($firstBoundary, $countedInput) {
  return constructCallableReference(($this$writer, $completion) => orPromise($completion, function *($completion) {
    yield* /*#__NOINLINE__*/parsePreambleImpl($firstBoundary, $countedInput, $this$writer.w27_1, 8193n, $completion);
    yield* $this$writer.w27_1.z26($completion);
    return Unit_instance;
  }), 1);
}
function parseMultipart$slambda($input, $boundaryPrefixed, $maxPartSize, $totalLength) {
  return constructCallableReference(($this$produce, $completion) => orPromise($completion, function *($completion) {
    var countedInput = counted($input);
    var readBeforeParse = countedInput.d28();
    var firstBoundary = $boundaryPrefixed.f21(get_PrefixString().j2());
    var preambleData = yield* readBuffer(writer($this$produce, VOID, VOID, parseMultipart$slambda$slambda(firstBoundary, countedInput)).m27_1, $completion);
    var tmp;
    if (get_remaining(preambleData) > 0n) {
      tmp = yield* $this$produce.h18(new Preamble(preambleData), $completion);
    }
    $l$loop: while (!countedInput.n26() && !(yield* skipIfFound(countedInput, get_PrefixString(), $completion))) {
      yield* skipIfFound(countedInput, get_CrLf(), $completion);
      if (yield* skipIfFound(countedInput, firstBoundary, $completion)) {
        continue $l$loop;
      }
      var body = new ByteChannel();
      var headers = CompletableDeferred();
      var part = new MultipartPart(headers, body);
      yield* $this$produce.h18(part, $completion);
      var headersMap = null;
      try {
        headersMap = yield* /*#__NOINLINE__*/parsePartHeadersImpl(countedInput, $completion);
        if (!headers.vz(headersMap)) {
          headersMap.n2x();
          throw CancellationException.n('Multipart processing has been cancelled');
        }
        yield* /*#__NOINLINE__*/parsePartBodyImpl($boundaryPrefixed, countedInput, body, headersMap, $maxPartSize, $completion);
        body.g6();
      } catch ($p) {
        if ($p instanceof Error) {
          var cause = $p;
          if (headers.wz(cause)) {
            var tmp0_safe_receiver = headersMap;
            if (tmp0_safe_receiver == null)
              null;
            else {
              tmp0_safe_receiver.n2x();
            }
          }
          close(body, cause);
          throw cause;
        } else {
          throw $p;
        }
      }
    }
    yield* skipIfFound(countedInput, get_CrLf(), $completion);
    yield* skipIfFound(countedInput, get_CrLf(), $completion);
    var tmp_0;
    if (!($totalLength == null)) {
      var consumedExceptEpilogue = subtract(countedInput.d28(), readBeforeParse);
      var size = subtract($totalLength, consumedExceptEpilogue);
      if (size > 2147483647n)
        throw IOException.u24('Failed to parse multipart: prologue is too long');
      var tmp_1;
      if (size > 0n) {
        tmp_1 = yield* $this$produce.h18(new Epilogue(yield* readPacket(countedInput, convertToInt(size), $completion)), $completion);
      }
      tmp_0 = tmp_1;
    } else {
      var epilogueContent = yield* readBuffer(countedInput, $completion);
      var tmp_2;
      if (!epilogueContent.r21()) {
        tmp_2 = yield* $this$produce.h18(new Epilogue(epilogueContent), $completion);
      }
      tmp_0 = tmp_2;
    }
    return Unit_instance;
  }), 1);
}
var properties_initialized_Multipart_kt_wu0sh0;
function _init_properties_Multipart_kt__ato98a() {
  if (!properties_initialized_Multipart_kt_wu0sh0) {
    properties_initialized_Multipart_kt_wu0sh0 = true;
    CrLf = ByteString.d21(toByteArray_0('\r\n'));
    PrefixString = ByteString_0(new Int8Array([45, 45]));
  }
}
function build($this, resultList, from, maxLength, idx, length, charAt) {
  // Inline function 'kotlin.collections.groupBy' call
  // Inline function 'kotlin.collections.groupByTo' call
  var destination = LinkedHashMap.c5();
  var _iterator__ex2g4s = from.l1();
  while (_iterator__ex2g4s.m1()) {
    var element = _iterator__ex2g4s.n1();
    var key = charAt(element, idx);
    // Inline function 'kotlin.collections.getOrPut' call
    var value = destination.b4(key);
    var tmp;
    if (value == null) {
      var answer = ArrayList.p2();
      destination.i4(key, answer);
      tmp = answer;
    } else {
      tmp = value;
    }
    var list = tmp;
    list.a2(element);
  }
  // Inline function 'kotlin.collections.forEach' call
  // Inline function 'kotlin.collections.iterator' call
  var _iterator__ex2g4s_0 = destination.u2().l1();
  while (_iterator__ex2g4s_0.m1()) {
    var element_0 = _iterator__ex2g4s_0.n1();
    // Inline function 'kotlin.collections.component1' call
    var ch = element_0.v2().f3_1;
    // Inline function 'kotlin.collections.component2' call
    var list_0 = element_0.w2();
    var nextIdx = idx + 1 | 0;
    var children = ArrayList.p2();
    var tmp_0 = Companion_instance;
    // Inline function 'kotlin.collections.filter' call
    // Inline function 'kotlin.collections.filterTo' call
    var destination_0 = ArrayList.p2();
    var _iterator__ex2g4s_1 = list_0.l1();
    while (_iterator__ex2g4s_1.m1()) {
      var element_1 = _iterator__ex2g4s_1.n1();
      if (length(element_1) > nextIdx) {
        destination_0.a2(element_1);
      }
    }
    build(tmp_0, children, destination_0, maxLength, nextIdx, length, charAt);
    children.b8();
    // Inline function 'kotlin.collections.filter' call
    // Inline function 'kotlin.collections.filterTo' call
    var destination_1 = ArrayList.p2();
    var _iterator__ex2g4s_2 = list_0.l1();
    while (_iterator__ex2g4s_2.m1()) {
      var element_2 = _iterator__ex2g4s_2.n1();
      if (length(element_2) === nextIdx) {
        destination_1.a2(element_2);
      }
    }
    resultList.a2(new Node(ch, destination_1, children));
  }
}
function AsciiCharTree$Companion$build$lambda(it) {
  return charSequenceLength(it);
}
function AsciiCharTree$Companion$build$lambda_0(s, idx) {
  return new Char(charSequenceGet(s, idx));
}
var Companion_instance;
function Companion_getInstance_1() {
  return Companion_instance;
}
function getImpl($this, index) {
  return bufferForIndex($this, index)[index % ensureNotNull($this.g2x_1).length | 0];
}
function copy($this, startIndex, endIndex) {
  if (startIndex === endIndex)
    return '';
  var builder = StringBuilder.rb(endIndex - startIndex | 0);
  var buffer;
  var base = startIndex - (startIndex % 2048 | 0) | 0;
  while (base < endIndex) {
    buffer = bufferForIndex($this, base);
    // Inline function 'kotlin.comparisons.maxOf' call
    var b = startIndex - base | 0;
    var innerStartIndex = Math.max(0, b);
    // Inline function 'kotlin.comparisons.minOf' call
    var a = endIndex - base | 0;
    var innerEndIndex = Math.min(a, 2048);
    var inductionVariable = innerStartIndex;
    if (inductionVariable < innerEndIndex)
      do {
        var innerIndex = inductionVariable;
        inductionVariable = inductionVariable + 1 | 0;
        builder.k1(buffer[innerIndex]);
      }
       while (inductionVariable < innerEndIndex);
    base = base + 2048 | 0;
  }
  return builder;
}
function bufferForIndex($this, index) {
  var list = $this.f2x_1;
  if (list == null) {
    if (index >= 2048) {
      throwSingleBuffer($this, index);
    }
    var tmp0_elvis_lhs = $this.g2x_1;
    var tmp;
    if (tmp0_elvis_lhs == null) {
      throwSingleBuffer($this, index);
    } else {
      tmp = tmp0_elvis_lhs;
    }
    return tmp;
  }
  return list.l2(index / ensureNotNull($this.g2x_1).length | 0);
}
function throwSingleBuffer($this, index) {
  if ($this.i2x_1)
    throw IllegalStateException.o('Buffer is already released');
  throw IndexOutOfBoundsException.pd('' + index + ' is not in range [0; ' + currentPosition($this) + ')');
}
function nonFullBuffer($this) {
  return $this.j2x_1 === 0 ? appendNewArray($this) : ensureNotNull($this.g2x_1);
}
function appendNewArray($this) {
  var newBuffer = $this.e2x_1.e29();
  var existing = $this.g2x_1;
  $this.g2x_1 = newBuffer;
  $this.j2x_1 = newBuffer.length;
  $this.i2x_1 = false;
  if (!(existing == null)) {
    var tmp0_elvis_lhs = $this.f2x_1;
    var tmp;
    if (tmp0_elvis_lhs == null) {
      // Inline function 'kotlin.also' call
      var this_0 = ArrayList.p2();
      $this.f2x_1 = this_0;
      this_0.a2(existing);
      tmp = this_0;
    } else {
      tmp = tmp0_elvis_lhs;
    }
    var list = tmp;
    list.a2(newBuffer);
  }
  return newBuffer;
}
function rangeEqualsImpl($this, start, other, otherStart, length) {
  var inductionVariable = 0;
  if (inductionVariable < length)
    do {
      var i = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      if (!(getImpl($this, start + i | 0) === charSequenceGet(other, otherStart + i | 0)))
        return false;
    }
     while (inductionVariable < length);
  return true;
}
function hashCodeImpl($this, start, end) {
  var hc = 0;
  var inductionVariable = start;
  if (inductionVariable < end)
    do {
      var i = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      var tmp = imul(31, hc);
      // Inline function 'kotlin.code' call
      var this_0 = getImpl($this, i);
      hc = tmp + Char__toInt_impl_vasixd(this_0) | 0;
    }
     while (inductionVariable < end);
  return hc;
}
function currentPosition($this) {
  return ensureNotNull($this.g2x_1).length - $this.j2x_1 | 0;
}
function get_CharArrayPool() {
  _init_properties_CharArrayPool_kt__u4nq0d();
  return CharArrayPool;
}
var CharArrayPool;
var properties_initialized_CharArrayPool_kt_aq0u0f;
function _init_properties_CharArrayPool_kt__u4nq0d() {
  if (!properties_initialized_CharArrayPool_kt_aq0u0f) {
    properties_initialized_CharArrayPool_kt_aq0u0f = true;
    var tmp;
    if (isPoolingDisabled()) {
      tmp = new CharArrayPool$1();
    } else {
      tmp = new CharArrayPool$2();
    }
    CharArrayPool = tmp;
  }
}
var DefaultHttpMethods;
var HexTable;
var HexLetterTable;
function hashCodeLowerCase(_this__u8e3s4, start, end) {
  start = start === VOID ? 0 : start;
  end = end === VOID ? charSequenceLength(_this__u8e3s4) : end;
  _init_properties_Chars_kt__d3i39x();
  var hashCode = 0;
  var inductionVariable = start;
  if (inductionVariable < end)
    do {
      var pos = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      // Inline function 'kotlin.code' call
      var this_0 = charSequenceGet(_this__u8e3s4, pos);
      // Inline function 'io.ktor.http.cio.internals.toLowerCase' call
      var this_1 = Char__toInt_impl_vasixd(this_0);
      var tmp;
      // Inline function 'kotlin.code' call
      var this_2 = _Char___init__impl__6a9atx(65);
      var containsLower = Char__toInt_impl_vasixd(this_2);
      var tmp_0;
      // Inline function 'kotlin.code' call
      var this_3 = _Char___init__impl__6a9atx(90);
      if (this_1 <= Char__toInt_impl_vasixd(this_3)) {
        tmp_0 = containsLower <= this_1;
      } else {
        tmp_0 = false;
      }
      if (tmp_0) {
        // Inline function 'kotlin.code' call
        var this_4 = _Char___init__impl__6a9atx(97);
        var tmp_1 = Char__toInt_impl_vasixd(this_4);
        // Inline function 'kotlin.code' call
        var this_5 = _Char___init__impl__6a9atx(65);
        tmp = tmp_1 + (this_1 - Char__toInt_impl_vasixd(this_5) | 0) | 0;
      } else {
        tmp = this_1;
      }
      var v = tmp;
      hashCode = imul(31, hashCode) + v | 0;
    }
     while (inductionVariable < end);
  return hashCode;
}
function equalsLowerCase(_this__u8e3s4, start, end, other) {
  start = start === VOID ? 0 : start;
  end = end === VOID ? charSequenceLength(_this__u8e3s4) : end;
  _init_properties_Chars_kt__d3i39x();
  if (!((end - start | 0) === charSequenceLength(other)))
    return false;
  var inductionVariable = start;
  if (inductionVariable < end)
    do {
      var pos = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      // Inline function 'kotlin.code' call
      var this_0 = charSequenceGet(_this__u8e3s4, pos);
      // Inline function 'io.ktor.http.cio.internals.toLowerCase' call
      var this_1 = Char__toInt_impl_vasixd(this_0);
      var tmp;
      // Inline function 'kotlin.code' call
      var this_2 = _Char___init__impl__6a9atx(65);
      var containsLower = Char__toInt_impl_vasixd(this_2);
      var tmp_0;
      // Inline function 'kotlin.code' call
      var this_3 = _Char___init__impl__6a9atx(90);
      if (this_1 <= Char__toInt_impl_vasixd(this_3)) {
        tmp_0 = containsLower <= this_1;
      } else {
        tmp_0 = false;
      }
      if (tmp_0) {
        // Inline function 'kotlin.code' call
        var this_4 = _Char___init__impl__6a9atx(97);
        var tmp_1 = Char__toInt_impl_vasixd(this_4);
        // Inline function 'kotlin.code' call
        var this_5 = _Char___init__impl__6a9atx(65);
        tmp = tmp_1 + (this_1 - Char__toInt_impl_vasixd(this_5) | 0) | 0;
      } else {
        tmp = this_1;
      }
      var tmp_2 = tmp;
      // Inline function 'kotlin.code' call
      var this_6 = charSequenceGet(other, pos - start | 0);
      // Inline function 'io.ktor.http.cio.internals.toLowerCase' call
      var this_7 = Char__toInt_impl_vasixd(this_6);
      var tmp_3;
      // Inline function 'kotlin.code' call
      var this_8 = _Char___init__impl__6a9atx(65);
      var containsLower_0 = Char__toInt_impl_vasixd(this_8);
      var tmp_4;
      // Inline function 'kotlin.code' call
      var this_9 = _Char___init__impl__6a9atx(90);
      if (this_7 <= Char__toInt_impl_vasixd(this_9)) {
        tmp_4 = containsLower_0 <= this_7;
      } else {
        tmp_4 = false;
      }
      if (tmp_4) {
        // Inline function 'kotlin.code' call
        var this_10 = _Char___init__impl__6a9atx(97);
        var tmp_5 = Char__toInt_impl_vasixd(this_10);
        // Inline function 'kotlin.code' call
        var this_11 = _Char___init__impl__6a9atx(65);
        tmp_3 = tmp_5 + (this_7 - Char__toInt_impl_vasixd(this_11) | 0) | 0;
      } else {
        tmp_3 = this_7;
      }
      if (!(tmp_2 === tmp_3))
        return false;
    }
     while (inductionVariable < end);
  return true;
}
function parseDecLong(_this__u8e3s4) {
  _init_properties_Chars_kt__d3i39x();
  var length = charSequenceLength(_this__u8e3s4);
  if (length > 19) {
    numberFormatException(_this__u8e3s4);
  }
  if (length === 19)
    return parseDecLongWithCheck(_this__u8e3s4);
  var result = 0n;
  var inductionVariable = 0;
  if (inductionVariable < length)
    do {
      var i = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      // Inline function 'kotlin.code' call
      var this_0 = charSequenceGet(_this__u8e3s4, i);
      var tmp$ret$0 = Char__toInt_impl_vasixd(this_0);
      var digit = subtract(fromInt(tmp$ret$0), 48n);
      if (digit < 0n || digit > 9n) {
        numberFormatException_0(_this__u8e3s4, i);
      }
      result = add(add(shiftLeft(result, 3), shiftLeft(result, 1)), digit);
    }
     while (inductionVariable < length);
  return result;
}
function numberFormatException(cs) {
  _init_properties_Chars_kt__d3i39x();
  throw NumberFormatException.ee('Invalid number ' + toString(cs) + ': too large for Long type');
}
function parseDecLongWithCheck(_this__u8e3s4) {
  _init_properties_Chars_kt__d3i39x();
  var result = 0n;
  var inductionVariable = 0;
  var last = charSequenceLength(_this__u8e3s4) - 1 | 0;
  if (inductionVariable <= last)
    do {
      var i = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      // Inline function 'kotlin.code' call
      var this_0 = charSequenceGet(_this__u8e3s4, i);
      var tmp$ret$0 = Char__toInt_impl_vasixd(this_0);
      var digit = subtract(fromInt(tmp$ret$0), 48n);
      if (digit < 0n || digit > 9n) {
        numberFormatException_0(_this__u8e3s4, i);
      }
      result = add(add(shiftLeft(result, 3), shiftLeft(result, 1)), digit);
      if (result < 0n) {
        numberFormatException(_this__u8e3s4);
      }
    }
     while (inductionVariable <= last);
  return result;
}
function numberFormatException_0(cs, idx) {
  _init_properties_Chars_kt__d3i39x();
  throw NumberFormatException.ee('Invalid number: ' + toString(cs) + ', wrong digit: ' + toString_1(charSequenceGet(cs, idx)) + ' at position ' + idx);
}
function DefaultHttpMethods$lambda(it) {
  _init_properties_Chars_kt__d3i39x();
  return it.t2p_1.length;
}
function DefaultHttpMethods$lambda_0(m, idx) {
  _init_properties_Chars_kt__d3i39x();
  return new Char(charCodeAt(m.t2p_1, idx));
}
var properties_initialized_Chars_kt_phjfhp;
function _init_properties_Chars_kt__d3i39x() {
  if (!properties_initialized_Chars_kt_phjfhp) {
    properties_initialized_Chars_kt_phjfhp = true;
    var tmp = Companion_instance;
    var tmp_0 = Companion_getInstance().s2p_1;
    var tmp_1 = DefaultHttpMethods$lambda;
    DefaultHttpMethods = tmp.q2y(tmp_0, tmp_1, DefaultHttpMethods$lambda_0);
    // Inline function 'kotlin.collections.map' call
    var this_0 = numberRangeToNumber(0, 255);
    // Inline function 'kotlin.collections.mapTo' call
    var destination = ArrayList.z1(collectionSizeOrDefault(this_0, 10));
    var inductionVariable = this_0.z2_1;
    var last = this_0.a3_1;
    if (inductionVariable <= last)
      do {
        var item = inductionVariable;
        inductionVariable = inductionVariable + 1 | 0;
        var v = item;
        var tmp_2;
        if (48 <= v ? v <= 57 : false) {
          tmp_2 = subtract(numberToLong(v), 48n);
        } else {
          var tmp_3;
          var tmp_4 = fromInt(v);
          // Inline function 'kotlin.code' call
          var this_1 = _Char___init__impl__6a9atx(97);
          var tmp$ret$3 = Char__toInt_impl_vasixd(this_1);
          if (tmp_4 >= fromInt(tmp$ret$3)) {
            var tmp_5 = fromInt(v);
            // Inline function 'kotlin.code' call
            var this_2 = _Char___init__impl__6a9atx(102);
            var tmp$ret$4 = Char__toInt_impl_vasixd(this_2);
            tmp_3 = tmp_5 <= fromInt(tmp$ret$4);
          } else {
            tmp_3 = false;
          }
          if (tmp_3) {
            // Inline function 'kotlin.code' call
            var this_3 = _Char___init__impl__6a9atx(97);
            var tmp$ret$5 = Char__toInt_impl_vasixd(this_3);
            // Inline function 'kotlin.Long.plus' call
            var this_4 = subtract(numberToLong(v), fromInt(tmp$ret$5));
            tmp_2 = add(this_4, fromInt(10));
          } else {
            var tmp_6;
            var tmp_7 = fromInt(v);
            // Inline function 'kotlin.code' call
            var this_5 = _Char___init__impl__6a9atx(65);
            var tmp$ret$7 = Char__toInt_impl_vasixd(this_5);
            if (tmp_7 >= fromInt(tmp$ret$7)) {
              var tmp_8 = fromInt(v);
              // Inline function 'kotlin.code' call
              var this_6 = _Char___init__impl__6a9atx(70);
              var tmp$ret$8 = Char__toInt_impl_vasixd(this_6);
              tmp_6 = tmp_8 <= fromInt(tmp$ret$8);
            } else {
              tmp_6 = false;
            }
            if (tmp_6) {
              // Inline function 'kotlin.code' call
              var this_7 = _Char___init__impl__6a9atx(65);
              var tmp$ret$9 = Char__toInt_impl_vasixd(this_7);
              // Inline function 'kotlin.Long.plus' call
              var this_8 = subtract(numberToLong(v), fromInt(tmp$ret$9));
              tmp_2 = add(this_8, fromInt(10));
            } else {
              tmp_2 = -1n;
            }
          }
        }
        var tmp$ret$2 = tmp_2;
        destination.a2(tmp$ret$2);
      }
       while (!(item === last));
    HexTable = toLongArray(destination);
    // Inline function 'kotlin.collections.map' call
    var this_9 = numberRangeToNumber(0, 15);
    // Inline function 'kotlin.collections.mapTo' call
    var destination_0 = ArrayList.z1(collectionSizeOrDefault(this_9, 10));
    var inductionVariable_0 = this_9.z2_1;
    var last_0 = this_9.a3_1;
    if (inductionVariable_0 <= last_0)
      do {
        var item_0 = inductionVariable_0;
        inductionVariable_0 = inductionVariable_0 + 1 | 0;
        var it = item_0;
        var tmp_9;
        if (it < 10) {
          tmp_9 = toByte(48 + it | 0);
        } else {
          // Inline function 'kotlin.code' call
          var this_10 = Char__minus_impl_a2frrh(Char__plus_impl_qi7pgj(_Char___init__impl__6a9atx(97), it), 10);
          var tmp$ret$3_0 = Char__toInt_impl_vasixd(this_10);
          tmp_9 = toByte(tmp$ret$3_0);
        }
        var tmp$ret$2_0 = tmp_9;
        destination_0.a2(tmp$ret$2_0);
      }
       while (!(item_0 === last_0));
    HexLetterTable = toByteArray(destination_0);
  }
}
function skipSpacesAndHorizontalTabs(text, start, end) {
  var index = start;
  $l$loop: while (index < end) {
    var ch = text.b(index);
    if (!isWhitespace(ch) && !(ch === _Char___init__impl__6a9atx(9)))
      break $l$loop;
    index = index + 1 | 0;
  }
  return index;
}
function isPoolingDisabled() {
  return false;
}
//region block: post-declaration
initMetadataForClass(CIOMultipartDataBase, 'CIOMultipartDataBase', VOID, VOID, [MultiPartData], [0, 1]);
initMetadataForClass(HttpHeadersMap, 'HttpHeadersMap');
initMetadataForClass(HeadersData, 'HeadersData', HeadersData);
initMetadataForClass(IntArrayPool$1);
initMetadataForClass(HeadersDataPool$1);
initMetadataForClass(ParserException, 'ParserException');
initMetadataForClass(MultipartEvent, 'MultipartEvent', VOID, VOID, VOID, [0]);
initMetadataForClass(Preamble, 'Preamble', VOID, VOID, VOID, [0]);
initMetadataForClass(MultipartPart, 'MultipartPart', VOID, VOID, VOID, [0]);
initMetadataForClass(Epilogue, 'Epilogue', VOID, VOID, VOID, [0]);
initMetadataForClass(Node, 'Node');
initMetadataForCompanion(Companion);
initMetadataForClass(AsciiCharTree, 'AsciiCharTree');
initMetadataForClass(SubSequenceImpl, 'SubSequenceImpl', VOID, VOID, [CharSequence]);
initMetadataForClass(CharArrayBuilder, 'CharArrayBuilder', CharArrayBuilder, VOID, [CharSequence]);
initMetadataForClass(CharArrayPool$1);
initMetadataForClass(CharArrayPool$2);
initMetadataForClass(UnsupportedMediaTypeExceptionCIO, 'UnsupportedMediaTypeExceptionCIO');
initMetadataForClass(MutableRange, 'MutableRange');
//endregion
//region block: init
Companion_instance = new Companion();
//endregion
//region block: exports
export {
  CIOMultipartDataBase as CIOMultipartDataBase3o5u8w437tq5s,
};
//endregion

//# sourceMappingURL=ktor-ktor-http-cio.mjs.map
