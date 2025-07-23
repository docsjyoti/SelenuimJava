package hooks;

import drivers.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotUtil;

public class TestHooks {

    @Before
    public void setUp() {
        DriverManager.getDriver().manage().window().maximize();
            System.out.println("Allure Results Directory: " + System.getProperty("allure.results.directory"));
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();

        try {
            if (scenario.isFailed()) {
                byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshotBytes, "image/png", scenario.getName());
                ScreenshotUtil.takeScreenshot(driver, scenario.getName().replaceAll("[^a-zA-Z0-9-_]", "_"));
            }

            // ✅ Close all open windows/tabs
            for (String handle : driver.getWindowHandles()) {
                driver.switchTo().window(handle);
                driver.close();
            }

        } catch (Exception e) {
            System.err.println("Teardown error: " + e.getMessage());
        } finally {
            try {
                driver.quit(); // ✅ Always call this, even after close()
            } catch (Exception e) {
                System.err.println("Driver quit error: " + e.getMessage());
            }
        }
    }
}