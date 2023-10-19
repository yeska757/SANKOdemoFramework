package steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.xml.DOMConfigurator;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Log;

public class LoginSteps extends CommonMethods {

    @Given("user is navigated to application")
    public void user_is_navigated_to_application() {
        openBrowserAndNavigateToURL();
    }

    @When("user enters valid admin username and password")
    public void user_enters_valid_admin_username_and_password() {
        DOMConfigurator.configure("log4j.xml");
        Log.startTestCase("test case starts here");
        sendText(ConfigReader.getPropertyValue("username"), loginPage.usernameField);
        Log.info("username has been entered");
        sendText(ConfigReader.getPropertyValue("password"), loginPage.passwordField);
        Log.info("password has been entered");
    }

    @When("user clicks on login button")
    public void user_clicks_on_login_button() {
        click(loginPage.loginButton);
    }

    @Then("user is successfully logged in the application")
    public void user_is_successfully_logged_in_the_application() {
        System.out.println("validating successful login");
    }

}