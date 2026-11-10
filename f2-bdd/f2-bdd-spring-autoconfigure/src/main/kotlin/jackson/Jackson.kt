package jackson

import io.cucumber.java.Before

class Jackson {

    @Before
    fun configureJacksonSerializer() {
        System.setProperty("spring.cloud.function.preferred-json-mapper", "jackson")
    }

    companion object {
        //The name of the package
        const val GLUE = "jackson"
    }
}
