package com.sam.pages.gmail.login;

import com.sam.pages.PageImpl;
import com.sam.pages.base.login.Login;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;

public class GMailLoginPageImpl extends PageImpl<Login> implements GMailLoginPage {

    private static Logger LOG = LogManager.getLogger();

    public GMailLoginPageImpl() {
        super(new GMailLoginImpl());
    }

    @Override
    public GMailMainPage login(String email, String password) {
        try {
            content.inputEmail(email);
            content.clickNext();
        } catch (NoSuchElementException ex) {
            LOG.info("Continue authentification without email.");
        }
        content.inputPassword(password);
        content.clickNext();
        return new GMailMainPageImpl();
    }

}
