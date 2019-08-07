package com.sam.pages.login;

import com.sam.PageImpl;
import com.sam.pages.main.MainPageImpl;

public class LoginPageImpl extends PageImpl<Login> implements LoginPage{

    public LoginPageImpl() {
        super(new LoginImpl());
    }

    @Override
    public MainPageImpl login(String email, String password) {
        content.inputLogin(email);
        content.clickNext();
        content.inputPassword(password);
        content.clickNext();
        return new MainPageImpl();
    }

}
