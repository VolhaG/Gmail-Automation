package com.sam.pages.gmail.sent;

import com.sam.pages.PageImpl;
import com.sam.pages.base.sent.Sent;

public class GMailSentPageImpl extends PageImpl<Sent> implements GMailSentPage {

    public GMailSentPageImpl() {
        super(new GMailSentImpl());
    }

    @Override
    public String getLastLetterTopic() {
        return content.getLetterTopic(0);
    }

    @Override
    public String getLastLetterRecipient() {
        return content.getLetterRecipient(0);
    }

    @Override
    public String getLastLetterBody() {
        return content.getLetterBody(0);
    }

    @Override
    public Boolean checkIfLetterSent(String recipient, String topic, String body) {
        if ((getLastLetterTopic().equals(topic)) && (getLastLetterRecipient().equals(recipient)) && (getLastLetterBody().equals(body))) {
            return true;
        }
        return false;
    }

}
