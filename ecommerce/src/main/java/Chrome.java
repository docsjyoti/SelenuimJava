import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class Chrome implements DriverStrategy {

    ChromeOptions options= new ChromeOptions();
    WebDriver driver = new ChromeDriver();

    public WebDriver setStrategy() {
        WebDriverManager.chromedriver().setup();
        options.setExperimentalOption("useAutomationExtension",false);
        options.addArguments("--no-sandbox");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return new ChromeDriver(options);
    }

    public void quitDriver() {
        driver.quit();
    }
}
