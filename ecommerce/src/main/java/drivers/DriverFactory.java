package drivers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utils.FrameworkProperties;

public class DriverFactory {
    public static WebDriver createInstance() {

        WebDriverManager.chromedriver().setup();
        ChromeDriver driver = new ChromeDriver();

        String browser = FrameworkProperties.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            return driver;
        } else if (browser.equalsIgnoreCase("firefox")) {
            return new FirefoxDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }
    }
}
