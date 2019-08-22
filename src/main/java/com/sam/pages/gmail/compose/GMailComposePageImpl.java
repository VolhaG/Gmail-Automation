package com.sam.pages.gmail.compose;

import com.sam.pages.PageImpl;
import com.sam.pages.base.compose.Compose;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GMailComposePageImpl extends PageImpl<Compose> implements GMailComposePage {

    private static Logger log = LogManager.getLogger("GMailComposePageImpl");

    public GMailComposePageImpl() {
        super(new GMailComposeImpl());
    }

    @Override
    public GMailComposePage writeLetter(String recipient, String topic, String letter) {
        log.info("Composing letter..");
        content.setRecipient(recipient);
        content.setTopic(topic);
        content.writeLetter(letter);
        return this;
    }

    @Override
    public GMailComposePage writeLetter(String to, String topic, String letterBody, String letterStart, String letterEnd) {
        log.info("Composing letter..");
        content.setRecipient(to);
        content.setTopic(topic);
        content.writeLetter(letterStart, letterBody, letterEnd);
        return this;
    }

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

    @Override
    public GMailMainPage closePage() {
        log.info("Closing compose page..");
         content.close();
        return new GMailMainPageImpl();
    }

}
