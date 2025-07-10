package pages;

import org.openqa.selenium.WebElement;
import Locators.Locator;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.Utilities;

public class HomePage {
    private WebDriver driver;
    private Utilities utils;
    private static final Logger log = LoggerFactory.getLogger(HomePage.class);

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.utils = new Utilities(driver);
    }

    /**
     * Verifies if the Amazon logo is visible on the homepage.
     * @return true if logo is displayed, false otherwise.
     */
    public boolean isLogoVisible() {
        return utils.waitForElement(Locator.AMAZON_LOGO, 5).isDisplayed();
    }

    /**
     * Verifies if the search bar is present on the homepage.
     * @return true if search bar is displayed, false otherwise.
     */
    public boolean isSearchBarVisible() {
        return utils.waitForElement(Locator.SEARCH_BAR, 5).isDisplayed();
    }

    /**
     * Checks if the search icon is visible and clickable (enabled).
     * @return true if search icon is displayed and enabled.
     */
    public boolean isSearchIconFunctional() {
        WebElement searchIcon = utils.waitForElement(Locator.SEARCH_ICON, 5);
        return searchIcon.isDisplayed() && searchIcon.isEnabled();
    }

    /**
     * Checks if the top promotional banner is visible on the homepage.
     * @return true if banner is displayed, false otherwise.
     */
    public boolean isTopBannerVisible() {
        return utils.waitForElement(Locator.TOP_BANNER, 15).isDisplayed();
    }

    /**
     * Verifies if the main menu bar is present on the homepage.
     * @return true if menu bar is displayed.
     */
    public boolean isMenuBarVisible() {
        return utils.waitForElement(Locator.MENU_BAR, 5).isDisplayed();
    }

    /**
     * Checks if the "Deals" or other headline sections are visible on homepage.
     * @return true if the section is visible.
     */
    public boolean isHeadlinesVisible() {
        return utils.waitForElement(Locator.DEALS_SECTION, 15).isDisplayed();
    }

    /**
     * Clicks on any category link on the homepage (e.g., Electronics, Books).
     */
    public void clickAnyCategory() {
        WebElement category = utils.waitForElement(Locator.CATEGORY_OPTION, 5);
        category.click();
    }

    /**
     * Enters a given search keyword into the Amazon search bar.
     * @param keyword the text to be entered in the search input field.
     */
    public void enterSearchKeyword(String keyword) {
        WebElement searchBox = utils.waitForElement(Locator.SEARCH_BAR, 30);
        searchBox.clear();
        utils.waitTill(100);
        searchBox.sendKeys(keyword);
    }

    /**
     * Clicks on the search icon to initiate the product search.
     */
    public void clickSearchIcon() {
        utils.waitForElement(Locator.SEARCH_ICON, 5).click();
    }

    /**
     * Clicks on the Amazon logo, typically to return to the homepage.
     */
    public void clickAmazonLogo() {
        utils.waitForElement(Locator.AMAZON_LOGO, 15).click();
    }

    /**
     * Verifies if the search results container is visible after a search action.
     * @return true if search results section is present.
     */
    public boolean isSearchResultVisible() {
        utils.waitForElement(Locator.SEARCH_RESULTS_CONTAINER, 20);
        utils.scrollToElement(Locator.RESULT_HEADER,"down");
        return utils.waitForElement(Locator.RESULT_HEADER, 20)!= null;
    }

    /**
     * Validates if the category page header is displayed after selecting a category.
     * @return true if category page is loaded.
     */
    public boolean isCategoryPageDisplayed() {
        return utils.waitForElement(Locator.CATEGORY_PAGE_HEADER, 10) != null;
    }

    /**
     * Closes the category page or popup by clicking the close icon (if present).
     */
    public void closeCategory() {
        utils.waitForElement(Locator.CATEGORY_CLOSE_ICON, 15);
        utils.click(Locator.CATEGORY_CLOSE_ICON);
        utils.waitTill(200);
    }
}