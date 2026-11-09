import { EmptySerializersModule991ju6pz9b79 as EmptySerializersModule } from './kotlinx-serialization-kotlinx-serialization-core.mjs';
import {
  initMetadataForCompanion1wyw17z38v6ac as initMetadataForCompanion,
  VOID3gxj6tk5isa35 as VOID,
  LinkedHashMap1zhqxkxv3xnkl as LinkedHashMap,
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
  _Char___init__impl__6a9atxrzocepzq3zgi as _Char___init__impl__6a9atx,
  charArrayOf27f4r3dozbrk1 as charArrayOf,
  trimEndvvzjdhan75g as trimEnd,
} from './kotlin-kotlin-stdlib.mjs';
//region block: imports
//endregion
//region block: pre-declaration
class Companion {
  constructor() {
    Companion_instance = this;
    this.f3z_1 = new KotlinxSerializerJsonSchemaInference(EmptySerializersModule());
  }
}
class KotlinxSerializerJsonSchemaInference {
  constructor(module_0, formats) {
    Companion_getInstance();
    formats = formats === VOID ? get_KotlinxSerializerDefaultFormats() : formats;
    this.g3z_1 = module_0;
    this.h3z_1 = formats;
    var tmp = this;
    // Inline function 'kotlin.collections.mutableMapOf' call
    tmp.i3z_1 = LinkedHashMap.c5();
  }
}
//endregion
function get_KotlinxSerializerDefaultFormats() {
  _init_properties_JsonSchemaInference_kt__gcjigy();
  return KotlinxSerializerDefaultFormats;
}
var KotlinxSerializerDefaultFormats;
var Companion_instance;
function Companion_getInstance() {
  if (Companion_instance === VOID)
    new Companion();
  return Companion_instance;
}
function get_nonNullSerialName(_this__u8e3s4) {
  _init_properties_JsonSchemaInference_kt__gcjigy();
  return trimEnd(_this__u8e3s4.p1e(), charArrayOf([_Char___init__impl__6a9atx(63)]));
}
function KotlinxSerializerDefaultFormats$lambda(type) {
  _init_properties_JsonSchemaInference_kt__gcjigy();
  switch (get_nonNullSerialName(type)) {
    case 'kotlin.uuid.Uuid':
      return 'uuid';
    case 'kotlinx.datetime.LocalDate':
      return 'date';
    case 'kotlinx.datetime.LocalTime':
      return 'time';
    case 'kotlinx.datetime.TimeZone':
      return 'time-zone';
    case 'kotlinx.datetime.UtcOffset':
      return 'utc-offset';
    case 'kotlin.time.Instant':
    case 'kotlinx.datetime.Instant':
    case 'kotlinx.datetime.LocalDateTime':
      return 'date-time';
    case 'kotlin.time.Duration':
    case 'kotlinx.datetime.DatePeriod':
    case 'kotlinx.datetime.DateTimePeriod':
      return 'duration';
    default:
      return null;
  }
}
var properties_initialized_JsonSchemaInference_kt_hhqb34;
function _init_properties_JsonSchemaInference_kt__gcjigy() {
  if (!properties_initialized_JsonSchemaInference_kt_hhqb34) {
    properties_initialized_JsonSchemaInference_kt_hhqb34 = true;
    KotlinxSerializerDefaultFormats = KotlinxSerializerDefaultFormats$lambda;
  }
}
//region block: post-declaration
initMetadataForCompanion(Companion);
initMetadataForClass(KotlinxSerializerJsonSchemaInference, 'KotlinxSerializerJsonSchemaInference');
//endregion
//region block: exports
export {
  KotlinxSerializerJsonSchemaInference as KotlinxSerializerJsonSchemaInference2pgk3rid93x2d,
};
//endregion

//# sourceMappingURL=ktor-ktor-openapi-schema.mjs.map
