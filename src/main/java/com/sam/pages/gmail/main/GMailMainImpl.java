package com.sam.pages.gmail.main;

import com.sam.webelement.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class GMailMainImpl extends WrapElementImpl implements GMailMain {

    private static final By INITIAL_LOCATOR = By.cssSelector("div.nH");
    private static Logger log = LogManager.getLogger(GMailMainImpl.class);
    private static final int DELAY_TIME = 1;
    private WrapElement title;
    private Button compose;
    private WrapElement accountMenu;
    private Button logout;
    private WrapElement sent;

    public GMailMainImpl() {
        super(INITIAL_LOCATOR);
        title = findWrapElement("title", By.cssSelector("a[title]"), ElementType.DEFAULT);
        compose = findWrapElement("compose", By.cssSelector("div.T-I.J-J5-Ji.T-I-KE.L3"), ElementType.BUTTON);
        accountMenu = findWrapElement("accountMenu", By.cssSelector("span.gb_Ba.gbii"), ElementType.DEFAULT);
        logout = findWrapElement("logout", By.cssSelector("div.gb_Wa.gb_B.gb_Hc div:nth-child(4) div:nth-child(2) a"), ElementType.BUTTON);
        sent = findWrapElement("sent", By.cssSelector("div.aim:nth-child(4) span.nU"), ElementType.DEFAULT);
    }

    @Override
    public void clickCompose() {
        ElementWaiters.wait(DELAY_TIME);
        compose.click();
    }

    @Override
    public String getTitle() {
        return title.getAttribute("title");
    }

    @Override
    public void openAccountMenu() {
        log.info("Click on {}", accountMenu.getElementName());
        accountMenu.click();
    }

    @Override
    public void clickLogout() {
        log.info("Click on {}", logout.getElementName());
        logout.click();
    }

    @Override
    public void clickSent() {
        log.info("Click on {}", sent.getElementName());
        sent.click();
    }

    @Override
    public boolean existsDefElement() {
        log.info("Wait for main page identifier..");
        return existsDefElement(By.cssSelector("div[id='loading']"), DELAY_TIME);
    }

}
