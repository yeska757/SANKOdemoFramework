package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/",
        glue = "steps",
        dryRun = false,
        tags = "@regression",
        monochrome = true,
        //"pretty" - print all the steps in console
        //html plugin is generating the report under target folder
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json"}
)

public class RegressionRunner {
}
