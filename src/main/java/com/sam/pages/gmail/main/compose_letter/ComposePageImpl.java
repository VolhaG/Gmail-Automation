package com.sam.pages.gmail.main.compose_letter;

import com.sam.PageImpl;
import com.sam.pages.ccompose.CCompose;
import com.sam.pages.gmail.main.MainPage;
import com.sam.pages.gmail.main.MainPageImpl;

public class ComposePageImpl extends PageImpl<CCompose> implements ComposePage {

    public ComposePageImpl() {
        super((CCompose) new ComposeImpl());
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
    public MainPage sendLetter(){
       content.sendLetter();
       return new MainPageImpl();
    }

    @Override
    public MainPage closePage(){
        content.close();
        return new MainPageImpl();
    }

}
