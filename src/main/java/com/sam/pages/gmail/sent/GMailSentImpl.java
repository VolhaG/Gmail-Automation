package com.sam.pages.gmail.sent;

import com.sam.utils.gmail.GMailRow;
import com.sam.webelement.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.List;

public class GMailSentImpl extends WrapElementImpl implements GMailSent {

    private static Logger log = LogManager.getLogger(GMailSentImpl.class);
    private static final int DELAY_TIME = 1;
    private static final By INITIAL_LOCATOR = By.cssSelector("div.ae4.UI table.F.cf.zt");
    private Table tableIdentifier;
    private List<GMailRow> sentTable;

    GMailSentImpl() {
        super(INITIAL_LOCATOR);
        tableIdentifier = new TableImpl("tableIdentifier", By.cssSelector("div.ae4.UI table.F.cf.zt"));
        initSentTable();
    }

    private List<GMailRow> initSentTable() {
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
        letterBody = letterBody.replace("\u00a0", "");
        if (!letterBody.isEmpty()) {
            letterBody = letterBody.subSequence(1, letterBody.length()).toString();
        }
        log.info(new StringBuilder().append("Get text of letter ").append(row + 1).append(": ").append(letterBody).toString());

        return letterBody;
    }

    @Override
    public boolean existsDefElement() {
        log.info("Wait for sent page identifier..");
        ElementWaiters.wait(DELAY_TIME);
        return existsDefElement(By.cssSelector("table.F.cf.zt"), DELAY_TIME);
    }

    @Override
    public List<GMailRow> getGMailTable() {
        List<GMailRow> gmailTable = tableIdentifier.getTable(GMailRow.class);
        for (GMailRow row : gmailTable) {
            row.setEmail(By.cssSelector("span[name]"), row);
            row.setTopic(By.cssSelector(" td:nth-child(6) span span"), row);
            row.setLetterBody(By.cssSelector("td:nth-child(6) span.y2"), row);
        }
        return gmailTable;
    }

}
