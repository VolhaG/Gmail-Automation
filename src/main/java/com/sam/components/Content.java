package com.sam.components;


import com.sam.webelement.ElementWaiters;
import com.sam.webelement.WrapElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface Content extends WrapElement {

    boolean existVerificationElement();

    default boolean existVerificationElement(By by, int timeOut) {
        WebElement el = ElementWaiters.waitForPresence(by, timeOut);
        return el != null;
    }

}
