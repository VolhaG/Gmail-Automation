package com.sam.pages.clogin;

import com.sam.components.Content;

public interface CLogin extends Content {

    void clickNext();

    String getTitle();

    void inputEmail(String email);

    void inputPassword(String email);
}
