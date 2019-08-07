package com.sam.pages.gmail.login;

import com.sam.PageImpl;
import com.sam.pages.clogin.CLogin;
import com.sam.pages.cmain.CMainPage;

public class LoginPageImpl extends PageImpl<CLogin> implements LoginPage {
    public LoginPageImpl() {
        super(new LoginImpl());
    }

    @Override
    public CMainPage login(String email, String password) {
        content.inputEmail(email);
        content.clickNext();
        content.inputPassword(password);
        content.clickNext();
        return null;//new MainPageImpl();
    }


}
