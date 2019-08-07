package com.sam.pages.gmail.main;

import com.sam.webelement.ButtonImpl;
import com.sam.webelement.ElementWaiters;
import com.sam.webelement.WrapElement;
import com.sam.webelement.WrapElementImpl;
import org.openqa.selenium.By;

public class MainImpl extends WrapElementImpl implements Main {

    private WrapElement title = new WrapElementImpl(By.xpath( "//a[@class ='gb_me gb_pc gb_ke']"), 10,1);
    private ButtonImpl compose = new ButtonImpl(By.xpath("//div[@class = 'T-I J-J5-Ji T-I-KE L3']"), 10,1);
    private By composeBy = By.xpath("//div[@class = 'T-I J-J5-Ji T-I-KE L3']");

    public MainImpl() {
        super(By.xpath("//body"));
    }

    @Override
    public void clickCompose() {
       // WebElement el = ElementWaiters.waitForPresence(composeBy,20);
        ElementWaiters.wait(7);
        compose.click();
    }

    @Override
    public String getTitle(){
        return title.getAttribute("title");
    }

    @Override
    public Boolean existsDefElement() {
        ElementWaiters.wait(7);
        return existsDefElement(composeBy,20);
    }
}
