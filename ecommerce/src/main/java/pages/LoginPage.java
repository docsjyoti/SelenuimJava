package pages;

import Locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Utilities;

/**
 * Page Object Model for LoginPage containing only page actions.
 * No validation/assertion logic — those belong in Step Definitions.
 */
public class LoginPage {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    private WebDriver driver;
    private Utilities utils;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.utils = new Utilities(driver);
    }

    /**
     * Opens the login form by hovering over the account dropdown and clicking Sign In.
     */
    public void openLoginForm() {
        log.info("Opening login form via account dropdown.");
        WebElement dropdown = utils.waitForElement(Locator.ACCOUNT_DROPDOWN_OPTION, 5);
        utils.hoverOverElement(dropdown, 5);
        utils.click(Locator.SIGNIN_OPTION);
        utils.waitForElement(Locator.LOGIN_FORM, 5);
    }

    /**
     * Enters login credentials into the login form (credentials from config).
     */
    public void loginWithEmail(String param, String password, String name, String phone) {
        log.info("Filling login form with email: {}",param);
        utils.type(Locator.EMAIL_TEXT_FIELD, param);
        utils.click(Locator.CONTINUE_BUTTON);
        if(utils.isDisplayed(Locator.PROCEED_SIGNUP)) {
            utils.click(Locator.PROCEED_SIGNUP);
            createAccount(name,phone,password);
        } else{
            WebElement passwordfield= utils.waitForElement(Locator.PASSWORD_FIELD);
            passwordfield.clear();
            passwordfield.sendKeys(password);
            utils.click(Locator.SUBMIT_BUTTON);
        }
    }

    /**
     * Handles account creation on the signup page when a user does not already have an account.
     *
     * This method fills in the mobile number, full name, and password fields, and submits the form
     * by clicking the "Verify mobile number" button. It uses the Utilities class for consistent
     * interaction with web elements and assumes valid test data is hardcoded for now.
     *
     * Expected to be called when the login page redirects to the signup flow (e.g., during a
     * failed login with an unregistered email).
     */
    private void createAccount(String name, String phone,String password) {
        log.info("Creating a new account...");

        // Enter Mobile Number
        WebElement mobileField = utils.waitForElement(Locator.MOBILE_FIELD);
        mobileField.clear(); // Optional: ensure field is empty
        mobileField.sendKeys(phone);

        // Enter Name
        WebElement nameField = utils.waitForElement(Locator.NAME_FIELD);
        nameField.clear();
        nameField.sendKeys(name);

        // Enter Password
        WebElement passwordField = utils.waitForElement(Locator.PASSWORD_FIELD);
        passwordField.clear();
        passwordField.sendKeys(password);

        // Click on "Verify mobile number"
        WebElement verifyButton = utils.waitForElement(Locator.VERIFY_BUTTON);
        verifyButton.click();
    }

}
