package com.sam.pages.gmail.main.compose_letter;

import com.sam.pages.PageImpl;
import com.sam.pages.base.compose.Compose;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;

public class GMailComposePageImpl extends PageImpl<Compose> implements GMailComposePage {

    public GMailComposePageImpl() {
        super(new GMailComposeImpl());
    }

    @Override
    public  GMailComposePage writeLetter(String recipient, String subject, String letter) {
        content.setRecipient(recipient);
        content.setSubject(subject);
        content.writeLetter(letter);
        return this;
    }

    @Override
    public  GMailComposePage writeLetter(String to, String topic, String letterBody, String letterStart, String letterEnd) {
        content.setRecipient(to);
        content.setSubject(topic);
        content.writeLetter(letterStart, letterBody, letterEnd);
        return this;
    }

    @Override
    public GMailMainPage sendLetter() {
        content.sendLetter();
        if (AlertAbsentRecipient.exists()) {
            AlertAbsentRecipient.close();
        }
        return new GMailMainPageImpl();
    }

    @Override
    public GMailMainPage closePage() {
        content.close();
        return new GMailMainPageImpl();
    }

}
