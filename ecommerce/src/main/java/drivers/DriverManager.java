package drivers;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    public static WebDriver getDriver() {
        return DriverFactory.createInstance();
    }
}