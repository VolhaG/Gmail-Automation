package com.sam.pages.gmail.login;

import com.sam.pages.PageImpl;
import com.sam.pages.base.login.Login;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;

public class GMailLoginPageImpl extends PageImpl<Login> implements GMailLoginPage {
    public GMailLoginPageImpl() {
        super(new GMailLoginImpl());
    }

    @Override
    public GMailMainPage login(String email, String password) {
        content.inputEmail(email);
        content.clickNext();
        content.inputPassword(password);
        content.clickNext();
        return new GMailMainPageImpl();
    }

}
