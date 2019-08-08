package com.sam.pages.gmail.login;

import com.sam.PageImpl;
import com.sam.pages.base.login.Login;
import com.sam.pages.base.main.MainPage;

public class GMailLoginPageImpl extends PageImpl<Login> implements GMailLoginPage {
    public GMailLoginPageImpl() {
        super(new GMailLoginImpl());
    }

    @Override
    public MainPage login(String email, String password) {
        content.inputEmail(email);
        content.clickNext();
        content.inputPassword(password);
        content.clickNext();
        return null;//new MainPageImpl();
    }

}
