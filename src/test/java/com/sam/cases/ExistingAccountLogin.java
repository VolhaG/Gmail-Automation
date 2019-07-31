package com.sam.cases;

import com.sam.GmailBaseTest;
import com.sam.pages.login.LoginPage;
import com.sam.pages.main.MainPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class ExistingAccountLogin extends GmailBaseTest {
    private final static String LOGIN_TITLE = "Google";
    private final static String MAIN_TITLE = "Google";

    @Test
    @Parameters({"email", "password"})
    void login(String email, String password) {

        getWebDriver().navigate().to("https://gmail.com");
        LoginPage loginPage = new LoginPage();
        assertThat(loginPage.getContent().getTitle()).isEqualTo(LOGIN_TITLE);
        MainPage mainPage = loginPage.login(email, password);
        assertThat(mainPage.getTitle()).isEqualTo(MAIN_TITLE);
    }
}
