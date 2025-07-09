package drivers;

import drivers.strategies.DriverStrategy;
import drivers.strategies.DriverStrategyImplementer;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public class DriverSingleton {

    private static DriverSingleton instance = null;
    private static WebDriver driver;

    // Make constructor private to prevent misuse
    private DriverSingleton(String browser) {
        instantiateDriver(browser);
    }

    // Singleton instance creator
    public static DriverSingleton getInstance(String browser) {
        if (instance == null) {
            instance = new DriverSingleton(browser);
        }
        return instance;
    }

    // Instantiate driver using chosen strategy
    private void instantiateDriver(String strategy) {
        DriverStrategy driverStrategy = DriverStrategyImplementer.chooseStrategy(strategy);
        if (driverStrategy == null) {
            throw new RuntimeException("No valid driver strategy found for: " + strategy);
        }

        driver = driverStrategy.setStrategy();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    // Expose WebDriver instance
    public static WebDriver getDriver() {
        if (driver == null) {
            throw new IllegalStateException("WebDriver is not initialized. Call getInstance() first.");
        }
        return driver;
    }

    // Tear down driver & singleton
    public static void closeObjectInstance() {
        if (driver != null) {
            driver.quit();
        }
        instance = null;
        driver = null;
    }
}
