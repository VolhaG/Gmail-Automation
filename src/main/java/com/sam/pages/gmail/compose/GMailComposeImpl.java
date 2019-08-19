package com.sam.pages.gmail.compose;

import com.sam.webelement.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class GMailComposeImpl extends WrapElementImpl implements GMailCompose {

    private static Logger log = LogManager.getLogger();
    private static final String GMAIL_COMPOSE_IDLOCATOR = "//div[@class = \"nH Hd\"]";
    private Button send;
    private Input recipient;
    private Input subject;
    private Input letter;
    private Input close;

    private By sendBy;
    private By closeBy;
    private static final Integer timeToWait = 5;

    GMailComposeImpl() {
        super(By.xpath(GMAIL_COMPOSE_IDLOCATOR));
        sendBy = By.xpath("//div[@class = 'dC']/div");
        closeBy = By.xpath("//td[@class = 'Hm']/img[3]");
        send = findWrapElement("send", sendBy, ElementType.BUTTON);
        recipient = findWrapElement("recipient", By.xpath("//div[@class = 'wO nr l1']/textarea"), ElementType.INPUT);
        subject = findWrapElement("subject", By.xpath("//input[@name = 'subjectbox']"), ElementType.INPUT);
        letter = findWrapElement("letter", By.xpath("//div[@class = 'Am Al editable LW-avf']"), ElementType.INPUT);
        close = findWrapElement("close", By.xpath("//td[@class = 'Hm']/img[3]"), ElementType.INPUT);
    }

    @Override
    public Boolean existsDefElement() {
        log.info("Wait for page identifier " + send.getElementName());
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
            log.info("Click on '" + send.getElementName());
            send.click();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
