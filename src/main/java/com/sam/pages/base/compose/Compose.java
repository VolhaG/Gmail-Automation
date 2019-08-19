package com.sam.pages.base.compose;

import com.sam.components.Content;

public interface Compose extends Content {

    void writeLetter(String startBody, String body, String endBody);

    void setTopic(String subject);

    void setRecipient(String subject);

    void sendLetter();

    void writeLetter(String body);

    void close();

}
