package drivers;

import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        if (driver == null) {
            driver = DriverFactory.createInstance();
        }
        return driver;
    }
}