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
    public static final By ACCOUNT_NAME_ELEMENT = By.id("nav-link-accountList-nav-line-1");
    public static final By AMAZON_LOGO = By.id("nav-logo-sprites");
    public static final By SEARCH_BAR = By.id("twotabsearchtextbox");
    public static final By SEARCH_ICON = By.id("nav-search-submit-button");
    public static final By TOP_BANNER = By.cssSelector("#desktop-banner"); // Replace with correct selector
    public static final By MENU_BAR = By.id("nav-main");
    public static final By DEALS_SECTION = By.xpath("//h2[contains(text(),'Deals')]");
    public static final By CATEGORY_OPTION = By.xpath("//span[@class='hm-icon-label']");
    public static final By SEARCH_RESULTS_CONTAINER = By.cssSelector("div.s-main-slot");
    public static final By CATEGORY_PAGE_HEADER = By.id("hmenu-customer-name"); // Adjust as per category
    public static final By CATEGORY_CLOSE_ICON = By.id("hmenu-close-icon");
    public static final By RESULT_HEADER= By.xpath("//h2[normalize-space()='Results']");
}
