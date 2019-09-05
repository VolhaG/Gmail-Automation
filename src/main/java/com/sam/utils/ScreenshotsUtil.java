package com.sam.utils;

import com.sam.webdriver.WebDriverProvider;
import com.sam.webelement.WrapElement;
import io.qameta.allure.Allure;
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

public class ScreenshotsUtil {

    private static Logger log = LogManager.getLogger(ScreenshotsUtil.class);
    private static WebDriverProvider provider = WebDriverProvider.getInstance();

    private static void writeScreenshotToFile(File srcFile, File fileWithPath, String attachmentName) {
        try {
            FileUtils.copyFile(srcFile, fileWithPath);
            Allure.addAttachment(attachmentName, "img/png", FileUtils.openInputStream(srcFile), ".png");
            log.info("Find screenshot in {}", fileWithPath);
        } catch (IOException ex) {
            ex.printStackTrace();
            log.info("Failed to capture screenshot due to exception");
        }
    }

    private static void takeScreenshot(String attachmentName, String fileWithPath) {
        WebDriver driver = provider.get();
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
        writeScreenshotToFile(srcFile, new File(fileWithPath), attachmentName);
    }

    private static void takeElementScreenshot(File fileWithPath, WrapElement el) {
        WrapsDriver wrapsDriver = (WrapsDriver) el;
        File srcFile = ((TakesScreenshot) wrapsDriver.getWrappedDriver()).getScreenshotAs(OutputType.FILE);
        writeScreenshotToFile(srcFile, fileWithPath, el.toString());
    }

    /**
     * screenShot method is invoked whenever the Testcase is Failed.
     */
    @Attachment(value = "Page screenshot", type = "img/png")
    public static byte[] saveScreenshot() {
        WebDriver driver = provider.get();
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        return scrShot.getScreenshotAs(OutputType.BYTES);
    }

    /**
     * screenShot method is invoked whenever the Testcase is Failed.
     * @param methodName
     */
    public static void takeScreenshot(String methodName) {
        String screenshotFilePath = new StringBuilder().append("./screenshots/").append(methodName).append(System.currentTimeMillis()).toString();
        String attachmentName = new StringBuilder().append(methodName).append(System.currentTimeMillis()).toString();
        takeScreenshot(attachmentName, screenshotFilePath);
    }

    /**
     * screenShot method is invoked whenever the Testcase is Failed.
     * @param el
     * @param methodName
     */
    public static void takeElementScreenshot(WrapElement el, String methodName) {
        String filePath = new StringBuilder().append("./screenshots/").append(methodName).append(System.currentTimeMillis()).toString();
        File fileWithPath =  new File(filePath);
        takeElementScreenshot(fileWithPath, el);
    }

}
