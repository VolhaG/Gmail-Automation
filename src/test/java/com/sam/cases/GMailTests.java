package com.sam.cases;

import com.sam.GmailBaseTest;
import com.sam.pages.gmail.login.GMailLoginPage;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;
import com.sam.pages.gmail.main.compose_letter.AlertAbsentRecipient;
import com.sam.pages.gmail.main.compose_letter.GMailComposePage;
import com.sam.pageservice.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class GMailTests extends GmailBaseTest {

    private static Logger log = LogManager.getLogger("ExistingAccountLogin");
    private static GMailLoginPage loginPage = LoginService.initFor(GMailLoginPage.class);

    @BeforeMethod
    @Parameters({"email", "password"})
    private void setUp(Method method, String email, String password) {
        log.info("Start " + method.getName() + " test...");
        if (!(method.getName() == "login")) {
            GMailMainPage gmailMainPage = (GMailMainPage) loginPage.login(email, password);
            assertThat(gmailMainPage.exists()).as("Main page verification passed.").isTrue();
            log.info("Authentication passed successfull");
        }
    }

    @AfterMethod
    void logOut(Method  method) {
        GMailMainPage gmailMainPage = new GMailMainPageImpl();
        gmailMainPage.logout();
        log.info("End " + method.getName() + " test...");
    }

    @Test(priority = 1)
    @Parameters({"email", "password"})
    void login(String email, String password) {
        assertThat(loginPage.exists()).as("Login page verification passed.").isTrue();
        log.info("Login page verification is passed");
        GMailMainPage gmailMainPage = (GMailMainPage) loginPage.login(email, password);
        assertThat(gmailMainPage.exists()).as("Main page verification passed.").isTrue();
        log.info("Main page verification is passed.");
    }

    @Test(priority = 2)
    void composeLetter() {
        GMailMainPage gmailMainPage = new GMailMainPageImpl();
        GMailComposePage gmailComposePage = (GMailComposePage) gmailMainPage.compose();
        gmailComposePage.writeLetter("tt7381566@gmail.com", "New","Hello! How are you?")
                .sendLetter();
        assertThat(gmailMainPage.exists()).as("Composing letter passed successful.").isTrue();
    }

    @Test(priority = 3)
    void tryToSendLetterWithoutRecipient() {
        GMailMainPage gmailMainPage = new GMailMainPageImpl();
        GMailComposePage gmailComposePage = (GMailComposePage) gmailMainPage.compose();
        gmailComposePage.writeLetter(" ", "New","Hello! How are you?").sendLetter();
        assertThat(AlertAbsentRecipient.exists()).as("Alert appears when recipient is absent. " +
                "The letter couldn't be sent.").isTrue();
        AlertAbsentRecipient.close();
        if (gmailComposePage.exists()) {
            gmailComposePage.closePage();
        }
    }

    @Test(priority = 4)
    void tryToSendLetterWithoutSubject() {
        GMailMainPage gmailMainPage = new GMailMainPageImpl();
        GMailComposePage gmailComposePage = (GMailComposePage) gmailMainPage.compose();
        gmailComposePage.writeLetter("tt7381566@gmail.com", " ","Hello! How are you?")
                        .sendLetter();
        assertThat(gmailMainPage.exists()).as("Sending letter without subject is successful.").isTrue();
    }

    @Test(priority = 5)
    void tryToSendLetterWithoutBody() {
        GMailMainPage gmailMainPage = new GMailMainPageImpl();
        GMailComposePage gmailComposePage = (GMailComposePage)gmailMainPage.compose();
        gmailComposePage.writeLetter("tt7381566@gmail.com", "New"," ").sendLetter();
        assertThat(gmailMainPage.exists()).as("Sending letter without body is successful.").isTrue();
    }

}
