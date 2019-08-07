package com.sam.pages.clogin;

import com.sam.components.Page;
import com.sam.pages.cmain.CMainPage;

public interface CLoginPage extends Page<CLogin> {
    CMainPage login(String email, String password);
}
