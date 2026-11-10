package f2.dsl.cqrs.error

import kotlin.js.collections.JsReadonlyArray
import kotlin.js.collections.JsReadonlyMap

// A refusal's values as JS reads them: a Kotlin Map and List reach JS as KtMap and KtList, so these hand out
// JS views of them instead, typed ReadonlyMap and ReadonlyArray in the TypeScript contract.

/** The values [refusal] names, by key: `refusalParameters(error.refusal).get("entity")`. */
@JsExport
@OptIn(ExperimentalJsCollectionsApi::class)
fun refusalParameters(refusal: F2RefusalDTO): JsReadonlyMap<String, String> = refusal.parameters.asJsReadonlyMapView()

/** The objects whose citing keeps the refused one alive, by name. */
@JsExport
@OptIn(ExperimentalJsCollectionsApi::class)
fun refusalCiters(refusal: F2RefusalDTO): JsReadonlyArray<String> = refusal.citers.asJsReadonlyArrayView()
