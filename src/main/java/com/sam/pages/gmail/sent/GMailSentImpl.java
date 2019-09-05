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
    public String getLetterTopic(int rowIndex) {
        String topic = sentRows.get(rowIndex).getTopic().getInnerText();
        if (topic.startsWith("(") && (topic.endsWith(")"))) {
            topic = "";
        }
        log.info("Get topic of letter {}: {}", (rowIndex + 1),  topic);
        return topic;
    }

    @Override
    public String getLetterRecipient(int rowIndex) {
        String email = sentRows.get(rowIndex).getEmail().getAttribute("email");
        log.info("Get email of letter {}: {}", (rowIndex + 1), email);
        return email;
    }

    @Override
    public String getLetterBody(int rowIndex) {
        String letterBody = sentRows.get(rowIndex).getLetterBody().getInnerText();
        letterBody = letterBody.replace("\u00a0", "");
        if (!letterBody.isEmpty()) {
            letterBody = letterBody.subSequence(2, letterBody.length()).toString();
        }
        log.info("Get text of letter {}: {}", (rowIndex + 1) , letterBody);

        return letterBody;
    }

    @Step("Sent page verification")
    @Override
    public boolean existVerificationElement() {
        log.info("Wait for sent page identifier..");
        ElementWaiters.wait(DELAY_TIME);
        return existVerificationElement(By.cssSelector("table.F.cf.zt"), DELAY_TIME);
    }

}
