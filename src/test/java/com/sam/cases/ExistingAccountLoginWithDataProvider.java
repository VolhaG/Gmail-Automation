package com.sam.cases;

import com.sam.GmailBaseTest;
import com.sam.data.StaticDataProvider;
import com.sam.pages.login.LoginPageImpl;
import com.sam.pages.main.MainPageImpl;
import com.sam.pages.main.compose_letter.AlertAbsentRecipient;
import com.sam.pages.main.compose_letter.ComposePageImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExistingAccountLoginWithDataProvider extends GmailBaseTest {

    private Logger LOG = LogManager.getLogger("ExistingAccountLogin");

    @BeforeMethod
    void navigateToGmail(){
        getWebDriver().navigate().to("https://gmail.com");
    }

    @Test(priority = 1, dataProvider = "login", dataProviderClass = StaticDataProvider.class, enabled = true)
    void login(String email, String password) {
        LOG.info("Start login test...");
        LoginPageImpl loginPage = new LoginPageImpl();
        assertThat(loginPage.exists()).isTrue();
        MainPageImpl mainPage = loginPage.login(email, password);
        assertThat(mainPage.exists()).as("Login was passed successful.").isTrue();
        LOG.info("End login test...");
    }

    @Test(priority = 2, dataProvider = "login", dataProviderClass = StaticDataProvider.class, enabled = true)
    void composeLetter(String email, String password) {
        LOG.info("Start composeLetter test...");
        MainPageImpl mainPage;
        LoginPageImpl loginPage = new LoginPageImpl();
        mainPage = getMainPage(email, password, loginPage);
        ComposePageImpl composePage = mainPage.compose();
        composePage.writeLetter("tt7381566@gmail.com", "New","Hello! How are you?");
        mainPage = composePage.sendLetter();
        assertThat(mainPage.exists()).as("Composing letter was passed successful.").isTrue();
        LOG.info("End composeLetter test...");
    }

    @Test(priority = 3, dataProvider = "login", dataProviderClass = StaticDataProvider.class, enabled = true)
    void tryToSendLetterWithoutRecipient(String email, String password) {
        LOG.info("Start tryToSendLetterWithoutRecipient test...");
        MainPageImpl mainPage;
        LoginPageImpl loginPage = new LoginPageImpl();
        mainPage = getMainPage(email, password, loginPage);
        ComposePageImpl composePage = mainPage.compose();
        composePage.writeLetter(" ", "New","Hello! How are you?");
        composePage.sendLetter();
        Boolean alertExists = AlertAbsentRecipient.exists();
        assertThat(alertExists).isTrue().as("Alert appears when recipient is absent. The letter couldn't be sent.");
        AlertAbsentRecipient.close();
        if (alertExists) {
            composePage.closePage();
        }
        LOG.info("End tryToSendLetterWithoutRecipient test...");
    }

    @Test(priority = 4, dataProvider = "login", dataProviderClass = StaticDataProvider.class, enabled = true)
    void tryToSendLetterWithoutSubject(String email, String password) {
        LOG.info("Start tryToSendLetterWithoutSubject test...");
        MainPageImpl mainPage;
        LoginPageImpl loginPage = new LoginPageImpl();
        mainPage = getMainPage(email, password, loginPage);
        ComposePageImpl composePage = mainPage.compose();
        composePage.writeLetter("tt7381566@gmail.com", " ","Hello! How are you?");
        mainPage = composePage.sendLetter();
        assertThat(mainPage.exists()).isTrue().as("Sending letter without subject is successful.");
        LOG.info("End tryToSendLetterWithoutSubject test...");
    }

    @Test(priority = 5, dataProvider = "login", dataProviderClass = StaticDataProvider.class, enabled = true)
    void tryToSendLetterWithoutBody(String email, String password) {
        LOG.info("Start tryToSendLetterWithoutBody test...");
        MainPageImpl mainPage;
        LoginPageImpl loginPage = new LoginPageImpl();
        mainPage = getMainPage(email, password, loginPage);
        ComposePageImpl composePage = mainPage.compose();
        composePage.writeLetter("tt7381566@gmail.com", "New"," ");
        mainPage = composePage.sendLetter();
        assertThat(mainPage.exists()).isTrue().as("Sending letter without body is successful.");
        LOG.info("End tryToSendLetterWithoutBody test...");
    }

    private MainPageImpl getMainPage(String email, String password, LoginPageImpl loginPage) {
        MainPageImpl mainPage;
        if (loginPage.exists()) {
            mainPage = loginPage.login(email, password);
        } else {
            mainPage = new MainPageImpl();
        }
        return mainPage;
    }
}