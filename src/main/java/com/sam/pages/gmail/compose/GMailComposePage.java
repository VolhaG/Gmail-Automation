package com.sam.pages.gmail.compose;

import com.sam.pages.base.compose.ComposePage;
import com.sam.pages.gmail.main.GMailMainPage;

public interface GMailComposePage extends ComposePage {

     @Override
     GMailComposePage writeLetter(String recipient, String topic, String letter);

     @Override
     GMailComposePage writeLetter(String to, String topic, String letterBody,  String letterStart, String letterEnd);

     @Override
     GMailMainPage sendLetter();

     @Override
     GMailMainPage closePage();

}
