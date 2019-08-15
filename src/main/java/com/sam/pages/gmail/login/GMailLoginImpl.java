package com.sam.pages.gmail.login;

import com.sam.webelement.*;
import org.openqa.selenium.*;

public class GMailLoginImpl extends WrapElementImpl implements GMailLogin {

    private static final String LOGIN_PAGE_ID_LOCATOR = "initialView";
    private static final By by = By.id(LOGIN_PAGE_ID_LOCATOR);
    private static final By logoBy = By.cssSelector("div#logo");
    private Button continueBtn;
    private WebElement login;
    private Input password;

    GMailLoginImpl() {
        super(by);
        WebElement defElement = getWebElement();
    //    continueBtn = (Button) defElement.findElement(By.xpath("//span[@class = 'RveJvd snByac']"));
        login =  getWebElement().findElement( By.name("identifier"));
//        password = (Input) getWebElement().findElement(By.name("password"));
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
    //    login.setText(email);
    }

    @Override
    public void inputPassword(String pwd) {
        password.setText(pwd);
    }

}
