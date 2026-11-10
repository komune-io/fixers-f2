package kserialization

import io.cucumber.java.Before

class Kserialization {

    @Before
    fun configureKSerialization() {
        System.setProperty("spring.cloud.function.preferred-json-mapper", "kSerialization")
    }

    companion object {
        //The name of the package
        const val GLUE = "kserialization"
    }
}
