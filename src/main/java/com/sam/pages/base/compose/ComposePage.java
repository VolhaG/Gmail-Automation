package com.sam.pages.base.compose;

import com.sam.components.Page;
import com.sam.pages.base.main.MainPage;

public interface ComposePage extends Page<Compose> {

    ComposePage writeLetter(String recipient, String subject, String letter);

    ComposePage writeLetter(String to, String topic, String letterBody, String letterStart, String letterEnd);

    MainPage sendLetter();

    MainPage closePage();

}
