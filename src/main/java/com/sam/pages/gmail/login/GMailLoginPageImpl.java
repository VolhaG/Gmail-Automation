package com.sam.pages.gmail.login;

import com.sam.pages.PageImpl;
import com.sam.pages.base.login.Login;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;

public class GMailLoginPageImpl extends PageImpl<Login> implements GMailLoginPage {

    private static Logger log = LogManager.getLogger();

    public GMailLoginPageImpl() {
        super(new GMailLoginImpl());
    }

    @Override
    public GMailMainPage login(String email, String password) {
        try {
            log.info("Try to log in with email: " + email);
            content.inputEmail(email);
            content.clickNext();
        } catch (NoSuchElementException ex) {
            log.info("Continue authentification without email.");
        }
        delay(1_000);
        log.info("Try to log in with password: " + password);
        content.inputPassword(password);
        content.clickNext();
        return new GMailMainPageImpl();
    }

}
