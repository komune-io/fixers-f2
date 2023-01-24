package f2.spring.http

import f2.bdd.spring.autoconfigure.steps.F2SpringContextBag
import f2.bdd.spring.autoconfigure.steps.F2SpringStep
import io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME
import jackson.Jackson
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "${F2SpringStep.GLUE}, f2, ${Jackson.GLUE}")
class F2SpringRSocketJacksonCucumberTests
