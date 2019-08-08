package com.sam.pages.gmail.main.compose_letter;

import com.sam.pages.base.compose.ComposePage;
import com.sam.pages.gmail.main.GMailMainPage;

public interface GMailComposePage extends ComposePage {

     void writeLetter(String recipient, String subject, String letter);

     void writeLetter(String to, String topic, String letterBody,  String letterStart, String letterEnd);

     GMailMainPage sendLetter();

     GMailMainPage closePage();

}
