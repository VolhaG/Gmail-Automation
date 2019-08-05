package com.sam.pages.main.compose_letter;

import com.sam.pages.main.MainImpl;
import com.sam.webelement.Button;
import com.sam.webelement.ButtonImpl;
import com.sam.webelement.Input;
import com.sam.webelement.InputImpl;
import org.openqa.selenium.By;

public class ComposeImpl extends MainImpl implements ComposeLetter {

    private Button defElement = new ButtonImpl(By.id(":qi"), 10 , 1);
    private Input recipient = new InputImpl(By.id(":ra"), 10,2);
    private Input subject = new InputImpl(By.id(":qs"));
    private Input letter = new InputImpl(By.id(":rx"));

    ComposeImpl(){
        super();
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
