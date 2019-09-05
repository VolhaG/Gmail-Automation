package com.sam.pages.gmail.compose;

import com.sam.annotations.ElementVerification;
import com.sam.webelement.Button;
import com.sam.webelement.ButtonImpl;
import com.sam.webelement.ElementWaiters;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AlertAbsentRecipient {

    private static Logger log = LogManager.getLogger(AlertAbsentRecipient.class);

    @ElementVerification(description = "Root element verification for alert")
    private static final By rootElement = By.cssSelector("div.Kj-JD");

    private static By defElementBy = By.cssSelector("div.Kj-JD");
    private static Button closeButton = new ButtonImpl("close", By.cssSelector("span.Kj-JD-K7-Jq"));
    private static Button okButton = new ButtonImpl("ok", By.cssSelector("button[name = 'ok'']"));
    private static final int DELAY_TIME = 10;

    @Description("Check if page exists")
    @Step("Check if page exists")
    public static boolean exists() {
        log.info("Wait for alert page identifier..");
        WebElement el = ElementWaiters.waitForPresence(defElementBy, DELAY_TIME);
        return el != null;
    }

    @Description("Try to close alert absent recipient page")
    @Step("Try to close alert absent recipient page")
    public static void close() {
        log.info("Click on {}", closeButton.getElementName());
        closeButton.click();
    }

    @Description("Try to close ok button")
    @Step("Try to close ok button")
    public static void ok() {
        log.info("Click on {}", okButton.getElementName());
        okButton.click();
    }

}
