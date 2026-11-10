package f2.spring.exception.config

import f2.dsl.cqrs.exception.F2Exception
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.core.Ordered
import org.springframework.web.filter.OncePerRequestFilter
import tools.jackson.databind.json.JsonMapper

/**
 * Answers an [F2Exception] a servlet filter raises with the status its error names and the F2 error body: thrown
 * outside the DispatcherServlet, it would reach the error page as a 500. First of every filter, so it sees theirs.
 *
 * The body is written with [jsonMapper], the application's own. A response already committed is left as it is.
 */
class F2ExceptionFilter(
    private val jsonMapper: JsonMapper,
) : OncePerRequestFilter(), Ordered {

    override fun getOrder(): Int = Ordered.HIGHEST_PRECEDENCE

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        try {
            chain.doFilter(request, response)
        } catch (failure: F2Exception) {
            if (response.isCommitted) throw failure
            response.writeF2Error(request.requestURI, failure.error, jsonMapper)
        }
    }
}
