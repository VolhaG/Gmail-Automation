package com.sam.pages.base.compose;

import com.sam.components.Page;
import com.sam.entities.Letter;
import com.sam.pages.base.main.MainPage;
public interface ComposePage extends Page<Compose> {

    ComposePage writeLetter(Letter letter);

    MainPage sendLetter();

    MainPage closePage();

}
