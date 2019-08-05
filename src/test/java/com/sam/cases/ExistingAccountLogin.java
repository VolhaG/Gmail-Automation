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

    private Logger LOG = LogManager.getLogger("ExistingAccountLogin");

    @Test(priority = 1, enabled = true)
    @Parameters({"email", "password"})
    void login(String email, String password) {
        LOG.info("Start login test...");
        getWebDriver().navigate().to("https://gmail.com");
        LoginPage loginPage = new LoginPage();
        assertThat(loginPage.exists()).isTrue();
        MainPage mainPage = loginPage.login(email, password);
        assertThat(mainPage.exists()).as("Login was passed successful.").isTrue();
        LOG.info("End login test...");
    }

    @Test(priority = 2, enabled = true)
    @Parameters({"email", "password"})
    void composeLetter(String email, String password) {
        LOG.info("Start composeLetter test...");
        MainPage mainPage;
        getWebDriver().navigate().to("https://gmail.com");
        LoginPage loginPage = new LoginPage();
        mainPage = getMainPage(email, password, loginPage);
        ComposePage composePage = mainPage.compose();
        composePage.writeLetter("tt7381566@gmail.com", "New","Hello! How are you?");
        mainPage = composePage.sendLetter();
        assertThat(mainPage.exists()).as("Composing letter was passed successful.").isTrue();
        LOG.info("End composeLetter test...");
    }

    @Test(priority = 3, enabled = true)
    @Parameters({"email", "password"})
    void tryToSendLetterWithoutRecipient(String email, String password) {
        LOG.info("Start tryToSendLetterWithoutRecipient test...");
        MainPage mainPage;
        getWebDriver().navigate().to("https://gmail.com");
        LoginPage loginPage = new LoginPage();
        mainPage = getMainPage(email, password, loginPage);
        ComposePage composePage = mainPage.compose();
        composePage.writeLetter(" ", "New","Hello! How are you?");
        composePage.sendLetter();
        Boolean alertExists = AlertAbsentRecipient.exists();
        assertThat(alertExists).isTrue().as("Alert is appeared when recipient is absent.");
        AlertAbsentRecipient.close();
        if (alertExists) {
            composePage.closePage();
        }
        LOG.info("End tryToSendLetterWithoutRecipient test...");
    }

    @Test(priority = 4, enabled = true)
    @Parameters({"email", "password"})
    void tryToSendLetterWithoutSubject(String email, String password) {
        LOG.info("Start tryToSendLetterWithoutSubject test...");
        MainPage mainPage;
        getWebDriver().navigate().to("https://gmail.com");
        LoginPage loginPage = new LoginPage();
        mainPage = getMainPage(email, password, loginPage);
        ComposePage composePage = mainPage.compose();
        composePage.writeLetter("tt7381566@gmail.com", " ","Hello! How are you?");
        mainPage = composePage.sendLetter();
        assertThat(mainPage.exists()).isTrue().as("Sending letter without subject is successful.");
        LOG.info("End tryToSendLetterWithoutSubject test...");
    }

    private MainPage getMainPage(String email, String password, LoginPage loginPage) {
        MainPage mainPage;
        if (loginPage.exists()) {
            mainPage = loginPage.login(email, password);
        } else {
            mainPage = new MainPage();
        }
        return mainPage;
    }
}
