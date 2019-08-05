package com.sam.cases;

import com.sam.GmailBaseTest;
import com.sam.pages.login.LoginPage;
import com.sam.pages.main.MainPage;
import com.sam.pages.main.compose_letter.AlertAbsentRecipient;
import com.sam.pages.main.compose_letter.ComposePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExistingAccountLogin extends GmailBaseTest {
    private final static String LOGIN_TITLE = "Google";
    private final static String MAIN_TITLE = "Gmail";
    private final static String Compose_TITLE = "Gmail";
    Logger LOG = LogManager.getLogger("ExistingAccountLogin");

    @Test(priority = 1, enabled = true)
    @Parameters({"email", "password"})
    void login(String email, String password) {
        LOG.info("Start login test...");
        getWebDriver().navigate().to("https://gmail.com");
        LoginPage loginPage = new LoginPage();
        assertThat(loginPage.getContent().getTitle()).isEqualTo(LOGIN_TITLE);
        MainPage mainPage = loginPage.login(email, password);
        assertThat(mainPage.getContent().getTitle()).as("Login was passed successful.").isEqualTo(MAIN_TITLE);
        LOG.info("End login test...");
    }

    @Test(priority = 2)
    @Parameters({"email", "password"})
    void composeLetter(String email, String password) {
        LOG.info("Start composeLetter test...");
        MainPage mainPage;
        getWebDriver().navigate().to("https://gmail.com");
        LoginPage loginPage = new LoginPage();
        if (loginPage.exists()) {
            mainPage = loginPage.login(email, password);
        }
        else {
            mainPage = new MainPage();
        }
        ComposePage composePage = mainPage.compose();
        composePage.writeLetter("volha.xx@gmail.com", "New","Hello! How are you?");
        mainPage = composePage.sendLetter();
        assertThat(mainPage.getContent().getTitle()).as("Composing letter was passed successful.").isEqualTo(MAIN_TITLE);
        LOG.info("End composeLetter test...");
    }

    @Test(priority = 3)
    @Parameters({"email", "password"})
    void tryToSendLetterWithoutRecipient(String email, String password) {
        LOG.info("Start tryToSendLetterWithoutRecipient test...");
        MainPage mainPage;
        getWebDriver().navigate().to("https://gmail.com");
        LoginPage loginPage = new LoginPage();
        if (loginPage.exists()) {
            mainPage = loginPage.login(email, password);
        }
        else {
            mainPage = new MainPage();
        }
        ComposePage composePage = mainPage.compose();
        composePage.writeLetter(" ", "New","Hello! How are you?");
        composePage.sendLetter();
        Boolean alertExists = AlertAbsentRecipient.exists();
        assertThat(alertExists).isTrue().as("Alert is appeared when recipient is absent.");
        LOG.info("End tryToSendLetterWithoutRecipient test...");
    }
}
