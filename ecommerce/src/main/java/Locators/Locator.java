package Locators;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ByAll;

public class Locator {
    public static final By LOGIN_FORM = By.id("claim-collection-container");
    public static final By SIGNIN_OPTION = By.cssSelector(".nav-action-inner");
    public static final By ACCOUNT_DROPDOWN_OPTION = By.id("nav-link-accountList");
    public static final By EMAIL_TEXT_FIELD = By.id("ap_email_login") ;
    public static final By CONTINUE_BUTTON = By.xpath("//input[@type='submit']");
    public static final By PROCEED_SIGNUP = By.cssSelector("input[type='submit']");
    public static final By MOBILE_FIELD = By.id("ap_phone_number");
    public static final By NAME_FIELD = By.id("ap_customer_number");
    public static final By PASSWORD_FIELD = By.id("ap_password");
    public static final By VERIFY_BUTTON = By.id("continue");
    public static final By SUBMIT_BUTTON = By.id("signInSubmit");
}
