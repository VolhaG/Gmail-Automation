package com.sam.pages.main.compose_letter;

import com.sam.webelement.Button;
import com.sam.webelement.ButtonImpl;
import com.sam.webelement.ElementWaiters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class AlertAbsentRecipient {

    private static By defElementBy = By.xpath("//div[@class = 'Kj-JD']");
    private static Button closeButton = new ButtonImpl(By.name("ok"));

    public static Boolean exists(){
        WebElement el = ElementWaiters.waitForPresence(defElementBy, 10);
        return el != null;
    }

    public static void close(){
        closeButton.click();
    }

}
