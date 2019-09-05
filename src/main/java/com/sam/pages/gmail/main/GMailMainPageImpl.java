package com.sam.pages.gmail.main;

import com.sam.annotations.ElementVerification;
import com.sam.pages.PageImpl;
import com.sam.pages.base.main.Main;
import com.sam.pages.gmail.compose.GMailComposePage;
import com.sam.pages.gmail.compose.GMailComposePageImpl;
import com.sam.pages.gmail.login.GMailLoginPage;
import com.sam.pages.gmail.login.GMailLoginPageImpl;
import com.sam.pages.gmail.sent.GMailSentPage;
import com.sam.pages.gmail.sent.GMailSentPageImpl;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class GMailMainPageImpl extends PageImpl<Main> implements GMailMainPage {

    private Logger log = LogManager.getLogger(GMailMainPageImpl.class);

    @ElementVerification(description = "Root element verification on main page")
    public static final By rootElement = By.cssSelector("div[id='loading']");

    public GMailMainPageImpl() {
        super(new GMailMainImpl());
    }

    @Step("Compose new letter")
    @Override
    public GMailComposePage compose() {
        log.info("Compose letter..");
        content.clickCompose();
        return new GMailComposePageImpl();
    }

    @Step("Log out from account")
    @Override
    public GMailLoginPage logout() {
        log.info("Try to log out..");
        content.openAccountMenu();
        delay(2);
        content.clickLogout();
        return new GMailLoginPageImpl();
    }

    @Step("Check sent letters")
    @Override
    public GMailSentPage openSent() {
        log.info("Open menu with sent letters..");
        content.clickSent();
        delay(3);
        return new GMailSentPageImpl();
    }

}
