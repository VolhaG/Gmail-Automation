package com.sam;

import com.sam.pages.gmail.login.GMailLoginPage;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;
import com.sam.pageservice.LoginService;
import com.sam.utils.BaseTest;
import com.sam.utils.ScreenshotsUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.lang.reflect.Method;

public abstract class GmailBaseTest extends BaseTest {

    private static Logger log = LogManager.getLogger(GmailBaseTest.class);
    protected GMailLoginPage loginPage = null;
    protected String letterRecipient;
    protected String letterTopic;
    protected String letterBody;

    private void init() {
        if (loginPage == null) {
            loginPage = LoginService.initFor(GMailLoginPage.class);
            letterRecipient = "tt7381566@gmail.com";
            letterTopic = "New";
            letterBody = "Hello! How are you?";
        }
    }

    @Override
    public void onTestInitialization(Method method) {
        log.info("Navigate to http://www.gmail.com");
        provider.get().navigate().to("http://www.gmail.com");
        init();
    }

    @AfterMethod
    @Override
    public void clearTest(Method method, ITestResult testResult) {
        if (!testResult.isSuccess()) {
            log.info("Test is failed. Taking screenshot..");
            ScreenshotsUtil.takeScreenshot();
            log.info("Screenshot of failed method " + method.getName() + " is in " + ScreenshotsUtil.getScreenshotsPath());
        }
        GMailMainPage gmailMainPage = new GMailMainPageImpl();
        gmailMainPage.logout();
        super.clearTest(method, testResult);
    }

}
