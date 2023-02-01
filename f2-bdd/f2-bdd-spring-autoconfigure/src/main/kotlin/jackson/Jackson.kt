package jackson

import io.cucumber.java.Before

class Jackson {
    companion object {
        //The name of the package
        const val GLUE = "jackson"
    }

    @Before
    fun configureJacksonSerializer() {
        System.setProperty("spring.cloud.function.preferred-json-mapper", "jackson")
    }
}
