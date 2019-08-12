package com.sam.pages.base.main;

import com.sam.components.Page;
import com.sam.pages.base.compose.ComposePage;
import com.sam.pages.base.login.LoginPage;

public interface MainPage extends Page<Main> {

    ComposePage compose();

    LoginPage logout();

}

