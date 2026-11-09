import {
  VOID3gxj6tk5isa35 as VOID,
  Unit_instance3kz1zqli6gr1r as Unit_instance,
} from './kotlin-kotlin-stdlib.mjs';
import { get_ContentNegotiationcp59ye9rorjc as get_ContentNegotiation } from './ktor-ktor-client-content-negotiation.mjs';
import { jsonu6qnfo3b405p as json } from './ktor-ktor-serialization-kotlinx-json.mjs';
import { Jsonsmkyu9xjl7fv as Json } from './kotlinx-serialization-kotlinx-serialization-json.mjs';
//region block: imports
//endregion
//region block: pre-declaration
//endregion
function applyConfig(_this__u8e3s4, config) {
  config = config === VOID ? null : config;
  var tmp = get_ContentNegotiation();
  _this__u8e3s4.a3i(tmp, applyConfig$lambda);
  if (config == null)
    null;
  else {
    // Inline function 'kotlin.let' call
    config(_this__u8e3s4);
  }
}
function applyConfig$lambda($this$install) {
  json($this$install, get_F2DefaultJson());
  return Unit_instance;
}
function get_F2DefaultJson() {
  _init_properties_Json_kt__5095e0();
  return F2DefaultJson;
}
var F2DefaultJson;
function F2DefaultJson$lambda($this$Json) {
  _init_properties_Json_kt__5095e0();
  $this$Json.e1h_1 = true;
  $this$Json.h1h_1 = true;
  $this$Json.s1h_1 = true;
  $this$Json.t1h_1 = true;
  $this$Json.i1h_1 = false;
  $this$Json.u1h_1 = false;
  $this$Json.g1h_1 = true;
  $this$Json.f1h_1 = false;
  return Unit_instance;
}
var properties_initialized_Json_kt_iojru2;
function _init_properties_Json_kt__5095e0() {
  if (!properties_initialized_Json_kt_iojru2) {
    properties_initialized_Json_kt_iojru2 = true;
    F2DefaultJson = Json(VOID, F2DefaultJson$lambda);
  }
}
//region block: exports
export {
  get_F2DefaultJson as get_F2DefaultJson2dz3s4djcnt9b,
  applyConfig as applyConfigjp8itbzszckf,
};
//endregion

//# sourceMappingURL=fixers-f2-f2-client-f2-client-ktor-f2-client-ktor-common.mjs.map
