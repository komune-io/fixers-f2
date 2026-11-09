import {
  VOID3gxj6tk5isa35 as VOID,
  initMetadataForClassbxx6q50dy2s7 as initMetadataForClass,
} from './kotlin-kotlin-stdlib.mjs';
//region block: imports
//endregion
//region block: pre-declaration
class CloudEvent {
  constructor(eventType, eventTypeVersion, cloudEventsVersion, source, eventID, eventTime, schemaURL, contentType, extensions, data) {
    eventTypeVersion = eventTypeVersion === VOID ? null : eventTypeVersion;
    eventTime = eventTime === VOID ? null : eventTime;
    schemaURL = schemaURL === VOID ? null : schemaURL;
    contentType = contentType === VOID ? null : contentType;
    extensions = extensions === VOID ? null : extensions;
    this.l6_1 = eventType;
    this.m6_1 = eventTypeVersion;
    this.n6_1 = cloudEventsVersion;
    this.o6_1 = source;
    this.p6_1 = eventID;
    this.q6_1 = eventTime;
    this.r6_1 = schemaURL;
    this.s6_1 = contentType;
    this.t6_1 = extensions;
    this.u6_1 = data;
  }
  v6() {
    return this.l6_1;
  }
  w6() {
    return this.m6_1;
  }
  x6() {
    return this.n6_1;
  }
  y6() {
    return this.o6_1;
  }
  z6() {
    return this.p6_1;
  }
  a7() {
    return this.q6_1;
  }
  b7() {
    return this.r6_1;
  }
  c7() {
    return this.s6_1;
  }
  d7() {
    return this.t6_1;
  }
  e7() {
    return this.u6_1;
  }
  get eventType() {
    return this.v6();
  }
  get eventTypeVersion() {
    return this.w6();
  }
  get cloudEventsVersion() {
    return this.x6();
  }
  get source() {
    return this.y6();
  }
  get eventID() {
    return this.z6();
  }
  get eventTime() {
    return this.a7();
  }
  get schemaURL() {
    return this.b7();
  }
  get contentType() {
    return this.c7();
  }
  get extensions() {
    return this.d7();
  }
  get data() {
    return this.e7();
  }
}
class SnapEvent extends CloudEvent {
  constructor(eventType, eventTypeVersion, cloudEventsVersion, source, eventID, eventTime, schemaURL, contentType, extensions, data) {
    eventTypeVersion = eventTypeVersion === VOID ? null : eventTypeVersion;
    eventTime = eventTime === VOID ? null : eventTime;
    schemaURL = schemaURL === VOID ? null : schemaURL;
    contentType = contentType === VOID ? null : contentType;
    extensions = extensions === VOID ? null : extensions;
    super(eventType, eventTypeVersion, cloudEventsVersion, source, eventID, eventTime, schemaURL, contentType, extensions, data);
  }
}
//endregion
//region block: post-declaration
initMetadataForClass(CloudEvent, 'CloudEvent');
initMetadataForClass(SnapEvent, 'SnapEvent');
//endregion
//region block: exports
export {
  CloudEvent as CloudEvent,
  SnapEvent as SnapEvent,
};
//endregion

//# sourceMappingURL=fixers-f2-f2-dsl-f2-dsl-event.mjs.map
