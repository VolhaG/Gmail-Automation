package com.sam.pages.gmail.compose;

import com.sam.webelement.Button;
import com.sam.webelement.ButtonImpl;
import com.sam.webelement.ElementWaiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AlertAbsentRecipient {

    private static By defElementBy = By.cssSelector("div.Kj-JD");
    private static Button closeButton = new ButtonImpl(By.name("button[name = 'ok']"));
    private static final Integer timeToWait = 10;

    public static Boolean exists() {
        WebElement el = ElementWaiters.waitForPresence(defElementBy, timeToWait);
        return el != null;
    }

    public static void close() {
        closeButton.click();
    }

}
