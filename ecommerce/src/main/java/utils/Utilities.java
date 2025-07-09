package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Utility class providing reusable Selenium actions.
 * Designed to avoid static usage for better flexibility and testability.
 */
public class Utilities {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    /**
     * Constructor to initialize Utilities class with driver.
     */
    public Utilities(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.actions = new Actions(driver);
    }

    public WebElement waitForElement(By locator, int seconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return customWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void hoverOverElement(WebElement element, int seconds) {
        WebDriverWait customWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        customWait.until(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(element).perform();
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    public void type(By locator, String text) {
        WebElement element = waitForElement(locator);
        element.clear();
        element.sendKeys(text);
    }

    public boolean isDisplayed(By locator) {
        try {
            return waitForElement(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
