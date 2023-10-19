package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //path of all the feature files
        features = "src/test/resources/features/",
        //package where step defs are available
        glue = "steps",
        //true = provide the missing step definition
        dryRun = true,
        tags = "@test",
        //removes all irrelevant information from the console
        monochrome = true,
        //print all the steps in console
        //html plugin is generating the report under target folder
        plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json",
                "rerun:target/failed.txt"}
)

public class RunnerClass {

}