package com.sam.pages.gmail.login;

import com.sam.pages.PageImpl;
import com.sam.pages.base.login.Login;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

public class GMailLoginPageImpl extends PageImpl<Login> implements GMailLoginPage {

    private static Logger log = LogManager.getLogger(GMailLoginPageImpl.class);
    private static final int DELAY_TIME = 2;

    public GMailLoginPageImpl() {
        super(new GMailLoginImpl());
    }

    @Step("Type {user.name} / {user.password}.")
    @Override
    public GMailMainPage login(String email, String password) {
        try {
            log.info("Try to log in with email: {}", email);
            content.inputEmail(email);
            content.clickNext();
        } catch (NoSuchElementException | ElementNotInteractableException | TimeoutException | NullPointerException ex) {
            log.info("Continue authentication without email.");
        }
        delay(DELAY_TIME);
        log.info("Try to log in with password: {}", password);
        content.inputPassword(password);
        content.clickNext();
        return new GMailMainPageImpl();
    }

}
