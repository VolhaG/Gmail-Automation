package com.sam.pages.main.compose_letter;

import com.sam.PageImpl;
import com.sam.pages.main.MainPageImpl;

public class ComposePageImpl extends PageImpl<ComposeImpl> implements ComposePage {

    public ComposePageImpl() {
        super(new ComposeImpl());
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
    public MainPageImpl sendLetter(){
       content.sendLetter();
       return new MainPageImpl();
    }

    @Override
    public MainPageImpl closePage(){
        content.close();
        return new MainPageImpl();
    }

}
