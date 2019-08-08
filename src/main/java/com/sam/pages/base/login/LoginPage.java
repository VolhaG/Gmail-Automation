package com.sam.pages.base.login;

import com.sam.components.Page;
import com.sam.pages.base.main.MainPage;

public interface LoginPage extends Page<Login> {

    MainPage login(String email, String password);

}
