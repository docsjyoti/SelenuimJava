package hooks;

import drivers.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotUtil;

public class TestHooks {

    private final WebDriver driver= DriverManager.getDriver();

    @Before
    public void setUp() {
        DriverManager.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) {
        try {
            if (scenario.isFailed()) {
                ScreenshotUtil.takeScreenshot(driver, scenario.getName().replaceAll(" ", "_"));
            }
            // 🧹 Clear cache and session storage (optional, if needed)
            driver.get("https://www.google.com"); // open a valid page instead of chrome://
            Thread.sleep(2000); // let page load

            ((JavascriptExecutor) driver).executeScript(
                    "window.localStorage.clear(); window.sessionStorage.clear();");
            driver.manage().deleteAllCookies();

        } catch (Exception e) {
            System.out.println("⚠️ Could not clear browser data: " + e.getMessage());
        } finally {
            if (driver != null) {
                driver.quit(); // ✅ close driver LAST
            }
        }
    }
}