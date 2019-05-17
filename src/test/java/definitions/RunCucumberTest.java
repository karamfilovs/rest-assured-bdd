package definitions;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/features",
        glue = {"definitions"},
        plugin = {"pretty", "json:target/cucumber-reports/Cucumber.json"},
        monochrome = true)
public class RunCucumberTest {
}