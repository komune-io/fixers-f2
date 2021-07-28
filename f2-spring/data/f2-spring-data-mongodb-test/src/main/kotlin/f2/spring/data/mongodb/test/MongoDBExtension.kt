package f2.spring.data.mongodb.test

import de.flapdoodle.embed.mongo.MongodExecutable
import de.flapdoodle.embed.mongo.MongodStarter
import de.flapdoodle.embed.mongo.config.MongodConfigBuilder
import de.flapdoodle.embed.mongo.config.Net
import de.flapdoodle.embed.mongo.distribution.Version
import de.flapdoodle.embed.process.runtime.Network
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

class MongoDBExtension : BeforeAllCallback, AfterAllCallback {

	companion object {
		var mongodExecutable: MongodExecutable? = null
	}

	fun start(): MongodExecutable {
		try {
			val starter = MongodStarter.getDefaultInstance()
			val bindIp = "localhost"
			val port = 12345
			val mongodConfig = MongodConfigBuilder()
				.version(Version.Main.PRODUCTION)
				.net(Net(bindIp, port, Network.localhostIsIPv6()))
				.build()
			var mongodExecutable = starter.prepare(mongodConfig)
			mongodExecutable!!.start()
			return mongodExecutable
		} catch (e: Exception) {
			// log exception here
			mongodExecutable?.let { it.stop() }
			throw e
		}
	}

	override fun beforeAll(context: ExtensionContext?) {
		if (mongodExecutable == null) {
			mongodExecutable = start()
		}
	}

	override fun afterAll(context: ExtensionContext?) {
		mongodExecutable?.let { it.stop() }
		mongodExecutable = null
	}
}