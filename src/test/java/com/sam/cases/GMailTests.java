package com.sam.cases;

import com.sam.GmailBaseTest;
import com.sam.pages.gmail.compose.GMailComposePage;
import com.sam.pages.gmail.login.GMailLoginPage;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;
import com.sam.pageservice.LoginService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.*;

import java.lang.reflect.Method;

import static org.assertj.core.api.Assertions.assertThat;

public class GMailTests extends GmailBaseTest {

    private static Logger log = LogManager.getLogger("ExistingAccountLogin");
    private GMailLoginPage loginPage = null;// = LoginService.initFor(GMailLoginPage.class);

    private void init(){
        if (loginPage == null) {
         loginPage = LoginService.initFor(GMailLoginPage.class);}
    }

    @BeforeMethod
    @Parameters({"email", "password"})
    void setUp(Method method, String email, String password) {
        init();
        log.info("Start " + method.getName() + " test...");
        if (!(method.getName().equals("login"))) {
            GMailMainPage gmailMainPage = loginPage.login(email, password);
            assertThat(gmailMainPage.exists()).as("Main page verification passed.").isTrue();
            log.info("Authentication passed successful");
        }
    }

    @AfterMethod
    void finish(Method  method) {
        GMailMainPage gmailMainPage = new GMailMainPageImpl();
        gmailMainPage.logout();
        log.info("End " + method.getName() + " test...");
    }

    @Test(priority = 1)
    @Parameters({"email", "password"})
    void login(String email, String password) {
        assertThat(loginPage.exists()).as("Login page verification passed.").isTrue();
        log.info("Login page verification passed");
        GMailMainPage gmailMainPage = loginPage.login(email, password);
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
        //TODO check that letter sent
    }

    @Test(priority = 3)
    void tryToSendLetterWithoutRecipient() {
        GMailMainPage gmailMainPage = new GMailMainPageImpl();
        GMailComposePage gmailComposePage = (GMailComposePage) gmailMainPage.compose();
        gmailComposePage.writeLetter(" ", "New","Hello! How are you?").sendLetter();
        gmailComposePage.closePage();
        //TODO check that letter wasn't sent
    }

    @Test(priority = 4)
    void tryToSendLetterWithoutSubject() {
        GMailMainPage gmailMainPage = new GMailMainPageImpl();
        GMailComposePage gmailComposePage = (GMailComposePage) gmailMainPage.compose();
        gmailComposePage.writeLetter("tt7381566@gmail.com", " ","Hello! How are you?")
                        .sendLetter();
        //TODO check that letter sent
        assertThat(gmailMainPage.exists()).as("Sending letter without subject is successful.").isTrue();
    }

    @Test(priority = 5)
    void tryToSendLetterWithoutBody() {
        GMailMainPage gmailMainPage = new GMailMainPageImpl();
        GMailComposePage gmailComposePage = (GMailComposePage)gmailMainPage.compose();
        gmailComposePage.writeLetter("tt7381566@gmail.com", "New"," ").sendLetter();
        //TODO check that letter sent
        assertThat(gmailMainPage.exists()).as("Sending letter without body is successful.").isTrue();
    }

}
