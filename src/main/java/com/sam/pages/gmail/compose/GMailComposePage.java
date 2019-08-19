package com.sam.pages.gmail.compose;

import com.sam.pages.base.compose.ComposePage;
import com.sam.pages.gmail.main.GMailMainPage;

public interface GMailComposePage extends ComposePage {

     GMailComposePage writeLetter(String recipient, String subject, String letter);

     GMailComposePage writeLetter(String to, String topic, String letterBody,  String letterStart, String letterEnd);

     GMailMainPage sendLetter();

     GMailMainPage closePage();

}
