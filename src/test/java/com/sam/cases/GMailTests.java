package com.sam.cases;

import com.sam.GmailBaseTest;
import com.sam.entities.Letter;
import com.sam.entities.LetterImpl;
import com.sam.pages.gmail.compose.GMailComposePage;
import com.sam.pages.gmail.login.GMailLoginPage;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;
import com.sam.pages.gmail.sent.GMailSentPage;
import com.sam.pageservice.LoginService;
import com.sam.utils.ScreenshotsUtil;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class GMailTests extends GmailBaseTest {

    private static Logger log = LogManager.getLogger(GMailTests.class);
    private GMailMainPage gmailMainPage;
    private GMailComposePage gmailComposePage;
    private GMailSentPage gMailSentPage;
    private Letter letterWithoutRecipient;
    private Letter letterWithoutTopic;
    private Letter letterWithoutBody;
    private Letter letter;

    @Test(priority = 1, description = "Login with existing account")
    @Severity(SeverityLevel.CRITICAL)
    void login() {
        gmailMainPage = loginPage.login(email, password);
        assertThat(gmailMainPage.exists()).as("Main page verification passed.").isTrue();
    }

    @Test(priority = 2, dependsOnMethods = "login", description = "Compose new letter")
    void composeLetter() {
        gmailComposePage = gmailMainPage.compose();
        gmailMainPage = gmailComposePage.writeLetter(letter)
                .sendLetter();
        assertThat(gmailMainPage.exists()).as("Composing letter passed successful.").isTrue();
        gMailSentPage = gmailMainPage.openSent();
        boolean letterIsSent = gMailSentPage.checkIfLetterSent(letter);
        assertThat(letterIsSent).as("Letter is sent.").isTrue();
    }

    @Test(priority = 3, dependsOnMethods = "login", description = "Compose new letter without recipient and try to send it")
    void tryToSendLetterWithoutRecipient() {
        gmailComposePage = gmailMainPage.compose();
        gmailComposePage.writeLetter(letterWithoutRecipient).sendLetter();
        gMailSentPage = gmailMainPage.openSent();
        boolean letterIsSent = gMailSentPage.checkIfLetterSent(letterWithoutRecipient);
        assertThat(letterIsSent).as("Letter is sent without recipient.").isFalse();
    }

    @Test(priority = 4, dependsOnMethods = "login", description = "Compose new letter without subject and try to send it")
    void tryToSendLetterWithoutTopic() {
        gmailComposePage = gmailMainPage.compose();
        gmailComposePage.writeLetter(letterWithoutTopic)
                .sendLetter();
        gMailSentPage = gmailMainPage.openSent();
        boolean letterIsSent = gMailSentPage.checkIfLetterSent(letterWithoutTopic);
        assertThat(letterIsSent).as("Letter is sent without subject.").isTrue();
    }

    @Test(priority = 5, dependsOnMethods = "login", description = "Compose new letter without body and try to send it")
    void tryToSendLetterWithoutBody() {
        gmailComposePage = gmailMainPage.compose();
        gmailComposePage.writeLetter(letterWithoutBody).sendLetter();
        gMailSentPage = gmailMainPage.openSent();
        boolean letterIsSent = gMailSentPage.checkIfLetterSent(letterWithoutBody);
        assertThat(letterIsSent).as("Letter is sent without body.").isTrue();
    }

    @Description("Start tests. Navigate to http://www.gmail.com and set test's parameters.")
    @Parameters({"email","password"})
    @BeforeTest
    public void startTests(String email, String password) {

        provider.initialize("chrome_default");
        getWebDriver().navigate().to("http://www.gmail.com");
        loginPage = LoginService.initFor(GMailLoginPage.class);
        letter = letterWithoutRecipient = letterWithoutTopic = letterWithoutBody =
                new LetterImpl("tt7381566@gmail.com",  "New",  "Hello! How are you?");
        letterWithoutRecipient =  new LetterImpl("",  "New",  "Hello! How are you?");
        letterWithoutTopic = new LetterImpl("tt7381566@gmail.com",  "",  "Hello! How are you?");
        letterWithoutBody =  new LetterImpl("tt7381566@gmail.com",  "New",  "");

        initCommandLineArguments();
        if (this.email == null) {
            this.email = email;
        }
        if (this.password == null) {
            this.password = password;
        }
    }

    @Description("Log out after tests have been finished.")
    @AfterTest
    public void finishTests() {
        GMailMainPage gmailMainPage = new GMailMainPageImpl();
        gmailMainPage.logout();
        provider.get().quit();
    }

    @Description("Clear after test. Get screenshot in fail case.")
    @Override
    public void clearTest(Method method, ITestResult testResult) {
        if (!testResult.isSuccess()) {
            ScreenshotsUtil.takeScreenshot(method.getName());
        }
        log.info("Clear test");
    }

    @Override
    public void initializeTest(String configName, Method method) {
        log.info("Init test");
    }

}
