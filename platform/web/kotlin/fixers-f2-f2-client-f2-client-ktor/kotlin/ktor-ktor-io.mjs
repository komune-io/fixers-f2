import {
  Companion_instancejy2056q2ma5w as Companion_instance,
  Unit_instance3kz1zqli6gr1r as Unit_instance,
  _Result___init__impl__xyqfz832zdzqrocazzs as _Result___init__impl__xyqfz8,
  initMetadataForCompanion1wyw17z38v6ac as initMetadataForCompanion,
  VOID3gxj6tk5isa35 as VOID,
  initMetadataForObject1cxne3s9w65el as initMetadataForObject,
  toString30pk9tzaqopn as toString,
  hashCodeq5arwsb9dgti as hashCode,
  equals2au1ep9vhcato as equals,
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
  createFailure8paxfkfa5dc7 as createFailure,
  Result3t1vadv16kmzk as Result,
  initMetadataForInterface1egvbzx539z91 as initMetadataForInterface,
  toString1h6jjoch8cjt8 as toString_0,
  newThrowablezl37abp36p5f as newThrowable,
  stackTraceToString2670q6lbhdojj as stackTraceToString,
  protoOf180f3jzyo7rfj as protoOf,
  isInterface3d6p8outrmvmk as isInterface,
  FunctionAdapter3lcrrz3moet5b as FunctionAdapter,
  CancellationException3b36o9qz53rgr as CancellationException,
  fromInt2ii0rejb1w62w as fromInt,
  numberToLong2pakxeg38estk as numberToLong,
  add2suhfggl4zvkk as add,
  intercepted2ogpsikxxj4u0 as intercepted,
  noWhenBranchMatchedException2a6r7ubxgky5j as noWhenBranchMatchedException,
  convertToInty04h231mmjoh as convertToInt,
  IllegalStateExceptionkoljg5n0nrlr as IllegalStateException,
  toString1pkumu07cwy4m as toString_1,
  captureStack1fzi4aczwc4hg as captureStack,
  THROW_CCE2g6jy02ryeudk as THROW_CCE,
  get_ONEazvfdh9ju3d4 as get_ONE,
  replace3le3ie7l9k8aq as replace,
  IllegalArgumentException2asla15b5jaob as IllegalArgumentException,
  subtract2orl8z9upxd9l as subtract,
  orPromise1iucc2to2udt2 as orPromise,
  constructCallableReference23y65rf941mch as constructCallableReference,
  EmptyCoroutineContext_instance3dshdbvg42f79 as EmptyCoroutineContext_instance,
  Continuation1aa2oekvx7jm7 as Continuation,
  $3l5vvh8yg0ier as $,
  Enum3alwj03lh1n41 as Enum,
  ArrayList3it5z8td81qkl as ArrayList,
  listOf1jh22dvmctj1r as listOf,
  StringBuildermazzzhj6kkai as StringBuilder,
  charSequenceLength3278n89t01tmv as charSequenceLength,
  encodeToByteArray1onwao0uakjfh as encodeToByteArray,
  AutoCloseable1l5p57f9lp7kv as AutoCloseable,
  _Char___init__impl__6a9atxrzocepzq3zgi as _Char___init__impl__6a9atx,
  replaceqbix900hl8kl as replace_0,
  getStringHashCode26igk1bx568vk as getStringHashCode,
  charSequenceSubSequence1iwpdba8s3jc7 as charSequenceSubSequence,
  isCharSequence1ju9jr1w86plq as isCharSequence,
  trim11nh7r46at6sx as trim,
  toByte4i43936u611k as toByte,
  decodeToString1x4faah2liw2p as decodeToString,
  setOf45ia9pnfhe90 as setOf,
  charSequenceGet1vxk1y5n17t1z as charSequenceGet,
  Char__toInt_impl_vasixd28v1p2u6jau5g as Char__toInt_impl_vasixd,
} from './kotlin-kotlin-stdlib.mjs';
import {
  DisposableHandle1uzxt8frdchxn as DisposableHandle,
  CopyableThrowable1mvc99jcyvivf as CopyableThrowable,
  CancellableContinuationImpl1cx201opicavg as CancellableContinuationImpl,
  launch1c91vkjzdi9sd as launch,
  startCoroutineCancellable18shtfwdieib as startCoroutineCancellable,
  get_job2zvlvce9o9a29 as get_job,
  Job13y4jkazwjho0 as Job,
  cancel1xim2hrvjmwpn as cancel,
  Dispatchers_getInstance128lm494a5r5s as Dispatchers_getInstance,
} from './kotlinx-coroutines-core.mjs';
import {
  Buffergs925ekssbch as Buffer,
  indexOf29nspdgx2e3ap as indexOf,
  IOException1wyutdmfe71nu as IOException,
  EOFExceptionh831u25jcq9n as EOFException,
  readByteString2hnsbql6t4ads as readByteString,
  readStringo8i62qxt5m4m as readString,
  readString3v6duspiz33tv as readString_0,
  readString2nvggcfaijfhd as readString_1,
  readByteArray1fhzfwi2j014k as readByteArray,
  writeString33ca4btrgctw7 as writeString,
  readByteArray1ri21h2rciakw as readByteArray_0,
} from './kotlinx-io-kotlinx-io-core.mjs';
import { atomic$ref$130aurmcwdfdf1 as atomic$ref$1 } from './kotlinx-atomicfu.mjs';
import { decodeToString2ede220pr5xir as decodeToString_0 } from './kotlinx-io-kotlinx-io-bytestring.mjs';
//region block: imports
//endregion
//region block: pre-declaration
class Companion {
  constructor() {
    Companion_instance_0 = this;
    this.x24_1 = new Closed(null);
    var tmp = this;
    // Inline function 'kotlin.Companion.success' call
    tmp.y24_1 = _Result___init__impl__xyqfz8(Unit_instance);
  }
}
class Empty {
  toString() {
    return 'Empty';
  }
  hashCode() {
    return -231472095;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof Empty))
      return false;
    return true;
  }
}
class Closed {
  constructor(cause) {
    this.z24_1 = cause;
  }
  toString() {
    return 'Closed(cause=' + toString(this.z24_1) + ')';
  }
  hashCode() {
    return this.z24_1 == null ? 0 : hashCode(this.z24_1);
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof Closed))
      return false;
    if (!equals(this.z24_1, other.z24_1))
      return false;
    return true;
  }
}
class Task {}
function resume() {
  return this.b25().ac(Companion_getInstance().y24_1);
}
function resume_0(throwable) {
  var tmp = this.b25();
  var tmp_0;
  if (throwable == null) {
    tmp_0 = null;
  } else {
    // Inline function 'kotlin.let' call
    // Inline function 'kotlin.Companion.failure' call
    var tmp$ret$0 = _Result___init__impl__xyqfz8(createFailure(throwable));
    tmp_0 = new Result(tmp$ret$0);
  }
  var tmp1_elvis_lhs = tmp_0;
  return tmp.ac(tmp1_elvis_lhs == null ? Companion_getInstance().y24_1 : tmp1_elvis_lhs.fr_1);
}
class Read {
  constructor(continuation) {
    this.f25_1 = continuation;
    this.g25_1 = null;
    if (get_DEVELOPMENT_MODE()) {
      var tmp = this;
      // Inline function 'kotlin.also' call
      var this_0 = newThrowable('ReadTask 0x' + toString_0(hashCode(this.f25_1), 16));
      stackTraceToString(this_0);
      tmp.g25_1 = this_0;
    }
  }
  b25() {
    return this.f25_1;
  }
  a25() {
    return this.g25_1;
  }
  c25() {
    return 'read';
  }
}
class Write {
  constructor(continuation) {
    this.h25_1 = continuation;
    this.i25_1 = null;
    if (get_DEVELOPMENT_MODE()) {
      var tmp = this;
      // Inline function 'kotlin.also' call
      var this_0 = newThrowable('WriteTask 0x' + toString_0(hashCode(this.h25_1), 16));
      stackTraceToString(this_0);
      tmp.i25_1 = this_0;
    }
  }
  b25() {
    return this.h25_1;
  }
  a25() {
    return this.i25_1;
  }
  c25() {
    return 'write';
  }
}
class sam$kotlinx_coroutines_DisposableHandle$0 {
  constructor(function_0) {
    this.t25_1 = function_0;
  }
  vw() {
    return this.t25_1();
  }
  r4() {
    return this.t25_1;
  }
  equals(other) {
    var tmp;
    if (!(other == null) ? isInterface(other, DisposableHandle) : false) {
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
class ByteReadChannel {}
function awaitContent$default(min, $completion, $super) {
  min = min === VOID ? 1 : min;
  return $super === VOID ? this.q26(min, $completion) : $super.q26.call(this, min, $completion);
}
class ByteWriteChannel {}
function get_autoFlush() {
  return false;
}
class ByteChannel {
  constructor(autoFlush) {
    autoFlush = autoFlush === VOID ? false : autoFlush;
    this.j25_1 = autoFlush;
    this.k25_1 = new Buffer();
    this.l25_1 = 0;
    this.m25_1 = new Object();
    this.n25_1 = atomic$ref$1(Empty_instance);
    this.o25_1 = new Buffer();
    this.p25_1 = new Buffer();
    this.q25_1 = atomic$ref$1(null);
    this.r25_1 = atomic$ref$1(null);
  }
  u25() {
    return this.j25_1;
  }
  v25() {
    return this.l25_1 < 1048576;
  }
  w25() {
    var tmp0_safe_receiver = this.q25_1.kotlinx$atomicfu$value;
    if (tmp0_safe_receiver == null)
      null;
    else {
      // Inline function 'io.ktor.utils.io.Companion.throwOrNull' call
      // Inline function 'io.ktor.utils.io.Companion.wrapCause' call
      var tmp0_subject = access$_get_origin__hdh1qz(tmp0_safe_receiver);
      var tmp;
      if (tmp0_subject == null) {
        tmp = null;
      } else {
        if (isInterface(tmp0_subject, CopyableThrowable)) {
          tmp = access$_get_origin__hdh1qz(tmp0_safe_receiver).i10();
        } else {
          if (tmp0_subject instanceof CancellationException) {
            tmp = CancellationException.p(access$_get_origin__hdh1qz(tmp0_safe_receiver).message, access$_get_origin__hdh1qz(tmp0_safe_receiver));
          } else {
            var p0 = access$_get_origin__hdh1qz(tmp0_safe_receiver);
            tmp = ClosedReadChannelException.b26(p0);
          }
        }
      }
      var tmp0_safe_receiver_0 = tmp;
      var tmp_0;
      if (tmp0_safe_receiver_0 == null) {
        tmp_0 = null;
      } else {
        // Inline function 'kotlin.let' call
        throw tmp0_safe_receiver_0;
      }
    }
    if (this.o25_1.r21()) {
      moveFlushToReadBuffer(this);
    }
    return this.o25_1;
  }
  c26() {
    if (this.i26()) {
      var tmp0_safe_receiver = this.q25_1.kotlinx$atomicfu$value;
      var tmp;
      if (tmp0_safe_receiver == null) {
        tmp = null;
      } else {
        // Inline function 'io.ktor.utils.io.Companion.throwOrNull' call
        // Inline function 'io.ktor.utils.io.Companion.wrapCause' call
        var tmp0_subject = access$_get_origin__hdh1qz(tmp0_safe_receiver);
        var tmp_0;
        if (tmp0_subject == null) {
          tmp_0 = null;
        } else {
          if (isInterface(tmp0_subject, CopyableThrowable)) {
            tmp_0 = access$_get_origin__hdh1qz(tmp0_safe_receiver).i10();
          } else {
            if (tmp0_subject instanceof CancellationException) {
              tmp_0 = CancellationException.p(access$_get_origin__hdh1qz(tmp0_safe_receiver).message, access$_get_origin__hdh1qz(tmp0_safe_receiver));
            } else {
              var p0 = access$_get_origin__hdh1qz(tmp0_safe_receiver);
              tmp_0 = ClosedWriteChannelException.h26(p0);
            }
          }
        }
        var tmp0_safe_receiver_0 = tmp_0;
        var tmp_1;
        if (tmp0_safe_receiver_0 == null) {
          tmp_1 = null;
        } else {
          // Inline function 'kotlin.let' call
          throw tmp0_safe_receiver_0;
        }
        tmp = tmp_1;
      }
      if (tmp == null)
        throw ClosedWriteChannelException.h26();
    }
    return this.p25_1;
  }
  s25() {
    var tmp0_safe_receiver = this.q25_1.kotlinx$atomicfu$value;
    var tmp;
    if (tmp0_safe_receiver == null) {
      tmp = null;
    } else {
      // Inline function 'io.ktor.utils.io.Companion.wrapCause' call
      var tmp0_subject = access$_get_origin__hdh1qz(tmp0_safe_receiver);
      var tmp_0;
      if (tmp0_subject == null) {
        tmp_0 = null;
      } else {
        if (isInterface(tmp0_subject, CopyableThrowable)) {
          tmp_0 = access$_get_origin__hdh1qz(tmp0_safe_receiver).i10();
        } else {
          if (tmp0_subject instanceof CancellationException) {
            tmp_0 = CancellationException.p(access$_get_origin__hdh1qz(tmp0_safe_receiver).message, access$_get_origin__hdh1qz(tmp0_safe_receiver));
          } else {
            var p0 = access$_get_origin__hdh1qz(tmp0_safe_receiver);
            tmp_0 = ClosedByteChannelException.m26(p0);
          }
        }
      }
      tmp = tmp_0;
    }
    return tmp;
  }
  i26() {
    return !(this.q25_1.kotlinx$atomicfu$value == null);
  }
  n26() {
    var tmp;
    var tmp0_safe_receiver = this.q25_1.kotlinx$atomicfu$value;
    if ((tmp0_safe_receiver == null ? null : tmp0_safe_receiver.p26()) === true) {
      tmp = true;
    } else {
      tmp = (this.i26() && this.l25_1 === 0 && this.o25_1.r21());
    }
    return tmp;
  }
  *q26(min, $completion) {
    rethrowCloseCauseIfNeeded(this);
    if (this.o25_1.j2() >= fromInt(min))
      return true;
    // Inline function 'io.ktor.utils.io.ByteChannel.sleepWhile' call
    var this_0 = this;
    $l$loop: while (add(numberToLong(this.l25_1), this.o25_1.j2()) < fromInt(min) && this.q25_1.kotlinx$atomicfu$value == null) {
      // Inline function 'kotlinx.coroutines.suspendCancellableCoroutine' call
      // Inline function 'kotlin.js.suspendCoroutineUninterceptedOrReturnJS' call
      (yield () => {
        var uCont = $completion;
        var cancellable = new CancellableContinuationImpl(intercepted(uCont), 1);
        cancellable.cy();
        var tmp0 = this_0;
        var tmp2 = new Read(cancellable);
        $l$block_0: {
          // Inline function 'io.ktor.utils.io.ByteChannel.trySuspend' call
          var previous = tmp0.n25_1.kotlinx$atomicfu$value;
          if (!(previous instanceof Closed)) {
            if (!tmp0.n25_1.atomicfu$compareAndSet(previous, tmp2)) {
              tmp2.d25();
              break $l$block_0;
            }
          }
          if (previous instanceof Read) {
            previous.e25(ConcurrentIOException.v26(tmp2.c25(), previous.a25()));
          } else {
            if (isInterface(previous, Task)) {
              previous.d25();
            } else {
              if (previous instanceof Closed) {
                tmp2.e25(previous.z24_1);
                break $l$block_0;
              } else {
                if (!equals(previous, Empty_instance)) {
                  noWhenBranchMatchedException();
                }
              }
            }
          }
          if (!(add(numberToLong(this.l25_1), this.o25_1.j2()) < fromInt(min) && this.q25_1.kotlinx$atomicfu$value == null)) {
            // Inline function 'io.ktor.utils.io.ByteChannel.resumeSlot' call
            var current = tmp0.n25_1.kotlinx$atomicfu$value;
            var tmp;
            if (current instanceof Read) {
              tmp = tmp0.n25_1.atomicfu$compareAndSet(current, Empty_instance);
            } else {
              tmp = false;
            }
            if (tmp) {
              current.d25();
            }
          }
        }
        return cancellable.ky();
      });
    }
    rethrowCloseCauseIfNeeded(this);
    if (this.o25_1.j2() < 1048576n) {
      moveFlushToReadBuffer(this);
    }
    return this.o25_1.j2() >= fromInt(min);
  }
  *x26($completion) {
    rethrowCloseCauseIfNeeded(this);
    this.y26();
    if (this.v25())
      return Unit_instance;
    // Inline function 'io.ktor.utils.io.ByteChannel.sleepWhile' call
    var this_0 = this;
    $l$loop: while (!this.v25() && this.q25_1.kotlinx$atomicfu$value == null) {
      // Inline function 'kotlinx.coroutines.suspendCancellableCoroutine' call
      // Inline function 'kotlin.js.suspendCoroutineUninterceptedOrReturnJS' call
      (yield () => {
        var uCont = $completion;
        var cancellable = new CancellableContinuationImpl(intercepted(uCont), 1);
        cancellable.cy();
        var tmp0 = this_0;
        var tmp2 = new Write(cancellable);
        $l$block_0: {
          // Inline function 'io.ktor.utils.io.ByteChannel.trySuspend' call
          var previous = tmp0.n25_1.kotlinx$atomicfu$value;
          if (!(previous instanceof Closed)) {
            if (!tmp0.n25_1.atomicfu$compareAndSet(previous, tmp2)) {
              tmp2.d25();
              break $l$block_0;
            }
          }
          if (previous instanceof Write) {
            previous.e25(ConcurrentIOException.v26(tmp2.c25(), previous.a25()));
          } else {
            if (isInterface(previous, Task)) {
              previous.d25();
            } else {
              if (previous instanceof Closed) {
                tmp2.e25(previous.z24_1);
                break $l$block_0;
              } else {
                if (!equals(previous, Empty_instance)) {
                  noWhenBranchMatchedException();
                }
              }
            }
          }
          if (!(!this.v25() && this.q25_1.kotlinx$atomicfu$value == null)) {
            // Inline function 'io.ktor.utils.io.ByteChannel.resumeSlot' call
            var current = tmp0.n25_1.kotlinx$atomicfu$value;
            var tmp;
            if (current instanceof Write) {
              tmp = tmp0.n25_1.atomicfu$compareAndSet(current, Empty_instance);
            } else {
              tmp = false;
            }
            if (tmp) {
              current.d25();
            }
          }
        }
        return cancellable.ky();
      });
    }
    return Unit_instance;
  }
  y26() {
    if (this.p25_1.r21())
      return Unit_instance;
    // Inline function 'io.ktor.utils.io.locks.synchronized' call
    this.m25_1;
    var count = convertToInt(this.p25_1.j2());
    this.k25_1.j23(this.p25_1);
    this.l25_1 = this.l25_1 + count | 0;
    // Inline function 'io.ktor.utils.io.ByteChannel.resumeSlot' call
    var current = this.n25_1.kotlinx$atomicfu$value;
    var tmp;
    if (current instanceof Read) {
      tmp = this.n25_1.atomicfu$compareAndSet(current, Empty_instance);
    } else {
      tmp = false;
    }
    if (tmp) {
      current.d25();
    }
  }
  g6() {
    this.y26();
    if (!this.q25_1.atomicfu$compareAndSet(null, get_CLOSED()))
      return Unit_instance;
    closeSlot(this, null);
  }
  *z26($completion) {
    // Inline function 'kotlin.runCatching' call
    var tmp;
    try {
      yield* this.x26($completion);
      // Inline function 'kotlin.Companion.success' call
      tmp = _Result___init__impl__xyqfz8(Unit_instance);
    } catch ($p) {
      var tmp_0;
      if ($p instanceof Error) {
        var e = $p;
        // Inline function 'kotlin.Companion.failure' call
        tmp_0 = _Result___init__impl__xyqfz8(createFailure(e));
      } else {
        throw $p;
      }
      tmp = tmp_0;
    }
    if (!this.q25_1.atomicfu$compareAndSet(null, get_CLOSED()))
      return Unit_instance;
    closeSlot(this, null);
    return Unit_instance;
  }
  a27(cause) {
    if (!(this.q25_1.kotlinx$atomicfu$value == null))
      return Unit_instance;
    var closedToken = new CloseToken(cause);
    this.q25_1.atomicfu$compareAndSet(null, closedToken);
    // Inline function 'io.ktor.utils.io.Companion.wrapCause' call
    var tmp0_subject = access$_get_origin__hdh1qz(closedToken);
    var tmp;
    if (tmp0_subject == null) {
      tmp = null;
    } else {
      if (isInterface(tmp0_subject, CopyableThrowable)) {
        tmp = access$_get_origin__hdh1qz(closedToken).i10();
      } else {
        if (tmp0_subject instanceof CancellationException) {
          tmp = CancellationException.p(access$_get_origin__hdh1qz(closedToken).message, access$_get_origin__hdh1qz(closedToken));
        } else {
          var p0 = access$_get_origin__hdh1qz(closedToken);
          tmp = ClosedByteChannelException.m26(p0);
        }
      }
    }
    var wrappedCause = tmp;
    closeSlot(this, wrappedCause);
  }
  b27(handler) {
    var existingCause = this.q25_1.kotlinx$atomicfu$value;
    if (!(existingCause == null)) {
      // Inline function 'io.ktor.utils.io.Companion.wrapCause' call
      var tmp0_subject = access$_get_origin__hdh1qz(existingCause);
      var tmp;
      if (tmp0_subject == null) {
        tmp = null;
      } else {
        if (isInterface(tmp0_subject, CopyableThrowable)) {
          tmp = access$_get_origin__hdh1qz(existingCause).i10();
        } else {
          if (tmp0_subject instanceof CancellationException) {
            tmp = CancellationException.p(access$_get_origin__hdh1qz(existingCause).message, access$_get_origin__hdh1qz(existingCause));
          } else {
            var p0 = access$_get_origin__hdh1qz(existingCause);
            tmp = ClosedByteChannelException.m26(p0);
          }
        }
      }
      handler(tmp);
      var tmp_0 = ByteChannel$invokeOnClose$lambda;
      return new sam$kotlinx_coroutines_DisposableHandle$0(tmp_0);
    }
    if (!this.r25_1.atomicfu$compareAndSet(null, handler)) {
      // Inline function 'kotlin.error' call
      var message = 'Only one invokeOnClose handler is supported per channel';
      throw IllegalStateException.o(toString_1(message));
    }
    var cause = this.q25_1.kotlinx$atomicfu$value;
    if (!(cause == null) && this.r25_1.atomicfu$compareAndSet(handler, null)) {
      // Inline function 'io.ktor.utils.io.Companion.wrapCause' call
      var tmp0_subject_0 = access$_get_origin__hdh1qz(cause);
      var tmp_1;
      if (tmp0_subject_0 == null) {
        tmp_1 = null;
      } else {
        if (isInterface(tmp0_subject_0, CopyableThrowable)) {
          tmp_1 = access$_get_origin__hdh1qz(cause).i10();
        } else {
          if (tmp0_subject_0 instanceof CancellationException) {
            tmp_1 = CancellationException.p(access$_get_origin__hdh1qz(cause).message, access$_get_origin__hdh1qz(cause));
          } else {
            var p0_0 = access$_get_origin__hdh1qz(cause);
            tmp_1 = ClosedByteChannelException.m26(p0_0);
          }
        }
      }
      handler(tmp_1);
      var tmp_2 = ByteChannel$invokeOnClose$lambda_0;
      return new sam$kotlinx_coroutines_DisposableHandle$0(tmp_2);
    }
    var tmp_3 = ByteChannel$invokeOnClose$lambda_1(this, handler);
    return new sam$kotlinx_coroutines_DisposableHandle$0(tmp_3);
  }
  toString() {
    return 'ByteChannel[' + hashCode(this) + ']';
  }
}
class ConcurrentIOException extends IllegalStateException {
  constructor(taskName, cause) {
    return new.target.v26(taskName, cause);
  }
  static v26(taskName, cause) {
    cause = cause === VOID ? null : cause;
    var $this = this.q('Concurrent ' + taskName + ' attempts', cause);
    captureStack($this, $this.u26_1);
    return $this;
  }
}
class ByteChannelScanner {
  constructor(channel, matchString, writeChannel, limit) {
    limit = limit === VOID ? 9223372036854775807n : limit;
    this.c27_1 = channel;
    this.d27_1 = matchString;
    this.e27_1 = writeChannel;
    this.f27_1 = limit;
    // Inline function 'kotlin.require' call
    if (!(this.d27_1.j2() > 0)) {
      var message = 'Empty match string not permitted for scanning';
      throw IllegalArgumentException.i2(toString_1(message));
    }
    this.g27_1 = this.c27_1.w25();
    this.h27_1 = buildPartialMatchTable(this);
    this.i27_1 = new Buffer();
    this.j27_1 = 0n;
    this.k27_1 = 0;
  }
  *l27(ignoreMissing, $completion) {
    this.j27_1 = 0n;
    while (!this.g27_1.r21() || (yield* this.c27_1.w26(VOID, $completion))) {
      yield* /*#__NOINLINE__*/advanceToNextPotentialMatch(this, $completion);
      if (yield* /*#__NOINLINE__*/checkFullMatch(this, $completion)) {
        return this.j27_1;
      }
    }
    if (!ignoreMissing) {
      throw IOException.u24('Expected "' + toSingleLineString(this, this.d27_1) + '" but encountered end of input');
    }
    this.j27_1 = add(this.j27_1, this.i27_1.u22(this.e27_1.c26()));
    yield* this.e27_1.x26($completion);
    return this.j27_1;
  }
}
class ByteReadChannel$Companion$Empty$1 {
  constructor() {
    this.o27_1 = null;
    this.p27_1 = new Buffer();
  }
  s25() {
    return this.o27_1;
  }
  n26() {
    return true;
  }
  w25() {
    return this.p27_1;
  }
  *q26(min, $completion) {
    return false;
  }
  a27(cause) {
  }
}
class Companion_0 {
  constructor() {
    Companion_instance_1 = this;
    var tmp = this;
    tmp.q27_1 = new ByteReadChannel$Companion$Empty$1();
  }
}
class WriterJob {
  constructor(channel, job) {
    this.m27_1 = channel;
    this.n27_1 = job;
  }
  lz() {
    return this.n27_1;
  }
}
class WriterScope {
  constructor(channel, coroutineContext) {
    this.w27_1 = channel;
    this.x27_1 = coroutineContext;
  }
  st() {
    return this.x27_1;
  }
}
class NO_CALLBACK$1 {
  constructor() {
    this.y27_1 = EmptyCoroutineContext_instance;
  }
  yb() {
    return this.y27_1;
  }
  zb(result) {
    return Unit_instance;
  }
  ac(result) {
    return this.zb(result);
  }
}
class Companion_1 {}
class CloseToken {
  constructor(origin) {
    this.o26_1 = origin;
  }
  p26() {
    return !(this.o26_1 == null);
  }
}
class CountedByteReadChannel {
  constructor(delegate) {
    this.z27_1 = delegate;
    this.a28_1 = new Buffer();
    this.b28_1 = 0n;
    this.c28_1 = 0n;
  }
  d28() {
    updateConsumed(this);
    return this.c28_1;
  }
  s25() {
    return this.z27_1.s25();
  }
  n26() {
    return this.a28_1.r21() && this.z27_1.n26();
  }
  w25() {
    transferFromDelegate(this);
    return this.a28_1;
  }
  *q26(min, $completion) {
    if (this.w25().j2() >= fromInt(min)) {
      return true;
    }
    if (yield* this.z27_1.q26(min, $completion)) {
      transferFromDelegate(this);
      return true;
    }
    return false;
  }
  a27(cause) {
    this.z27_1.a27(cause);
    this.a28_1.g6();
  }
}
class ClosedByteChannelException extends IOException {
  constructor(cause) {
    return new.target.m26(cause);
  }
  static m26(cause) {
    cause = cause === VOID ? null : cause;
    var $this = this.v24(cause == null ? null : cause.message, cause);
    captureStack($this, $this.l26_1);
    return $this;
  }
}
class ClosedReadChannelException extends ClosedByteChannelException {
  constructor(cause) {
    return new.target.b26(cause);
  }
  static b26(cause) {
    cause = cause === VOID ? null : cause;
    var $this = this.m26(cause);
    captureStack($this, $this.a26_1);
    return $this;
  }
}
class ClosedWriteChannelException extends ClosedByteChannelException {
  constructor(cause) {
    return new.target.h26(cause);
  }
  static h26(cause) {
    cause = cause === VOID ? null : cause;
    var $this = this.m26(cause);
    captureStack($this, $this.g26_1);
    return $this;
  }
}
class LineEnding extends Enum {}
class Companion_2 {
  constructor() {
    Companion_instance_3 = this;
    this.e28_1 = _LineEndingMode___init__impl__jo5bul(1);
    this.f28_1 = _LineEndingMode___init__impl__jo5bul(2);
    this.g28_1 = _LineEndingMode___init__impl__jo5bul(4);
    this.h28_1 = _LineEndingMode___init__impl__jo5bul(7);
    this.i28_1 = listOf([new LineEndingMode(this.e28_1), new LineEndingMode(this.f28_1), new LineEndingMode(this.g28_1)]);
  }
}
class LineEndingMode {
  constructor(mode) {
    Companion_getInstance_2();
    this.j28_1 = mode;
  }
  toString() {
    return LineEndingMode__toString_impl_j4h76r(this.j28_1);
  }
  hashCode() {
    return LineEndingMode__hashCode_impl_2mopm4(this.j28_1);
  }
  equals(other) {
    return LineEndingMode__equals_impl_qyr4nk(this.j28_1, other);
  }
}
class SourceByteReadChannel {
  constructor(source) {
    this.k28_1 = source;
    this.l28_1 = null;
  }
  s25() {
    var tmp0_safe_receiver = this.l28_1;
    var tmp;
    if (tmp0_safe_receiver == null) {
      tmp = null;
    } else {
      // Inline function 'io.ktor.utils.io.Companion.wrapCause' call
      var tmp0_subject = access$_get_origin__hdh1qz(tmp0_safe_receiver);
      var tmp_0;
      if (tmp0_subject == null) {
        tmp_0 = null;
      } else {
        if (isInterface(tmp0_subject, CopyableThrowable)) {
          tmp_0 = access$_get_origin__hdh1qz(tmp0_safe_receiver).i10();
        } else {
          if (tmp0_subject instanceof CancellationException) {
            tmp_0 = CancellationException.p(access$_get_origin__hdh1qz(tmp0_safe_receiver).message, access$_get_origin__hdh1qz(tmp0_safe_receiver));
          } else {
            var p0 = access$_get_origin__hdh1qz(tmp0_safe_receiver);
            tmp_0 = ClosedByteChannelException.m26(p0);
          }
        }
      }
      tmp = tmp_0;
    }
    return tmp;
  }
  n26() {
    return this.k28_1.r21();
  }
  w25() {
    var tmp0_safe_receiver = this.s25();
    if (tmp0_safe_receiver == null)
      null;
    else {
      // Inline function 'kotlin.let' call
      throw tmp0_safe_receiver;
    }
    return this.k28_1.q21();
  }
  *q26(min, $completion) {
    var tmp0_safe_receiver = this.s25();
    if (tmp0_safe_receiver == null)
      null;
    else {
      // Inline function 'kotlin.let' call
      throw tmp0_safe_receiver;
    }
    return this.k28_1.t21(fromInt(min));
  }
  a27(cause) {
    if (!(this.l28_1 == null))
      return Unit_instance;
    this.k28_1.g6();
    var tmp = this;
    var tmp1_elvis_lhs = cause == null ? null : cause.message;
    tmp.l28_1 = new CloseToken(IOException.v24(tmp1_elvis_lhs == null ? 'Channel was cancelled' : tmp1_elvis_lhs, cause));
  }
}
class MalformedInputException extends IOException {
  constructor(message) {
    return new.target.p28(message);
  }
  static p28(message) {
    var $this = this.u24(message);
    captureStack($this, $this.o28_1);
    return $this;
  }
}
class TooLongLineException extends MalformedInputException {
  constructor(message) {
    return new.target.v27(message);
  }
  static v27(message) {
    var $this = this.p28(message);
    captureStack($this, $this.u27_1);
    return $this;
  }
}
class ObjectPool {}
function close() {
  this.vw();
}
class DefaultPool {
  constructor(capacity) {
    this.y28_1 = capacity;
    var tmp = this;
    // Inline function 'kotlin.arrayOfNulls' call
    var size = this.y28_1;
    tmp.z28_1 = Array(size);
    this.a29_1 = 0;
  }
  b29(instance) {
  }
  c29(instance) {
    return instance;
  }
  d29(instance) {
  }
  e29() {
    if (this.a29_1 === 0)
      return this.x28();
    this.a29_1 = this.a29_1 - 1 | 0;
    var idx = this.a29_1;
    var tmp = this.z28_1[idx];
    var instance = !(tmp == null) ? tmp : THROW_CCE();
    this.z28_1[idx] = null;
    return this.c29(instance);
  }
  f29(instance) {
    this.d29(instance);
    if (this.a29_1 === this.y28_1) {
      this.b29(instance);
    } else {
      var _unary__edvuaz = this.a29_1;
      this.a29_1 = _unary__edvuaz + 1 | 0;
      this.z28_1[_unary__edvuaz] = instance;
    }
  }
  vw() {
    var inductionVariable = 0;
    var last = this.a29_1;
    if (inductionVariable < last)
      do {
        var i = inductionVariable;
        inductionVariable = inductionVariable + 1 | 0;
        var tmp = this.z28_1[i];
        var instance = !(tmp == null) ? tmp : THROW_CCE();
        this.z28_1[i] = null;
        this.b29(instance);
      }
       while (inductionVariable < last);
    this.a29_1 = 0;
  }
}
class ByteArrayPool$1 extends DefaultPool {
  constructor() {
    super(128);
  }
  x28() {
    return new Int8Array(4096);
  }
}
class NoPoolImpl {
  f29(instance) {
    return Unit_instance;
  }
  vw() {
    return Unit_instance;
  }
}
class Companion_3 {
  g29(name) {
    switch (name) {
      case 'UTF-8':
      case 'utf-8':
      case 'UTF8':
      case 'utf8':
        return Charsets_getInstance().q28_1;
    }
    var tmp;
    var tmp_0;
    switch (name) {
      case 'ISO-8859-1':
      case 'iso-8859-1':
        tmp_0 = true;
        break;
      default:
        // Inline function 'kotlin.let' call

        var it = replace_0(name, _Char___init__impl__6a9atx(95), _Char___init__impl__6a9atx(45));
        var tmp_1;
        if (it === 'iso-8859-1') {
          tmp_1 = true;
        } else {
          // Inline function 'kotlin.text.lowercase' call
          // Inline function 'kotlin.js.asDynamic' call
          tmp_1 = it.toLowerCase() === 'iso-8859-1';
        }

        tmp_0 = tmp_1;
        break;
    }
    if (tmp_0) {
      tmp = true;
    } else {
      tmp = name === 'latin1' || name === 'Latin1';
    }
    if (tmp) {
      return Charsets_getInstance().r28_1;
    }
    throw IllegalArgumentException.i2('Charset ' + name + ' is not supported');
  }
}
class Charset {
  constructor(_name) {
    this.s28_1 = _name;
  }
  equals(other) {
    if (this === other)
      return true;
    if (other == null || !(this.constructor == other.constructor))
      return false;
    if (!(other instanceof Charset))
      THROW_CCE();
    return this.s28_1 === other.s28_1;
  }
  hashCode() {
    return getStringHashCode(this.s28_1);
  }
  toString() {
    return this.s28_1;
  }
}
class Charsets {
  constructor() {
    Charsets_instance = this;
    this.q28_1 = new CharsetImpl('UTF-8');
    this.r28_1 = new CharsetImpl('ISO-8859-1');
  }
}
class CharsetDecoder {
  constructor(_charset) {
    this.i29_1 = _charset;
  }
}
class CharsetEncoder {
  constructor(_charset) {
    this.j29_1 = _charset;
  }
}
class CharsetImpl extends Charset {
  t28() {
    return new CharsetEncoderImpl(this);
  }
  h29() {
    return new CharsetDecoderImpl(this);
  }
}
class CharsetEncoderImpl extends CharsetEncoder {
  constructor(charset) {
    super(charset);
    this.n29_1 = charset;
  }
  toString() {
    return 'CharsetEncoderImpl(charset=' + this.n29_1.toString() + ')';
  }
  hashCode() {
    return this.n29_1.hashCode();
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof CharsetEncoderImpl))
      return false;
    if (!this.n29_1.equals(other.n29_1))
      return false;
    return true;
  }
}
class CharsetDecoderImpl extends CharsetDecoder {
  constructor(charset) {
    super(charset);
    this.p29_1 = charset;
  }
  toString() {
    return 'CharsetDecoderImpl(charset=' + this.p29_1.toString() + ')';
  }
  hashCode() {
    return this.p29_1.hashCode();
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof CharsetDecoderImpl))
      return false;
    if (!this.p29_1.equals(other.p29_1))
      return false;
    return true;
  }
}
class toKtor$1 {
  constructor($this_toKtor) {
    this.q29_1 = $this_toKtor;
  }
  k29(buffer) {
    return this.q29_1.decode(buffer);
  }
}
class TextDecoderFallback {
  constructor(encoding, fatal) {
    this.r29_1 = fatal;
    // Inline function 'kotlin.text.trim' call
    // Inline function 'kotlin.text.lowercase' call
    // Inline function 'kotlin.js.asDynamic' call
    var requestedEncoding = toString_1(trim(isCharSequence(encoding) ? encoding : THROW_CCE())).toLowerCase();
    // Inline function 'kotlin.check' call
    if (!get_ENCODING_ALIASES().r3(requestedEncoding)) {
      var message = encoding + ' is not supported.';
      throw IllegalStateException.o(toString_1(message));
    }
  }
  k29(buffer) {
    // Inline function 'io.ktor.utils.io.core.buildPacket' call
    var builder = new Buffer();
    var bytes = buffer instanceof Int8Array ? buffer : THROW_CCE();
    var inductionVariable = 0;
    var last = bytes.length;
    if (inductionVariable < last)
      $l$loop: do {
        var index = inductionVariable;
        inductionVariable = inductionVariable + 1 | 0;
        // Inline function 'org.khronos.webgl.get' call
        // Inline function 'kotlin.js.asDynamic' call
        var byte = bytes[index];
        var point = toCodePoint(byte);
        if (point < 0) {
          // Inline function 'kotlin.check' call
          if (!!this.r29_1) {
            var message = 'Invalid character: ' + point;
            throw IllegalStateException.o(toString_1(message));
          }
          writeFully_0(builder, get_REPLACEMENT());
          continue $l$loop;
        }
        if (point > 255) {
          builder.k23(toByte(point >> 8));
        }
        builder.k23(toByte(point & 255));
      }
       while (inductionVariable < last);
    return decodeToString(readByteArray_0(builder));
  }
}
//endregion
var Companion_instance_0;
function Companion_getInstance() {
  if (Companion_instance_0 === VOID)
    new Companion();
  return Companion_instance_0;
}
var Empty_instance;
function Empty_getInstance() {
  return Empty_instance;
}
function rethrowCloseCauseIfNeeded($this) {
  var tmp0_safe_receiver = $this.s25();
  if (tmp0_safe_receiver == null)
    null;
  else {
    // Inline function 'kotlin.let' call
    throw tmp0_safe_receiver;
  }
}
function moveFlushToReadBuffer($this) {
  // Inline function 'io.ktor.utils.io.locks.synchronized' call
  $this.m25_1;
  $this.k25_1.u22($this.o25_1);
  $this.l25_1 = 0;
  // Inline function 'io.ktor.utils.io.ByteChannel.resumeSlot' call
  var current = $this.n25_1.kotlinx$atomicfu$value;
  var tmp;
  if (current instanceof Write) {
    tmp = $this.n25_1.atomicfu$compareAndSet(current, Empty_instance);
  } else {
    tmp = false;
  }
  if (tmp) {
    current.d25();
  }
}
function closeSlot($this, cause) {
  var closeContinuation = !(cause == null) ? new Closed(cause) : Companion_getInstance().x24_1;
  var continuation = $this.n25_1.atomicfu$getAndSet(closeContinuation);
  if (isInterface(continuation, Task)) {
    continuation.e25(cause);
  }
  var tmp0_safe_receiver = $this.r25_1.atomicfu$getAndSet(null);
  if (tmp0_safe_receiver == null)
    null;
  else
    tmp0_safe_receiver(cause);
}
function ByteChannel$invokeOnClose$lambda() {
  return Unit_instance;
}
function ByteChannel$invokeOnClose$lambda_0() {
  return Unit_instance;
}
function ByteChannel$invokeOnClose$lambda_1(this$0, $handler) {
  return () => {
    this$0.r25_1.atomicfu$compareAndSet($handler, null);
    return Unit_instance;
  };
}
function ByteReadChannel_0(content, offset, length) {
  offset = offset === VOID ? 0 : offset;
  length = length === VOID ? content.length : length;
  // Inline function 'kotlin.also' call
  var this_0 = new Buffer();
  this_0.a23(content, offset, offset + length | 0);
  var source = this_0;
  return ByteReadChannel_1(source);
}
function ByteReadChannel_1(source) {
  return new SourceByteReadChannel(source);
}
function buildPartialMatchTable($this) {
  var table = new Int32Array($this.d27_1.j2());
  var j = 0;
  var inductionVariable = 1;
  var last = $this.d27_1.j2();
  if (inductionVariable < last)
    do {
      var i = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      while (j > 0 && !($this.d27_1.l2(i) === $this.d27_1.l2(j))) {
        j = table[j - 1 | 0];
      }
      if ($this.d27_1.l2(i) === $this.d27_1.l2(j)) {
        j = j + 1 | 0;
      }
      table[i] = j;
    }
     while (inductionVariable < last);
  return table;
}
function *advanceToNextPotentialMatch($this, $completion) {
  while (!$this.g27_1.r21() || (yield* $this.c27_1.w26(VOID, $completion))) {
    var nextMatch = indexOf($this.g27_1, $this.d27_1.l2(0));
    if (nextMatch === -1n) {
      var tmp = $this.g27_1;
      checkBounds($this, (tmp instanceof Buffer ? tmp : THROW_CCE()).j2());
      $this.j27_1 = add($this.j27_1, $this.g27_1.u22($this.e27_1.c26()));
      yield* flushIfNeeded($this.e27_1, $completion);
    } else {
      checkBounds($this, nextMatch);
      var tmp_0 = $this;
      var tmp_1 = $this.j27_1;
      var tmp_2 = $this.e27_1.c26();
      tmp_0.j27_1 = add(tmp_1, $this.g27_1.r22(tmp_2 instanceof Buffer ? tmp_2 : THROW_CCE(), nextMatch));
      yield* flushIfNeeded($this.e27_1, $completion);
      return Unit_instance;
    }
  }
  return Unit_instance;
}
function *checkFullMatch($this, $completion) {
  while (!$this.g27_1.r21() || (yield* $this.c27_1.w26(VOID, $completion))) {
    var byte = $this.g27_1.u21();
    if ($this.k27_1 > 0 && !(byte === $this.d27_1.l2($this.k27_1))) {
      var oldMatchIndex = $this.k27_1;
      while ($this.k27_1 > 0 && !(byte === $this.d27_1.l2($this.k27_1))) {
        $this.k27_1 = $this.h27_1[$this.k27_1 - 1 | 0];
      }
      var retained = fromInt(oldMatchIndex - $this.k27_1 | 0);
      checkBounds($this, retained);
      var tmp = $this;
      var tmp_0 = $this.j27_1;
      var tmp_1 = $this.e27_1.c26();
      tmp.j27_1 = add(tmp_0, $this.i27_1.r22(tmp_1 instanceof Buffer ? tmp_1 : THROW_CCE(), retained));
      if ($this.k27_1 === 0 && !(byte === $this.d27_1.l2($this.k27_1))) {
        yield* writeByte($this.e27_1, byte, $completion);
        var _unary__edvuaz = $this.j27_1;
        $this.j27_1 = add(_unary__edvuaz, get_ONE());
        return false;
      }
    }
    $this.k27_1 = $this.k27_1 + 1 | 0;
    if ($this.k27_1 === $this.d27_1.j2()) {
      return true;
    }
    $this.i27_1.k23(byte);
  }
  return false;
}
function checkBounds($this, extra) {
  if (add($this.j27_1, extra) > $this.f27_1) {
    throw IOException.u24('Limit of ' + $this.f27_1.toString() + ' bytes exceeded ' + ('while searching for "' + toSingleLineString($this, $this.d27_1) + '"'));
  }
}
function toSingleLineString($this, $receiver) {
  return replace(decodeToString_0($receiver), '\n', '\\n');
}
function attachJob(_this__u8e3s4, job) {
  job.xu(true, VOID, attachJob$lambda(_this__u8e3s4));
}
function attachWriterJob(_this__u8e3s4, writerJob) {
  var tmp = writerJob.m27_1;
  var tmp0_safe_receiver = tmp instanceof ByteChannel ? tmp : null;
  if (tmp0_safe_receiver == null)
    null;
  else {
    tmp0_safe_receiver.b27(attachWriterJob$lambda(_this__u8e3s4));
  }
}
function attachJob$lambda($this_attachJob) {
  return (it) => {
    var tmp;
    if (!(it == null)) {
      $this_attachJob.a27(it);
      tmp = Unit_instance;
    }
    return Unit_instance;
  };
}
function attachWriterJob$lambda($this_attachWriterJob) {
  return (cause) => {
    var tmp;
    if (!(cause == null)) {
      $this_attachWriterJob.a27(cause);
      tmp = Unit_instance;
    }
    return Unit_instance;
  };
}
var Companion_instance_1;
function Companion_getInstance_0() {
  if (Companion_instance_1 === VOID)
    new Companion_0();
  return Companion_instance_1;
}
function cancel_0(_this__u8e3s4) {
  _this__u8e3s4.a27(IOException.u24('Channel was cancelled'));
}
function *readBuffer(_this__u8e3s4, $completion) {
  var result = new Buffer();
  while (!_this__u8e3s4.n26()) {
    if (_this__u8e3s4.w25().r21()) {
      yield* _this__u8e3s4.w26(VOID, $completion);
    }
    result.j23(_this__u8e3s4.w25());
  }
  rethrowCloseCauseIfNeeded_0(_this__u8e3s4);
  return result;
}
function *readAvailable(_this__u8e3s4, buffer, offset, length, $completion) {
  offset = offset === VOID ? 0 : offset;
  length = length === VOID ? buffer.length - offset | 0 : length;
  if (_this__u8e3s4.n26())
    return -1;
  if (_this__u8e3s4.w25().r21()) {
    yield* _this__u8e3s4.w26(VOID, $completion);
  }
  if (_this__u8e3s4.n26())
    return -1;
  return readAvailable_0(_this__u8e3s4.w25(), buffer, offset, length);
}
function *toByteArray(_this__u8e3s4, $completion) {
  return readBytes(yield* /*#__NOINLINE__*/readBuffer(_this__u8e3s4, $completion));
}
function *copyTo(_this__u8e3s4, channel, limit, $completion) {
  var remaining = limit;
  try {
    while (!_this__u8e3s4.n26() && remaining > 0n) {
      if (_this__u8e3s4.w25().r21()) {
        yield* _this__u8e3s4.w26(VOID, $completion);
      }
      var tmp0 = remaining;
      // Inline function 'kotlin.comparisons.minOf' call
      var b = get_remaining(_this__u8e3s4.w25());
      var count = tmp0 <= b ? tmp0 : b;
      _this__u8e3s4.w25().t22(channel.c26(), count);
      remaining = subtract(remaining, count);
      yield* channel.x26($completion);
    }
  } catch ($p) {
    if ($p instanceof Error) {
      var cause = $p;
      _this__u8e3s4.a27(cause);
      close_0(channel, cause);
      throw cause;
    } else {
      throw $p;
    }
  }
  finally {
    yield* channel.x26($completion);
  }
  return subtract(limit, remaining);
}
function rethrowCloseCauseIfNeeded_0(_this__u8e3s4) {
  var tmp0_safe_receiver = _this__u8e3s4.s25();
  if (tmp0_safe_receiver == null)
    null;
  else {
    // Inline function 'kotlin.let' call
    throw tmp0_safe_receiver;
  }
}
function *discard(_this__u8e3s4, max, $completion) {
  max = max === VOID ? 9223372036854775807n : max;
  var remaining = max;
  while (remaining > 0n && !_this__u8e3s4.n26()) {
    if (get_availableForRead(_this__u8e3s4) === 0) {
      yield* _this__u8e3s4.w26(VOID, $completion);
    }
    var tmp0 = remaining;
    // Inline function 'kotlin.comparisons.minOf' call
    var b = get_remaining(_this__u8e3s4.w25());
    var count = tmp0 <= b ? tmp0 : b;
    discard_0(_this__u8e3s4.w25(), count);
    remaining = subtract(remaining, count);
  }
  return subtract(max, remaining);
}
function *copyTo_0(_this__u8e3s4, channel, $completion) {
  var result = 0n;
  try {
    while (!_this__u8e3s4.n26()) {
      result = add(result, _this__u8e3s4.w25().u22(channel.c26()));
      yield* channel.x26($completion);
      yield* _this__u8e3s4.w26(VOID, $completion);
    }
  } catch ($p) {
    if ($p instanceof Error) {
      var cause = $p;
      _this__u8e3s4.a27(cause);
      close_0(channel, cause);
      throw cause;
    } else {
      throw $p;
    }
  }
  finally {
    yield* channel.x26($completion);
  }
  return result;
}
function *skipIfFound(_this__u8e3s4, byteString, $completion) {
  if (equals(yield* peek(_this__u8e3s4, byteString.j2(), $completion), byteString)) {
    yield* /*#__NOINLINE__*/discard(_this__u8e3s4, fromInt(byteString.j2()), $completion);
    return true;
  }
  return false;
}
function *readPacket(_this__u8e3s4, packet, $completion) {
  var result = new Buffer();
  $l$loop: while (result.j2() < fromInt(packet)) {
    if (_this__u8e3s4.w25().r21()) {
      yield* _this__u8e3s4.w26(VOID, $completion);
    }
    if (_this__u8e3s4.n26())
      break $l$loop;
    if (get_remaining(_this__u8e3s4.w25()) > subtract(numberToLong(packet), result.j2())) {
      _this__u8e3s4.w25().t22(result, subtract(numberToLong(packet), result.j2()));
    } else {
      _this__u8e3s4.w25().u22(result);
    }
  }
  if (result.j2() < fromInt(packet)) {
    throw EOFException.p21('Not enough data available, required ' + packet + ' bytes but only ' + result.j2().toString() + ' available');
  }
  return result;
}
function *readUntil(_this__u8e3s4, matchString, writeChannel, limit, ignoreMissing, $completion) {
  limit = limit === VOID ? 9223372036854775807n : limit;
  ignoreMissing = ignoreMissing === VOID ? false : ignoreMissing;
  return yield* (new ByteChannelScanner(_this__u8e3s4, matchString, writeChannel, limit)).l27(ignoreMissing, $completion);
}
function *readLineStrictTo(_this__u8e3s4, out, limit, lineEnding, $completion) {
  limit = limit === VOID ? 9223372036854775807n : limit;
  lineEnding = lineEnding === VOID ? LineEnding_Default_getInstance() : lineEnding;
  // Inline function 'kotlin.require' call
  if (!(limit >= 0n)) {
    var message = 'Limit (' + limit.toString() + ') should be non-negative';
    throw IllegalArgumentException.i2(toString_1(message));
  }
  return yield* internalReadLineTo(_this__u8e3s4, out, limit, lineEnding.equals(LineEnding_Lenient_getInstance()), true, $completion);
}
function get_availableForRead(_this__u8e3s4) {
  return convertToInt(_this__u8e3s4.w25().q21().j2());
}
function *peek(_this__u8e3s4, count, $completion) {
  if (_this__u8e3s4.n26())
    return null;
  if (!(yield* _this__u8e3s4.q26(count, $completion)))
    return null;
  return readByteString(_this__u8e3s4.w25().v22(), count);
}
function *internalReadLineTo(_this__u8e3s4, out, limit, lenientLineEnding, throwOnIncompleteLine, $completion) {
  var readBuffer = _this__u8e3s4.w25();
  if (readBuffer.r21()) {
    yield* _this__u8e3s4.w26(VOID, $completion);
  }
  if (_this__u8e3s4.n26())
    return -1n;
  var consumed = {_v: 0n};
  var outBuffer = {_v: null};
  try {
    $l$loop: while (consumed._v < limit && !_this__u8e3s4.n26()) {
      var limitLeft = subtract(limit, consumed._v);
      var lfIndex = indexOf(readBuffer, 10, VOID, limitLeft);
      var crIndex = internalReadLineTo$scanForSoleCr(readBuffer, lenientLineEnding, lfIndex, limitLeft);
      if (crIndex >= 0n) {
        internalReadLineTo$appendOrBuffer(outBuffer, out, readBuffer, consumed, crIndex);
        discard_0(readBuffer, 1n);
        return consumed._v;
      }
      if (lfIndex === 0n) {
        discard_0(readBuffer, 1n);
        return consumed._v;
      }
      if (lfIndex > 0n) {
        var tmp;
        var tmp_0 = readBuffer.q21();
        // Inline function 'kotlin.Long.minus' call
        var tmp$ret$0 = subtract(lfIndex, fromInt(1));
        if (tmp_0.m22(tmp$ret$0) === 13) {
          tmp = 1n;
        } else {
          tmp = 0n;
        }
        var isCrlf = tmp;
        internalReadLineTo$appendOrBuffer(outBuffer, out, readBuffer, consumed, subtract(lfIndex, isCrlf));
        discard_0(readBuffer, add(numberToLong(1), isCrlf));
        return consumed._v;
      }
      // Inline function 'kotlin.comparisons.minOf' call
      var b = get_remaining(readBuffer);
      var count = limitLeft <= b ? limitLeft : b;
      var tmp_1 = readBuffer.q21();
      // Inline function 'kotlin.Long.minus' call
      var tmp$ret$2 = subtract(count, fromInt(1));
      if (tmp_1.m22(tmp$ret$2) === 13) {
        // Inline function 'kotlin.Long.minus' call
        var tmp$ret$3 = subtract(count, fromInt(1));
        internalReadLineTo$appendOrBuffer(outBuffer, out, readBuffer, consumed, tmp$ret$3);
        if (yield* internalReadLineTo$discardCrlfOrCr(readBuffer, _this__u8e3s4, lenientLineEnding, $completion)) {
          return consumed._v;
        } else {
          internalReadLineTo$bufferBytes(outBuffer, readBuffer, consumed, 1n);
        }
      } else {
        internalReadLineTo$bufferBytes(outBuffer, readBuffer, consumed, count);
      }
      if (consumed._v < limit && get_remaining(readBuffer) === 0n && !(yield* _this__u8e3s4.w26(VOID, $completion)))
        break $l$loop;
    }
    if (consumed._v === 0n && _this__u8e3s4.n26())
      return -1n;
    // Inline function 'kotlin.check' call
    if (!(consumed._v <= limit)) {
      var message = 'Consumed bytes exceed the limit: ' + consumed._v.toString() + ' > ' + limit.toString() + ". It's an implementation bug, please report it.";
      throw IllegalStateException.o(toString_1(message));
    }
    if (consumed._v === limit) {
      if (limit === 9223372036854775807n)
        throw TooLongLineException.v27('Max line length exceeded');
      if (get_remaining(readBuffer) === 0n && !(yield* _this__u8e3s4.w26(VOID, $completion))) {
        throwEndOfStreamException(consumed._v);
      }
      var tmp0_subject = readBuffer.q21().m22(0n);
      if (tmp0_subject === 10) {
        discard_0(readBuffer, 1n);
        return consumed._v;
      } else if (tmp0_subject === 13)
        if (yield* internalReadLineTo$discardCrlfOrCr(readBuffer, _this__u8e3s4, lenientLineEnding, $completion)) {
          return consumed._v;
        }
      throwTooLongLineException(limit);
    }
    if (throwOnIncompleteLine) {
      throwEndOfStreamException(consumed._v);
    }
    return consumed._v;
  }finally {
    if (!(outBuffer._v == null)) {
      out.t1(readString(outBuffer._v));
    }
  }
}
function throwEndOfStreamException(consumed) {
  throw EOFException.p21('Unexpected end of stream after reading ' + consumed.toString() + ' bytes');
}
function throwTooLongLineException(limit) {
  throw TooLongLineException.v27('Line exceeds limit of ' + limit.toString() + ' bytes');
}
function rethrowCloseCauseIfNeeded_1(_this__u8e3s4) {
  var tmp0_safe_receiver = _this__u8e3s4.s25();
  if (tmp0_safe_receiver == null)
    null;
  else {
    // Inline function 'kotlin.let' call
    throw tmp0_safe_receiver;
  }
}
function internalReadLineTo$bufferBytes(outBuffer, readBuffer, consumed, count) {
  if (count > 0n) {
    if (outBuffer._v == null) {
      outBuffer._v = new Buffer();
    }
    outBuffer._v.e23(readBuffer, count);
    consumed._v = add(consumed._v, count);
  }
}
function internalReadLineTo$appendOrBuffer(outBuffer, $out, readBuffer, consumed, count) {
  if (outBuffer._v == null) {
    if (count > 0n) {
      $out.t1(readString_0(readBuffer, count));
      consumed._v = add(consumed._v, count);
    }
  } else {
    internalReadLineTo$bufferBytes(outBuffer, readBuffer, consumed, count);
  }
}
function internalReadLineTo$scanForSoleCr(_this__u8e3s4, $lenientLineEnding, lfIndex, limitLeft) {
  if (!$lenientLineEnding)
    return -1n;
  var tmp;
  if (lfIndex === -1n) {
    // Inline function 'kotlin.Long.minus' call
    var this_0 = get_remaining(_this__u8e3s4);
    // Inline function 'kotlin.comparisons.minOf' call
    var b = subtract(this_0, fromInt(1));
    tmp = limitLeft <= b ? limitLeft : b;
  } else if (lfIndex === 0n) {
    tmp = 0n;
  } else {
    // Inline function 'kotlin.Long.minus' call
    tmp = subtract(lfIndex, fromInt(1));
  }
  var endIndex = tmp;
  return indexOf(_this__u8e3s4, 13, VOID, endIndex);
}
function *internalReadLineTo$discardCrlfOrCr(_this__u8e3s4, $this_internalReadLineTo, $lenientLineEnding, $completion) {
  if ((get_remaining(_this__u8e3s4) >= 2n || (yield* $this_internalReadLineTo.q26(2, $completion))) && _this__u8e3s4.q21().m22(1n) === 10) {
    discard_0(_this__u8e3s4, 2n);
    return true;
  }
  if ($lenientLineEnding) {
    discard_0(_this__u8e3s4, 1n);
    return true;
  }
  return false;
}
function *flushIfNeeded(_this__u8e3s4, $completion) {
  rethrowCloseCauseIfNeeded_1(_this__u8e3s4);
  if (_this__u8e3s4.u25() || get_size(_this__u8e3s4.c26()) >= 1048576)
    yield* _this__u8e3s4.x26($completion);
  return Unit_instance;
}
function ByteWriteChannel$flushAndClose$ref(p0) {
  return constructCallableReference(($completion) => orPromise($completion, function *($completion) {
    return yield* p0.z26($completion);
  }), 0, 1, 16, 'flushAndClose', [p0]);
}
function get_NO_CALLBACK() {
  _init_properties_ByteWriteChannelOperations_kt__i7slrs();
  return NO_CALLBACK;
}
var NO_CALLBACK;
function *writeFully(_this__u8e3s4, value, startIndex, endIndex, $completion) {
  startIndex = startIndex === VOID ? 0 : startIndex;
  endIndex = endIndex === VOID ? value.length : endIndex;
  _this__u8e3s4.c26().a23(value, startIndex, endIndex);
  yield* flushIfNeeded(_this__u8e3s4, $completion);
  return Unit_instance;
}
function writer(_this__u8e3s4, coroutineContext, autoFlush, block) {
  coroutineContext = coroutineContext === VOID ? EmptyCoroutineContext_instance : coroutineContext;
  autoFlush = autoFlush === VOID ? false : autoFlush;
  _init_properties_ByteWriteChannelOperations_kt__i7slrs();
  return writer_0(_this__u8e3s4, coroutineContext, new ByteChannel(autoFlush), block);
}
function writer_0(_this__u8e3s4, coroutineContext, channel, block) {
  coroutineContext = coroutineContext === VOID ? EmptyCoroutineContext_instance : coroutineContext;
  _init_properties_ByteWriteChannelOperations_kt__i7slrs();
  // Inline function 'kotlin.apply' call
  var this_0 = launch(_this__u8e3s4, coroutineContext, VOID, writer$slambda(block, channel));
  this_0.vu(writer$lambda(channel));
  var job = this_0;
  return new WriterJob(channel, job);
}
function invokeOnCompletion(_this__u8e3s4, block) {
  _init_properties_ByteWriteChannelOperations_kt__i7slrs();
  return _this__u8e3s4.lz().vu(block);
}
function close_0(_this__u8e3s4, cause) {
  _init_properties_ByteWriteChannelOperations_kt__i7slrs();
  if (cause == null) {
    fireAndForget(ByteWriteChannel$flushAndClose$ref(_this__u8e3s4));
  } else {
    _this__u8e3s4.a27(cause);
  }
}
function *writePacket(_this__u8e3s4, source, $completion) {
  while (!source.r21()) {
    _this__u8e3s4.c26().e23(source, get_remaining(source));
    yield* flushIfNeeded(_this__u8e3s4, $completion);
  }
  return Unit_instance;
}
function fireAndForget(_this__u8e3s4) {
  _init_properties_ByteWriteChannelOperations_kt__i7slrs();
  startCoroutineCancellable(_this__u8e3s4, get_NO_CALLBACK());
}
function *writeByte(_this__u8e3s4, value, $completion) {
  _this__u8e3s4.c26().k23(value);
  yield* flushIfNeeded(_this__u8e3s4, $completion);
  return Unit_instance;
}
function writer$slambda($block, $channel) {
  return constructCallableReference(($this$launch, $completion) => orPromise($completion, function *($completion) {
    var nested = Job(get_job($this$launch.st()));
    var tmp;
    try {
      yield* $($block(new WriterScope($channel, $this$launch.st().jk(nested)), $completion), $completion);
      nested.xz();
      var tmp_0;
      if (get_job($this$launch.st()).pu()) {
        $channel.a27(get_job($this$launch.st()).su());
        tmp_0 = Unit_instance;
      }
      tmp = tmp_0;
    } catch ($p) {
      var tmp_1;
      if ($p instanceof Error) {
        var cause = $p;
        cancel(nested, 'Exception thrown while writing to channel', cause);
        $channel.a27(cause);
        tmp_1 = Unit_instance;
      } else {
        throw $p;
      }
      tmp = tmp_1;
    }
    finally {
      yield* nested.zu($completion);
      // Inline function 'kotlin.runCatching' call
      var tmp_2;
      try {
        yield* $channel.z26($completion);
        // Inline function 'kotlin.Companion.success' call
        tmp_2 = _Result___init__impl__xyqfz8(Unit_instance);
      } catch ($p_0) {
        var tmp_3;
        if ($p_0 instanceof Error) {
          var e = $p_0;
          // Inline function 'kotlin.Companion.failure' call
          tmp_3 = _Result___init__impl__xyqfz8(createFailure(e));
        } else {
          throw $p_0;
        }
        tmp_2 = tmp_3;
      }
    }
    return Unit_instance;
  }), 1);
}
function writer$lambda($channel) {
  return (it) => {
    var tmp;
    if (!(it == null) && !$channel.i26()) {
      $channel.a27(it);
      tmp = Unit_instance;
    }
    return Unit_instance;
  };
}
var properties_initialized_ByteWriteChannelOperations_kt_acrf6u;
function _init_properties_ByteWriteChannelOperations_kt__i7slrs() {
  if (!properties_initialized_ByteWriteChannelOperations_kt_acrf6u) {
    properties_initialized_ByteWriteChannelOperations_kt_acrf6u = true;
    NO_CALLBACK = new NO_CALLBACK$1();
  }
}
function get_CLOSED() {
  _init_properties_CloseToken_kt__9ucr41();
  return CLOSED;
}
var CLOSED;
var Companion_instance_2;
function Companion_getInstance_1() {
  return Companion_instance_2;
}
function access$_get_origin__hdh1qz($this) {
  return $this.o26_1;
}
var properties_initialized_CloseToken_kt_lgg8zn;
function _init_properties_CloseToken_kt__9ucr41() {
  if (!properties_initialized_CloseToken_kt_lgg8zn) {
    properties_initialized_CloseToken_kt_lgg8zn = true;
    CLOSED = new CloseToken(null);
  }
}
function transferFromDelegate($this) {
  updateConsumed($this);
  var appended = $this.a28_1.j23($this.z27_1.w25());
  $this.b28_1 = add($this.b28_1, appended);
}
function updateConsumed($this) {
  $this.c28_1 = add($this.c28_1, subtract($this.b28_1, $this.a28_1.j2()));
  $this.b28_1 = $this.a28_1.j2();
}
function counted(_this__u8e3s4) {
  return new CountedByteReadChannel(_this__u8e3s4);
}
function readText(_this__u8e3s4) {
  return readString_1(_this__u8e3s4);
}
var static_init_called;
function static_init() {
  if (static_init_called)
    return Unit_instance;
  static_init_called = true;
  LineEnding_Default_instance = new LineEnding('Default', 0);
  LineEnding_Lenient_instance = new LineEnding('Lenient', 1);
}
var LineEnding_Default_instance;
var LineEnding_Lenient_instance;
function LineEnding_Default_getInstance() {
  static_init();
  return LineEnding_Default_instance;
}
function LineEnding_Lenient_getInstance() {
  static_init();
  return LineEnding_Lenient_instance;
}
function _LineEndingMode___init__impl__jo5bul(mode) {
  return mode;
}
function _get_mode__dah3bc($this) {
  return $this;
}
function LineEndingMode__contains_impl_q5pr68($this, other) {
  return (_get_mode__dah3bc($this) | _get_mode__dah3bc(other)) === _get_mode__dah3bc($this);
}
function LineEndingMode__plus_impl_ttpz2j($this, other) {
  return _LineEndingMode___init__impl__jo5bul(_get_mode__dah3bc($this) | _get_mode__dah3bc(other));
}
function LineEndingMode__toString_impl_j4h76r($this) {
  var tmp;
  if (equals(new LineEndingMode($this), new LineEndingMode(Companion_getInstance_2().e28_1))) {
    tmp = 'CR';
  } else if (equals(new LineEndingMode($this), new LineEndingMode(Companion_getInstance_2().f28_1))) {
    tmp = 'LF';
  } else if (equals(new LineEndingMode($this), new LineEndingMode(Companion_getInstance_2().g28_1))) {
    tmp = 'CRLF';
  } else {
    // Inline function 'kotlin.collections.filter' call
    var tmp0 = Companion_getInstance_2().i28_1;
    // Inline function 'kotlin.collections.filterTo' call
    var destination = ArrayList.p2();
    var _iterator__ex2g4s = tmp0.l1();
    while (_iterator__ex2g4s.m1()) {
      var element = _iterator__ex2g4s.n1();
      var it = element.j28_1;
      if (LineEndingMode__contains_impl_q5pr68($this, it)) {
        destination.a2(element);
      }
    }
    tmp = toString_1(destination);
  }
  return tmp;
}
var Companion_instance_3;
function Companion_getInstance_2() {
  if (Companion_instance_3 === VOID)
    new Companion_2();
  return Companion_instance_3;
}
function LineEndingMode__hashCode_impl_2mopm4($this) {
  return $this;
}
function LineEndingMode__equals_impl_qyr4nk($this, other) {
  if (!(other instanceof LineEndingMode))
    return false;
  if (!($this === other.j28_1))
    return false;
  return true;
}
function decode(_this__u8e3s4, input, max) {
  max = max === VOID ? 2147483647 : max;
  var tmp0 = fromInt(max);
  // Inline function 'kotlin.comparisons.minOf' call
  var b = input.q21().j2();
  var tmp$ret$0 = tmp0 <= b ? tmp0 : b;
  // Inline function 'kotlin.text.buildString' call
  var capacity = convertToInt(tmp$ret$0);
  // Inline function 'kotlin.apply' call
  var this_0 = StringBuilder.rb(capacity);
  decode_0(_this__u8e3s4, input, this_0, max);
  return this_0.toString();
}
function encode(_this__u8e3s4, input, fromIndex, toIndex) {
  fromIndex = fromIndex === VOID ? 0 : fromIndex;
  toIndex = toIndex === VOID ? charSequenceLength(input) : toIndex;
  // Inline function 'io.ktor.utils.io.core.buildPacket' call
  var builder = new Buffer();
  encodeToImpl(_this__u8e3s4, builder, input, fromIndex, toIndex);
  return builder;
}
function encodeToImpl(_this__u8e3s4, destination, input, fromIndex, toIndex) {
  var start = fromIndex;
  if (start >= toIndex)
    return Unit_instance;
  $l$loop: while (true) {
    var rc = encodeImpl(_this__u8e3s4, input, start, toIndex, destination);
    // Inline function 'kotlin.check' call
    if (!(rc >= 0)) {
      throw IllegalStateException.o('Check failed.');
    }
    start = start + rc | 0;
    if (start >= toIndex)
      break $l$loop;
  }
}
function canRead(_this__u8e3s4) {
  return !_this__u8e3s4.r21();
}
function readBytes(_this__u8e3s4, count) {
  count = count === VOID ? convertToInt(_this__u8e3s4.j2()) : count;
  return readByteArray(_this__u8e3s4, count);
}
function writeFully_0(_this__u8e3s4, buffer, offset, length) {
  offset = offset === VOID ? 0 : offset;
  length = length === VOID ? buffer.length - offset | 0 : length;
  _this__u8e3s4.a23(buffer, offset, offset + length | 0);
}
function BytePacketBuilder() {
  return new Buffer();
}
function build(_this__u8e3s4) {
  return _this__u8e3s4.q21();
}
function get_size(_this__u8e3s4) {
  return convertToInt(_this__u8e3s4.q21().j2());
}
var ByteReadPacketEmpty;
function ByteReadPacket(array, offset, length) {
  offset = offset === VOID ? 0 : offset;
  length = length === VOID ? array.length : length;
  _init_properties_ByteReadPacket_kt__28475y();
  // Inline function 'kotlin.apply' call
  var this_0 = new Buffer();
  this_0.a23(array, offset, offset + length | 0);
  return this_0;
}
function get_remaining(_this__u8e3s4) {
  _init_properties_ByteReadPacket_kt__28475y();
  return _this__u8e3s4.q21().j2();
}
function discard_0(_this__u8e3s4, count) {
  count = count === VOID ? 9223372036854775807n : count;
  _init_properties_ByteReadPacket_kt__28475y();
  _this__u8e3s4.t21(count);
  // Inline function 'kotlin.comparisons.minOf' call
  var b = get_remaining(_this__u8e3s4);
  var countToDiscard = count <= b ? count : b;
  _this__u8e3s4.q21().o22(countToDiscard);
  return countToDiscard;
}
function takeWhile(_this__u8e3s4, block) {
  _init_properties_ByteReadPacket_kt__28475y();
  while (!_this__u8e3s4.r21() && block(_this__u8e3s4.q21())) {
  }
}
var properties_initialized_ByteReadPacket_kt_hw4st4;
function _init_properties_ByteReadPacket_kt__28475y() {
  if (!properties_initialized_ByteReadPacket_kt_hw4st4) {
    properties_initialized_ByteReadPacket_kt_hw4st4 = true;
    ByteReadPacketEmpty = new Buffer();
  }
}
function readAvailable_0(_this__u8e3s4, buffer, offset, length) {
  offset = offset === VOID ? 0 : offset;
  length = length === VOID ? buffer.length - offset | 0 : length;
  var result = _this__u8e3s4.p22(buffer, offset, offset + length | 0);
  return result === -1 ? 0 : result;
}
function toByteArray_0(_this__u8e3s4, charset) {
  charset = charset === VOID ? Charsets_getInstance().q28_1 : charset;
  if (charset.equals(Charsets_getInstance().q28_1))
    return encodeToByteArray(_this__u8e3s4);
  return encodeToByteArray_0(charset.t28(), _this__u8e3s4, 0, _this__u8e3s4.length);
}
function writeText(_this__u8e3s4, text, fromIndex, toIndex, charset) {
  fromIndex = fromIndex === VOID ? 0 : fromIndex;
  toIndex = toIndex === VOID ? charSequenceLength(text) : toIndex;
  charset = charset === VOID ? Charsets_getInstance().q28_1 : charset;
  if (charset === Charsets_getInstance().q28_1) {
    return writeString(_this__u8e3s4, toString_1(text), fromIndex, toIndex);
  }
  encodeToImpl(charset.t28(), _this__u8e3s4, text, fromIndex, toIndex);
}
function get_ByteArrayPool() {
  _init_properties_ByteArrayPool_kt__kfi3uj();
  return ByteArrayPool;
}
var ByteArrayPool;
var properties_initialized_ByteArrayPool_kt_td6pfh;
function _init_properties_ByteArrayPool_kt__kfi3uj() {
  if (!properties_initialized_ByteArrayPool_kt_td6pfh) {
    properties_initialized_ByteArrayPool_kt_td6pfh = true;
    ByteArrayPool = new ByteArrayPool$1();
  }
}
var Companion_instance_4;
function Companion_getInstance_3() {
  return Companion_instance_4;
}
var Charsets_instance;
function Charsets_getInstance() {
  if (Charsets_instance === VOID)
    new Charsets();
  return Charsets_instance;
}
function get_name(_this__u8e3s4) {
  return _this__u8e3s4.s28_1;
}
function forName(_this__u8e3s4, name) {
  return Companion_instance_4.g29(name);
}
function decode_0(_this__u8e3s4, input, dst, max) {
  var decoder = Decoder(get_name(get_charset(_this__u8e3s4)), true);
  input.t21(fromInt(max));
  var tmp0 = input.q21().j2();
  // Inline function 'kotlin.comparisons.minOf' call
  var b = fromInt(max);
  var count = tmp0 <= b ? tmp0 : b;
  var array = readByteArray(input, convertToInt(count));
  var tmp;
  try {
    // Inline function 'org.khronos.webgl.toInt8Array' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp = decoder.k29(array);
  } catch ($p) {
    var tmp_0;
    if ($p instanceof Error) {
      var cause = $p;
      var tmp0_elvis_lhs = cause.message;
      throw MalformedInputException.p28('Failed to decode bytes: ' + (tmp0_elvis_lhs == null ? 'no cause provided' : tmp0_elvis_lhs));
    } else {
      throw $p;
    }
  }
  var result = tmp;
  dst.t1(result);
  return result.length;
}
function encodeImpl(_this__u8e3s4, input, fromIndex, toIndex, dst) {
  // Inline function 'kotlin.require' call
  // Inline function 'kotlin.require' call
  if (!(fromIndex <= toIndex)) {
    var message = 'Failed requirement.';
    throw IllegalArgumentException.i2(toString_1(message));
  }
  if (get_charset_0(_this__u8e3s4).equals(Charsets_getInstance().r28_1)) {
    return encodeISO88591(input, fromIndex, toIndex, dst);
  }
  // Inline function 'kotlin.require' call
  if (!(get_charset_0(_this__u8e3s4) === Charsets_getInstance().q28_1)) {
    var message_0 = 'Only UTF-8 encoding is supported in JS';
    throw IllegalArgumentException.i2(toString_1(message_0));
  }
  var encoder = new TextEncoder();
  // Inline function 'kotlin.text.substring' call
  var tmp$ret$5 = toString_1(charSequenceSubSequence(input, fromIndex, toIndex));
  var result = encoder.encode(tmp$ret$5);
  // Inline function 'kotlin.js.unsafeCast' call
  // Inline function 'kotlin.js.asDynamic' call
  dst.d23(result);
  return result.length;
}
function encodeToByteArray_0(_this__u8e3s4, input, fromIndex, toIndex) {
  fromIndex = fromIndex === VOID ? 0 : fromIndex;
  toIndex = toIndex === VOID ? charSequenceLength(input) : toIndex;
  return encodeToByteArrayImpl(_this__u8e3s4, input, fromIndex, toIndex);
}
function get_charset(_this__u8e3s4) {
  return _this__u8e3s4.i29_1;
}
function get_charset_0(_this__u8e3s4) {
  return _this__u8e3s4.j29_1;
}
function encodeToByteArrayImpl(_this__u8e3s4, input, fromIndex, toIndex) {
  fromIndex = fromIndex === VOID ? 0 : fromIndex;
  toIndex = toIndex === VOID ? charSequenceLength(input) : toIndex;
  var start = fromIndex;
  if (start >= toIndex)
    return new Int8Array(0);
  var dst = new Buffer();
  var rc = encodeImpl(_this__u8e3s4, input, start, toIndex, dst);
  start = start + rc | 0;
  if (start === toIndex) {
    return readByteArray_0(dst);
  }
  encodeToImpl(_this__u8e3s4, dst, input, start, toIndex);
  return readByteArray_0(dst);
}
function Decoder(encoding, fatal) {
  fatal = fatal === VOID ? true : fatal;
  var tmp;
  try {
    tmp = toKtor(new TextDecoder(encoding, textDecoderOptions(fatal)));
  } catch ($p) {
    var tmp_0;
    if ($p instanceof Error) {
      var cause = $p;
      tmp_0 = new TextDecoderFallback(encoding, fatal);
    } else {
      throw $p;
    }
    tmp = tmp_0;
  }
  return tmp;
}
function toKtor(_this__u8e3s4) {
  return new toKtor$1(_this__u8e3s4);
}
function textDecoderOptions(fatal) {
  fatal = fatal === VOID ? false : fatal;
  // Inline function 'kotlin.apply' call
  var this_0 = new Object();
  // Inline function 'kotlin.js.asDynamic' call
  // Inline function 'kotlin.with' call
  this_0.fatal = fatal;
  return this_0;
}
function get_ENCODING_ALIASES() {
  _init_properties_TextDecoderFallback_js_kt__an7r6m();
  return ENCODING_ALIASES;
}
var ENCODING_ALIASES;
function get_REPLACEMENT() {
  _init_properties_TextDecoderFallback_js_kt__an7r6m();
  return REPLACEMENT;
}
var REPLACEMENT;
function toCodePoint(_this__u8e3s4) {
  _init_properties_TextDecoderFallback_js_kt__an7r6m();
  var value = _this__u8e3s4 & 255;
  if (isASCII(value)) {
    return value;
  }
  return get_WIN1252_TABLE()[value - 128 | 0];
}
function isASCII(_this__u8e3s4) {
  _init_properties_TextDecoderFallback_js_kt__an7r6m();
  return 0 <= _this__u8e3s4 ? _this__u8e3s4 <= 127 : false;
}
var properties_initialized_TextDecoderFallback_js_kt_6rekzk;
function _init_properties_TextDecoderFallback_js_kt__an7r6m() {
  if (!properties_initialized_TextDecoderFallback_js_kt_6rekzk) {
    properties_initialized_TextDecoderFallback_js_kt_6rekzk = true;
    ENCODING_ALIASES = setOf(['ansi_x3.4-1968', 'ascii', 'cp1252', 'cp819', 'csisolatin1', 'ibm819', 'iso-8859-1', 'iso-ir-100', 'iso8859-1', 'iso88591', 'iso_8859-1', 'iso_8859-1:1987', 'l1', 'latin1', 'us-ascii', 'windows-1252', 'x-cp1252']);
    // Inline function 'kotlin.byteArrayOf' call
    REPLACEMENT = new Int8Array([-17, -65, -67]);
  }
}
function readText_0(_this__u8e3s4, charset, max) {
  charset = charset === VOID ? Charsets_getInstance().q28_1 : charset;
  max = max === VOID ? 2147483647 : max;
  if (charset.equals(Charsets_getInstance().q28_1)) {
    if (max === 2147483647)
      return readString_1(_this__u8e3s4);
    _this__u8e3s4.t21(fromInt(max));
    var tmp0 = _this__u8e3s4.q21().j2();
    // Inline function 'kotlin.math.min' call
    var b = fromInt(max);
    var count = tmp0 <= b ? tmp0 : b;
    return readString_0(_this__u8e3s4, count);
  }
  return decode(charset.h29(), _this__u8e3s4, max);
}
function get_DEVELOPMENT_MODE() {
  return false;
}
function ioDispatcher() {
  return Dispatchers_getInstance().g14_1;
}
function encodeISO88591(input, fromIndex, toIndex, dst) {
  if (fromIndex >= toIndex)
    return 0;
  var inductionVariable = fromIndex;
  if (inductionVariable < toIndex)
    do {
      var index = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      // Inline function 'kotlin.code' call
      var this_0 = charSequenceGet(input, index);
      var character = Char__toInt_impl_vasixd(this_0);
      if (character > 255) {
        failedToMapError(character);
      }
      dst.k23(toByte(character));
    }
     while (inductionVariable < toIndex);
  return toIndex - fromIndex | 0;
}
function failedToMapError(ch) {
  throw MalformedInputException.p28('The character with unicode point ' + ch + " couldn't be mapped to ISO-8859-1 character");
}
function get_WIN1252_TABLE() {
  _init_properties_Win1252Table_kt__tl0v64();
  return WIN1252_TABLE;
}
var WIN1252_TABLE;
var properties_initialized_Win1252Table_kt_pkmjoq;
function _init_properties_Win1252Table_kt__tl0v64() {
  if (!properties_initialized_Win1252Table_kt_pkmjoq) {
    properties_initialized_Win1252Table_kt_pkmjoq = true;
    // Inline function 'kotlin.intArrayOf' call
    WIN1252_TABLE = new Int32Array([8364, -1, 8218, 402, 8222, 8230, 8224, 8225, 710, 8240, 352, 8249, 338, -1, 381, -1, -1, 8216, 8217, 8220, 8221, 8226, 8211, 8212, 732, 8482, 353, 8250, 339, -1, 382, 376, 160, 161, 162, 163, 164, 165, 166, 167, 168, 169, 170, 171, 172, 173, 174, 175, 176, 177, 178, 179, 180, 181, 182, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215, 216, 217, 218, 219, 220, 221, 222, 223, 224, 225, 226, 227, 228, 229, 230, 231, 232, 233, 234, 235, 236, 237, 238, 239, 240, 241, 242, 243, 244, 245, 246, 247, 248, 249, 250, 251, 252, 253, 254, 255]);
  }
}
//region block: post-declaration
initMetadataForCompanion(Companion);
initMetadataForObject(Empty, 'Empty');
initMetadataForClass(Closed, 'Closed');
initMetadataForInterface(Task, 'Task');
protoOf(Read).d25 = resume;
protoOf(Read).e25 = resume_0;
initMetadataForClass(Read, 'Read', VOID, VOID, [Task]);
protoOf(Write).d25 = resume;
protoOf(Write).e25 = resume_0;
initMetadataForClass(Write, 'Write', VOID, VOID, [Task]);
initMetadataForClass(sam$kotlinx_coroutines_DisposableHandle$0, 'sam$kotlinx_coroutines_DisposableHandle$0', VOID, VOID, [DisposableHandle, FunctionAdapter]);
initMetadataForInterface(ByteReadChannel, 'ByteReadChannel', VOID, VOID, VOID, [1]);
initMetadataForInterface(ByteWriteChannel, 'ByteWriteChannel', VOID, VOID, VOID, [0]);
protoOf(ByteChannel).w26 = awaitContent$default;
initMetadataForClass(ByteChannel, 'ByteChannel', ByteChannel, VOID, [ByteReadChannel, ByteWriteChannel], [1, 0]);
initMetadataForClass(ConcurrentIOException, 'ConcurrentIOException');
initMetadataForClass(ByteChannelScanner, 'ByteChannelScanner', VOID, VOID, VOID, [1, 0]);
protoOf(ByteReadChannel$Companion$Empty$1).w26 = awaitContent$default;
initMetadataForClass(ByteReadChannel$Companion$Empty$1, VOID, VOID, VOID, [ByteReadChannel], [1]);
initMetadataForCompanion(Companion_0);
initMetadataForClass(WriterJob, 'WriterJob');
initMetadataForClass(WriterScope, 'WriterScope');
initMetadataForClass(NO_CALLBACK$1, VOID, VOID, VOID, [Continuation]);
initMetadataForCompanion(Companion_1);
initMetadataForClass(CloseToken, 'CloseToken');
protoOf(CountedByteReadChannel).w26 = awaitContent$default;
initMetadataForClass(CountedByteReadChannel, 'CountedByteReadChannel', VOID, VOID, [ByteReadChannel], [1]);
initMetadataForClass(ClosedByteChannelException, 'ClosedByteChannelException', ClosedByteChannelException.m26);
initMetadataForClass(ClosedReadChannelException, 'ClosedReadChannelException', ClosedReadChannelException.b26);
initMetadataForClass(ClosedWriteChannelException, 'ClosedWriteChannelException', ClosedWriteChannelException.h26);
initMetadataForClass(LineEnding, 'LineEnding');
initMetadataForCompanion(Companion_2);
initMetadataForClass(LineEndingMode, 'LineEndingMode');
protoOf(SourceByteReadChannel).w26 = awaitContent$default;
initMetadataForClass(SourceByteReadChannel, 'SourceByteReadChannel', VOID, VOID, [ByteReadChannel], [1]);
initMetadataForClass(MalformedInputException, 'MalformedInputException');
initMetadataForClass(TooLongLineException, 'TooLongLineException');
initMetadataForInterface(ObjectPool, 'ObjectPool', VOID, VOID, [AutoCloseable]);
protoOf(DefaultPool).g6 = close;
initMetadataForClass(DefaultPool, 'DefaultPool', VOID, VOID, [ObjectPool]);
initMetadataForClass(ByteArrayPool$1);
protoOf(NoPoolImpl).g6 = close;
initMetadataForClass(NoPoolImpl, 'NoPoolImpl', VOID, VOID, [ObjectPool]);
initMetadataForCompanion(Companion_3);
initMetadataForClass(Charset, 'Charset');
initMetadataForObject(Charsets, 'Charsets');
initMetadataForClass(CharsetDecoder, 'CharsetDecoder');
initMetadataForClass(CharsetEncoder, 'CharsetEncoder');
initMetadataForClass(CharsetImpl, 'CharsetImpl');
initMetadataForClass(CharsetEncoderImpl, 'CharsetEncoderImpl');
initMetadataForClass(CharsetDecoderImpl, 'CharsetDecoderImpl');
initMetadataForClass(toKtor$1);
initMetadataForClass(TextDecoderFallback, 'TextDecoderFallback');
//endregion
//region block: init
Empty_instance = new Empty();
Companion_instance_2 = new Companion_1();
Companion_instance_4 = new Companion_3();
//endregion
//region block: exports
export {
  LineEndingMode__plus_impl_ttpz2j as LineEndingMode__plus_impl_ttpz2j3huakwvr4g74l,
  Charsets_getInstance as Charsets_getInstancezrqwjk0yrlyw,
  Companion_getInstance_0 as Companion_getInstance1wcuorfuztxv3,
  Companion_getInstance_2 as Companion_getInstance325d2evlpmaqk,
  copyTo as copyTo2t9ovypu2ttur,
  copyTo_0 as copyToi5ntzjcye2q9,
  readAvailable as readAvailable3nvldm7ch5xu5,
  readBuffer as readBuffery45c0omfxhxc,
  readLineStrictTo as readLineStrictTo1b1nwo5trwh7s,
  readPacket as readPacket290wjvm56mmfy,
  readUntil as readUntil2ls7o0c1kzd4x,
  skipIfFound as skipIfFoundarb6fjv6fvg7,
  toByteArray as toByteArraylei52b7x53md,
  writeFully as writeFully3k1eaua3t4bqd,
  writePacket as writePacket12ojj8o5tswuc,
  MalformedInputException as MalformedInputExceptionbvc6h5ij0ias,
  decode as decode1t43jmuxrxpmo,
  encode as encode35e4rpnc94tb5,
  forName as forName2faodmskqnoz5,
  get_name as get_name2f11g4r0d5pxp,
  BytePacketBuilder as BytePacketBuilder2biodf4wxvlba,
  ByteReadPacket as ByteReadPacket24tdckgvuvatn,
  build as buildjygoh729rhy8,
  canRead as canRead1guo8vbveth0f,
  discard_0 as discard3ugntd47xyll6,
  readText_0 as readText27783kyxjxi1g,
  get_remaining as get_remaining1lapv95kcmm0y,
  get_size as get_size2imoy2jq11jxl,
  takeWhile as takeWhile34751tcfg6owx,
  toByteArray_0 as toByteArray1i3ns5jnoqlv6,
  writeFully_0 as writeFully359t6q8kam2g5,
  writeText as writeText19qfzm98fbm4l,
  get_ByteArrayPool as get_ByteArrayPool3f7yrgvqxz9ct,
  DefaultPool as DefaultPool2gb1fm4epwgu9,
  NoPoolImpl as NoPoolImplgos9n8jphzjp,
  ByteChannel as ByteChannel3cxdguezl3lu7,
  ByteReadChannel_1 as ByteReadChannel2ndu7k90iil3s,
  ByteReadChannel_0 as ByteReadChannel1cb89sbyipkce,
  ByteReadChannel as ByteReadChannel2wzou76jce72d,
  ClosedByteChannelException as ClosedByteChannelException3il8gfpye60w,
  attachJob as attachJoby0kwkw2fc958,
  attachWriterJob as attachWriterJob2dr3tee7atoa9,
  cancel_0 as canceldn4b3cdqcfny,
  close_0 as close3semq7pafb42g,
  counted as counted3iniv3aql3f9n,
  invokeOnCompletion as invokeOnCompletion1ziuxzoag0iwj,
  ioDispatcher as ioDispatcher3b9pa3x0duop,
  readText as readText3frapgncbqrcg,
  rethrowCloseCauseIfNeeded_0 as rethrowCloseCauseIfNeeded3ixo8wl4o3bz4,
  writer as writer1eia5its2a1fh,
  writer_0 as writers3lcf9rq7qek,
};
//endregion

//# sourceMappingURL=ktor-ktor-io.mjs.map
