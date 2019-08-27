package com.sam.utils.gmail;

import com.sam.utils.AbstractRow;
import com.sam.webelement.WrapElement;
import com.sam.webelement.WrapElementImpl;
import org.openqa.selenium.By;

public class GMailRow extends AbstractRow {

    protected WrapElement time;
    protected WrapElement email;
    protected WrapElement topic;
    protected WrapElement letterBody;

    public GMailRow(String name, By by) {
        super(name, by);
    }

    public GMailRow() {
    }

    public WrapElement getTime() {
        return time;
    }

    public void setTime(By by, WrapElement row) {
        this.time = new WrapElementImpl("time", by, row.getWebElement());
    }

    public WrapElement getEmail() {
        return email;
    }

    public void setEmail(By by, WrapElement row) {
        this.email = new WrapElementImpl("email", by,  row.getWebElement());
    }

    public WrapElement getTopic() {
        return topic;
    }

    public void setTopic(By by, WrapElement row) {
        this.topic = new WrapElementImpl("topic", by,  row.getWebElement());
    }

    public WrapElement getLetterBody() {
        return letterBody;
    }

    public void setLetterBody(By by, WrapElement row) {
        this.letterBody = new WrapElementImpl("letterBody", by,  row.getWebElement());
    }

}