package hooks;

import drivers.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario; // ✅ Correct import
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotUtil;

public class TestHooks {

    private WebDriver driver;

    @Before
    public void setUp() {
        driver = DriverManager.getDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                ScreenshotUtil.takeScreenshot(driver, scenario.getName().replaceAll(" ", "_"));
            }

            // Optional: Clear cache and session
            driver.get("https://www.google.com");
            Thread.sleep(200);

            ((JavascriptExecutor) driver).executeScript(
                    "window.localStorage.clear(); window.sessionStorage.clear();"
            );
            driver.manage().deleteAllCookies();

        } catch (Exception e) {
            System.out.println("⚠️ Could not clear browser data: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit();
            }
        }
    }
}
