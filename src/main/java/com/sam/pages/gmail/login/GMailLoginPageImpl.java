package com.sam.pages.gmail.login;

import com.sam.annotations.ElementVerification;
import com.sam.pages.PageImpl;
import com.sam.pages.base.login.Login;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;

public class GMailLoginPageImpl extends PageImpl<Login> implements GMailLoginPage {

    private static Logger log = LogManager.getLogger(GMailLoginPageImpl.class);
    private static final int DELAY_TIME = 2;

    @ElementVerification(description = "Root element verification on login page")
    public static final By rootElement = By.cssSelector("div[id= 'view_container']");

    public GMailLoginPageImpl() {
        super(new GMailLoginImpl());
    }

    @Step("Login to www.gmail.com with email: {0} and password: {1}.")
    @Description("Login to www.gmail.com with email: {0} and password: {1}.")
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
