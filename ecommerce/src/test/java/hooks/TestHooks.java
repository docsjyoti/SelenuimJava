package hooks;

import drivers.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotUtil;

public class TestHooks {

    @Before
    public void setUp() {
        DriverManager.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();

        if (scenario.isFailed()) {
            ScreenshotUtil.takeScreenshot(driver, scenario.getName());
        }

        if (driver != null) {
            driver.quit();  // Directly quit the driver if not null
        }
    }
}
