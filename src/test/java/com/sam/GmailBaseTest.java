package com.sam;

public class GmailBaseTest extends BaseTest {

   @Override
    public void onTestInitialization(){
        getWebDriver().navigate().to("https://gmail.com");
    }
}
