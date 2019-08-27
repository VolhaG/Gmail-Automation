package com.sam.cases;

import com.sam.GmailBaseTest;
import com.sam.pages.gmail.compose.GMailComposePage;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;
import com.sam.pages.gmail.sent.GMailSentPage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GMailTests extends GmailBaseTest {

    private static Logger log = LogManager.getLogger(GMailTests.class);

    private void logIn(String email, String password) {
        GMailMainPage gmailMainPage = loginPage.login(email, password);
        assertThat(gmailMainPage.exists()).as("Main page verification passed.").isTrue();
        log.info("Authentication passed successful");
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
    @Parameters({"email", "password"})
    void composeLetter(String email, String password) {
        logIn(email, password);
        GMailMainPage gmailMainPage = new GMailMainPageImpl();
        GMailComposePage gmailComposePage = gmailMainPage.compose();
        gmailMainPage = gmailComposePage.writeLetter(letterRecipient, letterTopic, letterBody)
                .sendLetter();
        assertThat(gmailMainPage.exists()).as("Composing letter passed successful.").isTrue();
        GMailSentPage gMailSentPage = gmailMainPage.openSent();
        boolean letterIsSent = gMailSentPage.checkIfLetterSent(letterRecipient, letterTopic, letterBody);
        assertThat(letterIsSent).as("Letter is sent.").isTrue();
    }

    @Test(priority = 3)
    @Parameters({"email", "password"})
    void tryToSendLetterWithoutRecipient(String email, String password) {
        logIn(email, password);
        GMailMainPage gmailMainPage = new GMailMainPageImpl();
        GMailComposePage gmailComposePage = gmailMainPage.compose();
        gmailComposePage.writeLetter("", letterTopic, letterBody).sendLetter();
        GMailSentPage gMailSentPage = gmailMainPage.openSent();
        boolean letterIsSent = gMailSentPage.checkIfLetterSent("", letterTopic, letterBody);
        assertThat(letterIsSent).as("Letter is sent without recipient.").isFalse();
    }

    @Test(priority = 4)
    @Parameters({"email", "password"})
    void tryToSendLetterWithoutSubject(String email, String password) {
        logIn(email, password);
        GMailMainPage gmailMainPage = new GMailMainPageImpl();
        GMailComposePage gmailComposePage = gmailMainPage.compose();
        gmailComposePage.writeLetter(letterRecipient, "", letterBody)
                .sendLetter();
        GMailSentPage gMailSentPage = gmailMainPage.openSent();
        boolean letterIsSent = gMailSentPage.checkIfLetterSent(letterRecipient, "", letterBody);
        assertThat(letterIsSent).as("Letter is sent without subject.").isTrue();
    }

    @Test(priority = 5)
    @Parameters({"email", "password"})
    void tryToSendLetterWithoutBody(String email, String password) {
        logIn(email, password);
        GMailMainPage gmailMainPage = new GMailMainPageImpl();
        GMailComposePage gmailComposePage = gmailMainPage.compose();
        gmailComposePage.writeLetter(letterRecipient, letterTopic, "").sendLetter();
        GMailSentPage gMailSentPage = gmailMainPage.openSent();
        boolean letterIsSent = gMailSentPage.checkIfLetterSent(letterRecipient, letterTopic, "");
        assertThat(letterIsSent).as("Letter is sent without body.").isTrue();
    }

}
