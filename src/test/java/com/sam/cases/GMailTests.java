package com.sam.cases;

import com.sam.GmailBaseTest;
import com.sam.pages.base.login.LoginPage;
import com.sam.pages.base.main.MainPage;
import com.sam.pages.gmail.login.GMailLoginPage;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.compose_letter.GMailComposePage;
import com.sam.pageservice.ComposeService;
import com.sam.pageservice.LoginService;
import com.sam.pageservice.MainService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GMailTests extends GmailBaseTest {

    private final static Logger LOG = LogManager.getLogger("ExistingAccountLogin");

    private static GMailLoginPage loginPage = LoginService.initFor(GMailLoginPage.class);
    private static GMailMainPage mainPage = MainService.initFor(GMailMainPage.class);
    private static GMailComposePage composePage = ComposeService.initFor(GMailComposePage.class);

    @Test(priority = 1)
    @Parameters({"email", "password"})
    void login(String email, String password) {
        LOG.info("Start login test...");
        assertThat(loginPage.exists()).as("Login page verification.").isTrue();
        loginPage.login(email, password);
        assertThat(mainPage.exists()).as("Main page verification.").isTrue();
        mainPage.logout();
        LOG.info("End login test...");
    }


//    @Test(priority = 2, enabled = false)
//    @Parameters({"email", "password"})
//    void composeLetter(String email, String password) {
//        LOG.info("Start composeLetter test...");
//        MainPage mainPage;
//        LoginPage loginPage = new LoginPageImpl();
//        mainPage = getMainPage(email, password, loginPage);
//        ComposePage composePage = mainPage.compose();
//        composePage.writeLetter("tt7381566@gmail.com", "New","Hello! How are you?");
//        mainPage = composePage.sendLetter();
//        assertThat(mainPage.exists()).as("Composing letter was passed successful.").isTrue();
//        LOG.info("End composeLetter test...");
//    }
//
//    @Test(priority = 3, enabled = false)
//    @Parameters({"email", "password"})
//    void tryToSendLetterWithoutRecipient(String email, String password) {
//        LOG.info("Start tryToSendLetterWithoutRecipient test...");
//        MainPage mainPage;
//        LoginPage loginPage = new LoginPageImpl();
//        mainPage = getMainPage(email, password, loginPage);
//        ComposePage composePage = mainPage.compose();
//        composePage.writeLetter(" ", "New","Hello! How are you?");
//        composePage.sendLetter();
//        Boolean alertExists = AlertAbsentRecipient.exists();
//        assertThat(alertExists).isTrue().as("Alert appears when recipient is absent. The letter couldn't be sent.");
//        AlertAbsentRecipient.close();
//        if (alertExists) {
//            composePage.closePage();
//        }
//        LOG.info("End tryToSendLetterWithoutRecipient test...");
//    }
//
//    @Test(priority = 4, enabled = false)
//    @Parameters({"email", "password"})
//    void tryToSendLetterWithoutSubject(String email, String password) {
//        LOG.info("Start tryToSendLetterWithoutSubject test...");
//        MainPage mainPage;
//        LoginPage loginPage = new LoginPageImpl();
//        mainPage = getMainPage(email, password, loginPage);
//        ComposePage composePage = mainPage.compose();
//        composePage.writeLetter("tt7381566@gmail.com", " ","Hello! How are you?");
//        mainPage = composePage.sendLetter();
//        assertThat(mainPage.exists()).isTrue().as("Sending letter without subject is successful.");
//        LOG.info("End tryToSendLetterWithoutSubject test...");
//    }
//
//    @Test(priority = 5,  enabled = false)
//    @Parameters({"email", "password"})
//    void tryToSendLetterWithoutBody(String email, String password) {
//        LOG.info("Start tryToSendLetterWithoutBody test...");
//        MainPage mainPage;
//        LoginPageImpl loginPage = new LoginPageImpl();
//        mainPage = getMainPage(email, password, loginPage);
//        ComposePage composePage = mainPage.compose();
//        composePage.writeLetter("tt7381566@gmail.com", "New"," ");
//        mainPage = composePage.sendLetter();
//        assertThat(mainPage.exists()).isTrue().as("Sending letter without body is successful.");
//        LOG.info("End tryToSendLetterWithoutBody test...");
//    }

    private MainPage getMainPage(String email, String password, LoginPage loginPage) {
        MainPage mainPage;
        if (loginPage.exists()) {
            mainPage = loginPage.login(email, password);
        } else {
            //mainPage = MainService.initFor(GMailLoginPageImpl.class);
        }
        return null;
    }
}