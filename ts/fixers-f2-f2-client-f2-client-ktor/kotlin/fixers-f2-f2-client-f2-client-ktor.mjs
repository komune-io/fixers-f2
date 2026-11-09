import {
  IllegalArgumentException2asla15b5jaob as IllegalArgumentException,
  toString1pkumu07cwy4m as toString,
  Unit_instance3kz1zqli6gr1r as Unit_instance,
  startsWith26w8qjqapeeq6 as startsWith,
  VOID3gxj6tk5isa35 as VOID,
  initMetadataForObject1cxne3s9w65el as initMetadataForObject,
  Exceptiondt2hlxn7j7vw as Exception,
  captureStack1fzi4aczwc4hg as captureStack,
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
} from './kotlin-kotlin-stdlib.mjs';
import {
  httpClientBuilderDefault3fuy994a7lxld as httpClientBuilderDefault,
  httpClientBuilderGenerics3w104dkjbc3g3 as httpClientBuilderGenerics,
} from './fixers-f2-f2-client-f2-client-ktor-f2-client-ktor-http.mjs';
import './ktor-ktor-client-core.mjs';
//region block: imports
//endregion
//region block: pre-declaration
class F2ClientBuilder {
  constructor() {
    this.k46_1 = 'http:';
    this.l46_1 = 'https:';
  }
  m46(url) {
    return startsWith(url, 'http:') || startsWith(url, 'https:');
  }
  getHttp(url) {
    requireHttpUrl(this, url);
    return httpClientBuilderDefault().build(url);
  }
  n46(urlBase, config) {
    if (!this.m46(urlBase)) {
      throw InvalidUrlException.q46(urlBase);
    }
    return httpClientBuilderGenerics(config).build(urlBase);
  }
  r46(urlBase, config, $super) {
    config = config === VOID ? null : config;
    return $super === VOID ? this.n46(urlBase, config) : $super.n46.call(this, urlBase, config);
  }
  o2b(url) {
    requireHttpUrl(this, url);
    return httpClientBuilderDefault().build(url);
  }
}
class InvalidUrlException extends Exception {
  constructor(url) {
    return new.target.q46(url);
  }
  static q46(url) {
    var $this = this.id(url + ' is invalid');
    captureStack($this, $this.p46_1);
    return $this;
  }
}
//endregion
function requireHttpUrl($this, url) {
  // Inline function 'kotlin.require' call
  if (!$this.m46(url)) {
    var message = 'Invalid Url[' + url + '] must start with one of http:, https:';
    throw IllegalArgumentException.i2(toString(message));
  }
}
var F2ClientBuilder_instance;
function F2ClientBuilder_getInstance() {
  return F2ClientBuilder_instance;
}
//region block: post-declaration
initMetadataForObject(F2ClientBuilder, 'F2ClientBuilder');
initMetadataForClass(InvalidUrlException, 'InvalidUrlException');
//endregion
//region block: init
F2ClientBuilder_instance = new F2ClientBuilder();
//endregion
//region block: exports
F2ClientBuilder.getInstance = F2ClientBuilder_getInstance;
export {
  F2ClientBuilder as F2ClientBuilder,
  InvalidUrlException as InvalidUrlException,
};
//endregion

//# sourceMappingURL=fixers-f2-f2-client-f2-client-ktor.mjs.map
