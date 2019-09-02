package com.sam.annotations;

import com.sam.webelement.ElementWaiters;
import org.openqa.selenium.By;

public class PageVerificator {
    public static void verify(Object page) {
        Class<? extends Object> pageClass = page.getClass();
        PageVerification pageAnnotation = pageClass.getAnnotation(PageVerification.class);
        if (pageAnnotation == null) {
            throw new IllegalArgumentException("Class " + pageClass.getCanonicalName() + " is not annotated with PageVerification annotation");
        }

        String locator = pageAnnotation.locator();
        By by = null;
        switch (pageAnnotation.byType()) {
            case CSS:
                by = By.cssSelector(locator);
                break;
            case XPATH:
                by = By.xpath(locator);
                break;
            case ID:
                by = By.id(locator);
                break;
            case NAME:
                by = By.name(locator);
                break;
        }
        ElementWaiters.waitForPresence(by, pageAnnotation.delayTime());
    }
}
