package com.sam.pages.login;

import com.sam.components.Content;

public interface Login extends Content {
    void clickNext();

    String getTitle();

    void inputLogin(String email);

    void inputPassword(String email);
}
