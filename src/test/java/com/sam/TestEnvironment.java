package com.sam;

import org.testng.annotations.BeforeSuite;

public abstract class TestEnvironment {

    public static void setup() {
//        System.setProperty("webdriver.chrome.driver", "C:/Projects/drivers/chromedriver.exe");
//        System.setProperty("webdriver.gecko.driver", "C:/Projects/drivers/geckodriver");
//        System.setProperty("webdriver.edge.driver", "C:/Projects/drivers/edgedriver");
        System.setProperty("webdriver.chrome.driver", "/Users/Olya/Applications/chromedriver");
//        System.setProperty("webdriver.gecko.driver", "/Users/Olya/Applications/geckodriver");
        System.setProperty("web.browser", "chrome");
    }
}

