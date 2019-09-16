package com.sam.pages.gmail.compose;

import com.sam.annotations.ElementVerification;
import com.sam.pages.PageImpl;
import com.sam.pages.base.compose.Compose;
import com.sam.entities.Letter;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class GMailComposePageImpl extends PageImpl<Compose> implements GMailComposePage {

    private static Logger log = LogManager.getLogger(GMailComposePageImpl.class);

    @ElementVerification(description = "Root element verification on compose page")
    public static final By rootElement = By.cssSelector("div.dC div[role = 'button']:nth-child(1)");

    public GMailComposePageImpl() {
        super(new GMailComposeImpl());
    }

    @Description("Try to write letter: {}")
    @Step("Write letter: {0}")
    @Override
    public GMailComposePage writeLetter(Letter letter) {
        log.info("Composing letter..");
        content.setRecipient(letter.getRecipient());
        content.setTopic(letter.getTopic());
        content.writeLetter(letter.getLetterContent());
        return this;
    }

    @Step("Send letter")
    @Override
    public GMailMainPage sendLetter() {
        log.info("Sending letter..");
        content.sendLetter();
        if (AlertAbsentRecipient.exists()) {
            AlertAbsentRecipient.close();
        }
        if (exists()) {
            content.close();
        }
        return new GMailMainPageImpl();
    }

    @Step("Close composing letter page")
    @Override
    public GMailMainPage closePage() {
        log.info("Closing compose page..");
        content.close();
        return new GMailMainPageImpl();
    }

}
