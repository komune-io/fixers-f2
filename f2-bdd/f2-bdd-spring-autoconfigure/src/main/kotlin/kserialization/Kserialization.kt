package kserialization

import io.cucumber.java.Before

class Kserialization {

    companion object {
        //The name of the package
        const val GLUE = "kserialization"
    }
    @Before
    fun configureKotlinSerializationSerializer() {
        System.setProperty("spring.cloud.function.preferred-json-mapper", "kSerialization")
    }
}
