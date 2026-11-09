import {
  Unit_instance3kz1zqli6gr1r as Unit_instance,
  THROW_IAE23kobfj9wdoxr as THROW_IAE,
  Enum3alwj03lh1n41 as Enum,
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
  initMetadataForInterface1egvbzx539z91 as initMetadataForInterface,
  defineProp3ur6h3slcvq4x as defineProp,
  VOID3gxj6tk5isa35 as VOID,
} from './kotlin-kotlin-stdlib.mjs';
//region block: imports
//endregion
//region block: pre-declaration
class F2ClientType extends Enum {
  get name() {
    return this.d1();
  }
  get ordinal() {
    return this.l4();
  }
}
class F2Client {}
//endregion
var static_init_called;
function static_init() {
  if (static_init_called)
    return Unit_instance;
  static_init_called = true;
  F2ClientType_HTTP_instance = new F2ClientType('HTTP', 0);
}
var F2ClientType_HTTP_instance;
function values() {
  static_init();
  return [F2ClientType_HTTP_getInstance()];
}
function valueOf(value) {
  static_init();
  if (value === 'HTTP')
    return F2ClientType_HTTP_getInstance();
  else {
    THROW_IAE('No enum constant f2.client.F2ClientType.' + value);
  }
}
function F2ClientType_HTTP_getInstance() {
  static_init();
  return F2ClientType_HTTP_instance;
}
//region block: post-declaration
initMetadataForClass(F2ClientType, 'F2ClientType');
initMetadataForInterface(F2Client, 'F2Client');
//endregion
//region block: exports
F2ClientType.values = values;
F2ClientType.valueOf = valueOf;
defineProp(F2ClientType, 'HTTP', F2ClientType_HTTP_getInstance, VOID, true);
export {
  F2ClientType as F2ClientType,
};
export {
  F2Client as F2Client19svmuxijhxn7,
  F2ClientType_HTTP_getInstance as F2ClientType_HTTP_getInstance1uppk423v5x3a,
};
//endregion

//# sourceMappingURL=fixers-f2-f2-client-f2-client-core.mjs.map
