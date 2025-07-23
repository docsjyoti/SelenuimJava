package hooks;

import drivers.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotUtil;

import java.io.ByteArrayInputStream;

public class TestHooks {

    @Before
    public void setUp() {
        WebDriver driver = DriverManager.getDriver();
        driver.manage().window().maximize();
        System.out.println("Allure Results Directory: " + System.getProperty("allure.results.directory"));
    }

    @After
    public void tearDown(Scenario scenario) {
        WebDriver driver = DriverManager.getDriver();

        if (driver != null) {
            try {
                if (scenario.isFailed()) {
                    byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                    Allure.addAttachment("Failed Screenshot", new ByteArrayInputStream(screenshotBytes));
                    ScreenshotUtil.takeScreenshot(driver, scenario.getName().replaceAll("[^a-zA-Z0-9-_]", "_"));
                }
            } catch (Exception e) {
                System.err.println("Error taking screenshot: " + e.getMessage());
            } finally {
                DriverManager.quitDriver();
            }
        }
    }
}
