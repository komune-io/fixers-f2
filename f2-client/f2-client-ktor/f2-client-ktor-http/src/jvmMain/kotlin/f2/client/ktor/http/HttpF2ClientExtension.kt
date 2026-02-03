package f2.client.ktor.http

import f2.client.ktor.http.model.F2UploadCommand
import f2.dsl.fnc.F2Function
import io.ktor.util.reflect.typeInfo
import kotlin.reflect.full.isSubclassOf

/**
 * JVM-only extension that uses reflection to determine if MSG is a subclass of F2UploadCommand.
 * This allows proper handling of upload commands vs regular JSON payloads.
 */
inline fun <reified MSG, reified RESPONSE> HttpF2Client.function(route: String): F2Function<MSG, RESPONSE> {
	return if (MSG::class.isSubclassOf(F2UploadCommand::class)) {
		function(route, typeInfo<MSG>(), typeInfo<RESPONSE>())
	} else {
		function(route, typeInfo<List<MSG>>(), typeInfo<List<RESPONSE>>())
	}
}
