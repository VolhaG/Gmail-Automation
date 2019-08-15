package com.sam.pages.base.login;

import com.sam.components.Page;
import com.sam.pages.gmail.main.GMailMainPage;
import com.sam.pages.gmail.main.GMailMainPageImpl;

public interface LoginPage extends Page<Login> {

    GMailMainPage login(String email, String password);

}
