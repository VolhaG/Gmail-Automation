package com.sam.pages.base.sent;

public interface SentPage {

    String getLastLetterTopic();

    String getLastLetterRecipient();

    String getLastLetterBody();

    Boolean checkIfLetterSent(String recepient, String topic, String body);

}
