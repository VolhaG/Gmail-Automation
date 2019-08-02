package com.sam.pages.main.compose_letter;

import com.sam.PageImpl;
import com.sam.pages.main.MainPage;

public class ComposePage extends PageImpl<ComposeImpl> {

    public ComposePage() {
        super(new ComposeImpl());
    }

    public void writeLetter(String recipient, String subject, String letter) {
        content.setRecipient(recipient);
        content.setSubject(subject);
        content.writeLetter(letter);
    }

    public void writeLetter(String to, String topic, String letterBody,  String letterStart, String letterEnd) {
        content.setRecipient(to);
        content.setSubject(topic);
        content.writeLetter(letterStart,letterBody,letterEnd);
    }

    public MainPage sendLetter(){
       content.sendLetter();
       return new MainPage();
    }
}
