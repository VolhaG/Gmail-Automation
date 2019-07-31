package com.sam.pages.main;

import com.sam.webelement.WrapWebElement;
import org.openqa.selenium.By;

public class MainImpl extends WrapWebElement implements Main {

    MainImpl() {
        super(By.xpath("/"));
    }

    @Override
    public void clickCompose() {

    }

}
