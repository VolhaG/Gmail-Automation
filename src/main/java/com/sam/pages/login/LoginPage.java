package com.sam.pages.login;

import com.sam.pages.main.MainPageImpl;

public interface LoginPage {
    MainPageImpl login(String email, String password);
}
