package com.sam.utils.gmail;

import com.sam.webelement.ElementType;
import com.sam.webelement.TableRowImpl;
import com.sam.webelement.WrapElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GMailRow extends TableRowImpl {

    private final WrapElement time;
    private final WrapElement email;
    private final WrapElement topic;
    private final WrapElement letterBody;

    public GMailRow(String name, By by, WebElement parent) {
        super(name, by, parent);
        time = null;
        email = findWrapElement(getElementName() + "#email", By.cssSelector("span[email]"), ElementType.DEFAULT);
        topic = findWrapElement(getElementName() + "#topic", By.cssSelector("span.bog span"), ElementType.DEFAULT);
        letterBody = findWrapElement(getElementName() + "#letterBody", By.cssSelector("span.y2"), ElementType.DEFAULT);
    }

    public WrapElement getTime() {
        return time;
    }

    public WrapElement getEmail() {
        return email;
    }

    public WrapElement getTopic() {
        return topic;
    }

    public WrapElement getLetterBody() {
        return letterBody;
    }

}