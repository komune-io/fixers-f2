package city.smartb.f2.client.ktor.r2.mongodb.test

import org.springframework.test.context.ActiveProfilesResolver
import org.springframework.test.context.support.DefaultActiveProfilesResolver


class SystemPropertyActiveProfileResolver : ActiveProfilesResolver {

    private val defaultActiveProfilesResolver = DefaultActiveProfilesResolver()

    override fun resolve(testClass: Class<*>): Array<String> {
        val springProfileKey = "SPRING_PROFILES_ACTIVE"
        val profile = defaultActiveProfilesResolver.resolve(testClass)
        return if (System.getenv().containsKey(springProfileKey))
            profile.filterNotNull().union(profileEnvAsArray(springProfileKey)).toTypedArray()
        else
            profile
    }

    private fun profileEnvAsArray(springProfileKey: String): List<String> =
            System.getenv()[springProfileKey]!!.split("\\s*,\\s*".toRegex()).dropLastWhile { it.isEmpty() }
}