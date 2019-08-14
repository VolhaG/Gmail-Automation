package com.sam.pages.gmail.main.compose_letter;

import com.sam.pages.gmail.main.GMailMainImpl;
import com.sam.webelement.*;
import org.openqa.selenium.By;

public class GMailComposeImpl extends GMailMainImpl implements GMailCompose {

    private Button send;
    private Input recipient;
    private Input subject;
    private Input letter;
    private Input close;

    private By sendBy;
    private By closeBy;

    GMailComposeImpl() {
        super();
        send =(Button) this.findWrapElement(By.xpath("//div[@class = 'dC']/div"));
        recipient = (Input) this.findWrapElement(By.xpath("//div[@class = 'wO nr l1']/textarea"));
        subject = (Input) this.findWrapElement(By.name("subjectbox"));
        letter = (Input) this.findWrapElement(By.xpath("//div[@class = 'Am Al editable LW-avf']"));
        close = (Input) this.findWrapElement(By.xpath("//td[@class = 'Hm']/img[3]"));
        sendBy = By.xpath("//div[@class = 'dC']/div");
        closeBy = By.xpath("//td[@class = 'Hm']/img[3]");
    }

    @Override
    public Boolean existsDefElement() {
        return existsDefElement(sendBy, 20);
    }

    @Override
    public void writeLetter(String startBody, String body, String endBody) {
        StringBuilder sb = new StringBuilder();
        sb.append(startBody)
                .append(body)
                .append(endBody);
        letter.setText(sb.toString());
    }

    @Override
    public void writeLetter(String body) {
        letter.setText(body);
    }

    @Override
    public void close() {
        ElementWaiters.wait(5);
        close.click();
    }

    @Override
    public void setSubject(String topic) {
        subject.setText(topic);
    }

    @Override
    public void setRecipient(String mail) {
        ElementWaiters.wait(3);
        recipient.setText(mail);
    }

    @Override
    public void sendLetter() {
        send.click();
    }

}
