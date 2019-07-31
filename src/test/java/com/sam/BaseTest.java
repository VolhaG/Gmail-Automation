package com.sam;


import com.sam.webdriver.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {

    private WebDriverProvider provider = WebDriverProvider.getInstance();

    @BeforeSuite
    public void setupSuite() {
        TestEnvironment.setup();
    }

    protected WebDriver getWebDriver() {
        WebDriver driver = provider.get();
        return driver;
    }

    @BeforeClass
    @Parameters("configurationName")
    public void initializeTest(@Optional("") String configName) {
        provider.initialize(configName);
    }

    @AfterClass
    public void clearTest() {
        provider.end();
    }
}
