package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //the path of all the feature files
        features = "src/test/resources/features/",
        //package with step defs
        glue = "APIstepDef",
        //true=provide the missing step definition
        dryRun = false,
        tags = "@APItest",
        //simple output with no irrelevant information
        monochrome = true,
        plugin = {"pretty"}
)

public class APIrunner {

}
