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
    this.q2g_1 = new Closed(null);
    var tmp = this;
    // Inline function 'kotlin.Companion.success' call
    tmp.r2g_1 = _Result___init__impl__xyqfz8(Unit_instance);
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
    this.s2g_1 = cause;
  }
  toString() {
    return 'Closed(cause=' + toString(this.s2g_1) + ')';
  }
  hashCode() {
    return this.s2g_1 == null ? 0 : hashCode(this.s2g_1);
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof Closed))
      return false;
    if (!equals(this.s2g_1, other.s2g_1))
      return false;
    return true;
  }
}
class Task {}
function resume() {
  return this.u2g().ac(Companion_getInstance().r2g_1);
}
function resume_0(throwable) {
  var tmp = this.u2g();
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
  return tmp.ac(tmp1_elvis_lhs == null ? Companion_getInstance().r2g_1 : tmp1_elvis_lhs.fr_1);
}
class Read {
  constructor(continuation) {
    this.y2g_1 = continuation;
    this.z2g_1 = null;
    if (get_DEVELOPMENT_MODE()) {
      var tmp = this;
      // Inline function 'kotlin.also' call
      var this_0 = newThrowable('ReadTask 0x' + toString_0(hashCode(this.y2g_1), 16));
      stackTraceToString(this_0);
      tmp.z2g_1 = this_0;
    }
  }
  u2g() {
    return this.y2g_1;
  }
  t2g() {
    return this.z2g_1;
  }
  v2g() {
    return 'read';
  }
}
class Write {
  constructor(continuation) {
    this.a2h_1 = continuation;
    this.b2h_1 = null;
    if (get_DEVELOPMENT_MODE()) {
      var tmp = this;
      // Inline function 'kotlin.also' call
      var this_0 = newThrowable('WriteTask 0x' + toString_0(hashCode(this.a2h_1), 16));
      stackTraceToString(this_0);
      tmp.b2h_1 = this_0;
    }
  }
  u2g() {
    return this.a2h_1;
  }
  t2g() {
    return this.b2h_1;
  }
  v2g() {
    return 'write';
  }
}
class sam$kotlinx_coroutines_DisposableHandle$0 {
  constructor(function_0) {
    this.m2h_1 = function_0;
  }
  b1w() {
    return this.m2h_1();
  }
  r4() {
    return this.m2h_1;
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
  return $super === VOID ? this.j2i(min, $completion) : $super.j2i.call(this, min, $completion);
}
class ByteWriteChannel {}
function get_autoFlush() {
  return false;
}
class ByteChannel {
  constructor(autoFlush) {
    autoFlush = autoFlush === VOID ? false : autoFlush;
    this.c2h_1 = autoFlush;
    this.d2h_1 = new Buffer();
    this.e2h_1 = 0;
    this.f2h_1 = new Object();
    this.g2h_1 = atomic$ref$1(Empty_instance);
    this.h2h_1 = new Buffer();
    this.i2h_1 = new Buffer();
    this.j2h_1 = atomic$ref$1(null);
    this.k2h_1 = atomic$ref$1(null);
  }
  n2h() {
    return this.c2h_1;
  }
  o2h() {
    return this.e2h_1 < 1048576;
  }
  p2h() {
    var tmp0_safe_receiver = this.j2h_1.kotlinx$atomicfu$value;
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
          tmp = access$_get_origin__hdh1qz(tmp0_safe_receiver).o1z();
        } else {
          if (tmp0_subject instanceof CancellationException) {
            tmp = CancellationException.p(access$_get_origin__hdh1qz(tmp0_safe_receiver).message, access$_get_origin__hdh1qz(tmp0_safe_receiver));
          } else {
            var p0 = access$_get_origin__hdh1qz(tmp0_safe_receiver);
            tmp = ClosedReadChannelException.u2h(p0);
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
    if (this.h2h_1.k2d()) {
      moveFlushToReadBuffer(this);
    }
    return this.h2h_1;
  }
  v2h() {
    if (this.b2i()) {
      var tmp0_safe_receiver = this.j2h_1.kotlinx$atomicfu$value;
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
            tmp_0 = access$_get_origin__hdh1qz(tmp0_safe_receiver).o1z();
          } else {
            if (tmp0_subject instanceof CancellationException) {
              tmp_0 = CancellationException.p(access$_get_origin__hdh1qz(tmp0_safe_receiver).message, access$_get_origin__hdh1qz(tmp0_safe_receiver));
            } else {
              var p0 = access$_get_origin__hdh1qz(tmp0_safe_receiver);
              tmp_0 = ClosedWriteChannelException.a2i(p0);
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
        throw ClosedWriteChannelException.a2i();
    }
    return this.i2h_1;
  }
  l2h() {
    var tmp0_safe_receiver = this.j2h_1.kotlinx$atomicfu$value;
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
          tmp_0 = access$_get_origin__hdh1qz(tmp0_safe_receiver).o1z();
        } else {
          if (tmp0_subject instanceof CancellationException) {
            tmp_0 = CancellationException.p(access$_get_origin__hdh1qz(tmp0_safe_receiver).message, access$_get_origin__hdh1qz(tmp0_safe_receiver));
          } else {
            var p0 = access$_get_origin__hdh1qz(tmp0_safe_receiver);
            tmp_0 = ClosedByteChannelException.f2i(p0);
          }
        }
      }
      tmp = tmp_0;
    }
    return tmp;
  }
  b2i() {
    return !(this.j2h_1.kotlinx$atomicfu$value == null);
  }
  g2i() {
    var tmp;
    var tmp0_safe_receiver = this.j2h_1.kotlinx$atomicfu$value;
    if ((tmp0_safe_receiver == null ? null : tmp0_safe_receiver.i2i()) === true) {
      tmp = true;
    } else {
      tmp = (this.b2i() && this.e2h_1 === 0 && this.h2h_1.k2d());
    }
    return tmp;
  }
  *j2i(min, $completion) {
    rethrowCloseCauseIfNeeded(this);
    if (this.h2h_1.j2() >= fromInt(min))
      return true;
    // Inline function 'io.ktor.utils.io.ByteChannel.sleepWhile' call
    var this_0 = this;
    $l$loop: while (add(numberToLong(this.e2h_1), this.h2h_1.j2()) < fromInt(min) && this.j2h_1.kotlinx$atomicfu$value == null) {
      // Inline function 'kotlinx.coroutines.suspendCancellableCoroutine' call
      // Inline function 'kotlin.js.suspendCoroutineUninterceptedOrReturnJS' call
      (yield () => {
        var uCont = $completion;
        var cancellable = new CancellableContinuationImpl(intercepted(uCont), 1);
        cancellable.i1x();
        var tmp0 = this_0;
        var tmp2 = new Read(cancellable);
        $l$block_0: {
          // Inline function 'io.ktor.utils.io.ByteChannel.trySuspend' call
          var previous = tmp0.g2h_1.kotlinx$atomicfu$value;
          if (!(previous instanceof Closed)) {
            if (!tmp0.g2h_1.atomicfu$compareAndSet(previous, tmp2)) {
              tmp2.w2g();
              break $l$block_0;
            }
          }
          if (previous instanceof Read) {
            previous.x2g(ConcurrentIOException.o2i(tmp2.v2g(), previous.t2g()));
          } else {
            if (isInterface(previous, Task)) {
              previous.w2g();
            } else {
              if (previous instanceof Closed) {
                tmp2.x2g(previous.s2g_1);
                break $l$block_0;
              } else {
                if (!equals(previous, Empty_instance)) {
                  noWhenBranchMatchedException();
                }
              }
            }
          }
          if (!(add(numberToLong(this.e2h_1), this.h2h_1.j2()) < fromInt(min) && this.j2h_1.kotlinx$atomicfu$value == null)) {
            // Inline function 'io.ktor.utils.io.ByteChannel.resumeSlot' call
            var current = tmp0.g2h_1.kotlinx$atomicfu$value;
            var tmp;
            if (current instanceof Read) {
              tmp = tmp0.g2h_1.atomicfu$compareAndSet(current, Empty_instance);
            } else {
              tmp = false;
            }
            if (tmp) {
              current.w2g();
            }
          }
        }
        return cancellable.q1x();
      });
    }
    rethrowCloseCauseIfNeeded(this);
    if (this.h2h_1.j2() < 1048576n) {
      moveFlushToReadBuffer(this);
    }
    return this.h2h_1.j2() >= fromInt(min);
  }
  *q2i($completion) {
    rethrowCloseCauseIfNeeded(this);
    this.r2i();
    if (this.o2h())
      return Unit_instance;
    // Inline function 'io.ktor.utils.io.ByteChannel.sleepWhile' call
    var this_0 = this;
    $l$loop: while (!this.o2h() && this.j2h_1.kotlinx$atomicfu$value == null) {
      // Inline function 'kotlinx.coroutines.suspendCancellableCoroutine' call
      // Inline function 'kotlin.js.suspendCoroutineUninterceptedOrReturnJS' call
      (yield () => {
        var uCont = $completion;
        var cancellable = new CancellableContinuationImpl(intercepted(uCont), 1);
        cancellable.i1x();
        var tmp0 = this_0;
        var tmp2 = new Write(cancellable);
        $l$block_0: {
          // Inline function 'io.ktor.utils.io.ByteChannel.trySuspend' call
          var previous = tmp0.g2h_1.kotlinx$atomicfu$value;
          if (!(previous instanceof Closed)) {
            if (!tmp0.g2h_1.atomicfu$compareAndSet(previous, tmp2)) {
              tmp2.w2g();
              break $l$block_0;
            }
          }
          if (previous instanceof Write) {
            previous.x2g(ConcurrentIOException.o2i(tmp2.v2g(), previous.t2g()));
          } else {
            if (isInterface(previous, Task)) {
              previous.w2g();
            } else {
              if (previous instanceof Closed) {
                tmp2.x2g(previous.s2g_1);
                break $l$block_0;
              } else {
                if (!equals(previous, Empty_instance)) {
                  noWhenBranchMatchedException();
                }
              }
            }
          }
          if (!(!this.o2h() && this.j2h_1.kotlinx$atomicfu$value == null)) {
            // Inline function 'io.ktor.utils.io.ByteChannel.resumeSlot' call
            var current = tmp0.g2h_1.kotlinx$atomicfu$value;
            var tmp;
            if (current instanceof Write) {
              tmp = tmp0.g2h_1.atomicfu$compareAndSet(current, Empty_instance);
            } else {
              tmp = false;
            }
            if (tmp) {
              current.w2g();
            }
          }
        }
        return cancellable.q1x();
      });
    }
    return Unit_instance;
  }
  r2i() {
    if (this.i2h_1.k2d())
      return Unit_instance;
    // Inline function 'io.ktor.utils.io.locks.synchronized' call
    this.f2h_1;
    var count = convertToInt(this.i2h_1.j2());
    this.d2h_1.c2f(this.i2h_1);
    this.e2h_1 = this.e2h_1 + count | 0;
    // Inline function 'io.ktor.utils.io.ByteChannel.resumeSlot' call
    var current = this.g2h_1.kotlinx$atomicfu$value;
    var tmp;
    if (current instanceof Read) {
      tmp = this.g2h_1.atomicfu$compareAndSet(current, Empty_instance);
    } else {
      tmp = false;
    }
    if (tmp) {
      current.w2g();
    }
  }
  g6() {
    this.r2i();
    if (!this.j2h_1.atomicfu$compareAndSet(null, get_CLOSED()))
      return Unit_instance;
    closeSlot(this, null);
  }
  *s2i($completion) {
    // Inline function 'kotlin.runCatching' call
    var tmp;
    try {
      yield* this.q2i($completion);
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
    if (!this.j2h_1.atomicfu$compareAndSet(null, get_CLOSED()))
      return Unit_instance;
    closeSlot(this, null);
    return Unit_instance;
  }
  t2i(cause) {
    if (!(this.j2h_1.kotlinx$atomicfu$value == null))
      return Unit_instance;
    var closedToken = new CloseToken(cause);
    this.j2h_1.atomicfu$compareAndSet(null, closedToken);
    // Inline function 'io.ktor.utils.io.Companion.wrapCause' call
    var tmp0_subject = access$_get_origin__hdh1qz(closedToken);
    var tmp;
    if (tmp0_subject == null) {
      tmp = null;
    } else {
      if (isInterface(tmp0_subject, CopyableThrowable)) {
        tmp = access$_get_origin__hdh1qz(closedToken).o1z();
      } else {
        if (tmp0_subject instanceof CancellationException) {
          tmp = CancellationException.p(access$_get_origin__hdh1qz(closedToken).message, access$_get_origin__hdh1qz(closedToken));
        } else {
          var p0 = access$_get_origin__hdh1qz(closedToken);
          tmp = ClosedByteChannelException.f2i(p0);
        }
      }
    }
    var wrappedCause = tmp;
    closeSlot(this, wrappedCause);
  }
  u2i(handler) {
    var existingCause = this.j2h_1.kotlinx$atomicfu$value;
    if (!(existingCause == null)) {
      // Inline function 'io.ktor.utils.io.Companion.wrapCause' call
      var tmp0_subject = access$_get_origin__hdh1qz(existingCause);
      var tmp;
      if (tmp0_subject == null) {
        tmp = null;
      } else {
        if (isInterface(tmp0_subject, CopyableThrowable)) {
          tmp = access$_get_origin__hdh1qz(existingCause).o1z();
        } else {
          if (tmp0_subject instanceof CancellationException) {
            tmp = CancellationException.p(access$_get_origin__hdh1qz(existingCause).message, access$_get_origin__hdh1qz(existingCause));
          } else {
            var p0 = access$_get_origin__hdh1qz(existingCause);
            tmp = ClosedByteChannelException.f2i(p0);
          }
        }
      }
      handler(tmp);
      var tmp_0 = ByteChannel$invokeOnClose$lambda;
      return new sam$kotlinx_coroutines_DisposableHandle$0(tmp_0);
    }
    if (!this.k2h_1.atomicfu$compareAndSet(null, handler)) {
      // Inline function 'kotlin.error' call
      var message = 'Only one invokeOnClose handler is supported per channel';
      throw IllegalStateException.o(toString_1(message));
    }
    var cause = this.j2h_1.kotlinx$atomicfu$value;
    if (!(cause == null) && this.k2h_1.atomicfu$compareAndSet(handler, null)) {
      // Inline function 'io.ktor.utils.io.Companion.wrapCause' call
      var tmp0_subject_0 = access$_get_origin__hdh1qz(cause);
      var tmp_1;
      if (tmp0_subject_0 == null) {
        tmp_1 = null;
      } else {
        if (isInterface(tmp0_subject_0, CopyableThrowable)) {
          tmp_1 = access$_get_origin__hdh1qz(cause).o1z();
        } else {
          if (tmp0_subject_0 instanceof CancellationException) {
            tmp_1 = CancellationException.p(access$_get_origin__hdh1qz(cause).message, access$_get_origin__hdh1qz(cause));
          } else {
            var p0_0 = access$_get_origin__hdh1qz(cause);
            tmp_1 = ClosedByteChannelException.f2i(p0_0);
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
    return new.target.o2i(taskName, cause);
  }
  static o2i(taskName, cause) {
    cause = cause === VOID ? null : cause;
    var $this = this.q('Concurrent ' + taskName + ' attempts', cause);
    captureStack($this, $this.n2i_1);
    return $this;
  }
}
class ByteChannelScanner {
  constructor(channel, matchString, writeChannel, limit) {
    limit = limit === VOID ? 9223372036854775807n : limit;
    this.v2i_1 = channel;
    this.w2i_1 = matchString;
    this.x2i_1 = writeChannel;
    this.y2i_1 = limit;
    // Inline function 'kotlin.require' call
    if (!(this.w2i_1.j2() > 0)) {
      var message = 'Empty match string not permitted for scanning';
      throw IllegalArgumentException.i2(toString_1(message));
    }
    this.z2i_1 = this.v2i_1.p2h();
    this.a2j_1 = buildPartialMatchTable(this);
    this.b2j_1 = new Buffer();
    this.c2j_1 = 0n;
    this.d2j_1 = 0;
  }
  *e2j(ignoreMissing, $completion) {
    this.c2j_1 = 0n;
    while (!this.z2i_1.k2d() || (yield* this.v2i_1.p2i(VOID, $completion))) {
      yield* /*#__NOINLINE__*/advanceToNextPotentialMatch(this, $completion);
      if (yield* /*#__NOINLINE__*/checkFullMatch(this, $completion)) {
        return this.c2j_1;
      }
    }
    if (!ignoreMissing) {
      throw IOException.n2g('Expected "' + toSingleLineString(this, this.w2i_1) + '" but encountered end of input');
    }
    this.c2j_1 = add(this.c2j_1, this.b2j_1.n2e(this.x2i_1.v2h()));
    yield* this.x2i_1.q2i($completion);
    return this.c2j_1;
  }
}
class ByteReadChannel$Companion$Empty$1 {
  constructor() {
    this.h2j_1 = null;
    this.i2j_1 = new Buffer();
  }
  l2h() {
    return this.h2j_1;
  }
  g2i() {
    return true;
  }
  p2h() {
    return this.i2j_1;
  }
  *j2i(min, $completion) {
    return false;
  }
  t2i(cause) {
  }
}
class Companion_0 {
  constructor() {
    Companion_instance_1 = this;
    var tmp = this;
    tmp.j2j_1 = new ByteReadChannel$Companion$Empty$1();
  }
}
class WriterJob {
  constructor(channel, job) {
    this.f2j_1 = channel;
    this.g2j_1 = job;
  }
  r1y() {
    return this.g2j_1;
  }
}
class WriterScope {
  constructor(channel, coroutineContext) {
    this.p2j_1 = channel;
    this.q2j_1 = coroutineContext;
  }
  y1s() {
    return this.q2j_1;
  }
}
class NO_CALLBACK$1 {
  constructor() {
    this.r2j_1 = EmptyCoroutineContext_instance;
  }
  yb() {
    return this.r2j_1;
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
    this.h2i_1 = origin;
  }
  i2i() {
    return !(this.h2i_1 == null);
  }
}
class CountedByteReadChannel {
  constructor(delegate) {
    this.s2j_1 = delegate;
    this.t2j_1 = new Buffer();
    this.u2j_1 = 0n;
    this.v2j_1 = 0n;
  }
  w2j() {
    updateConsumed(this);
    return this.v2j_1;
  }
  l2h() {
    return this.s2j_1.l2h();
  }
  g2i() {
    return this.t2j_1.k2d() && this.s2j_1.g2i();
  }
  p2h() {
    transferFromDelegate(this);
    return this.t2j_1;
  }
  *j2i(min, $completion) {
    if (this.p2h().j2() >= fromInt(min)) {
      return true;
    }
    if (yield* this.s2j_1.j2i(min, $completion)) {
      transferFromDelegate(this);
      return true;
    }
    return false;
  }
  t2i(cause) {
    this.s2j_1.t2i(cause);
    this.t2j_1.g6();
  }
}
class ClosedByteChannelException extends IOException {
  constructor(cause) {
    return new.target.f2i(cause);
  }
  static f2i(cause) {
    cause = cause === VOID ? null : cause;
    var $this = this.o2g(cause == null ? null : cause.message, cause);
    captureStack($this, $this.e2i_1);
    return $this;
  }
}
class ClosedReadChannelException extends ClosedByteChannelException {
  constructor(cause) {
    return new.target.u2h(cause);
  }
  static u2h(cause) {
    cause = cause === VOID ? null : cause;
    var $this = this.f2i(cause);
    captureStack($this, $this.t2h_1);
    return $this;
  }
}
class ClosedWriteChannelException extends ClosedByteChannelException {
  constructor(cause) {
    return new.target.a2i(cause);
  }
  static a2i(cause) {
    cause = cause === VOID ? null : cause;
    var $this = this.f2i(cause);
    captureStack($this, $this.z2h_1);
    return $this;
  }
}
class LineEnding extends Enum {}
class Companion_2 {
  constructor() {
    Companion_instance_3 = this;
    this.x2j_1 = _LineEndingMode___init__impl__jo5bul(1);
    this.y2j_1 = _LineEndingMode___init__impl__jo5bul(2);
    this.z2j_1 = _LineEndingMode___init__impl__jo5bul(4);
    this.a2k_1 = _LineEndingMode___init__impl__jo5bul(7);
    this.b2k_1 = listOf([new LineEndingMode(this.x2j_1), new LineEndingMode(this.y2j_1), new LineEndingMode(this.z2j_1)]);
  }
}
class LineEndingMode {
  constructor(mode) {
    Companion_getInstance_2();
    this.c2k_1 = mode;
  }
  toString() {
    return LineEndingMode__toString_impl_j4h76r(this.c2k_1);
  }
  hashCode() {
    return LineEndingMode__hashCode_impl_2mopm4(this.c2k_1);
  }
  equals(other) {
    return LineEndingMode__equals_impl_qyr4nk(this.c2k_1, other);
  }
}
class SourceByteReadChannel {
  constructor(source) {
    this.d2k_1 = source;
    this.e2k_1 = null;
  }
  l2h() {
    var tmp0_safe_receiver = this.e2k_1;
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
          tmp_0 = access$_get_origin__hdh1qz(tmp0_safe_receiver).o1z();
        } else {
          if (tmp0_subject instanceof CancellationException) {
            tmp_0 = CancellationException.p(access$_get_origin__hdh1qz(tmp0_safe_receiver).message, access$_get_origin__hdh1qz(tmp0_safe_receiver));
          } else {
            var p0 = access$_get_origin__hdh1qz(tmp0_safe_receiver);
            tmp_0 = ClosedByteChannelException.f2i(p0);
          }
        }
      }
      tmp = tmp_0;
    }
    return tmp;
  }
  g2i() {
    return this.d2k_1.k2d();
  }
  p2h() {
    var tmp0_safe_receiver = this.l2h();
    if (tmp0_safe_receiver == null)
      null;
    else {
      // Inline function 'kotlin.let' call
      throw tmp0_safe_receiver;
    }
    return this.d2k_1.j2d();
  }
  *j2i(min, $completion) {
    var tmp0_safe_receiver = this.l2h();
    if (tmp0_safe_receiver == null)
      null;
    else {
      // Inline function 'kotlin.let' call
      throw tmp0_safe_receiver;
    }
    return this.d2k_1.m2d(fromInt(min));
  }
  t2i(cause) {
    if (!(this.e2k_1 == null))
      return Unit_instance;
    this.d2k_1.g6();
    var tmp = this;
    var tmp1_elvis_lhs = cause == null ? null : cause.message;
    tmp.e2k_1 = new CloseToken(IOException.o2g(tmp1_elvis_lhs == null ? 'Channel was cancelled' : tmp1_elvis_lhs, cause));
  }
}
class MalformedInputException extends IOException {
  constructor(message) {
    return new.target.i2k(message);
  }
  static i2k(message) {
    var $this = this.n2g(message);
    captureStack($this, $this.h2k_1);
    return $this;
  }
}
class TooLongLineException extends MalformedInputException {
  constructor(message) {
    return new.target.o2j(message);
  }
  static o2j(message) {
    var $this = this.i2k(message);
    captureStack($this, $this.n2j_1);
    return $this;
  }
}
class ObjectPool {}
function close() {
  this.b1w();
}
class DefaultPool {
  constructor(capacity) {
    this.r2k_1 = capacity;
    var tmp = this;
    // Inline function 'kotlin.arrayOfNulls' call
    var size = this.r2k_1;
    tmp.s2k_1 = Array(size);
    this.t2k_1 = 0;
  }
  u2k(instance) {
  }
  v2k(instance) {
    return instance;
  }
  w2k(instance) {
  }
  x2k() {
    if (this.t2k_1 === 0)
      return this.q2k();
    this.t2k_1 = this.t2k_1 - 1 | 0;
    var idx = this.t2k_1;
    var tmp = this.s2k_1[idx];
    var instance = !(tmp == null) ? tmp : THROW_CCE();
    this.s2k_1[idx] = null;
    return this.v2k(instance);
  }
  y2k(instance) {
    this.w2k(instance);
    if (this.t2k_1 === this.r2k_1) {
      this.u2k(instance);
    } else {
      var _unary__edvuaz = this.t2k_1;
      this.t2k_1 = _unary__edvuaz + 1 | 0;
      this.s2k_1[_unary__edvuaz] = instance;
    }
  }
  b1w() {
    var inductionVariable = 0;
    var last = this.t2k_1;
    if (inductionVariable < last)
      do {
        var i = inductionVariable;
        inductionVariable = inductionVariable + 1 | 0;
        var tmp = this.s2k_1[i];
        var instance = !(tmp == null) ? tmp : THROW_CCE();
        this.s2k_1[i] = null;
        this.u2k(instance);
      }
       while (inductionVariable < last);
    this.t2k_1 = 0;
  }
}
class ByteArrayPool$1 extends DefaultPool {
  constructor() {
    super(128);
  }
  q2k() {
    return new Int8Array(4096);
  }
}
class NoPoolImpl {
  y2k(instance) {
    return Unit_instance;
  }
  b1w() {
    return Unit_instance;
  }
}
class Companion_3 {
  z2k(name) {
    switch (name) {
      case 'UTF-8':
      case 'utf-8':
      case 'UTF8':
      case 'utf8':
        return Charsets_getInstance().j2k_1;
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
      return Charsets_getInstance().k2k_1;
    }
    throw IllegalArgumentException.i2('Charset ' + name + ' is not supported');
  }
}
class Charset {
  constructor(_name) {
    this.l2k_1 = _name;
  }
  equals(other) {
    if (this === other)
      return true;
    if (other == null || !(this.constructor == other.constructor))
      return false;
    if (!(other instanceof Charset))
      THROW_CCE();
    return this.l2k_1 === other.l2k_1;
  }
  hashCode() {
    return getStringHashCode(this.l2k_1);
  }
  toString() {
    return this.l2k_1;
  }
}
class Charsets {
  constructor() {
    Charsets_instance = this;
    this.j2k_1 = new CharsetImpl('UTF-8');
    this.k2k_1 = new CharsetImpl('ISO-8859-1');
  }
}
class CharsetDecoder {
  constructor(_charset) {
    this.b2l_1 = _charset;
  }
}
class CharsetEncoder {
  constructor(_charset) {
    this.c2l_1 = _charset;
  }
}
class CharsetImpl extends Charset {
  m2k() {
    return new CharsetEncoderImpl(this);
  }
  a2l() {
    return new CharsetDecoderImpl(this);
  }
}
class CharsetEncoderImpl extends CharsetEncoder {
  constructor(charset) {
    super(charset);
    this.g2l_1 = charset;
  }
  toString() {
    return 'CharsetEncoderImpl(charset=' + this.g2l_1.toString() + ')';
  }
  hashCode() {
    return this.g2l_1.hashCode();
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof CharsetEncoderImpl))
      return false;
    if (!this.g2l_1.equals(other.g2l_1))
      return false;
    return true;
  }
}
class CharsetDecoderImpl extends CharsetDecoder {
  constructor(charset) {
    super(charset);
    this.i2l_1 = charset;
  }
  toString() {
    return 'CharsetDecoderImpl(charset=' + this.i2l_1.toString() + ')';
  }
  hashCode() {
    return this.i2l_1.hashCode();
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof CharsetDecoderImpl))
      return false;
    if (!this.i2l_1.equals(other.i2l_1))
      return false;
    return true;
  }
}
class toKtor$1 {
  constructor($this_toKtor) {
    this.j2l_1 = $this_toKtor;
  }
  d2l(buffer) {
    return this.j2l_1.decode(buffer);
  }
}
class TextDecoderFallback {
  constructor(encoding, fatal) {
    this.k2l_1 = fatal;
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
  d2l(buffer) {
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
          if (!!this.k2l_1) {
            var message = 'Invalid character: ' + point;
            throw IllegalStateException.o(toString_1(message));
          }
          writeFully_0(builder, get_REPLACEMENT());
          continue $l$loop;
        }
        if (point > 255) {
          builder.d2f(toByte(point >> 8));
        }
        builder.d2f(toByte(point & 255));
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
  var tmp0_safe_receiver = $this.l2h();
  if (tmp0_safe_receiver == null)
    null;
  else {
    // Inline function 'kotlin.let' call
    throw tmp0_safe_receiver;
  }
}
function moveFlushToReadBuffer($this) {
  // Inline function 'io.ktor.utils.io.locks.synchronized' call
  $this.f2h_1;
  $this.d2h_1.n2e($this.h2h_1);
  $this.e2h_1 = 0;
  // Inline function 'io.ktor.utils.io.ByteChannel.resumeSlot' call
  var current = $this.g2h_1.kotlinx$atomicfu$value;
  var tmp;
  if (current instanceof Write) {
    tmp = $this.g2h_1.atomicfu$compareAndSet(current, Empty_instance);
  } else {
    tmp = false;
  }
  if (tmp) {
    current.w2g();
  }
}
function closeSlot($this, cause) {
  var closeContinuation = !(cause == null) ? new Closed(cause) : Companion_getInstance().q2g_1;
  var continuation = $this.g2h_1.atomicfu$getAndSet(closeContinuation);
  if (isInterface(continuation, Task)) {
    continuation.x2g(cause);
  }
  var tmp0_safe_receiver = $this.k2h_1.atomicfu$getAndSet(null);
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
    this$0.k2h_1.atomicfu$compareAndSet($handler, null);
    return Unit_instance;
  };
}
function ByteReadChannel_0(content, offset, length) {
  offset = offset === VOID ? 0 : offset;
  length = length === VOID ? content.length : length;
  // Inline function 'kotlin.also' call
  var this_0 = new Buffer();
  this_0.t2e(content, offset, offset + length | 0);
  var source = this_0;
  return ByteReadChannel_1(source);
}
function ByteReadChannel_1(source) {
  return new SourceByteReadChannel(source);
}
function buildPartialMatchTable($this) {
  var table = new Int32Array($this.w2i_1.j2());
  var j = 0;
  var inductionVariable = 1;
  var last = $this.w2i_1.j2();
  if (inductionVariable < last)
    do {
      var i = inductionVariable;
      inductionVariable = inductionVariable + 1 | 0;
      while (j > 0 && !($this.w2i_1.l2(i) === $this.w2i_1.l2(j))) {
        j = table[j - 1 | 0];
      }
      if ($this.w2i_1.l2(i) === $this.w2i_1.l2(j)) {
        j = j + 1 | 0;
      }
      table[i] = j;
    }
     while (inductionVariable < last);
  return table;
}
function *advanceToNextPotentialMatch($this, $completion) {
  while (!$this.z2i_1.k2d() || (yield* $this.v2i_1.p2i(VOID, $completion))) {
    var nextMatch = indexOf($this.z2i_1, $this.w2i_1.l2(0));
    if (nextMatch === -1n) {
      var tmp = $this.z2i_1;
      checkBounds($this, (tmp instanceof Buffer ? tmp : THROW_CCE()).j2());
      $this.c2j_1 = add($this.c2j_1, $this.z2i_1.n2e($this.x2i_1.v2h()));
      yield* flushIfNeeded($this.x2i_1, $completion);
    } else {
      checkBounds($this, nextMatch);
      var tmp_0 = $this;
      var tmp_1 = $this.c2j_1;
      var tmp_2 = $this.x2i_1.v2h();
      tmp_0.c2j_1 = add(tmp_1, $this.z2i_1.k2e(tmp_2 instanceof Buffer ? tmp_2 : THROW_CCE(), nextMatch));
      yield* flushIfNeeded($this.x2i_1, $completion);
      return Unit_instance;
    }
  }
  return Unit_instance;
}
function *checkFullMatch($this, $completion) {
  while (!$this.z2i_1.k2d() || (yield* $this.v2i_1.p2i(VOID, $completion))) {
    var byte = $this.z2i_1.n2d();
    if ($this.d2j_1 > 0 && !(byte === $this.w2i_1.l2($this.d2j_1))) {
      var oldMatchIndex = $this.d2j_1;
      while ($this.d2j_1 > 0 && !(byte === $this.w2i_1.l2($this.d2j_1))) {
        $this.d2j_1 = $this.a2j_1[$this.d2j_1 - 1 | 0];
      }
      var retained = fromInt(oldMatchIndex - $this.d2j_1 | 0);
      checkBounds($this, retained);
      var tmp = $this;
      var tmp_0 = $this.c2j_1;
      var tmp_1 = $this.x2i_1.v2h();
      tmp.c2j_1 = add(tmp_0, $this.b2j_1.k2e(tmp_1 instanceof Buffer ? tmp_1 : THROW_CCE(), retained));
      if ($this.d2j_1 === 0 && !(byte === $this.w2i_1.l2($this.d2j_1))) {
        yield* writeByte($this.x2i_1, byte, $completion);
        var _unary__edvuaz = $this.c2j_1;
        $this.c2j_1 = add(_unary__edvuaz, get_ONE());
        return false;
      }
    }
    $this.d2j_1 = $this.d2j_1 + 1 | 0;
    if ($this.d2j_1 === $this.w2i_1.j2()) {
      return true;
    }
    $this.b2j_1.d2f(byte);
  }
  return false;
}
function checkBounds($this, extra) {
  if (add($this.c2j_1, extra) > $this.y2i_1) {
    throw IOException.n2g('Limit of ' + $this.y2i_1.toString() + ' bytes exceeded ' + ('while searching for "' + toSingleLineString($this, $this.w2i_1) + '"'));
  }
}
function toSingleLineString($this, $receiver) {
  return replace(decodeToString_0($receiver), '\n', '\\n');
}
function attachJob(_this__u8e3s4, job) {
  job.d1u(true, VOID, attachJob$lambda(_this__u8e3s4));
}
function attachWriterJob(_this__u8e3s4, writerJob) {
  var tmp = writerJob.f2j_1;
  var tmp0_safe_receiver = tmp instanceof ByteChannel ? tmp : null;
  if (tmp0_safe_receiver == null)
    null;
  else {
    tmp0_safe_receiver.u2i(attachWriterJob$lambda(_this__u8e3s4));
  }
}
function attachJob$lambda($this_attachJob) {
  return (it) => {
    var tmp;
    if (!(it == null)) {
      $this_attachJob.t2i(it);
      tmp = Unit_instance;
    }
    return Unit_instance;
  };
}
function attachWriterJob$lambda($this_attachWriterJob) {
  return (cause) => {
    var tmp;
    if (!(cause == null)) {
      $this_attachWriterJob.t2i(cause);
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
  _this__u8e3s4.t2i(IOException.n2g('Channel was cancelled'));
}
function *readBuffer(_this__u8e3s4, $completion) {
  var result = new Buffer();
  while (!_this__u8e3s4.g2i()) {
    if (_this__u8e3s4.p2h().k2d()) {
      yield* _this__u8e3s4.p2i(VOID, $completion);
    }
    result.c2f(_this__u8e3s4.p2h());
  }
  rethrowCloseCauseIfNeeded_0(_this__u8e3s4);
  return result;
}
function *readAvailable(_this__u8e3s4, buffer, offset, length, $completion) {
  offset = offset === VOID ? 0 : offset;
  length = length === VOID ? buffer.length - offset | 0 : length;
  if (_this__u8e3s4.g2i())
    return -1;
  if (_this__u8e3s4.p2h().k2d()) {
    yield* _this__u8e3s4.p2i(VOID, $completion);
  }
  if (_this__u8e3s4.g2i())
    return -1;
  return readAvailable_0(_this__u8e3s4.p2h(), buffer, offset, length);
}
function *toByteArray(_this__u8e3s4, $completion) {
  return readBytes(yield* /*#__NOINLINE__*/readBuffer(_this__u8e3s4, $completion));
}
function *copyTo(_this__u8e3s4, channel, limit, $completion) {
  var remaining = limit;
  try {
    while (!_this__u8e3s4.g2i() && remaining > 0n) {
      if (_this__u8e3s4.p2h().k2d()) {
        yield* _this__u8e3s4.p2i(VOID, $completion);
      }
      var tmp0 = remaining;
      // Inline function 'kotlin.comparisons.minOf' call
      var b = get_remaining(_this__u8e3s4.p2h());
      var count = tmp0 <= b ? tmp0 : b;
      _this__u8e3s4.p2h().m2e(channel.v2h(), count);
      remaining = subtract(remaining, count);
      yield* channel.q2i($completion);
    }
  } catch ($p) {
    if ($p instanceof Error) {
      var cause = $p;
      _this__u8e3s4.t2i(cause);
      close_0(channel, cause);
      throw cause;
    } else {
      throw $p;
    }
  }
  finally {
    yield* channel.q2i($completion);
  }
  return subtract(limit, remaining);
}
function rethrowCloseCauseIfNeeded_0(_this__u8e3s4) {
  var tmp0_safe_receiver = _this__u8e3s4.l2h();
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
  while (remaining > 0n && !_this__u8e3s4.g2i()) {
    if (get_availableForRead(_this__u8e3s4) === 0) {
      yield* _this__u8e3s4.p2i(VOID, $completion);
    }
    var tmp0 = remaining;
    // Inline function 'kotlin.comparisons.minOf' call
    var b = get_remaining(_this__u8e3s4.p2h());
    var count = tmp0 <= b ? tmp0 : b;
    discard_0(_this__u8e3s4.p2h(), count);
    remaining = subtract(remaining, count);
  }
  return subtract(max, remaining);
}
function *copyTo_0(_this__u8e3s4, channel, $completion) {
  var result = 0n;
  try {
    while (!_this__u8e3s4.g2i()) {
      result = add(result, _this__u8e3s4.p2h().n2e(channel.v2h()));
      yield* channel.q2i($completion);
      yield* _this__u8e3s4.p2i(VOID, $completion);
    }
  } catch ($p) {
    if ($p instanceof Error) {
      var cause = $p;
      _this__u8e3s4.t2i(cause);
      close_0(channel, cause);
      throw cause;
    } else {
      throw $p;
    }
  }
  finally {
    yield* channel.q2i($completion);
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
    if (_this__u8e3s4.p2h().k2d()) {
      yield* _this__u8e3s4.p2i(VOID, $completion);
    }
    if (_this__u8e3s4.g2i())
      break $l$loop;
    if (get_remaining(_this__u8e3s4.p2h()) > subtract(numberToLong(packet), result.j2())) {
      _this__u8e3s4.p2h().m2e(result, subtract(numberToLong(packet), result.j2()));
    } else {
      _this__u8e3s4.p2h().n2e(result);
    }
  }
  if (result.j2() < fromInt(packet)) {
    throw EOFException.i2d('Not enough data available, required ' + packet + ' bytes but only ' + result.j2().toString() + ' available');
  }
  return result;
}
function *readUntil(_this__u8e3s4, matchString, writeChannel, limit, ignoreMissing, $completion) {
  limit = limit === VOID ? 9223372036854775807n : limit;
  ignoreMissing = ignoreMissing === VOID ? false : ignoreMissing;
  return yield* (new ByteChannelScanner(_this__u8e3s4, matchString, writeChannel, limit)).e2j(ignoreMissing, $completion);
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
  return convertToInt(_this__u8e3s4.p2h().j2d().j2());
}
function *peek(_this__u8e3s4, count, $completion) {
  if (_this__u8e3s4.g2i())
    return null;
  if (!(yield* _this__u8e3s4.j2i(count, $completion)))
    return null;
  return readByteString(_this__u8e3s4.p2h().o2e(), count);
}
function *internalReadLineTo(_this__u8e3s4, out, limit, lenientLineEnding, throwOnIncompleteLine, $completion) {
  var readBuffer = _this__u8e3s4.p2h();
  if (readBuffer.k2d()) {
    yield* _this__u8e3s4.p2i(VOID, $completion);
  }
  if (_this__u8e3s4.g2i())
    return -1n;
  var consumed = {_v: 0n};
  var outBuffer = {_v: null};
  try {
    $l$loop: while (consumed._v < limit && !_this__u8e3s4.g2i()) {
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
        var tmp_0 = readBuffer.j2d();
        // Inline function 'kotlin.Long.minus' call
        var tmp$ret$0 = subtract(lfIndex, fromInt(1));
        if (tmp_0.f2e(tmp$ret$0) === 13) {
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
      var tmp_1 = readBuffer.j2d();
      // Inline function 'kotlin.Long.minus' call
      var tmp$ret$2 = subtract(count, fromInt(1));
      if (tmp_1.f2e(tmp$ret$2) === 13) {
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
      if (consumed._v < limit && get_remaining(readBuffer) === 0n && !(yield* _this__u8e3s4.p2i(VOID, $completion)))
        break $l$loop;
    }
    if (consumed._v === 0n && _this__u8e3s4.g2i())
      return -1n;
    // Inline function 'kotlin.check' call
    if (!(consumed._v <= limit)) {
      var message = 'Consumed bytes exceed the limit: ' + consumed._v.toString() + ' > ' + limit.toString() + ". It's an implementation bug, please report it.";
      throw IllegalStateException.o(toString_1(message));
    }
    if (consumed._v === limit) {
      if (limit === 9223372036854775807n)
        throw TooLongLineException.o2j('Max line length exceeded');
      if (get_remaining(readBuffer) === 0n && !(yield* _this__u8e3s4.p2i(VOID, $completion))) {
        throwEndOfStreamException(consumed._v);
      }
      var tmp0_subject = readBuffer.j2d().f2e(0n);
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
  throw EOFException.i2d('Unexpected end of stream after reading ' + consumed.toString() + ' bytes');
}
function throwTooLongLineException(limit) {
  throw TooLongLineException.o2j('Line exceeds limit of ' + limit.toString() + ' bytes');
}
function rethrowCloseCauseIfNeeded_1(_this__u8e3s4) {
  var tmp0_safe_receiver = _this__u8e3s4.l2h();
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
    outBuffer._v.x2e(readBuffer, count);
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
  if ((get_remaining(_this__u8e3s4) >= 2n || (yield* $this_internalReadLineTo.j2i(2, $completion))) && _this__u8e3s4.j2d().f2e(1n) === 10) {
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
  if (_this__u8e3s4.n2h() || get_size(_this__u8e3s4.v2h()) >= 1048576)
    yield* _this__u8e3s4.q2i($completion);
  return Unit_instance;
}
function ByteWriteChannel$flushAndClose$ref(p0) {
  return constructCallableReference(($completion) => orPromise($completion, function *($completion) {
    return yield* p0.s2i($completion);
  }), 0, 1, 18, 'flushAndClose', [p0]);
}
function get_NO_CALLBACK() {
  _init_properties_ByteWriteChannelOperations_kt__i7slrs();
  return NO_CALLBACK;
}
var NO_CALLBACK;
function *writeFully(_this__u8e3s4, value, startIndex, endIndex, $completion) {
  startIndex = startIndex === VOID ? 0 : startIndex;
  endIndex = endIndex === VOID ? value.length : endIndex;
  _this__u8e3s4.v2h().t2e(value, startIndex, endIndex);
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
  this_0.b1u(writer$lambda(channel));
  var job = this_0;
  return new WriterJob(channel, job);
}
function invokeOnCompletion(_this__u8e3s4, block) {
  _init_properties_ByteWriteChannelOperations_kt__i7slrs();
  return _this__u8e3s4.r1y().b1u(block);
}
function close_0(_this__u8e3s4, cause) {
  _init_properties_ByteWriteChannelOperations_kt__i7slrs();
  if (cause == null) {
    fireAndForget(ByteWriteChannel$flushAndClose$ref(_this__u8e3s4));
  } else {
    _this__u8e3s4.t2i(cause);
  }
}
function *writePacket(_this__u8e3s4, source, $completion) {
  while (!source.k2d()) {
    _this__u8e3s4.v2h().x2e(source, get_remaining(source));
    yield* flushIfNeeded(_this__u8e3s4, $completion);
  }
  return Unit_instance;
}
function fireAndForget(_this__u8e3s4) {
  _init_properties_ByteWriteChannelOperations_kt__i7slrs();
  startCoroutineCancellable(_this__u8e3s4, get_NO_CALLBACK());
}
function *writeByte(_this__u8e3s4, value, $completion) {
  _this__u8e3s4.v2h().d2f(value);
  yield* flushIfNeeded(_this__u8e3s4, $completion);
  return Unit_instance;
}
function writer$slambda($block, $channel) {
  return constructCallableReference(($this$launch, $completion) => orPromise($completion, function *($completion) {
    var nested = Job(get_job($this$launch.y1s()));
    var tmp;
    try {
      yield* $($block(new WriterScope($channel, $this$launch.y1s().jk(nested)), $completion), $completion);
      nested.d1z();
      var tmp_0;
      if (get_job($this$launch.y1s()).v1t()) {
        $channel.t2i(get_job($this$launch.y1s()).y1t());
        tmp_0 = Unit_instance;
      }
      tmp = tmp_0;
    } catch ($p) {
      var tmp_1;
      if ($p instanceof Error) {
        var cause = $p;
        cancel(nested, 'Exception thrown while writing to channel', cause);
        $channel.t2i(cause);
        tmp_1 = Unit_instance;
      } else {
        throw $p;
      }
      tmp = tmp_1;
    }
    finally {
      yield* nested.f1u($completion);
      // Inline function 'kotlin.runCatching' call
      var tmp_2;
      try {
        yield* $channel.s2i($completion);
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
    if (!(it == null) && !$channel.b2i()) {
      $channel.t2i(it);
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
  return $this.h2i_1;
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
  var appended = $this.t2j_1.c2f($this.s2j_1.p2h());
  $this.u2j_1 = add($this.u2j_1, appended);
}
function updateConsumed($this) {
  $this.v2j_1 = add($this.v2j_1, subtract($this.u2j_1, $this.t2j_1.j2()));
  $this.u2j_1 = $this.t2j_1.j2();
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
  if (equals(new LineEndingMode($this), new LineEndingMode(Companion_getInstance_2().x2j_1))) {
    tmp = 'CR';
  } else if (equals(new LineEndingMode($this), new LineEndingMode(Companion_getInstance_2().y2j_1))) {
    tmp = 'LF';
  } else if (equals(new LineEndingMode($this), new LineEndingMode(Companion_getInstance_2().z2j_1))) {
    tmp = 'CRLF';
  } else {
    // Inline function 'kotlin.collections.filter' call
    var tmp0 = Companion_getInstance_2().b2k_1;
    // Inline function 'kotlin.collections.filterTo' call
    var destination = ArrayList.p2();
    var _iterator__ex2g4s = tmp0.l1();
    while (_iterator__ex2g4s.m1()) {
      var element = _iterator__ex2g4s.n1();
      var it = element.c2k_1;
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
  if (!($this === other.c2k_1))
    return false;
  return true;
}
function decode(_this__u8e3s4, input, max) {
  max = max === VOID ? 2147483647 : max;
  var tmp0 = fromInt(max);
  // Inline function 'kotlin.comparisons.minOf' call
  var b = input.j2d().j2();
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
  return !_this__u8e3s4.k2d();
}
function readBytes(_this__u8e3s4, count) {
  count = count === VOID ? convertToInt(_this__u8e3s4.j2()) : count;
  return readByteArray(_this__u8e3s4, count);
}
function writeFully_0(_this__u8e3s4, buffer, offset, length) {
  offset = offset === VOID ? 0 : offset;
  length = length === VOID ? buffer.length - offset | 0 : length;
  _this__u8e3s4.t2e(buffer, offset, offset + length | 0);
}
function BytePacketBuilder() {
  return new Buffer();
}
function build(_this__u8e3s4) {
  return _this__u8e3s4.j2d();
}
function get_size(_this__u8e3s4) {
  return convertToInt(_this__u8e3s4.j2d().j2());
}
var ByteReadPacketEmpty;
function get_remaining(_this__u8e3s4) {
  _init_properties_ByteReadPacket_kt__28475y();
  return _this__u8e3s4.j2d().j2();
}
function takeWhile(_this__u8e3s4, block) {
  _init_properties_ByteReadPacket_kt__28475y();
  while (!_this__u8e3s4.k2d() && block(_this__u8e3s4.j2d())) {
  }
}
function discard_0(_this__u8e3s4, count) {
  count = count === VOID ? 9223372036854775807n : count;
  _init_properties_ByteReadPacket_kt__28475y();
  _this__u8e3s4.m2d(count);
  // Inline function 'kotlin.comparisons.minOf' call
  var b = get_remaining(_this__u8e3s4);
  var countToDiscard = count <= b ? count : b;
  _this__u8e3s4.j2d().h2e(countToDiscard);
  return countToDiscard;
}
function ByteReadPacket(array, offset, length) {
  offset = offset === VOID ? 0 : offset;
  length = length === VOID ? array.length : length;
  _init_properties_ByteReadPacket_kt__28475y();
  // Inline function 'kotlin.apply' call
  var this_0 = new Buffer();
  this_0.t2e(array, offset, offset + length | 0);
  return this_0;
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
  var result = _this__u8e3s4.i2e(buffer, offset, offset + length | 0);
  return result === -1 ? 0 : result;
}
function toByteArray_0(_this__u8e3s4, charset) {
  charset = charset === VOID ? Charsets_getInstance().j2k_1 : charset;
  if (charset.equals(Charsets_getInstance().j2k_1))
    return encodeToByteArray(_this__u8e3s4);
  return encodeToByteArray_0(charset.m2k(), _this__u8e3s4, 0, _this__u8e3s4.length);
}
function writeText(_this__u8e3s4, text, fromIndex, toIndex, charset) {
  fromIndex = fromIndex === VOID ? 0 : fromIndex;
  toIndex = toIndex === VOID ? charSequenceLength(text) : toIndex;
  charset = charset === VOID ? Charsets_getInstance().j2k_1 : charset;
  if (charset === Charsets_getInstance().j2k_1) {
    return writeString(_this__u8e3s4, toString_1(text), fromIndex, toIndex);
  }
  encodeToImpl(charset.m2k(), _this__u8e3s4, text, fromIndex, toIndex);
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
  return _this__u8e3s4.l2k_1;
}
function forName(_this__u8e3s4, name) {
  return Companion_instance_4.z2k(name);
}
function decode_0(_this__u8e3s4, input, dst, max) {
  var decoder = Decoder(get_name(get_charset(_this__u8e3s4)), true);
  input.m2d(fromInt(max));
  var tmp0 = input.j2d().j2();
  // Inline function 'kotlin.comparisons.minOf' call
  var b = fromInt(max);
  var count = tmp0 <= b ? tmp0 : b;
  var array = readByteArray(input, convertToInt(count));
  var tmp;
  try {
    // Inline function 'org.khronos.webgl.toInt8Array' call
    // Inline function 'kotlin.js.unsafeCast' call
    // Inline function 'kotlin.js.asDynamic' call
    tmp = decoder.d2l(array);
  } catch ($p) {
    var tmp_0;
    if ($p instanceof Error) {
      var cause = $p;
      var tmp0_elvis_lhs = cause.message;
      throw MalformedInputException.i2k('Failed to decode bytes: ' + (tmp0_elvis_lhs == null ? 'no cause provided' : tmp0_elvis_lhs));
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
  if (get_charset_0(_this__u8e3s4).equals(Charsets_getInstance().k2k_1)) {
    return encodeISO88591(input, fromIndex, toIndex, dst);
  }
  // Inline function 'kotlin.require' call
  if (!(get_charset_0(_this__u8e3s4) === Charsets_getInstance().j2k_1)) {
    var message_0 = 'Only UTF-8 encoding is supported in JS';
    throw IllegalArgumentException.i2(toString_1(message_0));
  }
  var encoder = new TextEncoder();
  // Inline function 'kotlin.text.substring' call
  var tmp$ret$5 = toString_1(charSequenceSubSequence(input, fromIndex, toIndex));
  var result = encoder.encode(tmp$ret$5);
  // Inline function 'kotlin.js.unsafeCast' call
  // Inline function 'kotlin.js.asDynamic' call
  dst.w2e(result);
  return result.length;
}
function encodeToByteArray_0(_this__u8e3s4, input, fromIndex, toIndex) {
  fromIndex = fromIndex === VOID ? 0 : fromIndex;
  toIndex = toIndex === VOID ? charSequenceLength(input) : toIndex;
  return encodeToByteArrayImpl(_this__u8e3s4, input, fromIndex, toIndex);
}
function get_charset(_this__u8e3s4) {
  return _this__u8e3s4.b2l_1;
}
function get_charset_0(_this__u8e3s4) {
  return _this__u8e3s4.c2l_1;
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
  charset = charset === VOID ? Charsets_getInstance().j2k_1 : charset;
  max = max === VOID ? 2147483647 : max;
  if (charset.equals(Charsets_getInstance().j2k_1)) {
    if (max === 2147483647)
      return readString_1(_this__u8e3s4);
    _this__u8e3s4.m2d(fromInt(max));
    var tmp0 = _this__u8e3s4.j2d().j2();
    // Inline function 'kotlin.math.min' call
    var b = fromInt(max);
    var count = tmp0 <= b ? tmp0 : b;
    return readString_0(_this__u8e3s4, count);
  }
  return decode(charset.a2l(), _this__u8e3s4, max);
}
function get_DEVELOPMENT_MODE() {
  return false;
}
function ioDispatcher() {
  return Dispatchers_getInstance().m23_1;
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
      dst.d2f(toByte(character));
    }
     while (inductionVariable < toIndex);
  return toIndex - fromIndex | 0;
}
function failedToMapError(ch) {
  throw MalformedInputException.i2k('The character with unicode point ' + ch + " couldn't be mapped to ISO-8859-1 character");
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
protoOf(Read).w2g = resume;
protoOf(Read).x2g = resume_0;
initMetadataForClass(Read, 'Read', VOID, VOID, [Task]);
protoOf(Write).w2g = resume;
protoOf(Write).x2g = resume_0;
initMetadataForClass(Write, 'Write', VOID, VOID, [Task]);
initMetadataForClass(sam$kotlinx_coroutines_DisposableHandle$0, 'sam$kotlinx_coroutines_DisposableHandle$0', VOID, VOID, [DisposableHandle, FunctionAdapter]);
initMetadataForInterface(ByteReadChannel, 'ByteReadChannel', VOID, VOID, VOID, [1]);
initMetadataForInterface(ByteWriteChannel, 'ByteWriteChannel', VOID, VOID, VOID, [0]);
protoOf(ByteChannel).p2i = awaitContent$default;
initMetadataForClass(ByteChannel, 'ByteChannel', ByteChannel, VOID, [ByteReadChannel, ByteWriteChannel], [1, 0]);
initMetadataForClass(ConcurrentIOException, 'ConcurrentIOException');
initMetadataForClass(ByteChannelScanner, 'ByteChannelScanner', VOID, VOID, VOID, [1, 0]);
protoOf(ByteReadChannel$Companion$Empty$1).p2i = awaitContent$default;
initMetadataForClass(ByteReadChannel$Companion$Empty$1, VOID, VOID, VOID, [ByteReadChannel], [1]);
initMetadataForCompanion(Companion_0);
initMetadataForClass(WriterJob, 'WriterJob');
initMetadataForClass(WriterScope, 'WriterScope');
initMetadataForClass(NO_CALLBACK$1, VOID, VOID, VOID, [Continuation]);
initMetadataForCompanion(Companion_1);
initMetadataForClass(CloseToken, 'CloseToken');
protoOf(CountedByteReadChannel).p2i = awaitContent$default;
initMetadataForClass(CountedByteReadChannel, 'CountedByteReadChannel', VOID, VOID, [ByteReadChannel], [1]);
initMetadataForClass(ClosedByteChannelException, 'ClosedByteChannelException', ClosedByteChannelException.f2i);
initMetadataForClass(ClosedReadChannelException, 'ClosedReadChannelException', ClosedReadChannelException.u2h);
initMetadataForClass(ClosedWriteChannelException, 'ClosedWriteChannelException', ClosedWriteChannelException.a2i);
initMetadataForClass(LineEnding, 'LineEnding');
initMetadataForCompanion(Companion_2);
initMetadataForClass(LineEndingMode, 'LineEndingMode');
protoOf(SourceByteReadChannel).p2i = awaitContent$default;
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
