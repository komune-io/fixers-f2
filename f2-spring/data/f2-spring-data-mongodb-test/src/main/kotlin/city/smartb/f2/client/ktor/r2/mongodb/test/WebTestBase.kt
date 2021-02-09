package city.smartb.f2.client.ktor.r2.mongodb.test

import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.junit.jupiter.SpringExtension

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test", resolver = SystemPropertyActiveProfileResolver::class)
@ExtendWith(SpringExtension::class, MongoDBExtension::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = ["spring.main.allow-bean-definition-overriding=true"])
@DirtiesContext
class WebTestBase {

    @Autowired
    @LocalServerPort
    protected var port: Int = 0
}
