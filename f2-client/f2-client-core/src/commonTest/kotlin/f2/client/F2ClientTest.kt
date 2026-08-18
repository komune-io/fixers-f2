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

		assertEquals(typeInfo<List<String>>(), client.getTypeInfo<String>())
	}

	@Test
	fun getTypeInfoKeepsTheDataTypeArgumentDistinct() {
		val client = RecordingF2Client()

		val stringTypeInfo = client.getTypeInfo<String>()
		val intTypeInfo = client.getTypeInfo<Int>()

		assertEquals(typeInfo<List<Int>>(), intTypeInfo)
		assertEquals(false, stringTypeInfo == intTypeInfo)
	}

	@Test
	fun supplierPassesTheListTypeInfoOfTheResponse() {
		val client = RecordingF2Client()

		client.supplier<String>("mySupplier")

		assertEquals("mySupplier", client.route)
		assertEquals(typeInfo<List<String>>(), client.responseTypeInfo)
		assertNull(client.queryTypeInfo)
	}

	@Test
	fun supplierSinglePassesTheRawTypeInfoOfTheResponse() {
		val client = RecordingF2Client()

		client.supplierSingle<String>("mySupplierSingle")

		assertEquals("mySupplierSingle", client.route)
		assertEquals(typeInfo<String>(), client.responseTypeInfo)
	}

	@Test
	fun functionPassesTheListTypeInfoOfBothQueryAndResponse() {
		val client = RecordingF2Client()

		client.function<Int, String>("myFunction")

		assertEquals("myFunction", client.route)
		assertEquals(typeInfo<List<Int>>(), client.queryTypeInfo)
		assertEquals(typeInfo<List<String>>(), client.responseTypeInfo)
	}

	@Test
	fun consumerPassesTheListTypeInfoOfTheQuery() {
		val client = RecordingF2Client()

		client.consumer<Int>("myConsumer")

		assertEquals("myConsumer", client.route)
		assertEquals(typeInfo<List<Int>>(), client.queryTypeInfo)
		assertNull(client.responseTypeInfo)
	}

	@Test
	fun consumerSinglePassesTheRawTypeInfoOfTheQuery() {
		val client = RecordingF2Client()

		client.consumerSingle<Int>("myConsumerSingle")

		assertEquals("myConsumerSingle", client.route)
		assertEquals(typeInfo<Int>(), client.queryTypeInfo)
	}
}
