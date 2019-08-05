package com.sam.pages.login;

import com.sam.webdriver.WebDriverProvider;
import com.sam.webelement.*;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class LoginImpl extends WrapElementImpl implements Login {

    private Button continueBtn = new ButtonImpl(By.xpath("//span[@class = 'RveJvd snByac']"));
    private Input login = new InputImpl(By.name("identifier"));
    private Input password = new InputImpl(By.name("password"));
    private By defElementBy = By.cssSelector("div#logo");

    public LoginImpl() {
        super(By.xpath("//body"));
    }

    @Override
    public Boolean existsDefElement(){
        return WrapElementImpl.exists(defElementBy);
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
    public void inputLogin(String email) {
        login.setText(email);
    }

    @Override
    public void inputPassword(String pwd) {
        password.setText(pwd);
    }

}
