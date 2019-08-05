package com.sam.pages.main.compose_letter;

import com.sam.webelement.Button;
import com.sam.webelement.ButtonImpl;
import com.sam.webelement.WrapElementImpl;
import org.openqa.selenium.By;

public class AlertAbsentRecipient {

    private static By defElementBy = By.xpath("//div[@class = 'Kj-JD']");
    private static Button closeButton = new ButtonImpl(By.name("ok"));

    public static Boolean exists(){
        return WrapElementImpl.exists(defElementBy);
    }

    public static void close(){
        closeButton.click();
    }

}
