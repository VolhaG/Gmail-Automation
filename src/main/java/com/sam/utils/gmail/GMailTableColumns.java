package com.sam.utils.gmail;

import com.sam.utils.AbstractTable;
import com.sam.webelement.WrapElement;
import com.sam.webelement.WrapElementImpl;
import org.openqa.selenium.By;

abstract public class GMailTableColumns extends AbstractTable {

    protected WrapElement time;
    protected WrapElement email;
    protected WrapElement topic;
    protected WrapElement letterBody;

    public WrapElement getTime() {
        return time;
    }

    public void setTime(By by) {
        this.time = new WrapElementImpl("time", by, super.element);
    }

    public WrapElement getEmail() {
        return email;
    }

    public void setEmail(By by) {
        this.email = new WrapElementImpl("email", by, super.element);
    }

    public WrapElement getTopic() {
        return topic;
    }

    public void setTopic(By by) {
        this.topic = new WrapElementImpl("topic", by, super.element);
    }

    public WrapElement getLetterBody() {
        return letterBody;
    }

    public void setLetterBody(By by) {
        this.letterBody = new WrapElementImpl("letterBody", by, super.element);
    }

}