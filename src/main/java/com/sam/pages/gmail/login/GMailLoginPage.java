package com.sam.pages.gmail.login;

import com.sam.pages.base.login.LoginPage;
import com.sam.pages.gmail.main.GMailMainPage;
import io.qameta.allure.Step;

public interface GMailLoginPage extends LoginPage {

    @Override
    GMailMainPage login(String email, String password);

}
