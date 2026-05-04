package StepDefinition.TestRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "Features/MySFBUApplicantUseScenarioOutlineNew.feature", // Path to feature files
        glue = "StepDefinition", // Package containing step definitions
        plugin = {"pretty", "html:target/cucumber-reports.html"}, // Reporting plugins
        monochrome = true                         // Better console output readability
)
public class TestRunner {

}