package com.sam;


import com.sam.webdriver.WebDriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {

    public WebDriverProvider provider = WebDriverProvider.getInstance();

    protected WebDriver getWebDriver() {
        WebDriver driver = provider.get();
        return driver;
    }

    @BeforeClass
    @Parameters("configurationName")
    public void initializeTest(@Optional("") String configName) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        provider.initialize(configName);
    }

    @AfterClass
    public void clearTest() {
        provider.end();
    }
}
