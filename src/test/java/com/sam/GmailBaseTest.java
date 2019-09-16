package com.sam;

import com.beust.jcommander.JCommander;
import com.sam.pages.gmail.login.GMailLoginPage;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;
import com.sam.pageservice.LoginService;
import com.sam.utils.BaseTest;
import com.sam.utils.ScreenshotsUtil;
import com.sam.utils.argumentsToUseWithJSCommander.CommandLineArgs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import java.lang.reflect.Method;

public abstract class GmailBaseTest extends BaseTest {

    private static Logger log = LogManager.getLogger(GmailBaseTest.class);
    protected GMailLoginPage loginPage = null;
    protected String email;
    protected String password;

    private void init() {
        if (loginPage == null) {
            loginPage = LoginService.initFor(GMailLoginPage.class);
        }
    }

    protected void initCommandLineArguments() {
        CommandLineArgs args = new CommandLineArgs();
        String mail = System.getProperty("email");
        String pwd = System.getProperty("password");
        String[] argv;
        if (mail != null && pwd != null) {
            argv = new String[]{"-email", mail, "-password", pwd};
            if (mail != null) {
                argv = new String[]{"-email", mail};
            }
            if (pwd != null) {
                argv = new String[]{"-password", pwd};
            }
            if (argv.length > 0) {
                JCommander.newBuilder()
                        .addObject(args)
                        .build()
                        .parse(argv);

                email = args.email;
                password = args.password;
            }
        }
    }

    @Override
    public void onTestInitialization(Method method) {
        log.info("Navigate to http://www.gmail.com");
        provider.get().navigate().to("http://www.gmail.com");
        init();
    }

    @AfterMethod(alwaysRun = true)
    @Override
    public void clearTest(Method method, ITestResult testResult) {
        if (!testResult.isSuccess()) {
            log.info("Test is failed. Taking screenshot..");
            String methodName = method.getName();
            ScreenshotsUtil.takeScreenshot(methodName);
            log.info("Got screenshot of failed method: {}", methodName);
        }
        GMailMainPage gmailMainPage = new GMailMainPageImpl();
        gmailMainPage.logout();
        super.clearTest(method, testResult);
        provider.get().quit();
    }

}
