import {
  initMetadataForObject1cxne3s9w65el as initMetadataForObject,
  Unit_instance3kz1zqli6gr1r as Unit_instance,
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
  ensureNotNull1e947j3ixpazm as ensureNotNull,
  VOID3gxj6tk5isa35 as VOID,
  isNaNymqb93xtq8w8 as isNaN_0,
  IllegalArgumentException2asla15b5jaob as IllegalArgumentException,
  toString1pkumu07cwy4m as toString,
} from './kotlin-kotlin-stdlib.mjs';
import { atomic$ref$130aurmcwdfdf1 as atomic$ref$1 } from './kotlinx-atomicfu.mjs';
//region block: imports
//endregion
//region block: pre-declaration
class Js {
  toString() {
    return 'Js';
  }
  hashCode() {
    return -527824213;
  }
  equals(other) {
    if (this === other)
      return true;
    if (!(other instanceof Js))
      return false;
    return true;
  }
}
class Node {
  constructor(entry, next) {
    this.pk_1 = entry;
    this.qk_1 = next;
  }
}
class engines$iterator$1 {
  constructor() {
    this.rk_1 = engines_getInstance().nk_1.kotlinx$atomicfu$value;
  }
  h() {
    var result = ensureNotNull(this.rk_1);
    this.rk_1 = result.qk_1;
    return result.pk_1.sk_1;
  }
  g() {
    return !(null == this.rk_1);
  }
}
class engines {
  constructor() {
    engines_instance = this;
    this.nk_1 = atomic$ref$1(null);
  }
  ok(item) {
    this.uk(item, 1.0);
  }
  uk(item, priority) {
    $l$loop: while (true) {
      var current = this.nk_1.kotlinx$atomicfu$value;
      var new_0 = new Node(new EngineEntry(item, priority), current);
      if (this.nk_1.atomicfu$compareAndSet(current, new_0))
        break $l$loop;
    }
  }
  f() {
    return new engines$iterator$1();
  }
}
class EngineEntry {
  constructor(factory, priority) {
    priority = priority === VOID ? 1.0 : priority;
    this.sk_1 = factory;
    this.tk_1 = priority;
    // Inline function 'kotlin.require' call
    if (!!isNaN_0(this.tk_1)) {
      var message = 'Priority cannot be NaN';
      throw IllegalArgumentException.q2(toString(message));
    }
  }
}
//endregion
function get_initHook() {
  return initHook;
}
var initHook;
var Js_instance;
function Js_getInstance() {
  return Js_instance;
}
function initHook$init$() {
  engines_getInstance().ok(Js_instance);
  return Unit_instance;
}
var engines_instance;
function engines_getInstance() {
  if (engines_instance === VOID)
    new engines();
  return engines_instance;
}
var useEngineDispatcher;
//region block: post-declaration
initMetadataForObject(Js, 'Js');
initMetadataForClass(Node, 'Node');
initMetadataForClass(engines$iterator$1);
initMetadataForObject(engines, 'engines');
initMetadataForClass(EngineEntry, 'EngineEntry');
//endregion
//region block: init
Js_instance = new Js();
useEngineDispatcher = true;
//endregion
//region block: eager init
initHook = initHook$init$();
//endregion
//region block: exports
var initHook_0 = {get: get_initHook};
export {
  initHook_0 as initHook,
};
//endregion

//# sourceMappingURL=ktor-ktor-client-core.mjs.map
