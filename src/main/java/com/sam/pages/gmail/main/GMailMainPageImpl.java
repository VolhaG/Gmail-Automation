package com.sam.pages.gmail.main;

import com.sam.pages.PageImpl;
import com.sam.pages.base.main.Main;
import com.sam.pages.gmail.compose.GMailComposePage;
import com.sam.pages.gmail.compose.GMailComposePageImpl;
import com.sam.pages.gmail.login.GMailLoginPage;
import com.sam.pages.gmail.login.GMailLoginPageImpl;
import com.sam.pages.gmail.sent.GMailSentPage;
import com.sam.pages.gmail.sent.GMailSentPageImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GMailMainPageImpl extends PageImpl<Main> implements GMailMainPage {

    Logger log = LogManager.getLogger(GMailMainPageImpl.class);

    public GMailMainPageImpl() {
        super(new GMailMainImpl());
    }

    @Override
    public GMailComposePage compose() {
        log.info("Compose letter..");
        content.clickCompose();
        return new GMailComposePageImpl();
    }

    @Override
    public GMailLoginPage logout() {
        log.info("Try to log out..");
        content.openAccountMenu();
        content.clickLogout();
        return new GMailLoginPageImpl();
    }

    @Override
    public GMailSentPage openSent() {
        log.info("Open menu with sent letters..");
        content.clickSent();
        delay(3);
        return new GMailSentPageImpl();
    }

}
