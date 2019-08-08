package com.sam.pages.base.login;

import com.sam.components.Content;

public interface Login extends Content {

    void clickNext();

    String getTitle();

    void inputEmail(String email);

    void inputPassword(String email);

}
