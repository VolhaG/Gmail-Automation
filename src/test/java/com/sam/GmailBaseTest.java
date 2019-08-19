package com.sam;

import com.sam.utils.BaseTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GmailBaseTest extends BaseTest {

    private static Logger log = LogManager.getLogger("GmailBaseTest");

    @Override
    public void onTestInitialization() {
        log.info("Navigate to http://www.gmail.com");
        provider.get().navigate().to("http://www.gmail.com");
    }

}
