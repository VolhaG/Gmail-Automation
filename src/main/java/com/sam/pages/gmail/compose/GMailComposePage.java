package com.sam.pages.gmail.compose;

import com.sam.entities.Letter;
import com.sam.pages.base.compose.ComposePage;
import com.sam.pages.gmail.main.GMailMainPage;

public interface GMailComposePage extends ComposePage {

     @Override
     GMailComposePage writeLetter(Letter letter);

     @Override
     GMailMainPage sendLetter();

     @Override
     GMailMainPage closePage();

}
