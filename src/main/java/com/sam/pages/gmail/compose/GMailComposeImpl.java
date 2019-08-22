package com.sam.pages.gmail.compose;

import com.sam.webelement.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class GMailComposeImpl extends WrapElementImpl implements GMailCompose {

    private static Logger log = LogManager.getLogger();
    private static final By INITIAL_LOCATOR = By.cssSelector("div.nH.Hd[role = 'dialog']");
    private Button send;
    private Input recipient;
    private Input subject;
    private Input letter;
    private Input close;

    private By sendBy;
    private By closeBy;
    private static final Integer timeToWait = 5;

    GMailComposeImpl() {
        super(INITIAL_LOCATOR);
        sendBy = By.cssSelector("div.dC div");
        closeBy = By.cssSelector("tr td:nth-child(2) img:nth-child(3)");
        send = findWrapElement("send", sendBy, ElementType.BUTTON);
        recipient = findWrapElement("recipient", By.cssSelector("textarea[name = 'to']"), ElementType.INPUT);
        subject = findWrapElement("subject", By.cssSelector("input[name = 'subjectbox']"), ElementType.INPUT);
        letter = findWrapElement("letter", By.cssSelector("div[role = 'textbox']"), ElementType.INPUT);
        close = findWrapElement("close", By.cssSelector("tr td:nth-child(2) img:nth-child(3)"), ElementType.INPUT);
    }

    @Override
    public Boolean existsDefElement() {
        log.info("Wait for compose page identifier.." );
        return existsDefElement(sendBy, 20);
    }

    @Override
    public void writeLetter(String startBody, String body, String endBody) {
        StringBuilder sb = new StringBuilder();
        sb.append(startBody)
                .append(body)
                .append(endBody);
        log.info("Set text '" + sb.toString() + "' in " + letter.getElementName());
        letter.setText(sb.toString());
    }

    @Override
    public void writeLetter(String body) {
        log.info("Set text '" + body + "' in " + letter.getElementName());
        letter.setText(body);
    }

    @Override
    public void close() {
        log.info("Click on " + close.getElementName());
        ElementWaiters.wait(timeToWait);
        close.click();
    }

    @Override
    public void setTopic(String topic) {
        log.info("Set text '" + topic + "' in " + subject.getElementName());
        subject.setText(topic);
        subject.addText(String.valueOf(Keys.ENTER));
    }

    @Override
    public void setRecipient(String mail) {
        ElementWaiters.wait(timeToWait);
        log.info("Set text '" + mail + "' in " + recipient.getElementName());
        recipient.setText(mail);
        recipient.addText(String.valueOf(Keys.ENTER));
    }

    @Override
    public void sendLetter() {
        try {
            log.info("Click on " + send.getElementName());
            send.click();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
