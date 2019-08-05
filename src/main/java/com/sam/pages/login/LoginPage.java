package com.sam.pages.login;

import com.sam.PageImpl;
import com.sam.pages.main.MainPage;

public class LoginPage extends PageImpl<Login> {

    public LoginPage() {
        super(new LoginImpl());
    }

    @Override
    public Boolean exists(){
        return content.existsDefElement();
    }

    public MainPage login(String email, String password) {
        content.inputLogin(email);
        content.clickNext();
        content.inputPassword(password);
        content.clickNext();
        return new MainPage();
    }
}
