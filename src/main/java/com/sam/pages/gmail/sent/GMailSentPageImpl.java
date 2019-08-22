package com.sam.pages.gmail.sent;

import com.sam.pages.PageImpl;
import com.sam.pages.base.sent.Sent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GMailSentPageImpl extends PageImpl<Sent> implements GMailSentPage {

    Logger log = LogManager.getLogger(GMailSentPageImpl.class);

    public GMailSentPageImpl() {
        super(new GMailSentImpl());
    }

    @Override
    public String getLastLetterTopic() {
        log.info("Getting last letter topic..");
        return content.getLetterTopic(0);
    }

    @Override
    public String getLastLetterRecipient() {
        log.info("Getting last letter recipient..");
        return content.getLetterRecipient(0);
    }

    @Override
    public String getLastLetterBody() {
        log.info("Getting last letter text..");
        return content.getLetterBody(0);
    }

    @Override
    public Boolean checkIfLetterSent(String recipient, String topic, String body) {
        if (!getLastLetterTopic().equals(topic)) {
            log.info("Letter topic: " + getLastLetterTopic() + " is not equal expected: " + topic);
            return false;
        }
        if (!getLastLetterRecipient().equals(recipient)) {
            log.info("Letter recipient: " + getLastLetterRecipient() + " is not equal expected: " + recipient);
            return false;
        }

        if (!getLastLetterBody().equals(body)) {
            log.info("Letter body: " + getLastLetterBody() + " is not equal expected: " + body);
            return false;
        }
        log.info("Found sent letter with expected recipient: " + recipient + ", topic: " + topic + ", body: " + body);
        return true;
    }

}
