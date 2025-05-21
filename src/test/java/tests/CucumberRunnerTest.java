package tests;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

import static io.cucumber.junit.platform.engine.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.junit.platform.engine.Constants.PLUGIN_PROPERTY_NAME;

/**
 * Cucumber test runner class that configures the test suite to use Cucumber with Allure reporting.
 * This class sets up the Cucumber test environment and specifies the glue code and plugins.
 * It is responsible for running the Cucumber tests and generating Allure reports.
 * The class ensures that the tests are executed in a structured manner with proper reporting.
 * It integrates Cucumber with Allure to provide detailed test reports, enhancing the visibility of test results.
 */
@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("scenarios")
@ConfigurationParameter(key = GLUE_PROPERTY_NAME, value = "steps,hooks")
@ConfigurationParameter(key = PLUGIN_PROPERTY_NAME, value = "pretty, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm")
public class CucumberRunnerTest {
}