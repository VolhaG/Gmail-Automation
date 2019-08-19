package com.sam.pages.gmail.compose;

import com.sam.pages.PageImpl;
import com.sam.pages.base.compose.Compose;
import com.sam.pages.gmail.main.GMailMainPage;

public class GMailComposePageImpl extends PageImpl<Compose> implements GMailComposePage{

    public GMailComposePageImpl() {
        super( new GMailComposeImpl());
    }

    @Override
    public GMailComposePage writeLetter(String recipient, String subject, String letter) {
        return null;
    }

    @Override
    public GMailComposePage writeLetter(String to, String topic, String letterBody, String letterStart, String letterEnd) {
        return null;
    }

    @Override
    public GMailMainPage sendLetter() {
        return null;
    }

    @Override
    public GMailMainPage closePage() {
        return null;
    }
}
