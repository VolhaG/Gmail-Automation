package com.sam.pages.gmail.main.compose_letter;

import com.sam.PageImpl;
import com.sam.pages.base.compose.Compose;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;

public class GMailComposePageImpl extends PageImpl<Compose> implements GMailComposePage {

    public GMailComposePageImpl() {
        super((Compose) new GMailComposeImpl());
    }

    @Override
    public void writeLetter(String recipient, String subject, String letter) {
        content.setRecipient(recipient);
        content.setSubject(subject);
        content.writeLetter(letter);
    }

    @Override
    public void writeLetter(String to, String topic, String letterBody,  String letterStart, String letterEnd) {
        content.setRecipient(to);
        content.setSubject(topic);
        content.writeLetter(letterStart,letterBody,letterEnd);
    }

    @Override
    public GMailMainPage sendLetter(){
       content.sendLetter();
       return new GMailMainPageImpl();
    }

    @Override
    public GMailMainPage closePage(){
        content.close();
        return new GMailMainPageImpl();
    }

}
