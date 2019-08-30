package com.sam.cases;

import com.sam.GmailBaseTest;
import com.sam.data.StaticDataProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

public class ExistingAccountLoginWithDataProvider extends GmailBaseTest {

    private Logger LOG = LogManager.getLogger("ExistingAccountLogin");

    @Test(priority = 1, dataProvider = "login", dataProviderClass = StaticDataProvider.class, enabled = true)
    void login(String email, String password) {
        LOG.info("Start login test...");
        LOG.info("End login test...");
    }

}
