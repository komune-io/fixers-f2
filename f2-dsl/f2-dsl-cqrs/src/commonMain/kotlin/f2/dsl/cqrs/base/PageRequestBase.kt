package f2.dsl.cqrs.base

import f2.dsl.cqrs.PageRequest
import kotlin.js.JsExport
import kotlin.js.JsName

@JsExport
@JsName("PageRequestBase")
class PageRequestBase(
        override val page: Int?,
        override val size: Int?,
) : PageRequest