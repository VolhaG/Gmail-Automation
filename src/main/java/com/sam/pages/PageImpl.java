package com.sam.pages;

import com.sam.annotations.PageVerification;
import com.sam.components.Content;
import com.sam.components.Page;
import com.sam.webdriver.WebDriverProvider;
import com.sam.webelement.ElementWaiters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.net.URL;

public class PageImpl<C extends Content> implements Page<C> {

    protected Logger log = LogManager.getLogger(PageImpl.class);
    protected final C content;


    protected WebDriver getDriver() {
        return WebDriverProvider.getInstance().get();
    }

    protected void delay(int delay) {
        try {
            Thread.sleep(delay * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public PageImpl(C content) {
        this.content = content;
        verify();
    }

    @Override
    public C getContent() {
        return content;
    }

    @Override
    public boolean exists() {
        return content.existsDefElement();
    }

    @Override
    public void refresh() {
        getDriver().navigate().refresh();
    }

    @Override
    public String getUrl() {
        return getDriver().getCurrentUrl();
    }

    @Override
    public Dimension getSize() {
        return getDriver().manage().window().getSize();
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

    private void verify() {
        Class<? extends PageImpl> pageClass = getClass();
        PageVerification pageAnnotation = pageClass.getAnnotation(PageVerification.class);
        if (pageAnnotation == null) {
            throw new IllegalArgumentException("Class " + pageClass.getCanonicalName() + " is not annotated with PageVerification annotation");
        }

        String locator = pageAnnotation.locator();
        By by = null;
        switch (pageAnnotation.byType()) {
            case CSS:
                by = By.cssSelector(locator);
                break;
            case XPATH:
                by = By.xpath(locator);
                break;
            case ID:
                by = By.id(locator);
                break;
            case NAME:
                by = By.name(locator);
                break;
        }
        ElementWaiters.waitForPresence(by, pageAnnotation.delayTime());
    }

}
