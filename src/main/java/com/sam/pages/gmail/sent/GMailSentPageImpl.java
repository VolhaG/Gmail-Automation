package com.sam.pages.gmail.sent;

import com.sam.annotations.ElementVerification;
import com.sam.pages.PageImpl;
import com.sam.pages.base.sent.Sent;
import com.sam.entities.Letter;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

public class GMailSentPageImpl extends PageImpl<Sent> implements GMailSentPage {

    private static Logger log = LogManager.getLogger(GMailSentPageImpl.class);

    @ElementVerification(description = "Root element verification on sent page")
    public static final By rootElement = By.cssSelector("table.F.cf.zt");

    public GMailSentPageImpl() {
        super(new GMailSentImpl());
    }

    @Step("Get last sent letter")
    @Override
    public Letter getLastLetter(){
        return content.getLetter(0);
    }

    @Step("Check if letter: {0} was sent")
    @Override
    public boolean checkIfLetterSent(Letter letter) {
        Letter lastLetter = getLastLetter();
        if (!lastLetter.equals(letter)) {
            log.info("Letter: {} is not equal expected: {}", lastLetter, letter);
            return false;
        }
        log.info("Found last sent letter: {}", letter);
        return true;
    }

}
