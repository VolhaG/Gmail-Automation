package com.sam;


import com.sam.webdriver.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

public class BaseTest {

    private WebDriverProvider provider = WebDriverProvider.getInstance();

    @BeforeSuite
    public void setupSuite() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        System.setProperty("web.browser", "chrome");
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
