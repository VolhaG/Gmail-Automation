package com.sam.cases;

import com.sam.GmailBaseTest;
import com.sam.pages.gmail.login.GMailLoginPage;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.compose_letter.AlertAbsentRecipient;
import com.sam.pageservice.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GMailTests extends GmailBaseTest {

    private static Logger log = LogManager.getLogger("ExistingAccountLogin");
    private static GMailLoginPage loginPage = LoginService.initFor(GMailLoginPage.class);

//    @AfterMethod
//    void logout(){
//        mainPage.logout();
//    }

    @Test(priority = 1)
    @Parameters({"email", "password"})
    void login(String email, String password) {
        log.info("Start login test...");
        assertThat(loginPage.exists()).as("Login page verification is passed.").isTrue();
        GMailMainPage gmailMainPage = (GMailMainPage) loginPage.login(email, password);
        assertThat(gmailMainPage.exists()).as("Main page verification is passed.").isTrue();
        gmailMainPage.logout();
        log.info("End login test...");
    }

//    @Test(priority = 2)
//    @Parameters({"email", "password"})
//    void composeLetter(String email, String password) {
//        log.info("Start composeLetter test...");
//        loginPage.login(email, password);
//        mainPage.compose();
//        composePage.writeLetter("tt7381566@gmail.com", "New","Hello! How are you?");
//        composePage.sendLetter();
//        assertThat(mainPage.exists()).as("Composing letter was passed successful.").isTrue();
//        log.info("End composeLetter test...");
//    }
//
//    @Test(priority = 3)
//    @Parameters({"email", "password"})
//    void tryToSendLetterWithoutRecipient(String email, String password) {
//        log.info("Start tryToSendLetterWithoutRecipient test...");
//        loginPage.login(email, password);
//        mainPage.compose();
//        composePage.writeLetter(" ", "New","Hello! How are you?");
//        composePage.sendLetter();
//        assertThat(AlertAbsentRecipient.exists()).isTrue().as("Alert appears when recipient is absent. The letter couldn't be sent.");
//        AlertAbsentRecipient.close();
//        if (composePage.exists()) {
//            composePage.closePage();
//        }
//        log.info("End tryToSendLetterWithoutRecipient test...");
//    }
//
//    @Test(priority = 4)
//    @Parameters({"email", "password"})
//    void tryToSendLetterWithoutSubject(String email, String password) {
//        log.info("Start tryToSendLetterWithoutSubject test...");
//        loginPage.login(email, password);
//        mainPage.compose();
//        composePage.writeLetter("tt7381566@gmail.com", " ","Hello! How are you?");
//        composePage.sendLetter();
//        assertThat(mainPage.exists()).as("Sending letter without subject is successful.").isTrue();
//        log.info("End tryToSendLetterWithoutSubject test...");
//    }
//
//    @Test(priority = 5)
//    @Parameters({"email", "password"})
//    void tryToSendLetterWithoutBody(String email, String password) {
//        log.info("Start tryToSendLetterWithoutBody test...");
//        loginPage.login(email, password);
//        mainPage.compose();
//        composePage.writeLetter("tt7381566@gmail.com", "New"," ");
//        composePage.sendLetter();
//        assertThat(mainPage.exists()).isTrue().as("Sending letter without body is successful.");
//        log.info("End tryToSendLetterWithoutBody test...");
//    }

}
