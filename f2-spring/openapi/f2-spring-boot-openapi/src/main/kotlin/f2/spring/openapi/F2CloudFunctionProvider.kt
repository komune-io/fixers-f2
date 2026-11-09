package f2.spring.openapi

import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import f2.dsl.fnc.F2SupplierSingle
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.Operation
import io.swagger.v3.oas.models.media.Content
import io.swagger.v3.oas.models.media.MediaType
import io.swagger.v3.oas.models.parameters.RequestBody
import io.swagger.v3.oas.models.responses.ApiResponse
import io.swagger.v3.oas.models.responses.ApiResponses
import java.lang.reflect.Type
import org.springdoc.core.fn.RouterOperation
import org.springdoc.core.properties.SpringDocConfigProperties
import org.springdoc.core.providers.CloudFunctionProvider
import org.springdoc.core.utils.SpringDocAnnotationsUtils
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.core.ResolvableType
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestMethod

class F2CloudFunctionProvider(
    private val springDocConfigProperties: SpringDocConfigProperties,
    private val prefix: String,
) : CloudFunctionProvider, ApplicationContextAware {

    private lateinit var applicationContext: ApplicationContext

    override fun setApplicationContext(applicationContext: ApplicationContext) {
        this.applicationContext = applicationContext
    }

    override fun getRouterOperations(openAPI: OpenAPI): List<RouterOperation> {
        return collectOperations(openAPI, F2Function::class.java, Kind.FUNCTION) +
            collectOperations(openAPI, F2Supplier::class.java, Kind.SUPPLIER) +
            collectOperations(openAPI, F2SupplierSingle::class.java, Kind.SUPPLIER) +
            collectOperations(openAPI, F2Consumer::class.java, Kind.CONSUMER)
    }

    private fun <T : Any> collectOperations(
        openAPI: OpenAPI,
        rawType: Class<T>,
        kind: Kind,
    ): List<RouterOperation> {
        return applicationContext.getBeansOfType(rawType).map { (name, bean) ->
            val resolvable = ResolvableType.forClass(rawType, getBeanClass(name, bean))
            val inputType = kind.inputIndex?.let { resolvable.getGeneric(it).type }
            val outputType = kind.outputIndex?.let { resolvable.getGeneric(it).type }
            buildRouterOperation(openAPI, name, kind, inputType, outputType)
        }
    }

    private fun getBeanClass(beanName: String, bean: Any): Class<*> {
        return applicationContext.getType(beanName) ?: bean.javaClass
    }

    private fun buildRouterOperation(
        openAPI: OpenAPI,
        name: String,
        kind: Kind,
        inputType: Type?,
        outputType: Type?,
    ): RouterOperation {
        val method = kind.method
        val operation = Operation()
            .operationId("${name}_$method")
            .description("$name ${kind.label}")

        if (inputType != null && method == RequestMethod.POST) {
            operation.requestBody(requestBody(openAPI, inputType))
        }
        operation.responses(apiResponses(openAPI, outputType))

        return RouterOperation().apply {
            setPath(routePath(name))
            setMethods(arrayOf(method))
            setConsumes(DEFAULT_MEDIA_TYPES)
            setProduces(DEFAULT_MEDIA_TYPES)
            operationModel = operation
        }
    }

    private fun requestBody(openAPI: OpenAPI, inputType: Type): RequestBody {
        val schema = SpringDocAnnotationsUtils.extractSchema(
            openAPI.components, inputType, null, null, openAPI.specVersion
        )
        val content = Content()
        DEFAULT_MEDIA_TYPES.forEach { mediaType ->
            content.addMediaType(mediaType, MediaType().schema(schema))
        }
        return RequestBody().content(content)
    }

    private fun apiResponses(openAPI: OpenAPI, outputType: Type?): ApiResponses {
        val apiResponses = ApiResponses()
        if (outputType == null) {
            apiResponses.addApiResponse(
                HttpStatus.ACCEPTED.value().toString(),
                ApiResponse().description(HttpStatus.ACCEPTED.reasonPhrase).content(Content())
            )
            return apiResponses
        }

        val responseSchema = SpringDocAnnotationsUtils.extractSchema(
            openAPI.components, outputType, null, null, openAPI.specVersion
        )
        val responseContent = Content()
        val producesMediaType = springDocConfigProperties.defaultProducesMediaType
            ?: org.springframework.http.MediaType.APPLICATION_JSON_VALUE
        responseContent.addMediaType(producesMediaType, MediaType().schema(responseSchema))
        apiResponses.addApiResponse(
            HttpStatus.OK.value().toString(),
            ApiResponse().description(HttpStatus.OK.reasonPhrase).content(responseContent)
        )
        return apiResponses
    }

    private fun routePath(name: String): String {
        val normalizedPrefix = prefix.trimEnd('/')
        return if (normalizedPrefix.isEmpty()) "/$name" else "/$normalizedPrefix/$name".replace("//", "/")
    }

    private enum class Kind(val label: String, val method: RequestMethod, val inputIndex: Int?, val outputIndex: Int?) {
        FUNCTION("function", RequestMethod.POST, inputIndex = 0, outputIndex = 1),
        SUPPLIER("supplier", RequestMethod.GET, inputIndex = null, outputIndex = 0),
        CONSUMER("consumer", RequestMethod.POST, inputIndex = 0, outputIndex = null),
    }

    companion object {
        private val DEFAULT_MEDIA_TYPES = arrayOf(
            org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
            org.springframework.http.MediaType.TEXT_PLAIN_VALUE,
        )
    }
}
