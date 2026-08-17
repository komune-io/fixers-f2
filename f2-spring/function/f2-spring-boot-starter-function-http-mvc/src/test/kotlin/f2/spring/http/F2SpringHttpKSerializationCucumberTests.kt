package f2.spring.http

import f2.bdd.spring.autoconfigure.steps.F2SpringStep
import io.cucumber.junit.platform.engine.Constants.FILTER_TAGS_PROPERTY_NAME
import io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME
import kserialization.Kserialization
import org.junit.platform.suite.api.ConfigurationParameter
import org.junit.platform.suite.api.IncludeEngines
import org.junit.platform.suite.api.SelectClasspathResource
import org.junit.platform.suite.api.Suite

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "${F2SpringStep.GLUE}, f2, ${Kserialization.GLUE}")
@ConfigurationParameter(key = FILTER_TAGS_PROPERTY_NAME, value = "not @jacksonOnly")
class F2SpringHttpKSerializationCucumberTests
