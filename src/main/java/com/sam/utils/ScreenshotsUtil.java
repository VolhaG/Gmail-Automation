package com.sam.utils;

import com.sam.webdriver.WebDriverProvider;
import com.sam.webelement.WrapElement;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WrapsDriver;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class ScreenshotsUtil {

    private static Logger log = LogManager.getLogger(ScreenshotsUtil.class);
    private static WebDriverProvider provider = WebDriverProvider.getInstance();
    private static String screenshotFilePath;

    private static void writeScreenshotToFile(File srcFile, String fileWithPath) {
        File destFile = new File(fileWithPath);
        try {
            FileUtils.copyFile(srcFile, destFile);
            log.info("Find screenshot in {}", destFile);
            screenshotFilePath = fileWithPath;
        } catch (IOException ex) {
            ex.printStackTrace();
            log.info("Failed to capture screenshot due to exception");
        }
    }

    @Attachment(value = "Page screenshot for {0} and method {1}", type = "img/png")
    public static void takeScreenshot(String fileWithPath, String methodName) {
        WebDriver driver = provider.get();
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        screenshotFilePath = fileWithPath;
        writeScreenshotToFile(srcFile, fileWithPath);
    }

    public static void takeScreenshot(String methodName) {
        StringBuilder builder = new StringBuilder();
        String fileWithPath = builder.append("./screenshots/").append(methodName).append(System.currentTimeMillis()).toString();
        screenshotFilePath = fileWithPath;
        takeScreenshot(fileWithPath, methodName);
    }

    @Attachment(value = "Element screenshot in file:{0} for element: {1} and method: {2}", type = "img/png")
    public static void takeElementScreenshot(String fileWithPath, WrapElement el, String methodName) {
        WrapsDriver wrapsDriver = (WrapsDriver) el;
        File srcFile = ((TakesScreenshot) wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
        screenshotFilePath = fileWithPath;
        writeScreenshotToFile(srcFile, fileWithPath);
    }

    public static void takeElementScreenshot(WrapElement el, String methodName) {
        StringBuilder builder = new StringBuilder();
        String fileWithPath = builder.append("./screenshots/").append(methodName).append(System.currentTimeMillis()).toString();
        screenshotFilePath = fileWithPath;
        takeElementScreenshot(fileWithPath, el, methodName);
    }

    public static String getScreenshotsPath() {
        return screenshotFilePath;
    }

}
