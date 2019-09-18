package com.sam.pages;

import com.sam.annotations.ElementVerification;
import com.sam.components.Content;
import com.sam.components.Page;
import com.sam.webdriver.WebDriverProvider;
import com.sam.webelement.ElementWaiters;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.assertj.core.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.Objects;

public class PageImpl<C extends Content> implements Page<C> {

    private static Logger log = LogManager.getLogger(PageImpl.class);
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
        Class<? extends PageImpl> pageClass = getClass();
        verifyAnnotatedElements(pageClass);
    }

    @Override
    public C getContent() {
        return content;
    }

    @Override
    public boolean exists() {
        return content.existVerificationElement();
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

    @Step("Element verification for page: {0}")
    private <T extends PageImpl> void verifyAnnotatedElements(Class<T> pageClass) {
        log.info("Try to verify element for page: {} ..", pageClass);
        Field[] fieldsList = pageClass.getDeclaredFields();
        for (Field field : fieldsList) {
            ElementVerification verificationAnnotation = field.getAnnotation(ElementVerification.class);
            if (verificationAnnotation != null) {
                try {
                    log.info(verificationAnnotation.description());
                    log.info("Try to find annotated element");
                    By by = (By) field.get(this);
                    log.info("Annotated element:{} is found", by);
                    WebElement el = ElementWaiters.waitForPresence(Objects.requireNonNull(by),
                            verificationAnnotation.delayTime());
                    Assertions.assertThat(el != null).isTrue().as("Element: " + by
                            + " verification has been passed");
                    log.info("Element: {} verification for page: {} passed successful", by, pageClass);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
