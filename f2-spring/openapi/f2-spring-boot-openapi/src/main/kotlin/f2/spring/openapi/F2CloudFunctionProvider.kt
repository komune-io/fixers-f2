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
        val operations = mutableListOf<RouterOperation>()

        discoverBeans<F2Function<*, *>>().forEach { (name, _) ->
            val resolvable = ResolvableType.forClass(F2Function::class.java, getBeanClass(name))
            val inputType = resolvable.getGeneric(0).type
            val outputType = resolvable.getGeneric(1).type
            operations.add(
                buildRouterOperation(
                    openAPI, name, "function", RequestMethod.POST, inputType, outputType
                )
            )
        }

        discoverBeans<F2Supplier<*>>().forEach { (name, _) ->
            val resolvable = ResolvableType.forClass(F2Supplier::class.java, getBeanClass(name))
            val outputType = resolvable.getGeneric(0).type
            operations.add(
                buildRouterOperation(
                    openAPI, name, "supplier", RequestMethod.GET, null, outputType
                )
            )
        }

        discoverBeans<F2SupplierSingle<*>>().forEach { (name, _) ->
            val resolvable = ResolvableType.forClass(F2SupplierSingle::class.java, getBeanClass(name))
            val outputType = resolvable.getGeneric(0).type
            operations.add(
                buildRouterOperation(
                    openAPI, name, "supplier", RequestMethod.GET, null, outputType
                )
            )
        }

        discoverBeans<F2Consumer<*>>().forEach { (name, _) ->
            val resolvable = ResolvableType.forClass(F2Consumer::class.java, getBeanClass(name))
            val inputType = resolvable.getGeneric(0).type
            operations.add(
                buildRouterOperation(
                    openAPI, name, "consumer", RequestMethod.POST, inputType, null
                )
            )
        }

        return operations
    }

    private fun getBeanClass(beanName: String): Class<*> {
        return applicationContext.getType(beanName) ?: Any::class.java
    }

    private inline fun <reified T : Any> discoverBeans(): Map<String, T> {
        return applicationContext.getBeansOfType(T::class.java)
    }

    private fun buildRouterOperation(
        openAPI: OpenAPI,
        name: String,
        type: String,
        method: RequestMethod,
        inputType: java.lang.reflect.Type?,
        outputType: java.lang.reflect.Type?,
    ): RouterOperation {
        val operation = Operation()
            .operationId("${name}_$method")
            .description("$name $type")

        if (inputType != null && method == RequestMethod.POST) {
            val schema = SpringDocAnnotationsUtils.extractSchema(
                openAPI.components, inputType, null, null, openAPI.specVersion
            )
            val content = Content()
            DEFAULT_MEDIA_TYPES.forEach { mediaType ->
                content.addMediaType(mediaType, MediaType().schema(schema))
            }
            operation.requestBody(RequestBody().content(content))
        }

        val apiResponses = ApiResponses()
        if (outputType != null) {
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
        } else {
            apiResponses.addApiResponse(
                HttpStatus.ACCEPTED.value().toString(),
                ApiResponse().description(HttpStatus.ACCEPTED.reasonPhrase).content(Content())
            )
        }
        operation.responses(apiResponses)

        val normalizedPrefix = prefix.trimEnd('/')
        val path = if (normalizedPrefix.isEmpty()) "/$name" else "/$normalizedPrefix/$name".replace("//", "/")

        return RouterOperation().apply {
            setPath(path)
            setMethods(arrayOf(method))
            setConsumes(DEFAULT_MEDIA_TYPES)
            setProduces(DEFAULT_MEDIA_TYPES)
            operationModel = operation
        }
    }

    companion object {
        private val DEFAULT_MEDIA_TYPES = arrayOf(
            org.springframework.http.MediaType.APPLICATION_JSON_VALUE,
            org.springframework.http.MediaType.TEXT_PLAIN_VALUE,
        )
    }
}
