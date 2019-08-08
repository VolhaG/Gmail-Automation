package com.sam.pages.base.compose;

import com.sam.pages.base.main.Main;

public interface Compose extends Main {

    void writeLetter(String startBody, String body, String endBody);

    void setSubject(String subject);

    void setRecipient(String subject);

    void sendLetter();

    void writeLetter(String body);

    void close();

}
