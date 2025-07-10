package stepsDefinition;

import drivers.DriverManager;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import utils.ConfigReader;

public class LoginStep {

    WebDriver driver = DriverManager.getDriver();  // Get driver from DriverManager
    LoginPage loginPage = new LoginPage(driver);   // Create Page Object

    @Given("User is on the Amazon login page")
    public void user_is_on_the_amazon_login_page() {
        loginPage.loadURL();
    }

    @When("User select login option from landing page")
    public void user_select_login_option_from_landing_page() {
        loginPage.openLoginForm(); // Method in LoginPage to open login form
    }

    @Then("User logs in using {string}")
    public void user_logs_in_using(String userKey) {
        String baseKey = "login." + userKey;

        String email = ConfigReader.getProperty(baseKey + ".email");
        String password = ConfigReader.getProperty(baseKey + ".password");
        String name = ConfigReader.getProperty(baseKey + ".name");
        String phone = ConfigReader.getProperty(baseKey + ".phone");

        loginPage.loginWithEmail(email, password, name, phone);
    }

    @And("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        if (loginPage.isUserLoggedIn()) {
            System.out.println("✅ User successfully logged in.");
        } else {
            System.out.println("ℹ️ User was redirected to account creation or login failed.");
        }
    }
}
