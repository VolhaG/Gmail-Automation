package com.sam.cases;

import com.sam.GmailBaseTest;
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
import org.testng.annotations.*;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class GMailTests extends GmailBaseTest {

    private static Logger log = LogManager.getLogger(GMailTests.class);
    private GMailMainPage gmailMainPage;
    private GMailComposePage gmailComposePage;
    private GMailSentPage gMailSentPage;

    @Test(priority = 1, description = "Login with existing account")
    @Severity(SeverityLevel.CRITICAL)
    @Parameters({"email", "password"})
    void login(String email, String password) {
        gmailMainPage = loginPage.login(email, password);
        assertThat(gmailMainPage.exists()).as("Main page verification passed.").isTrue();
    }

    @Test(priority = 2, dependsOnMethods = "login", description = "Compose new letter")
    void composeLetter() {
        gmailComposePage = gmailMainPage.compose();
        gmailMainPage = gmailComposePage.writeLetter(letterRecipient, letterTopic, letterBody)
                .sendLetter();
        assertThat(gmailMainPage.exists()).as("Composing letter passed successful.").isTrue();
        gMailSentPage = gmailMainPage.openSent();
        boolean letterIsSent = gMailSentPage.checkIfLetterSent(letterRecipient, letterTopic, letterBody);
        assertThat(letterIsSent).as("Letter is sent.").isTrue();
    }

    @Ignore
    @Test(priority = 3, dependsOnMethods = "login", description = "Compose new letter without recipient and try to send it")
    void tryToSendLetterWithoutRecipient() {
        gmailComposePage = gmailMainPage.compose();
        gmailComposePage.writeLetter("", letterTopic, letterBody).sendLetter();
        gMailSentPage = gmailMainPage.openSent();
        boolean letterIsSent = gMailSentPage.checkIfLetterSent("", letterTopic, letterBody);
        assertThat(letterIsSent).as("Letter is sent without recipient.").isFalse();
    }

    @Ignore
    @Test(priority = 4, dependsOnMethods = "login", description = "Compose new letter without subject and try to send it")
    void tryToSendLetterWithoutSubject() {
        gmailComposePage = gmailMainPage.compose();
        gmailComposePage.writeLetter(letterRecipient, "", letterBody)
                .sendLetter();
        gMailSentPage = gmailMainPage.openSent();
        boolean letterIsSent = gMailSentPage.checkIfLetterSent(letterRecipient, "", letterBody);
        assertThat(letterIsSent).as("Letter is sent without subject.").isTrue();
    }

    @Ignore
    @Test(priority = 5, dependsOnMethods = "login", description = "Compose new letter without body and try to send it")
    void tryToSendLetterWithoutBody() {
        gmailComposePage = gmailMainPage.compose();
        gmailComposePage.writeLetter(letterRecipient, letterTopic, "").sendLetter();
        gMailSentPage = gmailMainPage.openSent();
        boolean letterIsSent = gMailSentPage.checkIfLetterSent(letterRecipient, letterTopic, "");
        assertThat(letterIsSent).as("Letter is sent without body.").isTrue();
    }

    @Description("Start tests. Navigate to http://www.gmail.com and set test's parameters.")
    @BeforeTest
    public void startTests() {
        provider.initialize("chrome_default");
        getWebDriver().navigate().to("http://www.gmail.com");
        loginPage = LoginService.initFor(GMailLoginPage.class);
        letterRecipient = "tt7381566@gmail.com";
        letterTopic = "New";
        letterBody = "Hello! How are you?";
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
