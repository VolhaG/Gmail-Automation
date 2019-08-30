package com.sam.pages.gmail.compose;

import com.sam.webelement.Button;
import com.sam.webelement.ButtonImpl;
import com.sam.webelement.ElementWaiters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AlertAbsentRecipient {

    private static Logger log = LogManager.getLogger(AlertAbsentRecipient.class);
    private static By defElementBy = By.cssSelector("div.Kj-JD");
    private static Button closeButton = new ButtonImpl("close", By.cssSelector("span.Kj-JD-K7-Jq"));
    private static Button okButton = new ButtonImpl("ok", By.cssSelector("button[name = 'ok'']"));
    private static final int DELAY_TIME = 10;

    public static boolean exists() {
        log.info("Wait for alert page identifier..");
        WebElement el = ElementWaiters.waitForPresence(defElementBy, DELAY_TIME);
        return el != null;
    }

    public static void close() {
        log.info("Click on {}", closeButton.getElementName());
        closeButton.click();
    }

    public static void ok() {
        log.info("Click on {}", okButton.getElementName());
        okButton.click();
    }

}
