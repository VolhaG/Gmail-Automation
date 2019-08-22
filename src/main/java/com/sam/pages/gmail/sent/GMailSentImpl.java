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

    private static Logger log = LogManager.getLogger(GMailSentImpl.class);
    private static final Integer TIME_TO_WAIT = 5;
    private static final By INITIAL_LOCATOR = By.cssSelector("table.F.cf.zt");
    private WrapElement tableIdentifier;
    List<GMailTableColumns> sentTable;

    GMailSentImpl() {
        super(INITIAL_LOCATOR);
        tableIdentifier = findWrapElement("tableIdentifier", By.cssSelector("table.F.cf.zt"), ElementType.DEFAULT);
        initSentTable();
    }

    private List<GMailTableColumns> initSentTable() {
        if (sentTable == null) {
            log.info("Init table with sent letters");
            sentTable = getGMailTable();
        }
        return sentTable;
    }

    @Override
    public String getLetterTopic(int row) {
        String topic = sentTable.get(row).getTopic().getInnerText();
        if (topic.startsWith("(") && (topic.endsWith(")"))) {
            topic = "";
        }
        log.info(new StringBuilder().append("Get topic for letter ").append(row + 1).append(": ").append(topic).toString());
        return topic;
    }

    @Override
    public String getLetterRecipient(int row) {
        String email = sentTable.get(row).getEmail().getAttribute("email");
        log.info(new StringBuilder().append("Get email for letter ").append(row + 1).append(": ").append(email).toString());
        return email;
    }

    @Override
    public String getLetterBody(int row) {
        String letterBody = sentTable.get(row).getLetterBody().getInnerText();
        letterBody = letterBody.replace("\u00a0","");
        if (!letterBody.isEmpty()) {
            letterBody = letterBody.subSequence(1, letterBody.length()).toString();
        }
        log.info(new StringBuilder().append("Get text of letter ").append(row + 1).append(": ").append(letterBody).toString());

        return letterBody;
    }

    @Override
    public Boolean existsDefElement() {
        log.info("Wait for sent page identifier..");
        ElementWaiters.wait(TIME_TO_WAIT);
        return existsDefElement(By.cssSelector("table.F.cf.zt"), TIME_TO_WAIT);
    }

    @Override
    public List<GMailTableColumns> getGMailTable() {
        WebElement el = new WrapElementImpl(By.cssSelector("table.F.cf.zt")).getWebElement();
        List<GMailTableColumns> gmailTable = getTable(el, GMailTableColumns.class);
        for (GMailTableColumns row : gmailTable) {
            row.setEmail(By.cssSelector("span[name]"));
            row.setTopic(By.cssSelector(" td:nth-child(6) span span"));
            row.setLetterBody(By.cssSelector("td:nth-child(6) span.y2"));
        }
        return gmailTable;
    }

}
