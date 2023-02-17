package f2.dsl.cqrs

import f2.dsl.cqrs.page.OffsetPagination
import org.springframework.data.domain.PageRequest

inline fun OffsetPagination?.toPage(): PageRequest = this?.let {
    val size = limit - offset
    val page = (if(size > 0 ) limit / size else 1) - 1

    return PageRequest.of(page, size);
} ?: PageRequest.of(0, 10000)