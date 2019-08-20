package com.sam.pages.gmail.sent;

import com.sam.utils.gmail.GMailTableColumns;
import com.sam.webelement.ElementType;
import com.sam.webelement.ElementWaiters;
import com.sam.webelement.WrapElement;
import com.sam.webelement.WrapElementImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GMailSentImpl extends WrapElementImpl implements GMailSent {

    private static final Integer TIME_TO_WAIT = 5;
    private static final By INITIAL_LOCATOR = By.cssSelector("table.F.cf.zt");
    private static Logger log = LogManager.getLogger();
    private WrapElement pageIdentifier;
    private WrapElement tableIdentifier;

    GMailSentImpl() {
        super(INITIAL_LOCATOR);
        tableIdentifier = findWrapElement("tableIdentifier", By.cssSelector("table.F.cf.zt"), ElementType.DEFAULT);
        pageIdentifier = findWrapElement("pageIdentifier", INITIAL_LOCATOR, ElementType.DEFAULT);
    }

    @Override
    public String getLetterTopic(int row) {
        return getGMailTable().get(row).getTopic().getInnerText();
    }

    @Override
    public String getLetterRecipient(int row) {
        return getGMailTable().get(row).getEmail().getInnerText();
    }

    @Override
    public String getLetterBody(int row) {
        return getGMailTable().get(row).getLetterBody().getInnerText();
    }

    @Override
    public Boolean existsDefElement() {
        log.info("Wait for page identifier " + pageIdentifier.getElementName());
        ElementWaiters.wait(TIME_TO_WAIT);
        return existsDefElement(INITIAL_LOCATOR, TIME_TO_WAIT);
    }

    @Override
    public List<GMailTableColumns> getGMailTable() {
        WebElement el = new WrapElementImpl(By.cssSelector("table.F.cf.zt")).getWebElement();
        List<GMailTableColumns> gmailTable = getTable(el, GMailTableColumns.class);
        for (GMailTableColumns row: gmailTable ) {
            row.setEmail(By.cssSelector("span[name]"));
            row.setTopic(By.cssSelector(" td:nth-child(6) span span"));
            row.setLetterBody(By.cssSelector("td:nth-child(6) span:nth-child(3) span"));
        }
        return gmailTable;
    }

}
