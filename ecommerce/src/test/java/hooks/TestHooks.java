package hooks;

import drivers.DriverSingleton;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import utils.FrameworkProperties;

public class TestHooks {
    private final FrameworkProperties frameworkProperties = new FrameworkProperties();

    @Before
    public void setUp() {
        String browser = frameworkProperties.getProperty("browser");
        DriverSingleton.getInstance(browser);
        WebDriver driver = DriverSingleton.getDriver();
        driver.get("https://www.amazon.in");
    }

    @After
    public void tearDown() {
        DriverSingleton.closeObjectInstance();          // Quits and nulls driver/instance
    }
}
