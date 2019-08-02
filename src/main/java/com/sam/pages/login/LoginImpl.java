package com.sam.pages.login;

import com.sam.webelement.*;
import org.openqa.selenium.By;

public class LoginImpl extends WrapElementImpl implements Login {

    private Button continueBtn = new ButtonImpl(By.xpath("//span[@class = 'RveJvd snByac']"));
    private Input login = new InputImpl(By.name("identifier"));
    private Input password = new InputImpl(By.name("password"));

    public LoginImpl() {
        super(By.xpath("//body"));
    }

    @Override
    public void clickNext() {
        continueBtn.click();
    }

    @Override
    public String getTitle() {
        return findElement(By.cssSelector("div#logo")).getAttribute("title");
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
