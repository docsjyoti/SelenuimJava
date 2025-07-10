package hooks;

import drivers.DriverManager;
import io.cucumber.java.Before;
import io.cucumber.java.After;

public class TestHooks {

    @Before
    public void setUp() {
        DriverManager.getDriver().manage().window().maximize();
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}