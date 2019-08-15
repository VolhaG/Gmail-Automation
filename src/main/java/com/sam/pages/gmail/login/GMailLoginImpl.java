package com.sam.pages.gmail.login;

import com.sam.webelement.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;

public class GMailLoginImpl extends WrapElementImpl implements GMailLogin {

    private static Logger log = LogManager.getLogger();
    private static final String LOGIN_PAGE_CSSLOCATOR = "div#initialView";
    private static final By by = By.cssSelector(LOGIN_PAGE_CSSLOCATOR);
    private static final By logoBy = By.cssSelector("div#logo");
    private Button continueBtn;
    private Input login;
    private Input password;

    GMailLoginImpl() {
        super(by);
        continueBtn = findWrapElement(By.xpath("//span[@class = 'RveJvd snByac']"), ElementType.BUTTON);
        login = findWrapElement( By.id("identifierId"), ElementType.INPUT);
        password = findWrapElement(By.name("password"), ElementType.INPUT);
    }

    public Boolean existsDefElement(){
        return existsDefElement(logoBy,20);
    }

    @Override
    public void clickNext() {
        continueBtn.click();
        log.info("Click continue button");
    }

    @Override
    public String getTitle() {
        return findElement(logoBy).getAttribute("title");
    }

    @Override
    public void inputEmail(String email) {
        login.setText(email);
        log.info("Log in with email: "+ email);
    }

    @Override
    public void inputPassword(String pwd) {
        password.setText(pwd);
        log.info("Log in with password: "+ pwd);
    }

}
