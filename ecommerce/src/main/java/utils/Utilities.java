package utils;

import org.openqa.selenium.*;
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

//    public void waitTill(int seconds) {
//        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
//    }

    public void waitTill(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Interrupted while waiting", e);
        }
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

    /**
     * Scrolls in the specified direction until the element located by the locator is visible.
     * Supported directions: "down", "up", "left", "right".
     *
     * @param locator   The locator of the target element.
     * @param direction The scroll direction ("down", "up", "left", or "right").
     */
    public void scrollToElement(By locator, String direction) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        int scrollAmount = 300;  // scroll step in pixels

        for (int i = 0; i < 20; i++) {  // max attempts to scroll and find element
            try {
                WebElement element = new WebDriverWait(driver, Duration.ofSeconds(2))
                        .until(ExpectedConditions.visibilityOfElementLocated(locator));
                if (element.isDisplayed()) {
                    return; // Element is now visible, exit the loop
                }
            } catch (TimeoutException ignored) {
                // Element not visible yet, scroll
                switch (direction.toLowerCase()) {
                    case "down":
                        js.executeScript("window.scrollBy(0, arguments[0]);", scrollAmount);
                        break;
                    case "up":
                        js.executeScript("window.scrollBy(0, arguments[0]);", -scrollAmount);
                        break;
                    case "right":
                        js.executeScript("window.scrollBy(arguments[0], 0);", scrollAmount);
                        break;
                    case "left":
                        js.executeScript("window.scrollBy(arguments[0], 0);", -scrollAmount);
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid scroll direction: " + direction);
                }
            }
        }

        throw new RuntimeException("Element not found after scrolling in direction: " + direction);
    }
}
