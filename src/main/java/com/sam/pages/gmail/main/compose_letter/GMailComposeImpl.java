package com.sam.pages.gmail.main.compose_letter;

import com.sam.pages.gmail.main.GMailMainImpl;
import com.sam.webelement.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class GMailComposeImpl extends GMailMainImpl implements GMailCompose {

    Logger LOG = LogManager.getLogger("ComposeImpl");

    private Button send = new ButtonImpl(By.xpath("//div[@class = 'dC']/div"), 10 , 1);
    private Input recipient = new InputImpl(By.xpath("//div[@class = 'wO nr l1']/textarea"), 20,1);
    private Input subject = new InputImpl(By.name("subjectbox"),10,1);
    private Input letter = new InputImpl(By.xpath("//div[@class = 'Am Al editable LW-avf']"),10,1);
    private Input close = new InputImpl(By.xpath("//td[@class = 'Hm']/img[3]"));

    private By sendBy = By.xpath("//div[@class = 'dC']/div");
    private By  closeBy = By.xpath("//td[@class = 'Hm']/img[3]");

    GMailComposeImpl(){
        super();
    }

    @Override
    public Boolean existsDefElement() {
        return existsDefElement(sendBy,20);
    }

    @Override
    public void writeLetter(String startBody, String body, String endBody) {
        String letterBuild = startBody + "%n" + body + "%n" + endBody;
        letter.setText(letterBuild);
    }

    @Override
    public void writeLetter(String body) {
        letter.setText(body);
    }

    @Override
    public void close() {
      // WebElement el = ElementWaiters.waitForPresence(closeBy,20);
        ElementWaiters.wait(5);
        close.click();
    }

    @Override
    public void setSubject(String topic) {
        subject.setText(topic);
    }

    @Override
    public void setRecipient(String mail) {
        recipient.setText(mail);
    }

    @Override
    public void sendLetter() {
        send.click();
    }

}