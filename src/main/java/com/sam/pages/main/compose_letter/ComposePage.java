package com.sam.pages.main.compose_letter;

import com.sam.pages.main.MainPageImpl;

public interface ComposePage {

     void writeLetter(String recipient, String subject, String letter);

     void writeLetter(String to, String topic, String letterBody,  String letterStart, String letterEnd);

     MainPageImpl sendLetter();

     MainPageImpl closePage();

}
