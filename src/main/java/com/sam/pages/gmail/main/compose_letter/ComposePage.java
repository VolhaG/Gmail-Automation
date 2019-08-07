package com.sam.pages.gmail.main.compose_letter;

import com.sam.pages.ccompose.CComposePage;
import com.sam.pages.gmail.main.MainPage;
import com.sam.pages.gmail.main.MainPageImpl;

public interface ComposePage extends CComposePage {

     void writeLetter(String recipient, String subject, String letter);

     void writeLetter(String to, String topic, String letterBody,  String letterStart, String letterEnd);

     MainPage sendLetter();

     MainPage closePage();

}
