package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import pages.HomePage;
import drivers.DriverManager;
import org.junit.Assert;

public class HomeStep {

    WebDriver driver = DriverManager.getDriver();
    HomePage homePage = new HomePage(driver);

    @Given("User is on the Amazon homepage")
    public void user_is_on_amazon_homepage() {
        driver.get("https://www.amazon.in/");
    }

    @Then("Amazon logo should be visible")
    public void amazon_logo_should_be_visible() {
        Assert.assertTrue("Amazon logo is not visible", homePage.isLogoVisible());
    }

    @Then("Search bar should be visible")
    public void search_bar_should_be_visible() {
        Assert.assertTrue("Search bar not visible", homePage.isSearchBarVisible());
    }

    @Then("Search icon should be functional")
    public void search_icon_should_be_functional() {
        Assert.assertTrue("Search icon not functional", homePage.isSearchIconFunctional());
    }

    @Then("Top banner should be visible")
    public void top_banner_should_be_visible() {
        Assert.assertTrue("Top banner is not visible", homePage.isTopBannerVisible());
    }

    @Then("Menu bar with categories should be visible")
    public void menu_bar_should_be_visible() {
        Assert.assertTrue("Menu bar/categories not visible", homePage.isMenuBarVisible());
    }

    @Then("Headlines or deals section should be visible")
    public void deals_section_should_be_visible() {
        Assert.assertTrue("Headlines or deals section not visible", homePage.isHeadlinesVisible());
    }

    @When("User clicks on any category in menu bar")
    public void user_clicks_on_any_category() {
        homePage.clickAnyCategory();
    }

    @Then("Category page should be displayed")
    public void category_page_should_be_displayed() {
        Assert.assertTrue("Category page not loaded", homePage.isCategoryPageDisplayed());
    }

    @And("User navigates back to homepage")
    public void user_navigates_back_to_homepage() {
        homePage.closeCategory();  // Or use clickAmazonLogo() if required
    }

    @When("User enters {string} in search bar and clicks search icon")
    public void user_enters_keyword_and_searches(String keyword) {
        homePage.enterSearchKeyword(keyword);
        homePage.clickSearchIcon();
    }

    @Then("Search results should be displayed")
    public void search_results_should_be_displayed() {
        Assert.assertTrue("Search results not visible", homePage.isSearchResultVisible());
    }

    @And("User clicks Amazon logo to return to homepage")
    public void user_clicks_logo_to_homepage() {
        homePage.clickAmazonLogo();
    }
}