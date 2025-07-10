package pages;

import Locators.Locator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Utilities;

public class LoginPage {

    private static final Logger log = LoggerFactory.getLogger(LoginPage.class);
    private WebDriver driver;
    private Utilities utils;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.utils = new Utilities(driver);
    }

    /**
     * Navigating to the Amazon homePage
     */
    public void loadURL() {
        log.info("Navigating to Amazon homepage");
        utils.waitTill(3); // Thoughtful wait before navigating
        driver.get("https://www.amazon.in");
        utils.waitTill(5); // Wait for page to fully load
    }

    /**
     * Opens the login form by hovering over the account dropdown and clicking Sign In.
     */
    public void openLoginForm() {
        log.info("Opening login form via account dropdown.");
        WebElement dropdown = utils.waitForElement(Locator.ACCOUNT_DROPDOWN_OPTION, 10);
        utils.hoverOverElement(dropdown, 5);
        utils.waitTill(2); // Wait to ensure hover dropdown is visible
        utils.click(Locator.SIGNIN_OPTION);
        utils.waitTill(3); // Let login page load
        utils.waitForElement(Locator.LOGIN_FORM, 10);
    }

    /**
     * Enters login credentials into the login form (credentials from config).
     */
    public void loginWithEmail(String email, String password, String name, String phone) {
        log.info("Filling login form with email: {}", email);

        utils.waitTill(2); // Wait before interacting
        utils.type(Locator.EMAIL_TEXT_FIELD, email);

        utils.waitTill(1); // Wait before clicking
        utils.click(Locator.CONTINUE_BUTTON);

        utils.waitTill(3); // Wait for next step/page

        if (utils.isDisplayed(Locator.PROCEED_SIGNUP)) {
            utils.waitTill(2);
            utils.click(Locator.PROCEED_SIGNUP);
            utils.waitTill(2); // Let signup form load
            createAccount(name, phone, password);
        } else {
            WebElement passwordField = utils.waitForElement(Locator.PASSWORD_FIELD, 10);
            utils.waitTill(1);
            passwordField.clear();
            passwordField.sendKeys(password);

            utils.waitTill(2); // Mimic real user wait
            utils.click(Locator.SUBMIT_BUTTON);
        }
    }

    /**
     * Handles account creation on the signup page when a user does not already have an account.
     */
    private void createAccount(String name, String phone, String password) {
        log.info("Creating a new account...");

        WebElement mobileField = utils.waitForElement(Locator.MOBILE_FIELD, 10);
        utils.waitTill(100);
        mobileField.clear();
        mobileField.sendKeys(phone);

        WebElement nameField = utils.waitForElement(Locator.NAME_FIELD, 5);
        nameField.clear();
        nameField.sendKeys(name);

        WebElement passwordField = utils.waitForElement(Locator.PASSWORD_FIELD, 5);
        passwordField.clear();
        passwordField.sendKeys(password);

        utils.waitTill(100);
        WebElement verifyButton = utils.waitForElement(Locator.VERIFY_BUTTON, 5);
        verifyButton.click();
        utils.waitTill(300); // Let verification process start
    }

    /**
     * Verifies that user is successfully logged in by checking if the account name is visible.
     */
    public boolean isUserLoggedIn() {
        utils.waitTill(4); // Wait before validation
        WebElement accountNameElement = utils.waitForElement(Locator.ACCOUNT_NAME_ELEMENT, 10);
        return accountNameElement != null && accountNameElement.isDisplayed();
    }
}
