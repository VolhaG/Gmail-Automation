package com.sam.pages.main.compose_letter;

import com.sam.pages.main.MainImpl;
import com.sam.webelement.*;
import org.openqa.selenium.By;

public class ComposeImpl extends MainImpl implements Compose {

    private Button defElement = new ButtonImpl(By.xpath("//div[@class = 'dC']/div"), 10 , 1);
    private Input recipient = new InputImpl(By.xpath("//div[@class = 'wO nr l1']/textarea"), 10,1);
    private Input subject = new InputImpl(By.name("subjectbox"),10,1);
    private Input letter = new InputImpl(By.xpath("//div[@class = 'Am Al editable LW-avf']"),10,1);
    private Input close = new InputImpl(By.xpath("//td[@class = 'Hm']/img[3]"));

    private By defElementBy = By.xpath("//div[@class = 'dC']/div");

    ComposeImpl(){
        super();
    }

    @Override
    public Boolean existsDefElement() {
        return existsDefElement(defElementBy);
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
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        defElement.click();
    }

}
