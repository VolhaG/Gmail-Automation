package com.sam.pages.gmail.login;

import com.sam.webelement.*;
import org.openqa.selenium.*;

public class GMailLoginImpl extends WrapElementImpl implements GMailLogin {

    private static final String LOGIN_PAGE_CSSLOCATOR = "div#initialView";
    private static final By by = By.cssSelector(LOGIN_PAGE_CSSLOCATOR);
    private static final By logoBy = By.cssSelector("div#logo");
    private Button continueBtn;
    private Input login;
    private Input password;

    GMailLoginImpl() {
        super(by);
        continueBtn = findButton(By.xpath("//span[@class = 'RveJvd snByac']"));
        login = findInput( By.id("identifierId"));
        password = findInput(By.name("password"));
    }

    public Boolean existsDefElement(){
        return existsDefElement(logoBy,20);
    }

    @Override
    public void clickNext() {
        continueBtn.click();
    }

    @Override
    public String getTitle() {
        return findElement(logoBy).getAttribute("title");
    }

    @Override
    public void inputEmail(String email) {
        login.setText(email);
    }

    @Override
    public void inputPassword(String pwd) {
        password.setText(pwd);
    }

}
