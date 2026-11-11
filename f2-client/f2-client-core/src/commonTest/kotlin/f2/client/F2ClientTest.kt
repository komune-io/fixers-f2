package f2.client

import f2.dsl.fnc.F2Consumer
import f2.dsl.fnc.F2Function
import f2.dsl.fnc.F2Supplier
import io.ktor.util.reflect.TypeInfo
import io.ktor.util.reflect.typeInfo
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlinx.coroutines.flow.emptyFlow

private class RecordingF2Client : F2Client {
    override val type: F2ClientType = F2ClientType.HTTP
    var route: String? = null
    var queryTypeInfo: TypeInfo? = null
    var responseTypeInfo: TypeInfo? = null

    override fun <RESPONSE> supplier(route: String, responseTypeInfo: TypeInfo): F2Supplier<RESPONSE> {
        this.route = route
        this.responseTypeInfo = responseTypeInfo
        return F2Supplier { emptyFlow() }
    }

    override fun <QUERY, RESPONSE> function(
        route: String, queryTypeInfo: TypeInfo, responseTypeInfo: TypeInfo
    ): F2Function<QUERY, RESPONSE> {
        this.route = route
        this.queryTypeInfo = queryTypeInfo
        this.responseTypeInfo = responseTypeInfo
        return F2Function { emptyFlow() }
    }

    override fun <QUERY> consumer(route: String, queryTypeInfo: TypeInfo): F2Consumer<QUERY> {
        this.route = route
        this.queryTypeInfo = queryTypeInfo
        return F2Consumer { }
    }
}

class F2ClientTest {

    @Test
    fun getTypeInfoWrapsTheDataTypeInAList() {
        val client = RecordingF2Client()

        assertEquals(expected = typeInfo<List<String>>(), actual = client.getTypeInfo<String>())
    }

    @Test
    fun getTypeInfoKeepsTheDataTypeArgumentDistinct() {
        val client = RecordingF2Client()

        val stringTypeInfo = client.getTypeInfo<String>()
        val intTypeInfo = client.getTypeInfo<Int>()

        assertEquals(expected = typeInfo<List<Int>>(), actual = intTypeInfo)
        assertEquals(expected = false, actual = stringTypeInfo == intTypeInfo)
    }

    @Test
    fun supplierPassesTheListTypeInfoOfTheResponse() {
        val client = RecordingF2Client()

        val _ = client.supplier<String>("mySupplier")

        assertEquals(expected = "mySupplier", actual = client.route)
        assertEquals(expected = typeInfo<List<String>>(), actual = client.responseTypeInfo)
        assertNull(client.queryTypeInfo)
    }

    @Test
    fun supplierSinglePassesTheRawTypeInfoOfTheResponse() {
        val client = RecordingF2Client()

        val _ = client.supplierSingle<String>("mySupplierSingle")

        assertEquals(expected = "mySupplierSingle", actual = client.route)
        assertEquals(expected = typeInfo<String>(), actual = client.responseTypeInfo)
    }

    @Test
    fun functionPassesTheListTypeInfoOfBothQueryAndResponse() {
        val client = RecordingF2Client()

        val _ = client.function<Int, String>("myFunction")

        assertEquals(expected = "myFunction", actual = client.route)
        assertEquals(expected = typeInfo<List<Int>>(), actual = client.queryTypeInfo)
        assertEquals(expected = typeInfo<List<String>>(), actual = client.responseTypeInfo)
    }

    @Test
    fun consumerPassesTheListTypeInfoOfTheQuery() {
        val client = RecordingF2Client()

        val _ = client.consumer<Int>("myConsumer")

        assertEquals(expected = "myConsumer", actual = client.route)
        assertEquals(expected = typeInfo<List<Int>>(), actual = client.queryTypeInfo)
        assertNull(client.responseTypeInfo)
    }

    @Test
    fun consumerSinglePassesTheRawTypeInfoOfTheQuery() {
        val client = RecordingF2Client()

        val _ = client.consumerSingle<Int>("myConsumerSingle")

        assertEquals(expected = "myConsumerSingle", actual = client.route)
        assertEquals(expected = typeInfo<Int>(), actual = client.queryTypeInfo)
    }
}
