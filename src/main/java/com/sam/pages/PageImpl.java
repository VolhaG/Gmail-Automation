package com.sam.pages;

import com.sam.components.Content;
import com.sam.components.Page;
import com.sam.webdriver.WebDriverProvider;
import com.sam.webelement.WrapElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.net.URL;

public class PageImpl<C extends Content> implements Page<C> {

    protected final C content;

    protected WebDriver getDriver()  {
        return WebDriverProvider.getInstance().get();
    }

    protected void delay(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public PageImpl(C content) {
        this.content = content;
    }

    @Override
    public C getContent() {
        return content;
    }

    @Override
    public Boolean exists() {
        return content.existsDefElement();
    }

    @Override
    public void refresh() {
        getDriver().navigate().refresh();
    }

    @Override
    public String getUrl() {
        return  getDriver().getCurrentUrl();
    }

    @Override
    public Dimension getSize() {
        return  getDriver().manage().window().getSize();
    }

    @Override
    public void setSize(int width, int height) {
        Dimension targetSize = new Dimension(width, height);
        getDriver().manage().window().setSize(targetSize);
    }

    @Override
    public void setSize(java.awt.Dimension dm) {
        Dimension targetSize = new Dimension(dm.width, dm.height);
        getDriver().manage().window().setSize(targetSize);
    }

    @Override
    public void close() {
        getDriver().close();
    }

    @Override
    public void back() {
        getDriver().navigate().back();
    }

    @Override
    public void forward() {
        getDriver().navigate().forward();
    }

    @Override
    public void navigateTo(String url) {
        getDriver().navigate().to(url);
    }

    @Override
    public void navigateTo(URL url) {
        getDriver().navigate().to(url);
    }

    @Override
    public void getURL() {
        getDriver().getCurrentUrl();
    }

    @Override
    public void quit() {
        getDriver().quit();
    }

    @Override
    public void dismissAlertPopup() {
        getDriver().switchTo().alert().dismiss();
    }

    @Override
    public void acceptAlertPopup() {
        getDriver().switchTo().alert().accept();
    }

}
