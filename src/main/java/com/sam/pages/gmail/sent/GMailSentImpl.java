package com.sam.pages.gmail.sent;

import com.sam.utils.gmail.GMailRow;
import com.sam.webelement.*;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.util.List;

public class GMailSentImpl extends TableImpl implements GMailSent {

    private static Logger log = LogManager.getLogger(GMailSentImpl.class);
    private static final int DELAY_TIME = 1;
    private static final By INITIAL_LOCATOR = By.cssSelector("div.ae4.UI table.F.cf.zt");
    private final List<GMailRow> sentRows;

    GMailSentImpl() {
        super("GMailSentTable", INITIAL_LOCATOR);
        sentRows = getRows(GMailRow.class);
    }

    @Override
    public String getLetterTopic(int row) {
        String topic = sentRows.get(row).getTopic().getInnerText();
        if (topic.startsWith("(") && (topic.endsWith(")"))) {
            topic = "";
        }
        log.info("Get topic of letter {}: {}", (row + 1),  topic);
        return topic;
    }

    @Override
    public String getLetterRecipient(int row) {
        String email = sentRows.get(row).getEmail().getAttribute("email");
        log.info("Get email of letter {}: {}", (row + 1), email);
        return email;
    }

    @Override
    public String getLetterBody(int row) {
        String letterBody = sentRows.get(row).getLetterBody().getInnerText();
        letterBody = letterBody.replace("\u00a0", "");
        if (!letterBody.isEmpty()) {
            letterBody = letterBody.subSequence(2, letterBody.length()).toString();
        }
        log.info("Get text of letter {}: {}", (row + 1) , letterBody);

        return letterBody;
    }

    @Step("Sent page verification")
    @Override
    public boolean existsDefElement() {
        log.info("Wait for sent page identifier..");
        ElementWaiters.wait(DELAY_TIME);
        return existsDefElement(By.cssSelector("table.F.cf.zt"), DELAY_TIME);
    }

}
