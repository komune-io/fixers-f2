package f2.feature.vc.fnc.config

import city.smartb.iris.crypto.rsa.RSAKeyPairReader
import java.security.KeyPair
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

class CredentialsKey(val name: String, val pair: KeyPair) {

	companion object {
		@Throws(Exception::class)
		fun loadFromFile(name: String, filename: String): CredentialsKey {
			val keypair = RSAKeyPairReader.loadKeyPair(filename)
			return CredentialsKey(name, keypair)
		}
	}

	fun getRSAPrivateKey(): RSAPrivateKey {
		return pair.private as RSAPrivateKey
	}

	fun getRSAPublicKey(): RSAPublicKey {
		return pair.public as RSAPublicKey
	}
}
