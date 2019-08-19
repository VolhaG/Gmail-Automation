package com.sam.pages.gmail.login;

import com.sam.webelement.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class GMailLoginImpl extends WrapElementImpl implements GMailLogin {

    private static Logger log = LogManager.getLogger();
    private static final String LOGIN_PAGE_CSSLOCATOR = "div#initialView";
    private static final By initialElement = By.cssSelector(LOGIN_PAGE_CSSLOCATOR);
    private static final By logoBy = By.cssSelector("div#logo");
    private WrapElement pageIdentifier;
    private Button continueBtn;
    private Input login;
    private Input password;

    GMailLoginImpl() {
        super(initialElement);
        pageIdentifier = findWrapElement("pageIdentifier", logoBy, ElementType.DEFAULT);
        continueBtn = findWrapElement("continue", By.xpath("//span[@class = 'RveJvd snByac']"), ElementType.BUTTON);
        login = findWrapElement("login", By.id("identifierId"), ElementType.INPUT);
        password = findWrapElement("password", By.name("password"), ElementType.INPUT);
    }

    public Boolean existsDefElement() {
        log.info("Wait for page identifier " + pageIdentifier.getElementName());
        return existsDefElement(logoBy, 20);
    }

    @Override
    public void clickNext() {
        log.info("Click on" + continueBtn.getElementName());
        continueBtn.click();
    }

    @Override
    public String getTitle() {
        return findElement(logoBy).getAttribute("title");
    }

    @Override
    public void inputEmail(String email) {
        log.info("Set text '" + email + "' in " + login.getElementName());
        login.setText(email);
    }

    @Override
    public void inputPassword(String pwd) {
        log.info("Set text '" + pwd + "' in " + password.getElementName());
        password.setText(pwd);
    }

}
