package com.sam.pages.gmail.main;

import com.sam.pages.base.main.MainPage;
import com.sam.pages.gmail.compose.GMailComposePage;
import com.sam.pages.gmail.login.GMailLoginPage;
import com.sam.pages.gmail.sent.GMailSentPage;

public interface GMailMainPage extends MainPage {

    @Override
    GMailComposePage compose();

    @Override
    GMailLoginPage logout();

    @Override
    GMailSentPage openSent();
}
