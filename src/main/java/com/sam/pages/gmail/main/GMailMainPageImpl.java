package com.sam.pages.gmail.main;

import com.sam.pages.PageImpl;
import com.sam.pages.base.login.LoginPage;
import com.sam.pages.base.main.Main;
import com.sam.pages.gmail.login.GMailLoginPageImpl;
import com.sam.pages.gmail.main.compose_letter.GMailComposePage;
import com.sam.pages.gmail.main.compose_letter.GMailComposePageImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GMailMainPageImpl extends PageImpl<Main> implements GMailMainPage {

    Logger log = LogManager.getLogger("GMailMainPageImpl");

    public GMailMainPageImpl() {
        super(new GMailMainImpl());
    }

    @Override
    public GMailComposePage compose() {
        content.clickCompose();
        return new GMailComposePageImpl();
    }

    @Override
    public LoginPage logout() {
        content.openAccountMenu();
        content.clickLogout();
        return new GMailLoginPageImpl();
    }

}
