package com.sam.pages.gmail.login;

import com.sam.webelement.*;
import org.openqa.selenium.*;

public class LoginImpl extends WrapElementImpl implements Login {

    private Button continueBtn = new ButtonImpl(By.xpath("//span[@class = 'RveJvd snByac']"));
    private Input login = new InputImpl(By.name("identifier"));
    private Input password = new InputImpl(By.name("password"));

    private By defElementBy = By.cssSelector("div#logo");

    public LoginImpl() {
        super(By.xpath("//body"));
    }

    public Boolean existsDefElement(){
        return existsDefElement(defElementBy,20);
    }

    @Override
    public void clickNext() {
        continueBtn.click();
    }

    @Override
    public String getTitle() {
        return findElement(defElementBy).getAttribute("title");
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
