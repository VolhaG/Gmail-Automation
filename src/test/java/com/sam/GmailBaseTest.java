package com.sam;

public class GmailBaseTest extends BaseTest {

    public static final String PAGESNAME = "gmail";

    @Override
    public void onTestInitialization() {
        getWebDriver().navigate().to("https://gmail.com");
    }

}
