package com.sam.utils;

import com.sam.webdriver.WebDriverProvider;
import com.sam.webelement.WrapElement;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WrapsDriver;

import java.io.File;
import java.io.IOException;

public class ScreenshotsUtil {

    private static Logger log = LogManager.getLogger("ScreenshotsUtil");

    private static WebDriverProvider provider = WebDriverProvider.getInstance();

    private static void writeScreenshotToFile(File srcFile, String fileWithPath) {
        File destFile = new File(fileWithPath);
        try {
            FileUtils.copyFile(srcFile, destFile);
            log.info("Find screenshot in " + destFile);
        } catch (IOException ex) {
            ex.printStackTrace();
            log.info("Failed to capture screenshot due to exception");
        }
    }

    public static void takeScreenshot(String fileWithPath) {
        WebDriver driver = provider.get();
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        writeScreenshotToFile(srcFile, fileWithPath);
    }

    public static void takeScreenshot() {
        String fileWithPath = "./screenshots/" + provider.get().getPageSource().getClass() + System.currentTimeMillis();
        takeScreenshot(fileWithPath);
    }

    public static void takeElementScreenshot(WrapElement el, String fileWithPath) {
        WrapsDriver wrapsDriver = (WrapsDriver) el;
        File srcFile = ((TakesScreenshot) wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
        writeScreenshotToFile(srcFile, fileWithPath);
    }

    public static void takeElementScreenshot(WrapElement el) {
        String fileWithPath = "./screenshots/" + provider.get().getPageSource().getClass() + System.currentTimeMillis();
        takeElementScreenshot(el, fileWithPath);
    }

}
