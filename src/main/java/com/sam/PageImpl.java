package com.sam;

import com.sam.components.Content;
import com.sam.components.Page;
import com.sam.webdriver.WebDriverProvider;
import com.sam.webelement.WrapElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.net.URL;

public class PageImpl<C extends Content> implements Page<C> {

    protected WebDriver driver = WebDriverProvider.getInstance().get();
    protected final C content;

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
        driver.navigate().refresh();
    }

    @Override
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    @Override
    public Dimension getSize() {
        return driver.manage().window().getSize();
    }

    @Override
    public void setSize(int width, int height) {
        Dimension targetSize = new Dimension(width, height);
        driver.manage().window().setSize(targetSize);
    }

    @Override
    public void setSize(java.awt.Dimension dm) {
        Dimension targetSize = new Dimension(dm.width, dm.height);
        driver.manage().window().setSize(targetSize);
    }

    @Override
    public void close() {
        driver.close();
    }

    @Override
    public void back() {
        driver.navigate().back();
    }

    @Override
    public void forward() {
        driver.navigate().forward();
    }

    @Override
    public void navigateTo(String url) {
        driver.navigate().to(url);
    }

    @Override
    public void navigateTo(URL url) {
        driver.navigate().to(url);
    }

    @Override
    public void getURL() {
        driver.getCurrentUrl();
    }

    @Override
    public void quit() {
        driver.quit();
    }

}
